<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<title>查询商品列表</title>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:'${pageContext.request.contextPath }/item/testAjax.action',
			type:'post',
			contentType:"application/json;charset=utf-8",
			data:'{"name":"手机","price":"2000"}',
			success:function(data){
// 				alert(data)
			}
		})
	})
</script>
</head>
<body> 
<form action="${pageContext.request.contextPath }/item/queryItem.action" method="post">
查询条件：
<table width="100%" border=1>
<tr>
	<td>商品名称:<input type="text" name="items.name"/></td>
	<td>商品价格:<input type="text" name="items.price"/></td>
	<td><input type="submit" value="查询"/></td>
</tr>
</table>
</form>
商品列表：
<%-- <form action="${pageContext.request.contextPath }/updateItemsArray.action" method="post"> --%>
<form action="${pageContext.request.contextPath }/updateItemsList.action" method="post">
	<table width="100%" border=1>
	<tr>
		<td>商品名称</td>
		<td>商品价格</td>
		<td>生产日期</td>
		<td>商品描述</td>
		<td>操作</td> 
	</tr>
	<c:forEach items="${itemList }" var="item" varStatus="status">
	<tr>
		<td><input type="checkbox" value="${item.id }" name="ids"/></td>
		<td><input type="text" value="${item.name }" name="itemsList[${status.index }].name"/></td>
		<td><input type="text" value="${item.price }" name="itemsList[${status.index }].price"/></td>
		<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td><input type="text" value="${item.detail }" name="itemsList[${status.index }].detail"/></td>
		
<%-- 		<td><a href="${pageContext.request.contextPath }/item/itemEdit.action?id=${item.id}">修改</a></td> --%>
		<td><a href="${pageContext.request.contextPath }/item/toEditPage.action?id=${item.id}">修改</a></td>
	
	</tr>
	</c:forEach>
	
	</table>
	<input type="submit" value="保存"/>
</form>
</body>

</html>