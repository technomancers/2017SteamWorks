package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1758.robot.subsystems.Sensors;
import org.usfirst.frc.team1758.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	protected static DriveTrain driveTrain;
	public	 static Vision vision;
	public static Sensors sensors;
	public static void init() {
		driveTrain = new DriveTrain();
		vision = new Vision();
		sensors = new Sensors();
	}
	public CommandBase(String name) {
		super(name);
	}
	public CommandBase() {
		super();
	}
}
