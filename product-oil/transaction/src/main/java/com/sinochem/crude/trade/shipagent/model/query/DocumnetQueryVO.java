package com.sinochem.crude.trade.shipagent.model.query;

import com.sinochem.crude.trade.blockchain.domain.TShipagentDocument;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import java.io.Serializable;

/**
 *
 * @author admin1
 * @date 2018/9/18
 */
public class DocumnetQueryVO extends TShipagentDocument implements Serializable{



	@JsonUnwrapped
	private SimplePageInfo simplePageInfo;

	public SimplePageInfo getSimplePageInfo() {
		return simplePageInfo;
	}

	public void setSimplePageInfo(SimplePageInfo simplePageInfo) {
		this.simplePageInfo = simplePageInfo;
	}
}
