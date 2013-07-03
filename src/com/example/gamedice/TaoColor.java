package com.example.gamedice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.graphics.Color;

public enum TaoColor {
	White,
	Black,
	Blue,
	Green,
	Red,
	Yellow;
	
	private static final List<TaoColor> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static TaoColor randomColor() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
	// Other methods
	public int convertColor() {
		switch (this) {
		case Black:
			return Color.BLACK;
		case Blue:
			return Color.BLUE;
		case Green:
			return Color.GREEN;
		case Red:
			return Color.RED;
		case Yellow:
			return Color.YELLOW;
		default:
			return Color.WHITE;
		}
	}
}
