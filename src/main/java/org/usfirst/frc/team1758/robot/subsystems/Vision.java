package org.usfirst.frc.team1758.robot.subsystems;

import org.opencv.core.Mat;
import org.usfirst.frc.team1758.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
			UsbCamera gearCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.GEAR_CAMERA_PORT);
			gearCamera.setResolution(RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);
			gearCamera.setExposureManual(5);

			CvSink gearSink = CameraServer.getInstance().getVideo(gearCamera);
			CvSource outputStream = CameraServer.getInstance().putVideo("Cameras", RobotMap.CAMERA_WIDTH,
					RobotMap.CAMERA_HEIGHT);

			Mat image = new Mat();
			gearSink.setEnabled(true);
			while (!Thread.interrupted()) {
				gearSink.grabFrame(image);
				//Do stuff Here
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
