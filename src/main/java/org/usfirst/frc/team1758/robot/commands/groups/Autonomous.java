package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.CenterRobotTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {
	public Autonomous() {
		addSequential(new CenterRobotTarget());
		addSequential(new ApproachPeg());
		//addSequential(new GearPushAndPull());
	}
}
