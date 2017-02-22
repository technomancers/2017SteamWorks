package org.usfirst.frc.team1758.robot.commands; 
 
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor; 
 
 
 
public class StrafeRight extends CommandBase { 
  private boolean finished; 
 
  public StrafeRight() { 
    requires(driveTrain); 
  } 
 
  protected void initialize() { 
    driveTrain.resetEncoderPosition(); 
    finished = false; 
  } 
 
  protected void execute() { 
    if(driveTrain.getEncoderPosition(Motor.FrontRight)> 22500) 
    { 
      driveTrain.mecanumDriveCartesian(-.3, 0, 0, 0); 
    } else { 
    finished = true; 
    } 
  } 
 
  protected boolean isFinished() { 
    driveTrain.mecanumDriveCartesian(0, 0, 0, 0); 
    return finished; 
  } 
 
  protected void end() { 
  } 
 
  protected void interrupted() { 
  } 
} 
WIP on VisionTracking
