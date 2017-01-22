package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Sensors extends Subsystem
{
	private ADXRS450_Gyro gyro;
	private Encoder encoder;
	public Sensors()
	{
		gyro = new ADXRS450_Gyro();
		encoder = new Encoder(RobotMap.ENCODER_CHANNEL_A,RobotMap.ENCODER_CHANNEL_B);
		Talon newTalon = new Talon(0);
	}
	public void initDefaultCommand()
	{
		}
	public double getGyroAngle()
	{
		return gyro.getAngle();
	}
	public double getEncoderDistance()
	{
		return encoder.getDistance();
	}

}
