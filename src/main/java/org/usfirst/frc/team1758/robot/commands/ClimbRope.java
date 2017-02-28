package org.usfirst.frc.team1758.robot.commands;

public class ClimbRope extends CommandBase {
	private boolean finished;

	public ClimbRope() {
		requires(rope);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		rope.setMotor(1);
	}

	protected boolean isFinished() {
		return finished;
	}
}
