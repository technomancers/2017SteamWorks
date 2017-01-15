package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.AxisCamera;

public class Vision extends Subsystem
{
	private boolean isLightOn;
	private AxisCamera activeCamera, frontCamera, backCamera;
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
		frontCamera = new AxisCamera("activeCamera", RobotMap.ipFrontCamera);
		backCamera = new AxisCamera("activeCamera", RobotMap.ipBackCamera);
    isLightOn = false;
	} 
	protected void initDefaultCommand() 
  { 
		//setDefaultCommand(new ToggleLight(LightMode.ON));
  }
	public void switchToCamera(CameraMode cm)
	{
		switch(cm){
			case BACK:
				activeCamera = backCamera;
				break;
			default:
				activeCamera = frontCamera;
				break;
		}	
		serverCamera.removeCamera("activeCamera");
		serverCamera.addCamera(activeCamera);
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
	public void configureCameras()
	{
		activeCamera.setResolution(RobotMap.imageWidth, RobotMap.imageHeight);
		activeCamera.setExposureManual(1);
	}
}
	


