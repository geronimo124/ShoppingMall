<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">

		<!-- Sidebar user panel (optional) -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="/dist/img/user2-160x160.jpg" class="img-circle"
					alt="User Image">
			</div>
			<div class="pull-left info">
				<c:choose>
					<c:when test="${member == null}">
						<p>로그인하세요</p>
					</c:when>
					<c:otherwise>
						<p>${member.mbNick }</p>
					</c:otherwise>
				</c:choose>
				<!-- Status -->
				<a href="#"> <i class="fa fa-circle text-success"></i> Online
				</a>
			</div>
		</div>

		<!-- search form (Optional) -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control"
					placeholder="Search..."> <span class="input-group-btn">
					<button type="submit" name="search" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu" data-widget="tree">
			<li class="header">MENU</li>
			<!-- <li class="active">
				<a href="#">
					<i class="fa fa-link"></i>
					<span>Link</span>
				</a>
			</li>
			<li>
				<a href="#">
					<i class="fa fa-link"></i>
					<span>Another Link</span>
				</a>
			</li> -->
			<c:forEach items="${mainCateList}" var="categoryVO">
				<li class="treeview mainCate">
					<a href="#"> <i class="fa fa-tags"></i> <span>${categoryVO.ctgyNm }</span>
						<span class="pull-right-container">
									<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
							<ul class="treeview-menu subCategory">
								
							</ul>
					<input type="hidden" class="mainCategory" value="${categoryVO.ctgyCd }"/>
				</li>
			</c:forEach>
			
			<c:choose>
				<c:when test="${sessionScope.member == null}">
					<!-- 로그인 X -->
					<li><a href="/member/register"><i class="fa fa-user"></i><span>회원
								가입</span></a>
					<li><a href="/member/login"><i class="fa fa-sign-in"></i><span>로그인</span></a>
				</c:when>
				<c:otherwise>
					<!-- 일반 회원 -->

					<li><a href="/basket/list"><i class="fa fa-suitcase"></i><span>장바구니</span></a>
					</li>
					<li><a href="/order/list"><i class="fa fa-shopping-cart"></i><span>주문조회</span></a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
		<!-- /.sidebar-menu -->
	</section>
	<!-- /.sidebar -->
</aside>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

	<script id="cateTemplate" type="text/x-handlebars-template">
		{{#each .}}
			<li><a href="/product/list?${pageMaker.makeQuery(1)}&ctgyCd={{ctgyCd}}"><i class="fa fa-dot-circle-o"></i><span>{{ctgyNm}}</span></a></li>
		{{/each}}
	</script>
	<script src="/bower_components/jquery/dist/jquery.min.js"></script>
<script>

	$(() => {

		$('.mainCate').on('click', function() {

			let clicked = $(this);
			let mainCategory = clicked.find('.mainCategory').val();
			let cateTemplate = Handlebars.compile($("#cateTemplate").html());

			$.ajax({
				   url:"/product/getSubCateList/" + mainCategory,
				   type:"get",
				   dataType:"json",
				   success:function(subCategories){
						  
						  let html = cateTemplate(subCategories);

						  clicked.find('.subCategory li').remove();
						  clicked.find('.subCategory').append(html);
				   }
			});
		});

	});

</script>