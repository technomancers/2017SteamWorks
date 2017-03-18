package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMiddle extends CommandGroup {
	public AutonomousMiddle() {
		addSequential(new TurnOnLight());
		addSequential(new MoveBack(4000));
		addSequential(new Autonomous());
	}
}
