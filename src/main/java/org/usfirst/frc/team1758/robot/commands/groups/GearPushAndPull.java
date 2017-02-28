package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.ToggleGear;
import org.usfirst.frc.team1758.robot.commands.WaitOneSec;

import edu.wpi.first.wpilibj.command.CommandGroup;



public class GearPushAndPull extends CommandGroup{
	
	public GearPushAndPull(){
		addSequential(new ToggleGear());
		addSequential(new GearPushAndPull());
		addSequential(new ToggleGear());
	}
}
