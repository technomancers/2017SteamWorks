package org.usfirst.frc.team1758.robot.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.utilities.Configuration.SensorsConfig;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem {
  private ADXRS450_Gyro gyro;
  private AnalogInput sonic;
  private Logger logger;
  private SensorsConfig configs;

  public Sensors(SensorsConfig configs) {
    this.configs = configs;
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Creating Sensors subsystem");
    gyro = new ADXRS450_Gyro();
    sonic = new AnalogInput(configs.ultrasonic().port());
  }

  public void initDefaultCommand() {
  }

  public double getGyroAngle() {
    return gyro.getAngle();
  }

  public double getUltrasonicValue() {
    double ratio = (configs.ultrasonic().suppliedVolts() / 512.0);
    return sonic.getVoltage() / ratio;
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
