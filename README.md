# compose.danke

# compose_test

### 빌드 실패 정리

- 기존 groovy DSL인데 현재 프로젝트는 kotlin DSL 쓰면서 나온 이슈

1. gradle 8.1로 진행시
- 자바 17로 올려야하는 이슈, 올리면 해결

2. 자바 11로 진행하고 싶은 경우
- gradle 7.0~7.5로 진행해야함
- android gradle 는 7.4
- gradle -> packaging -> packagingOptions으로 바꿔줘야함
