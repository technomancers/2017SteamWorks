package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.ToggleLight;
import org.usfirst.frc.team1758.robot.commands.ToggleLight.LightMode;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem
{
	private boolean isLightOn;
	private Relay cameraLightRelay;
	public Vision()
	{
		cameraLightRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY);
		isLightOn = false;
	}
	protected void initDefaultCommand()
	{
		setDefaultCommand(new ToggleLight(LightMode.ON));
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
}
