package org.usfirst.frc.team1758.robot.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;



public class Servos extends Subsystem
{
	private Servo servo;
	private Logger logger;
	public Servos()
	{
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Creating Servo subsystem");
		servo = new Servo(RobotMap.SERVO_PORT);
	}
	protected void initDefaultCommand()
	{
	}
	public void setServo(double value)
	{
		logger.trace("Setting servo to {}", value);
		servo.set(value);
	}
}
