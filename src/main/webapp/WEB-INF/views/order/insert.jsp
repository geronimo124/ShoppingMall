<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<style>
/* API ZIP */
.search-zip {
	display: none;
	border: 1px solid;
	width: 450px;
	height: 400px;
	margin: 5px 0;
	position: relative;
}

/* API ZIP close img */
.search-zip-close-img {
	cursor: pointer;
	position: absolute;
	right: 0px;
	top: -1px;
	z-index: 1;
}
</style>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/include/main_header.jsp"%>

		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/include/left.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Page Header <small>Optional description</small>
				</h1>
				<%=application.getRealPath("/")%>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">
				<form id="formOrder" method="post" action="insert">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">주문 리스트</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table class="table table-bordered text-center">
							<tr>
								<th></th>
								<th>상품명</th>
								<th>수량</th>
								<th>판매가</th>
								<th>적립금</th>
							</tr>


							<c:forEach items="${basketList}" var="basketVO">

								<tr>
									<td><img
										src="/product/displayFile?fileName=${basketVO.pdImg }"
										alt="Attachment"></td>
									<td>${basketVO.pdNm }</td>
									<td>${basketVO.bskQty }</td>
									<td class="tag"><fmt:parseNumber
											value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100 * basketVO.bskQty}"
											integerOnly="true" pattern="#,###" /></td>
									<td class="mile"><fmt:parseNumber
											value="${basketVO.pdTag * (100 - basketVO.pdSale) / 100 * basketVO.bskQty / 100}"
											integerOnly="true" /></td>
								</tr>
								<input type="hidden" name="pdNo" value="${basketVO.pdNo }">
								<input type="hidden" name="bskQty" value="${basketVO.bskQty }">
							</c:forEach>

						</table>
					</div>
				</div>
					<div class="box box-default">
						<div class="box-header with-border">
							<h3 class="box-title">배송 정보</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<div class="form-horizontal">
							<div class="box-body">
								<div class="form-group">
									<label for="ordNm" class="col-sm-1 control-label">이름</label>

									<div class="col-sm-11">
										<input type="text" class="form-control" style="width: 430px;"
											id="ordNm" name="ordNm" value="${member.mbNm }">
									</div>
								</div>
								<div class="form-group form-row">
									<label class="col-sm-1 control-label">연락처</label>

									<div class="col-sm-11">
										<c:set var="phone" value="${fn:split(member.mbPhone, '-')}" />
										<c:forEach var="phones" items="${phone }" varStatus="g">
											<c:if test="${g.count == 1 }">
												<select class="form-control select2"
													style="width: 135px; display: inline-block;" id="ordPhone1">
													<c:if test="${phones eq '010' }">
														<option selected="selected" value="010">010</option>
													</c:if>
													<c:if test="${phones ne '010' }">
														<option value="010">010</option>
													</c:if>
													<c:if test="${phones eq '011' }">
														<option selected="selected" value="011">011</option>
													</c:if>
													<c:if test="${phones ne '011' }">
														<option value="011">011</option>
													</c:if>
													<c:if test="${phones eq '019' }">
														<option selected="selected" value="019">019</option>
													</c:if>
													<c:if test="${phones ne '019' }">
														<option value="019">019</option>
													</c:if>
												</select>
											</c:if>
											<c:if test="${g.count == 2 }">
												<span>-</span>
												<input type="text" class="form-control" value="${phones }"
													style="width: 135px; display: inline-block;" id="ordPhone2"
													autocomplete="off" pattern="\d{3,4}" required />
											</c:if>
											<c:if test="${g.count == 3 }">
												<span>-</span>
												<input type="text" class="form-control" value="${phones }"
													style="width: 135px; display: inline-block;" id="ordPhone3"
													autocomplete="off" pattern="\d{3,4}" required />
											</c:if>
										</c:forEach>
									</div>
									<input type="hidden" id="ordPhone" name="ordPhone">
								</div>
								<div class="form-group form-row">
									<label for="btnSearchZip" class="col-sm-1 control-label">주소</label>

									<div class="col-sm-11">
										<div class="form-row">
											<input type="text" class="form-control"
												style="width: 80px; display: inline-block;" id="ordZip" name="ordZip"
												readonly="readonly" value="${member.mbZip }"> <input
												type="button" id="btnSearchZip" class="btn"
												style="width: 120px; display: inline-block;" value="우편번호 찾기">
										</div>
										<input type="text" class="form-control" name="ordAddr"
											style="width: 430px; display: inline-block;"
											readonly="readonly" value="${member.mbAddr }"> <input
											type="text" class="form-control" name="ordDeaddr"
											style="width: 430px; display: inline-block;"
											value="${member.mbDeaddr }">
										<div id="iframeSearchZip" class="search-zip">
											<img
												src="//t1.daumcdn.net/postcode/resource/images/close.png"
												class="search-zip-close-img" id="imgSearchZip" alt="접기 버튼">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="mbNm" class="col-sm-1 control-label">주문 메세지</label>

									<div class="col-sm-11">
										<textarea name="ordMsg" rows="5" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="box box-info">
						<div class="box-body row text-center">
							<div class="col-md-3">
								<strong>총 상품 금액<br /> <span id="sum"></span></strong>
							</div>
							<div class="col-md-3">
								<strong>배송비<br />2500
								</strong>
							</div>
							<div class="col-md-3">
								<strong>할인 금액<br /> <span id="mile">0</span></strong>
							</div>
							<div class="col-md-3">
								<strong>결제 예정 금액<br /> <span id="total"></span></strong>
							</div>
							<br />
							<div class="form-group">
								<label for="mbNm" class="col-md-1 control-label">적립금 사용</label>

								<div class="col-md-11">
									<input type="text" id="useMile" class="form-control text-left"
										style="width: 200px; display: inline-block;" pattern="[0-9]+"
										value="0"> (사용가능 적립금: <strong><span
										id="mbMile">${member.mbMile }</span></strong>원)
								</div>
							</div>
							<br />
							<button id="btnOrder" type="button"
								class="btn btn-lg btn-primary" style="width: 300px;">주문하기</button>
							<br />
						</div>
					</div>
					<input type="hidden" name="mbId" value="${member.mbId }">
					<input type="hidden" id="mileage" name="mile">
					<input type="hidden" id="ordPrice" name="ordPrice">
				</form>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@include file="/WEB-INF/views/include/footer.jsp"%>

		<!-- Control Sidebar -->
		<%@include file="/WEB-INF/views/include/aside.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
	<script>

		$(() => {
			
			let maxMile = '${member.mbMile}';
			let sum = 0;
			
			$('.tag').each(function() {
				sum += parseInt($(this).html());
			})

			$('#sum').html(sum);
			$('#total').html(sum + 2500);

			$('#useMile').on('focusout', function() {

				if(maxMile < $(this).val()) {
					alert('적립금을 초과하여 사용할 수 없습니다.');
					$(this).val(0);
				} else {

					if($(this).val() == null) {
						$(this).val(0);
					} else {
						let total = parseInt($('#total').html());

						$('#total').html(total - $(this).val());
						$('#mile').html($(this).val());

					}
				}

			});

			$('#btnOrder').on('click', () => {

				$('#ordPhone').val($('#ordPhone1').val() + '-' + $('#ordPhone2').val() + '-' + $('#ordPhone3').val());
				$('#ordPrice').val($('#total').html());

				let totalMile = 0;
				
				$('.mile').each(function() {
					totalMile += parseInt($(this).html());
				});
				$('#mileage').val(parseInt(maxMile) + totalMile - parseInt($('#useMile').val()));

				$('#formOrder').submit();

			});

		});

	</script>




	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		$(() => {
			$('#imgSearchZip').on('click', () => $('#iframeSearchZip').css('display', 'none'));
			$('#btnSearchZip').on('click', () => {
                const currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
                new daum.Postcode({
                    oncomplete: data => {

                        let addr = "";

                        if (data.userSelectedType === 'R') {
                            addr = data.roadAddress;
                        } else {
                            addr = data.jibunAddress;
                        }

                        $('#mbZip').val(data.zonecode);
                        $('#mbAddr').val(addr);
                        $('#mbDeaddr').focus();

                        $('#iframeSearchZip').css('display', 'none');

                        document.body.scrollTop = currentScroll;

                    },

                    width: '100%',
                    height: '100%'
                }).embed($('#iframeSearchZip').get(0));

                $('#iframeSearchZip').css('display', 'block');
            });
			
		});
	</script>
</body>
</html>