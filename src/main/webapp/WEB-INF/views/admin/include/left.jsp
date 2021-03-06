<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">

		<!-- Sidebar user panel (optional) -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>${sessionScope.admin.admNm }</p>
				<!-- Status -->
				<a href="#"> <i class="fa fa-circle text-success"></i> Online
				</a>
			</div>
		</div>

		<!-- search form (Optional) -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control" placeholder="Search..."> <span class="input-group-btn">
					<button type="submit" name="search" id="search-btn" class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu" data-widget="tree">
			<li class="header">MENU</li>
			<li class="treeview"><a href="#"> <i class="fa fa-link"></i>
					<span>카테고리 관리</span> <span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="#">카테고리 등록</a></li>
					<li><a href="#">카테고리 수정</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-link"></i>
					<span>상품 관리</span> <span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="/admin/product/register">상품 등록</a></li>
					<li><a href="/admin/product/list">상품 목록</a></li>
					<li><a href="/admin/product/qna">상품 QNA</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-link"></i>
					<span>회원 관리</span> <span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="/admin/member/list">회원 목록</a></li>
					<li><a href="/admin/email/send">메일 전송</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-link"></i>
					<span>주문 관리</span> <span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="/admin/order/list">주문 조회</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-link"></i>
					<span>공지사항 관리</span> <span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="/admin/notice">공지사항 목록</a></li>
					<li><a href="/admin/notice/write">공지사항 등록</a></li>
				</ul></li>
		</ul>
		<!-- /.sidebar-menu -->
	</section>
	<!-- /.sidebar -->
</aside>
