package org.geojson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.geojson.jackson.CrsType;

/**
 * CRS对象可以通过名字来表明坐标参考系统。
 * 在这种情况下，它的”type”成员的值必须是字符串”name”。
 * 它的”properties”成员的值必须是包含”name”成员的对象。
 * 这个”name”成员的值必须是标识坐标参考系统的字符串。
 * 比如“urn:ogc:def:crs:OGC:1.3:CRS84“的OGC CRS的URN应当优先于旧的标识符如”EPSG:4326”得到选用：
 * "crs": {
 * "type": "name",
 * "properties": {
 *  "name": "urn:ogc:def:crs:OGC:1.3:CRS84"
 *  }
 * }
 * @author niuwk
 *
 */
public class Crs implements Serializable{

	private CrsType type = CrsType.name;
	private Map<String, Object> properties = new HashMap<String, Object>();

	public CrsType getType() {
		return type;
	}

	public void setType(CrsType type) {
		this.type = type;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Crs)) {
			return false;
		}
		Crs crs = (Crs)o;
		if (properties != null ? !properties.equals(crs.properties) : crs.properties != null) {
			return false;
		}
		return !(type != null ? !type.equals(crs.type) : crs.type != null);
	}

	@Override
	public int hashCode() {
		int result = type != null ? type.hashCode() : 0;
		result = 31 * result + (properties != null ? properties.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Crs{" + "type='" + type + '\'' + ", properties=" + properties + '}';
	}
}
