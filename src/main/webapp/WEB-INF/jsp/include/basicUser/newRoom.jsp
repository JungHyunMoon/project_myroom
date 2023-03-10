<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<div class="room-info-box">
	<h4>위치정보</h4>
	<hr>
	<form id="newRoomForm" action="/add_room" enctype="multipart/form-data">
		<input class="d-none" id="local1" name="local1">
		<input class="d-none" id="local2" name="local2">
		<input class="d-none" id="local3" name="local3">
<!-- 		<input class="d-none" id="local4" name="local4"> -->
		<table class="table">
			<tbody>
				<tr>
					<th>주소</th>
					<td class="col-10">
						<div>
							<input type="text" class="form-control w-10" id="roomAddress" name="jibunAddress" onclick="findAddress()" placeholder="주소">
						</div>
						· 주소와 도로명 주소 모두 검색이 가능합니다.<br> 
						· 주소 입력 시에는 동/읍/면 으로 검색해 주세요. 예) 자곡동, 동읍면, 신월읍<br>
						<div id="infoMap" class="d-none"></div>
						<div class="">
							· 상단 지도에 보이는 마커의 위치가 다소 부정확할 수 있습니다.
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		
		<h4>사진 등록</h4>
		<hr>
		<p class="">
			· 등록 버튼을 이용하여 사진 최소 5장 최대 15장 까지 등록할 수 있습니다.<br><i class="bi bi-image"></i>
        	· 사진을 여러장 선택하여 한번에 등록도 가능합니다.
        </p>
        <div class="d-flex">
        	<div>
        		<a href="#" class="fileUploadBtn">
        			<img width="35" id="thumbNail" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png">
        		</a>
        		<input type="file" id="img1" class="imageFile d-none" accept=".gif, .jpg, .png, .jpeg">
        	</div>
        	<div>
        		<a href="#" class="fileUploadBtn">
        			<img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png">
        		</a>
        		<input type="file" id="img2" class="imageFile d-none">
        	</div>
        	<div>
        		<a href="#" class="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
        		<input type="file" id="img3" class="imageFile d-none">
        	</div>
        </div><br>
			
		<h4>상세 정보</h4>
		<hr>
		<table class="table">
			<tbody>
				<tr>
					<th>보증금</th>
					<td class="">
						<input type="text" class="form-control" id="deposit" name="deposit" placeholder="만원 단위로 입력해주세요.">
						<small>※무보증일 경우, 0을 입력 하세요.</small>
					</td>
				</tr>
				<tr>
					<th>월세</th>
					<td>
						<input type="text" class="form-control" id="monthRentPrice" name="monthRentPrice" placeholder="만원 단위로 입력해주세요.">
						<small>※전세일 경우, 0을 입력 하세요.</small>
					</td>
				</tr>
				<tr>
					<th>건축물 용도</th>
					<td class="d-flex">
						<input type="text" class="form-control" id="residence_type" name="residence_type" placeholder="예) 공동주택, 다세대주택">
					</td>
				</tr>
				<tr>
					<th>크기</th>
					<td class="d-flex">
						<input type="text" class="form-control" id="area" name="area" placeholder="평 단위로 입력해주세요">
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td class="d-flex">
						<input type="text" class="form-control" id="title" name="title" placeholder="지도에 표시될 썸네일 제목을 입력해주세요">
					</td>
				</tr>
				<tr>
					<th>설명</th>
					<td class="d-flex">
						<textarea name="description" class="form-control" rows="6" placeholder="해당 방에 대한 특징과 소개를 최소 50자 이상 입력해야 합니다.
방의 위치와 교통, 주변 편의시설, 방의 특징과 장점, 보안시설, 옵션, 주차, 전체적인
방의 느낌 등을 작성해 주세요."></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<button type="submit" class="btn-submit btn btn-info w-100">등록하기</button>
	</form>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a12e90f6a3acfbdf4b442ae4229a9790&libraries=services"></script>

<script>
	var mapContainer = document.getElementById('infoMap'), // 지도를 표시할 div
	mapOption = {
	    center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};
	
	//지도를 미리 생성
	var map = new daum.maps.Map(mapContainer, mapOption);
	//주소-좌표 변환 객체를 생성
	var geocoder = new daum.maps.services.Geocoder();
	//마커를 미리 생성
	var marker = new daum.maps.Marker({
	position: new daum.maps.LatLng(37.537187, 127.005476),
	map: map
	});
	var markerImage = new kakao.maps.MarkerImage(
    	    src = '/static/img/house.svg',
    	    new kakao.maps.Size(24, 24), new kakao.maps.Point(13, 34));
    	marker.setImage(markerImage);
	
	
	function findAddress() {
		new daum.Postcode({
		    oncomplete: function(data) {
		        var address = data.address; 
		        var jibunAddress = data.jibunAddress; // 지도에 쓰일 변수
		        if (jibunAddress == "" || jibunAddress == null) {
		        	jibunAddress = data.autoJibunAddress;
		        }
		        var local1 = data.sido;
		        var local2 = data.sigungu;
		        var local3 = data.bname
		        // 주소 정보를 해당 필드에 넣는다.
		        document.getElementById("roomAddress").value = jibunAddress;
		        document.getElementById("local1").value = local1;
		        document.getElementById("local2").value = local2;
		        document.getElementById("local3").value = local3;
		        console.log(data);
				
		        // 주소로 상세 정보를 검색
		        geocoder.addressSearch(data.address, function(results, status) {
		            if (status === daum.maps.services.Status.OK) {
		                var result = results[0];
		
		                var coords = new daum.maps.LatLng(result.y, result.x);
		                $('#infoMap').removeClass("d-none");
		                map.relayout();
		                map.setCenter(coords);
		                // 마커를 결과값으로 받은 위치로 옮긴다.
		                marker.setPosition(coords)
		            }
		        });
		    }
		}).open();
	}
	
	$('document').ready(function() {
		$('.fileUploadBtn').on('click', function() {
			$(this).next().click();
		});
		
		$(".imageFile").on('change', function(e) {
			let fileName = e.target.files[0].name;
			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != 'jpg' && ext != 'jpeg' && ext != 'gif' && ext != 'png') {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$(this).val(''); // 파일 태그에 실제 파일 제거
				$(this).prev().children().attr('src', "https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png")
				return;
			}
			
			console.log($(this).val());
			$(this).prev().children().attr('src', $(this).val());
// 			$.ajax({
// 				type : "POST"
// 				, url : "/uploadImage"
// 		        , processData: false
// 		        , contentType: false
// 		        , context: this
// 				, data : formData
				
// 				, success : function(data) {
					
// 					console.log(data.imagePath);
// 					$(this).prev().children().attr('src', data.imagePath);
// 				}
// 				, error : function(e) {
// 					alert('실패');
// 				}
// 			});
		});
		
		
		
		$('#newRoomForm').on('submit', function(e) {
			e.preventDefault();
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			alert(params);
			
			$.post(url, params) 
			.done(function(data) {
				if (data.code == 1) {
					alert("등록 완료.")
					window.location = "/myroom/user/map?local=" + data.keyword
				} else {
					alert("등록 실패.")
				}
			});
		})
	});
</script>