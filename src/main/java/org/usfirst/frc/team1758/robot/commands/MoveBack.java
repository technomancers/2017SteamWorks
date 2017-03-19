package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;

public class MoveBack extends CommandBase {
  private boolean finished;
  private int distance;

  public MoveBack(int pulses) {
    requires(driveTrain);
    distance = pulses;
  }

  protected void initialize() {
    finished = false;
    driveTrain.resetEncoderPosition();
    sensors.resetGyroAngle();
  }

  protected void execute() {
    if (driveTrain.getEncoderPosition(Motor.FrontRight) < -1 * distance) {
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
  }

  protected void interrupted() {
  }
}
