package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.Operator;

public class GearRumble extends CommandBase {
  private boolean finished;
  private Logger logger;
  
  public GearRumble() {
    logger = LoggerFactory.getLogger(this.getClass());
    requires(gear);
  }

  protected void initialize() {
    finished = false;
    logger.debug("Starting GearRumble");
  }

  protected void execute() {
    if (gear.isOpen()) {
      Operator.drivingController.setRumble(1);
    } else {
      Operator.drivingController.setRumble(0);
    }
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
    logger.debug("GearRumble finished");
  }

  protected void interrupted() {
    logger.debug("GearRumble interrupted");
  }
}
