package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.Operator;
import org.usfirst.frc.team1758.utilities.Controller.Axes;

public class DriveWithJoystick extends CommandBase {
  private boolean finished;
  private boolean firstIt;
  private boolean firstTime;
  private boolean firstPress;
  private double staticAngle;
  private int mode;
  private Logger logger;

  public DriveWithJoystick() {
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Created DriveWithJoystick command");
    requires(driveTrain);
    requires(sensors);
  }

  protected void initialize() {
    finished = false;
    firstIt = true;
    firstTime = true;
    staticAngle = 0.0;
    mode = 1;
    firstPress = true;

  }

  protected void execute() {
    if (firstTime) {
      logger.debug("DriveWithJoystick command started");
      firstTime = false;
    }
    double gyroAngle;
    if (Operator.drivingController.buttonRb.get()) {
      if (firstIt) {
        sensors.resetGyroAngle();
      }
      gyroAngle = sensors.getGyroAngle();
      staticAngle = gyroAngle;
      firstIt = false;
    } else {
      gyroAngle = staticAngle;
      firstIt = true;
    }
    if (Operator.drivingController.buttonX.get() && firstPress) {
      mode++;
      firstPress = false;
    } else if (!Operator.drivingController.buttonX.get()) {
      firstPress = true;
    }
    double movementRatio = (double) ((mode % 2) + 1)  * .5;
    double x = movementRatio * Operator.drivingController.getNormalizedAxis(Axes.LEFT_X);
    double y = movementRatio * Operator.drivingController.getNormalizedAxis(Axes.LEFT_Y);
    double twist = .8 * Operator.drivingController.getNormalizedAxis(Axes.RIGHT_X);
    driveTrain.mecanumDriveCartesian(x, y, twist, gyroAngle);
    if (Operator.drivingController.buttonLb.get()) {
      sensors.resetGyroAngle();
      driveTrain.resetEncoderPosition();
      staticAngle = 0.0;
    }
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
