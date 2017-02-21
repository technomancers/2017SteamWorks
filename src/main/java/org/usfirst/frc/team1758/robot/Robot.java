package org.usfirst.frc.team1758.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.commands.CommandBase;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.groups.Centering;

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
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("No Autonomous", null);
		autoChooser.addObject("Left", new TurnOnLight());
		autoChooser.addObject("Middle", new Centering());
		autoChooser.addObject("Right", null);
		SmartDashboard.putData("Autonomous", autoChooser);
		CommandBase.getSensors().calibrateGyroAngle();
		CommandBase.getDriveTrain().resetEncoderPosition();
		CommandBase.getVision().startVisionThread();
		updateSmartDashboard();
	}

	public void robotPeriodic() {
		logger.trace("Loop robot in");
		updateSmartDashboard();
		logger.trace("Loop robot out");
	}

	public void updateSmartDashboard() {
		logger.trace("Update Smart Dashboard");
		SmartDashboard.putNumber("Ultrasonic distance", CommandBase.getSensors().getUltrasonicValue());
	}

	public void autonomousInit() {
		logger.debug("Starting Autonoumous");
		// autonomousCommand = autoChooser.getSelected();
		// if (autonomousCommand != null) {
		// 	logger.debug("Choosing {} for autonoumous.", autonomousCommand.getClass());
		// 	autonomousCommand.start();
		// }
		(new Centering()).start();
	}

	public void autonomousPeriodic() {
		logger.trace("Loop autonomous in");
		Scheduler.getInstance().run();
		logger.trace("Loog autonomous out");
	}

	public void teleopInit() {
		logger.debug("Starting Telop");
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		(new TurnOnLight()).start();
	}

	public void teleopPeriodic() {
		logger.trace("Loop telop in");
		Scheduler.getInstance().run();
		logger.trace("Loop telop out");
	}
}
