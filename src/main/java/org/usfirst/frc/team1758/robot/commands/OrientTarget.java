package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;

public class OrientTarget extends CommandBase {
  private boolean finished;
  private Logger logger;
  private boolean firstTime;

  public OrientTarget() {
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("CenterRobotTarget command created.");
    requires(vision);
    requires(driveTrain);

  }

  protected void initialize() {
    finished = false;
    firstTime = true;
  }

  protected void execute() {
    if (firstTime) {
      logger.debug("CenterRobotTarget command started");
      firstTime = false;
    }
    if (isDone()) {
      finished = true;
      logger.debug("CenterRobotTarget isDone");
    } else {
      double rot = 0;
      if (!isCentered()) {
        rot = (vision.getCenterX() - RobotMap.CAMERA_WIDTH / 2) / (4 * (RobotMap.CAMERA_WIDTH / 2));
      }
      double x = 0;
      if (!oriented()) {
        x = (vision.getLeftMost().area() - vision.getRightMost().area()) / -300;
      }
      double y = 0; 
      logger.trace("X: {}, ROT: {}", x, rot);
      driveTrain.mecanumDriveCartesian(.8 * x, .5 * y, rot, 0.0);
    }
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
  }

  protected void interrupted() {
  }

  private boolean isCentered() {
    return vision.getCenterX() < RobotMap.CAMERA_WIDTH / 2 + 5 && vision.getCenterX() > RobotMap.CAMERA_WIDTH / 2 - 5;
  }

  private boolean oriented() {
    return Math.abs(vision.getLeftMost().area() - vision.getRightMost().area()) < 60;
  }

  private boolean isDone() {
    return oriented() && isCentered();
  }
}
