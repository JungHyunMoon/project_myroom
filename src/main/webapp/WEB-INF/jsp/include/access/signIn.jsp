<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="login-right col-6">
	<div class="access-box bolder col-6">
		<div class="text-center mt-3">
			<p class="logo-text">MyRoom</p>
			<i>방을 구할때도? 내놓을때도? 마이룸~!</i>
		</div>
		<form id="signInForm" method="post" action="/myroom/sign_in">
			<div class="login d-flex justify-content-center">
				<div class="form-group col-8">
					<input type="text" id="loginId" name="loginId" class="form-control mt-3" placeholder="아이디">
					<input type="password" id="password" name="password" class="form-control mt-2" placeholder="비밀번호">
					<button type="submit" class="btn btn-info border-radius mt-4 w-100">로그인</button>
					<div class="text-center mt-2">
						<a href="/myroom/find_my_id">비밀번호를 잊으셨나요?</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="sign-up-box d-flex justify-content-center align-items-center">
		<span class="mr-2">계정이 없으신가요?</span>
		<a href="/myroom/sign_up">가입하기</a>				
	</div>
</div>

<script>
// 	$(document).ready(function() {
// 		$('#signInForm').submit()
// 	});
</script>