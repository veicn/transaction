package com.sinochem.crude.trade.broker.domain;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/10 10:55
 * @Version: [v1.0]
 */
public class ResultDataPages extends ResultData{

        private Integer pageNum;

        private Integer pageSize;

        private Integer pageCount;

        private Long total;

        public Integer getPageNum() {
            return pageNum;
        }

        public void setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

}
