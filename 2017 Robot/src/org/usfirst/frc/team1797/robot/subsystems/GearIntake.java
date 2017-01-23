package org.usfirst.frc.team1797.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntake extends Subsystem {
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private VictorSP leftGearWheel, rightGearWheel;
	private DoubleSolenoid gearPiston;
	private long lastActuation;
	private long gearIntakeTime;
	
	public GearIntake(){
		leftGearWheel = new VictorSP(2);
		rightGearWheel = new VictorSP(3);
		gearPiston = new DoubleSolenoid(0,1);
		lastActuation = Long.MAX_VALUE;
		gearIntakeTime = Long.MAX_VALUE;
	}
	public void intakeGear(){
		leftGearWheel.set(.5);
		rightGearWheel.set(-.5);
		gearIntakeTime = System.currentTimeMillis();
	}
	public void outtakeGear(){
		leftGearWheel.set(-.5);
		rightGearWheel.set(.5);
	}
	public void gearWheelsOff(){
		leftGearWheel.set(0);
		rightGearWheel.set(0);
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

