package com.sinochem.crude.trade.transaction.servlet;

import com.sinochem.crude.trade.transaction.constant.Sinochem;
import com.sinochem.crude.trade.transaction.helper.MemberInfoHelper;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServlet;

/**
 * 启动时加载的Servlet，用来获取中化新和泉化的ID
 * @author Yichen Zhao
 * date: 20180323
 */
public class TransactionStartupServlet extends HttpServlet {

    /*
    @Override
    public void init() {
        MemberInfoHelper memberInfoHelper = ContextLoader.getCurrentWebApplicationContext().getBean(MemberInfoHelper.class);

        Sinochem.SINOCHEM_QUANZHOU_ID = memberInfoHelper.getEnterpriseByName(Sinochem.SINOCHEM_QUANZHOU).getMemberId();
        Sinochem.SINOCHEM_SINGAPORE_ID = memberInfoHelper.getEnterpriseByName(Sinochem.SINOCHEM_SINGAPORE).getMemberId();
    }
    */
}
