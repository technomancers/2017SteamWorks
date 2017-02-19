package org.usfirst.frc.team1758.robot.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {
	private Logger logger;
	private Relay lightRelay;

	public Vision() {
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Vision subsystem created");
		lightRelay = new Relay(RobotMap.CAMERA_LIGHT_RELAY);
	}

	public void initDefaultCommand() {
	}

	public void turnOnLights() {
		logger.debug("Turning on light");
		lightRelay.set(Value.kForward);
	}
}
