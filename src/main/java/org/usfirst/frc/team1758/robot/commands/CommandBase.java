package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1758.robot.subsystems.Vision;
import org.usfirst.frc.team1758.robot.subsystems.Sensors;
import org.usfirst.frc.team1758.robot.subsystems.Servos;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	protected static DriveTrain driveTrain;
	protected static Vision vision;
	protected static Sensors sensors;
	protected static Servos servos;

	public static void init() {
		driveTrain = new DriveTrain();
		vision = new Vision();
		sensors = new Sensors();
		servos = new Servos();
	}

	public CommandBase(String name) {
		super(name);
	}

	public CommandBase() {
		super();
	}

	public static Sensors getSensors() {
		return sensors;
	}

	public static DriveTrain getDriveTrain() {
		return driveTrain;
	}
}
