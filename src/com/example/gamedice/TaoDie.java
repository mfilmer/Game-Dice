package com.example.gamedice;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;


public class TaoDie {
	RectF rect;
	TaoColor color = TaoColor.randomColor();
	Paint diePaint = new Paint();
	Paint dotPaint = new Paint();
	int width, height;					// Screen dimensions
	int winMinX, winMaxX, winMinY, winMaxY;		// Die drawing window (center of die must be within window)
	int x, y;							// Die Location
	Random dieRand = new Random();		// Random generator for die location and rotation
	float rotation;						// Die rotation in degrees
	double length;						// Die length
	float dotRadius;					// Dot radius
	float rectRadius;					// Rect corner radius
	double dieRadius;					// Radius of bounding circle
	boolean selected = false;			// Is the die currently selected
	
	public TaoDie(int width, int height) {
		float length = Math.min(width, height) / 10;
		rect = new RectF(-length, -length, length, length);
		this.width = width;
		this.height = height;
		
		dieRadius = length * 1.1;
		dotRadius = (float) (length * 0.7);
		rectRadius = (float) (length * 0.3);
		winMinX = (int) dieRadius;
		winMaxX = width - (int) dieRadius;
		winMinY = (int) dieRadius;
		winMaxY = height - (int) dieRadius;
		
		dotPaint.setStyle(Style.FILL);
		diePaint.setStyle(Style.FILL);
		diePaint.setColor(Color.WHITE);
	}
	
	public void roll() {
		color = TaoColor.randomColor();
		dotPaint.setColor(color.convertColor());
		changeLocation();
	}
	
	/*public TaoColor getRoll() {
		color = TaoColor.randomColor();
		changeLocation();
		return getColor();
	}
	
	public TaoColor getColor() {
		return color;
	}*/
	
	public void changeLocation() {
		x = dieRand.nextInt(winMaxX - winMinX + 1) + winMinX;
		y = dieRand.nextInt(winMaxY - winMinY + 1) + winMinY;
		rotation = dieRand.nextFloat() * 90;
	}
	
	// Die selection methods
	public void select() {
		selected = true;
	}
	
	public void unselect() {
		selected = false;
	}
	
	public void toggleSelect() {
		selected = ! selected;
	}
	
	public void draw(Canvas canvas) {
		canvas.save();
		canvas.translate(x, y);
		canvas.rotate(rotation, 0, 0);
		canvas.drawRoundRect(rect, rectRadius, rectRadius, diePaint);
		canvas.drawCircle(0,  0,  dotRadius,  dotPaint);
		canvas.restore();
	}
}