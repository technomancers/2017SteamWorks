package org.usfirst.frc.team1758.robot.commands;

public class DriveWithJoystick extends CommandBase {
	private static boolean finished;

	public DriveWithJoystick() {
		requires(driveTrain);
	}

	public void initialize() {
		finished = false;
	}

	public void execute() {

	}

	public boolean isFinished() {
		return finished;
	}

	public void end() {

	}

	public void interrupted() {
	}
}
