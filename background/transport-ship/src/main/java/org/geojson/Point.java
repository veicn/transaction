package org.geojson;


/**
 * 点
 * 对类型”Point”来说，“coordinates”成员必须是一个单独的位置。
 * @author niuwk
 *
 */
public class Point extends GeoJsonObject {

	//private LngLatAlt coordinates;//一个单独的位置
	
	//private String coordinates ;//自定义属性 niuwk
	
	private double[] coordinates = new double[0];//自定义属性 niuwk
	
	


/*	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}*/

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

	public Point() {
	}

	/*public Point(LngLatAlt coordinates) {
		this.coordinates = coordinates;
	}

	public Point(double longitude, double latitude) {
		coordinates = new LngLatAlt(longitude, latitude);
	}

	public Point(double longitude, double latitude, double altitude) {
		coordinates = new LngLatAlt(longitude, latitude, altitude);
	}

	public Point(double longitude, double latitude, double altitude, double... additionalElements) {
		coordinates = new LngLatAlt(longitude, latitude, altitude, additionalElements);
	}

	public LngLatAlt getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(LngLatAlt coordinates) {
		this.coordinates = coordinates;
	}
*/
	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Point point = (Point)o;
		return !(coordinates != null ? !coordinates.equals(point.coordinates) : point.coordinates != null);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Point{" + "coordinates=" + coordinates + "} " + super.toString();
	}
	
	
}
