# JWT

https://velog.io/@tjdals9638/Spring-Boot-2.7.0-Security-Jwt-구현-1 참고
https://aljjabaegi.tistory.com/659 참고2

## JWT란?
Json 객체를 사용해서 토큰 자체에 정보들을 저장하고 있는 웹 토큰이라고 생각하면 됩니다.

## JWT 구성
- Header
Signature를 해싱하기 위한 알고리즘 정보들이 담겨있음
- Payload
서버와 클라이언트가 주고받는 실제 데이터가 있음.
- Signature
토큰의 유효성 검증을 위한 문자열

## 장점
- 중앙의 인증서버, 데이터 스토어에 대한 의존성 없음(Session을 쓰지 않기때문에 스프링 Session 저장소를 사용하지 않는다. => 서버 부담이 줄어든다!)
- 보통 Base64 URL Safe Encoding > URL, Cookie, Header 모두 사용 가능

## 단점
- Payload의 정보가 많아지면 네트워크 사용량이 기하급수적으로 늘어나므로 데이터 설계 고려가 필요함.
- 단일키 문제가 존재하여서 키가 유출되면 시스템 전체가 위험해짐.
