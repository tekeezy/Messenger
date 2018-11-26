<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<%@include file="../include/header.jsp"%>


<script type="text/javascript">
   if(window.name == "newWindow") {
	   window.close();
   }
   function onSearch() {
      var friend_name = document.getElementById("friendname").value;

      if (friend_name != "") {
         location.href = "/messenger/search/" + friend_name;
      } else {
         location.href = "/messenger/search/";
      }
   }
   
   function ondelete(friend_email){
	   if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		   location.href= "/friend_delete/" + friend_email;
		}else{   //취소
		    return;
		}
   }
   
   function go(friendEmail) {
	   var form = document.createElement("form");
	   form.setAttribute("action","/messenger/chat/"+friendEmail);     
	   form.setAttribute("method","POST");
	   document.body.appendChild(form); 
	   
	   var input = document.createElement("input");  
	   input.setAttribute("type", "hidden");                 
	   input.setAttribute("name", "_csrf");                        
	   input.setAttribute("value", '${_csrf.token}');
	   form.appendChild(input);
	   form.submit();
   }
</script>

<div class="container">
   <nav class="navbar navbar-default navbar-static-top">
      <div class="navbar-header">
            <tr>
               <td><img class="img-circle" width="40" height="40"
                  src="${pageContext.request.contextPath}/resources/upload/profile/${member.profile}" /></td>
            </tr>
            <tr>
               <td>&nbsp&nbsp${member.name}</td>
            </tr>
      </div>   
      <ul class="nav navbar-top-links navbar-right" role="menu">
         <li class="dropdown" id="menu">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
               <i class="fa fa-ellipsis-h fa-fw" style="font-size:18px"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
               <li><a href="/memberUpdate"><i class="fa fa-user fa-fw"></i>프로필수정(구현 예정)</a></li>
               <li><a href="/pwchange"><i class="fa fa-sign-out fa-fw"></i>비밀번호 변경(구현 예정)</a></li>
               <li><a href="/logout"><i class="fa fa-sign-out fa-fw"></i>로그아웃</a></li>
            </ul>
         </li>
         <li class="dropdown">
            <a href="/messenger"><i class="fa fa-user fa-fw" style="font-size:18px"></i></a>
         </li>
         
         <li class="dropdown">
         <a href="/messenger/chat_list">
            <c:choose>
               <c:when test="${totalmsg ne 0}">
                  <i class="fa fa-comments fa-fw" style="font-size:18px">
                     <span class="badge bg-important" style="background-color: red">${totalmsg}</span>
                  </i>
               </c:when>
               <c:otherwise>
                  <i class="fa fa-comments fa-fw" style="font-size:18px"></i>
               </c:otherwise>

            </c:choose>
         </a>
         </li>
         
      
      </ul>
   </nav>

   <div class="panel panel-default">
      <div class="panel-heading" style="text-align: center;">
         친구
         <a href="/messenger/search"><img style="width:20px; height: 20px; float:right; margin-top: 3px" 
         	src="${pageContext.request.contextPath}/resources/upload/button/friend+.png" /></a>
      </div>
      
      <div class="panel panel-body">
         <div class="list-group">
            <input type="text" id="friendname" name="friendname" onkeypress="if(event.keyCode==13) {onSearch();}" 
            class="form-control" placeholder="친구검색" style="text-align: center;"> 
            <span class="input-group-btn"></span>
            <c:forEach items="${friends}" var="vo">
               <p class="list-group-item">
                  <img class="img-circle" width="50" height="50" 
                  		src="${pageContext.request.contextPath}/resources/upload/profile/${vo.profile}" />&nbsp&nbsp
                  <a href="#" onclick="go('${vo.email}');">${vo.name}</a>
                  <%-- <a style="float: right" href="#" onclick="ondelete('${vo.email}');"><img style="width:23px; height: 23px; margin-top: 10px" src="/image/profile/friend-.png" /></a> --%>
               </p>
            </c:forEach>
         </div>
      </div>
   </div>
</div>

<%@include file="../include/footer.jsp"%>