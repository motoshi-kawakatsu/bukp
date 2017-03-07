<%@ tag language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${status == '2'}">
<c:set var="newline" value="<%= \"\n\" %>" />

             <c:if test="${errorInfo.errors[0].messageType == 'I'}">
	            <div style="text-align: left; display:block;" id="info1">
		             <div id="messageInfo" class="textarea_agr">
			             <ul>
			                <li class="icon"><img src="${baseurl}/imgs/icon_2_normal.png" class="rightspace4"></li>
							<li> ${fn:replace(errorInfo.errors[0].message, newline, "<br/>")} </li>
			             </ul>
		             </div>
	            </div>
			</c:if> 


			 <c:if test="${errorInfo.errors[0].messageType != 'I'}">
			
				<div id="messageInfo" class="textareared">
					<ul>
						<c:if test="${errorInfo.errors[0].messageType == 'E'}">
							<li class="icon"><img src="${baseurl}/imgs/icon_1_normal.png" class="rightspace4"></li>
						</c:if>
						
						<c:if test="${errorInfo.errors[0].messageType == 'W'}">
							<li class="icon"><img src="${baseurl}/imgs/icon_1_normal.png" class="rightspace4"></li>
						</c:if>
					    <li> 
							<span class="red">「<c:out value="${errorInfo.errors[0].title}" /> 」</span> 
						      ${fn:replace(errorInfo.errors[0].message, newline, "<br/>")}
					    </li>
					</ul>
				</div>
			</c:if> 
</c:if>
<c:if test="${status != '2'}">
	<div id="messageInfo"></div>
</c:if> 
