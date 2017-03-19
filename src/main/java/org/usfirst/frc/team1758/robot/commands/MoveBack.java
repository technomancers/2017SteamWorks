package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;



public class MoveBack extends CommandBase {
  private boolean finished;
  private int distance;

  public MoveBack(int distance) {
    requires(driveTrain);
    this.distance = distance;
  }

  protected void initialize() {
    finished = false;
    driveTrain.resetEncoderPosition();
    sensors.resetGyroAngle();
  }

  protected void execute() {
    if (driveTrain.getEncoderPosition(Motor.FrontRight) < -1 * this.distance) {
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
