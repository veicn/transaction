package com.sinochem.crude.trade.inspector.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorPresentation;
import com.sinochem.crude.trade.inspector.model.query.ReportInfoQuery;
import com.sinochem.crude.trade.inspector.model.query.TaskInfoQuery;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.model.vo.ResultDatas;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.Map;

public interface ReportInfoService {

    /**
     * 新增
     */
    ResultDatas insertRecord(TInspectorPresentation reportInfo) throws BizException;

    /**
     * 修改保存
     */
    ResultDatas updateRecord(TInspectorPresentation reportInfo) throws BizException;

    /**
     * 提交保存
     */
    int submitRecord(TInspectorPresentation reportInfo,/**  List<ChmentInspectorVO> chmentsVOList ,*/CurrentUser user);

    /**
     * 查询报告详情
     */
    TInspectorPresentation queryReportInfo(ReportInfoQuery reportInfoQuery);


    /**
     * 查询报告列表
     */
    Page<Map<String, Object>> queryReportInfoList(ReportInfoQuery reportInfoQuery, SimplePageInfo pageInfo);

    /**
     * 查询报告详情
     * @return
     */
    TInspectorPresentation queryReportInfoById(Long id);

    /**
     * 查询报告详情
     * @return
     */
    Page<Map<String, Object>> queryTaskList(TaskInfoQuery inspectorAppoint, SimplePageInfo pageInfo);

    /**
     * 查询交易信息
     * @return
     */
    Map<String, Object> getTransactionInfo(String id);


    /**
     * 商检报告billno模糊查询下拉
     */
    Map<String, Object> getReportBillNo(ReportInfoQuery reportInfoQuery);
}
