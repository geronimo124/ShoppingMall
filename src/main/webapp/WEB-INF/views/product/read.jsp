<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/header.jsp"%>
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

				<div class="box box-primary">
					<div class="row">
						<div class="col-12 col-sm-6">
							<div class="col-12 text-center">
								<p></p>
								<img src="/product/displayFile?fileName=${productVO.pdImg }"
									class="product-image my-3" alt="Attachment"> <input
									type="hidden" id="pdNo" value="${productVO.pdNo }">
							</div>
							<p></p>
						</div>
						<div class="col-12 col-sm-4">
							<h3 class="my-3">${productVO.pdNm }</h3>

							<hr>
							<h4>Available Colors</h4>
							<div class="btn-group btn-group-toggle" data-toggle="buttons">
								<label class="btn btn-default text-center active"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off" checked=""> Green <br> <i
									class="fa fa-circle text-green"></i>
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option2"
									autocomplete="off"> Blue <br> <i
									class="fa fa-circle text-blue"></i>
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option3"
									autocomplete="off"> Purple <br> <i
									class="fa fa-circle text-purple"></i>
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option4"
									autocomplete="off"> Red <br> <i
									class="fa fa-circle text-red"></i>
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option5"
									autocomplete="off"> Orange <br> <i
									class="fa fa-circle text-orange"></i>
								</label>
							</div>

							<h4 class="mt-3">
								Size <small>Please select one</small>
							</h4>
							<div class="btn-group btn-group-toggle" data-toggle="buttons">
								<label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off"> <span class="text-xl">S</span> <br>
									Small
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off"> <span class="text-xl">M</span> <br>
									Medium
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off"> <span class="text-xl">L</span> <br>
									Large
								</label> <label class="btn btn-default text-center"> <input
									type="radio" name="color_option" id="color_option1"
									autocomplete="off"> <span class="text-xl">XL</span> <br>
									Xtra-Large
								</label>
							</div>

							<div class="py-2 px-3 mt-4">
								<h2 class="mb-0">
									<fmt:formatNumber value="${productVO.pdTag }" pattern="#,###" />
									원
								</h2>
								<h4 class="mt-0">
									<small>Sale: ${productVO.pdSale }%</small>
								</h4>
								<h4>
									수량 <input id="bskQty" value="1" type="number"
										class="form-control text-center"
										style="width: 150px; display: inline-block;">
								</h4>
							</div>

							<div class="my-4">
								<div id="btnOrder" class="btn btn-primary btn-lg btn-flat">
									<i class="fa fa-shopping-cart mr-2"></i>즉시 구매
								</div>

								<div id="btnBasket" class="btn btn-default btn-lg btn-flat">
									<i class="fa fa-cart-plus mr-2"></i>장바구니 담기
								</div>
							</div>
							<p></p>
						</div>
					</div>
					<!-- /.card-body -->
				</div>
				<div class="box box-default">
					<div class="box-body text-center">${productVO.pdDetl}</div>
				</div>

				<div class="row">
					<div class="col-md-12">

						<!-- The time line -->
						<ul class="timeline">
							<!-- timeline time label -->
							<li class="time-label" id="reviewDiv"><span
								class="bg-green"> 상품 리뷰 <small id='reviewcntSmall'></small>
							</span></li>
						</ul>

						<div class='text-center'>
							<ul id="pagination" class="pagination pagination-sm no-margin ">

							</ul>
						</div>

					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->



				<!-- Modal -->
				<div id="modifyModal" class="modal modal-default fade" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title"></h4>
							</div>
							<div class="modal-body" data-rno>
								<p>
									<select id="revGrade" name="revGrade" class="select2">
										<option value="1">★</option>
										<option value="2">★★</option>
										<option value="3">★★★</option>
										<option value="4">★★★★</option>
										<option value="5" selected="selected">★★★★★</option>
									</select> <input type="text" id="revTitle" name="revTitle"
										class="form-control"><br />
									<textarea rows="10" id="revContent" name="revContent" class="form-control"></textarea>
								</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-info" id="btnModRev">수정</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>
				
				<!-- /.card -->
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<script id="template" type="text/x-handlebars-template">
		{{#each .}}
	         <li class="reviewLi" data-rno={{revNo}}>
             <i class="fa fa-comments bg-blue"></i>
             <div class="timeline-item" >
                <span class="time">
                  <i class="fa fa-clock-o"></i>{{prettifyDate revDt}}
                </span>
                <h3 class="timeline-header"><strong>[<span class="revGrade">{{grade revGrade}}</span>]
					<span class="revTitle">{{revTitle}}</span>
					</strong> -{{revWriter}}</h3>
                <div class="timeline-body">{{revContent}} </div>
								<div class="timeline-footer">
								{{#eqReviewer revWriter }}
				<a class="btn btn-primary" 
									data-toggle="modal" data-target="#modifyModal">수정</a>
				<a class="btn btn-danger" onClick="btnDelRev(this)">삭제</a>				
								{{/eqReviewer}}
							  </div>
	            </div>			
           </li>
        {{/each}}
	</script>  

	<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
	<script>
	
	Handlebars.registerHelper("eqReviewer", function(reviewer, block) {
		var accum = '';
		if (reviewer == '${member.mbNick}') {
			accum += block.fn();
		}
		return accum;
	});
	

	Handlebars.registerHelper("prettifyDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
	});
	
	Handlebars.registerHelper("grade", function(grade) {

		let gradeStar = '';
		
		switch(grade) {
		case 1:
			gradeStar = '★';
			break;			
		case 2:
			gradeStar = '★★';
			break;			
		case 3:
			gradeStar = '★★★';
			break;			
		case 4:
			gradeStar = '★★★★';
			break;			
		case 5:
			gradeStar = '★★★★★';
		}

		return gradeStar;
		
	});

	var reviewPage = 1;
	
	var printData = function(replyArr, target, templateObject) {

		var template = Handlebars.compile(templateObject.html());

		var html = template(replyArr);
		$(".reviewLi").remove();
		target.after(html);

	}

	function getPage(pageInfo) {

		$.getJSON(pageInfo, function(data) {
			printData(data.list, $("#reviewDiv"), $('#template'));
			printPaging(data.pageMaker, $(".pagination"));

			$("#reviewcntSmall").html("[ " + data.pageMaker.totalCount + " ]");

		});
	}

	function btnDelRev(button) {

		let revNo = button.parentNode.parentNode.parentNode.getAttribute("data-rno");

		$.ajax({
			type : 'delete',
			url : '/review/' + revNo,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("리뷰가 삭제 되었습니다.");
					getPage("/review/" + '${productVO.pdNo}' + "/" + reviewPage);
				}
			}
		});
	}

	var printPaging = function(pageMaker, target) {

		var str = "";

		if (pageMaker.prev) {
			str += "<li><a href='" + (pageMaker.startPage - 1)
					+ "'> << </a></li>";
		}

		for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i ? 'class=active' : '';
			str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
		}

		if (pageMaker.next) {
			str += "<li><a href='" + (pageMaker.endPage + 1)
					+ "'> >> </a></li>";
		}

		target.html(str);
	};
	
		$(() => {

			getPage("/review/" + '${productVO.pdNo}' + "/1");

			$.ajaxSetup({

				error:function(x, e) {

					if(x.status == 500) {

						alert('로그인이 필요합니다');
						self.location = '/member/login';

					}

				}

			});
				
			
			$('#btnOrder').on('click', () => {

				self.location = '/order/insert?pdNo=' + '${productVO.pdNo}'
							 + '&bskQty=' + $('#bskQty').val();

			});

			$('#btnBasket').on('click', () => {
				
				$.ajax({
						url:"/basket/insert",
					    type:"post",
					    data: JSON.stringify(
							{pdNo : $('#pdNo').val(),
						     bskQty : $('#bskQty').val()}),
						headers : {
								"Content-Type" : "application/json",
								"X-HTTP-Method-Override" : "POST"
							},
					    dataType:"text",
					    success:(data) => {
					    	if(data == 'SUCCESS')
						    	alert('장바구니에 상품이 추가되었습니다.');
					    	else
						    	alert('이미 장바구니에 상품이 있습니다.');
						}

				 });
			

			});

			$(".pagination").on("click", "li a", function(event) {

				event.preventDefault();

				reviewPage = $(this).attr("href");

				getPage("/review/" + '${productVO.pdNo}' + "/" + reviewPage);

			});

			$(".timeline").on("click", ".reviewLi", function(event) {

				var review = $(this);

				$("#revTitle").val(review.find('.revTitle').text());
				$("#revContent").val(review.find('.timeline-body').text());
				$('.modal-body').attr('data-rno', review.attr('data-rno'));

			});

			$('#btnModRev').on('click', function() {

				let revNo = $('.modal-body').attr('data-rno');

				$.ajax({
					type : 'put',
					url : '/review/' + revNo,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "PUT"
					},
					data : JSON.stringify({
						revTitle : $('#revTitle').val(),
						revWriter : '${member.mbNick}',
						revContent : $('#revContent').val(),
						revGrade : $('#revGrade').val()
					}),
					dataType : 'text',
					success : function(data) {
						if (data == 'SUCCESS') {
							alert("수정 되었습니다.");
						}
						$("#modifyModal").modal('hide');
						getPage("/review/" + '${productVO.pdNo}' + "/" + reviewPage);
					}
				});
			});
		});
	
	</script>
</body>
</html>