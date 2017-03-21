package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TurnLeft extends CommandBase {
  private boolean finished;
  private double angle;
  private Logger logger;

  public TurnLeft(double angle) {
    logger = LoggerFactory.getLogger(this.getClass());
    requires(driveTrain);
    this.angle = angle;
  }

  protected void initialize() {
    logger.debug("TurnLeft Started");
    finished = false;
    driveTrain.resetEncoderPosition();
    driveTrain.resetGyroAngle();
  }

  protected void execute() {
    if (driveTrain.getGyroAngle() < -1.0 * this.angle) {
      finished = true;
      driveTrain.tankDrive(0, 0);
    } else {
      driveTrain.tankDrive(-.5, -.5);
    }
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
    logger.debug("TurnLeft finished");
  }

  protected void interrupted() {
  }
}
