## 프로젝트 실행 전 h2 database 활성화 필요
~/h2/bin/h2.sh
```
~/h2/bin$ ./h2.sh
```
---
## jpa-03 : 도메인 분석 설계

## jpa-02 : h2 database 설정
### 사이트에서 다운로드 후 로컬에 압축해제
https://www.h2database.com/html/main.html  
### 유저 홈 경로의 Documents 에 압축을 해제 함.
~/Documents/h2
### h2 database 실행(Mac)
```bash
$ sudo chmod +x h2.sh 
$ ./h2.sh
```
### 관리화면(웹) 접속
http://localhost:8082/  
최초 : 세션유지하며 파일생성 jdbc url => jdbc:h2:~/jpashop  
두번째 이후 : jdbc url => jdbc:h2:tcp://localhost/~/jpashop


## jpa-01 : 프로젝트 기본 설정을 하고 테스트 합니다.
- 확인 url  
. http://localhost:8080  
. http://localhost:8080/hello  
 
- 라이브러리 디펜던시 확인은 콘솔창에 아래의 명령어로 확인 가능합니다.  
./gradlew dependencies  
