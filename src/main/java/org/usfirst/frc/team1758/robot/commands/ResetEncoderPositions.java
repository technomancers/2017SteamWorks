package org.usfirst.frc.team1758.robot.commands;

public class ResetEncoderPositions extends CommandBase {
	private boolean finished;

	public ResetEncoderPositions() {
		requires(driveTrain);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		driveTrain.resetEncoderPosition();
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
