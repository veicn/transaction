package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DecMessage")
public class CustomsXmlVO {


	private DecHeadXMLVO DecHead;
	private String version;
	@XmlAttribute
	public String getXmlns() {
		return xmlns;
	}
	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}
	private String xmlns;
	@XmlElement(name="DecHead")
	public DecHeadXMLVO getDecHead() {
		return DecHead;
	}
	public void setDecHead(DecHeadXMLVO DecHead) {
		this.DecHead = DecHead;
	}
	@XmlAttribute
	public String getversion() {
		return version;
	}
	public void setversion(String version) {
		this.version = version;
	}
	@XmlElement(name="DecLists")
	public DecListsXMLVO getDecLists() {
		return DecLists;
	}

	public void setDecLists(DecListsXMLVO decLists) {
		DecLists = decLists;
	}

	private DecListsXMLVO DecLists;

	@XmlElement(name="DecContainers")
	public DecContainersXMLVO getDecContainers() {
		return DecContainers;
	}

	public void setDecContainers(DecContainersXMLVO decContainers) {
		DecContainers = decContainers;
	}

	private DecContainersXMLVO DecContainers;
	@XmlElement(name="DecLicenseDocus")
	public DecLicenseDocusXMLVO getDecLicenseDocus() {
		return DecLicenseDocus;
	}

	public void setDecLicenseDocus(DecLicenseDocusXMLVO decLicenseDocus) {
		DecLicenseDocus = decLicenseDocus;
	}


	private DecLicenseDocusXMLVO DecLicenseDocus;
	@XmlElement(name="DecFreeTxt")
	public DecFreeTxtXMLVO getDecFreeTxt() {
		return DecFreeTxt;
	}

	public void setDecFreeTxt(DecFreeTxtXMLVO decFreeTxt) {
		DecFreeTxt = decFreeTxt;
	}

	private DecFreeTxtXMLVO DecFreeTxt;

	private DecSignXMLVO DecSign;
	@XmlElement(name="DecSign")
	public DecSignXMLVO getDecSign() {
		return DecSign;
	}

	public void setDecSign(DecSignXMLVO decSign) {
		DecSign = decSign;
	}
}


