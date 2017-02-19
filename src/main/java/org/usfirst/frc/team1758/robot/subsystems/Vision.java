package org.usfirst.frc.team1758.robot.subsystems;

import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger;
	private Relay lightRelay;
	private UsbCamera gearCamera;
	private CvSink gearSink;
	private CvSource outputStream;
	private MjpegServer cameraServer;
	private Thread visionThread;
	private PegPipeline pegPipeline;

	public Vision() {
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Vision subsystem created");
		lightRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY);
		pegPipeline = new PegPipeline();
		initCamera();
	}

	public void initDefaultCommand() {
	}

	public void turnOnLights() {
		logger.debug("Turning on light");
		lightRelay.set(Value.kForward);
	}
	public void turnOffLights(){
		logger.debug("Turning off light");
		lightRelay.set(Value.kReverse);
	}
	public void initCamera(){
		logger.debug("Initializing Camera");
		if(gearCamera == null){
			logger.debug("Creating a new gear camera");
			gearCamera = new UsbCamera("gearCamera", RobotMap.GEAR_CAMERA_PORT);
			gearCamera.setExposureManual(RobotMap.CAMERA_EXPOSURE);
			gearCamera.setBrightness(RobotMap.CAMERA_BRIGHTNESS);
			gearCamera.setVideoMode(RobotMap.CAMERA_PIXEL_FORMAT, RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT, RobotMap.CAMERA_FPS);
		}

		if(gearSink == null){
			logger.debug("Creating a new gear sink");
			gearSink = new CvSink("gearSink");
			gearSink.setEnabled(true);
			gearSink.setSource(gearCamera);
		}

		if(outputStream == null){
			logger.debug("Creating a new output stream");
			outputStream = new CvSource("Cameras", PixelFormat.kMJPEG, RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT, RobotMap.CAMERA_FPS);
		}
		
		//May need to create a server for each camera
		if(cameraServer == null){
			logger.debug("Creating a new camera server");
			cameraServer = new MjpegServer("serveCameras", RobotMap.MJPEG_SERVER_PORT);
			cameraServer.setSource(outputStream);
		}
	}
	public void startVisionThread(){
		if(visionThread == null){
			logger.debug("Creating a new vision thread");
			visionThread = new Thread(() -> {
				Mat image = new Mat(RobotMap.CAMERA_HEIGHT, RobotMap.CAMERA_WIDTH, 6);
				while(!Thread.interrupted()){
					logger.trace("Grabbing frame from gear sink");
					gearSink.grabFrameNoTimeout(image);
					pegPipeline.process(image);
					ArrayList<MatOfPoint> mops = pegPipeline.filterContoursOutput();
					for(MatOfPoint mop : mops){
						Rect r = Imgproc.boundingRect(mop);
						Imgproc.rectangle(image, r.tl(), r.br(), new Scalar(255, 0, 255));
					}
					outputStream.putFrame(image);
				}		
			});
		}
		logger.debug("Starting Vision Thread");
		visionThread.start();
	}
	public void stopVisionThread(){
		logger.debug("Stopping vision thread");
		if(visionThread != null && visionThread.getState() == Thread.State.RUNNABLE){
			visionThread.interrupt();
		}else{
			logger.warn("Vision thread is not created or not runnable to stop");
		}
	}
}
