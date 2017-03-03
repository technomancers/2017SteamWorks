package org.usfirst.frc.team1758.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.commands.ClimbRope;
import org.usfirst.frc.team1758.robot.commands.CommandBase;
import org.usfirst.frc.team1758.robot.commands.ResetBallPickup;
import org.usfirst.frc.team1758.robot.commands.ToggleBallPickup;
//import org.usfirst.frc.team1758.robot.commands.ResetBallPickup;
//import org.usfirst.frc.team1758.robot.commands.ShootBall;
//import org.usfirst.frc.team1758.robot.commands.ToggleBallPickup;
import org.usfirst.frc.team1758.robot.commands.ToggleCompressor;
import org.usfirst.frc.team1758.robot.commands.ToggleGear;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.groups.AutonomousLeft;
import org.usfirst.frc.team1758.robot.commands.groups.AutonomousRight;
import org.usfirst.frc.team1758.robot.commands.groups.AutonomousMiddle;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Command autonomousCommand;
	private SendableChooser<Command> autoChooser;
	private Logger logger;

	public void robotInit() {
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Initializing Robot");
		OI.init();
		CommandBase.init();
		autonomousCommand = null;
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("No Autonomous", null);
		autoChooser.addObject("Left", new AutonomousLeft());
		autoChooser.addObject("Middle", new AutonomousMiddle());
		autoChooser.addObject("Right", new AutonomousRight());
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
		logger.trace("Loop autonomous out");
	}

	public void teleopInit() {
		logger.debug("Starting Telop");
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		(new TurnOnLight()).start();
		//(new ToggleBallPickup()).start();
		//(new ShootBall()).start();
		(new ClimbRope()).start();
		OI.drivingController.a.whenPressed(new ToggleGear());
		//OI.drivingController.b.whenPressed(new ToggleBallPickup());
<<<<<<< HEAD
		//OI.pitController.a.whenPressed(new ResetBallPickup());
=======
		OI.pitController.a.whenPressed(new ResetBallPickup());
>>>>>>> 04605d6a2f93b3e3fc40f1129985d4cd645b0827
	}

	public void teleopPeriodic() {
		logger.trace("Loop telop in");
		Scheduler.getInstance().run();
		logger.trace("Loop telop out");
	}
}
