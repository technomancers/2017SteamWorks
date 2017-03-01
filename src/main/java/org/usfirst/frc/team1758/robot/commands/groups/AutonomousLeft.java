package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnBackThreeFive;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLeft extends CommandGroup {

	public AutonomousLeft() {
		addSequential(new TurnOnLight());
		addSequential(new TurnBackThreeFive());
		addSequential(new MoveBack());
		addSequential(new Autonomous());
	}
}
