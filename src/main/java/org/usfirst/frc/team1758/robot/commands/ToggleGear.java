package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToggleGear extends CommandBase {
  private boolean finished;
  private Logger logger;

  public ToggleGear() {
    logger = LoggerFactory.getLogger(this.getClass());
    requires(gear);
  }

  protected void initialize() {
    finished = false;
  }

  protected void execute() {
    logger.debug("Toggling Gear");
    if (gear.isEngaged()) {
      gear.pullPiston();
    } else {
      gear.pushPiston();
    }
    finished = true;
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
