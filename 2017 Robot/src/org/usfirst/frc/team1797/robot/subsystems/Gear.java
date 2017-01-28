package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem {
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private VictorSP intake;
	private DoubleSolenoid gearPiston;
	
	private long lastActuation;
	private long gearIntakeTime;
	
	public Gear(){
		intake = RobotMap.GEAR_INTAKE;
		gearPiston = RobotMap.GEAR_PISTON;
		
		lastActuation = Long.MAX_VALUE;
		gearIntakeTime = Long.MAX_VALUE;
	}
	public void intakeGear(){
		intake.set(.5);
		gearIntakeTime = System.currentTimeMillis();
	}
	public void outtakeGear(){
		intake.set(-.5);
	}
	public void gearWheelsOff(){
		intake.set(0);
		gearIntakeTime = Long.MAX_VALUE;
	}
	public void clawUp(){
		gearPiston.set(DoubleSolenoid.Value.kForward);
		lastActuation = System.currentTimeMillis();
	}
	public void clawDown(){
		gearPiston.set(DoubleSolenoid.Value.kReverse);
		lastActuation = System.currentTimeMillis();
	}
	public void clawPistonOff(){
		gearPiston.set(DoubleSolenoid.Value.kOff);
		lastActuation = Long.MAX_VALUE;
	}
	public long getLastActuation(){
		return lastActuation;
	}
	public long getGearIntakeTime(){
		return gearIntakeTime;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

