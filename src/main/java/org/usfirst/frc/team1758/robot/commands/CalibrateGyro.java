package org.usfirst.frc.team1758.robot.commands;

public class CalibrateGyro extends CommandBase {
	private boolean finished;

	public CalibrateGyro() {
		requires(sensors);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		sensors.calibrateGyroAngle();
		finished = true;
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
