package org.usfirst.frc.team1758.robot;

import org.usfirst.frc.team1758.robot.commands.CommandBase;
import org.usfirst.frc.team1758.robot.commands.groups.StartUpProcess;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;
import org.usfirst.frc.team1758.utilities.Controller;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Command autonomousCommand;
	public SendableChooser<Command> autoChooser;

	public void robotInit() {
		OI.init();
		CommandBase.init();
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("No Autonomous", null);
		autoChooser.addObject("Left", null);
		autoChooser.addObject("Middle", null);
		autoChooser.addObject("Right", null);
		SmartDashboard.putData("Autonomous", autoChooser);
		(new StartUpProcess()).start();
		updateSmartDashboard();
	}

	public void robotPeriodic() {
		updateSmartDashboard();
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Left X", OI.drivingController.getRawAxis(Controller.Axes.LEFT_X));
		SmartDashboard.putNumber("Left Y", OI.drivingController.getRawAxis(Controller.Axes.LEFT_Y));
		SmartDashboard.putNumber("Right X", OI.drivingController.getRawAxis(Controller.Axes.RIGHT_X));
		SmartDashboard.putNumber("Right Y", OI.drivingController.getRawAxis(Controller.Axes.RIGHT_Y));
		SmartDashboard.putNumber("Triggers Left", OI.drivingController.getRawAxis(Controller.Axes.TRIGGER_LEFT));
		SmartDashboard.putNumber("Triggers Right", OI.drivingController.getRawAxis(Controller.Axes.TRIGGER_RIGHT));
		SmartDashboard.putNumber("Gyro", CommandBase.getSensors().getGyroAngle());
		SmartDashboard.putNumber("Front Left V", CommandBase.getDriveTrain().getEncoderVelocity(Motor.FrontLeft));
		SmartDashboard.putNumber("Front Right V", CommandBase.getDriveTrain().getEncoderVelocity(Motor.FrontRight));
		SmartDashboard.putNumber("Back Left V", CommandBase.getDriveTrain().getEncoderVelocity(Motor.BackLeft));
		SmartDashboard.putNumber("Back Right V", CommandBase.getDriveTrain().getEncoderVelocity(Motor.BackRight));
		SmartDashboard.putNumber("Front Left P", CommandBase.getDriveTrain().getEncoderPosition(Motor.FrontLeft));
		SmartDashboard.putNumber("Front Right P", CommandBase.getDriveTrain().getEncoderPosition(Motor.FrontRight));
		SmartDashboard.putNumber("Back Left P", CommandBase.getDriveTrain().getEncoderPosition(Motor.BackLeft));
		SmartDashboard.putNumber("Back Right P", CommandBase.getDriveTrain().getEncoderPosition(Motor.BackRight));
	}

	public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		//Comment this line out if you want autonomous to continue until interrupted
		if (autonomousCommand != null)
			autonomousCommand.cancel();

	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
	}
}
