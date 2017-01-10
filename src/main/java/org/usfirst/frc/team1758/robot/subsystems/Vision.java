package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap; 
import org.usfirst.frc.team1758.robot.commands.ToggleLight; 
import org.usfirst.frc.team1758.robot.commands.ToggleLight.LightMode;

import edu.wpi.first.wpilibj.CameraServer;
 
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;



public class Vision extends Subsystem
{
	private boolean isLightOn;
	private VisionThread frontVisionThread, backVisionThread;
	private Relay cameraLightRelay;
	AxisCamera frontCamera, backCamera;
	UsbCamera frontCameraUSB, backCameraUSB;
	CameraServer serverCamera;
	
	

 public Vision() 
  { 
    cameraLightRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY); 
    isLightOn = false; 
		frontCamera = new AxisCamera("frontCamera", RobotMap.ipFrontCamera);
		backCamera = new AxisCamera("backCamera", RobotMap.ipBackCamera);
		configureFrontCamera();
		serverCamera = CameraServer.getInstance();
		serverCamera.startAutomaticCapture(0);

	//	frontCameraUSB = CameraServer.getInstance().
		//backCameraUSB = CameraServer.getInstance().addServer("backCameraUSB", port);
		

  } 


	protected void initDefaultCommand() 
  { 
    setDefaultCommand(new ToggleLight(LightMode.ON)); 
  } 
	public void switchToBackCamera()
	{
		serverCamera.startAutomaticCapture(1);
	}

	public void switchToFrontCamera()
	{
		serverCamera.startAutomaticCapture(0);
	}
	public void startFrontCamera()
	{
		// Pipline is from GRIP
		// frontVisionThread = new VisionThread(frontCamera, new Pipeline() , pipeline -> {

		// });
	}
  public void turnOnLight(){ 
    cameraLightRelay.set(Value.kOn); 
    isLightOn = true; 
  } 
  public void turnOffLight(){ 
    cameraLightRelay.set(Value.kOff); 
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
	public void configureBackCmera()
	{
		backCamera.setResolution(RobotMap.imageWidth, RobotMap.imageHeight);
		backCamera.setExposureManual(1);
	}
}
	


