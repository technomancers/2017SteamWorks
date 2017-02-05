package org.usfirst.frc.team1758.robot.commands;

public class ResetGyroAngle extends CommandBase {
	private boolean finished;

	public ResetGyroAngle() {
		requires(sensors);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		sensors.resetGyroAngle();
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
