package org.usfirst.frc.team1758.robot.subsystems;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.vision.TestPipeline;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision extends Subsystem {
	private CameraMode mode;
	private UsbCamera front_camera, back_camera;
	private CameraServer cServer;
	private Relay cameraRelay;
	private VisionThread boilerThread, gearThread;
	private double centerX;
	private final Object imgLock = new Object();

	public enum CameraMode {
		FRONT, BACK;
	}

	public Vision() {
		cameraRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY);
		cServer = CameraServer.getInstance();
		front_camera = new UsbCamera("front_camera", RobotMap.FRONT_CAMERA_PORT);
		back_camera = new UsbCamera("back_camera", RobotMap.BACK_CAMERA_PORT);
		configureCameras();
		boilerThread = new VisionThread(front_camera, new TestPipeline(), pipeline -> {
			if (!pipeline.findContoursOutput().isEmpty()) {
				Rect r = Imgproc.boundingRect(pipeline.findContoursOutput().get(0));
				synchronized (imgLock) {
					centerX = r.x + (r.width / 2);
				}
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

	public void configureCameras() {
		front_camera.setExposureManual(10);
		front_camera.setResolution(RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);
		back_camera.setExposureManual(10);
		back_camera.setResolution(RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);
	}

	public void startThread() {
		boilerThread.start();
	}

	public double getCenterX() {
		synchronized (imgLock) {
			return centerX;
		}
	}

	public void startAutomaticCapture() {
		cServer.startAutomaticCapture(front_camera);
		cServer.startAutomaticCapture(back_camera);
	}
}
