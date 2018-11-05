package org.geojson.domain;

import java.io.Serializable;

/**
 * 港口坐标信息实体
 * 
 * @author niuwk
 *
 */
public class HtmlInfo implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 4325481913276695597L;
	
		private double lon;
		private double lng;
		private double lat;
		
		
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
		public double getLon() {
			return lon;
		}
		public void setLon(double lon) {
			this.lon = lon;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		
		
		
			
}
