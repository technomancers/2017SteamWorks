package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.subsystems.BallPickup;
import org.usfirst.frc.team1758.robot.subsystems.Compressing;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1758.robot.subsystems.Gear;
import org.usfirst.frc.team1758.robot.subsystems.Rope;
import org.usfirst.frc.team1758.robot.subsystems.Sensors;
import org.usfirst.frc.team1758.robot.subsystems.Servos;
import org.usfirst.frc.team1758.robot.subsystems.Shooter;
import org.usfirst.frc.team1758.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	protected static DriveTrain driveTrain;
	protected static Sensors sensors;
	protected static Servos servos;
	protected static Vision vision;
	protected static Gear gear;
	protected static Compressing compressing;
	protected static BallPickup ballPickup;
	protected static Rope rope;
	protected static Shooter shooter;
	private Logger logger;

	public static void init() {
		Logger logger = LoggerFactory.getLogger(CommandBase.class);
		logger.debug("Initialized CommandBase");
		driveTrain = new DriveTrain();
		sensors = new Sensors();
		servos = new Servos();
		vision = new Vision();
		gear = new Gear();
		compressing = new Compressing();
		ballPickup = new BallPickup();
		rope = new Rope();
		shooter = new Shooter();
	}

	public CommandBase(String name) {
		super(name);
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Created CommandBase");
	}

	public CommandBase() {
		super();
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Created CommandBase");
	}

	public static Sensors getSensors() {
		return sensors;
	}

	public static DriveTrain getDriveTrain() {
		return driveTrain;
	}

	public static Vision getVision() {
		return vision;
	}

	public static Gear getGear() {
		return gear;
	}
}
