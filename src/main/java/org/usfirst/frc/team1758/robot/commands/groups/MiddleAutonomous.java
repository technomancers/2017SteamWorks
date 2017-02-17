package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.TurnOnCameraLight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleAutonomous extends CommandGroup{
	public MiddleAutonomous(){
		addSequential(new TurnOnCameraLight());
		addSequential(new ApproachPeg());
	}
}
