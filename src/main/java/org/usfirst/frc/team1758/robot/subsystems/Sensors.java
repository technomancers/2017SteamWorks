package org.usfirst.frc.team1758.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem
{
	private ADXRS450_Gyro gyro;
	private Encoder encoder;
	public Sensors()
	{
		gyro = new ADXRS450_Gyro();
		encoder = new Encoder(8, 9, false);
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
	public double getEncoderSpeed()
	{
		return encoder.getRate();
	}
	public void setEncoder()
	{
		encoder.setMaxPeriod(.1);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(5);
		encoder.setReverseDirection(true);
		encoder.setSamplesToAverage(7);
	}
	public int get()
	{
		return encoder.get();
	}
	public double getRaw()
	{
		return encoder.getRaw();
	}
	public boolean getDirection()
	{
		return encoder.getDirection();
	}
	public boolean getStopped()
	{
		return encoder.getStopped();
	}
	public double getRate()
	{
		return encoder.getRate();
	}
}
