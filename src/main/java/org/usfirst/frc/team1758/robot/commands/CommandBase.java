package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1758.robot.subsystems.Gear;
import org.usfirst.frc.team1758.robot.subsystems.Pneumatics;
import org.usfirst.frc.team1758.robot.subsystems.Rope;
import org.usfirst.frc.team1758.robot.subsystems.Sensors;
import org.usfirst.frc.team1758.robot.subsystems.Vision;
import org.usfirst.frc.team1758.utilities.Configuration.RobotConfig;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
  protected static DriveTrain driveTrain;
  protected static Sensors sensors;
  protected static Vision vision;
  protected static Gear gear;
  protected static Pneumatics pneumatics;
  protected static Rope rope;
  private Logger logger;

  public static void init(RobotConfig configs) {
    Logger logger = LoggerFactory.getLogger(CommandBase.class);
    logger.debug("Initialized CommandBase");
    driveTrain = new DriveTrain(configs.driveTrain());
    sensors = new Sensors(configs.sensors());
    vision = new Vision(configs.vision());
    gear = new Gear(configs.gear());
    pneumatics = new Pneumatics(configs.pneumatics());
    rope = new Rope(configs.rope());
  }

  public CommandBase(String name) {
    super(name);
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Created CommandBase");
  }

  public CommandBase() {
    super();
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Created CommandBase");
  }

  public static Sensors getSensors() {
    return sensors;
  }

  public static DriveTrain getDriveTrain() {
    return driveTrain;
  }

  public static Vision getVision() {
    return vision;
  }
}
