package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.commands.turnOnCameraLight;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;



public class Vision extends Subsystem
{
	public Relay light;
	public Vision()
	{
		light = new Relay(3);
	}

	protected void initDefaultCommand()
	{
		setDefaultCommand(new turnOnCameraLight());
	}
}
