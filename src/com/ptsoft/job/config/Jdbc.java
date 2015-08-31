package com.ptsoft.job.config;

import java.sql.*;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

/**
 * 数据库连接封装
 * */
public class Jdbc
{
	protected static Logger logger = Logger.getLogger(Jdbc.class.getName());

	private static final String X_Url = "url";
	private static final String X_User = "username";
	private static final String X_Pass = "password";

	private String o_Url;
	private String o_User;
	private String o_Pass;

	public Jdbc(Element element)
	{
		this.initDriver();

		this.parse(element);
	}

	/** 分析配置文件 */
	private void parse(Element node)
	{
		this.o_Url = node.getAttribute(X_Url);
		this.o_User = node.getAttribute(X_User);
		this.o_Pass = node.getAttribute(X_Pass);
	}

	/** 初始化驱动 */
	private void initDriver()
	{
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e)
		{
			System.err.println("initDriver:" + e.getMessage());
			return;
		}
	}

	/** 取得新连接 */
	private Connection getConn()
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(this.o_Url, this.o_User, this.o_Pass);
		} catch (SQLException ex)
		{
			System.err.println("URL:" + this.o_Url + " USR:" + this.o_User + " PAS:" + this.o_Pass);
			System.err.println("getConn:" + ex.getMessage());
		}
		return conn;
	}

	/**
	 * 执行存储过程
	 * @param procedure 组织好的存储过程
	 */
	public void executeProcedure(String procedureName, List<String> params) throws Exception
	{
		String p = "";
		for (int i=0; i<params.size(); i++)
		{
			p += "?,";
		}
		if (p.length() > 0)
		{
			p = p.substring(0, p.length()-1);
		}
		
		String procedure = "{call " + procedureName + "(" + p + ")}";
		
		Connection conn = null;
		CallableStatement stmt = null;
		try
		{
			conn = this.getConn();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareCall(procedure);
			for (int i=0; i<params.size(); i++)
			{
				stmt.setString(i + 1, params.get(i));
			}
			stmt.execute();
			
			conn.commit();
		} 
		catch (Exception e)
		{
			try
			{
				conn.rollback();
			} 
			catch (Exception ex)
			{
			}

			logger.error("executeProcedure: " + procedure + "  " + e.getMessage());
			throw e;
		} 
		finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
					stmt = null;
				}
			} catch (Exception e)
			{

			}
			
			try
			{
				if (conn != null)
				{
					conn.close();
					conn = null;
				}
			} catch (Exception e)
			{

			}
		}
	}
	
	/** 执行SQL语句 */
	public void executeSql(List<String> sqls) throws Exception
	{
		final int batchSize = 500;
		int count = 0;

		Connection conn = null;
		Statement stmt = null;

		try
		{
			conn = this.getConn();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (String sql : sqls)
			{
				stmt.addBatch(sql);
				if (++count % batchSize == 0)
				{
					stmt.executeBatch();
				}
			}
			stmt.executeBatch();
			stmt.close();
			conn.commit();
		} catch (Exception e)
		{
			try
			{
				conn.rollback();
			} catch (Exception ex)
			{
			}

			logger.error("loadData:" + e.getMessage());
			throw e;
		} finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
					stmt = null;
				}
			} catch (Exception e)
			{

			}

			try
			{
				if (conn != null)
				{
					conn.close();
					conn = null;
				}
			} catch (Exception e)
			{

			}
		}
	}

	/** 执行SQL语句 */
	public void executeSql(String sql)
	{
		Connection conn = null;
		PreparedStatement stmt = null;

		logger.debug(sql);
		try
		{
			conn = this.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.execute();
		} catch (Exception e)
		{
			logger.error("loadData:" + e.getMessage());
		} finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
			} catch (Exception e)
			{

			}

			try
			{
				if (conn != null)
				{
					conn.close();
				}
			} catch (Exception e)
			{

			}
		}
	}

	/** 加载数据 */
	public ResultSet loadData(String sql)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;

		logger.debug(sql);
		try
		{
			conn = this.getConn();
			stmt = conn.prepareStatement(sql);
			rst = stmt.executeQuery();
		} catch (Exception e)
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
			} catch (Exception e1)
			{

			}
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			} catch (Exception e1)
			{

			}
			logger.error("loadData:" + e.getMessage());
		} finally
		{

		}
		return rst;
	}

	/** 关闭结果集 */
	public void closeResultSet(ResultSet rst)
	{
		if (rst == null)
			return;

		try
		{
			rst.close();
		} catch (Exception e)
		{

		}

		try
		{
			if (rst.getStatement() != null)
			{
				rst.getStatement().close();
			}
		} catch (Exception e)
		{

		}

		try
		{
			if (rst.getStatement().getConnection() != null)
			{
				rst.getStatement().getConnection().close();
			}
		} catch (Exception e)
		{

		}
	}
	
	
}
