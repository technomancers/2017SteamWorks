package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.TurnRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLeft extends CommandGroup {

	public AutonomousLeft() {
		addSequential(new TurnOnLight());
		addSequential(new MoveBack());
		addSequential(new TurnRight());
		addSequential(new Autonomous());
	}
}
