# 팀프레시 온라인 사전 과제

## 프로그램 실행 방법
1. gradle로 프로젝트를 엽니다.
2. SubjectApplication 을 실행합니다.

** 만약 로컬 데이터베이스를 사용하신다면, h2 1.4.199 버전을 설치하시기 바랍니다.

## 따끈따끈한 프로젝트 후기
JPA를 처음 공부하면서 프로젝트를 진행했습니다. 전에 Node.js 환경에서도 TypeORM 을 사용하며 프로젝트를 진행한 적이 있었는데
JPA를 공부하면서 진행해보니, ORM 에 대한 이해도를 좀 더 높일 수 있었고 MyBatis로 못돌아갈 것 같습니다.
DB는 H2 db를 사용했는데, 심플하면서도 임베디드로 돌릴 수 있어서 코드를 보시는 입장에서 별도의 설치없이 실행해볼 수 있을 것 같아서 선택했습니다.
Company, Seller, Shipping 클래스를 DB에 매핑할때 어떤 전략(Joined, Single, TablePerClass)을 사용할지 고민했었는데 실제 서비스를 개발한다면
Joined 전략을 이용해서 객체 구조와도 일관적인 매핑이 가능하도록 개발하는게 나을 것 같다고 판단했습니다.

처음 요구사항을 분석할 때, 운송사에 대한 귀책이 바로 적용된다고 되어있고 그 밑에 기사님 귀책 시에 대한 내용이 들어가있는데
기사님 귀책 == 운송사 귀책이라고 생각해서 좀 헷갈렸습니다. 결과적으로 프로젝트에서는 고객사 귀책 시 바로 적용되도록 개발했습니다.
또한, voc 등록 시 고객사가 자동 적용될 때, 배상금액을 얼마로 해야될지 의문이어서(voc에 배상금액이 없습니다) 일단은 null로 등록되도록 처리했습니다.

VocReadService를 별도로 만들어서 findOne에 대한 처리만 하도록 개발했는데 이는 Transaction을 유지하면서 VocService, CompensationService에서
호출해서 쓸수 있도록 하기 위함입니다. 또한, VocService, CompensationService에서 각각 findOne으로 조회할 일이 많은데, VocService내에 
findOne을 넣게되면 순환참조가 발생할 수 있어서 회피하기 위함입니다.
Party 라는 Enum 을 만들었는데, 이를 Validation 하기 위해 EnumPattern 과 EnumPatterValidator를 만들었고 Validation 에러를 처리하기 위해
ValidationAdvisor를 추가했습니다.
그 외에도 IllegalStateException 처리를 위해 ExceptionHandlerAdvisor를 만들었고 추후, 또 다른 예외에 대해서도 처리를 추가할 수 있습니다.

테스트케이스의 경우, 사실 Mock Database를 이용해서 처리해야하지만 당장 공부가 부족해서 Jpa에 좀 의존적인 테스트를 만들게 되었습니다.
인수테스트도 아직 공부가 부족해서 유닛테스트만 개발했습니다. 팀프레시에 입사한다면, 이런 부족한 부분을 빠르게 습득해서 좋은 코드를 만들고 싶습니다.

이 프로젝트를 진행하며 Spring Boot와 JPA, Validation 처리에 대해 많이 배울수 있었습니다. 
