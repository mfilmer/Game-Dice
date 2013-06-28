package com.example.gamedice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.SurfaceView;

class DrawingPanel extends SurfaceView {
	private int width;
	private int height;
	private Paint diePaint = new Paint();
	private TaoDie die;
	
	public DrawingPanel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public DrawingPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public DrawingPanel(Context context) {
		super(context);
		init();
	}
	
	void init() {
		diePaint.setStyle(Style.FILL);
		diePaint.setColor(Color.WHITE);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldW, int oldH) {
		this.width = w;
		this.height = h;
		die = new TaoDie(w, h);
		super.onSizeChanged(w, h, oldW, oldH);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		diePaint.setColor(die.roll());
		canvas.save();
		canvas.rotate(45, 0, 0);
		canvas.drawRoundRect(die, 15, 15, diePaint);
		canvas.restore();
		// Do things like check if the dice have been updated
		//super.onDraw(canvas);
	}
}
