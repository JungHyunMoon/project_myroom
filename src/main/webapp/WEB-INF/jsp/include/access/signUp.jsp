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
		<form class="signUpFormUser" method="post" action="/sign_up_user">
			<div class="login d-flex justify-content-center">
				<div class="form-group col-8">
					<div class="input-group my-1">
						<input type="text" id="userLoginId" name="loginId" class="loginId form-control"  data-certified=false placeholder="아이디">
						<div class="input-group-append">
							<button type="button" class="check-btn form-control">
								중복확인
							</button>
						</div>
					</div>
					<div class="input-group my-1">
						<input type="password" id="userPassword" name="password" class="password form-control" placeholder="비밀번호">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								<i class="fa fa-eye-slash fa-lg"></i>
							</button>
						</div>
					</div>
					<div class="input-group my-1">
						<input type="password" id="userPasswordConfirm" name="passwordConfirm" class="password-confirm form-control" placeholder="비밀번호 확인">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								<i class="fa fa-eye-slash fa-lg"></i>
							</button>	
						</div>
					</div>
					<div class="my-1">
						<input type="text" id="userName" name="name" class="name form-control" placeholder="이름">
					</div>
					<div class="my-1">
						<input type="text" id="phoneNumber" name="phoneNumber" class="phoneNumber form-control" placeholder="전화번호 ex) 010-1234-5678">
					</div>
					<div class="my-1">
						<input type="email" id="userEmail" name="email" class="email form-control" placeholder="이메일">
					</div>
					
					<button type="submit" class="btn btn-info border-radius mt-2 w-100">회원가입</button>
					<div class="text-center my-2">
						<a href="/myroom/find_my_id">비밀번호를 잊으셨나요?</a>
					</div>
				</div>
			</div>
		</form>
		
		<!-- 중개사 회원가입 -->
		<form class="signUpFormRealtor d-none" method="post" action="/sign_up_realtor">
			<div class="login d-flex justify-content-center">
				<div class="form-group col-8">
					<input type="text" id="realtorName" name="name" class="name form-control" placeholder="중개인 이름">
					<div class="input-group my-1">
						<input type="text" id="registerNumber" name="registerNumber" class="registerNumber form-control" data-certified=false placeholder="법정 등록번호">
						<div class="input-group-append">
							<button type="button" class="registerNumberConfirm form-control">
								확인
							</button>
						</div>
					</div>
					<div class="input-group my-1">
						<input type="text" id="realtorLoginId" name="loginId" class="loginId form-control"  data-certified=false placeholder="아이디">
						<div class="input-group-append">
							<button type="button" class="check-btn form-control">
								중복확인
							</button>
						</div>
					</div>
					<div class="input-group my-1">
						<input type="password" id="realtorPassword" name="password" class="password form-control" placeholder="비밀번호">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								<i class="fa fa-eye-slash fa-lg"></i>
							</button>
						</div>
					</div>
					<div class="input-group my-1">
						<input type="password" id="realtorPasswordConfirm" name="passwordConfirm" class="password-confirm form-control" placeholder="비밀번호 확인">
						<div class="input-group-append">
							<button type="button" class="eye form-control">
								<i class="fa fa-eye-slash fa-lg"></i>
							</button>
						</div>
					</div>
					<div class="my-1">
						<input type="email" id="realtorEmail" name="email" class="email form-control" placeholder="이메일">
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
				$('.signUpFormRealtor').removeClass('d-none');	
				$('.signUpFormUser').addClass('d-none');
			} else {
				$('#switchUser').removeClass('d-none');	
				$('#switchRealtor').addClass('d-none');	
				$('.signUpFormUser').removeClass('d-none');
				$('.signUpFormRealtor').addClass('d-none');	
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
		
		$('.check-btn').on('click', function(e) {
			e.preventDefault();
			let loginId = $(this).parent().prev().val().trim();
			$.ajax({
				type:"POST"
				, url:"/isAlreadyExistId"
				, data: {"loginId":loginId}
				, context: this
				
				, success : function(data) {
					if (data.result == true) {
						alert("이미 존재하는 아이디입니다.");
						$(this).parent().prev().empty();
					} else if (data.result == false) {
						alert("가입 가능한 아이디입니다.");
						$(this).parent().prev().data("certified", true);
					}
				}
			})
		});
		
		$('.signUpFormUser').on('submit', function(e) {
			e.preventDefault();
			let loginId = $('#userLoginId').val().trim();
			let loginIdConfirm = $('#userLoginId').data('certified');
			let password = $('#userPassword').val();
			let passwordConfirm = $('#userPasswordConfirm').val();
			let name = $('#userName').val().trim();
			let phoneNumber = $('#phoneNumber').val().trim();
			let email = $('#userEmail').val().trim();
			
			if (loginId == "") {
				alert("아이디를 입력해 주세요.");
				return false;
			}
			if (loginIdConfirm == "") {
				alert("아이디 중복확인을 해주세요.");
				return false;
			}
			if (password == "") {
				alert("비밀번호를 입력해 주세요.");
				return false;
			}
			if (password != passwordConfirm) {
				alert("비밀번호가 다릅니다.");
				return false;
			}
			if (name == "") {
				alert("계정이름을 입력해 주세요.");
				return false;
			}
			if (phoneNumber == "") {
				alert("전화번호를 입력해 주세요.");
				return false;
			}
			if (email == "") {
				alert("이메일을 입력해 주세요.");
				return false;
			}
			
			let url = $(this).attr('action');
			console.log(url);
			let params = $(this).serialize();
			console.log(params);

			$.post(url, params)		// request
			.done(function(data) {	// response
				if (data.code == 1) {	// 성공
					alert("안녕하세요!")
					location.href="/myroom/sign_in";			
				} else {	// 실패
					alert("회원 가입에 실패하였습니다.");
				}
			});
		});
		
		$('.registerNumberConfirm').on('click', function() {
			let realtorName = $('#realtorName').val().trim();
			let registerNumber = $('#registerNumber').val();
			
			if (realtorName == "") {
				alert("이름을 입력해 주세요.");
				return false;
			}
			if (registerNumber == "") {
				alert("등록번호를 입력해 주세요.");
				return false;
			}
			
			$.ajax({
				type:"POST"
				, url:"/isRegisterdRealtor"
				, data: {"realtorName":realtorName, "registerNumber":registerNumber}
			
				, success : function(data) {
					if (data.result > 0) {
						alert("인증되었습니다")
						$('#registerNumber').data("certified", true);
						
					} else if (data.result == 0) {
						alert("등록되지 않은 번호입니다.")
						$('#registerNumber').data("certified", false);
					}
				}
			});
		});
		
		$('.signUpFormRealtor').on('submit', function(e) {
			e.preventDefault();
			let realtorName = $('#realtorName').val().trim();
			let realtorRegisterNumber = $('#realtorRegisterNumber').val();
			let isCertified = $('#registerNumber').data("certified");
			let realtorLoginId = $('#realtorLoginId').val().trim();
			let realtorPassword = $('#realtorPassword').val();
			let realtorPasswordConfirm = $('#realtorPasswordConfirm').val();
			let realtorEmail = $('#realtorEmail').val().trim();
			
			if (realtorName == "") {
				alert("이름을 입력해 주세요.");
				return false;
			}
			
			if (realtorRegisterNumber == "") {
				alert("등록번호를 입력해 주세요.");
				return false;
			}
			
			if (isCertified == false) {
				alert("등록번호 인증을 완료해주세요.");
				return false;
			}
			
			if (realtorLoginId == "") {
				alert("아이디를 입력해 주세요.");
				return false;
			}
			
			if (realtorPassword == "") {
				alert("비밀번호를 입력해 주세요.");
				return false;
			}
			
			if (realtorPassword != realtorPasswordConfirm) {
				alert("비밀번호가 일치하지 않습니다");
				return false;
			}
			
			if (realtorEmail == "") {
				alert("이메일을 입력해 주세요.");
				return false;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();

			$.post(url, params)		// request
			.done(function(data) {	// response
				if (data.code == 1) {	// 성공
					alert("안녕하세요!")
					location.href="/myroom/sign_in";			
				} else {	// 실패
					alert("회원 가입에 실패하였습니다.");
				}
			});
			
		});

	});
</script>