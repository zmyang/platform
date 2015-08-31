package com.ptsoft.pis;

import java.util.HashMap;
import java.util.Map;

public class PisConstants
{
	/**
	 * 系统类型
	 * */
	public enum SystemType
	{
		Rsc(1, "总部"),
		
		Store(0, "门店");
		
		private int key;
		private String text;
		private static String optionStr = "";
		private static final Map<Integer, SystemType> intToEnum = new HashMap<Integer, SystemType>();
		private static final Map<String, SystemType> stringToEnum = new HashMap<String, SystemType>();
		static 
	    {
	        for(SystemType type : values()) 
	        {
	        	intToEnum.put(type.key, type);
	            stringToEnum.put(type.toString(), type);
	            optionStr += "<option value=\"" + type.getKey() + "\">" + type.getText() + "</option>";
	        }
	    }
		SystemType(int key, String text) 
		{
			this.key = key;
	        this.text = text;
	    }
		public int getKey()
		{
			return this.key;
		}
	    public String getText() 
	    {
	        return this.text;
	    }
	    public static SystemType FromString(String symbol) 
	    {
	        return stringToEnum.get(symbol);
	    }
	    public static SystemType FromKey(Integer key)
	    {
	    	return intToEnum.get(key);
	    }
	    /**下拉列表数据源*/
	    public static String ToOptionString()
	    {
	    	return optionStr;
	    }
	    @Override
	    public String toString() 
	    {
	        return text;
	    }
	}
	
	/**系统中SysDataType表中使用的key*/
	public enum DataType
	{
		Company(				1, 		"公司"),
		Industry(				2, 		"行业"),
		StationType(				3, 		"监测点类别"),
		BankAccount(			4, 		"银行账号"),
		City(					5, 		"城市"),
		Market(					6, 		"市场"),
		Brand(					7, 		"品牌"),
		Zone(					8, 		"商圈"),
		StoreType(				9, 		"门店类型"),
		PosType(				10, 	"POS机类型"),
		PriceSystem(			11, 	"价格体系"),
		OutBizInComing(			12, 	"营业外收入"),
		OtherInComing(			13, 	"其他业务收入"),
		PaymentType(			14, 	"支付方式"),
		BankList(				15, 	"银行列表"),
		Department(				16,		"部门类型"),
		PersonaldDetailsCate(	17,		"坐支明细类别"),
		BankAccountCate(		18,		"银行账户类别"),
		Imprest(				19,		"备用金收支内容"),
		AtndnType(				20,		"考勤机类型"),
		
		MaterialType_OPT(		31, 	"营运类别"),
		MaterialType_FIS(		32, 	"财务类别"),
		MaterialType_DCS(		33, 	"物流类别"),
		MaterialType_SRT(		34, 	"分拣类别"),
		Accounting(				35, 	"会计科目"),
		Unit(					36, 	"计量单位"),
		
		Distribution(			38, 	"配送中心"),
		LoseType(				39, 	"损耗类型"),
		
		OrderType(				100, 	"订货类型(初始化)");
		
		
		private int key;
		private String text;
		private static final Map<Integer, DataType> intToEnum = new HashMap<Integer, DataType>();
		private static final Map<String, DataType> stringToEnum = new HashMap<String, DataType>();
		static 
	    {
	        for(DataType type : values()) 
	        {
	        	intToEnum.put(type.key, type);
	            stringToEnum.put(type.toString(), type);
	        }
	    }
		DataType(int key, String text) 
		{
			this.key = key;
	        this.text = text;
	    }
		public int getKey()
		{
			return this.key;
		}
	    public String getText() 
	    {
	        return this.text;
	    }
	    public static DataType FromString(String symbol) 
	    {
	        return stringToEnum.get(symbol);
	    }
	    public static DataType FromKey(Integer key)
	    {
	    	return intToEnum.get(key);
	    }
	    @Override
	    public String toString() 
	    {
	        return text;
	    }
	};
	
	/**盘点频率*/
	public enum CheckFrequency
	{
		None (0, "不盘点"),
		Day  (1, "日盘"),
		Week (2, "周盘"),
		Month(3, "月盘");
		
		private int key;
		private String text;
		private static String optionStr = "";
		private static final Map<Integer, CheckFrequency> intToEnum = new HashMap<Integer, CheckFrequency>();
		private static final Map<String, CheckFrequency> stringToEnum = new HashMap<String, CheckFrequency>();
		static 
	    {
	        for(CheckFrequency type : values()) 
	        {
	        	intToEnum.put(type.key, type);
	            stringToEnum.put(type.toString(), type);
	            optionStr += "<option value=\"" + type.getKey() + "\">" + type.getText() + "</option>";
	        }
	    }
		CheckFrequency(int key, String text) 
		{
			this.key = key;
	        this.text = text;
	    }
		public int getKey()
		{
			return this.key;
		}
	    public String getText() 
	    {
	        return this.text;
	    }
	    public static CheckFrequency FromString(String symbol) 
	    {
	        return stringToEnum.get(symbol);
	    }
	    public static CheckFrequency FromKey(Integer key)
	    {
	    	return intToEnum.get(key);
	    }
	    /**下拉列表数据源*/
	    public static String ToOptionString()
	    {
	    	return optionStr;
	    }
	    @Override
	    public String toString() 
	    {
	        return text;
	    }
	};
	
	/**可用/禁用*/
	public enum Available
	{
		No(0, "禁用"),
		Yes(1, "可用");
		
		private int key;
		private String text;
		private static String optionStr = "";
		private static final Map<Integer, Available> intToEnum = new HashMap<Integer, Available>();
		private static final Map<String, Available> stringToEnum = new HashMap<String, Available>();
		static 
	    {
	        for(Available type : values()) 
	        {
	        	intToEnum.put(type.key, type);
	            stringToEnum.put(type.toString(), type);
	            optionStr += "<option value=\"" + type.getKey() + "\">" + type.getText() + "</option>";
	        }
	    }
		Available(int key, String text) 
		{
			this.key = key;
	        this.text = text;
	    }
		public int getKey()
		{
			return this.key;
		}
	    public String getText() 
	    {
	        return this.text;
	    }
	    public static Available FromString(String symbol) 
	    {
	        return stringToEnum.get(symbol);
	    }
	    public static Available FromKey(Integer key)
	    {
	    	return intToEnum.get(key);
	    }
	    /**下拉列表数据源*/
	    public static String ToOptionString()
	    {
	    	return optionStr;
	    }
	    @Override
	    public String toString() 
	    {
	        return text;
	    }
	};
	
	/**是/否*/
	public enum Status
	{
		No(0, "否"),
		Yes(1, "是");
		
		private int key;
		private String text;
		private static String optionStr = "";
		private static final Map<Integer, Status> intToEnum = new HashMap<Integer, Status>();
		private static final Map<String, Status> stringToEnum = new HashMap<String, Status>();
		static 
	    {
	        for(Status type : values()) 
	        {
	        	intToEnum.put(type.key, type);
	            stringToEnum.put(type.toString(), type);
	            optionStr += "<option value=\"" + type.getKey() + "\">" + type.getText() + "</option>";
	        }
	    }
		Status(int key, String text) 
		{
			this.key = key;
	        this.text = text;
	    }
		public int getKey()
		{
			return this.key;
		}
	    public String getText() 
	    {
	        return this.text;
	    }
	    public static Status FromString(String symbol) 
	    {
	        return stringToEnum.get(symbol);
	    }
	    public static Status FromKey(Integer key)
	    {
	    	return intToEnum.get(key);
	    }
	    public static String ToOptionString()
	    {
	    	return optionStr;
	    }
	    @Override
	    public String toString() 
	    {
	        return text;
	    }
	};
	
	/**浮动类型*/
	public enum FloatType
	{
		NoControl(0, "不控制"),
		UpDown(1, "可以上下浮动"),
		OnlyUp(2, "只可向上浮动"),
		OnlyDown(3, "只可向下浮动");
		
		private int key;
		private String text;
		private static String optionStr = "";
		private static final Map<Integer, FloatType> intToEnum = new HashMap<Integer, FloatType>();
		private static final Map<String, FloatType> stringToEnum = new HashMap<String, FloatType>();
		static 
		{
			for(FloatType type : values()) 
			{
				intToEnum.put(type.key, type);
				stringToEnum.put(type.toString(), type);
				optionStr += "<option value=\"" + type.getKey() + "\">" + type.getText() + "</option>";
			}
		}
		FloatType(int key, String text) 
		{
			this.key = key;
			this.text = text;
		}
		public int getKey()
		{
			return this.key;
		}
		public String getText() 
		{
			return this.text;
		}
		public static FloatType FromString(String symbol) 
		{
			return stringToEnum.get(symbol);
		}
		public static FloatType FromKey(Integer key)
		{
			return intToEnum.get(key);
		}
		public static String ToOptionString()
		{
			return optionStr;
		}
		@Override
		public String toString() 
		{
			return text;
		}
	};
	
	/**总部单据类型*/
	public enum FormType
	{
		O_General(1 , 	"集采调拨订单",  	"CG"),
		O_PURCHASE(2 , 	"集采采购订单",  	"CP"),
		O_Supplier(3 , 	"厂商直送订单",		"ZS"),
		O_Add(4 , 		"集采加货订单",		"CZ"),
		O_Minus(5 , 	"集采减货订单",		"CJ"),
		
		D_General(11,	"集采配送货单",		"DG"),
		D_Supplier(12,	"厂商直送货单",		"DS"),
		D_Manual(13,	"手工送货单",		"DM"),
		
		R_General(16,	"集采退货单",		"RG"),
		R_Supplier(17,	"厂商退货单",		"RU"),
		R_Department(18,"部门退原料",		"RD"),//部门原料退库
		
		C_General(21,	"配送收货差异单",	"CG"),
		C_Supplier(22,	"直送收货差异单",	"CU"),
		C_Adjustment(23,"配送差异调整单",	"CA");
		
		private int key;
		private String text;
		private String sign;
		private static String optionStr = "";
		private static final Map<Integer, FormType> intToEnum = new HashMap<Integer, FormType>();
		private static final Map<String, FormType> stringToEnum = new HashMap<String, FormType>();
		static 
		{	
			optionStr+="<option value=-1 >----请选择----</option>";
			for(FormType type : values()) 
			{
				intToEnum.put(type.key, type);
				stringToEnum.put(type.toString(), type);
				optionStr += "<option value=\"" + type.getKey() + "\">" + type.getText() + "</option>";
			}
		}
		FormType(int key, String text, String sign) 
		{
			this.key = key;
			this.text = text;
			this.sign = sign;
		}
		public int getKey()
		{
			return this.key;
		}
		public String getText() 
		{
			return this.text;
		}
		public String getSign()
		{
			return this.sign;
		}
		public static FormType FromString(String symbol) 
		{
			return stringToEnum.get(symbol);
		}
		public static FormType FromKey(Integer key)
		{
			return intToEnum.get(key);
		}
		public static String ToOptionString()
		{
			return optionStr;
		}
		@Override
		public String toString() 
		{
			return text;
		}
	};
	
	/**处理状态*/
	public enum ProcessStatus
	{
		Draft (0 , "未处理"),
		Finish(1 , "已处理"),
		Fault (2 , "处理失败");
		
		private int key;
		private String text;
		private static String optionStr = "";
		private static final Map<Integer, ProcessStatus> intToEnum = new HashMap<Integer, ProcessStatus>();
		private static final Map<String, ProcessStatus> stringToEnum = new HashMap<String, ProcessStatus>();
		static 
		{	
			optionStr+="<option value=-1 >----请选择----</option>";
			for(ProcessStatus type : values()) 
			{
				intToEnum.put(type.key, type);
				stringToEnum.put(type.toString(), type);
				optionStr += "<option value=\"" + type.getKey() + "\">" + type.getText() + "</option>";
			}
		}
		ProcessStatus(int key, String text) 
		{
			this.key = key;
			this.text = text;
		}
		public int getKey()
		{
			return this.key;
		}
		public String getText() 
		{
			return this.text;
		}
		public static ProcessStatus FromString(String symbol) 
		{
			return stringToEnum.get(symbol);
		}
		public static ProcessStatus FromKey(Integer key)
		{
			return intToEnum.get(key);
		}
		public static String ToOptionString()
		{
			return optionStr;
		}
		@Override
		public String toString() 
		{
			return text;
		}
	};
	
	/**系统中sysdaypartsetup表中使用的时段类型*/
	public enum DayPartType
	{
		Breakfast(1, "早餐"),
		Lunch(2, "午餐"),
		Dinner(3, "晚餐"),
		AllNight(4, "通宵更"),
		AfternoonTea(5, "下午茶"),
		Snacks(6,"宵夜");
		
		
		private int key;
		private String text;
		private static String optionStr = "";
		private static final Map<Integer, DayPartType> intToEnum = new HashMap<Integer, DayPartType>();
		private static final Map<String, DayPartType> stringToEnum = new HashMap<String, DayPartType>();
		static 
	    {
	        for(DayPartType type : values()) 
	        {
	        	intToEnum.put(type.key, type);
	            stringToEnum.put(type.toString(), type);
	            optionStr += "<option value=\"" + type.getKey() + "\">" + type.getText() + "</option>";
	        }
	    }
		DayPartType(int key, String text) 
		{
			this.key = key;
	        this.text = text;
	    }
		public int getKey()
		{
			return this.key;
		}
	    public String getText() 
	    {
	        return this.text;
	    }
	    public static DayPartType FromString(String symbol) 
	    {
	        return stringToEnum.get(symbol);
	    }
	    public static DayPartType FromKey(Integer key)
	    {
	    	return intToEnum.get(key);
	    }
	    /**下拉列表数据源*/
	    public static String ToOptionString()
	    {
	    	return optionStr;
	    }
	    @Override
	    public String toString() 
	    {
	        return text;
	    }
	};
	
	/**系统中cashpaymentdetails表中使用的支付类型*/
	public enum PayDtlsType
	{	
		Cash(1, "现金"),
		Bank(2, "银行卡"),
		Property(3, "物业卡"),
		Token(4, "抵用券"),
		Manager(5, "经理餐");
		
		private int key;
		private String text;
		private static final Map<Integer, PayDtlsType> intToEnum = new HashMap<Integer, PayDtlsType>();
		private static final Map<String, PayDtlsType> stringToEnum = new HashMap<String, PayDtlsType>();
		static 
	    {
	        for(PayDtlsType type : values()) 
	        {
	        	intToEnum.put(type.key, type);
	            stringToEnum.put(type.toString(), type);
	        }
	    }
		PayDtlsType(int key, String text) 
		{
			this.key = key;
	        this.text = text;
	    }
		public int getKey()
		{
			return this.key;
		}
	    public String getText() 
	    {
	        return this.text;
	    }
	    public static PayDtlsType FromString(String symbol) 
	    {
	        return stringToEnum.get(symbol);
	    }
	    public static PayDtlsType FromKey(Integer key)
	    {
	    	return intToEnum.get(key);
	    }
	    @Override
	    public String toString() 
	    {
	        return text;
	    }
	};
	
}
