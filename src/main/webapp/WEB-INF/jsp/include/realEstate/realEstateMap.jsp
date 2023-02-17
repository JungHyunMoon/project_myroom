<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="map" style="width:100%;height:100%;"></div>

<script>
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(37.512979489485815, 127.05300297037), //지도의 중심좌표(삼성중앙역).
		level: 4 // 확대레벨 7레벨
	};
	
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	var geocoder = new kakao.maps.services.Geocoder();
	
	var setCenter = "${keyword}";
	
	var callback = function(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	    	LatLng = new kakao.maps.LatLng(result[0].y, result[0].x);
	    	map.setCenter(LatLng);
	    }
	};

	geocoder.addressSearch(setCenter, callback);
	
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
	var realEstateList = [{
// 		zIndex: 1,
//     	address: '서울 강남구 영동대로 513',
//     	title: '<div style="width:150px;text-align:center;padding:6px 0;">코엑스</div>', 
	}];
	<c:forEach var="realEstate" items="${realEstateList}">
		realEstateList.push({
			zIndex : "${realEstate.zIndex}",
			address: "${realEstate.jibunAddress}",
			title: '<div style="width:150px;text-align:center;padding:6px 0;">${realEstate.title}</div>'
		});
	</c:forEach>
// 	console.log(realEstateList);
	
	for (var i = 0; i < realEstateList.length; i ++) {
		
		var address = realEstateList[i].address;
		var title = realEstateList[i].title;
		var zIndex = realEstateList[i].zIndex;
		
		addMarker(address, title, zIndex);
		
	};
	
	function addMarker(address, title, zIndex) {

		geocoder.addressSearch(address, function(result, status) {
			 
		    // 정상적으로 검색이 완료됐으면 
		     if (status == kakao.maps.services.Status.OK) {
		    	
		    	LatLng = new kakao.maps.LatLng(result[0].y, result[0].x);
				
				var marker = new kakao.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: LatLng, // 마커의 위치
			        zIndex: zIndex	// z-index : db에 id
			    });
				
				var markerImage = new kakao.maps.MarkerImage(
			    	    src = '/static/img/house.svg',
			    	    new kakao.maps.Size(16, 16), new kakao.maps.Point(13, 34));
			    	marker.setImage(markerImage);
			
			    // 마커에 표시할 인포윈도우를 생성합니다 
			    var infowindow = new kakao.maps.InfoWindow({
			    	content: title,	 // 인포윈도우에 표시할 내용
			    	zIndex: 9999999
			    });
			
			    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
			    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
			    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
			    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
			    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
			    kakao.maps.event.addListener(marker, 'click', function() {
			        var realEstateId = marker.getZIndex();
			        alert(realEstateId);
// 			        var location = document.querySelector(
// 			        	$("div").attr("z-index", realEstateId)		
// 			        ).offsetTop;
// 			        alert(location);
			    });
		     };
		});	
	}
	    
	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
	    infowindow.setZIndex(9999999999); // 임시
	    return function() {
	        infowindow.open(map, marker);
	    };
	}
	
	// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(infowindow) {
	    return function() {
	        infowindow.close();
	    };
	}
	
</script>