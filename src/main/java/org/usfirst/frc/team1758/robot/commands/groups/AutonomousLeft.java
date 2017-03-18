package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.TurnRight;
import org.usfirst.frc.team1758.utilities.Configuration.AutonomousConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLeft extends CommandGroup {

	public AutonomousLeft(AutonomousConfig configs) {
		addSequential(new TurnOnLight());
		addSequential(new MoveBack(configs.blind().moveBack()));
		addSequential(new TurnRight(configs.blind().turnRight()));
		addSequential(new Autonomous(configs));
	}
}
