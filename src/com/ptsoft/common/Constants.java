package com.ptsoft.common;

public class Constants
{
	/** function表根节点 */
	public static final String MENU_ROOT = "SYS";

	/** 状态为1表示可用 */
	public static final String IMSDCSCHEDULELIST_STS = "1";

	/** 登录页面地址 */
	public static final String LOGIN_URL = "/login.do";
	/** 退出页面地址 */
	public static final String LOGOUT_URL = "/doLogout.do";

	//
	//以下几个Session信息，要在IndexController的logout方法中清除
	//
	/** 当前用户，Session Attribute ‘C_USR’ */
	public static final String SA_USER = "C_USR";
	/** 当前门店，Session Attribute ‘C_STE’ */
	public static final String SA_STORE = "C_STE";
	/** 营业日期，Session Attribute ‘C_BDT’ */
	public static final String SA_BIZDT = "C_BDT";
	/** 配送中心，Session Attribute ‘C_DCCD’ */
	public static final String SA_DCCD = "C_DCCD";

	/** Session Attribute ‘editData’ */
	public static final String SA_EDIT_DATA = "editData";
	/** Session Attribute ‘storeList’ */
	public static final String SA_STORE_LSIT = "storeList";

	/** Session Attribute ‘SA_SELECTED_DT’ */
	public static final String SA_SELECTED_DT = "Selected_DT";
	/** Session Attribute ‘FUNCTION_ID’ */
	public static final String SA_FUNCTION_ID = "FUNCTION_ID";
	/** Session Attribute ‘FUNCTION_NM’ */
	public static final String SA_FUNCTION_NM = "FUNCTION_NM";
	/** Session Attribute ‘LIST_PARAMTERS’ */
	public static final String SA_LIST_PARAMTERS = "LIST_PARAMTERS";
	/** Session Attribute ‘LIST_FMTP’ */
	public static final String SA_LIST_FMTP = "LIST_FMTP";
	/** Session Attribute ‘LIST_STCD’ */
	public static final String SA_LIST_STCD = "LIST_STCD";
	/** Session Attribute ‘LIST_BIZDT’ */
	public static final String SA_LIST_BIZDT = "LIST_BIZDT";

	/** 上传文件位置 */
	public static final String UPLOAD_PATH = "/upload";

	/** 每页记录数 */
	public static final int PAGE_SIZE = 30;

	/** Session Attribute ‘operTag’ */
	public static final String OPERTAG = "operTag";
	/** 编辑页面标志 */
	public static final int OPERTAG_EDIT = 1;

	/** 新增页面标志 */
	public static final int OPERTAG_ADD = 0;

	/**
	 * 服务状态
	 * */
	public enum ServiceState
	{
		/** 未创建 */
		Null
		{
			public int getValue()
			{
				return 0;
			};

			public String getName()
			{
				return "未创建";
			}
		},
		/** 已创建，未运行 */
		WAIT
		{
			public int getValue()
			{
				return 1;
			};

			public String getName()
			{
				return "未运行";
			}
		},
		/** 等待中 */
		SLEEP
		{
			public int getValue()
			{
				return 2;
			};

			public String getName()
			{
				return "等待中";
			}
		},
		/** 运行中 */
		RUNNING
		{
			public int getValue()
			{
				return 3;
			};

			public String getName()
			{
				return "运行中";
			}
		},
		/** 已停止 */
		STOPED
		{
			public int getValue()
			{
				return 4;
			};

			public String getName()
			{
				return "已停止";
			}
		};

		public abstract int getValue();

		public abstract String getName();
	}
}
