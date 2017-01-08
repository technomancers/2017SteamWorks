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
		vision.cameraLightRelay.set(Value.kOn);
		finished = true;
	}

	public boolean isFinished() {
		return finished;
	}

	public void end() {
		finished = true;
	}

	public void interrupted() {
	}
}
