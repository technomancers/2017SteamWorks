package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.utilities.Controller.Axes;

import org.usfirst.frc.team1758.robot.OI;

public class DriveWithJoystick extends CommandBase {
	private static boolean finished;
	public DriveWithJoystick() {
		requires(driveTrain);
	}
	protected void initialize() {
		finished = false;
	}
	protected void execute() {
	  double gyroAngle = 0.0;
		double x = OI.drivingController.getNormalizedAxis(Axes.LEFT_X);
		double y = OI.drivingController.getNormalizedAxis(Axes.LEFT_Y);
		double twist = OI.drivingController.getNormalizedAxis(Axes.RIGHT_X);
		driveTrain.mecanumDriveCartesian(x, y, twist, gyroAngle);
	}
	protected boolean isFinished() {
		return finished;
	}
	protected void end() {
	}
	protected void interrupted() {
	}
}
