package com.example.gamedice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
}
