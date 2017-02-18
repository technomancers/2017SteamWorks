package org.usfirst.frc.team1758.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.vision.PegPipeline;

import edu.wpi.cscore.CvSink;


import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Vision extends Subsystem {
	private CameraMode mode;
	private UsbCamera gear_camera;
	private CameraServer cServer;
	private Relay cameraRelay;
	private VisionThread boilerThread, gearThread;
	private double centerX;
	private final Object imgLock = new Object();
	private int numRectangles;
	private boolean seesSomething;
	private double singleX;


	public enum CameraMode {
		FRONT, BACK;
	}

	public Vision() {
		cameraRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY);
		cServer = CameraServer.getInstance();
		gear_camera = new UsbCamera("gear_camera", RobotMap.GEAR_CAMERA_PORT);
		configureCameras();
		gearThread = new VisionThread(gear_camera, new PegPipeline(), pipeline -> {
			if (!pipeline.findContoursOutput().isEmpty()) {
					numRectangles = pipeline.findContoursOutput().size();
					if(numRectangles == 2 || numRectangles == 3)
					{
						seesSomething = true;
						int leftMostX = RobotMap.CAMERA_WIDTH;
						int rightMostX = 0;
						for (MatOfPoint mop : pipeline.findContoursOutput()) {
							Rect r = Imgproc.boundingRect(mop);
							int leftX = r.x;
							int rightX = r.x + r.width;
							if(leftMostX > leftX){
								leftMostX = leftX;
							}
							if(rightMostX < rightX){
								rightMostX = rightX;
							}
						}
						synchronized (imgLock){
							centerX = (leftMostX + rightMostX)/2;
						}
					} else
					{
						synchronized (imgLock){
							seesSomething = false;
						}
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
	public int getNumberOfRectangles()
	{
		synchronized (imgLock){
			return numRectangles;
		}
	}

	public void configureCameras() {
		gear_camera.setExposureManual(5);
		gear_camera.setResolution(RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);
	}

	public void startGearThread() {
		gearThread.start();
	}

	public void startBoilerThred(){
		boilerThread.start();
	}
	public boolean getSeesSomething()
	{
		synchronized (imgLock){
			return seesSomething;
		}
	}
	public double getCenterX() {
		synchronized (imgLock) {
			return centerX;
		}
	}

	public void startAutomaticCapture() {
		cServer.startAutomaticCapture(gear_camera);
	}
}
