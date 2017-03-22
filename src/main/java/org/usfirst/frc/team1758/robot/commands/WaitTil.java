package org.usfirst.frc.team1758.robot.commands;

import edu.wpi.first.wpilibj.Timer;

public class WaitTil extends CommandBase {
  private boolean finished;
  private Timer timer;
  private double seconds;

  public WaitTil(double seconds) {
    this.seconds = seconds;
    timer = new Timer();
  }

  protected void initialize() {
    finished = false;
    timer.start();
  }

  protected void execute() {
    if (timer.get() > seconds) {
      finished = true;
    }
  }
  
  protected boolean isFinished() {
    return finished;
  }
}
