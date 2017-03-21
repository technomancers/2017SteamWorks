package org.usfirst.frc.team1758.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.GearRumble;

public class Gear extends Subsystem {
  private DoubleSolenoid gearSolenoid;
  private boolean open;

  public Gear() {
    open = false;
    gearSolenoid = new DoubleSolenoid(RobotMap.GEAR_SOLENOID_IN, RobotMap.GEAR_SOLENOID_OUT);
  }

  protected void initDefaultCommand() {
    setDefaultCommand(new GearRumble());
  }

  public void open() {
    gearSolenoid.set(Value.kForward);
    open = true;
  }

  public void close() {
    gearSolenoid.set(Value.kReverse);
    open = false;
  }

  public boolean isOpen() {
    return open;
  }
}
