package org.usfirst.frc.team1758.robot.commands;

public class StartUltrasonic extends CommandBase {
	private boolean finished;

	public StartUltrasonic() {
		requires(sensors);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		sensors.startUltrasonic();
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
