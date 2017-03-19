package org.usfirst.frc.team1758.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1758.robot.RobotMap;

public class Compressing extends Subsystem {
  private Compressor compressor; 

  public Compressing() {
    compressor = new Compressor(RobotMap.COMPRESSOR_NODE_ID);
    compressor.setClosedLoopControl(false);
  }

  protected void initDefaultCommand() {
  }

  public void turnOnCompressor() {
    compressor.setClosedLoopControl(true);
  }

  public void turnOffCompressor() {
    compressor.setClosedLoopControl(false);
  }

}
