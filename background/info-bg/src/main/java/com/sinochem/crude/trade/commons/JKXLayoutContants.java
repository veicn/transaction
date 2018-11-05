package com.sinochem.crude.trade.commons;

public class JKXLayoutContants {

	public enum JKX_LAYOUT_TYPE{
		TYPE_XFJBFL1("西非装船基本费率1","XFJBFL1","YLSC","untime","zh")
		,TYPE_XFJBFL2("西非装船基本费率2","XFJBFL2","YLSC","untime","zh")
		,TYPE_ALJBFL1("阿拉伯港基本费率1","ALJBFL1","YLSC","untime","zh")
		,TYPE_ALJBFL2("阿拉伯港基本费率2","ALJBFL2","YLSC","untime","zh")
		,TYPE_ALJBFL3("阿拉伯港基本费率3","ALJBFL3","YLSC","untime","zh")
		,TYPE_XFJBFL1EN("West Africa Basic Shipping rate 1","XFJBFL1EN","YLSC_EN","untime","en")
		,TYPE_XFJBFL2EN("West Africa Basic Shipping rate 2","XFJBFL2EN","YLSC_EN","untime","en")
		,TYPE_ALJBFL1EN("Arabian Gulf Basic rate 1","ALJBFL1EN","YLSC_EN","untime","en")
		,TYPE_ALJBFL2EN("Arabian Gulf Basic rate 2","ALJBFL2EN","YLSC_EN","untime","en")
		,TYPE_ALJBFL3EN("Arabian Gulf Basic rate 3","ALJBFL3EN","YLSC_EN","untime","en")
		;
		
		JKX_LAYOUT_TYPE(String jkxType,String jkxCode,String channelCode,String typeTime,String langVer){
    		this.jkxType = jkxType;
    		this.jkxCode = jkxCode;
    		this.channelCode = channelCode;
    		this.typeTime = typeTime;
    		this.langVer = langVer;
    	}
    	private String jkxType; //金凯讯 资讯类型
    	private String jkxCode; //金凯讯类型编码
    	private String channelCode; //对应的频道代码
    	private String typeTime;//对应的日报、周报、月报
    	private String langVer;//语言类型
    	
		public String getJkxType() {
			return jkxType;
		}

		public String getJkxCode() {
			return jkxCode;
		}

		public String getChannelCode() {
			return channelCode;
		}

		public String getTypeTime() {
			return typeTime;
		}

		public String getLangVer() {
			return langVer;
		}

		
	}
}
