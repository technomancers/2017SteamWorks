package org.usfirst.frc.team1758.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.utilities.Configuration.ControllersConfig;
import org.usfirst.frc.team1758.utilities.Controller;

public class Operator {
  public static Controller drivingController;
  public static Controller pitController;

  public static void init(ControllersConfig configs) {
    Logger logger = LoggerFactory.getLogger(Operator.class);
    logger.debug("Creating Controllers");
    drivingController = new Controller(configs.driving());
    pitController = new Controller(configs.pit());
  }
}
