<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, nemo.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="group" value="${groupInfo}" />

<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>네모</title>
  </head>
  <body>

    <!-- section1 시작 -->
    <div class="section1" style="background-image:url('${contextPath}/groupImageDownload?group_id=${group.groupVO.grp_id}&group_img=${group.groupVO.grp_img}')">
      <div class="group_containter">
        
        <div class="group_all">
            <div class="group_img">
                <img src="${contextPath}/groupImageDownload?group_id=${group.groupVO.grp_id}&group_img=${group.groupVO.grp_img}" />
            </div>
            <div class="group_name">
                <a href="${contextPath}/group/groupMain?group_id=${group.groupVO.grp_id}">
                    <span class="group_name_title">${group.groupVO.grp_name}</span>
                </a>
                <div class="group_info_category">
                    <div class="category_box group_info_category_box">${group.groupVO.main_name} | ${group.groupVO.sub_name}</div>
	                 <div class="group_info_member">
	                     <div class="group_info_title"><span>MEMBER</span></div>
	                     <div class="group_info_contents"><span class="group_info_number">${group.currentMemNo} / ${group.groupVO.mem_no}</span></div>
	                 </div>
	                 <div class="group_info_follower">
	                     <div class="group_info_title"><span>SINCE</span></div>
	                     <div class="group_info_contents"><span class="group_info_date"><fmt:formatDate pattern="yyyy.MM.dd." value="${group.groupVO.create_date}"/></span></div>
	                 </div>
            	</div>
            </div>
        </div>
        
      </div>
    </div>
    <!-- section1 종료 -->

  </body>
</html>
