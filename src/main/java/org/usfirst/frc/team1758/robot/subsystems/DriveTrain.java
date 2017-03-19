package org.usfirst.frc.team1758.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team1758.utilities.Configuration.DriveTrainConfig;

public class DriveTrain extends Subsystem {
  private CANTalon rfMotor;
  private CANTalon rbMotor;
  private CANTalon lfMotor;
  private CANTalon lbMotor;
  private RobotDrive tmDrive;
  private Logger logger;

  public enum Motor {
    FrontRight, FrontLeft, BackRight, BackLeft
  }

  protected void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoystick());
  }

  public DriveTrain(DriveTrainConfig configs) {
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Creating DriveTrain subsystem");
    rfMotor = new CANTalon(configs.motors().rightFront().port());
    rbMotor = new CANTalon(configs.motors().rightBack().port());
    lfMotor = new CANTalon(configs.motors().leftFront().port());
    lbMotor = new CANTalon(configs.motors().leftBack().port());
    configureEncoders(configs.encoders().encoderCodesPerRevolution());
    tmDrive = new RobotDrive(lfMotor, lbMotor, rfMotor, rbMotor);
    tmDrive.setInvertedMotor(MotorType.kFrontRight, configs.motors().rightFront().reverse());
    tmDrive.setInvertedMotor(MotorType.kRearRight, configs.motors().rightBack().reverse());
    tmDrive.setInvertedMotor(MotorType.kFrontLeft, configs.motors().leftFront().reverse());
    tmDrive.setInvertedMotor(MotorType.kRearLeft, configs.motors().leftBack().reverse());
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
      default: //ackRight
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
      default: //ackRight
        return rbMotor.getEncPosition();
    }
  }
}
