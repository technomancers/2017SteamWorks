package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.StrafeAway;
import org.usfirst.frc.team1758.robot.commands.StrafeForward;
import org.usfirst.frc.team1758.robot.commands.ToggleGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearPushAndPull extends CommandGroup {

	public GearPushAndPull() {
		addSequential(new StrafeForward());
		addSequential(new ToggleGear());
		addSequential(new StrafeAway());
		addSequential(new ToggleGear());
	}
}
