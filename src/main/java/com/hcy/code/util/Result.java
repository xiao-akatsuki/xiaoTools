package com.hcy.code.util;

import java.util.Date;

/**
 * 返回值模式
 * @author HCY
 * @since 2020-10-29
 */
public class Result {

    /**
     * 时间
     */
    private Date timestamp;

    /**
     * 状态信息
     */
    private Integer status;

    /**
     * 该类型状态码表示动作被成功接收、理解和接受
     */
    public static final Integer STATUS_200 = 200;
    public static final Integer STATUS_201 = 201;
    public static final Integer STATUS_202 = 202;
    public static final Integer STATUS_203 = 203;
    public static final Integer STATUS_204 = 204;
    public static final Integer STATUS_205 = 205;
    public static final Integer STATUS_206 = 206;

    /**
     * 该类型状态码表示为了完成指定的动作，必须接受进一步处理。
     */
    public static final Integer STATUS_300 = 300;
    public static final Integer STATUS_301 = 301;
    public static final Integer STATUS_302 = 302;
    public static final Integer STATUS_303 = 303;
    public static final Integer STATUS_304 = 304;
    public static final Integer STATUS_305 = 305;
    public static final Integer STATUS_307 = 307;

    /**
     * 该类型状态码表示请求包含错误语法或不能正确执行。
     */
    public static final Integer STATUS_400 = 400;
    public static final Integer STATUS_401 = 401;
    public static final Integer STATUS_402 = 402;
    public static final Integer STATUS_403 = 403;
    public static final Integer STATUS_404 = 404;
    public static final Integer STATUS_405 = 405;
    public static final Integer STATUS_406 = 406;
    public static final Integer STATUS_407 = 407;
    public static final Integer STATUS_408 = 408;
    public static final Integer STATUS_409 = 409;
    public static final Integer STATUS_410 = 410;
    public static final Integer STATUS_411 = 411;
    public static final Integer STATUS_412 = 412;
    public static final Integer STATUS_413 = 413;
    public static final Integer STATUS_414 = 414;
    public static final Integer STATUS_415 = 415;
    public static final Integer STATUS_416 = 416;
    public static final Integer STATUS_417 = 417;

    /**
     * 该类型状态码表示服务器或网关错误。
     */
    public static final Integer STATUS_500 = 500;
    public static final Integer STATUS_501 = 501;
    public static final Integer STATUS_502 = 502;
    public static final Integer STATUS_503 = 503;
    public static final Integer STATUS_504 = 504;
    public static final Integer STATUS_505 = 505;

    /**
     * 信息
     */
    private String info;

    /**
     * 该类型状态码表示动作被成功接收、理解和接受
     */
    public static final String INFO_200 = "请求被成功地完成，所请求的资源发送到客户端。";
    public static final String INFO_201 = "提示知道新文件的URL。";
    public static final String INFO_202 = "接受并处理，但处理未完成。";
    public static final String INFO_203 = "返回信息不确定或不完整。";
    public static final String INFO_204 = "收到请求，但返回信息为空。";
    public static final String INFO_205 = "服务器完成了请求，用户必须复位当前已经浏览过的文件。";
    public static final String INFO_206 = "服务器已经完成了部分用户的GET请求。";

    /**
     * 该类型状态码表示为了完成指定的动作，必须接受进一步处理。
     */
    public static final String INFO_300 = "请求的资源可在多处获得。";
    public static final String INFO_301 = "本网页被永久性转移到另一个URL。";
    public static final String INFO_302 = "请求的网页被重定向到新的地址。";
    public static final String INFO_303 = "建议用户访问其他URL或访问方式。";
    public static final String INFO_304 = "自从上次请求后，请求的网页未修改过。";
    public static final String INFO_305 = "请求的资源必须从服务器指定的地址获得。";
    public static final String INFO_307 = "声明请求的资源临时性删除。";

    /**
     * 该类型状态码表示请求包含错误语法或不能正确执行。
     */
    public static final String INFO_400 = "客户端请求有语法错误。";
    public static final String INFO_401 = "请求未经授权。";
    public static final String INFO_402 = "保留有效ChargeTo头响应。";
    public static final String INFO_403 = "禁止访问，服务器收到请求，但拒绝提供服务。";
    public static final String INFO_404 = "可连接服务器，但服务器无法取得所请求的网页，请求资源不存在。";
    public static final String INFO_405 = "用户在Request-Line字段定义的方法不被允许。";
    public static final String INFO_406 = "根据用户发送的Accept，请求资源不可访问。";
    public static final String INFO_407 = "用户必须首先在代理服务器上取得授权。";
    public static final String INFO_408 = "客户端没有在用户指定的时间内完成请求。";
    public static final String INFO_409 = "对当前资源状态，请求不能完成。";
    public static final String INFO_410 = "服务器上不再有此资源。";
    public static final String INFO_411 = "服务器拒绝用户定义的Content-Length属性请求。";
    public static final String INFO_412 = "一个或多个请求头字段在当前请求中错误。";
    public static final String INFO_413 = "请求的资源大于服务器允许的大小。";
    public static final String INFO_414 = "请求的资源URL长于服务器允许的长度。";
    public static final String INFO_415 = "请求资源不支持请求项目格式。";
    public static final String INFO_416 = "请求中包含Range请求头字段，在当前请求资源范围内没有range指示值。";
    public static final String INFO_417 = "服务器不满足请求Expect头字段指定的期望值。";

    /**
     * 该类型状态码表示服务器或网关错误。
     */
    public static final String INFO_500 = "服务器错误。";
    public static final String INFO_501 = "服务器不支持请求的功能。";
    public static final String INFO_502 = "网关错误。";
    public static final String INFO_503 = "无法获得服务。";
    public static final String INFO_504 = "网关超时。";
    public static final String INFO_505 = "不支持的http版本。";

    /**
     * 详情
     */
    private String details;

    /**
     * 该类型状态码表示动作被成功接收、理解和接受
     */
    public static final String DETAILS_200 = "The request is successfully completed and the requested resource is sent to the client.";
    public static final String DETAILS_201 = "Prompt for the URL of the new file.";
    public static final String DETAILS_202 = "Accepted and processed, but processing is not complete.";
    public static final String DETAILS_203 = "The return information is uncertain or incomplete.";
    public static final String DETAILS_204 = "The request was received, but the return information is empty.";
    public static final String DETAILS_205 = "After the server completes the request, the user must reset the file that has been browsed.";
    public static final String DETAILS_206 = "The server has completed the get request of some users.";

    /**
     * 该类型状态码表示为了完成指定的动作，必须接受进一步处理。
     */
    public static final String DETAILS_300 = "The requested resource is available in multiple locations.";
    public static final String DETAILS_301 = "This page is permanently transferred to another URL.";
    public static final String DETAILS_302 = "The requested page is redirected to a new address.";
    public static final String DETAILS_303 = "Users are advised to visit other URLs or access methods.";
    public static final String DETAILS_304 = "The requested page has not been modified since the last request.";
    public static final String DETAILS_305 = "The requested resource must be obtained from the address specified by the server.";
    public static final String DETAILS_307 = "Declare the temporary deletion of the requested resource.";

    /**
     * 该类型状态码表示请求包含错误语法或不能正确执行。
     */
    public static final String DETAILS_400 = "The client request has a syntax error.";
    public static final String DETAILS_401 = "The request is not authorized.";
    public static final String DETAILS_402 = "Keep valid ChargeTo header response.";
    public static final String DETAILS_403 = "Access is forbidden. The server receives the request but refuses to provide the service.";
    public static final String DETAILS_404 = "Can connect to the server, but the server can not get the requested page, the request resource does not exist.";
    public static final String DETAILS_405 = "The method defined by the user in the request line field is not allowed.";
    public static final String DETAILS_406 = "According to the accept sent by the user, the request resource is not accessible.";
    public static final String DETAILS_407 = "The user must first obtain authorization on the proxy server.";
    public static final String DETAILS_408 = "The client did not complete the request within the time specified by the user.";
    public static final String DETAILS_409 = "The request cannot be completed for the current resource state.";
    public static final String DETAILS_410 = "This resource no longer exists on the server.";
    public static final String DETAILS_411 = "The server rejected the user-defined request for the content length property.";
    public static final String DETAILS_412 = "One or more request header fields are incorrect in the current request.";
    public static final String DETAILS_413 = "The requested resource is larger than the size allowed by the server.";
    public static final String DETAILS_414 = "The requested resource URL is longer than the length allowed by the server.";
    public static final String DETAILS_415 = "The request resource does not support the request item format.";
    public static final String DETAILS_416 = "The request contains the range request header field, and there is no range indication value in the current request resource range.";
    public static final String DETAILS_417 = "The server does not meet the expected value specified in the request expect header field.";

    /**
     * 该类型状态码表示服务器或网关错误。
     */
    public static final String DETAILS_500 = "Server error.";
    public static final String DETAILS_501 = "The server does not support the requested feature.";
    public static final String DETAILS_502 = "Gateway error.";
    public static final String DETAILS_503 = "Unable to get service.";
    public static final String DETAILS_504 = "Gateway timed out.";
    public static final String DETAILS_505 = "Unsupported HTTP version.";

    /**
     * 返回信息
     */
    private Object message;
    /**
     * 调用URL
     */
    private String path;

    /**
     * 无参
     */
    public Result() {
    }

    /**
     * 有参
     */
    public Result(Integer status, String info, String details, Object message, String path) {
        this.timestamp = new Date();
        this.status = status;
        this.info = info;
        this.details = details;
        this.message = message;
        this.path = path;
    }

    /**
     * 请求被成功地完成，所请求的资源发送到客户端。
     * The request is successfully completed and the requested resource is sent to the client.
     * @author HCY
     * @since 2021/4/28 10:27 下午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
    */
    public Result result200(Object message, String path){
        return new Result(STATUS_200, INFO_200, DETAILS_200, message, path);
    }

    /**
     * 提示知道新文件的URL。
     * Prompt for the URL of the new file.
     * @author HCY
     * @since 2021/4/28 10:27 下午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result201(Object message, String path){
        return new Result(STATUS_201, INFO_201, DETAILS_201, message, path);
    }

    /**
     * 接受并处理，但处理未完成。
     * Accepted and processed, but processing is not complete.
     * @author HCY
     * @since 2021/4/28 10:27 下午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result202(Object message, String path){
        return new Result(STATUS_202, INFO_202, DETAILS_202, message, path);
    }

    /**
     * 返回信息不确定或不完整。
     * The return information is uncertain or incomplete.
     * @author HCY
     * @since 2021/4/28 10:27 下午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result203(Object message, String path){
        return new Result(STATUS_203, INFO_203, DETAILS_203, message, path);
    }

    /**
     * 收到请求，但返回信息为空。
     * The request was received, but the return information is empty.
     * @author HCY
     * @since 2021/4/28 10:27 下午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result204(Object message, String path){
        return new Result(STATUS_204, INFO_204, DETAILS_204, message, path);
    }

    /**
     * 服务器完成了请求，用户必须复位当前已经浏览过的文件。
     * After the server completes the request, the user must reset the file that has been browsed.
     * @author HCY
     * @since 2021/4/28 10:27 下午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result205(Object message, String path){
        return new Result(STATUS_205, INFO_205, DETAILS_205, message, path);
    }

    /**
     * 服务器已经完成了部分用户的GET请求。
     * The server has completed the get request of some users.
     * @author HCY
     * @since 2021/4/28 10:27 下午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result206(Object message, String path){
        return new Result(STATUS_206, INFO_206, DETAILS_206, message, path);
    }

    /**
     * 请求的资源可在多处获得。
     * The requested resource is available in multiple locations.
     * @author HCY
     * @since 2021/4/29 7:16 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
    */
    public Result result300(Object message,String path){
        return new Result(STATUS_300,INFO_300,DETAILS_300,message,path);
    }

    /**
     * 本网页被永久性转移到另一个URL。
     * This page is permanently transferred to another URL.
     * @author HCY
     * @since 2021/4/29 7:23 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
    */
    public Result result301(Object message,String path){
        return new Result(STATUS_301,INFO_301,DETAILS_301,message,path);
    }

    /**
     * 请求的网页被重定向到新的地址。
     * The requested page is redirected to a new address.
     * @author HCY
     * @since 2021/4/29 7:23 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result302(Object message,String path){
        return new Result(STATUS_302,INFO_302,DETAILS_302,message,path);
    }

    /**
     * 建议用户访问其他URL或访问方式。
     * Users are advised to visit other URLs or access methods.
     * @author HCY
     * @since 2021/4/29 7:23 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result303(Object message,String path){
        return new Result(STATUS_303,INFO_303,DETAILS_303,message,path);
    }

    /**
     * 自从上次请求后，请求的网页未修改过。
     * The requested page has not been modified since the last request.
     * @author HCY
     * @since 2021/4/29 7:23 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result304(Object message,String path){
        return new Result(STATUS_304,INFO_304,DETAILS_304,message,path);
    }

    /**
     * 请求的资源必须从服务器指定的地址获得。
     * The requested resource must be obtained from the address specified by the server.
     * @author HCY
     * @since 2021/4/29 7:23 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result305(Object message,String path){
        return new Result(STATUS_305,INFO_305,DETAILS_305,message,path);
    }

    /**
     * 声明请求的资源临时性删除。
     * Declare the temporary deletion of the requested resource.
     * @author HCY
     * @since 2021/4/29 7:23 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result307(Object message,String path){
        return new Result(STATUS_307,INFO_307,DETAILS_307,message,path);
    }

    /**
     * 客户端请求有语法错误。
     * The client request has a syntax error.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result400(Object message,String path){
        return new Result(STATUS_400,INFO_400,DETAILS_400,message,path);
    }

    /**
     * 请求未经授权。
     * The request is not authorized.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result401(Object message,String path){
        return new Result(STATUS_401,INFO_401,DETAILS_401,message,path);
    }

    /**
     * 保留有效ChargeTo头响应。
     * Keep valid ChargeTo header response.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result402(Object message,String path){
        return new Result(STATUS_402,INFO_402,DETAILS_402,message,path);
    }

    /**
     * 禁止访问，服务器收到请求，但拒绝提供服务。
     * Access is forbidden. The server receives the request but refuses to provide the service.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result403(Object message,String path){
        return new Result(STATUS_403,INFO_403,DETAILS_403,message,path);
    }

    /**
     * 可连接服务器，但服务器无法取得所请求的网页，请求资源不存在。
     * Can connect to the server, but the server can not get the requested page, the request resource does not exist.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result404(Object message,String path){
        return new Result(STATUS_404,INFO_404,DETAILS_404,message,path);
    }

    /**
     * 用户在Request-Line字段定义的方法不被允许。
     * The method defined by the user in the request line field is not allowed.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result405(Object message,String path){
        return new Result(STATUS_405,INFO_405,DETAILS_405,message,path);
    }

    /**
     * 根据用户发送的Accept，请求资源不可访问。
     * According to the accept sent by the user, the request resource is not accessible.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result406(Object message,String path){
        return new Result(STATUS_406,INFO_406,DETAILS_406,message,path);
    }

    /**
     * 用户必须首先在代理服务器上取得授权。
     * The user must first obtain authorization on the proxy server.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result407(Object message,String path){
        return new Result(STATUS_407,INFO_407,DETAILS_407,message,path);
    }

    /**
     * 客户端没有在用户指定的时间内完成请求。
     * The client did not complete the request within the time specified by the user.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result408(Object message,String path){
        return new Result(STATUS_408,INFO_408,DETAILS_408,message,path);
    }

    /**
     * 对当前资源状态，请求不能完成。
     * The request cannot be completed for the current resource state.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result409(Object message,String path){
        return new Result(STATUS_409,INFO_409,DETAILS_409,message,path);
    }

    /**
     * 服务器上不再有此资源。
     * This resource no longer exists on the server.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result410(Object message,String path){
        return new Result(STATUS_410,INFO_410,DETAILS_410,message,path);
    }

    /**
     * 服务器拒绝用户定义的Content-Length属性请求。
     * The server rejected the user-defined request for the content length property.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result411(Object message,String path){
        return new Result(STATUS_411,INFO_411,DETAILS_411,message,path);
    }

    /**
     * 一个或多个请求头字段在当前请求中错误。
     * One or more request header fields are incorrect in the current request.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result412(Object message,String path){
        return new Result(STATUS_412,INFO_412,DETAILS_412,message,path);
    }

    /**
     * 请求的资源大于服务器允许的大小。
     * The requested resource is larger than the size allowed by the server.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result413(Object message,String path){
        return new Result(STATUS_413,INFO_413,DETAILS_413,message,path);
    }

    /**
     * 请求的资源URL长于服务器允许的长度。
     * The requested resource URL is longer than the length allowed by the server.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result414(Object message,String path){
        return new Result(STATUS_414,INFO_414,DETAILS_414,message,path);
    }

    /**
     * 请求资源不支持请求项目格式。
     * The request resource does not support the request item format.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result415(Object message,String path){
        return new Result(STATUS_415,INFO_415,DETAILS_415,message,path);
    }

    /**
     * 请求中包含Range请求头字段，在当前请求资源范围内没有range指示值。
     * The request contains the range request header field, and there is no range indication value in the current request resource range.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result416(Object message,String path){
        return new Result(STATUS_416,INFO_416,DETAILS_416,message,path);
    }

    /**
     * 服务器不满足请求Expect头字段指定的期望值。
     * The server does not meet the expected value specified in the request expect header field.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result417(Object message,String path){
        return new Result(STATUS_417,INFO_417,DETAILS_417,message,path);
    }

    /**
     * 服务器错误。
     * Server error.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result500(Object message,String path){
        return new Result(STATUS_500,INFO_500,DETAILS_500,message,path);
    }

    /**
     * 服务器不支持请求的功能。
     * The server does not support the requested feature.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result501(Object message,String path){
        return new Result(STATUS_501,INFO_501,DETAILS_501,message,path);
    }

    /**
     * 网关错误。
     * Gateway error.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result502(Object message,String path){
        return new Result(STATUS_502,INFO_502,DETAILS_502,message,path);
    }

    /**
     * 无法获得服务。
     * Unable to get service.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result503(Object message,String path){
        return new Result(STATUS_503,INFO_503,DETAILS_503,message,path);
    }

    /**
     * 网关超时。
     * Gateway timed out.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result504(Object message,String path){
        return new Result(STATUS_504,INFO_504,DETAILS_504,message,path);
    }

    /**
     * 不支持的http版本。
     * Unsupported HTTP version.
     * @author HCY
     * @since 2021/4/29 7:25 上午
     * @param message: 信息
     * @param path: URL路径
     * @return Result
     */
    public Result result505(Object message,String path){
        return new Result(STATUS_505,INFO_505,DETAILS_505,message,path);
    }

    /**
     * toString 语句
     * @author HCY
     * @since 2021/4/28 10:32 下午
     * @return java.lang.String
    */
    @Override
    public String toString() {
        return "Result{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", info='" + info + '\'' +
                ", details='" + details + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
