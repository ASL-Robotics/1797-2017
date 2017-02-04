package org.usfirst.frc.team1797.vision;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class GripSubsystem extends PIDSubsystem {

	private GripPipeline pipeline;
	private Rect[] boundingRects;
	private CvSink sink;
	private Mat currentFrame;

	// Set up all the contstants for vision tracking
	public static final int CAMERA_WIDTH = 160;
	public static final int LEFT_CENTER_PIXEL;
	public static final int CENTER_ALLOWANCE = 4;
	static {
		LEFT_CENTER_PIXEL = (CAMERA_WIDTH >> 1) - 1;
	}

	// Set up all constants for PID
	// TODO: tune PID for vision tracking
	public static final double KP = 0;
	public static final double KI = 0;
	public static final double KD = 0;

	public GripSubsystem() {
		super(KP, KI, KD);
		pipeline = new GripPipeline();
		boundingRects = new Rect[2];
		sink = RobotMap.CAMERA_SERVER.getVideo("Front Camera");
		currentFrame = new Mat();
	}

	public void initDefaultCommand() {
		// TODO: create default command for GripSubsystem
	}

	public int getCurrentCenterX() {
		// Reset the boundingRects array so no old rectangles are left in the
		// array
		boundingRects[0] = null;
		boundingRects[1] = null;

		// Grab the current frame from the camera
		sink.grabFrame(currentFrame);

		// Process the frame in the GRIP pipeline
		pipeline.process(currentFrame);

		// Take the outputted contours from the pipeline, and find the bounding
		// rectangles of the two of them
		ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
		if (contours != null) {
			for (int i = 0; i < 2 && i < contours.size(); i++) {
				boundingRects[i] = Imgproc.boundingRect(contours.get(i));
			}
		}

		try {
			// We want to center our robot on x value of the midpoint between
			// the two rects
			// This is found by taking the average of the 2 x values
			int rect0centerx = boundingRects[0].x + (boundingRects[0].width >> 1);
			int rect1centerx = boundingRects[1].x + (boundingRects[1].width >> 1);
			return (rect0centerx + rect1centerx) >> 1;
		} catch (NullPointerException e) {
			// Kaito thinks we shouldn't worry about it if there are not 2
			// rectangles in frame
			System.out.println("Error: there are not 2 rectangles in frame!");
			return -1;
		}
	}

	@Override
	protected double returnPIDInput() {
		return getCurrentCenterX();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO: Add ultrasonic component and vision component to determine
		// robot trajetctory

	}
}
