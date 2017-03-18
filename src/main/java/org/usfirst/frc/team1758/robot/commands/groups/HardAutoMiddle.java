package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.utilities.Configuration.BlindConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HardAutoMiddle extends CommandGroup {

	public HardAutoMiddle(BlindConfig configs) {
		addSequential(new TurnOnLight());
		addSequential(new MoveBack(configs.finalBack()));
	}
}
