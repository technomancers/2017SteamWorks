package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.SwitchCamera;
 
import org.usfirst.frc.team1758.robot.commands.ToggleLight;
import org.usfirst.frc.team1758.robot.commands.SwitchCamera.cameraInUse;
 
import org.usfirst.frc.team1758.robot.commands.ToggleLight.LightMode;

import edu.wpi.first.wpilibj.CameraServer;
 
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;



public class Vision extends Subsystem
{
	private boolean isLightOn;
	private VisionThread frontVisionThread, backVisionThread;
	private Relay cameraLightRelay;
	AxisCamera frontCamera, backCamera;
	UsbCamera frontCameraUSB, backCameraUSB;
	CameraServer serverCamera;
	MjpegServer mjpegServer;	
	CvSink cvSink;
	CvSource cvSource;
 public Vision() 
  { 
    cameraLightRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY); 
    isLightOn = false; 
		frontCamera = new AxisCamera("frontCamera", RobotMap.ipFrontCamera);
		backCamera = new AxisCamera("backCamera", RobotMap.ipBackCamera);
		configureFrontCamera();
		serverCamera = CameraServer.getInstance();
		frontCameraUSB = new UsbCamera("frontCameraUSB", 0);
		//mjpegServer = new MjpegServer("cameraStream", 1181);
		//mjpegServer.setSource(frontCameraUSB);
		//Testing around with the CvSink
		//cvSink = new CvSink("openCv_USBCamera_0");
		//cvSink.setSource(frontCameraUSB);
		//CameraServer.getInstance().addServer(mjpegServer);
		serverCamera.addAxisCamera("frontCamera", "host");
		
		

		

	//	frontCameraUSB = CameraServer.getInstance().
		//backCameraUSB = CameraServer.getInstance().addServer("backCameraUSB", port);
		

  } 


	protected void initDefaultCommand() 
  { 
    //setDefaultCommand(new ToggleLight(LightMode.ON)); 
		setDefaultCommand(new SwitchCamera(cameraInUse.TOGGLE));
  } 
	public void switchToBackCamera()
	{
		serverCamera.removeCamera("frontCamera");
		serverCamera.addAxisCamera("backCamera", "host");
	}
	public void switchToFrontCamera()
	{
		serverCamera.removeCamera("backCamera");
		serverCamera.addAxisCamera("frontCamera", "host");	
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
	
	public void	turnOnCamera()
	{
		serverCamera.addAxisCamera("frontCamera", "host");
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
	


