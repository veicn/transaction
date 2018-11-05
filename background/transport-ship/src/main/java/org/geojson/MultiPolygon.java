package org.geojson;

import java.util.List;

/**
 * 多面
 * 对类型”MultiPlygon”来说，”coordinates”成员必须是面坐标数组的数组
 * @author niuwk
 *
 */
public class MultiPolygon extends Geometry<List<List<LngLatAlt>>> {

	public MultiPolygon() {
	}

	public MultiPolygon(Polygon polygon) {
		add(polygon);
	}

	public MultiPolygon add(Polygon polygon) {
		coordinates.add(polygon.getCoordinates());
		return this;
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MultiPolygon{} " + super.toString();
	}
}
