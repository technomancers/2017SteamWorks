package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.utilities.Configuration.PneumaticsConfig;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
  private Compressor compressor;

  public Pneumatics(PneumaticsConfig configs) {
    compressor = new Compressor(configs.compressor().port());
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
