package org.usfirst.frc.team1758.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.DriveWithJoystick;

public class DriveTrain extends Subsystem {
  private CANTalon rfMotor;
  private CANTalon rbMotor;
  private CANTalon lfMotor;
  private CANTalon lbMotor;
  private RobotDrive tmDrive;
  private ADXRS450_Gyro gyro;
  private AnalogInput sonic;
  private Logger logger;
  private int counter;

  public enum Motor {
    FrontRight, FrontLeft, BackRight, BackLeft
  }

  protected void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoystick());
  }

  public DriveTrain() {
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Creating DriveTrain subsystem");
    counter = 0;
    gyro = new ADXRS450_Gyro();
    sonic = new AnalogInput(RobotMap.ANALOG_SONIC_PORT);
    rfMotor = new CANTalon(RobotMap.RIGHT_FRONT_MOTOR);
    rbMotor = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
    lfMotor = new CANTalon(RobotMap.LEFT_FRONT_MOTOR);
    lbMotor = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
    configureEncoders(RobotMap.ENCODER_CODES_PER_REVOLUTION);
    tmDrive = new RobotDrive(lfMotor, lbMotor, rfMotor, rbMotor);
    tmDrive.setInvertedMotor(MotorType.kFrontRight, true);
    tmDrive.setInvertedMotor(MotorType.kRearRight, true);
  }

  public void mecanumDriveCartesian(double x, double y, double rotation, double gyroAngle) {
    logger.trace("Mecanum Cartesian: X:{} Y:{} Rotation:{} Gyro:{}", x, y, rotation, gyroAngle);
    tmDrive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
  }

  public void mecanumDrivePolar(double magnitude, double direction, double rotation) {
    logger.trace("Mecanum Polar: Magnitude:{} Direction:{} Rotation:{}", magnitude, direction, rotation);
    tmDrive.mecanumDrive_Polar(magnitude, direction, rotation);
  }

  public void tankDrive(double left, double right) {
    logger.trace("Tank: Left:{} Right:{}", left, right);
    tmDrive.tankDrive(left, right);
  }

  public void configureEncoders(int codesPerRev) {
    logger.debug("Configuring the Encoders to {} per revolution", codesPerRev);
    lfMotor.configEncoderCodesPerRev(codesPerRev);
    lbMotor.configEncoderCodesPerRev(codesPerRev);
    rfMotor.configEncoderCodesPerRev(codesPerRev);
    rbMotor.configEncoderCodesPerRev(codesPerRev);
  }

  public void resetEncoderPosition() {
    logger.debug("Reseting encoder position");
    lfMotor.setEncPosition(0);
    rfMotor.setEncPosition(0);
    lbMotor.setEncPosition(0);
    rbMotor.setEncPosition(0);
  }

  public int getEncoderVelocity(Motor motor) {
    switch (motor) {
      case FrontLeft:
        return lfMotor.getEncVelocity();
      case FrontRight:
        return rfMotor.getEncVelocity();
      case BackLeft:
        return lbMotor.getEncVelocity();
      default: //BackRight
        return rbMotor.getEncVelocity();
    }
  }

  public int getEncoderPosition(Motor motor) {
    switch (motor) {
      case FrontLeft:
        return lfMotor.getEncPosition();
      case FrontRight:
        return rfMotor.getEncPosition();
      case BackLeft:
        return lbMotor.getEncPosition();
      default: //BackRight
        return rbMotor.getEncPosition();
    }
  }

  public boolean doneMoving(int distance) {
    int done = 0;
    int stopped = 0;
    if (Math.abs(getEncoderPosition(Motor.FrontLeft)) > distance) {
      done++;
    }
    if (Math.abs(getEncoderVelocity(Motor.FrontLeft)) < 10) {
      stopped++;
    }
    if (Math.abs(getEncoderPosition(Motor.FrontRight)) > distance) {
      done++;
    }
    if (Math.abs(getEncoderVelocity(Motor.FrontRight)) < 10) {
      stopped++;
    }
    if (Math.abs(getEncoderPosition(Motor.BackLeft)) > distance) {
      done++;
    }
    if (Math.abs(getEncoderVelocity(Motor.BackLeft)) < 10) {
      stopped++;
    }
    if (Math.abs(getEncoderPosition(Motor.BackRight)) > distance) {
      done++;
    }
    if (Math.abs(getEncoderVelocity(Motor.BackRight)) < 10) {
      stopped++;
    }
    if (done >= 2 || (done >= 1 && stopped >= 3)) {
      counter ++;
    } else {
      counter = 0;
    }
    return counter > 5;
  }
  
  public double getGyroAngle() {
    return gyro.getAngle();
  }

  public double getUltrasonicValue() {
    double ratio = (RobotMap.OUT_VOLTS / 512.0);
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
