package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;

public class StrafeForward extends CommandBase {
	private boolean finished;
	private Logger logger;

	public StrafeForward() {
		logger = LoggerFactory.getLogger(this.getClass());
		requires(driveTrain);
		requires(sensors);
	}

	protected void initialize() {
		finished = false;
		driveTrain.resetEncoderPosition();
		sensors.resetGyroAngle();
	}

	protected void execute() {
		logger.trace("Strafing Away");
		if (driveTrain.getEncoderPosition(Motor.FrontRight) < 6000) {
			finished = true;
			driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
		} else {
			driveTrain.mecanumDriveCartesian(0, -.3, 0, sensors.getGyroAngle());
		}
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
