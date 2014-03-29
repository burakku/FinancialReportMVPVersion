/*
 * 	   Created by Daniel Nadeau
 * 	   daniel.nadeau01@gmail.com
 * 	   danielnadeau.blogspot.com
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
	 * getTitle method.
	 *
	 * @return title the title of the pie slice
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * setTitle method which set the title of the pie slice.
	 *
	 * @param title the title of the pie slice
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	/**
	 * getColor method.
	 *
	 * @return color the color of the pie slice
	 */
	public int getColor() {
		return color;
	}
	/**
	 * setColor method which set the color of the pie slice.
	 *
	 * @param color the color of the pie slice
	 */
	public void setColor(final int color) {
		this.color = color;
	}
	/**
	 * getValue method.
	 *
	 * @return value the value of the pie slice
	 */
	public float getValue() {
		return value;
	}
	/**
	 * setValue method which set the value of the pie slice.
	 *
	 * @param value the value of the pie slice
	 */
	public void setValue(final float value) {
		this.value = value;
	}
	/**
	 * getPath method.
	 *
	 * @return path the path of the pie slice
	 */
	public Path getPath() {
		return path;
	}
	/**
	 * setPath method which set the path of the pie slice.
	 *
	 * @param path the path of the pie slice
	 */
	public void setPath(final Path path) {
		this.path = path;
	}
	/**
	 * getRegion method.
	 *
	 * @return region the region of the pie slice
	 */
	public Region getRegion() {
		return region;
	}
	/**
	 * setRegion method which set the region of the pie slice.
	 *
	 * @param region the region of the pie slice
	 */
	public void setRegion(final Region region) {
		this.region = region;
	}
}
