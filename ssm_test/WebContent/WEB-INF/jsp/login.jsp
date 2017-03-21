<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>登入</title>
	</head>
	
	<body>
		<form action="<%=request.getContextPath()%>/item/submit.action" method="post">
			<table>
				<tr><td>
					用户名:<input type="text" name="username"  />
				</td></tr>
				<tr><td>
					密码:<input type="password" name="password"  />
				</td></tr>
				<tr><td>
					<input type="submit" value="登入"  />
				</td></tr>
			</table>
		</form>
	</body>
</html>