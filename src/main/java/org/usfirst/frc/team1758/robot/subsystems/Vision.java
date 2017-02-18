package org.usfirst.frc.team1758.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.vision.PegPipeline;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;


import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {
	private Relay cameraRelay;
	private Thread visionThread;
	private UsbCamera gearCamera;
	private CvSink gearSink;
	private MjpegServer cameraServer;
	private CvSource outputStream;
	private PegPipeline pegPipeline;
	private int numRect;
	private double centerX;
	private boolean seesTarget;

	public Vision() {
		cameraRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY);
		pegPipeline = new PegPipeline();
		initCameras();
	}

	protected void initDefaultCommand() {
	}

	private void initCameras(){
		if(gearCamera == null){
			gearCamera = new UsbCamera("gearCamera", RobotMap.GEAR_CAMERA_PORT);
			gearCamera.setExposureManual(RobotMap.CAMERA_EXPOSURE);
			gearCamera.setBrightness(RobotMap.CAMERA_BRIGHTNESS);
			gearCamera.setVideoMode(RobotMap.CAMERA_PIXEL_FORMAT, RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT, RobotMap.CAMERA_FPS);
		}

		if(gearSink == null){
			gearSink = new CvSink("gearSink");
			gearSink.setEnabled(true);
			gearSink.setSource(gearCamera);
		}

		if(outputStream == null){
			outputStream = new CvSource("Cameras", PixelFormat.kMJPEG, RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT, RobotMap.CAMERA_FPS);
		}

		//May need to create a server for each camera
		if(cameraServer == null){
			cameraServer = new MjpegServer("serveCameras", RobotMap.MJPEG_SERVER_PORT);
			cameraServer.setSource(outputStream);
		}
	}

	public void turnOnCameraLight() {
		cameraRelay.set(Value.kForward);
	}

	public void turnOffCameraLight() {
		cameraRelay.set(Value.kReverse);
	}

	public void startVisionThread() {
		if(visionThread == null){
			visionThread = new Thread(() -> {
				Mat image = new Mat();
				while (!Thread.interrupted()) {
					gearSink.grabFrame(image);
					pegPipeline.process(image);
					ArrayList<MatOfPoint> mops = pegPipeline.filterContoursOutput();
					if(!mops.isEmpty())
					{
						numRect = mops.size();
						if(numRect == 2 || numRect == 3)
						{
							seesTarget = true;
							Point tlMost = new Point(RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);
							Point brMost = new Point();
							for(MatOfPoint mop: mops)
							{
								Rect r = Imgproc.boundingRect(mop);
								Imgproc.rectangle(image, r.tl(), r.br(), new Scalar(255, 255, 255));
								if(tlMost.x > r.tl().x || tlMost.y > r.tl().y){
									tlMost = r.tl();
								}
								if(brMost.x < r.br().x || brMost.y < r.br().y){
									brMost = r.br();
								}
							}
							Imgproc.rectangle(image, tlMost, brMost, new Scalar(255,0,0));
							centerX = (tlMost.x + brMost.x) / 2.0;
						}
					}
					Imgproc.putText(image, "testing", new Point(0,0), Core.FONT_HERSHEY_PLAIN, 1, new Scalar(255, 255, 255));
					outputStream.putFrame(image);
				}
			});
		}
		visionThread.start();
	}

	public void stopVisionThread(){
		if(visionThread != null && visionThread.getState() == Thread.State.RUNNABLE){
			visionThread.interrupt();
		}
	}

	public double getCenterX(){
		return centerX;
	}

	public boolean doesSeeTarget(){
		return seesTarget;
	}
	
	public int getNumberOfRectangles(){
		return numRect;
	}
}
