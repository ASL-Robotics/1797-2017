package org.usfirst.frc.team1797.vision;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.util.Vector;

import edu.wpi.cscore.CvSink;

public class VisionProcessor {

	private static final int LIFT_VISION_HEIGHT = 5;

	private CvSink sink;
	private GripPipeline pipeline;
	private Mat mat;
	private ArrayList<MatOfPoint> contours;
	private ArrayList<Rect> targetRects;

	public VisionProcessor() {
		sink = RobotMap.VISION_SINK;
		pipeline = RobotMap.VISION_PIPELINE;

		update();
	}

	public void update() {
		sink.grabFrame(mat);
		pipeline.process(mat);
		contours = pipeline.filterContoursOutput();

		targetRects.clear();

		for (int i = 0; i < contours.size(); i++)
			targetRects.add(Imgproc.boundingRect(contours.get(i)));
	}

	public double getTurnAngle() {
		Vector v = new Vector(getR(), getPhi());
		Vector q = Vector.add(RobotMap.VISION_CAMERA_VECTOR, v);
		return q.getPhi();
	}

	public double getAvgX() {
		if (targetRects.size() != 2)
			return -1;

		double rect0 = targetRects.get(0).x + targetRects.get(0).width / 2.0;
		double rect1 = targetRects.get(1).x + targetRects.get(1).width / 2.0;
		return (rect0 + rect1) / 2;
	}

	public double getPhi() {
		return (getAvgX() - RobotMap.kVISION_CENTER_X) / RobotMap.kVISION_FOCAL_LENGTH;
	}

	public double getAvgHeight() {
		if (targetRects.size() != 2)
			return -1;

		return (targetRects.get(0).height + targetRects.get(1).height) / 2;

	}

	public double getR() {
		return RobotMap.kVISION_FOCAL_LENGTH * LIFT_VISION_HEIGHT / getAvgHeight();
	}

}
