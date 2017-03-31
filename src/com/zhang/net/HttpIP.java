package com.zhang.net;

public class HttpIP {

	public static String key="e573aac7e747502e6b362023db8c9261";
	
	/**详细菜谱+步骤*/
	public static String URL="http://apis.juhe.cn/cook/query";
	
	/**分类标签*/
	public static String ClassifyURL="http://apis.juhe.cn/cook/category";

	/**按标签ID检索菜谱*/
	public static String ClassifyListViewURL="http://apis.juhe.cn/cook/index";
	
	/**按菜谱ID查询详情*/
	public static String ClassifyListViewIdURL="http://apis.juhe.cn/cook/queryid";
	
	//http://apis.juhe.cn/cook/query?key=e573aac7e747502e6b362023db8c9261&menu=肉&rn=10&pn=1
	//http://apis.juhe.cn/cook/category?key=e573aac7e747502e6b362023db8c9261
	
	//http://apis.juhe.cn/cook/index?key=e573aac7e747502e6b362023db8c9261&cid=9
	public static String rn="5";  // 每次获取条数
	
	public static boolean MenuCareState=true;  //菜谱收藏状态
	
	public static boolean NET_STATE=true;  //当前网络状态
}
