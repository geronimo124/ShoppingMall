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
								<h3 class="box-title">QNA List</h3>
							</div>


							<div class='box-body'>

								<select name="searchType">
									<option value="x"
										<c:out value="${cri.searchType == null?'selected':''}"/>>
										---</option>
									<option value="n"
										<c:out value="${cri.searchType eq 'n'?'selected':''}"/>>
										상품 이름</option>
									<option value="i"
										<c:out value="${cri.searchType eq 'i'?'selected':''}"/>>
										아이디</option>
									<option value="t"
										<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
										제목</option>
									<option value="w"
										<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
										작성자</option>
									<option value="c"
										<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
										내용</option>
								</select> <input type="text" name='keyword' id="keywordInput"
									value='${cri.keyword }'>
								<button id='btnSearch'>검색</button>
							</div>
						</div>


						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">LIST PAGING</h3>
							</div>
							<div class="box-body">
								<table class="table table-bordered text-center" id="qnaList">
									<tr>
										<th>번호</th>
										<th>아이디</th>
										<th>상품 이름</th>
										<th>제목</th>
										<th>작성자</th>
										<th>내용</th>
										<th>날짜</th>
										<th>버튼</th>
									</tr>

									<c:forEach items="${qnaList}" var="qnaVO">

										<tr class="qnaTr" data-level=${qnaVO.qnaLevel }
									data-step=${qnaVO.qnaStep } data-group=${qnaVO.qnaGroup } data-pdNo=${qnaVO.pdNo }>
											<td><span class="qnaNo">${qnaVO.qnaNo }</span></td>
											<td>${qnaVO.mbId }</td>
											<td>${qnaVO.pdNm }</td>
											<td style="text-align: left;"><c:forEach begin="1"
													end="${qnaVO.qnaLevel }" step="1">
													<i class="fa fa-arrow-right"></i>
												</c:forEach><span class="qnaTitle">${qnaVO.qnaTitle }</span></td>
											<td>${qnaVO.qnaWriter }</td>
											<td><span class="qnaContent">${qnaVO.qnaContent }</span></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${qnaVO.qnaDt}" /></td>
											<td><button type="button" class="btn btn-sm btn-success"
													data-toggle="modal" data-target="#qnaModal">답변</button>
											<button type="button" class="btn btn-sm btn-danger btnDelete">삭제</button>
											<c:set var="id" value="${admin.admId }" />
											<c:if test="${qnaVO.mbId eq id}">
												<button type="button" class="btn btn-sm btn-info btnModify"
												data-toggle="modal" data-target="#qnaModModal">수정</button>											
											</c:if>
											</td>
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
												href="qna${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
										</c:if>

										<c:forEach begin="${pageMaker.startPage }"
											end="${pageMaker.endPage }" var="idx">
											<li
												<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
												<a href="qna${pageMaker.makeSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>

										<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
											<li><a
												href="qna${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
										</c:if>

									</ul>
								</div>

							</div>
							<!-- /.box-footer-->
						</div>
					</div>
					<!--/.col (left) -->

					<!-- QNA Modal -->
					<div id="qnaModal" class="modal modal-default fade" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">QNA 등록</h4>
								</div>
								<div class="modal-body" id="qnaRegModalBody" data-level
									data-step data-group data-pdNo>
									<p>
										<input type="text" id="qnaRegTitle" name="qnaTitle"
											class="form-control"><br />
										<textarea rows="10" id="qnaRegContent" name="qnaContent"
											class="form-control"></textarea>
									</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" id="btnRegQna">등록</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">취소</button>
								</div>
							</div>
						</div>
					</div>
					
					<!-- QNA Modify Modal -->
					<div id="qnaModModal" class="modal modal-default fade" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">QNA 수정</h4>
								</div>
								<div class="modal-body" id="qnqModModalBody" data-qno>
									<p>
										<input type="text" id="qnaModTitle" name="qnaTitle"
											class="form-control"><br />
										<textarea rows="10" id="qnaModContent" name="qnaContent"
											class="form-control"></textarea>
									</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" id="btnModQna">수정</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">취소</button>
								</div>
							</div>
						</div>
					</div>
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

				self.location = "qna"
					+ '${pageMaker.makeQuery(1)}'
					+ "&searchType="
					+ $("select option:selected").val()
					+ "&keyword="
					+ $('#keywordInput').val();

			});

			$("#qnaList").on("click", ".qnaTr", function(event) {

				var qna = $(this);

				$('#qnaRegModalBody').attr('data-group', qna.attr('data-group'));
				$('#qnaRegModalBody').attr('data-step', qna.attr('data-step'));
				$('#qnaRegModalBody').attr('data-level', qna.attr('data-level'));
				$('#qnaRegModalBody').attr('data-pdNo', qna.attr('data-pdNo'));

			});

			
			$('#btnRegQna').on('click', () => {
				
				$.ajax({
					type : 'post',
					url : '/admin/product/qna/insert',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "post"
					},
					data : JSON.stringify({
						qnaGroup : $('#qnaRegModalBody').attr('data-group'),
						qnaStep : $('#qnaRegModalBody').attr('data-step'),
						qnaLevel : $('#qnaRegModalBody').attr('data-level'),
						mbId : '${admin.admId}',
						pdNo : $('#qnaRegModalBody').attr('data-pdNo'),
						qnaTitle : $('#qnaRegTitle').val(),
						qnaWriter : '${admin.admNm}',
						qnaContent : $('#qnaRegContent').val(),
					}),
					dataType : 'text',
					success : function(data) {
						if (data == 'SUCCESS') {
							alert("상품 답변이 등록 되었습니다.");
						}
						$("#qnaRegModal").modal('hide');
						$("#qnaRegTitle").val('');
						$("#qnaRegContent").val('');

						self.location = "qna"
							+ '${pageMaker.makeQuery(1)}'
							+ "&searchType="
							+ $("select option:selected").val()
							+ "&keyword="
							+ $('#keywordInput').val();
					}
				});
				
			});

			$('.btnModify').on('click', function() {

				let that = $(this).parent().parent();

				$("#qnaModTitle").val(that.find('.qnaTitle').text());
				$("#qnaModContent").val(that.find('.qnaContent').text());
				$('#qnqModModalBody').attr('data-qno', that.find('.qnaNo').text());
			});

			$('#btnModQna').on('click', () => {

				$.ajax({
					type : 'put',
					url : '/admin/product/qna/modify',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "put"
					},
					data : JSON.stringify({qnaNo : $('#qnqModModalBody').attr('data-qno'),
										   qnaTitle : $('#qnaModTitle').val(),
										   qnaContent : $('#qnaModContent').val()}),
					dataType : 'text',
					success : function(data) {

						alert('QNA가 수정되었습니다.');
						self.location = "qna"
							+ '${pageMaker.makeQuery(1)}'
							+ "&searchType="
							+ $("select option:selected").val()
							+ "&keyword="
							+ $('#keywordInput').val();
					}
				});

			});


			$('.btnDelete').on('click', function() {

				let that = $(this).parent().parent();
				
				$.ajax({
					type : 'delete',
					url : '/admin/product/qna/delete',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "delete"
					},
					data : JSON.stringify({qnaGroup : that.attr('data-group'),
							qnaStep : that.attr('data-step'),
							qnaLevel : that.attr('data-level')}),
					dataType : 'text',
					success : function(data) {

						alert('QNA가 삭제되었습니다.');
						self.location = "qna"
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