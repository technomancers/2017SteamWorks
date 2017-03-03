package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.StrafeForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMiddle extends CommandGroup {

	public AutonomousMiddle() {
		addSequential(new TurnOnLight());
        addSequential(new StrafeForward());
		addSequential(new Autonomous());
	}
}
