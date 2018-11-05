//	
//	function divMouseover(obj, orderDocumentId) {
//		return;
//		
//		if(orderDocumentId) {
//			$(obj).addClass("sel");
//		}
//	}
//
//	function divMouseout(obj, orderDocumentId) {
//		return;
//		if(orderDocumentId) {
//			$(obj).removeClass("sel");
//		}
//	}	
//	
//	function divOnclick(uuid, orderDocumentId, documentType, tradeCategory) {
//		window.location.href = orderServer + "/buyerCenter/orderDocument/documentEdit.htm"
//			+ "?uuid=" + uuid
//			+ "&orderDocumentId=" + orderDocumentId
//			+ "&documentType=" + documentType
//			+ "&tradeCategory=" + tradeCategory
//			+ "&formPage=DOCUMENTLIST";
//	}
//	
//	function divOnclick2(uuid) {
//		window.location.href= orderServer + "/common/contract/edit.htm"
//				+ "?ouid=" + uuid + "&redirect=buyerCenter/orderDocument/documentList.htm?uuid="+uuid;
//	}