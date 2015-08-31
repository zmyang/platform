package com.ptsoft.common.annotation;

import net.sf.json.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.ptsoft.pis.PisUtils;
import com.ptsoft.pis.account.model.vo.SysUser;
import com.ptsoft.pis.system.dao.SysLogDao;
import com.ptsoft.pis.system.model.vo.SysLog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 切点类，用于记录指定Service方法的日志
 * 
 * @author fuyiyong
 */
@Aspect
@Component
public class SystemLogAspect
{
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

	@Resource
	private SysLogDao logDao;

	/**
	 * 切入点，所有Service方法的调用都处理
	 */
	@Pointcut("@annotation(com.ptsoft.common.annotation.ServiceLog)")
	public void serviceCall()
	{
	}

	/**
	 * Service方法完成时，记录正常完成日志
	 */
	@After(value = "serviceCall()")
	public void logInfo(JoinPoint joinPoint)
	{
		this.doInsertLog(joinPoint, "");
	}

	/**
	 * Service方法异常时，记录异常完成日志
	 */
	@AfterThrowing(value = "serviceCall()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e)
	{
		this.doInsertLog(joinPoint, e.getMessage());
	}

	//记录日志到数据库
	private void doInsertLog(JoinPoint joinPoint, String result)
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		SysUser user = PisUtils.getCurrentUser(request);
		//获取用户请求方法的参数并序列化为JSON格式字符串  
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0)
		{
			Object obj = null;
			String json = "";
			for (int i = 0; i < joinPoint.getArgs().length; i++)
			{
				obj = joinPoint.getArgs()[i];
				try
				{
					json = JSONObject.fromObject(obj).toString();
				}
				catch (Exception e)
				{
					json = obj.toString();
				}
				params += json + ";";
			}
		}

		try
		{
			SysLog log = new SysLog();
			log.setOprtr(user == null ? "-" : user.getLgnNm());
			log.setIp(request.getRemoteAddr());
			log.setBrowser(request.getHeader("User-Agent"));
			log.setActnId(joinPoint.getTarget().getClass().getName() + ":" + joinPoint.getSignature().getName() + "()");
			log.setFnctnId(request.getHeader("Referer").split(request.getContextPath())[1]);
			log.setDataId(getServiceMthodDescription(joinPoint));
			log.setCntnt(params.substring(0, (params.length() >= 1999 ? 1999 : params.length())));
			log.setRst(result.substring(0, (result.length() >= 1999 ? 1999 : result.length())));
			log.setSts(1);
			//保存数据库  
			this.logDao.insert(log);
		}
		catch (Exception ex)
		{
			//记录本地异常日志  
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception
	{
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods)
		{
			if (method.getName().equals(methodName))
			{
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length)
				{
					ServiceLog log = method.getAnnotation(ServiceLog.class);
					if (log == null)
					{
						description = "";
					}
					else
					{
						description = log.description();
					}
					break;
				}
			}
		}
		return description;
	}
}