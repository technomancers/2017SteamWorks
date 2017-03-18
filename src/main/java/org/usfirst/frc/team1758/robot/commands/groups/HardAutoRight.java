package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnLeft;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HardAutoRight extends CommandGroup {

	public HardAutoRight() {
		addSequential(new TurnOnLight());
		addSequential(new MoveBack());
		addSequential(new TurnLeft());
	}
}
