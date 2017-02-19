package org.usfirst.frc.team1758.robot.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem {
	private ADXRS450_Gyro gyro;
	private AnalogInput sonic;
	private AnalogInput prox;
	private Logger logger;

	public Sensors() {
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Creating Sensors subsystem");
		gyro = new ADXRS450_Gyro();
		sonic = new AnalogInput(RobotMap.ANALOG_SONIC_PORT);
		prox = new AnalogInput(RobotMap.PROXIMITY_PORT);
	}

	public void initDefaultCommand() {
	}

	public double getGyroAngle() {
		return gyro.getAngle();
	}

	public double getUltrasonicValue() {
		double ratio = (RobotMap.OUT_VOLTS / 512.0);
		return sonic.getVoltage() / ratio;
	}

	public boolean getProximity() {
		if (prox.getVoltage() < 0.3) {
			return true;
		} else {
			return false;
		}
	}

	public void calibrateGyroAngle() {
		logger.debug("Calibrating gyroscope");
		gyro.calibrate();
	}

	public void resetGyroAngle() {
		logger.debug("Reseting the gyroscope");
		gyro.reset();
	}
}
