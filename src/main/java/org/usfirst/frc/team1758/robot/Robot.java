package org.usfirst.frc.team1758.robot;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.CommandBase;
import org.usfirst.frc.team1758.robot.commands.TurnOnCameraLight;
import org.usfirst.frc.team1758.robot.commands.groups.MiddleAutonomous;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;
import org.usfirst.frc.team1758.utilities.Controller;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Command autonomousCommand;
	private SmartDashboard dash;
	private SendableChooser<Command> autoChooser;

	public void robotInit() {
		String[] autoModes = new String[4];
		autoModes[0] = "No Autonomous";
		autoModes[1] = "Left";
		autoModes[2] = "Middle";
		autoModes[3] = "Right";
		OI.init();
		CommandBase.init();
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("No Autonomous", null);
		autoChooser.addObject("Left", new TurnOnCameraLight());
		autoChooser.addObject("Middle", new MiddleAutonomous());
		autoChooser.addObject("Right", new TurnOnCameraLight());
		SmartDashboard.putData("Autonomous", autoChooser);
		dash = new SmartDashboard();
		dash.putStringArray("Auto List", autoModes);
		SmartDashboard.putString("Auto Selector", "");
		CommandBase.getSensors().calibrateGyroAngle();
		CommandBase.getDriveTrain().resetEncoderPosition();
		CommandBase.getVision().startAutomaticCapture();
		CommandBase.getVision().startGearThread();
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
		SmartDashboard.putNumber("Center X", CommandBase.getVision().getCenterX());
		SmartDashboard.putNumber("Ultrasonic distance", CommandBase.getSensors().getUltrasonicValue());
		SmartDashboard.putBoolean("Proximity", CommandBase.getSensors().getProximity());
		SmartDashboard.putNumber("Number of Rectangles", CommandBase.getVision().getNumberOfRectangles());
		SmartDashboard.putBoolean("Sees Something", CommandBase.getVision().getSeesSomething());
	}

	public void autonomousInit() {
		
		switch (SmartDashboard.getString("Auto Selector", null)) {
		case "Left":
			autonomousCommand = new TurnOnCameraLight();
			break;
		case "Middle":
			autonomousCommand = new MiddleAutonomous();
			break;
		case "Right":
			autonomousCommand = new TurnOnCameraLight();
			break;
		default:
			autonomousCommand = autoChooser.getSelected();
			break;
		}
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		new TurnOnCameraLight().start();
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
