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
	  double gyroAngle = sensors.getGyroAngle();
		driveTrain.mecanumDrive(OI.drivingController.getNormalizedAxis(Axes.LEFT_X), OI.drivingController.getNormalizedAxis(Axes.LEFT_Y), OI.drivingController.getNormalizedAxis(Axes.RIGHT_X), gyroAngle);
		}
	protected boolean isFinished() {
		return finished;
	}
	protected void end() {
	}
	protected void interrupted() {
	}
}
