package com.example.gamedice;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ViewFlipper;

class DrawingPanel extends SurfaceView {
	private int width;
	private int height;
	private Paint diePaint = new Paint();
	private TaoDie die;
	private List<TaoDie> dieList = new ArrayList<TaoDie>();
	
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
	public boolean onTouchEvent(MotionEvent e) {
		gestureDetector.onTouchEvent(e);
		return true;
	}
	
	SimpleOnGestureListener simpleOnGestureListener = new SimpleOnGestureListener() {
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			selectDieByTap(e.getX(), e.getY());
			return super.onSingleTapUp(e);
		}
	};
	
	GestureDetector gestureDetector = new GestureDetector(simpleOnGestureListener);
	
	@Override
	public void onDraw(Canvas canvas) {
		/*diePaint.setColor(die.getRoll().convertColor());
		canvas.save();
		canvas.rotate(45, 0, 0);
		canvas.drawRoundRect(die, 15, 15, diePaint);
		canvas.restore();*/
		for (int i = 0; i < dieList.size(); i++) {
			if (i > 2)
				dieList.get(i).draw(canvas, true);
			else
				dieList.get(i).draw(canvas);
		}
	}
	
	private void selectDieByTap(float x, float y) {
		for (int i = 0;i < dieList.size();i++) {
			if (dieList.get(i).isIn(x, y)) {
				dieList.get(i).toggleSelect();
				fixButtonBar();
			}
		}
		invalidate();
	}
	
	public void rollDice(int dieCount) {
		// Generate new dice
		dieList = new ArrayList<TaoDie>();
		for(int i = 0; i < dieCount; i++) {
			die = new TaoDie(width, height);
			do {
				die.roll();
			} while (isOverlapping((ArrayList<TaoDie>) dieList, die));
			dieList.add(die);
		}
		invalidate();
	}
	
	private boolean isOverlapping(ArrayList<TaoDie> dieList, TaoDie newDie) {
		for (int i = 0;i < dieList.size();i++) {
			if (dieList.get(i).isOverlapping(newDie)) {
				return true;
			}
		}
		return false;
	}
	
	public void reRollDice() {
		
	}
	
	private void fixButtonBar() {
		final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.taoFlipper);
		for (int i = 0;i < dieList.size();i++) {
			if (dieList.get(i).isSelected()) {
				//viewFlipper.setDisplayedChild(1);
				//viewFlipper.showNext();
				return;
			}
		}
		//viewFlipper.setDisplayedChild(0);
		//viewFlipper.showNext();
	}
}
