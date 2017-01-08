package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.commands.turnOnCameraLight;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;



public class Vision extends Subsystem
{
	public Relay cameraLightRelay;
	public Vision()
	{
		cameraLightRelay = new Relay(3);
	}
	CommandGroup comma = new CommandGroup();

	protected void initDefaultCommand()
	{
		setDefaultCommand(new turnOnCameraLight());
	}
}
