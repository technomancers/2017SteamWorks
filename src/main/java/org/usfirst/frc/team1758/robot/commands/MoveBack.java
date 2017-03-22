package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MoveBack extends CommandBase {
  private boolean finished;
  private int distance;
  private Logger logger;

  public MoveBack(int distance) {
    logger = LoggerFactory.getLogger(this.getClass());
    requires(driveTrain);
    this.distance = distance;
  }

  protected void initialize() {
    logger.debug("MoveBack Command Started");
    finished = false;
    driveTrain.resetEncoderPosition();
    driveTrain.resetGyroAngle();
  }

  protected void execute() {
    if (driveTrain.doneMoving(this.distance)) {
      finished = true;
      driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
    } else {
      driveTrain.mecanumDriveCartesian(0, .3, 0, 0);
    }
  }
  
  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
    logger.debug("MoveBack command finished");
  }

  protected void interrupted() {
  }
}
