package org.geojson;

import java.util.List;
/**
 * 多线
 * 对类型“MultiLineString”来说，”coordinates”成员必须是一个线坐标数组的数组
 * @author niuwk
 *
 */
public class MultiLineString extends Geometry<List<LngLatAlt>> {

	public MultiLineString() {
	}

	public MultiLineString(List<LngLatAlt> line) {
		add(line);
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MultiLineString{} " + super.toString();
	}
}
