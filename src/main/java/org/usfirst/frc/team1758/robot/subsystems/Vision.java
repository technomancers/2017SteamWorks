package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;

public class Vision extends Subsystem
{
	private boolean isLightOn;
	private AxisCamera activeCamera, frontCamera;
	private UsbCamera backCamera, backCameraTwo;
	private Relay cameraLightRelay;
	CameraServer serverCamera;
	CameraMode mode;
	public enum CameraMode
	{
		FRONT, BACK;
	}
	public Vision() 
	{ 
    cameraLightRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY); 
		serverCamera = CameraServer.getInstance();
		frontCamera = new AxisCamera("frontCamera", RobotMap.ipFrontCamera);
		backCamera = new UsbCamera("backCamera", 1);
		backCameraTwo = new UsbCamera("backCameraTwo", 0);
		addCameras();
    isLightOn = false;
	} 
	protected void initDefaultCommand() 
  { 
		//setDefaultCommand(new ToggleLight(LightMode.ON));
  }
	public void addCameras()
	{
		serverCamera.addCamera(frontCamera);
		serverCamera.addCamera(backCamera);
		serverCamera.startAutomaticCapture(backCamera);
		serverCamera.startAutomaticCapture(frontCamera);

	}

  public void turnOnLight(){ 
    cameraLightRelay.set(Value.kForward); 
    isLightOn = true; 
  } 
  public void turnOffLight(){ 
    cameraLightRelay.set(Value.kReverse); 
    isLightOn = false; 
  } 
  public void toggleLight(){ 
    if(isLightOn){ 
      turnOffLight(); 
    }else{ 
      turnOnLight(); 
    } 
	}
	public void configureCameras()
	{
	
	}
}
	


