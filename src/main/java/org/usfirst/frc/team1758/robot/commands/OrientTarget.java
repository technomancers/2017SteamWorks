package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.utilities.Configuration.OrientConfig;

public class OrientTarget extends CommandBase {
  private boolean finished;
  private Logger logger;
  private boolean firstTime;
  private OrientConfig configs;

  public OrientTarget(OrientConfig configs) {
    this.configs = configs;
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
        rot = configs.centerProportional()
            * ((vision.getCenterX() - vision.getCameraConfig().width() / 2) / (vision.getCameraConfig().width() / 2));
      }
      double x = 0;
      if (!oriented()) {
        x = configs.orientedProportional() * (vision.getLeftMost().area() - vision.getRightMost().area());
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
    return vision.getCenterX() < vision.getCameraConfig().width() / 2 + configs.centerThreshold()
        && vision.getCenterX() > vision.getCameraConfig().width() / 2 - configs.centerThreshold();
  }

  private boolean oriented() {
    return Math.abs(vision.getLeftMost().area() - vision.getRightMost().area()) < configs.orientThreshold();
  }

  private boolean isDone() {
    return oriented() && isCentered();
  }
}
