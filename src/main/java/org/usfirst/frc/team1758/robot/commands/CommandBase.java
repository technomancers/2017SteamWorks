package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1758.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	protected static DriveTrain driveTrain;
	protected static Vision vision;
	public static void init() {
		driveTrain = new DriveTrain();
		vision = new Vision();
	}
	public CommandBase(String name) {
		super(name);
	}
	public CommandBase() {
		super();
	}
}
