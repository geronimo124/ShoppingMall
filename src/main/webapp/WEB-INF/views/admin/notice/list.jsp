<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/admin/include/header.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/admin/include/main_header.jsp"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/admin/include/left.jsp"%>

		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<!-- left column -->


					<div class="col-md-12">
						<!-- general form elements -->
						<div class='box'>
							<div class="box-header with-border">
								<h3 class="box-title">Notice List</h3>
							</div>


							<div class='box-body'>

								<select name="searchType">
									<option value="x"
										<c:out value="${cri.searchType == null?'selected':''}"/>>
										---</option>
									<option value="t"
										<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
										제목</option>
									<option value="c"
										<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
										내용</option>
								</select> <input type="text" name='keyword' id="keywordInput"
									value='${cri.keyword }'>
								<button id='btnSearch'>검색</button>
								<button id='btnWrite'>공지사항 등록</button>
							</div>
						</div>


						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">LIST PAGING</h3>
							</div>
							<div class="box-body">
								<table class="table table-bordered text-center" id="orderList">
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>내용</th>
										<th>작성자</th>
										<th>날짜</th>
									</tr>

									<c:forEach items="${noticeList}" var="noticeVO">

										<tr>
											<td>${noticeVO.ntNo }</td>
											<td><a href="/admin/notice/read${pageMaker.makeSearch(pageMaker.cri.page) }&ntNo=${noticeVO.ntNo}">${noticeVO.ntTitle }</a></td>
											<td>${noticeVO.ntContent }</td>
											<td>${noticeVO.admNm }</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${noticeVO.ntDt}" /></td>
										</tr>

									</c:forEach>

								</table>
							</div>
							<!-- /.box-body -->


							<div class="box-footer">

								<div class="text-center">
									<ul class="pagination">

										<c:if test="${pageMaker.prev}">
											<li><a
												href="${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
										</c:if>

										<c:forEach begin="${pageMaker.startPage }"
											end="${pageMaker.endPage }" var="idx">
											<li
												<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
												<a href="${pageMaker.makeSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>

										<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
											<li><a
												href="${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
										</c:if>

									</ul>
								</div>

							</div>
							<!-- /.box-footer-->
						</div>
					</div>
					<!--/.col (left) -->

				</div>
				<!-- /.row -->
			</section>
		</div>
	</div>
	<!-- /.content -->
	<%@include file="/WEB-INF/views/admin/include/footer.jsp"%>
	<%@include file="/WEB-INF/views/admin/include/aside.jsp"%>
	<%@include file="/WEB-INF/views/admin/include/plugin_js.jsp"%>

	<script>
		var result = '${msg}';

		if(result == 'SUCCESS')
			alert('공지사항 등록이 완료되었습니다.');
	</script>

	<script>
		$(() => {

			$('#btnWrite').on('click', () => {

				self.location = "/admin/notice/write";

			});
			
			$('#btnSearch').on('click', () => {

				self.location = '${pageMaker.makeQuery(1)}'
					+ "&searchType="
					+ $("select option:selected").val()
					+ "&keyword="
					+ $('#keywordInput').val();

			});

		});
	</script>
</body>
</html>