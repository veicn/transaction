package org.geojson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 类型为”Feature”的GeoJSON对象是特征对象
 * 特征对象必须由一个名字为”geometry”的成员，这个几何成员的值是上面定义的几何对象或者JSON的null值。
 * 特征对戏那个必须有一个名字为“properties”的成员，这个属性成员的值是一个对象（任何JSON对象或者JSON的null值）。
 * 如果特征是常用的标识符，那么这个标识符应当包含名字为“id”的特征对象成员
 * 
 * @author niuwk
 *
 */
public class Feature extends GeoJsonObject {

	@JsonInclude(JsonInclude.Include.ALWAYS)
	private Map<String, Object> properties = new HashMap<String, Object>();
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private GeoJsonObject geometry;
	private String id;

	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getProperty(String key) {
		return (T)properties.get(key);
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public GeoJsonObject getGeometry() {
		return geometry;
	}

	public void setGeometry(GeoJsonObject geometry) {
		this.geometry = geometry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Feature feature = (Feature)o;
		if (properties != null ? !properties.equals(feature.properties) : feature.properties != null)
			return false;
		if (geometry != null ? !geometry.equals(feature.geometry) : feature.geometry != null)
			return false;
		return !(id != null ? !id.equals(feature.id) : feature.id != null);
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (properties != null ? properties.hashCode() : 0);
		result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Feature{properties=" + properties + ", geometry=" + geometry + ", id='" + id + "'}";
	}
}
