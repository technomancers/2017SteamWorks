package org.usfirst.frc.team1758.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartUpProcess extends CommandGroup{
	public StartUpProcess()
	{
		addSequential(new TurnOnCameraLight());
		addSequential(new StartAutomaticCapture());
	}
}
