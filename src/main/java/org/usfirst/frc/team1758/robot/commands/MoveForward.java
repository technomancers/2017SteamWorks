package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wpi.first.wpilibj.Timer;

public class MoveForward extends CommandBase {
  private boolean finished;
  private int distance;
  private Logger logger;

  public MoveForward(int distance) {
    logger = LoggerFactory.getLogger(this.getClass());
    requires(driveTrain);
    this.distance = distance;
  }

  protected void initialize() {
    logger.debug("MoveForward started");
    finished = false;
    driveTrain.resetEncoderPosition();
    driveTrain.resetGyroAngle();
    Timer timer = new Timer();
    timer.start();
    while (timer.get() < .5){
    }
  }

  protected void execute() {
    if (driveTrain.doneMoving(this.distance)) {
      finished = true;
      driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
    } else {
      driveTrain.mecanumDriveCartesian(0, -.3, 0, 0);
    }
  }
  
  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
    logger.debug("MoveForward finished");
  }

  protected void interrupted() {
  }
}
