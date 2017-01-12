/**
 * 
 */
package org.usfirst.frc.team1797.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * @author Will
 *
 */
public class Vision {
	/**
	 * The range of HSV values which should be included in the output image
	 */
	HSVRange range;
	
	/**
	 * A range found in experimentation to be the range of values in which retroreflective
	 * tape will appear and not much else.
	 */
	public static final HSVRange tapeRange;
	
	static {
		tapeRange = new HSVRange(75, 95, 180, 255, 60, 170);
	}
	
	/**
	 * @param range
	 *		The range of HSV values which should be included in the output image
	 */
	public Vision(HSVRange range) {
		this.range = range;
	}
	
	/**
	 * @param hMin
	 * 		The minimum h value of a pixel to be included in the output image
	 * @param hMax
	 * 		The maximum h value of a pixel to be included in the output image
	 * @param sMin
	 * 		The minimum s value of a pixel to be included in the output image
	 * @param sMax
	 * 		The maximum s value of a pixel to be included in the output image
	 * @param vMin
	 * 		The minimum v value of a pixel to be included in the output image
	 * @param vMax
	 * 		The maximum v value of a pixel to be included in the output image
	 */
	public Vision(double hMin, double hMax, double sMin, double sMax, double vMin, double vMax) {
		this(new HSVRange(hMin, hMax, sMin, sMax, vMin, vMax));
	}
	
	
	/**
	 * @param sourceImage
	 * 		The Mat collected from the camera, which will be filtered
	 * @return
	 * 		The filtered Mat, where the pixels which were filtered out appear
	 * 		gray and the pixels remaining appear some color
	 */
	public Mat findRetroreflectiveTape(Mat sourceImage) {
		// Initialize intermediate mats
		Mat dilated = cloneDimensions(sourceImage);
		Mat out = cloneDimensions(sourceImage);
		
		// Process the image by taking the source image, running it through an HSV filter, and then dilating and eroding it
		Mat filtered = hsvFilter(sourceImage, range.getMinValues(), range.getMaxValues());
		Imgproc.dilate(filtered, dilated, new Mat());
		Imgproc.erode(dilated, out, new Mat());
		return out;
	}
	
	private Mat hsvFilter(Mat sourceImage, double[] hsvMinimumValues, double[] hsvMaximumValues) {
		Mat hsv = cloneDimensions(sourceImage);
		Mat out = cloneDimensions(sourceImage);
		
		Imgproc.cvtColor(sourceImage, hsv, Imgproc.COLOR_BGR2HSV);
		Core.inRange(hsv, new Scalar(hsvMinimumValues), new Scalar(hsvMinimumValues), out);
		return null;
	}
	
	private Mat cloneDimensions(Mat sourceImage) {
		return new Mat(sourceImage.size(), sourceImage.type(), new Scalar(0,0,0,0));
	}
}