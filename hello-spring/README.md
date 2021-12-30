김영한님 스프링 입문 강의 따라해보기
=============

## 알게된 점
### Spring 웹개발의 기초 구성
- 정적 페이지 
  - index.html과 같이 변화하지 않는 페이지
- MVC와 템플릿 엔진
  - 템플릿 엔진: php, jsp와 같은 친구들. 
    - 쉽게 보면, 통짜 .html 파일을 뿌리는게 아니라 서버쪽에서 좀 오밀조밀하게 프로그래밍해서 동적으로 뿌려주는 것. (jsp에서 여러 <% ~~ 와 같은걸 적어줬었는데, 그런 것들이 가공되어 html로 뿌려짐.)
  - MVC (Model - View - Controller)
    - 저렇게 페이지를 가공해주는 놈 : Controller
    - 그 페이지가 보여지는 놈 : View
    - ... 좀 더 공부해보자.
  - API (Application Programming Interface)
    - 나 허구헌날 잘하는거.

## 꿀팁
- 빌드 (프로젝트 루트 경로에서)
  - windows
    - gradle.bat build
  - macOS / Linux
    - ./gradlew build
- 위 과정으로 생긴 빌드파일 (이 프로젝트에선 jar로 빌드됨) ./build/libs 내에 있다. 이 파일을 실행하기 위해서는 해당 경로로 가서 java -jar [buildFile.jar] 를 실행하면 해당 빌드파일이 실행된다.
  - 이 방식을 이용해 aws ec2, heroku와 같은 서버에 jar파일을 올려만 두면 배포 싹- 가능.


- 빌드 꼬였을 때
  - ./gradlew clean 을 하면 build 폴더가 싸그리 날라가니 다시 빌드할 때 오류 없.


- 테스트케이스 작성
  - 코드로 코드를 검증한다. = JUnit이라는 프레임워크를 사용해 굳이 main 메소드를 통해 번거롭게 실행하지 않고도 내 코드를 정상작동하는지 확인할 수 있다.
  - 테스트를 원하는 클래스에 커서를 두고 CMD + Shift + T 를 하면 Create new Test 라는 기능을 사용 가능. 더 쉽게 테스트 쌉가능.


- 툴팁 표시
  - cmd+p 를 누르면 툴팁 표시됨. (이 줄에 뭘 더 넣을 수 있는지)


- 리턴값 받는 객체 만드는 단축키
  - cmd + option + V 하면 해당 메소드의 리턴값을 받는 변수를 만들어줌.


- inline return 단축키
  - 예를 들어 Member member = ~~~ ; return member; 와 같은 상황에서 cmd + option + N 을 누르면 바로 return new Member(~~~) 처럼 코드를 줄일 수 있다.