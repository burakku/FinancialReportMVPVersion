/*
 * 	   Created by Daniel Nadeau
 * 	   daniel.nadeau01@gmail.com
 * 	   danielnadeau.blogspot.com
 */

package views;
import android.graphics.Path;
import android.graphics.Region;

/**
 * View class for setting and getting information
 * for the account interface
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class Bar { // NOPMD by hailin on 3/28/14 7:01 PM
	private int color;
	private String name;
	private float value;
	private Path path;
	private Region region;
	/**
	 * getColor method 
	 *
	 * @return color color of the interface
	 */
	public int getColor() {
		return color;
	}
	/**
	 * setColor method which set the color of the interface
	 *
	 * @return color color of the interface
	 */
	public void setColor(final int color) {
		this.color = color;
	}
	/**
	 * getName method 
	 *
	 * @return name name displayed the interface
	 */
	public String getName() {
		return name;
	}
	/**
	 * setName method which set the name displayed the interface
	 *
	 * @param name name displayed the interface
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * getValue method 
	 *
	 * @return value value displayed on the interface
	 */
	public float getValue() {
		return value;
	}
	/**
	 * setValue method which set the value displayed on the interface
	 *
	 * @param value value displayed on the interface
	 */
	public void setValue(final float value) {
		this.value = value;
	}
	/**
	 * getPath method 
	 *
	 * @return path path of the interface
	 */
	public Path getPath() {
		return path;
	}
	/**
	 * setPath method which set the path of the interface
	 * 
	 * @param path path of the interface
	 */
	public void setPath(final Path path) {
		this.path = path;
	}
	/**
	 * getRegion method 
	 *
	 * @return region region of the interface
	 */
	public Region getRegion() {
		return region;
	}
	/**
	 * getRegion method 
	 *
	 * @return region region of the interface
	 */
	public void setRegion(final Region region) {
		this.region = region;
	}
	
}
