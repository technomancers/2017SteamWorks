package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;



public class Servos extends Subsystem
{
	private Servo servo;
	public Servos()
	{
		servo = new Servo(RobotMap.SERVO_PORT);
	}
	protected void initDefaultCommand()
	{
	}
	public void setServo(double value)
	{
		servo.set(value);
	}
}
