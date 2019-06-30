package org.gpsmaster.cleaning;

import java.util.Map;
import java.util.TreeMap;

import org.gpsmaster.gpxpanel.Waypoint;
import org.gpsmaster.gpxpanel.WaypointGroup;

import se.kodapan.osm.domain.Way;

/**
 * bereinigen nach gau�'scher normalverteilung:
 * badewannenkurve nach Distance(p1, p2)
 * unterste x% (k�rzeste strecken) entfernen (idle points)
 * oberste x% (l�ngste strecken) entfernen (ausreisser)
 * 
 * @author rfu
 *
 */
public class Gaussian extends CleaningAlgorithm {

	// TreeMap holding a Waypoint (value) and the distance to its neighbour (key)
	// 
	private TreeMap<Double, Waypoint> map = new TreeMap<Double, Waypoint>();
	
	public Gaussian() {
		super();
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	private void populateMap() {
		
		// we will not remove startpoint 
		if (trackpoints.size() > 2) {			
			int i = 1;
			while (i < trackpoints.size() - 1) {
				Waypoint p1 = trackpoints.get(i);
				Waypoint p2 = trackpoints.get(i+1);
				map.put(p1.getDistance(p2), p1);
				i++;
			}
		}
	}
	
	private void printMap() {
		for(Map.Entry<Double,Waypoint> entry : map.entrySet()) {
			  System.out.println(entry.getKey());
			}
	}
	@Override
	protected void applyAlgorithm() {
		// TODO Auto-generated method stub
		populateMap();
		printMap(); // debug
	}

}