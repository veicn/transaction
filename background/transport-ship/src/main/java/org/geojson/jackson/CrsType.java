package org.geojson.jackson;
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
 * CRS对象也可以连接到互联网上的CRS参数。
 * 在这种情况下，它的”type”成员的值必须是字符串”link”,
 * 它的”properties”成员的值必须是一个连接对象
 * "crs": {
 * "type": "link",
 *"properties": {
 *   "href": "http://example.com/crs/42",
 *   "type": "proj4"
 *   }
 * }
 * 
 * @author niuwk
 *
 */
public enum CrsType {
	name, link;
}
