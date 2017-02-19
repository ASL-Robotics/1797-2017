
package org.usfirst.frc.team1797.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1797.robot.subsystems.Climber;
import org.usfirst.frc.team1797.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1797.robot.subsystems.FloorGear;
import org.usfirst.frc.team1797.robot.subsystems.Shooter;
import org.usfirst.frc.team1797.robot.subsystems.SlotGear;
import org.usfirst.frc.team1797.robot.subsystems.Storage;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	// Instantiate required subsystems here and only here
	public static Drivetrain drivetrain;
	public static FloorGear floorgear;
	public static Climber climber;
	public static SlotGear slotgear;
	public static Storage storage;
	public static Shooter shooter;
	
	public static java.util.Timer timer;

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be 
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		RobotMap.init();

		drivetrain = new Drivetrain();
		floorgear = new FloorGear();
		climber = new Climber();
		slotgear = new SlotGear();
		storage = new Storage();
		shooter = new Shooter();
		
		oi = new OI();
		
		RobotMap.auto();
		
		new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("FRONT", 0);
            camera.setResolution(160, 90);
            CvSink cvSink = CameraServer.getInstance().getVideo("FRONT");
            //CvSink cvSink = new CvSink("CV Input");
            //cvSink.setSource(camera);
            CvSource outputStream = new CvSource("CV Image Source", VideoMode.PixelFormat.kMJPEG, 160, 90, 30);
    		MjpegServer cvStream = new MjpegServer("CV Image Stream", 1186);
    		cvStream.setSource(outputStream);
            
            Mat source = new Mat();
            
            while(!Thread.interrupted()) {
                long frameTime = cvSink.grabFrame(source);
                if(frameTime == 0)
                	continue;
                //Imgproc.line(source, new Point(44, 0), new Point(44, 160), new Scalar(255, 255, 255));
                outputStream.putFrame(source);
            }
        }).start();
		
		//timer = new java.util.Timer();
		//timer.schedule(new VisionAnnotator.AnnotatorTask(), 100);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = RobotMap.AUTO_CHOOSER.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
