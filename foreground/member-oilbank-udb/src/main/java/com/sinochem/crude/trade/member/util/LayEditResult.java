package com.sinochem.crude.trade.member.util;

public class LayEditResult {

    private int code;

    private String msg;

    private Detail data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Detail getData() {
        return data;
    }

    public void setData(Detail data) {
        this.data = data;
    }

    public LayEditResult() {
    }

    public LayEditResult(int code, String msg, String src) {
        this.code = code;
        this.msg = msg;
        Detail detail = new Detail();
        detail.setSrc(src);
        this.setData(detail);
    }


    public LayEditResult(int code, String msg, String src, String title) {
        this.code = code;
        this.msg = msg;
        Detail detail = new Detail();
        detail.setSrc(src);
//        detail.setTitle(title);
        this.setData(detail);
    }

    public LayEditResult(String msg, String src) {
        this.code = 0;
        this.msg = msg;
        Detail detail = new Detail();
        detail.setSrc(src);
        this.setData(detail);
    }

    public LayEditResult(String msg, String src, String title) {
        this.code = 0;
        this.msg = msg;
        Detail detail = new Detail();
        detail.setSrc(src);
//        detail.setTitle(title);
        this.setData(detail);
    }

    public static LayEditResult setSuccess(String msg, String src, String title) {
        return new LayEditResult(msg, src, title);
    }

    public static LayEditResult setFail(String msg) {
        return new LayEditResult(-1, msg, null);
    }

    class Detail {
        private String src;

//        private String title;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
    }
}

