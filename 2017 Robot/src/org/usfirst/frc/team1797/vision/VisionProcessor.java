package org.usfirst.frc.team1797.vision;

import java.util.Arrays;
import java.util.List;

import org.opencv.core.Rect;
import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.util.Vector;

import com.google.gson.Gson;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionProcessor {

	private static final int LIFT_VISION_HEIGHT = 5;

	private Gson gson;
	private List<Rect> targetRects;

	public VisionProcessor() {
		gson = new Gson();
		
		clearNetworkTable();
		update();
	}

	public void update() {
		targetRects = Arrays.asList(gson.fromJson(NetworkTable.getTable("Vision").getString("Target Rectangles","[]"), Rect[].class));
	}
	
	public void clearNetworkTable() {
		NetworkTable.getTable("Vision").putString("Target Rectangles","[]");
	}

	public double getTurnAngle() {
		if (targetRects.size() != 2)
			return 0;

		Vector v = new Vector(getR(), getPhi());
		Vector q = Vector.add(RobotMap.VISION_CAMERA_VECTOR, v);
		return q.getPhi();
	}

	public double getAvgX() {
		double rect0 = targetRects.get(0).x + targetRects.get(0).width / 2.0;
		double rect1 = targetRects.get(1).x + targetRects.get(1).width / 2.0;
		return (rect0 + rect1) / 2;
	}

	public double getPhi() {
		return (getAvgX() - RobotMap.kVISION_CENTER_X) / RobotMap.kVISION_FOCAL_LENGTH;
	}

	public double getAvgHeight() {
		return (targetRects.get(0).height + targetRects.get(1).height) / 2;
	}

	public double getR() {
		return RobotMap.kVISION_FOCAL_LENGTH * LIFT_VISION_HEIGHT / getAvgHeight();
	}

}
