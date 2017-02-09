package org.usfirst.frc.team1797.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XBox360 extends Joystick {

	public XBox360(int port) {
		super(port);
	}

	public double getLeftX() {
		return getRawAxis(0);
	}

	public double getLeftY() {
		return getRawAxis(1);
	}

	public double getLeftTrigger() {
		return getRawAxis(2);
	}

	public double getRightTrigger() {
		return getRawAxis(3);
	}

	public double getRightX() {
		return getRawAxis(4);
	}

	public double getRightY() {
		return getRawAxis(5);
	}

	public JoystickButton getAButton() {
		return new JoystickButton(this, 1);
	}

	public JoystickButton getBButton() {
		return new JoystickButton(this, 1);
	}

	public JoystickButton getXButton() {
		return new JoystickButton(this, 3);
	}

	public JoystickButton getYButton() {
		return new JoystickButton(this, 4);
	}

	public JoystickButton getLeftBumper() {
		return new JoystickButton(this, 5);
	}

	public JoystickButton getRightBumper() {
		return new JoystickButton(this, 6);
	}

	public JoystickButton getBack() {
		return new JoystickButton(this, 7);
	}

	public JoystickButton getStart() {
		return new JoystickButton(this, 8);
	}

	public JoystickButton getLeftStick() {
		return new JoystickButton(this, 9);
	}

	public JoystickButton getRightStick() {
		return new JoystickButton(this, 10);
	}

	public DPadButton getNorth() {
		return new DPadButton(this, DPadButton.Direction.NORTH);
	}

	public DPadButton getNorthEast() {
		return new DPadButton(this, DPadButton.Direction.NORTHEAST);
	}

	public DPadButton getEast() {
		return new DPadButton(this, DPadButton.Direction.EAST);
	}

	public DPadButton getSouthEast() {
		return new DPadButton(this, DPadButton.Direction.SOUTHEAST);
	}

	public DPadButton getSouth() {
		return new DPadButton(this, DPadButton.Direction.SOUTH);
	}

	public DPadButton getSouthWest() {
		return new DPadButton(this, DPadButton.Direction.SOUTHWEST);
	}

	public DPadButton getWest() {
		return new DPadButton(this, DPadButton.Direction.WEST);
	} 

	public DPadButton getNorthWest() {
		return new DPadButton(this, DPadButton.Direction.NORTHWEST);
	}

	public static class DPadButton extends Button {
		public static enum Direction {
			NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST
		}

		private Joystick joystick;
		private Direction direction;

		public DPadButton(Joystick joystick, Direction direction) {
			this.joystick = joystick;
			this.direction = direction;
		}

		@Override
		public boolean get() {
			// TODO Auto-generated method stub
			return false;
		}

	}
}
