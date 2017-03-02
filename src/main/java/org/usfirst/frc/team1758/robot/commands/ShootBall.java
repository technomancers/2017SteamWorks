package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.OI;
import org.usfirst.frc.team1758.utilities.Controller.Axes;

public class ShootBall extends CommandBase {
	private boolean finished;

	public ShootBall() {
		requires(shooter);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		double speed = OI.drivingController.getNormalizedAxis(Axes.TRIGGER_RIGHT);
		shooter.setMotor(speed);
	}

	protected boolean isFinished() {
		return finished;
	}
}
