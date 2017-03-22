package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;

public class ApproachPeg extends CommandBase {
  private boolean finished;
  private Logger logger;
  private int counter;

  public ApproachPeg() {
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("ApproachPeg command created");
    requires(vision);
    requires(driveTrain);
  }

  protected void initialize() {
    driveTrain.resetGyroAngle();
    finished = false;
    counter = 0;
  }

  protected void execute() {
    logger.trace("Gyro: {} Center: {}", driveTrain.getUltrasonicValue(), isCentered());
    if (isDone()) {
      finished = true;
      logger.trace("ApproachPeg done");
      driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
    } else {
      iterate();
    }

  }

  public void iterate() {
    double y = 0;
    if (driveTrain.getUltrasonicValue() > 30) {
      y = 0.225;
    }
    double x = 0;
    if (!isCentered()) {
      x = -.375 * (vision.getCenterX() - RobotMap.CAMERA_WIDTH / 2) / (RobotMap.CAMERA_WIDTH / 2);
    }
    double rotate = 0;
    driveTrain.mecanumDriveCartesian(x, y, rotate, 0);
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
  }

  private boolean isCentered() {
    return (vision.getCenterX() < (RobotMap.CAMERA_WIDTH / 2) + 2)
        && (vision.getCenterX() > (RobotMap.CAMERA_WIDTH / 2) - 2);
  }

  private boolean isDone() {
    if (driveTrain.getUltrasonicValue() < 30) {
      counter++;
    } else {
      counter = 0;
    }
    return counter > 2;
  }
  
  protected void interrupted() {
  }
}
