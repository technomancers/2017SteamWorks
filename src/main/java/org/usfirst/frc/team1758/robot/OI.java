package org.usfirst.frc.team1758.robot;

import org.usfirst.frc.team1758.robot.commands.ResetGyroAngle;
import org.usfirst.frc.team1758.utilities.Controller;

public class OI {
	public static Controller operatorController, drivingController;

	public static void init() {
		drivingController = new Controller(0, 0.2);
		operatorController = new Controller(1);
	}
}
