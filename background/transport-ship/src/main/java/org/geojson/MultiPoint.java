package org.geojson;

/**
 * 多点
 * 对类型”MultiPoint”来说，”coordinates”成员必须是位置数组
 * @author niuwk
 *
 */
public class MultiPoint extends Geometry<LngLatAlt> {

	public MultiPoint() {
	}

	public MultiPoint(LngLatAlt... points) {
		super(points);
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MultiPoint{} " + super.toString();
	}
}
