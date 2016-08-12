package com.rick.tools.kuidi100.bean;

/**
 * @category 快递100实体类
 * @source [https://www.kuaidi100.com/openapi/api_post.shtml]
 * @description [输入参数和返回结果]
 * @author rick
 * @date 2016-07-01
 * @version v1.0
 *
 */
public class Kuidi100 {
	
	private String id;//--身份授权key
	private String com;//--要查询的快递公司代码
	private String nu;//--要查询的快递单号
	/**
	 * @design 返回类型：
	 * @p 0：返回json字符串，
	 * @p 1：返回xml对象，
	 * @p 2：返回html对象，
	 * @p 3：返回text文本。
	 */
	private String show;
	/**
	 * 返回信息数量： 1:返回多行完整的信息， 0:只返回一行信息。 不填默认返回多行。
	 */
	private String muti;
	/**
	 * 排序： desc：按时间由新到旧排列， asc：按时间由旧到新排列。 不填默认返回倒序（大小写不敏感）
	 */
	private String order;
	
	
	private String time;//--每条跟踪信息的时间
	private String context;//--每条跟综信息的描述
	/**
	 * @design 快递单当前的状态 ：
	 * @p 0：在途，即货物处于运输过程中；
	 * @p 1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息；
	 * @p 2：疑难，货物寄送过程出了问题；
	 * @p 3：签收，收件人已签收；
	 * @p 4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收；
	 * @p 5：派件，即快递正在进行同城派件；
	 * @p 6：退回，货物正处于退回发件人的途中；
	 */
	private String state;
	/**
	 * @design 查询结果状态：
	 * @p 0：物流单暂无结果，
	 * @p 1：查询成功，
	 * @p 2：接口出现异常，
	 */
	private String status;
	
	/**
	 * @design 无意义，请忽略
	 */
	private String message;
	
	/**
	 * [手机查询API]要查询的快递公司的代码，支持中文和模糊输入
	 */
	private String type;
	/**
	 * [手机查询API]要查询的快递单号
	 */
	private String postid;
	/**
	 * [手机查询API]在查询结果页面点击"返回"时跳转的地址
	 */
	private String callbackurl;
	
	public Kuidi100() {
		super();
	}
	
	/**
	 * @category 手机查询API-3
	 * @source 【https://www.kuaidi100.com/openapi/mobileapi.shtml】
	 * @url [http://m.kuaidi100.com/index_all.html?type=[快递公司编码]&postid=[快递单号]&callbackurl=[点击"返回"跳转的地址]]
	 * @param type
	 * @param postid
	 * @param callbackurl
	 */
	public Kuidi100(String type, String postid, String callbackurl) {
		super();
		this.type = type;
		this.postid = postid;
		this.callbackurl = callbackurl;
	}

	/**
	 * @category 见【4.输入参数】
	 * @param id
	 * @param com
	 * @param nu
	 * @param show
	 * @param muti
	 * @param order
	 */
	public Kuidi100(String id, String com, String nu, String show, String muti, String order) {
		super();
		this.id = id;
		this.com = com;
		this.nu = nu;
		this.show = show;
		this.muti = muti;
		this.order = order;
	}

	/**
	 * @category 见 【5.返回结果】
	 * @param com
	 * @param nu
	 * @param time
	 * @param context
	 * @param state
	 * @param status
	 * @param message [无意义，请忽略]
	 */
	public Kuidi100(String com, String nu, String time, String context, String state, String status,String message) {
		super();
		this.com = com;
		this.nu = nu;
		this.time = time;
		this.context = context;
		this.state = state;
		this.status = status;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNu() {
		return nu;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getMuti() {
		return muti;
	}

	public void setMuti(String muti) {
		this.muti = muti;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

}
