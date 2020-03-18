<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 하위 카테고리 목록 -->
<ul style="list-style: none; text-align: center;">
	<c:forEach items="${userSubCategoryList}" var="subCategory">
		<li style="display: inline-block;">
			<a href="/?cate_prtcode=${subCategory.cate_prtcode}&cate_code=${subCategory.cate_code}" style="color: black;">
				<c:choose>
					<c:when test="${productVO == null}">
						<!-- 리스트 -->
						<c:if test="${subCategory.cate_code eq cate_code}"><b>${subCategory.cate_name}</b></c:if>
						<c:if test="${subCategory.cate_code ne cate_code}">${subCategory.cate_name}</c:if>		
					</c:when>
					<c:otherwise>
						<!-- 상세 페이지 -->
						<c:if test="${subCategory.cate_code eq productVO.cate_code}"><b>${subCategory.cate_name}</b></c:if>
						<c:if test="${subCategory.cate_code ne productVO.cate_code}">${subCategory.cate_name}</c:if>		
					</c:otherwise>
				</c:choose>
				
				<%-- <c:if test="${subCategory.cate_code eq productVO.cate_code}"><b>${subCategory.cate_name}</b></c:if>
				<c:if test="${subCategory.cate_code ne productVO.cate_code}">${subCategory.cate_name}</c:if> --%>
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</li>
	</c:forEach>
</ul>
<br>