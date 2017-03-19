package org.usfirst.frc.team1758.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1758.robot.RobotMap;

public class Rope extends Subsystem {
  private CANTalon motor;

  public Rope() {
    motor = new CANTalon(RobotMap.ROPE_MOTOR_PORT);
    motor.setInverted(true);
  }

  protected void initDefaultCommand() {
  }

  public void setMotor(double speed) {
    motor.set(speed);
  }
}
