package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.CenterRobotTarget;
import org.usfirst.frc.team1758.robot.commands.MoveForward;
import org.usfirst.frc.team1758.robot.commands.MoveForwardUntilSight;
import org.usfirst.frc.team1758.robot.commands.TouchThePeg;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

import edu.wpi.first.wpilibj.command.CommandGroup;



public class AutonomousRight extends CommandGroup{
	
	public AutonomousRight(){
		addSequential(new TurnOnLight());
		addSequential(new MoveForwardUntilSight());
		addSequential(new MoveForward());
		addSequential(new CenterRobotTarget());
	  addSequential(new ApproachPeg());
		addSequential(new TouchThePeg());
		addSequential(new GearPushAndPull());
		}
}
