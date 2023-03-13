<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="login-right col-6">
	<div class="access-box bolder col-6">
		<div class="text-center my-2">
			<div class="logo-text">MyRoom</div>
			<i>방을 구할때도? 내놓을때도? 마이룸~!</i>
		</div>
		<form class="signInFormUser" method="post" action="/myroom/sign_up">
			<div class="login d-flex justify-content-center">
				<div class="col-8">
					<div class="input-group my-1">
						<input type="email" name="email" class="email form-control" placeholder="이메일">
						<div class="input-group-append">
							<button type="button" class="send-email form-control">인증번호 발송</button>
						</div>
					</div>
					<div class="input-group my-1">
						<input type="text" name="authentication-number" class="authentication-number form-control" placeholder="인증번호">
						<div class="input-group-append">
							<button type="button" class="check-btn form-control">확인</button>
						</div>
					</div>
					<div class="after-checked d-none">
						<div id="">password</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	
	<!-- 페이지 이동 -->
	<div class="sign-up-box d-flex justify-content-center align-items-center">
		<span class="mr-2">계정을 찾으셨나요?</span>
		<a href="/myroom/sign_in">로그인 하기</a>				
	</div>
</div>

<script>
	$(document).ready(function() {
		$('.form-switch').on('change', function() {
			if ($('#switchRealtor').attr('class') == 'd-none') {
				$('#switchRealtor').removeClass('d-none');	
				$('#switchUser').addClass('d-none');
				$('.signInFormRealtor').removeClass('d-none');	
				$('.signInFormUser').addClass('d-none');
			} else {
				$('#switchUser').removeClass('d-none');	
				$('#switchRealtor').addClass('d-none');	
				$('.signInFormUser').removeClass('d-none');
				$('.signInFormRealtor').addClass('d-none');	
			}
		});
		
		$('.check-btn').on('click', function() {
			let authenticationNumber = $('.authentication-number').val().trim();
			console.log(authenticationNumber);
			
			$('.after-checked').removeClass('d-none')
		});
	});
</script>