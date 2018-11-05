package org.geojson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 几何集合
 * 类型为”GeometryCollection”的GeoJSON对象是一个集合对象，它表示几何对象的集合。
 *  几何集合必须有一个名字为”geometries”的成员。与”geometries”相对应的值是一个数组。
 *  这个数组中的每个元素都是一个GeoJSON几何对象。
 * @author Administrator
 *
 */
public class GeometryCollection extends GeoJsonObject implements Iterable<GeoJsonObject> {

	private List<GeoJsonObject> geometries = new ArrayList<GeoJsonObject>();

	public List<GeoJsonObject> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<GeoJsonObject> geometries) {
		this.geometries = geometries;
	}

	@Override
	public Iterator<GeoJsonObject> iterator() {
		return geometries.iterator();
	}

	public GeometryCollection add(GeoJsonObject geometry) {
		geometries.add(geometry);
		return this;
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof GeometryCollection))
			return false;
		if (!super.equals(o))
			return false;
		GeometryCollection that = (GeometryCollection)o;
		return !(geometries != null ? !geometries.equals(that.geometries) : that.geometries != null);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (geometries != null ? geometries.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "GeometryCollection{" + "geometries=" + geometries + "} " + super.toString();
	}
}
