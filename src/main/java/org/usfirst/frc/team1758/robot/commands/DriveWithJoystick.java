package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.utilities.Controller.Axes;

import org.usfirst.frc.team1758.robot.OI;

public class DriveWithJoystick extends CommandBase {
	private boolean finished;
	private double staticAngle;

	public DriveWithJoystick() {
		requires(driveTrain);
		requires(sensors);
		requires(servos);
	}

	protected void initialize() {
		finished = false;
		staticAngle = 0.0;
	}

	protected void execute() {
		double gyroAngle;
		if (OI.drivingController.rb.get()) {
			gyroAngle = sensors.getGyroAngle();
			staticAngle = gyroAngle;
		} else {
			gyroAngle = staticAngle;
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
