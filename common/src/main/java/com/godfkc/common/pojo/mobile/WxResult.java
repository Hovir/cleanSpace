package com.godfkc.common.pojo.mobile;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2017/12/19
 * @description
 */
public class WxResult {
    private String status;
    private String order_no;
    private String payUrl;
    private String s_no;
    private String msg;
    private String b_id;
    private String bm_id;
    private String order_price;
    private String order_time;
    private String is_pay;
    private String transaction_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getS_no() {
        return s_no;
    }

    public void setS_no(String s_no) {
        this.s_no = s_no;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getBm_id() {
        return bm_id;
    }

    public void setBm_id(String bm_id) {
        this.bm_id = bm_id;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(String is_pay) {
        this.is_pay = is_pay;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    @Override
    public String toString() {
        return "WxResult{" +
                "status='" + status + '\'' +
                ", order_no='" + order_no + '\'' +
                ", payUrl='" + payUrl + '\'' +
                ", s_no='" + s_no + '\'' +
                ", msg='" + msg + '\'' +
                ", b_id='" + b_id + '\'' +
                ", bm_id='" + bm_id + '\'' +
                ", order_price='" + order_price + '\'' +
                ", order_time='" + order_time + '\'' +
                ", is_pay='" + is_pay + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                '}';
    }

}
