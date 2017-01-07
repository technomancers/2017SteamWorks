package org.usfirst.frc.team1758.robot;

public class OI {

	public static Controller operatorController, drivingController;
	public static void init()
	{
		drivingController = new Controller(0);
		operatorController = new Controller(1);
	}
}
