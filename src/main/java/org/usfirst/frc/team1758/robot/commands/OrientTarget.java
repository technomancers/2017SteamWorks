package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;

public class OrientTarget extends CommandBase {
  private boolean finished;
  private boolean widthEquals;
  private boolean heightEquals;
  private Logger logger;
  private boolean firstTime;

  public OrientTarget() {
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("OrientTarget command created.");
    requires(vision);
    requires(driveTrain);
  }

  protected void initialize() {
    finished = false;
    firstTime = true;
  }

  protected void execute() {
    if (firstTime) {
      logger.debug("OrientTarget command started");
      firstTime = false;
    }
    if (isDone()) {
      finished = true;
      logger.debug("OrientTarget isDone");
    } else {
      double rot = 0;
      if (!isCentered()) {
        rot = .75 * (vision.getCenterX() - RobotMap.CAMERA_WIDTH / 2) / (RobotMap.CAMERA_WIDTH / 2);
        if (Math.abs(rot) < .2) {
          rot += .15 * Math.abs(rot) / rot;
        }
      }
      double x = 0;
      if (!oriented()) {
        x += (vision.getLeftMost().width - vision.getRightMost().width) * -.025;
        x += (vision.getLeftMost().tl().y - vision.getRightMost().tl().y) * .025;
      }
      double y = 0; 
      logger.trace("X: {}, ROT: {}", x, rot);
      driveTrain.mecanumDriveCartesian(x, y, rot, 0.0);
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
    widthEquals = (Math.abs(vision.getLeftMost().width - vision.getRightMost().width) < 6);
    heightEquals = (Math.abs(vision.getLeftMost().tl().y - vision.getRightMost().tl().y) < 3);
    return widthEquals && heightEquals;
  }

  private boolean isDone() {
    return oriented() && isCentered();
  }
}
