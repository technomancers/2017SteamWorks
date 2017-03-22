package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.Operator;
import org.usfirst.frc.team1758.utilities.Controller.Axes;

public class ClimbRope extends CommandBase {
  private boolean finished;

  public ClimbRope() {
    requires(rope);
  }

  protected void initialize() {
    finished = false;
  }

  protected void execute() {
    double speed = Operator.drivingController.getNormalizedAxis(Axes.TRIGGER_LEFT);
    speed += Operator.drivingController.getNormalizedAxis(Axes.TRIGGER_RIGHT);
    if (speed > 0) {
      Operator.drivingController.setRumble(1);
    } else {
      Operator.drivingController.setRumble(0);
    }
    rope.setMotor(speed);
  }

  protected boolean isFinished() {
    return finished;
  }
}
