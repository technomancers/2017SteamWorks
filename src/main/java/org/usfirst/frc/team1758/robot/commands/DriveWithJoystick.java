package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.Controller;
import org.usfirst.frc.team1758.robot.OI;



public class DriveWithJoystick extends CommandBase {
	private static boolean finished;

	public DriveWithJoystick() {
		requires(driveTrain);
	}

	public void initialize() {
		finished = false;
	}

	public void execute() {
		OI.drivingController.getRawAxis(Controller.Axes.LEFT_X);
	}

	public boolean isFinished() {
		return finished;
	}

	public void end() {

	}

	public void interrupted() {
	}
}
