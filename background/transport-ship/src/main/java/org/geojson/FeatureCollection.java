package org.geojson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 类型为”FeatureCollection”的GeoJSON对象是特征集合对象。
 *类型为”FeatureCollection”的对象必须由一个名字为”features”的成员。
 *与“features”相对应的值是一个数组。这个数组中的每个元素都是上面定义的特征对象
 * 
 * @author Administrator
 *
 */
public class FeatureCollection extends GeoJsonObject implements Iterable<Feature> {

	private List<Feature> features = new ArrayList<Feature>();

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public FeatureCollection add(Feature feature) {
		features.add(feature);
		return this;
	}

	public void addAll(Collection<Feature> features) {
		this.features.addAll(features);
	}

	@Override
	public Iterator<Feature> iterator() {
		return features.iterator();
	}

	@Override
	public <T> T accept(GeoJsonObjectVisitor<T> geoJsonObjectVisitor) {
		return geoJsonObjectVisitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof FeatureCollection))
			return false;
		FeatureCollection features1 = (FeatureCollection)o;
		return features.equals(features1.features);
	}

	@Override
	public int hashCode() {
		return features.hashCode();
	}

	@Override
	public String toString() {
		return "FeatureCollection{" + "features=" + features + '}';
	}
}
