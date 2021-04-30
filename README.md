# 휴가 신청 시스템
- 개인프로젝트</br>
Goal </br>
  - Spring Security, Spring Data JPA 적용
  - REST API 설계

## 1. 요구 사항 & 기능 명세
요구사항
* 완전히 동작하는 웹 어플리케이션 또는 API 서버 작성
* DBMS 사용</br>

기능명세
* 사용자 모델과 로그인 시스템
* 사용자에게는 매년 15일의 연차가 부되며 사용자는 연차/반차(0.5일)/반반차(0.25일)에 해당하는 휴가를 신청할 수 있음
* 휴가 신청시 시작일, 종료일(반차/반반차의 경우는 필요없음), 사용 일수, 코멘트(선택 항목)를 입력
* 휴가 신청시 남은 연차를 표시하며 연차를 모두 사용한 경우 휴가를 신청할 수 없음
* 추가 기능: 사용 일수를 입력하는 대신 시작일, 종료일을 가지고 공휴일을 제외하고 계산 가능 
* 아직 시작하지 않은 휴가는 취소 가능

## 2. Solution strategy
* Spring Security를 이용해 비밀번호 암복호화, 로그인 시스템을 구현한다.
* 일수 처리</br>
  - 신청한 휴가 시작일과 종료일에 포함된 모든 날짜에 대해 1일 단위로 휴가 레코드를 생성하여 수정/취소를 관리한다. 이미 신청한 날짜에 대해 재신청은 불가하며 삭제 후 재신청해야 한다.
  - 신청한 사용일수에 연차/반차(0.5)/반반차(0.25)를 입력하고 이를 기준으로 남은 연차를 관리한다.
  - 시작하지 않은 휴가의 기준은 당일 날짜이며, 따라는 취소는 휴가 전날까지만 가능하다.

## 3. Prerequisite 
   * IDE : STS-4.0.1.RELEASE
   * Java 1.8 + / Spring Boot 4.1.0 + / JPA / Lombok / Gradle / Spring Security
   * Database : mysql

## 4. Architecture
##### APIs 
 * 데이터 타입 : JSON
 * Swagger 문서 잠조 : http://localhost:8080/swagger-ui.html
 * 기본 동작 순서 : 유저 생성 -> 로그인 -> 휴가 생성 -> 휴가 조회 / 수정 / 삭제

#### Controller - VacationController
- GET: /vacation/detail/{seq} <br/> 특정 휴가 조회 <br/>
- GET: /vacation/list <br/> 사용자가 신청한 모든 휴가 목록 조회 <br/>
- PUT /vacation/resources <br/> 휴가 코멘트 수정 <br/>
- POST: /vacation/resources <br/> 새로운 휴가 생성 <br/>
- DELETE : /vacation/resources/{seq} <br/> 휴가 삭제 <br/>
#### Controller - UserController
- POST : /users/auth <br/> 로그인 <br/>
- POST : /users/new <br/> 유저 생성 

#### Services
##### 1) VacationService  휴가 로직 처리
##### 2) UserService.    사용자 로직 처리 

#### Repositories
##### 1) VacationRepository
`Vacation` 조회, 저장, 삭제, 수정
<br/>
##### 2) UserRepository
`UserInfo` 조회, 저장

#### Entities
1) Time
``` 
    private LocalDateTime fstEntDt;  // @CreatedDate 자동 생성 시간
    private LocalDateTime lstUpdDt;  // @LastModifiedDate 자동 수정 시간    
```

2) Vacation
``` 
    private Long vacSeq;    // 휴가 일련 번호 자동 생성
    private String vacDt;   // 휴가 일자
    private Double vacDs;   // 휴가 일수 (1 / 0.5 / 0.25)
    private String usrId;   // 사용자 아이디
    private String vacRmk;  // 코멘트    
```

3) UserInfo
```
    private String usrId;            // 사용자 아이디
    private String usrPw;            // 사용자 비밀번호
    private String usrName;          // 사용자 이름
    private String usrRole;          // 사용자 권한  
    private Double availableVacDs;   // 사용 가능 연차 일수
    private Double enrolledVacDs;    // 신청 연차 일수 
```
