<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" required="true" type="cn.org.rapid_framework.page.Page" description="Page.java" %>
<%@ attribute name="pageSizeSelectList" type="java.lang.Number[]" required="false"  %>
<%@ attribute name="isShowPageSizeList" type="java.lang.Boolean" required="false"  %>
<%@ attribute name="action" type="java.lang.String" required="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// set default values
	Integer[] defaultPageSizes = new Integer[]{10,50,100};
	if(jspContext.getAttribute("pageSizeSelectList") == null) {
		jspContext.setAttribute("pageSizeSelectList",defaultPageSizes); 
	}
	
	if(jspContext.getAttribute("isShowPageSizeList") == null) {
		jspContext.setAttribute("isShowPageSizeList",true); 
	} 
%>
<script >
function jumpPage(pageNum){
	var form =  document.getElementById('myForm');
	form.ec_p.value=pageNum;
	form.setAttribute("action","<%=jspContext.getAttribute("action")%>");
	form.setAttribute("method","post");
	form.submit();
}
function changePageSize(pageSize){
	var form =  document.getElementById('myForm');
	form.ec_crd.value=pageSize;
	form.ec_p.value=1;
	form.setAttribute("action","<%=jspContext.getAttribute("action")%>");
	form.setAttribute("method","post");
	form.submit();
}
</script>
<form action="" id="myForm">
<INPUT TYPE="hidden" NAME="ec_p" value="">
<INPUT TYPE="hidden" NAME="ec_crd" value="10">
</form>
<table width="100%"  border="0" cellspacing="0" class="gridToolbar">
  <tr>
	<td>
		<div class="box">
		
			<div  class="leftControls" >
				<jsp:doBody/>
			</div>
			
			<div class="paginationControls">
				<span class="buttonLabel">${page.thisPageFirstElementNumber} - ${page.thisPageLastElementNumber} of ${page.totalCount}</span>
				
				<c:choose>
				<c:when test="${page.firstPage}"><img src="<c:url value='/widgets/simpletable/images/firstPageDisabled.gif'/>" style="border:0" ></c:when>
				<c:otherwise><a href="javascript:simpleTable.togglePage(1);"><img src="<c:url value='/widgets/simpletable/images/firstPage.gif'/>" style="border:0" ></a></c:otherwise>
				</c:choose>
				
				<c:choose>
				<c:when test="${page.hasPreviousPage}"><a href="javascript:jumpPage(${page.previousPageNumber});"><img src="<c:url value='/widgets/simpletable/images/prevPage.gif'/>" style="border:0" ></a></c:when>
				<c:otherwise><img src="<c:url value='/widgets/simpletable/images/prevPageDisabled.gif'/>" style="border:0" ></c:otherwise>
				</c:choose>
				
				<c:forEach var="item" items="${page.linkPageNumbers}">
				<c:choose>
				<c:when test="${item == page.thisPageNumber}">[${item}]</c:when>
				<c:otherwise><a href="javascript:jumpPage(${item});">[${item}]</a></c:otherwise>
				</c:choose>
				</c:forEach>
				
				<c:choose>
				<c:when test="${page.hasNextPage}"><a href="javascript:jumpPage(${page.nextPageNumber});"><img src="<c:url value='/widgets/simpletable/images/nextPage.gif'/>" style="border:0" ></a></c:when>
				<c:otherwise><img src="<c:url value='/widgets/simpletable/images/nextPageDisabled.gif'/>" style="border:0" ></c:otherwise>
				</c:choose>
				
				<c:choose>
				<c:when test="${page.lastPage}"><img src="<c:url value='/widgets/simpletable/images/lastPageDisabled.gif'/>" style="border:0"></c:when>
				<c:otherwise><a href="javascript:jumpPage(${page.lastPageNumber});"><img src="<c:url value='/widgets/simpletable/images/lastPage.gif'/>" style="border:0" ></a></c:otherwise>
				</c:choose>
				
				<c:if test="${isShowPageSizeList}">
				<select onChange="changePageSize(this.value)">
					<c:forEach var="item" items="${pageSizeSelectList}">
						<option value="${item}" ${page.pageSize == item ? 'selected' : '' }>${item}</option>
					</c:forEach> 
				</select>
				</c:if>
			</div>
		<div>
	</td>
  </tr>
</table>