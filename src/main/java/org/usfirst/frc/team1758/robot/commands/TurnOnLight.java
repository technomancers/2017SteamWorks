package org.usfirst.frc.team1758.robot.commands;

public class TurnOnLight extends CommandBase {
  private boolean finished;

  public TurnOnLight() {
    requires(vision);
  }

  protected void initialize() {
    finished = false;
  }

  protected void execute() {
    vision.turnOnLights();
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
