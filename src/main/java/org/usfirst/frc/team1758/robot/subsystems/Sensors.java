package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.commands.GetGyroAngle;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Sensors extends Subsystem
{
	private ADXRS450_Gyro gyro;
	public Sensors()
	{
		gyro = new ADXRS450_Gyro();
	}
	public void initDefaultCommand()
	{
		setDefaultCommand(new GetGyroAngle());	
	}
	public double getGyroAngle()
	{
		return gyro.getAngle();
	}


}
