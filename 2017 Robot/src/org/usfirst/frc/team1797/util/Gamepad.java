package org.usfirst.frc.team1797.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Class for both the Logitech Dual Action 2 Gamepad and the Logitech Gamepad
 * F310. The Logitech Gamepad F310 must have the switch on the back set to "D"
 * for this class to work. This class probably also works with the Logitech
 * Wireless Gamepad F710 (untested, but it has the exact same layout as the
 * F310).
 * 
 * @author wangmeister
 */

public class Gamepad extends Joystick {

	public Gamepad(int port) {
		super(port);
	}

	/**
	 * The left analog stick x-axis.
	 * 
	 * @return value of left analog x-axis
	 */
	public double getLeftX() {
		return getRawAxis(0);
	}

	/**
	 * The left analog stick y-axis.
	 * 
	 * @return value of left analog y-axis
	 */
	public double getLeftY() {
		return getRawAxis(1);
	}

	/**
	 * The right analog stick x-axis.
	 * 
	 * @return value of right analog x-axis
	 */
	public double getRightX() {
		return getRawAxis(2);
	}

	/**
	 * The right analog stick y-axis.
	 * 
	 * @return value of right analog y-axis
	 */
	public double getRightY() {
		return getRawAxis(3);
	}

	/**
	 * The upper d-pad button.
	 * 
	 * @return if upper d-pad button is pressed
	 */
	public boolean getRawDPadNorth() {
		return getPOV() == 0;
	}

	public DPadButton getDPadNorth() {
		return new DPadButton(this, DPadButton.Direction.NORTH);
	}

	/**
	 * The lower d-pad button.
	 * 
	 * @return if the lower d-pad button is pressed
	 */
	public boolean getRawDPadSouth() {
		return getPOV() == 180;
	}

	public DPadButton getDPadSouth() {
		return new DPadButton(this, DPadButton.Direction.SOUTH);
	}

	/**
	 * The left d-pad button.
	 * 
	 * @return if the left d-pad button is pressed
	 */
	public boolean getRawDPadWest() {
		return getPOV() == 270;
	}

	public DPadButton getDPadWest() {
		return new DPadButton(this, DPadButton.Direction.WEST);
	}

	/**
	 * The right d-pad button.
	 * 
	 * @return if the right d-pad button is pressed
	 */
	public boolean getRawDPadEast() {
		return getPOV() == 90;
	}

	public DPadButton getDPadEast() {
		return new DPadButton(this, DPadButton.Direction.EAST);
	}
	
	/**
	 * The upper-right d-pad button.
	 * 
	 * @return if upper d-pad button is pressed
	 */
	public boolean getRawDPadNorthEast() {
		return getPOV() == 0;
	}

	public DPadButton getDPadNorthEast() {
		return new DPadButton(this, DPadButton.Direction.NORTHEAST);
	}

	/**
	 * The lower-right d-pad button.
	 * 
	 * @return if the lower d-pad button is pressed
	 */
	public boolean getRawDPadSouthEast() {
		return getPOV() == 180;
	}

	public DPadButton getDPadSouthEast() {
		return new DPadButton(this, DPadButton.Direction.SOUTHEAST);
	}

	/**
	 * The upper-left d-pad button.
	 * 
	 * @return if the left d-pad button is pressed
	 */
	public boolean getRawDPadNorthWest() {
		return getPOV() == 270;
	}

	public DPadButton getDPadNorthWest() {
		return new DPadButton(this, DPadButton.Direction.NORTHWEST);
	}

	/**
	 * The lower-left d-pad button.
	 * 
	 * @return if the right d-pad button is pressed
	 */
	public boolean getRawDPadSouthWest() {
		return getPOV() == 90;
	}

	public DPadButton getDPadSouthWest() {
		return new DPadButton(this, DPadButton.Direction.SOUTHWEST);
	}

	/**
	 * The left bumper.
	 * 
	 * @return if the left bumper is pressed
	 */
	public boolean getRawLeftBumper() {
		return getRawButton(5);
	}

	public JoystickButton getLeftBumper() {
		return new JoystickButton(this, 5);
	}

	/**
	 * The right bumper.
	 * 
	 * @return if the right bumper is pressed
	 */
	public boolean getRawRightBumper() {
		return getRawButton(6);
	}

	public JoystickButton getRightBumper() {
		return new JoystickButton(this, 6);
	}

	/**
	 * The left trigger.
	 * 
	 * @return if the left trigger is pressed
	 */
	public boolean getRawLeftTrigger() {
		return getRawButton(7);
	}

	public JoystickButton getLeftTrigger() {
		return new JoystickButton(this, 7);
	}

	/**
	 * The right trigger.
	 * 
	 * @return if the right trigger is pressed
	 */
	public boolean getRawRightTrigger() {
		return getRawButton(8);
	}

	public JoystickButton getRightTrigger() {
		return new JoystickButton(this, 8);
	}

	/**
	 * The left button of the button group. On some gamepads this is X.
	 * 
	 * @return if the left button is pressed
	 */
	public boolean getRawLeftButton() {
		return getRawButton(1);
	}

	public JoystickButton getLeftButton() {
		return new JoystickButton(this, 1);
	}

	/**
	 * The bottom button of the button group. On some gamepads this is A.
	 * 
	 * @return if the bottom button is pressed
	 */
	public boolean getRawBottomButton() {
		return getRawButton(2);
	}

	public JoystickButton getBottomButton() {
		return new JoystickButton(this, 2);
	}

	/**
	 * The right button of the button group. On some gamepads this is B.
	 * 
	 * @return if the right button is pressed
	 */
	public boolean getRawRightButton() {
		return getRawButton(3);
	}

	public JoystickButton getRightButton() {
		return new JoystickButton(this, 3);
	}

	/**
	 * The top button of the button group. On some gamepads this is Y.
	 * 
	 * @return if the top button is pressed
	 */
	public boolean getRawTopButton() {
		return getRawButton(4);
	}

	public JoystickButton getTopButton() {
		return new JoystickButton(this, 4);
	}

	/**
	 * The central left button. On some gamepads this is the select button.
	 * 
	 * @return if the back button is pressed
	 */
	public boolean getRawSelectButton() {
		return getRawButton(9);
	}

	public JoystickButton getSelectButton() {
		return new JoystickButton(this, 9);
	}

	/**
	 * The central right button. On some gamepads this is the start button.
	 * 
	 * @return if the start button is pressed
	 */
	public boolean getRawStartButton() {
		return getRawButton(10);
	}

	public JoystickButton getStartButton() {
		return new JoystickButton(this, 10);
	}

	/**
	 * The click-function of the left analog stick.
	 * 
	 * @return if the left analog stick is being clicked down
	 */
	public boolean getRawLeftAnalogButton() {
		return getRawButton(11);
	}

	public JoystickButton getLeftAnalogButton() {
		return new JoystickButton(this, 11);
	}

	/**
	 * The click-function of the right analog stick.
	 * 
	 * @return if the right analog stick is being clicked down
	 */
	public boolean getRawRightAnalogButton() {
		return getRawButton(12);
	}

	public JoystickButton getRightAnalogButton() {
		return new JoystickButton(this, 12);
	}

	public static class DPadButton extends Button {
		public static enum Direction {
			NORTH,NORTHEAST,EAST,SOUTHEAST,SOUTH,SOUTHWEST,WEST,NORTHWEST
		}

		private Gamepad gamepad;
		private Direction direction;

		public DPadButton(Gamepad gamepad, Direction direction) {
			this.gamepad = gamepad;
			this.direction = direction;
		}

		public boolean get() {
			switch (direction) {
			case NORTH:
				return gamepad.getRawDPadNorth();
			case EAST:
				return gamepad.getRawDPadSouth();
			case SOUTH:
				return gamepad.getRawDPadWest();
			case WEST:
				return gamepad.getRawDPadEast();
			case NORTHEAST:
				return gamepad.getRawDPadNorth();
			case SOUTHEAST:
				return gamepad.getRawDPadSouth();
			case NORTHWEST:
				return gamepad.getRawDPadWest();
			case SOUTHWEST:
				return gamepad.getRawDPadEast();
			default: // Never reached
				return false;
			}
		}
	}
}