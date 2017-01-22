package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.utilities.Controller.Axes;
import org.usfirst.frc.team1758.robot.OI;

public class DriveWithJoystick extends CommandBase {
	private static boolean finished;
	public DriveWithJoystick() {
		requires(driveTrain);
		requires(sensors);
	}
	protected void initialize() {
		finished = false;
	}
	protected void execute() {
		//If you need to do calculations on the Axis do them in a new method inside the Controller
		double x = OI.drivingController.getNormalizedAxis(Axes.LEFT_X);
		double y = OI.drivingController.getNormalizedAxis(Axes.LEFT_Y);
		double twist = OI.drivingController.getTwist();
		double gyroAngle = sensors.getGyroAngle();
		driveTrain.mecanumDrive(x, y, twist, gyroAngle);
		}
	protected boolean isFinished() {
		return finished;
	}
	protected void end() {
		driveTrain.setPower(0);
	}
	protected void interrupted() {
		driveTrain.setPower(0);
	}
}
