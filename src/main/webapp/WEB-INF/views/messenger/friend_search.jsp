
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<%@include file="../include/header.jsp" %>
 
<script type="text/javascript">
   function onSearch(){
      var friend_name = document.getElementById("friendsearch").value;
      
      if(friendsearch!=""){
         location.href="/messenger/search/" + friend_name; 
      }
   }

</script>
<div class="container">
   <div class="panel panel-default">
		<div class="panel-heading" style="text-align: center;">
			<a href="/messenger"><i class="fa fa-chevron-left fa-5x" style="float: left; font-size:15px" ></i></a>친구찾기
		</div>
		<div class="panel-body">
			<div class="list-group">
				<input type="text" id="friendsearch" name="friendsearch"onkeypress="if(event.keyCode==13) {onSearch();}" 
				class="form-control" placeholder="친구찾기" style="text-align: center;">
				<span class="input-group-btn"></span>
				

				<br/>
				<c:if test="${!empty friend}">
					<p class="list-group-item">
					<img class="img-circle" width="50" height="50"
							src="${pageContext.request.contextPath}/resources/upload/profile/${friend.profile}" />&nbsp&nbsp&nbsp${friend.name}
							<span class="pull-right text-muted small">
								<a class="fa fa-plus-circle" href="#" onclick="javascript:document.myForm.submit();" style="font-size:20px; margin-top: 12px"></a>
							</span>
						</p>
						<form name="myForm" action="/messenger/friend/${friend.email}" method="POST">
								<input type="hidden" name="_csrf" value="${_csrf.token}">
						</form>
				</c:if>
				<c:if test="${empty friend}">
					<c:if test="${empty init}">
						<p class="list-group-item">해당 사용자가 존재하지 않습니다.</p>
					</c:if>
				</c:if>

			</div>
		</div>
	</div>
	
</div>

<%@include file="../include/footer.jsp" %>


