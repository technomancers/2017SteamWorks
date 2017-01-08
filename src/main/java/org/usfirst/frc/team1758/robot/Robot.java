package org.usfirst.frc.team1758.robot;

import org.usfirst.frc.team1758.robot.commands.CommandBase;
import org.usfirst.frc.team1758.robot.commands.turnOnCameraLight;
import org.usfirst.frc.team1758.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionRunner;

public class Robot extends IterativeRobot {
	Command autonomousCommand;
	public SendableChooser<Command> autoChooser;

	public void robotInit() {
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("No Autonomous", null);
		SmartDashboard.putData("Autonomous", autoChooser);
		OI.init();
		CommandBase.init();
	}

	public void robotPeriodic() {
		updateSmartDashboard();
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Left X", OI.drivingController.getRawAxis(Controller.Axes.LEFT_X));
	}

	public void autonomousInit() {
		// autonomousCommand = autoChooser.getSelected();
		// if (autonomousCommand != null)
		// 	autonomousCommand.start();
		autonomousCommand = new turnOnCameraLight();
		autonomousCommand.start();

	}

	public void autonomousPeriodic() {
		// Scheduler.getInstance().run();
	}

	public void teleopInit() {
		//Comment this line out if you want autonomous to continue until interrupted
		// if (autonomousCommand != null)
		// 	autonomousCommand.cancel();
	}

	public void teleopPeriodic() {
		// Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		// LiveWindow.run();
	}
}
