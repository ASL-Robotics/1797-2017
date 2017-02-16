package org.usfirst.frc.team1797.vision;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.cscore.CvSink;

public class VisionProcessor {

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
			targetRects.set(i, Imgproc.boundingRect(contours.get(i)));
	}

	public double averageX() {
		if (targetRects.size() != 2)
			return -1;

		double rect0centerx = targetRects.get(0).x + targetRects.get(0).width / 2.0;
		double rect1centerx = targetRects.get(1).x + targetRects.get(1).width / 2.0;
		return (rect0centerx + rect1centerx) / 2;
	}

	public double distance() {

	}

}
