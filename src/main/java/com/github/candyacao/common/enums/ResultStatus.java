package com.github.candyacao.common.enums;

import lombok.Data;

/**
 * 普通返回类
 * 1打头 系统系列错误
 * 2 注册登录系列错误
 * 3 check 系列错误
 * 4 秒杀错误
 * 5 商品错误
 * 6 订单错误
 * 7 商品错误
 * 8 购物车错误
 */
public enum ResultStatus {
    SUCCESS(0, "成功"),
    FAILD(-1, "失败"),
    EXCEPTION(-1, "系统异常"),
    PARAM_ERROR(10000, "参数错误"),
    SYSTEM_ERROR(10001, "系统错误"),
    FILE_NOT_EXIST(10002, "文件不存在"),
    FILE_NOT_DOWNLOAD(10003, "文件没有下载"),
    FILE_NOT_GENERATE(10004, "文件没有生成"),
    FILE_NOT_STORAGE(10005, "文件没有入库"),
    SYSTEM_DB_ERROR(10006, "数据库系统错误"),
    FILE_ALREADY_DOWNLOAD(10007, "文件已经下载"),
    DATA_ALREADY_PEXISTS(10008, "数据已经存在"),

    /**
     * 注册登录
     */
    RESIGETR_SUCCESS(20000, "注册成功!"),
    RESIGETER_FAIL(20001, "注册失败!"),
    CODE_FAIL(20002, "验证码不一致!"),
    RESIGETR_ISEXSIT(20003, "该用户已注册"),

    /**
     * 商品模块
     */
    PRODUCT_ISNOT_EXSIT(70001, "商品或秒杀商品不存在"),
    PRODUCT_DEC_FAIL(70002, "商品或秒杀商品减少库存失败"),
    PRODUCT_ADD_FAIL(70003, "商品或秒杀商品添加数据失败"),
    PRODUCT_DELETE_FAIL(70004, "商品或秒杀商品删除数据失败"),
    PRODUCT_UPDATE_FAIL(70005, "商品更新数据失败"),

    /**
     * 购物车模块
     */
    CART_ADDGOOD_FAIL(80001, "商品添加购物车失败"),
    CART_UPDATEGOOD_FAIL(80002,"更改购物车商品信息失败"),
    CART_DELETEGOOD_FAIL(80003,"删除购物车商品失败"),
    CART_DECGOOD_FAIL(80004,"减少购物车商品数量失败"),

    /**
     * check
     */
    BIND_ERROR(30001, "参数校验异常：%s"),
    ACCESS_LIMIT_REACHED(30002, "请求非法!"),
    REQUEST_ILLEGAL(30004, "访问太频繁!"),
    SESSION_ERROR(30005, "Session不存在或者已经失效!"),
    PASSWORD_EMPTY(30006, "登录密码不能为空!"),
    MOBILE_EMPTY(30007, "手机号不能为空!"),
    MOBILE_ERROR(30008, "手机号格式错误!"),
    MOBILE_NOT_EXIST(30009, "手机号不存在!"),
    PASSWORD_ERROR(30010, "密码错误!"),
    USER_NOT_EXIST(30011, "用户不存在！"),
    USERNAME_OR_PASSWD_EMPTY(30012, "用户名或密码不能为空"),
    NEED_LOGIN(30013, "用户未登录"),

    /**
     * 订单模块
     */
    ORDER_NOT_EXIST(60001, "订单不存在"),
    ORDER_CREATE_FAIL(60002, "订单创建失败"),
    ORDER_DELETE_FAIL(60003, "订单删除失败"),

    /**
     * 秒杀模块
     */
    MIAO_SHA_OVER(40001, "商品已经秒杀完毕"),
    REPEATE_MIAOSHA(40002, "不能重复秒杀"),
    MIAOSHA_FAIL(40003, "秒杀失败");

    /**
     * 商品模块
     */
    private int code;
    private String message;

    private ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return this.name();
    }

    public String getOutputName() {
        return this.name();
    }

    public String toString() {
        return this.getName();
    }

    private ResultStatus(Object... args) {
        this.message = String.format(this.message, args);
    }
}
