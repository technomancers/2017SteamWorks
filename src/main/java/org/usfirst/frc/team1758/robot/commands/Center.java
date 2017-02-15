package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.Vision;
import org.usfirst.frc.team1758.utilities.TechnoPID;
public class Center{
    TechnoPID centerController;

    public Center(){
        centerController = new TechnoPID()
    }
}