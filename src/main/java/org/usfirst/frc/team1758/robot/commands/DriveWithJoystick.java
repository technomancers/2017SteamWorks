package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.utilities.Controller.Axes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import org.usfirst.frc.team1758.robot.OI;

public class DriveWithJoystick extends CommandBase {
	private boolean finished;
	private boolean firstIt;
	private boolean firstTime;
	private double staticAngle;
	private Logger logger;

	public DriveWithJoystick() {
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Created DriveWithJoystick command");
		requires(driveTrain);
		requires(sensors);
		requires(servos);
	}

	protected void initialize() {
		finished = false;
		firstIt = true;
		firstTime = true;
		staticAngle = 0.0;
	}

	protected void execute() {
		if(firstTime){
			logger.debug("DriveWithJoystick command started");
			firstTime = false;
		}
		double gyroAngle;
		if (OI.drivingController.rb.get()) {
			if (firstIt) {
				sensors.resetGyroAngle();
			}
			gyroAngle = sensors.getGyroAngle();
			staticAngle = gyroAngle;
			firstIt = false;
		} else {
			gyroAngle = staticAngle;
			firstIt = true;
		}
		double x = OI.drivingController.getNormalizedAxis(Axes.LEFT_X);
		double y = OI.drivingController.getNormalizedAxis(Axes.LEFT_Y);
		double twist = OI.drivingController.getNormalizedAxis(Axes.RIGHT_X);
		double trigger = OI.drivingController.getRawAxis(Axes.TRIGGER_LEFT);
		driveTrain.mecanumDriveCartesian(x, y, twist, gyroAngle);
		servos.setServo(trigger);
		if (OI.drivingController.lb.get()) {
			sensors.resetGyroAngle();
			driveTrain.resetEncoderPosition();
			staticAngle = 0.0;
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
