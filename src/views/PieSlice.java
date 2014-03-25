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

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;

/**
 * Class to the view for setting and getting information
 * for the bar graph. 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class PieSlice {
	private int color = Color.BLACK;
	private float value;
	private String title;
	private Path path;
	private Region region;
	/**
	 * getTitle method
	 *
	 * @return title the title of the pie slice
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * setTitle method which set the title of the pie slice
	 *
	 * @param title the title of the pie slice
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * getColor method
	 *
	 * @return color the color of the pie slice
	 */
	public int getColor() {
		return color;
	}
	/**
	 * setColor method which set the color of the pie slice
	 *
	 * @param color the color of the pie slice
	 */
	public void setColor(int color) {
		this.color = color;
	}
	/**
	 * getValue method
	 *
	 * @return value the value of the pie slice
	 */
	public float getValue() {
		return value;
	}
	/**
	 * setValue method which set the value of the pie slice
	 *
	 * @param value the value of the pie slice
	 */
	public void setValue(float value) {
		this.value = value;
	}
	/**
	 * getPath method
	 *
	 * @return path the path of the pie slice
	 */
	public Path getPath() {
		return path;
	}
	/**
	 * setPath method which set the path of the pie slice
	 *
	 * @param path the path of the pie slice
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	/**
	 * getRegion method
	 *
	 * @return region the region of the pie slice
	 */
	public Region getRegion() {
		return region;
	}
	/**
	 * setRegion method which set the region of the pie slice
	 *
	 * @param region the region of the pie slice
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	
}
