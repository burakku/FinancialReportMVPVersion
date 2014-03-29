/*
 * 	   Created by Daniel Nadeau
 * 	   daniel.nadeau01@gmail.com
 * 	   danielnadeau.blogspot.com
 * 
 * 	   Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package views;

import java.util.ArrayList;

import fiveminions.financialreportmvpversion.R;

import views.PieGraph.OnSliceClickedListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Graph class to the view for setting information
 * for the bar graph 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class BarGraph extends View {

	private transient ArrayList<Bar> points = new ArrayList<Bar>();
	private transient final Paint paint = new Paint();
	private transient final Path path = new Path();
	private transient Rect rect;
	private transient boolean showBarText = true;
	private transient int indexSelected = -1;
	private transient OnBarClickedListener listener;
	private transient Bitmap fullImage;
	private transient boolean shouldUpdate = false;
	/**
	 * Constructor for Bar Graph
	 * Calling super constructor
	 * 
	 * @param context context to type in
	 */
	public BarGraph(final Context context) {
		super(context);
	}
	/**
	 * Constructor for Bar Graph
	 * Calling super constructor
	 * 
	 * @param context context to type in
	 * @param attrs attribute of the bar graph
	 */
	public BarGraph(final Context context, final AttributeSet attrs) {
		super(context, attrs);
	}
	/**
	 * setShowBarText method which sets the text shown on the bar graph
	 * 
	 * @param show the text shown on the bar graph
	 */
	public void setShowBarText(final boolean show){
		showBarText = show;
	}
	/**
	 * setBars method which sets the points in the bar graph
	 * 
	 * @param points the points in the bar graph
	 */
	public void setBars(final ArrayList<Bar> points){
		this.points = points;
		postInvalidate();
	}
	/**
	 * getBars method which gets the points of the bar graph
	 * 
	 * @return points the points in the bar graph
	 */
	public ArrayList<Bar> getBars(){
		return this.points;
	}
	/**
	 * draws the interface of the bar graph, set the heigth and width
	 * and set the color of the graph
	 * 
	 * @param ca 
	 */
	public void onDraw(Canvas ca) {
		
		if (fullImage == null || shouldUpdate) {
			fullImage = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(fullImage);
			canvas.drawColor(Color.TRANSPARENT);
			NinePatchDrawable popup = (NinePatchDrawable)this.getResources().getDrawable(R.drawable.popup_black);
			
			float maxValue = 0;
			float padding = 7;
			int selectPadding = 4;
			float bottomPadding = 40;
			
			float usableHeight;
			if (showBarText) {
				this.paint.setTextSize(40);
				Rect r3 = new Rect();
				this.paint.getTextBounds("$", 0, 1, r3);
				usableHeight = getHeight()-bottomPadding-Math.abs(r3.top-r3.bottom)-26;
			} else {
				usableHeight = getHeight()-bottomPadding;
			}
			 
			
			paint.setColor(Color.BLACK);
			paint.setStrokeWidth(2);
			paint.setAlpha(50);
			paint.setAntiAlias(true);
			
			canvas.drawLine(0, getHeight()-bottomPadding+10, getWidth(), getHeight()-bottomPadding+10, paint);
			
			final float barWidth = (getWidth() - (padding*2)*points.size())/points.size();
			
			for (final Bar point : points) {
	        	maxValue += point.getValue();
	        }
			
			rect = new Rect();
			
			path.reset();
			
			int count = 0;
			for (final Bar point : points) {
				rect.set((int)((padding*2)*count + padding + barWidth*count), 
						(int)(getHeight()-bottomPadding-(usableHeight*(point.getValue()/maxValue))),
						(int)((padding*2)*count + padding + barWidth*(count+1))
						, (int)(getHeight()-bottomPadding));
	        	
	        	path.addRect(new RectF(rect.left-selectPadding, rect.top-selectPadding, rect.right+selectPadding, rect.bottom+selectPadding), Path.Direction.CW);
	        	point.setPath(path);
	        	point.setRegion(new Region(rect.left-selectPadding, rect.top-selectPadding, rect.right+selectPadding, rect.bottom+selectPadding));
				
	        	this.paint.setColor(point.getColor());
	        	this.paint.setAlpha(255);
				canvas.drawRect(rect, this.paint);
				this.paint.setTextSize(20);
				canvas.drawText(point.getName(), (int)(((rect.left+rect.right)/2)-(this.paint.measureText(point.getName())/2)), getHeight()-5, this.paint);
				if (showBarText){
					this.paint.setTextSize(40);
					this.paint.setColor(Color.WHITE);
					Rect r2 = new Rect();
					this.paint.getTextBounds("$"+point.getValue(), 0, 1, r2);
					popup.setBounds((int)(((rect.left+rect.right)/2)-(this.paint.measureText("$"+point.getValue())/2))-14, rect.top+(r2.top-r2.bottom)-26, (int)(((rect.left+rect.right)/2)+(this.paint.measureText("$"+point.getValue())/2))+14, rect.top);
					popup.draw(canvas);
					canvas.drawText("$"+point.getValue(), (int)(((rect.left+rect.right)/2)-(this.paint.measureText("$"+point.getValue())/2)), rect.top-20, this.paint);
				}
				if (indexSelected == count && listener != null) {
					this.paint.setColor(Color.parseColor("#33B5E5"));
					this.paint.setAlpha(100);
					canvas.drawPath(point.getPath(), this.paint);
					this.paint.setAlpha(255);
				}
	        	count++;
	        }
			shouldUpdate = false;
		}
		
		ca.drawBitmap(fullImage, 0, 0, null);
		
	}
	/**
	 * check if the event is touched
	 * 
	 * @param event event of the bar graph
	 * @return true if the event is touched  
	 */
	@Override
	public boolean onTouchEvent(final MotionEvent event) {

	    final Point point = new Point();
	    point.x = (int) event.getX();
	    point.y = (int) event.getY();
	    
	    int count = 0;
	    for (Bar bar : points){
	    	final Region region = new Region();
	    	region.setPath(bar.getPath(), bar.getRegion());
	    	if (region.contains((int)point.x,(int) point.y) && event.getAction() == MotionEvent.ACTION_DOWN){
	    		indexSelected = count;
	    	} else if (event.getAction() == MotionEvent.ACTION_UP){
	    		if (region.contains((int)point.x,(int) point.y) && listener != null){
	    			listener.onClick(indexSelected);
	    		}
	    		indexSelected = -1;
	    	}
		    count++;
	    }
	    
	    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP){
	    	shouldUpdate = true;
	    	postInvalidate();
	    }
	    
	    

	    return true;
	}
	/**
	 * setOnBarClickedListener method which sets the listener in the bar graph
	 * 
	 * @param listener
	 */
	public void setOnBarClickedListener(final OnBarClickedListener listener) {
		this.listener = listener;
	}
	/**
	 * interface for the click listener
	 * 
	 */
	public interface OnBarClickedListener {
		abstract void onClick(int index);
	}
}
