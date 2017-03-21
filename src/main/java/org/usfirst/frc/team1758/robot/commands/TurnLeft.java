package org.usfirst.frc.team1758.robot.commands;

public class TurnLeft extends CommandBase {
  private boolean finished;
  private double angle;

  public TurnLeft(double angle) {
    requires(driveTrain);
    this.angle = angle;
  }

  protected void initialize() {
    finished = false;
    driveTrain.resetEncoderPosition();
    driveTrain.resetGyroAngle();
  }

  protected void execute() {
    if (driveTrain.getGyroAngle() < -1.0 * this.angle) {
      finished = true;
      driveTrain.tankDrive(0, 0);
    } else {
      driveTrain.tankDrive(-.5, -.5);
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
