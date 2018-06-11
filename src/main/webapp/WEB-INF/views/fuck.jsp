<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title hereffff</title>
</head>
<body>
Insert title hereffff
    <h2>Title : ${title}</h2>
    <h2>Time : ${SPRING_SECURITY_LAST_EXCEPTION.message}</h2>
    
    <div class="text-center">
		  	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		  		<span class="text-danger"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></span>
		  		<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
		  	</c:if>
		  </div>
</body>
</html>