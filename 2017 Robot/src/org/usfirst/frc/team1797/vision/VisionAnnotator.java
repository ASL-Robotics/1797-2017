package org.usfirst.frc.team1797.vision;

import java.util.TimerTask;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1797.robot.RobotMap;

public class VisionAnnotator {
	Mat sourceImage;
	
	public static final VisionAnnotator INSTANCE;
	
	private java.util.Timer timer;
	
	static {
		INSTANCE = new VisionAnnotator();
	}
	
	private VisionAnnotator() {
		sourceImage = new Mat();
	}
	
	public void render() {
		RobotMap.VISION_SINK.grabFrame(sourceImage);
		Imgproc.line(sourceImage, new Point(44, 0), new Point(44, 160), new Scalar(255, 255, 255), 1, 8, 0);
		RobotMap.VISION_SOURCE.putFrame(sourceImage);
	}
	
	public static class AnnotatorTask extends TimerTask {

		@Override
		public void run() {
			VisionAnnotator.INSTANCE.render();
		}
		
	}
}
