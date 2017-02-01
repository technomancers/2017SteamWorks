package org.usfirst.frc.team1758.robot.subsystems;
import org.usfirst.frc.team1758.robot.RobotMap;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
public class Vision extends Subsystem
{
	CameraMode mode;
	UsbCamera front_camera, back_camera;
	CameraServer cServer;
	public enum CameraMode
	{
		FRONT, BACK;
	}
	public Vision() 
	{
		cServer = CameraServer.getInstance();
		front_camera = new UsbCamera("front_camera", RobotMap.FRONT_CAMERA_PORT);
		front_camera.setExposureManual(0);
		back_camera = new UsbCamera("back_camera", RobotMap.BACK_CAMERA_PORT);
		back_camera.setExposureManual(0);
	} 
	protected void initDefaultCommand() 
  { 
  }
	public void startAutomaticCapture()
	{
		cServer.startAutomaticCapture(front_camera);
		cServer.startAutomaticCapture(back_camera);
	}
}
	


