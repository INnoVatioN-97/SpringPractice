
## 알게된 점
- 빌드 (프로젝트 루트 경로에서)
  - windows
    - gradle.bat build
  - macOS / Linux
    - ./gradlew build
- 위 과정으로 생긴 빌드파일 (이 프로젝트에선 jar로 빌드됨) ./build/libs 내에 있다. 이 파일을 실행하기 위해서는 해당 경로로 가서 java -jar [buildFile.jar] 를 실행하면 해당 빌드파일이 실행된다.
  - 이 방식을 이용해 aws ec2, heroku와 같은 서버에 jar파일을 올려만 두면 배포 싹- 가능.