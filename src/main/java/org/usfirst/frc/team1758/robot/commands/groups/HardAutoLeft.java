package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.TurnRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HardAutoLeft extends CommandGroup {

	public HardAutoLeft() {
		addSequential(new TurnOnLight());
		addSequential(new MoveBack(8000));
		addSequential(new TurnRight(30));
		addSequential(new MoveBack(9931));
	}
}
