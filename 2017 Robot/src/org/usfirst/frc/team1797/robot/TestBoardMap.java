package org.usfirst.frc.team1797.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class TestBoardMap {

	// Test motors
	public static VictorSP TB_MOTOR_1, TB_MOTOR_2, TB_MOTOR_3, TB_MOTOR_4;
	public static Encoder TB_ENC_1;

	public static void init() {
		TB_MOTOR_1 = new VictorSP(0);
		TB_MOTOR_2 = new VictorSP(1);
		TB_MOTOR_3 = new VictorSP(2);
		TB_MOTOR_4 = new VictorSP(3);
		
		TB_ENC_1 = new Encoder(0,1,false, Encoder.EncodingType.k4X);
	
	}
}
