<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>报表打印</title>
    
    <script type="text/javascript" src="<c:url value="/media/fm/jquery-1.10.1.min.js"/>"></script>
    <script type="text/javascript">
		$().ready(function() 
				{
					
				});
		</script>
  </head>
  
  <body>
			${requestScope.print }
  </body>
</html>
