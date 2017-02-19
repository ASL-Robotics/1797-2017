package org.usfirst.frc.team1797.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.util.Vector;

import com.google.gson.Gson;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionProcessor {

	private static final int LIFT_VISION_HEIGHT = 5;

	private Gson gson;
	private List<Rect> targetRects;
	
	private GripPipeline pipeline;
	private Mat inputImage;
	private ArrayList<MatOfPoint> contours;

	public VisionProcessor() {
		gson = new Gson();
		pipeline = new GripPipeline();
		inputImage = new Mat();
		contours = new ArrayList<MatOfPoint>();
		
		new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
            
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
            
            Mat source = new Mat();
            Mat output = new Mat();
            
            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                outputStream.putFrame(output);
            }
        }).start();
		
		//clearNetworkTable();
		update();
	}

	public void update() {
		long frameTime = RobotMap.VISION_SINK.grabFrame(inputImage);
		if (frameTime == 0)
			return;
		pipeline.process(inputImage);
		contours = pipeline.filterContoursOutput();

		targetRects.clear();

		for (int i = 0; i < contours.size(); i++) {
			targetRects.add(Imgproc.boundingRect(contours.get(i)));
		}
		
		//targetRects = Arrays.asList(gson.fromJson(NetworkTable.getTable("Vision").getString("Target Rectangles","[]"), Rect[].class));
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

	private double getAvgX() {
		double rect0 = targetRects.get(0).x + targetRects.get(0).width / 2.0;
		double rect1 = targetRects.get(1).x + targetRects.get(1).width / 2.0;
		return (rect0 + rect1) / 2;
	}

	private double getPhi() {
		return (getAvgX() - RobotMap.kVISION_CENTER_X) / RobotMap.kVISION_FOCAL_LENGTH;
	}

	private double getAvgHeight() {
		return (targetRects.get(0).height + targetRects.get(1).height) / 2;
	}

	private double getR() {
		return RobotMap.kVISION_FOCAL_LENGTH * LIFT_VISION_HEIGHT / getAvgHeight();
	}

}
