package com.example.gamedice;

import android.graphics.Color;
import android.graphics.RectF;


public class TaoDie extends RectF{
	TaoColor color = TaoColor.randomColor();
	int width, height;		// Screen dimensions
	float radius;				// Die length
	
	private TaoDie(float halfLen) {
		super(-halfLen, -halfLen, halfLen, halfLen);
		radius = halfLen;
	}
	
	public TaoDie(int width, int height) {
		this(Math.min(width, height) / 20);
		this.width = width;
		this.height = height;
	}

	
	public int roll() {
		color = TaoColor.randomColor();
		return getColor();
	}
	
	public int getColor() {
		switch (color) {
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
	
	public float getRadius() {
		return radius;
	}
}