 # :blue_book: 리북 Rebook : 책 주문&리뷰 웹사이트

##  :full_moon_with_face: 소개
- 책을 주문할 수 있고, 책에 리뷰를 달 수 있는 웹사이트 제작 토이 프로젝트<br>
- 개발 기간 2022.2.7~2022.2.16 ...을 계획했으나 계속 보완 중<br>
- 개인 프로젝트<br>

##  :triangular_flag_on_post: 목적
- 스프링으로 개발한 첫번째 프로젝트로, 지금까지 공부한 내용을 총 정리하고 익힌다.<br>
- 아직 안 배운 것(Spring data jpa, Query dsl)이 많지만 지금까지 배운 것을 직접 작성해보고 활용해본다.<br>
- 스프링+jpa 기술 사용에 초점을 맞추고, 그 밖의 세세한 부분(보안 관련 문제, 책의 재고 수량 관리 등)은 앞으로 계속 보완한다.<br>



## :hammer: 기술 스택
- <img src="https://img.shields.io/badge/springboot-6DB33F?style=flat&logo=springboot&logoColor=white">
- <img src="https://img.shields.io/badge/jpa-E31E52?style=flat&logo=jpa&logoColor=white">
- <img src="https://img.shields.io/badge/mysql-4479A1?style=flat&logo=mysql&logoColor=white">
- <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=flat&logo=thymeleaf&logoColor=white">



## :wrench: 개발 환경
- <b>java11</b><br>
- <b>gradle</b><br>
- <b>spring-boot 2.6.3</b><br>
- <b>mysql 8.0.23</b><br>

## :memo: 개발 일지(최종 업데이트 : 2022년 5월 29일)
- [[Feb 7, 2022] member domain create](https://github.com/LeeYongjun1030/rebook/commit/0ab21186312a220322ba584441f30b8acdd35a79)<br>
- [[Feb 8, 2022] domain create](https://github.com/LeeYongjun1030/rebook/commit/3b506422c83b1469ba60eaa3c09ca4a91185113b)<br>
- [[Feb 9, 2022] login create](https://github.com/LeeYongjun1030/rebook/commit/331f2c404716a05b9340bfde31ef8bdc320962f2)<br>
- [[Feb 10, 2022] review function create](https://github.com/LeeYongjun1030/rebook/commit/99c10800ebfbf73157844c93c34187bffbb9bbdb)<br>
- [[Feb 11, 2022] review create](https://github.com/LeeYongjun1030/rebook/commit/8874f96a27034dcbd88a15c89f573eedbb140cc3)<br>
- [[Feb 12, 2022] review delete function create ](https://github.com/LeeYongjun1030/rebook/commit/ca11fcd5e3650fa68d424649ef6144beae5082f5)<br>
- [[Feb 13, 2022] order service create](https://github.com/LeeYongjun1030/rebook/commit/a614f95b06b0bedc3b3371aabe8478a4d1bfe9bd)<br>
- [[Feb 14, 2022] order service create](https://github.com/LeeYongjun1030/rebook/commit/64c4b315fd13319f41f9e09296ba73a727ed267f)<br>
- [[Feb 14, 2022] order cancel function create](https://github.com/LeeYongjun1030/rebook/commit/039f76210755ab6d0b26123d4c954321df11b6c1)<br>
- [[Feb 16, 2022] add order service test](https://github.com/LeeYongjun1030/rebook/commit/140dc3faf7ac89c34e794a8863eee58f50948bb2)<br>
- [[Feb 16, 2022] html modified](https://github.com/LeeYongjun1030/rebook/commit/db649957b4a005550ffc0fea01dfdd873a17a222)<br>
- [[Feb 27, 2022] optimization order list using fetch join](https://github.com/LeeYongjun1030/rebook/commit/51d54453a9e4c0480983c4c5e8f01658398df88b)<br>
- [[Apr 27, 2022] findByLoginId add , transaction update](https://github.com/LeeYongjun1030/rebook/commit/e23e8c25b0e07ff28c6925305a0bc7b65a9305a4)<br>
- [[May 12, 2022] refactoring service and controller](https://github.com/LeeYongjun1030/rebook/commit/cced3fa6c86addeec8291c6d1645de387bd00e49)<br>
- [[May 15, 2022] encrypt password using spring security](https://github.com/LeeYongjun1030/rebook/commit/6705916c9d74498c423bde497795b7ff264d39c0)<br>
- [[May 16, 2022] exception, service refactoring](https://github.com/LeeYongjun1030/rebook/commit/09a2a15d14ba10da67b80a9c002f7b9d3bd6dcb1)<br>
- [[May 21, 2022] test refactoring using mocking](https://github.com/LeeYongjun1030/rebook/commit/c00faefb9ae2d7a920ae48be7d87703331d08222)<br>
- [[May 29, 2022] member service update](https://github.com/LeeYongjun1030/rebook/commit/225692368beeef20dacb31f34267bb875b1cee2f)<br>


## :pushpin: 차례  
- <b>1. 요구 사항 분석</b>  
- <b>2. 도메인 모델과 테이블 설계</b>  
- <b>3. 개발과 구현</b>  
- <b>4. 완성 및 실행</b>  
- <b>5. 트러블슈팅</b>  
- <b>6. 평가</b>  


## :pushpin: 1. 요구사항 분석  
요구사항 명세서(가상)<br>
![image](https://user-images.githubusercontent.com/78812317/165491039-b8409cb9-7488-416d-9ae5-7c8605b1b803.png)

## :pushpin: 2. 도메인 모델과 테이블 설계
:pencil2: <b>회원, 책, 리뷰, 주문</b> <br>
![image](https://user-images.githubusercontent.com/78812317/154215024-8251ff72-5d72-4d7e-a5ab-0766ae8eec42.png)


## :pushpin: 3. 개발과 구현
### :ballot_box_with_check: 전체 흐름
:heavy_check_mark: 전체 흐름은 다음과 같다 : 클라이언트 -> 컨트롤러 -> 서비스 -> 레포지토리 -> 데이터베이스<br>

<details markdown="1">
<summary> :point_right: 왜 계층을 나누는가? </summary>
레이어를 나누면, 다른 레이어를 추상화할 수 있게 된다. 이는 특정 레이어의 스펙이 바뀌어도 다른 레이어에서는 이를 신경쓰지 않아도 된다는 뜻이다.<br>
각 레이어는 본인의 역할에만 충실하면 되고, 문제 발생 시 해당 레이어만 신경쓰면 된다.<br> 
이는 또한 각 레이어의 테스트가 쉬워진다는 것을 의미한다.(필요시 mock을 사용)<br>
<br>
- 각 레이어의 역할<br>
컨트롤러 : UI 및 웹 기능을 처리한다. 이때 서비스를 호출하여 사용하게 된다. <br>
서비스 : 비즈니스 로직이 담기는 곳이다. 도메인 모델 여러개의 비즈니스 로직을 사용하여 복합적인 비즈니스 로직을 만들어낸다. <br>
레포지터리 : db에 접근하여 데이터에 대한 CRUD 및 주어진 쿼리를 실행한다. <br>

</details>

### :ballot_box_with_check: 도메인 개발<br>
:heavy_check_mark: lombok 라이브러리를 활용하여 getter, constructor 코드를 간단히 작성. setter는 만들지 않는다.(무분별한 대입 방지)<br>
:heavy_check_mark: 엔티티 매핑을 위해 엔티티 설정 및 PK 생성<br>
:heavy_check_mark: 도메인 주도 개발을 위해 비즈니스 로직을 도메인에 작성한다.<br>

### :ballot_box_with_check: 레포지토리 개발<br>
:heavy_check_mark: 어떠한 데이터베이스를 사용할 지 정해지지 않은 상황을 가정하였으므로, 데이터베이스는 유연하게 변경가능해야 한다.(OCP원칙)<br>
:heavy_check_mark: 데이터베이스에 접근하는 리포지토리는 인터페이스를 활용하여 개발하고 구현체를 따로 만들어준다.(느슨하게 결합)<br>
:heavy_check_mark: 이때 여러 구현체에 대해 중복 빈 문제가 발생할 수 있으므로 사용할 구현체에는 @Primary 어노테이션을 달아준다.<br>
:heavy_check_mark: 엔티티 매니저를 주입하여 엔티티를 관리하고 db에 접근할 수 있도록 해준다.<br>
:heavy_check_mark: 필요 시 fectch join을 통해 쿼리 중복 발생 문제를 막는다.<br>

### :ballot_box_with_check: 서비스 개발<br>
:heavy_check_mark: 레포지토리에 접근할 수 있도록 연관관계를 주입시켜 준다. 연관관계 주입은 생성자 주입 방법을 사용한다.<br>
:heavy_check_mark: 도메인 내의 비즈니스 로직을 활용하여 핵심 서비스를 개발한다.<br>

### :ballot_box_with_check: 컨트롤러 개발<br>
:heavy_check_mark: 서비스를 주입받아 필요 서비스를 수행할 수 있도록 한다.<br>

### :ballot_box_with_check: 웹 관련 개발<br>
:heavy_check_mark: member의 로그인 정보 등 중요 정보가 외부로 노출되지 않도록 뷰에는 Dto를 만들어서 넘겨주도록 한다.<br>
:heavy_check_mark: 폼 객체 역시 별도로 두어 필요 정보만을 담을 수 있도록 한다.<br>


### :ballot_box_with_check: 회원가입, 로그인 개발
:heavy_check_mark: 회원가입, 로그인에는 검증 단계가 실행되어야 한다. 이를 위해 데이터가 입력되는 폼 객체에 Bean Validation을 적용한다.<br>
:heavy_check_mark: 검증에 오류가 있다면 폼 객체를 다시 보여주도록 한다.<br>
:heavy_check_mark: 회원가입 시에는 아이디, 닉네임의 중복 여부가 이루어져야 한다.<br>
:heavy_check_mark: Spring Security를 활용하여 DB에는 암호화된 패스워드가 저장되도록 한다.<br>

<details markdown="1">
<summary> :point_right: bcrypt 암호화 방법 자세히</summary>
 <br>
https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage<br>
</details>

:heavy_check_mark: 검증 실패에 대한 에러 메시지는 resources안에 errors.properties 파일을 별도로 두어 에러 메시지를 관리하도록 한다.<br>
:heavy_check_mark: 로그인 성공 시에는 세션을 생성하여 정해진 시간동안 세션 정보를 계속 사용할 수 있도록 한다.<br>
:heavy_check_mark: 주문, 리뷰 작성 페이지에는 비로그인 사용자가 접속하면 안되므로 로그인 여부를 확인하는 절차를 거쳐야 한다.<br>
이를 위해 스프링 인터셉터를 사용한다. <br>
:heavy_check_mark: 특정 페이지에는 컨트롤러에 매핑이 되기 전 인터셉트를 걸어서 로그인 여부를 판단하도록 하면 된다. 이때 비로그인이라면 로그인 화면으로 리다이렉트 시켜준다.<br>
:heavy_check_mark: 현재 요청 주소를 RequestParam로 넘겨주어 로그인 성공 시 자동으로 원래 페이지로 redirect 될 수 있도록 해준다. <br>



### :ballot_box_with_check: 뷰 개발
:heavy_check_mark: 타임리프를 활용하여 작성한다.<br>
:heavy_check_mark: resources안에 messages.properties파일을 별도로 두어 메시지 내용을 관리하도록 한다.<br>


### :ballot_box_with_check: ORM(Object Relational Mapping)<br>
:heavy_check_mark: member와 review 엔티티는 1대N 매핑<br>
:heavy_check_mark: book와 review 엔티티는 1대N 매핑<br>
:heavy_check_mark: member와 order 엔티티는 1대N 매핑<br>
:heavy_check_mark: order와 book 엔티티는 N대N 매핑<br><br>
:heavy_check_mark: 이때 order와 book 사이에 orderBook 엔티티를 두어 N대N 매핑을 1대N 매핑과 N대1 매핑으로 분리시킨다.<br>
그러면 orderBook 엔티티에서 order와 book의 id를 FK를 관리하게 된다.<br><br>
:heavy_check_mark: 엔티티 매핑의 기본은 단방향으로 이루어지지만, order 객체를 통해 orderBook 객체에 접근할 수 있도록 <br>
order 클래스 안에 orderBook 리스트 객체를 담도록 한다.<br>
이때 CASCADE 설정을 CascadeType.ALL로 설정한다. 그러면 부모 객체인 order 객체가 엔티티 매니저에 의해 persist될 때 orderBook 객체도 자동으로 persist된다. <br>
뿐만 아니라 order가 remove될 시에도 자동으로 remove된다. 즉 orderBook의 생명주기를 order가 관리할 수 있도록 설정한다.<br>



### :ballot_box_with_check: 테스트 코드 작성
:heavy_check_mark: 작성한 서비스의 기능을 테스트하는 코드를 작성한다.<br>
:heavy_check_mark: 필요 시 beforeEach, afterEach를 활용하여 데이터베이스를 초기화 시켜줘야 한다.<br>
:heavy_check_mark: 모든 테스트는 독립적으로 작동되도록 테스트한다.<br>


<details markdown="1">
<summary> :point_right: 테스트 레퍼런스 </summary>
스프링 공식 메뉴얼 : https://docs.spring.io/spring-security/reference/6.0.0-M5/servlet/test/index.html<br>
컨트롤러 테스트 : https://esoongan.tistory.com/101<br>
모킹(Mocking): https://galid1.tistory.com/772<br>
</details>



### :ballot_box_with_check: 기타
:heavy_check_mark: 객체 지향 설계 원칙 최대한 지키면서 개발하기<br>
:heavy_check_mark: 블로그 같은 웹 서칭도 좋지만 공식 문서 보는 습관 들이기<br>
:heavy_check_mark: 개발 일지 잘 작성하기<br>
:heavy_check_mark: 깊이있는 원리 학습이 제일 중요<br>

<details markdown="1">
<summary> :point_right: SOLID </summary>
내 블로그 참고 : https://yiyj1030.tistory.com/423?category=521632
</details>


<details markdown="1">
<summary> :point_right: 스프링 공식 문서 </summary>

스프링 MVC : https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc<br>
스프링 부트 : https://docs.spring.io/spring-boot/docs/current/api/<br>
스프링 부트 : https://docs.spring.io/spring-boot/docs/current/reference/html/index.html<br>

</details>




## :pushpin:  4. 완성 및 실행
<details markdown="1">
<summary> :computer: 홈 화면(로그인 전) </summary>
<img src="https://user-images.githubusercontent.com/78812317/154215546-179caacf-847b-4fee-bfd6-bd978507577c.png"  width="80%" height="80%"/>
</details>

<details markdown="1">
<summary> :computer: 회원 가입 </summary>
입력 텍스트를 검증하여 상황에 따라 다양한 오류 메시지를 표시해준다.
<img src="https://user-images.githubusercontent.com/78812317/154215756-bd9f821a-864a-4bca-a913-fe4bb96e1c05.png"  width="80%" height="80%"/>
</details>


<details markdown="1">
<summary> :computer: 로그인  </summary>
<img src="https://user-images.githubusercontent.com/78812317/154215895-307cadff-c96a-47f9-926f-865d9d2d3fb0.png"  width="80%" height="80%"/>
</details>


<details markdown="1">
<summary> :computer: 홈 화면(로그인) </summary>
로그인을 하면 내 닉네임과 등급 정보를 표시해준다.
<img src="https://user-images.githubusercontent.com/78812317/154216022-bf25a91c-a6d0-4655-a7f3-d12f6bccbaf3.png"  width="80%" height="80%"/>
</details>

<details markdown="1">
<summary> :computer: 책 목록  </summary>
모든 책 목록을 볼 수 있다.
<img src="https://user-images.githubusercontent.com/78812317/154216355-b6a304a6-2306-460d-a689-795c076eef00.png"  width="80%" height="80%"/>
</details>


<details markdown="1">
<summary> :computer: 책 상세 화면  </summary>
책 목록에서 책을 클릭하면 상세정보와 함께 리뷰 목록을 볼 수 있다.
<img src="https://user-images.githubusercontent.com/78812317/154217850-dbe1c4ca-16e4-4454-96e8-3374c9d03397.png"  width="80%" height="80%"/>
</details>


<details markdown="1">
<summary> :computer: 리뷰 작성 화면 </summary>
별점은 selection을 이용하여 1점부터 5점까지 선택할 수 있다.<br>
리뷰는 2자 이상, 30자 이내로 적을 수 있으며 이에 맞지 않을 시 오류 메시지가 보인다.<br>
<img src="https://user-images.githubusercontent.com/78812317/154220496-b8c44e99-0f72-4ff0-996e-8f6d809116ec.png"  width="80%" height="80%"/>
</details>

<details markdown="1">
<summary> :computer: 리뷰 보기  </summary>
내가 쓴 리뷰 목록들을 볼 수 있다.<br>
원하는 리뷰를 체크박스로 선택하여 삭제할 수 있다.<br>
리뷰 개수에 따른 나의 등급 정보를 확인할 수 있다.<br>
<img src="https://user-images.githubusercontent.com/78812317/154220718-37dd9ce8-0c7f-4c29-9d8d-95e8ec65ff9c.png"  width="80%" height="80%"/>
</details>


<details markdown="1">
<summary>:computer: 책 주문하기 </summary>
원하는 책을 선택하여 수량을 선택하여 주문할 수 있다.<br>
<img src="https://user-images.githubusercontent.com/78812317/154220821-dc6e3041-bae1-49e2-986b-cf807e9ec78f.png"  width="80%" height="80%"/>
<img src="https://user-images.githubusercontent.com/78812317/154220863-c06ebd36-b53e-462a-ba4b-426fb5b845bf.png"  width="80%" height="80%"/>
</details>

<details markdown="1">
<summary> :computer: 주문 목록 보기 </summary>
내가 주문했던 모든 주문 목록을 조회할 수 있다.
<img src="https://user-images.githubusercontent.com/78812317/154220935-3c613783-03b1-4666-995b-3966b84ff959.png"  width="80%" height="80%"/>
</details>

<details markdown="1">
<summary> :computer: 주문 상세 보기 </summary>
주문 목록에서 주문을 클릭하면 주문 상세 내용과 할인 내용을 확인할 수 있다.<br>
주문 취소를 눌러 주문을 취소할 수도 있다.
<img src="https://user-images.githubusercontent.com/78812317/154221184-a7fb81ce-d4f7-4763-bacc-b2606148a33c.png"  width="80%" height="80%"/>
</details>


 
## :pushpin: 5. 트러블슈팅
 프로젝트의 모든 기능을 완성하고, 핵심 기능들을 실행해가며 실제로 날아가는 쿼리들을 확인하였다. 예상과는 다른 쿼리가 있다면 코드 상의 문제점이 있을 가능성이 있기 때문이다. 그러다 나의 주문 목록을 볼 때 필요 이상의 쿼리가 매우 많이 나가는 것을 발견했다.

 <details>
<summary> 발생 쿼리 </summary>
<div markdown="1">

```java

Hibernate: 
    select
        order0_.order_id as order_id1_3_,
        order0_.local_date as local_da2_3_,
        order0_.member_id as member_i3_3_ 
    from
        orders order0_ 
    where
        order0_.member_id=?
Hibernate: 
    select
        member0_.id as id1_1_0_,
        member0_.grade as grade2_1_0_,
        member0_.login_id as login_id3_1_0_,
        member0_.money as money4_1_0_,
        member0_.nickname as nickname5_1_0_,
        member0_.password as password6_1_0_ 
    from
        member member0_ 
    where
        member0_.id=?
OrderController.orders
Hibernate: 
    select
        orderbooks0_.order_id as order_id4_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_1_,
        orderbooks0_.book_id as book_id3_2_1_,
        orderbooks0_.order_id as order_id4_2_1_,
        orderbooks0_.quantity as quantity2_2_1_,
        book1_.book_id as book_id1_0_2_,
        book1_.book_name as book_nam2_0_2_,
        book1_.category as category3_0_2_,
        book1_.price as price4_0_2_,
        book1_.publisher as publishe5_0_2_ 
    from
        order_book orderbooks0_ 
    left outer join
        book book1_ 
            on orderbooks0_.book_id=book1_.book_id 
    where
        orderbooks0_.order_id=?
Hibernate: 
    select
        orderbooks0_.order_id as order_id4_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_1_,
        orderbooks0_.book_id as book_id3_2_1_,
        orderbooks0_.order_id as order_id4_2_1_,
        orderbooks0_.quantity as quantity2_2_1_,
        book1_.book_id as book_id1_0_2_,
        book1_.book_name as book_nam2_0_2_,
        book1_.category as category3_0_2_,
        book1_.price as price4_0_2_,
        book1_.publisher as publishe5_0_2_ 
    from
        order_book orderbooks0_ 
    left outer join
        book book1_ 
            on orderbooks0_.book_id=book1_.book_id 
    where
        orderbooks0_.order_id=?
Hibernate: 
    select
        orderbooks0_.order_id as order_id4_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_1_,
        orderbooks0_.book_id as book_id3_2_1_,
        orderbooks0_.order_id as order_id4_2_1_,
        orderbooks0_.quantity as quantity2_2_1_,
        book1_.book_id as book_id1_0_2_,
        book1_.book_name as book_nam2_0_2_,
        book1_.category as category3_0_2_,
        book1_.price as price4_0_2_,
        book1_.publisher as publishe5_0_2_ 
    from
        order_book orderbooks0_ 
    left outer join
        book book1_ 
            on orderbooks0_.book_id=book1_.book_id 
    where
        orderbooks0_.order_id=?

```

</div>
</details>
 

### :ballot_box_with_check: 필요없는 쿼리 지우기
쿼리를 확인해보니, member를 조회하는 select 문이 나가는 것을 확인되었다. member 정보를 사용하지 않지만 쿼리가 발생한 이유는 현재 Order 클래스가 갖고 있는 Member 프로퍼티가 즉시로딩(default)로 설정되어 있기때문이었다. 이 설정을 지연 로딩으로 설정하면 member 정보를 사용할 때까지 조회를 미루기 때문에 불필요한 쿼리가 발생되는 것을 막을 수 있다.
 
```java
public class Order{
    ....
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩으로 설정
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    ....
}
```

<details>
<summary> 발생 쿼리</summary>
<div markdown="1">

```java

Hibernate: 
    select
        order0_.order_id as order_id1_3_,
        order0_.local_date as local_da2_3_,
        order0_.member_id as member_i3_3_ 
    from
        orders order0_ 
    where
        order0_.member_id=?
OrderController.orders
Hibernate: 
    select
        orderbooks0_.order_id as order_id4_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_1_,
        orderbooks0_.book_id as book_id3_2_1_,
        orderbooks0_.order_id as order_id4_2_1_,
        orderbooks0_.quantity as quantity2_2_1_,
        book1_.book_id as book_id1_0_2_,
        book1_.book_name as book_nam2_0_2_,
        book1_.category as category3_0_2_,
        book1_.price as price4_0_2_,
        book1_.publisher as publishe5_0_2_ 
    from
        order_book orderbooks0_ 
    left outer join
        book book1_ 
            on orderbooks0_.book_id=book1_.book_id 
    where
        orderbooks0_.order_id=?
Hibernate: 
    select
        orderbooks0_.order_id as order_id4_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_1_,
        orderbooks0_.book_id as book_id3_2_1_,
        orderbooks0_.order_id as order_id4_2_1_,
        orderbooks0_.quantity as quantity2_2_1_,
        book1_.book_id as book_id1_0_2_,
        book1_.book_name as book_nam2_0_2_,
        book1_.category as category3_0_2_,
        book1_.price as price4_0_2_,
        book1_.publisher as publishe5_0_2_ 
    from
        order_book orderbooks0_ 
    left outer join
        book book1_ 
            on orderbooks0_.book_id=book1_.book_id 
    where
        orderbooks0_.order_id=?
Hibernate: 
    select
        orderbooks0_.order_id as order_id4_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_0_,
        orderbooks0_.order_book_id as order_bo1_2_1_,
        orderbooks0_.book_id as book_id3_2_1_,
        orderbooks0_.order_id as order_id4_2_1_,
        orderbooks0_.quantity as quantity2_2_1_,
        book1_.book_id as book_id1_0_2_,
        book1_.book_name as book_nam2_0_2_,
        book1_.category as category3_0_2_,
        book1_.price as price4_0_2_,
        book1_.publisher as publishe5_0_2_ 
    from
        order_book orderbooks0_ 
    left outer join
        book book1_ 
            on orderbooks0_.book_id=book1_.book_id 
    where
        orderbooks0_.order_id=?


```

</div>
</details>

### :ballot_box_with_check: N+1 문제 해결
또 다른 문제는 각 주문(Order)마다 orderBooks를 조회하는 쿼리문을 발생시킨다는 것이였다. <br>
이 경우 주문 목록에 100만개의 주문이 있다면, orderBooks를 조회하는 동일한 쿼리문을 100만 번 발생시킨다는 의미였다. <br>
우선 관련 부분의 코드를 살펴 원인을 파악하기로 했다.<br>
```java
    /**
     * 주문 목록 조회
     */
    @GetMapping("/orders")
    public String orders(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         Model model) {

        List<Order> orders = orderService.findByMemberId(loginMember.getId());
        
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setMemberName(loginMember.getNickname());
            orderDto.setLocalDate(order.getLocalDate());
            orderDto.setTotalOrderQuantities(orderService.getOrderTotalQuantities(order));
            orderDto.setTotalOrderPrice(orderService.getOrderTotalPrice(order));
            orderDtos.add(orderDto);
        }
        
        model.addAttribute("orders", orderDtos);

        return "/order/orders";
    }

```
콘솔 출력을 통해 확인한 결과, 반복문 안에서 각 order 별로 orderBooks를 조회한다는 사실을 확인하였다.<br><br>

:white_check_mark: <b>시도 1. 로딩 설정 변경</b><br>
첫번째 가설은 @OneToMany의 경우 기본이 지연 로딩으로 설정되어 있는데, 이것이 원인일 수도 있다는 생각에 Eager로 바꾸고 쿼리를 즉시 로딩하는 것으로 바꿔주었다. 

```java
public class Order{
    ....    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 즉시 로딩으로 변경
    private List<OrderBook> orderBooks = new ArrayList<>();   
    ....
}
```
하지만 쿼리를 확인해보니 쿼리 발생 시점만 달라질 뿐, 발생되는 쿼리는 동일했다.<br>
Lazy의 경우 반복문 안에서 orderBook 리스트 객체에 접근할 때 쿼리를 날리는 반면,<br>
Eager의 경우 반복문 진입 전에 order를 조회하는 시점에서 쿼리를 날렸다.<br>
그러다 문득 JPA를 공부할 때 배웠던 N+1문제에 대해 생각이 났다. <br>

<br>

:white_check_mark: <b>시도 2. 페치 조인 사용</b><br>
아래와 같이 페치 조인을 사용하는 쿼리문을 만들어 연관된 객체의 정보들을 한번에 가져오는 방법을 사용하기로 했다.

```java
public class DbOrderRepository implements OrderRepository {
    ....    
    @Override
    public List<Order> findByMemberId(Long memberId) {
        return em.createQuery(
                "select o from Order o " +
                       " join fetch o.orderBooks ob" +
                " where o.member.id = :memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }    
    ....
}

```

<details>
<summary> 즉시로딩 VS 페치조인</summary>
<div markdown="1">
 
 ```
즉시로딩
1. order 목록을 우선 끌고옴
2. 각 order에 대해 연관 객체 정보를 새 쿼리를 날려 끌고옴

페치조인
1. 내부조인을 발생시켜 연관 객체 정보를 한번에 끌고 옴
(그냥 조인이랑 다른점은 그냥 조인으로 하면 객체 1개로 해결하지 못함)
 ```
 
</div>
</details>


그러자 데이터가 중복되어 조회되는 상황이 발생하였다. 쿼리를 살펴보니 다음과 같았다.<br>

<details>
<summary> 발생 쿼리</summary>
<div markdown="1">

```java

Hibernate: 
    select
        order0_.order_id as order_id1_3_0_,
        orderbooks1_.order_book_id as order_bo1_2_1_,
        order0_.local_date as local_da2_3_0_,
        order0_.member_id as member_i3_3_0_,
        orderbooks1_.book_id as book_id3_2_1_,
        orderbooks1_.order_id as order_id4_2_1_,
        orderbooks1_.quantity as quantity2_2_1_,
        orderbooks1_.order_id as order_id4_2_0__,
        orderbooks1_.order_book_id as order_bo1_2_0__ 
    from
        orders order0_ 
    inner join
        order_book orderbooks1_ 
            on order0_.order_id=orderbooks1_.order_id 
    where
        order0_.member_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?

```

</div>
</details>

쿼리를 통해 살펴본 바로는, 데이터가 중복 저장되는 이유는 order가 갖고 있는 orderBooks 리스트의 각 orderBook 마다 order와 조인을 해버리기 때문에 동일한 order가 여러번 중복되어 조회되는 것이었다.<br>
(일대다 관계에서 조인할 시 생기는 문제임. 데이터가 뻥튀기 된다.)<br>
이 문제에 대한 서치의 결과, distinct 명령어가 필요하다는 것을 발견했다. 그래서 다음과 같이 코드를 개선했다. 
jpql에서 distinct를 사용하게 되면, 조회 객체가 중복되어 조회되는 것을 제거해준다.<br>

<br>


:white_check_mark: <b>시도 3. distinct 사용</b><br>
아래와 같이 distinct 명령어를 추가시켜준다.

```java
public class DbOrderRepository implements OrderRepository {
    ....    
    @Override
    public List<Order> findByMemberId(Long memberId) {
        return em.createQuery(
                "select distinct o from Order o " +
                       " join fetch o.orderBooks ob" +
                " where o.member.id = :memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }    
    ....
}

```


<details>
<summary> 발생 쿼리</summary>
<div markdown="1">

```java

Hibernate: 
    select
        distinct order0_.order_id as order_id1_3_0_,
        orderbooks1_.order_book_id as order_bo1_2_1_,
        order0_.local_date as local_da2_3_0_,
        order0_.member_id as member_i3_3_0_,
        orderbooks1_.book_id as book_id3_2_1_,
        orderbooks1_.order_id as order_id4_2_1_,
        orderbooks1_.quantity as quantity2_2_1_,
        orderbooks1_.order_id as order_id4_2_0__,
        orderbooks1_.order_book_id as order_bo1_2_0__ 
    from
        orders order0_ 
    inner join
        order_book orderbooks1_ 
            on order0_.order_id=orderbooks1_.order_id 
    where
        order0_.member_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?
Hibernate: 
    select
        book0_.book_id as book_id1_0_0_,
        book0_.book_name as book_nam2_0_0_,
        book0_.category as category3_0_0_,
        book0_.price as price4_0_0_,
        book0_.publisher as publishe5_0_0_ 
    from
        book book0_ 
    where
        book0_.book_id=?

```

</div>
</details>

distinct를 추가함으로써 order가 중복 조회되는 문제는 해결했지만 여전히 쿼리에는 문제가 존재한다.<br>
기존에는 orderBook 조회 시 orderBook클래스가 갖고 있는 book 프로퍼티도 조인으로 한번에 가져왔다.(즉시로딩으로 설정되어 있기때문)<br>
근데 페치조인을 적용하고 쿼리를 살펴보면 order와 orderBook 사이에만 조인이 발생한다. 즉 orderBook 객체가 갖고 있는 book 프로퍼티는 같이 끌어오지 못하는 것이다. 그래서 어쩔 수 없이 새로운 쿼리를 통해 book 정보를 가져오는 것을 볼 수 있다. <br>
 
 
:white_check_mark: <b>시도 4. orderBook 클래스의 book 프로퍼티에도 지연로딩을 걸어주면 해결이 될까? NO </b><br>
이전에 살펴봤듯이 조회 시점만 미뤄줄 뿐 반복문 안에서 book에 대한 셀렉트 쿼리가 여전히 나간다.

:white_check_mark: <b>시도 5. book까지 fetch join으로 끌어오기</b><br>
book 정보까지 한번에 끌어올 수 있도록 아래와 같이 쿼리문을 수정했다.<br>

```java
public class DbOrderRepository implements OrderRepository {
    ....    
    @Override
    public List<Order> findByMemberId(Long memberId) {
        return em.createQuery(
                "select distinct o from Order o " +
                       " join fetch o.orderBooks ob" +
                       " join fetch ob.book" +
                " where o.member.id = :memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
    ....
}

```

<details>
<summary> 발생 쿼리</summary>
<div markdown="1">

```java

Hibernate: 
    select
        distinct order0_.order_id as order_id1_3_0_,
        orderbooks1_.order_book_id as order_bo1_2_1_,
        book2_.book_id as book_id1_0_2_,
        order0_.local_date as local_da2_3_0_,
        order0_.member_id as member_i3_3_0_,
        orderbooks1_.book_id as book_id3_2_1_,
        orderbooks1_.order_id as order_id4_2_1_,
        orderbooks1_.quantity as quantity2_2_1_,
        orderbooks1_.order_id as order_id4_2_0__,
        orderbooks1_.order_book_id as order_bo1_2_0__,
        book2_.book_name as book_nam2_0_2_,
        book2_.category as category3_0_2_,
        book2_.price as price4_0_2_,
        book2_.publisher as publishe5_0_2_ 
    from
        orders order0_ 
    inner join
        order_book orderbooks1_ 
            on order0_.order_id=orderbooks1_.order_id 
    inner join
        book book2_ 
            on orderbooks1_.book_id=book2_.book_id 
    where
        order0_.member_id=?

```

</div>
</details>
드디어 쿼리 한 번에 내가 의도하는 모든 정보를 끌어올 수 있게 되었다.<br>

<br>

:white_check_mark: <b>결과</b><br>
결론적으로 주문을 N개 생성했을 때,<br>
기존 쿼리가 주문 목록을 가져오기 위해 2+N개의 쿼리를 발생시켰던 반면,<br>
개선을 통해 단 1번의 쿼리로 줄일 수 있게 되었다. <br>
 
## :pushpin: 6. 평가
<SOLID 원칙 준수><br>
SRP: 클래스를 역할에 맞게 잘 분리<br>
OCP: 객체의 추상화(ex. 레포지터리)를 사용 및 느슨하게 연결 + 스프링 DI로 OCP 극대화<br>
LSP: 하위 객체는 상위 객체의 철학을 맞춤(근데 상속, 구현을 사용할 일이 거의 없었음)<br>
ISP: 인터페이스를 잘 분리. 근데 LSP와 마찬가지로 인터페이스를 사용할 일이 많이 없었음<br>
DIP: 추상화에 잘 의존함<br>

<br>
PS.<br>
- 레포지터리 개발에 생각보다 많은 시간이 소요되었다. 스프링 데이터 jpa 기술을 배워 레포지터리까지 좀 더 생산성있게 개발할 수 있도록 해야겠다. <br>
- 시큐리티 분야도 좀 더 배워 보완에 더 대비된 코드를 작성할 수 있도록 해야겠다.
