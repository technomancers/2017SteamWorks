package org.usfirst.frc.team1758.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.utilities.Controller;
import org.usfirst.frc.team1758.utilities.Configuration.ControllersConfig;

public class OI {
	public static Controller drivingController, pitController;

	public static void init(ControllersConfig configs) {
		Logger logger = LoggerFactory.getLogger(OI.class);
		logger.debug("Creating Controllers");
		drivingController = new Controller(configs.driving());
		pitController = new Controller(configs.pit());
	}
}
