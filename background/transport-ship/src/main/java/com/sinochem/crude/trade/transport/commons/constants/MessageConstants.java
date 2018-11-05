package com.sinochem.crude.trade.transport.commons.constants;


/**
 * Created by x on 2018/3/1.
 */
public class MessageConstants {
	
	
	/**APP提醒，站内信，邮件，短信*/
	public enum TYPE {
			/**航次开始-申请租船的操作员 - voyageStart*/
			ITRS001("尊敬的用户：您的租船协议：{0}，已更新配载计划，请您登录买原油及时查看。",	//尊敬的用户：您的租船协议：$!{agreementId}，已更新配载计划，请您登录买原油及时查看。Respected user: the cargo planning in your charter party: $!{agreementId} has been updated, please log onto Buy Crude Oil to promptly view same.
					"尊敬的用户：您的租船协议：{0}，已更新配载计划，请您登录买原油及时查看。",//尊敬的用户：您的租船协议： <a href="$!{idLink}">$!{agreementId}</a>，已更新配载计划，请您登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: the cargo planning in your charter party:  <a href="$!{idLink}">$!{agreementId}</a> has been updated, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"尊敬的用户：您的租船协议：{0}，已更新配载计划，请您登录买原油及时查看。",//尊敬的用户：您的租船协议： <a href="$!{idLink}">$!{agreementId}</a>，已更新配载计划，请您登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: the cargo planning in your charter party:  <a href="$!{idLink}">$!{agreementId}</a> has been updated, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"【买原油】尊敬的用户：您的租船协议：{0}，已更新配载计划，请您登录买原油及时查看。"),//【买原油】尊敬的用户：您的租船协议：$!{agreementId}，已更新配载计划，请您登录买原油及时查看。
			/**装港-申请租船的操作员 -loadPort*/
			ITRS002("尊敬的用户：您的租船协议：{0}，已更新装港信息（船），请您登录买原油及时查看。",//尊敬的用户：您的租船协议：$!{agreementId}，已更新装港信息（船），请您登录买原油及时查看。Respected user: the port of loading information (ship) in your charter party: $!{agreementId} has been updated, please log onto Buy Crude Oil to promptly view same.
					"尊敬的用户：您的租船协议：{0}，已更新装港信息（船），请您登录买原油及时查看。",//尊敬的用户：您的租船协议： <a href="$!{idLink}">$!{agreementId}</a>，已更新装港信息（船），请您登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: the port of loading information (ship) in your charter party:  <a href="$!{idLink}">$!{agreementId}</a> has been updated, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"尊敬的用户：您的租船协议：{0}，已更新装港信息（船），请您登录买原油及时查看。",//尊敬的用户：您的租船协议： <a href="$!{idLink}">$!{agreementId}</a>，已更新装港信息（船），请您登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: the port of loading information (ship) in your charter party:  <a href="$!{idLink}">$!{agreementId}</a> has been updated, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"【买原油】尊敬的用户：您的租船协议：{0}，已更新装港信息（船），请您登录买原油及时查看。"),//【买原油】尊敬的用户：您的租船协议：$!{agreementId}，已更新装港信息（船），请您登录买原油及时查看。
			/**在途-申请租船的操作员 -transitBeg*/
			ITRS003("尊敬的用户：您的租船协议：{0}，已更新在途信息，请您登录买原油及时查看。",//尊敬的用户：您的租船协议：$!{agreementId}，已更新在途信息，请您登录买原油及时查看。Respected user: the in-transit information in your charter party: $!{agreementId} has been updated, please log onto Buy Crude Oil to promptly view same.
					"尊敬的用户：您的租船协议：{0}，已更新在途信息，请您登录买原油及时查看。",//尊敬的用户：您的租船协议：<a href="$!{idLink}">$!{agreementId}</a>，已更新在途信息，请您登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: the in-transit information in your charter party: <a href="$!{idLink}">$!{agreementId}</a> has been updated, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"尊敬的用户：您的租船协议：{0}，已更新在途信息，请您登录买原油及时查看。",//尊敬的用户：您的租船协议：<a href="$!{idLink}">$!{agreementId}</a>，已更新在途信息，请您登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: the in-transit information in your charter party: <a href="$!{idLink}">$!{agreementId}</a> has been updated, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"【买原油】尊敬的用户：您的租船协议：{0}，已更新在途信息，请您登录买原油及时查看。"),//【买原油】尊敬的用户：您的租船协议：$!{agreementId}，已更新在途信息，请您登录买原油及时查看。
			/**卸港-申请租船的操作员 -unloadPort*/
			ITRS004("尊敬的用户：您的租船协议：{0}，已更新卸港信息（船），请您登录买原油及时查看。",//尊敬的用户：您的租船协议：$!{agreementId}，已更新卸港信息（船），请您登录买原油及时查看。Respected user: the port of unloading information (ship) in your charter party: $!{agreementId} has been updated, please log onto Buy Crude Oil to promptly view same.
					"尊敬的用户：您的租船协议：{0}，已更新卸港信息（船），请您登录买原油及时查看。",//尊敬的用户：您的租船协议：<a href="$!{idLink}">$!{agreementId}</a>，已更新卸港信息（船），请您登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: the port of unloading information (ship) in your charter party: <a href="$!{idLink}">$!{agreementId}</a> has been updated, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"尊敬的用户：您的租船协议：{0}，已更新卸港信息（船），请您登录买原油及时查看。",//尊敬的用户：您的租船协议：<a href="$!{idLink}">$!{agreementId}</a>，已更新卸港信息（船），请您登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: the port of unloading information (ship) in your charter party: <a href="$!{idLink}">$!{agreementId}</a> has been updated, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"【买原油】尊敬的用户：您的租船协议：{0}，已更新卸港信息（船），请您登录买原油及时查看。"),//【买原油】尊敬的用户：您的租船协议：$!{agreementId}，已更新卸港信息（船），请您登录买原油及时查看。
			/**航次结束-申请租船的操作员 -transitEnd*/
			ITRS005("尊敬的用户：您的租船协议：{0}，本次航次已结束，请知悉。",//尊敬的用户：您的租船协议：$!{agreementId}，本次航次已结束，请知悉。Respected user: please be informed that the voyage in your charter party: $!{agreementId} has ended.
					"尊敬的用户：您的租船协议：{0}，本次航次已结束，请知悉。",//尊敬的用户：您的租船协议：$!{agreementId}，本次航次已结束，请知悉。Respected user: please be informed that the voyage in your charter party: $!{agreementId} has ended.
					"尊敬的用户：您的租船协议：{0}，本次航次已结束，请知悉。",//尊敬的用户：您的租船协议：$!{agreementId}，本次航次已结束，请知悉。Respected user: please be informed that the voyage in your charter party: $!{agreementId} has ended.
					"【买原油】尊敬的用户：您的租船协议：{0}，本次航次已结束，请知悉。"),//【买原油】尊敬的用户：您的租船协议：$!{agreementId}，本次航次已结束，请知悉。
			/**生成租船协议-买卖家确认租船操作员 -agreement*/
			ITRS006("尊敬的用户：{0}已上传租船协议（租船协议编号：{1}），请登录买原油及时处理。",//尊敬的用户：$!{epMemberName}已上传租船协议（租船协议编号：$!{agreementId}），请登录买原油及时处理。Respected user: $!{epMemberName} has uploaded the Chartering Agreement (Chartering Agreement No.$!{agreementId}), please log onto Buy Crude Oil to promptly handle it.
					"尊敬的用户：{0}已上传租船协议（租船协议编号：{1}），请登录买原油及时处理。",//尊敬的用户：$!{epMemberName}已上传租船协议（租船协议编号：<a href="$!{idLink}">$!{agreementId}</a>），请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has uploaded the Chartering Agreement (Chartering Agreement No.<a href="$!{idLink}">$!{agreementId}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"尊敬的用户：{0}已上传租船协议（租船协议编号：{1}），请登录买原油及时处理。",//尊敬的用户：$!{epMemberName}已上传租船协议（租船协议编号：<a href="$!{idLink}">$!{agreementId}</a>），请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has uploaded the Chartering Agreement (Chartering Agreement No.<a href="$!{idLink}">$!{agreementId}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"【买原油】尊敬的用户：{0}已上传租船协议（租船协议编号：{1}），请您及时处理。"),//【买原油】尊敬的用户：$!{epMemberName}已上传租船协议（租船协议编号：$!{agreementId}），请登录买原油及时处理。
			/**船盘失效-发船盘的操作员 -shipPlate*/
			ITRS007("尊敬的用户：您发布的船盘{0}已过时，请及时登录买原油进行更新。",//尊敬的用户：您发布的船盘$!{shipPlateId}已过时，请及时登录买原油进行更新。Respected user: the ship information $!{shipPlateId} released by you has been outdated, please promptly log onto Buy Crude Oil to update same.
					"尊敬的用户：您发布的船盘{0}已过时，请及时登录买原油进行更新。",//尊敬的用户：您发布的船盘 <a href="$!{idLink}">$!{shipPlateId}</a> 已过时，请及时登录<a href="$!{loginLink}">买原油</a>进行更新。Respected user: the ship information <a href="$!{idLink}">$!{shipPlateId}</a> released by you has been outdated, please promptly log onto <a href="$!{loginLink}">Buy Crude Oil</a> to update same.
					"尊敬的用户：您发布的船盘{0}已过时，请及时登录买原油进行更新。",//尊敬的用户：您发布的船盘 <a href="$!{idLink}">$!{shipPlateId}</a> 已过时，请及时登录<a href="$!{loginLink}">买原油</a>进行更新。Respected user: the ship information <a href="$!{idLink}">$!{shipPlateId}</a> released by you has been outdated, please promptly log onto <a href="$!{loginLink}">Buy Crude Oil</a> to update same.
					"【买原油】尊敬的用户：您发布的船盘{0}已过时，请及时登录买原油进行更新。"),//【买原油】尊敬的用户：您发布的船盘$!{shipPlateId}已过时，请及时登录买原油进行更新。
			/**申请租船-转租船东企业 -applyShip*/
			ITRS008("尊敬的用户：{0}向您发布了一条租船需求（租船需求编号：{1}），请登录买原油及时处理。",//尊敬的用户：$!{epMemberName}向您发布了一条租船需求（租船需求编号：$!{palletId}），请登录买原油及时处理。Respected user: $!{epMemberName} has sent to you a chartering requirement (Chartering Requirement No.$!{palletId}), please log onto Buy Crude Oil to promptly handle it.
					"尊敬的用户：{0}向您发布了一条租船需求（租船需求编号：{1}），请登录买原油及时处理。",//尊敬的用户：$!{epMemberName}向您发布了一条租船需求（租船需求编号：<a href="$!{idLink}">$!{palletId}</a>），请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has sent to you a chartering requirement (Chartering Requirement No.<a href="$!{idLink}">$!{palletId}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"尊敬的用户：{0}向您发布了一条租船需求（租船需求编号：{1}），请登录买原油及时处理。",//尊敬的用户：$!{epMemberName}向您发布了一条租船需求（租船需求编号：<a href="$!{idLink}">$!{palletId}</a>），请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has sent to you a chartering requirement (Chartering Requirement No.<a href="$!{idLink}">$!{palletId}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"【买原油】尊敬的用户：{0}向您发布了一条租船需求（租船需求编号：{1}），请登录买原油及时处理。"),//【买原油】尊敬的用户：$!{epMemberName}向您发布了一条租船需求（租船需求编号：$!{palletId}），请登录买原油及时处理。								
			/**询盘-船盘发布操作员 -inquiry*/
			ITRS009("尊敬的用户：{0}向您发送的询盘信息（询盘单号：{1}），意向装货{2}吨，请登录买原油及时处理。",//尊敬的用户：$!{epMemberName}向您发送的询盘信息（询盘单号：$!{intentionCode}），意向装货$!{quantity}吨，请登录买原油及时处理。Respected user: the inquiry information sent by $!{epMemberName} to you (inquiry No.$!{intentionCode}) covers the intended loading of $!{quantity} tons goods, please log onto Buy Crude Oil to promptly handle it.
					"尊敬的用户：{0}向您发送的询盘信息（询盘单号:{1}），意向装货{2}吨，请登录买原油（超链接到船务中心-船盘管理页面）及时处理。", //尊敬的用户：$!{epMemberName}向您发送的询盘信息（询盘单号:<a href="$!{idLink}">$!{intentionCode}</a>），意向装货$!{quantity}吨，请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: the inquiry information sent by $!{epMemberName} to you (inquiry No.<a href="$!{idLink}">$!{intentionCode}</a>) covers the intended loading of $!{quantity} tons goods, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"尊敬的用户：{0}向您发送的询盘信息（询盘单号:{1}），意向装货{2}吨，请登录买原油（超链接到船务中心-船盘管理页面）及时处理。", //尊敬的用户：$!{epMemberName}向您发送的询盘信息（询盘单号:<a href="$!{idLink}">$!{intentionCode}</a>），意向装货$!{quantity}吨，请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: the inquiry information sent by $!{epMemberName} to you (inquiry No.<a href="$!{idLink}">$!{intentionCode}</a>) covers the intended loading of $!{quantity} tons goods, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"【买原油】尊敬的用户：{0}向您发送的询盘信息（询盘单号：{1}），意向装货{2}吨，请登录买原油及时处理。"),//【买原油】尊敬的用户：$!{epMemberName}向您发送的询盘信息（询盘单号:$!{intentionCode}），意向装货$!{quantity}吨，请登录买原油及时处理。
			/**还盘-询盘操作员 -counter*/
			ITRS010("尊敬的用户：您的询盘单{0}，{1}已响应，请及时处理。",//尊敬的用户：您的询盘单 $!{intentionCode}，$!{epMemberName}已响应，请及时处理。Respected user: $!{epMemberName} has responded to your inquiry $!{intentionCode}, please promptly handle it.
					"尊敬的用户：您的询盘单{0}，{1}已响应，请登录买原油（超链接到船务中心-租船需求管理页面）及时处理。", //尊敬的用户：您的询盘单 <a href="$!{idLink}">$!{intentionCode}</a>，$!{epMemberName}已响应，请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has responded to your inquiry <a href="$!{idLink}">$!{intentionCode}</a>, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"尊敬的用户：您的询盘单{0}，{1}已响应，请登录买原油（超链接到船务中心-租船需求管理页面）及时处理。", //尊敬的用户：您的询盘单 <a href="$!{idLink}">$!{intentionCode}</a>，$!{epMemberName}已响应，请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has responded to your inquiry <a href="$!{idLink}">$!{intentionCode}</a>, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"【买原油】尊敬的用户：您的询盘单{0}，{1}已响应，请及时处理。"),	//【买原油】尊敬的用户：您的询盘单$!{intentionCode}，$!{epMemberName}已响应，请登录买原油及时处理。									
			/**确认租船-发布船盘的操作员 -confirmShip*/
			ITRS011("尊敬的用户：您的还盘单（询盘单号：{0}），{1}已确认，请登录买原油及时处理。",//尊敬的用户：您的还盘单（询盘单号：$!{intentionCode}），$!{epMemberName}已确认，请登录买原油及时处理。Respected user: $!{epMemberName} has confirmed your counter offer (inquiry No.$!{intentionCode}), please log onto Buy Crude Oil to promptly handle it.
					"尊敬的用户：您的还盘单（询盘单号：{0}），{1}已确认，请您登录买原油（超链接到船务中心-船盘管理页面）及时处理。", //尊敬的用户：您的还盘单（询盘单号：<a href="$!{idLink}">$!{intentionCode}</a>），$!{epMemberName}已确认，请您登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has confirmed your counter offer (inquiry No.<a href="$!{idLink}">$!{intentionCode}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"尊敬的用户：您的还盘单（询盘单号：{0}），{1}已确认，请您登录买原油（超链接到船务中心-船盘管理页面）及时处理。", //尊敬的用户：您的还盘单（询盘单号：<a href="$!{idLink}">$!{intentionCode}</a>），$!{epMemberName}已确认，请您登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has confirmed your counter offer (inquiry No.<a href="$!{idLink}">$!{intentionCode}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"【买原油】尊敬的用户：您的还盘单（询盘单号：{0}），{1}已确认，请登录买原油及时处理。"),//【买原油】尊敬的用户：您的还盘单（询盘单号：$!{intentionCode}），$!{epMemberName}已确认，请您登录买原油及时处理。									
			/**船盘发布人终止-询盘操作员 -shipPlateMemberNameCancel*/
			ITRS012("尊敬的用户：非常抱歉，您的询盘单（询盘单号：{0}），{1}已终止，请登录买原油查看详情。",//尊敬的用户：非常抱歉，您的询盘单（询盘单号：$!{intentionCode}），$!{epMemberName}已终止，请登录买原油查看详情。Respected user: Sorry that $!{epMemberName} has ceased your inquiry (Inquiry No.$!{intentionCode}), and please log onto Buy Crude Oil to view the details.
					"尊敬的用户：非常抱歉，您的询盘单（询盘单号：{0}），{1}已终止，请登录买原油（超链接到船务中心-船盘管理页面）查看详情。", //尊敬的用户：非常抱歉，您的询盘单（询盘单号：<a href="$!{idLink}">$!{intentionCode}</a>），$!{epMemberName}已终止，请登录<a href="$!{loginLink}">买原油</a>查看详情。Respected user: Sorry that $!{epMemberName} has ceased your inquiry (Inquiry No.<a href="$!{idLink}">$!{intentionCode}</a>), and please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to view the details.
					"尊敬的用户：非常抱歉，您的询盘单（询盘单号：{0}），{1}已终止，请登录买原油（超链接到船务中心-船盘管理页面）查看详情。", //尊敬的用户：非常抱歉，您的询盘单（询盘单号：<a href="$!{idLink}">$!{intentionCode}</a>），$!{epMemberName}已终止，请登录<a href="$!{loginLink}">买原油</a>查看详情。Respected user: Sorry that $!{epMemberName} has ceased your inquiry (Inquiry No.<a href="$!{idLink}">$!{intentionCode}</a>), and please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to view the details.
					"【买原油】尊敬的用户：非常抱歉，您的询盘单（询盘单号：{0}），{1}已终止，请登录买原油查看详情。"),//【买原油】尊敬的用户：非常抱歉，您的询盘单（询盘单号：$!{intentionCode}），$!{epMemberName}已终止，请登录买原油查看详情。									
			/**询盘人终止-发布船盘操作员 -inquiryCancel*/
			ITRS013("尊敬的用户：非常抱歉，您的还盘单（询盘单号：{0}），{1}已终止，请登录买原油查看详情。",//尊敬的用户：非常抱歉，您的还盘单（询盘单号：$!{intentionCode}），$!{epMemberName}已终止，请登录买原油查看详情。Respected user: Sorry that $!{epMemberName} has ceased your counter offer (Inquiry No.$!{intentionCode}), and please log onto Buy Crude Oil to view the details.
					"尊敬的用户：非常抱歉，您的还盘单（询盘单号：{0}），{1}已终止，请登录买原油（超链接到船务中心-船盘管理页面）查看详情。", //尊敬的用户：非常抱歉，您的还盘单（询盘单号：<a href="$!{idLink}">$!{intentionCode}</a>），$!{epMemberName}已终止，请登录<a href="$!{loginLink}">买原油</a>查看详情。Respected user: Sorry that $!{epMemberName} has ceased your counter offer (Inquiry No.<a href="$!{idLink}">$!{intentionCode}</a>), and please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to view the details.
					"尊敬的用户：非常抱歉，您的还盘单（询盘单号：{0}），{1}已终止，请登录买原油（超链接到船务中心-船盘管理页面）查看详情。", //尊敬的用户：非常抱歉，您的还盘单（询盘单号：<a href="$!{idLink}">$!{intentionCode}</a>），$!{epMemberName}已终止，请登录<a href="$!{loginLink}">买原油</a>查看详情。Respected user: Sorry that $!{epMemberName} has ceased your counter offer (Inquiry No.<a href="$!{idLink}">$!{intentionCode}</a>), and please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to view the details.
					"【买原油】尊敬的用户：非常抱歉，您的还盘单（询盘单号：{0}），{1}已终止，请登录买原油查看详情。"),//【买原油】尊敬的用户：非常抱歉，您的还盘单（询盘单号：$!{intentionCode}），$!{epMemberName}已终止，请登录买原油查看详情。								
			/**报盘-申请租船操作员 -clause*/
			ITRS014("尊敬的用户：{0}向您发送的报盘信息（报盘单号：{1}），意向装货{2}吨，请登录买原油及时处理。",//尊敬的用户：$!{epMemberName}向您发送的报盘信息（报盘单号：$!{ClauseCode}），意向装货$!{quantity}吨，请登录买原油及时处理。Respected user: the offer information sent by $!{epMemberName} to you (Offer No.$!{ClauseCode}) covers the intended loading of $!{quantity} tons goods, please log onto Buy Crude Oil to promptly handle it.
					"尊敬的用户：{0}向您发送的报盘信息（报盘单号：{1}），意向装货{2}吨，请登录买原油（超链接到船务中心-租船需求管理页面）及时处理。", //尊敬的用户：$!{epMemberName}向您发送的报盘信息（报盘单号：<a href="$!{idLink}">$!{ClauseCode}</a>），意向装货$!{quantity}吨，请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: the offer information sent by $!{epMemberName} to you (Offer No.<a href="$!{idLink}">$!{ClauseCode}</a>) covers the intended loading of $!{quantity} tons goods, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"尊敬的用户：{0}向您发送的报盘信息（报盘单号：{1}），意向装货{2}吨，请登录买原油（超链接到船务中心-租船需求管理页面）及时处理。", //尊敬的用户：$!{epMemberName}向您发送的报盘信息（报盘单号：<a href="$!{idLink}">$!{ClauseCode}</a>），意向装货$!{quantity}吨，请登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: the offer information sent by $!{epMemberName} to you (Offer No.<a href="$!{idLink}">$!{ClauseCode}</a>) covers the intended loading of $!{quantity} tons goods, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"【买原油】尊敬的用户：{0}向您发送的报盘信息（报盘单号：{1}），意向装货{2}吨，请登录买原油及时处理。"),//【买原油】尊敬的用户：$!{epMemberName}向您发送的报盘信息（报盘单号：$!{ClauseCode}），意向装货$!{quantity}吨，请登录买原油及时处理。				
			/**确认报盘-转租船东报盘操作员 -confirmClause*/
			ITRS015("尊敬的用户：您的报盘单（报盘单号：{0}），{1}已确认，请登录买原油及时处理。",//尊敬的用户：您的报盘单（报盘单号：$!{ClauseCode}），$!{epMemberName}已确认，请登录买原油及时处理。Respected user: $!{epMemberName} has confirmed your offer (Offer No.$!{ClauseCode}), please log onto Buy Crude Oil to promptly handle it.
					"尊敬的用户：您的报盘单（报盘单号：{0}），{1}已确认，请您登录买原油（超链接到船务中心-租船需求管理页面）及时处理。", //尊敬的用户：您的报盘单（报盘单号：<a href="$!{idLink}">$!{ClauseCode}</a>），$!{epMemberName}已确认，请您登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has confirmed your offer (Offer No.<a href="$!{idLink}">$!{ClauseCode}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"尊敬的用户：您的报盘单（报盘单号：{0}），{1}已确认，请您登录买原油（超链接到船务中心-租船需求管理页面）及时处理。", //尊敬的用户：您的报盘单（报盘单号：<a href="$!{idLink}">$!{ClauseCode}</a>），$!{epMemberName}已确认，请您登录<a href="$!{loginLink}">买原油</a>及时处理。Respected user: $!{epMemberName} has confirmed your offer (Offer No.<a href="$!{idLink}">$!{ClauseCode}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly handle it.
					"【买原油】尊敬的用户：您的报盘单（报盘单号：{0}），{1}）已确认，请登录买原油及时处理。"),//【买原油】尊敬的用户：您的报盘单（报盘单号：$!{ClauseCode}），$!{epMemberName}已确认，请登录买原油及时处理。								
			/**终止报盘-转租船东报盘操作员 -clauseCancel*/
			ITRS016("尊敬的用户：非常抱歉，您的报盘单（报盘单号：{0}），{1}已终止，请登录买原油查看详情。",//尊敬的用户：非常抱歉，您的报盘单（报盘单号：$!{ClauseCode}），$!{Clause}已终止，请登录买原油查看详情。Respected user: Sorry that $!{epMemberName} has ceased your offer (Offer No.$!{ClauseCode}), and please log onto Buy Crude Oil to view the details.
					"尊敬的用户：非常抱歉，您的报盘单（报盘单号：{0}），{1}已终止，请登录买原油（超链接到船务中心-租船需求管理页面）查看详情。", //尊敬的用户：非常抱歉，您的报盘单（报盘单号：<a href="$!{idLink}">$!{ClauseCode}</a>），$!{epMemberName}已终止，请登录<a href="$!{loginLink}">买原油</a>查看详情。Respected user: $!{epMemberName} has ceased your offer (Offer No.<a href="$!{idLink}">$!{ClauseCode}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to view the details.
					"尊敬的用户：非常抱歉，您的报盘单（询盘单号：{0}），{1}已终止，请登录买原油（超链接到船务中心-租船需求管理页面）查看详情。", //尊敬的用户：非常抱歉，您的报盘单（报盘单号：<a href="$!{idLink}">$!{ClauseCode}</a>），$!{epMemberName}已终止，请登录<a href="$!{loginLink}">买原油</a>查看详情。Respected user: $!{epMemberName} has ceased your offer (Offer No.<a href="$!{idLink}">$!{ClauseCode}</a>), please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to view the details.
					"【买原油】尊敬的用户：非常抱歉，您的报盘单（报盘单号：{0}），{1}已终止，请登录买原油查看详情。"),//【买原油】尊敬的用户：非常抱歉，您的报盘单（报盘单号：$!{ClauseCode），$!{epMemberName}已终止，请登录买原油查看详情。										
			/**船舶审核通过-新增船舶方 -approveShip*/
			ITRS017("尊敬的用户：您添加的{0}船舶，已通过平台审核，您可登录买原油继续进行租船操作。",//尊敬的用户：您添加的$!{sysShipId}船舶，已通过平台审核，您可登录买原油继续进行租船操作。Respected user: ship $!{sysShipId} added by you has passed the platform examination, and you may log onto Buy Crude Oil to continue to conduct the chartering operation.
					"尊敬的用户：您添加的{0}船舶，已通过平台审核，您可登录买原油继续进行租船操作。",//尊敬的用户：您添加的 <a href="$!{idLink}">$!{sysShipId}</a> 船舶，已通过平台审核，您可登录<a href="$!{loginLink}">买原油</a>继续进行租船操作。Respected user: ship <a href="$!{idLink}">$!{sysShipId}</a> added by you has passed the platform examination, and you may log onto <a href="$!{loginLink}">Buy Crude Oil</a> to continue to conduct the chartering operation.
					"尊敬的用户：您添加的{0}船舶，已通过平台审核，您可登录买原油继续进行租船操作。", //尊敬的用户：您添加的 <a href="$!{idLink}">$!{sysShipId}</a> 船舶，已通过平台审核，您可登录<a href="$!{loginLink}">买原油</a>继续进行租船操作。Respected user: ship <a href="$!{idLink}">$!{sysShipId}</a> added by you has passed the platform examination, and you may log onto <a href="$!{loginLink}">Buy Crude Oil</a> to continue to conduct the chartering operation.
					"【买原油】尊敬的用户：您添加的{0}船舶，已通过平台审核，您可登录买原油继续进行租船操作。"),//【买原油】尊敬的用户：您添加的$!{sysShipId}船舶，已通过平台审核，您可登录买原油继续进行租船操作。									
			/**船舶审核驳回-新增船舶方 -rejectShip*/
			ITRS018("尊敬的用户：非常抱歉，您添加的{0}船舶，未通过平台审核，请登录买原油查看详情。",//尊敬的用户：非常抱歉，您添加的$!{sysShipId}船舶，未通过平台审核，请登录买原油查看详情。Respected user: Sorry that ship $!{sysShipId} added by you hasn’t passed the platform examination, and please log onto Buy Crude Oil to view the details.
					"尊敬的用户：非常抱歉，您添加的{0}船舶，未通过平台审核，请登录买原油查看详情。",//尊敬的用户：非常抱歉，您添加的 <a href="$!{idLink}">$!{sysShipId}</a> 船舶，未通过平台审核，请登录<a href="$!{loginLink}">买原油</a>查看详情。Respected user: Sorry that ship <a href="$!{idLink}">$!{sysShipId}</a> added by you hasn’t passed the platform examination, and please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to view the details.
					"尊敬的用户：非常抱歉，您添加的{0}船舶，未通过平台审核，请登录买原油查看详情。", //尊敬的用户：非常抱歉，您添加的 <a href="$!{idLink}">$!{sysShipId}</a> 船舶，未通过平台审核，请登录<a href="$!{loginLink}">买原油</a>查看详情。Respected user: Sorry that ship <a href="$!{idLink}">$!{sysShipId}</a> added by you hasn’t passed the platform examination, and please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to view the details.
					"【买原油】尊敬的用户：非常抱歉，您添加的{0}船舶，未通过平台审核，请登录买原油查看详情。"),//【买原油】尊敬的用户：非常抱歉，您添加的$!{sysShipId}船舶，未通过平台审核，请登录买原油查看详情。	
			/**指定船代装港-船代企业 -chuandaiLoadPort*/
			ITRS019("尊敬的用户：{0}已将您指定为[{1}船名]在[{2}港口]的装港船代，请登录买原油及时查看并录入装港信息。",//尊敬的用户：$!{epMemberName}已将您指定为[$!{shipName}]在[$!{port}]的装港船代，请登录买原油及时查看并录入装港信息。Respected user: $!{epMemberName} has appointed you as the shipping agent of [$!{shipName}] at the [$!{port}] of loading, please log onto Buy Crude Oil to promptly view and enter the port of loading information.
					"尊敬的用户：{0}已将您指定为[{1}船名]在[{2}港口]的装港船代，请登录买原油（超链接到船合同管理列表页面）及时查看并录入装港信息。", //尊敬的用户：$!{epMemberName}已将您指定为[<a href="$!{idLink}">$!{shipName}</a>]在[$!{port}]的装港船代，请登录<a href="$!{loginLink}">买原油</a>及时查看并录入装港信息。Respected user: $!{epMemberName} has appointed you as the shipping agent of [<a href="$!{idLink}">$!{shipName}</a>] at the [$!{port}] of loading, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view and enter the port of loading information.
					"尊敬的用户：{0}已将您指定为[{1}船名]在[{2}港口]的装港船代，请登录买原油（超链接到船合同管理列表页面）及时查看并录入装港信息。", //尊敬的用户：$!{epMemberName}已将您指定为[<a href="$!{idLink}">$!{shipName}</a>]在[$!{port}]的装港船代，请登录<a href="$!{loginLink}">买原油</a>及时查看并录入装港信息。Respected user: $!{epMemberName} has appointed you as the shipping agent of [<a href="$!{idLink}">$!{shipName}</a>] at the [$!{port}] of loading, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view and enter the port of loading information.
					"【买原油】尊敬的用户：{0}已将您指定为[{1}船名]在[{2}港口]的装港船代，请登录买原油及时查看并录入装港信息。"),	//【买原油】尊敬的用户：$!{epMemberName}已将您指定为[$!{shipName}]在[$!{port}]的装港船代，请登录买原油及时查看并录入装港信息。									
			/**指定船代卸港-船代企业 -chuandaiUnloadPort*/
			ITRS020("尊敬的用户：{0}已将您指定为[{1}船名]在[{2}港口]的卸港船代，请登录买原油及时查看并录入卸港信息。",//尊敬的用户：$!{epMemberName}已将您指定为[$!{shipName}]在[$!{port}]的卸港船代，请登录买原油及时查看并录入卸港信息。Respected user: $!{epMemberName} has appointed you as the shipping agent of [$!{shipName}] at the [$!{port}] of unloading, please log onto Buy Crude Oil to promptly view and enter the port of unloading information.
					"尊敬的用户：{0}已将您指定为[{1}船名]在[{2}港口]的卸港船代，请登录买原油（超链接到船合同管理列表页面）及时查看并录入卸港信息。", //尊敬的用户：$!{epMemberName}已将您指定为[<a href="$!{idLink}">$!{shipName}</a>]在[$!{port}]的卸港船代，请登录<a href="$!{loginLink}">买原油</a>及时查看并录入卸港信息。Respected user: $!{epMemberName} has appointed you as the shipping agent of [<a href="$!{idLink}">$!{shipName}</a>] at the [$!{port}] of unloading, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view and enter the port of unloading information.
					"尊敬的用户：{0}已将您指定为[{1}船名]在[{2}港口]的卸港船代，请登录买原油（超链接到船合同管理列表页面）及时查看并录入卸港信息。", //尊敬的用户：$!{epMemberName}已将您指定为[<a href="$!{idLink}">$!{shipName}</a>]在[$!{port}]的卸港船代，请登录<a href="$!{loginLink}">买原油</a>及时查看并录入卸港信息。Respected user: $!{epMemberName} has appointed you as the shipping agent of [<a href="$!{idLink}">$!{shipName}</a>] at the [$!{port}] of unloading, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view and enter the port of unloading information.
					"【买原油】尊敬的用户：{0}已将您指定为[{1}船名]在[{2}港口]的卸港船代，请登录买原油及时查看并录入卸港信息。"),	//【买原油】尊敬的用户：$!{epMemberName}已将您指定为[$!{shipName}]在[$!{port}]的卸港船代，请登录买原油及时查看并录入卸港信息。									
			/**已结算-申请租船操作员 -calculate*/
			ITRS021("尊敬的用户：您的租船协议：{0}，{1}已上传结算单（{2}），请登录买原油及时查看。",//尊敬的用户：您的租船协议：$!{agreementId}，$!{epMemberName}已上传结算单（$!{calculate}），请登录买原油及时查看。Respected user: $!{epMemberName} has uploaded the settlement statement ($!{calculate}) for your chartering Agreement: $!{agreementId}, please log onto Buy Crude Oil to promptly view same.
					"尊敬的用户：您的租船协议：{0}，{1}已上传结算单（{2}），请登录买原油（超链接到船务中心-结算单管理）及时查看。", //尊敬的用户：您的租船协议：$!{agreementId}，$!{epMemberName}已上传结算单（<a href="$!{idLink}">$!{calculate}</a>），请登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: $!{epMemberName} has uploaded the settlement statement (<a href="$!{idLink}">$!{calculate}</a>) for your Chartering Agreement: $!{agreementId}, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"尊敬的用户：您的租船协议：{0}，{1}已上传结算单（{2}），请登录买原油（超链接到船务中心-结算单管理）及时查看。", //尊敬的用户：您的租船协议：$!{agreementId}，$!{epMemberName}已上传结算单（<a href="$!{idLink}">$!{calculate}</a>），请登录<a href="$!{loginLink}">买原油</a>及时查看。Respected user: $!{epMemberName} has uploaded the settlement statement (<a href="$!{idLink}">$!{calculate}</a>) for your Chartering Agreement: $!{agreementId}, please log onto <a href="$!{loginLink}">Buy Crude Oil</a> to promptly view same.
					"【买原油】尊敬的用户：您的租船协议：{0}，{1}已上传结算单（{2}），请登录买原油及时查看。"),//【买原油】尊敬的用户：您的租船协议：$!{agreementId}，$!{epMemberName}已上传结算单（$!{calculate}），请登录买原油及时查看。										
													
													
													
			ITRS100("", "", "", "");
			
			
			
			/**app提醒文本*/
			private String appText;
			/**站内信*/
			private String mail;
			/**邮件*/
			private String email;
			/**短信*/
			private String sms;
			
			TYPE(String appText, String mail, String email, String sms) {
				this.appText = appText;
				this.mail = mail;
				this.email = email;
				this.sms = sms;
			}
			/**app提醒文本*/
			public String getAppText() {
				return appText;
			}
			/**站内信*/
			public String getMail() {
				return mail;
			}
			/**邮件*/
			public String getEmail() {
				return email;
			}
			/**短信*/
			public String getSms() {
				return sms;
			}
		}
    
    
    
}
