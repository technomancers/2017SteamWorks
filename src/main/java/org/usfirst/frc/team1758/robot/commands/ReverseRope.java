package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.OI;
import org.usfirst.frc.team1758.utilities.Controller.Axes;

public class ReverseRope extends CommandBase {
	private boolean finished;

	public ReverseRope() {
		requires(rope);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		double speed = -1.0 * OI.pitController.getNormalizedAxis(Axes.TRIGGER_LEFT);
		rope.setMotor(speed);
	}

	protected boolean isFinished() {
		return finished;
	}
}
