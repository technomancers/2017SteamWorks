package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.CenterRobotTarget;
import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.StrafeAway;
import org.usfirst.frc.team1758.robot.commands.ToggleGear;
import org.usfirst.frc.team1758.robot.commands.TouchThePeg;
import org.usfirst.frc.team1758.robot.commands.TurnBackThreeFive;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

import edu.wpi.first.wpilibj.command.CommandGroup;



public class AutonomousLeft extends CommandGroup{
	
	public AutonomousLeft(){
		addSequential(new TurnOnLight());
		addSequential(new TurnBackThreeFive());
		addSequential(new MoveBack());
		addSequential(new CenterRobotTarget());
		addSequential(new ApproachPeg());
		//addSequential(new TouchThePeg());
		//addSequential(new GearPushAndPull());
		addSequential(new ToggleGear());
		addSequential(new StrafeAway());
		addSequential(new ToggleGear());
	}
}
