<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header" class="d-flex">
	<nav class="navbar navbar-expand w-100">
		<div class="container-fluid">
			<a href="/myroom/user/map" class="logo-text mr-5"><i
				class="fa fa-home fa-fw"></i>MyRoom</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mt-3">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/myroom/user/map">매물 보기</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 방 내놓기/관리 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="/myroom/user/new_room">방
									내놓기</a></li>
							<li><a class="dropdown-item" href="/myroom/user/rooms">방
									관리</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/myroom/user/reservation">예약내역</a></li>
				</ul>
				<button type="button" class="modal-btn btn btn-outline-success"
					data-bs-toggle="modal" data-bs-target="#exampleModal">마이페이지</button>

				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="exampleModalLabel">마이페이지</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div id="email" class="mt-3 d-flex justify-content-between">
									<small>이메일</small>
									<span></span>
								</div>
								<hr>
								<div id="name" class="mt-3 d-flex justify-content-between">
									<small>이름</small>
									<span></span>
								</div>
								<hr>
								<div id="loginId" class="mt-3 d-flex justify-content-between">
									<small>아이디</small>
									<span></span>
								</div>
								<hr>
								<div id="password" class="mt-3 d-flex justify-content-between">
									<small>패스워드</small>
									<span>***************</span>
								</div>
								<hr>
								
							</div>
							<div class="modal-footer d-flex">
								<button type="button" class="btn btn-secondary form-control"
									data-bs-dismiss="modal">닫기</button>
								<button id="del-user-btn" class="form-control btn btn-danger" type="button" data-userId="">회원탈퇴</button>
							</div>
						</div>
					</div>
				</div>
				<button class="btn btn-outline-danger" type="button"
					onclick="location.href='/myroom/sign_out'">로그아웃</button>
			</div>
		</div>
	</nav>
</div>

<script>
	$(document).ready(function() {
		$('.modal-btn').on('click', function(e) {
			e.preventDefault();
			let userId = ${userId};
			
			$.ajax({
				type:"POST"
				, url:"/get_user_Info"
				, data: {"userId":userId}
				
				, success : function(data) {
					if (data.code == 1) {
						$('#email span').text(data.email);
						$('#name span').text(data.name);
						$('#loginId span').text(data.loginId);
						$('#del-user-btn').data("userId", data.userId);
					}
				}
			});
		});
		
		
		$('#del-user-btn').on('click', function() {
			let userId = $('#del-user-btn').data("userId");			

			$.ajax({
				type:"POST"
				, url:"/delete_user"
				, data: {
					"userId":userId
				}
			
				, success : function(data) {
					if (data.code == 1) {
						alert("다음에 또 만나요.");
						location.reload(true);
					}
				}
			});
		});
	});
</script>