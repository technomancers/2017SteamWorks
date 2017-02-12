package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.CalibrateGyro;
import org.usfirst.frc.team1758.robot.commands.ResetEncoderPositions;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SensorsStartUp extends CommandGroup {
	public SensorsStartUp() {
		addSequential(new CalibrateGyro());
		addSequential(new ResetEncoderPositions());
	}
}
