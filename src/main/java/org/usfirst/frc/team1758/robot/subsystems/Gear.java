package org.usfirst.frc.team1758.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1758.robot.RobotMap;

public class Gear extends Subsystem {
  private DoubleSolenoid gearSolenoid;
  private boolean engaged;

  public Gear() {
    engaged = false;
    gearSolenoid = new DoubleSolenoid(RobotMap.GEAR_SOLENOID_IN, RobotMap.GEAR_SOLENOID_OUT);
  }

  protected void initDefaultCommand() {
  }

  public void pushPiston() {
    gearSolenoid.set(Value.kForward);
    engaged = true;
  }

  public void pullPiston() {
    gearSolenoid.set(Value.kReverse);
    engaged = false;
  }

  public boolean isEngaged() {
    return engaged;
  }
}
