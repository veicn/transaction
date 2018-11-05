package com.sinochem.crude.trade.inspector.controllers;

import com.sinochem.crude.trade.inspector.domain.*;
import com.sinochem.crude.trade.inspector.domain.vo.TCommonAttachmentsListVO;
import com.sinochem.crude.trade.inspector.feign.HttpFeignClient;
import com.sinochem.crude.trade.inspector.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InspectorHttpApiController {

    @Autowired
    HttpFeignClient httpFeignClient;

    @RequestMapping("/test")
    public String show(){
        return "Hello World";
    }

    @RequestMapping(value = "/testHttpAPI", method = RequestMethod.POST)
    public SaleSheet testHttpAPI(@RequestBody SaleSheet saleSheetQuery) {
        return httpFeignClient.testHttpAPI(saleSheetQuery);
    }

    @RequestMapping(value = "/testApi")
    public String testApi() {
        return  httpFeignClient.testApi();
    }

    /**
     * 查询商检报告详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/inspector/report/getinfo.json", method = RequestMethod.GET)
    public TInspectorPresentation getinfo(@RequestParam("id") Long id) {
        ReportInfoQuery tmp = new ReportInfoQuery();
        tmp.setId(id);
        return httpFeignClient.getinfo(tmp);
    }

    /**
     * 查询商检报告列表
     * @param reportInfoQuery
     * @return
     */
    @RequestMapping(value = "/inspector/report/getlist.json", method = RequestMethod.POST)
    public ResultDatas getList(@RequestBody ReportInfoQuery reportInfoQuery,HttpSession session) {
        Map _user=(HashMap) session.getAttribute("_user");
        if(null!=_user&&null!=_user.get("_epId")){
            reportInfoQuery.setInspector_id("" + _user.get("_epId"));
        }else{
            ResultDatas res = new ResultDatas();
            res.setStatus(999);
            res.setMessage("session中未查询到 _epid");
            return res;
        }
        return  httpFeignClient.getList(reportInfoQuery);
    }

    /**
     * 商家报告暂存
     * @param reportInfo
     * @return
     */
    @RequestMapping(value = "/inspector/report/save.json", method = RequestMethod.POST)
    public ResultDatas save(@RequestBody TInspectorPresentation reportInfo,HttpSession session) {
        Map _user=(HashMap) session.getAttribute("_user");
        if(null==_user&&null==_user.get("_epId")){
            ResultDatas res = new ResultDatas();
            res.setStatus(999);
            res.setMessage("session中未查询到 _epid");
            return res;
        }
        //暂存states 0
        reportInfo.setState("0");
        //有id更新无id新增
        if(reportInfo.getId()==null||StringUtil.isEmpty("" + reportInfo.getId())){
            reportInfo.setCreateUser(Long.valueOf("" + _user.get("_epId")));
            reportInfo.setUpdateUser(null);
            //新增
            return  httpFeignClient.save(reportInfo);
        }else{
            reportInfo.setUpdateUser(Long.valueOf("" + _user.get("_epId")));
            //更新
            return httpFeignClient.update(reportInfo);
        }
        //更新商检任务状态
    }

    /**
     * 商检报告提交
     * @param reportInfo
     * @return
     */
    @RequestMapping(value = "/inspector/report/submit.json", method = RequestMethod.POST)
    public ResultDatas submit(@RequestBody TInspectorPresentation reportInfo,HttpSession session) {
        Map _user=(HashMap) session.getAttribute("_user");
        if(null==_user&&null==_user.get("_epId")){
            ResultDatas res = new ResultDatas();
            res.setStatus(999);
            res.setMessage("session中未查询到 _epid");
            return res;
        }
        //暂存states 1
        reportInfo.setState("1");
        //有id更新无id新增
        if(reportInfo.getId()==null||StringUtil.isEmpty("" + reportInfo.getId())){
            reportInfo.setCreateUser(Long.valueOf("" + _user.get("_epId")));
            reportInfo.setUpdateUser(null);
            //新增
            return  httpFeignClient.save(reportInfo);
        }else{
            reportInfo.setUpdateUser(Long.valueOf("" + _user.get("_epId")));
            //更新
            return httpFeignClient.update(reportInfo);
        }

    }


    /**
     * 商检任务查询
     * @param taskInfoQuery
     * @return
     */
    @RequestMapping(value = "/inspector/task/get.json", method = RequestMethod.POST)
    public ResultDatas taskget(@RequestBody TaskInfoQuery taskInfoQuery,HttpSession session) {
        Map _user=(HashMap) session.getAttribute("_user");
        if(null!=_user&&null!=_user.get("_epId")){
            taskInfoQuery.setEnterprise_id(Long.valueOf("" + _user.get("_epId")));
        }else{
            ResultDatas res = new ResultDatas();
            res.setStatus(999);
            res.setMessage("session中未查询到 _epid");
            return res;
        }
        return  httpFeignClient.getTaskList(taskInfoQuery);
    }

    /**
     * 商检报告附件上传
     * @param fileInfo
     * @return
     */
    @RequestMapping(value = "/inspector/BillsFile/insert.json", method = RequestMethod.POST)
    public ResultDatas insertBillsFileList(@RequestBody FileInfoVO fileInfo,HttpSession session) {
        Map _user=(HashMap) session.getAttribute("_user");
        if(null!=_user&&null!=_user.get("_epId")){
            fileInfo.setCreateUser(Long.valueOf("" + _user.get("_epId")));
        }else{
            ResultDatas res = new ResultDatas();
            res.setStatus(999);
            res.setMessage("session中未查询到 _epid");
            return res;
        }
        return  httpFeignClient.insertBillsFile(fileInfo);
    }


    /**
     * 商检报告附件列表查看
     * @param businessUuid
     * @return
     */
    @RequestMapping(value = "/inspector/BillsFile/list.json", method = RequestMethod.GET)
    public ResultDatas selectBillsFileList(String businessUuid) {
        return  httpFeignClient.selectBillsFileList(businessUuid);
    }

    /**
     * 商检报告附件删除
     * @param fileInfoVO
     * @return
     */
    @RequestMapping(value = "/inspector/BillsFile/delete.json", method = RequestMethod.POST)
    public ResultDatas deleteBillsFile(@RequestBody FileInfoVO fileInfoVO,HttpSession session) {
        Map _user=(HashMap) session.getAttribute("_user");
        if(null!=_user&&null!=_user.get("_epId")){
            fileInfoVO.setCreateUser(Long.valueOf("" + _user.get("_epId")));
        }else{
            ResultDatas res = new ResultDatas();
            res.setStatus(999);
            res.setMessage("session中未查询到 _epid");
            return res;
        }
        return  httpFeignClient.selectBillsFileDelete(fileInfoVO);
    }

    /**
     * 商检交易信息查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/inspector/transaction/get.json", method = RequestMethod.GET)
    public Map<String,Object> getTransactionInfo(String id) {
        return  httpFeignClient.getTransactionInfo(id);
    }

    /**
     * 商检报告billno模糊查询下拉
     * @param billno
     * @return
     */
    @RequestMapping(value = "/inspector/report/getBillno.json", method = RequestMethod.GET)
    public ResultDatas getReportBillNo(String billno,HttpSession session) {
        ReportInfoQuery reportInfoQuery = new ReportInfoQuery();
        reportInfoQuery.setBillno(billno);
        Map _user=(HashMap) session.getAttribute("_user");
        if(null!=_user&&null!=_user.get("_epId")){
            reportInfoQuery.setCreateUser(Long.valueOf("" + _user.get("_epId")));
        }else{
            ResultDatas res = new ResultDatas();
            res.setStatus(999);
            res.setMessage("session中未查询到 _epid");
            return res;
        }
        return  httpFeignClient.getReportBillNo(reportInfoQuery);
    }

}