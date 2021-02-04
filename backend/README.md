# SPRINGBOOT-BACKEND

- SPRINGBOOT-BACKEND 개발 관련 내용을 기술한다.

# 이력

- v0.0.1
  - 2020.07.14
    - 최초 등록
    - 개발프로세스, 소스코드관리 방법

# 구성

  <!-- blank line -->

- 환경
  - windows 10, mac
  - visual studio code
  - github v2.1.3

  <!-- blank line -->

- 라이브러리-FrontEnd

  - @vue/eslint-config-standard / 5.1.2
  - animate.css / 3.6.1
  - apexcharts / 3.8.6
  - awesome-bootstrap-checkbox / 1.0.1
  - axios / 0.19.2
  - bootstrap / 4.1.1
  - bootstrap-vue / 2.0.3
  - css-loader / 1.0.0
  - echarts / 4.3.0
  - eslint / 6.8.0
  - eslint-config-airbnb / 18.1.0
  - eslint-config-standard / 14.1.1
  - eslint-plugin-vue / 6.2.2
  - expose-loader / 0.7.5
  - font-awesome / 4.7.0
  - glyphicons-halflings / 1.9.1
  - highcharts / 7.2.0
  - highcharts-vue / 1.3.5
  - imports-loader / 0.8.0
  - line-awesome / 1.3.0
  - moment / 2.25.3
  - node-sass / 4.9.0
  - path / 0.12.7
  - rickshaw / 1.6.6
  - sass-loader / 7.0.1
  - tiptap / 1.27.1
  - tiptap-extensions / 1.29.1
  - vue / 2.5.17
  - vue-apexcharts / 1.5.0
  - vue-echarts / 4.0.4
  - vue-router / 3.0.1
  - vue-toasted / 1.1.27
  - vue-touch / 2.0.0-beta.4
  - vue2-daterange-picker / 0.5.0
  - vue2-editor / 2.10.2
  - vuetrend / 0.3.2
  - vuex / 3.0.1
  - webpack-cli / 3.1.2

- 라이브러리-BackEnd
  - springfox-swagger2 / 2.9.2
  - springfox-swagger-ui / 2.9.2
  - jjwt / 0.9.1

# 실행

- Visual Studio Code 실행
- FrontEnd

  - cd src/frontend
  - npm install
  - npm run serve

- BackEnd
  - ./gradlew bootRun
  - ./gradlew clean build
  - ./gradlew bootwar

# 개발

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
