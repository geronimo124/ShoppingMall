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
								<h3 class="box-title">Member List</h3>
							</div>


							<div class='box-body'>

								<select name="searchType">
									<option value="x"
										<c:out value="${cri.searchType == null?'selected':''}"/>>
										---</option>
									<option value="i"
										<c:out value="${cri.searchType eq 'i'?'selected':''}"/>>
										아이디</option>
									<option value="n"
										<c:out value="${cri.searchType eq 'n'?'selected':''}"/>>
										이름</option>
									<option value="a"
										<c:out value="${cri.searchType eq 'a'?'selected':''}"/>>
										닉네임</option>
								</select> <input type="text" name='keyword' id="keywordInput"
									value='${cri.keyword }'>
								<button id='btnSearch'>검색</button>
							</div>
						</div>


						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">LIST PAGING</h3>
								<button type="button" id="btnCheckDelete" class="btn btn-sm btn-danger" style="float: right;">체크 삭제</button>
							</div>
							<div class="box-body">
								<table class="table table-bordered text-center">
									<tr>
										<th><input type="checkbox" class="checkbox" id="checkAll"></th>
										<th>아이디</th>
										<th>이름</th>
										<th>닉네임</th>
										<th>전화번호</th>
										<th>이메일</th>
										<th>우편번호</th>
										<th>주소1</th>
										<th>주소2</th>
										<th>적립금</th>
										<th>가입일</th>
										<th>접속일</th>
									</tr>

									<c:forEach items="${memberList}" var="memberVO">

										<tr>
											<td><input type="checkbox" class="checkbox check" name="checkbox" value="${memberVO.mbId }"></td>
											<td>${memberVO.mbId }</td>
											<td>${memberVO.mbNm }</td>
											<td>${memberVO.mbNick }</td>
											<td>${memberVO.mbPhone }</td>
											<td>${memberVO.mbEmail }</td>
											<td>${memberVO.mbZip }</td>
											<td>${memberVO.mbAddr }</td>
											<td>${memberVO.mbDeaddr }</td>
											<td>${memberVO.mbMile }</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${memberVO.mbRegdt}" /></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${memberVO.mbCondt}" /></td>
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
												href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
										</c:if>

										<c:forEach begin="${pageMaker.startPage }"
											end="${pageMaker.endPage }" var="idx">
											<li
												<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
												<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>

										<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
											<li><a
												href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
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
		$(() => {
			
			$('#btnSearch').on('click', () => {

				self.location = "list"
					+ '${pageMaker.makeQuery(1)}'
					+ "&searchType="
					+ $("select option:selected").val()
					+ "&keyword="
					+ $('#keywordInput').val();

			});

			$('#checkAll').on('click', function () { $('.check').prop('checked', this.checked) });
			

			$('#btnCheckDelete').on('click', function() {

				let checkArr = [];
				
				$('tr .check:checked').each(function() {
					checkArr.push($(this).val());
				});

				$.ajax({
			        type		: "POST",
			        url 		: "deleteChecked",
			        data		: {memberList : checkArr},
			        success 	: function(data) {

						self.location = "list"
							+ '${pageMaker.makeQuery(1)}'
							+ "&searchType="
							+ $("select option:selected").val()
							+ "&keyword="
							+ $('#keywordInput').val();
			        }
			  	});
				
			});
		});
	</script>
</body>
</html>