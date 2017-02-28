package org.usfirst.frc.team1758.robot.commands;

public class ToggleBallPickup extends CommandBase {
	private boolean finished;
	private boolean engaged;

	public ToggleBallPickup() {
		requires(ballPickup);
		engaged = false;
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		if (engaged) {
			ballPickup.pullPiston();
			ballPickup.setPickupSpeed(0);
			engaged = false;
		} else {
			ballPickup.pushPiston();
			ballPickup.setPickupSpeed(.5);
			engaged = true;
		}
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
