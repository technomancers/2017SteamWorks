package org.usfirst.frc.team1758.robot.commands;

public class ResetBallPickup extends CommandBase {
	private boolean finished;

	public ResetBallPickup() {
		requires(ballPickup);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		ballPickup.reset();
		finished = true;
	}

	protected boolean isFinished() {
		return finished;
	}
}
