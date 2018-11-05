package org.geojson;

/**
 * 线
 * 对类型”LineString”来说，“coordinates”成员必须是两个或者多个位置的数组。
 * @author niuwk
 *
 */
public class LineString extends MultiPoint {

	public LineString() {
	}

	public LineString(LngLatAlt... points) {
		super(points);
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "LineString{} " + super.toString();
	}
}
