package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.AnalogJNI;

public class Sensors extends Subsystem {
	private ADXRS450_Gyro gyro;
	private AnalogInput sonic;

	public Sensors() {
		gyro = new ADXRS450_Gyro();
		sonic = new AnalogInput(1);
	}

	public void initDefaultCommand() {
	}

	public double getGyroAngle() {
		return gyro.getAngle();
	}
	public double getUltrasonicValue()
	{
		double distanceRatio = RobotMap.SONIC_VOLTS/512;
		return sonic.getVoltage();
	}

	public void calibrateGyroAngle() {
		gyro.calibrate();
	}

	public void resetGyroAngle() {
		gyro.reset();
	}
}
