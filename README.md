## 주제 선정 이유
- 프롭테크(Proptech)에 평소 관심이 많았기 때문에 첫 Web 개발 프로젝트로 가장 관심이 있었던 분야를 주제로 선정하고자 하였습니다.


## project_myroom 개요
본 프로젝트는 부동산 중개 웹사이트인 직방을 모티브로 하여 개발한 프로젝트로, 부동산 정보 검색 및 등록, 방문 예약을 제공합니다.
- 파이썬 데이터 크롤링 및 WordCloud
- MySql 관계형 데이터 베이스 모델링
- SpringBoot 프레임워크
- 공공API "국토교통부_부동산중개업정보서비스” 연동 서비스
- 카카오맵 API 부동산 매물 Marker생성 및 시각화
- MyBatis JDBC연동
- AWS EC2 인스턴스 배포

## 서비스 이용
### AWS domain
- [서비스 링크](http://3.133.119.173:8080/myroom)
### Test ID
- 일반유저(Id/password) : user/user
- 중개사 (Id/password) : realtor_gangnam/realtor_gangnam

## 아키텍쳐
- 전반적인 매물 정보는 직방 API를 파이썬으로 Crawling 하여 DB에 업로드 하여 좀 더 실제 서비스와 가깝게 Web을 설계해 보았습니다.
![architecture](https://github.com/JungHyunMoon/project_myroom/assets/120004247/cdf5507a-329b-483e-91fc-1989b2439e5e)

## Gantt Chart
- ProjectLibre 프로젝트 추적 관리 프로그램을 활용하여 일정표를 작성하였습니다.
![gantt chart](https://github.com/JungHyunMoon/project_myroom/assets/120004247/d86812b6-97eb-44af-8b3d-d5600f1881c9)

## ERD Diagram
- 관계형 데이터베이스를 설계하고 이를 실제 개발 환경에 적용함에 따라 이론적으로 배운 데이터 모델링과 실무에서 설계하는 바가 다르다는 점을 깨달았습니다. 예를 들자면 참조키(Foriegn key)는 현업에서 설정하지 않는다거나 과도한 정규화는 지양한다 같은 것들이였습니다.

## Data Crawling
- geohash map 형태로 나눠져 있는 데이터를 **크롤링** 하여 하단 이미지에 위치한 매물들을 확인 할 수 있었습니다. 각 geohash에 분류되어 있는 직방 API를 **request** 방식으로 호출하여 **pandas** 모듈로 DataFrame화 했습니다
![geohash](https://github.com/JungHyunMoon/project_myroom/assets/120004247/9e5144a3-8347-491d-b516-cb7252097e8f)
- 크롤링 한 데이터 중 title에 해당하는 데이터는 형태소 분석을 통해 wordCloud  시각화를 진행하였고 결과물을 main화면에 노출시켰습니다.
![main](https://github.com/JungHyunMoon/project_myroom/assets/120004247/6ea7367a-c0bd-4ecc-972d-c2bd5c2600a5)
- 자세한 데이터 수집코드는 마크다운파일를 첨부하겠습니다
[MoonProject.md](https://github.com/JungHyunMoon/project_myroom/files/10977262/MoonProject.md)

## 핵심 구현 기술 소개
**회원가입**
- 회원은 서비스를 이용하는 일반 유저와 중개 업무를 하는 중개사로 나누어서 회원가입을 할 수 있도록 구현하였습니다.
    - 일반 유저에 경우에는 MD5 방식으로 암호화를 진행했습니다.
    - 중개사의 경우에는 **spring security** 통해 BCrypt 방식으로 비밀번호 암호화를 진행하였습니다.
- **공공API** ***“[국토교통부_부동산중개업정보서비스](https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15058720)”*** 를 활용하여 실제 등록된 중개사만 인증 절차를 거친 후에 가입 할 수 있도록 구현하였습니다.
![회원가입](https://github.com/JungHyunMoon/project_myroom/assets/120004247/b7fee911-d150-4ecb-9e92-6d85dc3ac129)

**매물보기**
- 카카오맵 API
    - real_estate 테이블에 jibunAddress에 해당하는 데이터를 카카오 API **geocoder**를 통해 주소 → 위도 경도로 변환하고 변환한 데이터set을 key:value 형태로 저장하여 마커를 생성할 선행작업을 하였습니다.
    - 카카오 맵 API를 활용하여 서버DB로부터 검색창 키워드를 기준으로 데이터리스트를 받아 매물 위치에 마커 생성하고 해당 마커에 여러가지 이벤트(click, mouseover, mouseout)를 추가하였습니다
    - mouseover 시에 해당 데이터에 제목에 해당하는 column(real_estate.title)를 infowindow로 노출시켰습니다.
![매물보기](https://github.com/JungHyunMoon/project_myroom/assets/120004247/a4be8707-21c5-42bd-a590-1e6e68937a41)

**중개사 매물 확인 및 관리**

- 로그인시에 중개인 api로부터 로그인한 중개사의 사무실의 지역을 session에 “local2”에 담은 후에 해당하는 매물중 아직 중개사에게 배정되지 않은 (realtorId = null) 매물만 확인 할 수 있습니다.
- 매물 접수하기로 매물을 인수한 후에는 해당 매물을 일반 유저들이 볼때 노출 시킬 중개사 메모(realtor_comment)를 수정하고 매물을 확인하고 싶은 고객의 예약을 관리 할 수 있는 중개 관리 화면으로 구성하였습니다.
![중개관리](https://github.com/JungHyunMoon/project_myroom/assets/120004247/82e82b2c-46d1-4342-a9dd-e170d7f916d6)

## TroubleShooting

- Crawling 데이터 issue
    - 직방으로부터 크롤링한 데이터를 local DB에는 문제 없이 insert하였으나 다시 local DB에서 AWS server로 이전하는 과정에서 처음에는 Table Data Export Wizard로 exel 파일을 받았고 해당 파일을 pscp 명령어를 통해 aws서버로 업로드 하였습니다. LOAD DATA INFILE 명령어를 사용하여 엑셀파일을 insert하는 과정에서 방대한 데이터속에 하나씩 도메인 무결성(domain integrity)을 위배하여 insert에 번번히 실패 하였습니다.
    - 방법을 계속 찾던중 exel file로 업로드 하는것이 아닌 mysqldump로 .sql파일로 DB를 백업하여 서버 이전할 수 있는 명령어가 있다는 사실을 깨달아 .sql 파일로 백업하여 SOURCE /path/fileName.sql 로 서버 Data 이전 문제를 해결하였습니다.
- 카카오맵 API 활용 issue
    - api를 호출하여 사용하는것이 처음이라 js의 동작 방식을 완벽히 이해하지 못한 상태에서 개발을 시작하였고 초기에 주소 → 위도/경도 로 변환한 결과를 임시 변수 var에 담아서 사용 하려 했으나 빈번히 npe error 를 마주하게 되었습니다. 여러 시도와 api 동작 방식을 확인한 결과 addMarker함수 안에 이중으로 addressSearch callback함수를 이중으로 중첩하는 방법을 사용하여 marker생성 기능을 구현하였습니다.
    - 너무 많은 매물이 realEstateList에 노출되는 탓에 매물 marker를 눌렀을때 해당 매물의 스크롤 위치로 가는 방법에 있어서 많은 실패가 있었고 고민한 끝에 zIndex에 해당 매물의 id 를 담고 click 이벤트에 zindex를 담아 동일한 값을 id로 가지고 있는 div를 scrollTop으로 이동시키는 방식을 채택하였습니다. 이후에 ui를 다듬는 과정에서 zindex를 임시로 준 탓에 mouseOver 했을때 infowindow가 marker에 가리는 현상이 발생하였고 현재는 임시로 makeoverListener에 강제로 zindex를 999999를 줘서 강제로 해결하였지만 Z축의 interface에 대한 충분한 이해가 있었다면 이렇게 하지 않았을 것 같다는 아쉬움이 있습니다

## 아쉬운점
- session 기능은 HttpSession 으로 간단하게 session.add와 session.remove로 단순한 세션 관리를 구현하였지만 다음 프로젝트 부터는 spring security에서 제공하는 session관리 기능으로 구현했다면 전체 config설정으로 관리하였다면 어땠을까 하는 아쉬움이 있습니다.
- 처음 아키텍쳐를 설계할 당시 crawling data를 동적으로 서버로 가져와 개발하고 싶었는데 이는 k8s 에서 제공하는 Object를 반복하여 사용하는 ****크론잡(CronJob)을**** 이용해야 하는데 이에 대한 준비가 되어있지 않아 추후에 해당 기능을 추가하고 싶습니다.
