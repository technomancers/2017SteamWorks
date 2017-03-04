package org.usfirst.frc.team1758.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.utilities.Controller;

public class OI {
	public static Controller drivingController, pitController;

	public static void init() {
		Logger logger = LoggerFactory.getLogger(OI.class);
		logger.debug("Creating Controllers");
		drivingController = new Controller(0, 0.2);
		pitController = new Controller(1);
	}
}
