<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="login-right col-6">
	<div class="access-box bolder col-6">
		<div class="text-center my-2">
			<div class="logo-text">MyRoom</div>
			<i>방을 구할때도? 내놓을때도? 마이룸~!</i>
		</div>
		<div class="form-check form-switch d-flex justify-content-center col-6">
			<input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheck">
			<label class="" id="switchUser" for="flexSwitchCheck">평회원</label>
			<label class="d-none" id="switchRealtor" for="flexSwitchCheck">중개인</label>
		</div>
		<!-- 일반 유저 회원가입 -->
		<form class="signInFormUser" method="post" action="/myroom/sign_up">
			<div class="login d-flex justify-content-center">
				<div class="form-group col-8">
					<input type="text" name="loginId" class="loginId form-control mt-1" placeholder="아이디">
					<div class="input-group mt-2">
						<input type="password" name="password" class="password form-control" placeholder="비밀번호">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								<i class="fa fa-eye-slash fa-lg"></i>
							</button>
						</div>
					</div>
					<div class="input-group mt-2">
						<input type="password" name="password" class="password-confirm form-control" placeholder="비밀번호 확인">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								<i class="fa fa-eye-slash fa-lg"></i>
							</button>	
						</div>
					</div>
					<div class="mt-2">
						<input type="email" name="email" class="email form-control" placeholder="이메일">
					</div>
					
					<button type="submit" class="btn btn-info border-radius mt-2 w-100">회원가입</button>
					<div class="text-center my-2">
						<a href="/myroom/find_my_id">비밀번호를 잊으셨나요?</a>
					</div>
				</div>
			</div>
		</form>
		
		<!-- 중개사 회원가입 -->
		<form class="signInFormRealtor d-none" method="post" action="/myroom/sign_up">
			<div class="login d-flex justify-content-center">
				<div class="form-group col-8">
					<input type="text" name="name" class="name form-control" placeholder="중개인 이름">
					<div class="input-group my-1">
						<input type="text" name="office" class="office form-control" placeholder="법정 사무실 이름">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								확인
							</button>
						</div>
					</div>
					
					<input type="text" name="loginId" class="loginId form-control mt-1" placeholder="아이디">
					<div class="input-group my-1">
						<input type="password" name="password" class="password form-control" placeholder="비밀번호">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								<i class="fa fa-eye-slash fa-lg"></i>
							</button>
						</div>
					</div>
					<div class="input-group my-1">
						<input type="password" name="password" class="password-confirm form-control" placeholder="비밀번호 확인">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								<i class="fa fa-eye-slash fa-lg"></i>
							</button>
						</div>
					</div>
					<div class="mt-2">
						<input type="email" name="email" class="email form-control" placeholder="이메일">
					</div>
					
					<button type="submit" class="btn btn-info border-radius mt-2 w-100">회원가입</button>
					<div class="text-center my-2">
						<a href="/myroom/find_my_id">비밀번호를 잊으셨나요?</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	
	<!-- 페이지 이동 -->
	<div class="sign-up-box d-flex justify-content-center align-items-center">
		<span class="mr-2">계정이 있으신가요?</span>
		<a href="/myroom/sign_in">로그인 하기</a>				
	</div>
</div>

<script>
	$(document).ready(function() {
		// 단순 removeClass, addClass
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
		
		// toggleClass 로 간단하게
		$('i').on('click',function(){
			let inputTag = $(this).closest('div').prev();
	        inputTag.toggleClass('active');
	        if(inputTag.hasClass('active')){
	        	inputTag.attr('type', 'text')
	        }else{
	        	inputTag.attr('type', 'password')
	        }
	    });

	});
</script>