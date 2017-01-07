package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.OI;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	public static DriveTrain driveTrain;
	public static OI oi;

	public static void init() {
		driveTrain = new DriveTrain();
	}

	public CommandBase(String name) {
		super(name);
	}

	public CommandBase() {
		super();
	}
}
