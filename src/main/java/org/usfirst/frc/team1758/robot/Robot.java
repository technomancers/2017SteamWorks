package org.usfirst.frc.team1758.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.commands.ClimbRope;
import org.usfirst.frc.team1758.robot.commands.CommandBase;
import org.usfirst.frc.team1758.robot.commands.ToggleCompressor;
import org.usfirst.frc.team1758.robot.commands.ToggleGear;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.groups.AutonomousLeft;
import org.usfirst.frc.team1758.robot.commands.groups.AutonomousMiddle;
import org.usfirst.frc.team1758.robot.commands.groups.AutonomousRight;
import org.usfirst.frc.team1758.robot.commands.groups.HardAutoLeft;
import org.usfirst.frc.team1758.robot.commands.groups.HardAutoMiddle;
import org.usfirst.frc.team1758.robot.commands.groups.HardAutoRight;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;
import org.usfirst.frc.team1758.utilities.Configuration;

public class Robot extends IterativeRobot {
  Command autonomousCommand;
  private SendableChooser<Command> autoChooser;
  private Logger logger;
  private Configuration configs;

  public void robotInit() {
    configs = new Configuration();
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Initializing Robot");
    Operator.init(configs.robotConfig.controllers());
    CommandBase.init(configs.robotConfig);
    autonomousCommand = null;
    autoChooser = new SendableChooser<Command>();
    autoChooser.addDefault("No Autonomous", null);
    autoChooser.addObject("Left", new AutonomousLeft(configs.autonomousConfig, configs.robotConfig.vision().camera()));
    autoChooser.addObject("Middle",
        new AutonomousMiddle(configs.autonomousConfig, configs.robotConfig.vision().camera()));
    autoChooser.addObject("Right",
        new AutonomousRight(configs.autonomousConfig, configs.robotConfig.vision().camera()));
    autoChooser.addObject("Hard Left", new HardAutoLeft(configs.autonomousConfig.blind()));
    autoChooser.addObject("Hard Right", new HardAutoRight(configs.autonomousConfig.blind()));
    autoChooser.addObject("Hard Middle", new HardAutoMiddle(configs.autonomousConfig.blind()));
    SmartDashboard.putData("Autonomous", autoChooser);
    CommandBase.getSensors().calibrateGyroAngle();
    CommandBase.getDriveTrain().resetEncoderPosition();
    CommandBase.getVision().startVisionThread();
    updateSmartDashboard();
    (new ToggleCompressor()).start();
  }

  public void robotPeriodic() {
    logger.trace("Loop robot in");
    updateSmartDashboard();
    logger.trace("Loop robot out");
  }

  public void updateSmartDashboard() {
    logger.trace("Update Smart Dashboard");
    SmartDashboard.putNumber("Ultrasonic distance", CommandBase.getSensors().getUltrasonicValue());
    SmartDashboard.putNumber("Right Front Encoder", CommandBase.getDriveTrain().getEncoderPosition(Motor.FrontRight));
    SmartDashboard.putNumber(("Center x"), CommandBase.getVision().getCenterX());
  }

  public void autonomousInit() {
    logger.debug("Starting Autonoumous");
    autonomousCommand = autoChooser.getSelected();
    if (autonomousCommand != null) {
      logger.debug("Choosing {} for autonoumous.", autonomousCommand.getClass());
      autonomousCommand.start();
    }
  }

  public void autonomousPeriodic() {
    logger.trace("Loop autonomous in");
    Scheduler.getInstance().run();
    logger.trace("Loog autonomous out");
  }

  public void teleopInit() {
    logger.debug("Starting Telop");
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    (new TurnOnLight()).start();
    (new ClimbRope()).start();
    Operator.drivingController.buttonA.whenPressed(new ToggleGear());
  }

  public void teleopPeriodic() {
    logger.trace("Loop telop in");
    Scheduler.getInstance().run();
    logger.trace("Loop telop out");
  }
}
