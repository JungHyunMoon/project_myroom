## project_myroom 개요
부동산 매물 중개 서비스
- 파이썬 데이터 크롤링 및 WordCloud
- MySql 관계형 데이터 베이스 모델링
- SpringBoot 프레임워크
- 공공API "국토교통부_부동산중개업정보서비스” 연동 서비스
- 카카오맵 API 부동산 매물 Marker생성 및 시각화
- MyBatis JDBC연동
- AWS EC2 인스턴스 배포

## 주제 선정 이유 및 개요
- 프롭테크(Proptech)에 평소 관심이 많았기 때문에 첫 Web 개발 프로젝트로 가장 관심이 있었던 분야를 주제로 선정하고자 하였습니다.
- 본 프로젝트는 부동산 중개 웹사이트인 직방을 모티브로 하여 개발한 프로젝트로, 부동산 정보 검색 및 등록, 방문 예약을 제공합니다.
- 프로젝트는 Spring Boot와 Mybatis, Jquery, Bootstrap을 사용하여 개발되었습니다.
- AWS EC2 인스턴스를 사용하여 배포되었습니다.

### AWS domain
http://3.135.197.208:8080/myroom

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

## Review
https://www.notion.so/MyRoom-ee1d0389b09b47e59e96fe67d8e3f645

## Data Crawling
[MoonProject.md](https://github.com/JungHyunMoon/project_myroom/files/10977262/MoonProject.md)
