package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.CenterRobotTarget;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

import edu.wpi.first.wpilibj.command.CommandGroup;



public class Centering extends CommandGroup
{
	public Centering()
	{
		addSequential(new TurnOnLight());
		addSequential(new CenterRobotTarget());
	}
}
