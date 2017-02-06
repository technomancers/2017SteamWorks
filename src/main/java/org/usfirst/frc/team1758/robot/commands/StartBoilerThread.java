package org.usfirst.frc.team1758.robot.commands;

public class StartBoilerThread extends CommandBase {
	private boolean finished;

	public StartBoilerThread() {
		requires(vision);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		vision.startThread();
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
