package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.ToggleLight;
import org.usfirst.frc.team1758.robot.commands.ToggleLight.LightMode;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;

public class Vision extends Subsystem
{
	private boolean isLightOn;
	private AxisCamera frontCamera;
	private Relay cameraLightRelay;
	UsbCamera frontCameraUSB, backCameraUSB;
	CameraServer serverCamera;
	public Vision() 
	{ 
    cameraLightRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY); 
		serverCamera = CameraServer.getInstance();
    isLightOn = false;
		frontCamera = new AxisCamera("frontCamera", RobotMap.ipFrontCamera);
		configureFrontCamera();
		switchToFrontCamera();
	} 
	protected void initDefaultCommand() 
  { 
		setDefaultCommand(new ToggleLight(LightMode.ON));
  }
	public void switchToFrontCamera()
	{
		serverCamera.addCamera(frontCamera);	
	}

	public void startCapture(){
		serverCamera.startAutomaticCapture();
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
	public void configureFrontCamera()
	{
		frontCamera.setResolution(RobotMap.imageWidth, RobotMap.imageHeight);
		frontCamera.setExposureManual(1);
	}
}
	


