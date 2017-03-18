package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HardAutoMiddle extends CommandGroup {

	public HardAutoMiddle() {
		addSequential(new TurnOnLight());
	}
}
