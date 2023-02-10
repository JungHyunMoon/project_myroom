<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div id="map" style="width:100%;height:100%;"></div>

<script>
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(37.51148310935, 127.06033711446), //지도의 중심좌표(삼성역).
		level: 4 // 확대레벨 7레벨
	};
	
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 마커를 표시할 위치와 title 객체 배열입니다 
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
	var realEstateList = [
	    {
	    	zIndex: 1,
	    	address: '서울특별시 강남구 영동대로 506',
	    	title: '<div style="width:150px;text-align:center;padding:6px 0;">코엑스</div>', 
	        latlng: new kakao.maps.LatLng(37.51271095558583, 127.05878829473788)
	    },
	    {
	    	zIndex: 2,
	    	address: '서울 강남구 영동대로 506',
	    	title: '<div style="width:150px;text-align:center;padding:6px 0;">신라스테이</div>', 
	        latlng: new kakao.maps.LatLng(37.509913338995375, 127.0631491089418)
	    },
	    {
	    	zIndex: 3,
	    	address: '서울 강남구 테헤란로 517',
	    	title: '<div style="width:150px;text-align:center;padding:6px 0;">백화점</div>', 
	        latlng: new kakao.maps.LatLng(37.50863341861204, 127.05976908971027)
	    }
	],
    selectedMarker = null;
	
	for (var i = 0; i < realEstateList.length; i ++) {
		var position = realEstateList[i].latlng,
			title = realEstateList[i].title,
			zIndex = realEstateList[i].zIndex
		
		addMarker(position, title, zIndex);
	};
	
	function addMarker(position, title, zIndex) {

		 // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: position, // 마커의 위치
	        zIndex: zIndex	// z-index : db에 id
	    });
	    
	    var markerImage = new kakao.maps.MarkerImage(
	    	    src = '/static/img/house.svg',
	    	    new kakao.maps.Size(31, 35), new kakao.maps.Point(13, 34));
	    	marker.setImage(markerImage);
	
	    // 마커에 표시할 인포윈도우를 생성합니다 
	    var infowindow = new kakao.maps.InfoWindow({
	    	content: title // 인포윈도우에 표시할 내용
	    });
	
	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	    kakao.maps.event.addListener(marker, 'click', function() {
	        alert(marker.getZIndex());
	    });
	}
	    
	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
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