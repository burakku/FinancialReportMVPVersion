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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Graph class to the view for setting information
 * for the pie graph 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */	
public class PieGraph extends View {

	private ArrayList<PieSlice> slices = new ArrayList<PieSlice>();
	private Paint paint = new Paint();
	private Path path = new Path();
	
	private int indexSelected = -1;
	private int thickness = 50;
	private OnSliceClickedListener listener;
	
	/**
	 * Constructor for pie graph
	 * 
	 * @param context context in the pie graph
	 */
	public PieGraph(Context context) {
		super(context);
	}
	/**
	 * Constructor for pie graph
	 * 
	 * @param context context in the pie graph
	 * @param attrs in the pie graph
	 */
	public PieGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	/**
	 * draws the interface of the pie graph, set the height and width
	 * and set the color of the graph
	 * 
	 * @param canvas
	 */
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);
		paint.reset();
		paint.setAntiAlias(true);
		float midX, midY, radius, innerRadius;
		path.reset();
		
		float currentAngle = 270;
		float currentSweep = 0;
		int totalValue = 0;
		float padding = 2;
		
		midX = getWidth()/2;
		midY = getHeight()/2;
		if (midX < midY){
			radius = midX;
		} else {
			radius = midY;
		}
		radius -= padding;
		innerRadius = radius - thickness;
		
		for (PieSlice slice : slices){
			totalValue += slice.getValue();
		}
		
		int count = 0;
		for (PieSlice slice : slices){
			Path p = new Path();
			paint.setColor(slice.getColor());
			currentSweep = (slice.getValue()/totalValue)*(360);
			p.arcTo(new RectF(midX-radius, midY-radius, midX+radius, midY+radius), currentAngle+padding, currentSweep - padding);
			p.arcTo(new RectF(midX-innerRadius, midY-innerRadius, midX+innerRadius, midY+innerRadius), (currentAngle+padding) + (currentSweep - padding), -(currentSweep-padding));
			p.close();
			
			slice.setPath(p);
			slice.setRegion(new Region((int)(midX-radius), (int)(midY-radius), (int)(midX+radius), (int)(midY+radius)));
			canvas.drawPath(p, paint);
			
			if (indexSelected == count && listener != null){
				path.reset();
				paint.setColor(slice.getColor());
				paint.setColor(Color.parseColor("#33B5E5"));
				paint.setAlpha(100);
				
				if (slices.size() > 1) {
					path.arcTo(new RectF(midX-radius-(padding*2), midY-radius-(padding*2), midX+radius+(padding*2), midY+radius+(padding*2)), currentAngle, currentSweep+padding);
					path.arcTo(new RectF(midX-innerRadius+(padding*2), midY-innerRadius+(padding*2), midX+innerRadius-(padding*2), midY+innerRadius-(padding*2)), currentAngle + currentSweep + padding, -(currentSweep + padding));
					path.close();
				} else {
					path.addCircle(midX, midY, radius+padding, Direction.CW);
				}
				
				canvas.drawPath(path, paint);
				paint.setAlpha(255);
			}
			
			currentAngle = currentAngle+currentSweep;
			
			count++;
		}
		
		
	}
	/**
	 * check if the event is touched
	 * 
	 * @param event event of the bar graph
	 * @return true if the event is touched  
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

	    Point point = new Point();
	    point.x = (int) event.getX();
	    point.y = (int) event.getY();
	    
	    int count = 0;
	    for (PieSlice slice : slices){
	    	Region r = new Region();
	    	r.setPath(slice.getPath(), slice.getRegion());
	    	if (r.contains((int)point.x,(int) point.y) && event.getAction() == MotionEvent.ACTION_DOWN){
	    		indexSelected = count;
	    	} else if (event.getAction() == MotionEvent.ACTION_UP){
	    		if (r.contains((int)point.x,(int) point.y) && listener != null){
	    			if (indexSelected > -1){
		    			listener.onClick(indexSelected);
	    			}
	    			indexSelected = -1;
	    		}
	    		
	    	}
		    count++;
	    }
	    
	    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP){
	    	postInvalidate();
	    }
	    
	    

	    return true;
	}
	/**
	 * getSlices method 
	 * 
	 * @return slices an arraylist of the pie slices    
	 */
	public ArrayList<PieSlice> getSlices() {
		return slices;
	}
	/**
	 * setSlices method which set the slices of the pie graph 
	 * 
	 * @param slices arraylist of the pie slice  
	 */
	public void setSlices(ArrayList<PieSlice> slices) {
		this.slices = slices;
		postInvalidate();
	}
	/**
	 * getSlice method 
	 * 
	 * @return slice a particular slice in the arraylist    
	 */
	public PieSlice getSlice(int index) {
		return slices.get(index);
	}
	/**
	 * addSlices method which add a slice to the pie graph
	 * 
	 * @return slice the slice to be added    
	 */
	public void addSlice(PieSlice slice) {
		this.slices.add(slice);
		postInvalidate();
	}
	/**
	 * setOnSliceClickedListener method which sets the listener in the pie graph
	 * 
	 * @param listener
	 */
	public void setOnSliceClickedListener(OnSliceClickedListener listener) {
		this.listener = listener;
	}
	/**
	 * getThickness method 
	 * 
	 * @return thickness the thickness of the pie gragh    
	 */
	public int getThickness() {
		return thickness;
	}
	/**
	 * setThickness method which set the thickness of the pie graph 
	 * 
	 * @param thickness the thickness of the pie gragh    
	 */
	public void setThickness(int thickness) {
		this.thickness = thickness;
		postInvalidate();
	}
	/**
	 * remove all the slices in pie graph
	 *     
	 */
	public void removeSlices(){
		for (int i = slices.size()-1; i >= 0; i--){
			slices.remove(i);
		}
		postInvalidate();
	}
	/**
	 * interface for the click listener
	 * 
	 */
	public static interface OnSliceClickedListener {
		public abstract void onClick(int index);
	}

}
