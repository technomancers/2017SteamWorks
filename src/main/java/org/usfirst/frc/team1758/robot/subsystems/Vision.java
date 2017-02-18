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
	private double centerX;
	private final Object imgLock = new Object();
	private int numRectangles;
	private boolean seesSomething;

	public Vision() {
		cameraRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY);
		visionThread = new Thread(() -> {
			UsbCamera gearCamera = new UsbCamera("gearCamera", RobotMap.GEAR_CAMERA_PORT);
			gearCamera.setResolution(RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);
			gearCamera.setExposureManual(5);
			gearCamera.setFPS(30);
			CvSink gearSink = new CvSink("gearSink");
			gearSink.setSource(gearCamera);

			MjpegServer server = new MjpegServer("serve_Cameras", RobotMap.MJPEG_SERVER_PORT);
			CvSource outputStream = new CvSource("Cameras", PixelFormat.kMJPEG, RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT, 30);
			server.setSource(outputStream);

			PegPipeline gearPipeline = new PegPipeline();
			Mat image = new Mat();

			while (!Thread.interrupted()) {
				gearSink.grabFrame(image);
				gearPipeline.process(image);
				ArrayList<MatOfPoint> mops = gearPipeline.filterContoursOutput();
				if(!mops.isEmpty())
				{
					if(mops.size() == 2 || mops.size() == 3)
					{
						for(MatOfPoint mop: mops)
						{
							Rect r = Imgproc.boundingRect(mop);
							Core.rectangle(image, r.tl(), r.br(), new Scalar(255, 255, 255));;
						}
					}
				}
				Core.putText(image, "testing", new Point(0,0), Core.FONT_HERSHEY_PLAIN, 1, new Scalar(255,255,255));
				numRectangles = mops.size();
				outputStream.putFrame(image);
			}
		});
	}

	protected void initDefaultCommand() {
	}

	public void turnOnCameraLight() {
		cameraRelay.set(Value.kForward);
	}

	public void turnOffCameraLight() {
		cameraRelay.set(Value.kReverse);
	}

	public int getNumberOfRectangles() {
		synchronized (imgLock) {
			return numRectangles;
		}
	}

	public void startVisionThread() {
		visionThread.start();
	}

	public boolean getSeesSomething() {
		synchronized (imgLock) {
			return seesSomething;
		}
	}

	public double getCenterX() {
		synchronized (imgLock) {
			return centerX;
		}
	}
}
