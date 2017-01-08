package org.usfirst.frc.team1758.robot.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;


public class turnOnCameraLight extends CommandBase {
	private static boolean finished;

	public turnOnCameraLight() {
		requires(vision);
	}

	public void initialize() {
		finished = false;
	}

	public void execute() {
		vision.light.set(Value.kOn);
	}

	public boolean isFinished() {
		return finished;
	}

	public void end() {

	}

	public void interrupted() {
	}
}
