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
	private Ultrasonic ultra;

	public Sensors() {
		gyro = new ADXRS450_Gyro();
		sonic = new AnalogInput(1);
		ultra = new Ultrasonic(0,1);
	}

	public void initDefaultCommand() {
	}

	public double getGyroAngle() {
		return gyro.getAngle();
	}
	public void startUltrasonic(){
		//ultra.startAutomaticMode(true);
	}
	public double getUltrasonicValue()
	{
		//double distanceRatio = RobotMap.SONIC_VOLTS/512;
		return (sonic.getVoltage()/0.0022) - 86.5682;
		
		//approximate calibration, requires further testing
		//double range = (ultra.getRangeInches()/0.0022) - 86.5682;
	}

	public void calibrateGyroAngle() {
		gyro.calibrate();
	}

	public void resetGyroAngle() {
		gyro.reset();
	}
}
