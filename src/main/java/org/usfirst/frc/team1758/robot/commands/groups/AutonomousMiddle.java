package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.utilities.Configuration.AutonomousConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMiddle extends CommandGroup {
	public AutonomousMiddle(AutonomousConfig configs) {
		addSequential(new TurnOnLight());
		addSequential(new MoveBack(configs.blind().moveBackCenter()));
		addSequential(new Autonomous(configs));
	}
}
