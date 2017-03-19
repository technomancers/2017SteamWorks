package org.usfirst.frc.team1758.robot.commands;

public class ToggleCompressor extends CommandBase {
  private boolean finished;
  private boolean engaged;

  public ToggleCompressor() {
    requires(pneumatics);
    engaged = false;
  }

  protected void initialize() {
    finished = false;
  }

  protected void execute() {
    if (engaged) {
      pneumatics.turnOffCompressor();
      engaged = false;
    } else {
      pneumatics.turnOnCompressor();
      engaged = true;
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
