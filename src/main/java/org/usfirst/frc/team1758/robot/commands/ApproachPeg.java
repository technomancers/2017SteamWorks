package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.utilities.Configuration.ApproachConfig;

public class ApproachPeg extends CommandBase {
  private boolean finished;
  private Logger logger;
  private int counter;
  private ApproachConfig configs;

  public ApproachPeg(ApproachConfig configs) {
    this.configs = configs;
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("ApproachPeg command created");
    requires(vision);
    requires(driveTrain);
    requires(sensors);
  }

  protected void initialize() {
    sensors.resetGyroAngle();
    finished = false;
    counter = 0;
  }

  protected void execute() {
    logger.trace("Gyro: {} Center: {}", sensors.getUltrasonicValue(), isCentered());
    if (isDone()) {
      finished = true;
      driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
    } else {
      iterate();
    }

  }

  public void iterate() {
    double y = 0;
    if (sensors.getUltrasonicValue() > configs.untilDistance()) {
      y = configs.speed();
    }
    double x = 0;
    if (!isCentered()) {
      x = configs.centerProportional()
          * ((vision.getCenterX() - vision.getCameraConfig().width() / 2) / (vision.getCameraConfig().width() / 2));
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
    return (vision.getCenterX() < (vision.getCameraConfig().width() / 2) + configs.centerThreshold())
        && (vision.getCenterX() > (vision.getCameraConfig().width() / 2) - configs.centerThreshold());
  }

  private boolean isDone() {
    if (sensors.getUltrasonicValue() < configs.doneDistance() && isCentered()) {
      counter++;
    } else {
      counter = 0;
    }
    return counter > configs.doneIterations();
  }

  protected void interrupted() {
  }
}
