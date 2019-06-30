package org.gpsmaster.chart;

import java.util.Hashtable;

import org.gpsmaster.UnitConverter;
import org.gpsmaster.gpxpanel.Waypoint;
import org.jfree.chart.axis.AxisLabelLocation;
import org.jfree.chart.axis.AxisLabelPosition;
import org.jfree.ui.RectangleInsets;

/**
 * Base class for classes representing X-Axes
 * 
 * @author rfu
 *
 */
public abstract class ChartXAxis extends ChartAxis {

	protected Hashtable<Waypoint, Double> cache = null;
	
	public ChartXAxis(UnitConverter uc) {
		super(uc);
	}

	/**
	 * set defaults specific for GpsMaster and X-Axis data
	 */
	protected void setDefaults() {
		super.setDefaults();
	    valueAxis.setLowerMargin(0.0D);
	    valueAxis.setUpperMargin(0.0D);    	    
	    valueAxis.setLabelPosition(AxisLabelPosition.INSIDE);
	    valueAxis.setLabelLocation(AxisLabelLocation.HIGH_END);
	    valueAxis.setTickLabelInsets(new RectangleInsets(0, 2, 0, 2)); // smaller onscreen footprint
	    valueAxis.setLabelInsets(new RectangleInsets(0, 0, 2, 5));
	}


	/**
	 * enable fast lookup of values to Waypoints
	 */
	protected void enableCaching() {
		if (cache == null) {
			cache = new Hashtable<Waypoint, Double>();
		}		
	}
	
	/**
	 * Get the value for an arbitrary {@link Waypoint}
	 * @param wpt
	 * @return
	 */
	public double lookupValue(Waypoint wpt) {
		double value = Double.NaN;
		if (cache == null) {
			value = getValue(wpt);
		} else if (cache.containsKey(wpt)) { 
			value = cache.get(wpt);
		}
		return value;
	}
	
	/**
	 * Save the {@link Waypoint} and its associated value for later lookup
	 * @param wpt
	 * @param value
	 */
	protected void cache(Waypoint wpt, double value) {
		if (cache != null) {
			cache.put(wpt, value);
		}
	}
}