# SPRINGBOOT-VUE-STARTER-KIT-BACKEND

- SPRINGBOOT-VUE-STARTER-KIT-BACKEND 개발 관련 내용을 기술한다.

# 이력

- v0.0.1
  - 2021.01.16
    - 최초 등록
    - 개발프로세스, 소스코드관리 방법
  - 2021.01.23
    - 라이브러리 정리
    - 개발 구조 정리
  - 2021.01.24
    - JWT 개발
  - 2021.01.30
    - 회원, 공지사항 CRUD 개발
    - Swagget UI 적용

# 구성

  <!-- blank line -->

- 환경

  - windows 10, mac
  - visual studio code
  - github v2.1.3
  - mariadb v10.5
  - node v12.5.0
  - npm v6.9.0
  - openjdk v1.8

  ## <!-- blank line -->

- 라이브러리
  - io.jsonwebtoken:jjwt / 0.9.1
  - springfox-swagger2 / 2.9.2
  - springfox-swagger-ui / 2.9.2
  - javax.servlet:jstl / 1.2
  - jackson-dataformat-yaml / 2.10.3
  - com.google.code.gson / 2.7

# 실행

- Visual Studio Code 실행
- mariadb 실행
- springbootbackend db 생성
- ./gradlew bootRun

# 배포 ( war )

- ./gradlew clean build
- ./gradlew bootwar

# 주석

- TODO : 좀더 최적화시키고 리팩토링시킬 수 있을만한 구석이 있을때. 미래에 뭔가 의미있는 작업을 더 해야 할 필요성을 느낄때.
- FIXME : 문제가 있는것이 확실하지만, 그걸 지금 당장 그것을 수정할 필요는 없을 때.
- XXX : 해당 부분에 대해서는 더 생각해볼 필요성이 있을 때. 또는 해당 부분에 질문이 생길 때. 또는 코드에서 문제가 일어날만한 부분을 강조 표기할때. 완벽하게 정확히 구현되지 않은 부분이 있을 때. 나중에 고쳐야만하는 부분일 때.

# 개발

## 구조

```sh
src
├── frontend // 백엔드와 프론트엔드를 동시에 빌드할경우 프론트엔드 폴더내 코드 이동
└── main
│ └── java
│   ├── config // 설정
│   ├── controller // 컨트롤러
│   ├── entity // 엔티티
│   ├── exception // 공통 예외
│   ├── module // 모듈관리
│   ├── repository // JpaRepository 관리
│   └── service // 서비스 관리
│ └── resources // 리소트 관리
└── test // 단위 테스트
```

## 리소스

- application.properties
  - spring.datasource.url : DB Address / DB Name
  - spring.datasource.username : DB ID
  - spring.datasource.password : DB Password

## 개발 프로세스

- 이슈 단계
  issues - boards(open, todo, doing, merged develop, merged master, close)
  - 'open' 단계에 앞으로 진행 될 이슈 생성(마일스톤, 작업자, 일정, 내용)
  - 스프린트 단위로 한 주가 시작되기 전 'open' -> 'todo' 상태 변경하여 해당 주의 스프린트가 시작 됨을 알림
  - 해당 작업자는 이슈 상태 'doing' 변경하여 이슈 해결 시작을 알림
  - /develop 브랜치를 기반으로 /feature/이슈번호 브랜치 생성하여 개발 시작
  - 개발 완료 후 /feature/이슈번호에 commit/push 후 merge request 생성
  - /feature/이슈번호 맞는 merge request 를 해당 팀원은 모두 코드를 확인하고 변경사항이 있다면 소스 위치에 수정내용 작성, 없다면 '확인 완료' 커맨트 작성
  - 작업자는 merge request 의 모든 커맨트를 확인하고 변경 작업 후에 완료했다는 커맨트를 등록, 'mark as resolved' 선택,
    만약 '확인 완료' 만 있다면 'mark as resolved' 선택하고 병합
  - 변경요청 한 팀원은 변경 된 내용을 확인하고 작업자의 커맨트에 'mark as resolved'
  - 모든 작업이 완료되었다면 /develop 브랜치로 병합
  - 해당 프로젝트의 빌드전(배포서버에서) slack의 해당 채널에 배포 시작을 모든 팀원에게 알리고, 해당 프로젝트 deploy를 시작
  - deploy가 모두 완료되면 slack의 해당 채널에 배포 완료를 모든 팀원에게 알림
  - 해당 테스크 상태 'merged develop' 변경
  - 테스트 진행
  - 위 내용은 스프린트(단위:1주) 단위로 이루어 지며 모든 이슈에 대해서 위와 같이 동일하게 반복

## 소스 버전 관리

- 해당 브랜치 구조는 git-flow 정책을 따름

- 브랜치 구조
  feature - develop - release - tag - master - hotfix
  - feature : 테스크 단위 브랜치
  - develop : 전체 개발 단위 브랜치
  - release : 릴리즈 단위 브랜치
  - master : 전체 운영 단위 브랜치
  - hotfix : 전체운영 핫픽스 단위 브랜치
