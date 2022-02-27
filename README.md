 # :blue_book: 리북 Rebook : 책 주문&리뷰 웹사이트

##  :full_moon_with_face: 소개
책을 주문할 수 있고, 책에 리뷰를 달 수 있는 웹사이트 제작 토이 프로젝트

## :hammer: 기술 스택
<img src="https://img.shields.io/badge/springboot-6DB33F?style=flat&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/jpa-E31E52?style=flat&logo=jpa&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=flat&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/thymeleaf-005F0F?style=flat&logo=thymeleaf&logoColor=white">



## :wrench: 개발 환경
:bulb: <b>java11</b><br>
:bulb: <b>gradle</b><br>
:bulb: <b>spring-boot 2.6.3</b><br>
:bulb: <b>mysql 8.0.23</b><br>

## :memo: 개발 일지
:memo:[[Feb 7, 2022] member domain create](https://github.com/LeeYongjun1030/rebook/commit/0ab21186312a220322ba584441f30b8acdd35a79)<br>
:memo:[[Feb 8, 2022] domain create](https://github.com/LeeYongjun1030/rebook/commit/3b506422c83b1469ba60eaa3c09ca4a91185113b)<br>
:memo:[[Feb 9, 2022] login create](https://github.com/LeeYongjun1030/rebook/commit/331f2c404716a05b9340bfde31ef8bdc320962f2)<br>
:memo:[[Feb 10, 2022] review function create](https://github.com/LeeYongjun1030/rebook/commit/99c10800ebfbf73157844c93c34187bffbb9bbdb)<br>
:memo:[[Feb 11, 2022] review create](https://github.com/LeeYongjun1030/rebook/commit/8874f96a27034dcbd88a15c89f573eedbb140cc3)<br>
:memo:[[Feb 12, 2022] review delete function create ](https://github.com/LeeYongjun1030/rebook/commit/ca11fcd5e3650fa68d424649ef6144beae5082f5)<br>
:memo:[[Feb 13, 2022] order service create](https://github.com/LeeYongjun1030/rebook/commit/a614f95b06b0bedc3b3371aabe8478a4d1bfe9bd)<br>
:memo:[[Feb 14, 2022] order service create](https://github.com/LeeYongjun1030/rebook/commit/64c4b315fd13319f41f9e09296ba73a727ed267f)<br>
:memo:[[Feb 14, 2022] order cancel function create](https://github.com/LeeYongjun1030/rebook/commit/039f76210755ab6d0b26123d4c954321df11b6c1)<br>
:memo:[[Feb 16, 2022] add order service test](https://github.com/LeeYongjun1030/rebook/commit/140dc3faf7ac89c34e794a8863eee58f50948bb2)<br>
:memo:[[Feb 16, 2022] html modified](https://github.com/LeeYongjun1030/rebook/commit/db649957b4a005550ffc0fea01dfdd873a17a222)<br>
:memo:[[Feb 27, 2022] optimization order list using fetch join](https://github.com/LeeYongjun1030/rebook/commit/51d54453a9e4c0480983c4c5e8f01658398df88b)<br>

## :pushpin: 차례  
:pushpin: <b>1. 요구 사항 분석</b>  
:pushpin: <b>2. 도메인 모델과 테이블 설계</b>  
:pushpin: <b>3. 개발과 구현</b>  
:pushpin: <b>4. 완성 및 실행</b>  
:pushpin: <b>5. 트러블슈팅</b>  
:pushpin: <b>6. 평가</b>  


## :pushpin: 1. 요구사항 분석  

![image](https://user-images.githubusercontent.com/78812317/155678914-87daaa78-df28-4b9f-8911-31daee2ce029.png)

## :pushpin: 2. 도메인 모델과 테이블 설계
:pencil2: <b>회원, 책, 리뷰, 주문</b> <br>
![image](https://user-images.githubusercontent.com/78812317/154215024-8251ff72-5d72-4d7e-a5ab-0766ae8eec42.png)


## :pushpin: 3. 개발과 구현
### :ballot_box_with_check: 전체 흐름
:heavy_check_mark: 전체 흐름은 다음과 같다 : 클라이언트 -> 컨트롤러 -> 서비스 -> 리포지토리 -> 데이터베이스<br>

### :ballot_box_with_check: 도메인 개발<br>
:heavy_check_mark: lombok라이브러리를 활용하여 getter, setter, constructor 코드를 간단히 작성<br>
:heavy_check_mark: 엔티티 매핑을 위해 엔티티 설정 및 PK 생성<br>

### :ballot_box_with_check: 리포지토리 개발<br>
:heavy_check_mark: 어떠한 데이터베이스를 사용할 지 정해지지 않은 상황을 가정하였으므로, 데이터베이스는 유연하게 변경가능해야 한다.<br>
:heavy_check_mark: 데이터베이스에 접근하는 리포지토리는 인터페이스를 활용하여 개발하고 구현체를 따로 만들어준다.<br>
:heavy_check_mark: 이때 여러 구현체에 대해 중복 빈 문제가 발생할 수 있으므로 사용할 구현체에는 @Primary 어노테이션을 달아준다.<br>
:heavy_check_mark: 엔티티 매니저를 주입하여 엔티티를 관리하고 db에 접근할 수 있도록 해준다.<br>

### :ballot_box_with_check: 서비스 개발<br>
:heavy_check_mark: 리포지토리에 접근할 수 있도록 연관관계를 주입시켜 준다. 연관관계 주입은 생성자 주입 방법을 사용한다.<br>
:heavy_check_mark: 핵심 비즈니스 로직이 서비스 안에 담기도록 설계한다.<br>

### :ballot_box_with_check: 컨트롤러 개발<br>
:heavy_check_mark: 서비스를 주입받아 필요 서비스를 수행할 수 있도록 한다.<br>

### :ballot_box_with_check: 웹 관련 개발<br>
:heavy_check_mark: member의 로그인 정보 등 중요 정보가 외부로 노출되지 않도록 뷰에는 Dto를 만들어서 넘겨주도록 한다.<br>
:heavy_check_mark: 폼 객체 역시 별도로 두어 필요 정보만을 담을 수 있도록 한다.<br>


### :ballot_box_with_check: 엔티티 매핑<br>
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


### :ballot_box_with_check: 회원가입, 로그인 개발
:heavy_check_mark: 회원가입, 로그인에는 검증 단계가 실행되어야 한다. 이를 위해 데이터가 입력되는 폼 객체에 Bean Validation을 적용한다.<br>
:heavy_check_mark: 검증에 오류가 있다면 폼 객체를 다시 보여주도록 한다.<br>
:heavy_check_mark: 회원가입 시에는 아이디, 닉네임의 중복 여부도 검증하도록 한다.<br>
:heavy_check_mark: 검증 실패에 대한 에러 메시지는 resources안에 errors.properties 파일을 별도로 두어 에러 메시지를 관리하도록 한다.<br><br>
:heavy_check_mark: 로그인 성공 시에는 세션을 생성하여 정해진 시간동안 세션 정보를 계속 사용할 수 있도록 한다.<br>
:heavy_check_mark: 주문, 리뷰 작성 페이지에는 비로그인 사용자가 접속하면 안되므로 로그인 여부를 확인하는 절차를 거쳐야 한다.<br>
이를 위해 스프링 인터셉터를 사용한다. <br>
:heavy_check_mark: 특정 페이지에는 컨트롤러에 매핑이 되기 전 인터셉트를 걸어서 로그인 여부를 판단하도록 하면 된다. 이때 비로그인이라면 로그인 화면으로 리다이렉트 시켜준다.<br>
:heavy_check_mark: 현재 요청 주소를 RequestParam로 넘겨주어 로그인 성공 시 자동으로 원래 페이지로 돌아갈 수 있도록 해준다. <br>


### :ballot_box_with_check: 뷰 개발
:heavy_check_mark: 타임리프를 활용하여 작성한다.<br>
:heavy_check_mark: resources안에 messages.properties파일을 별도로 두어 메시지 내용을 관리하도록 한다.<br>


### :ballot_box_with_check: 테스트 코드 작성
:heavy_check_mark: 작성한 서비스의 기능을 테스트하는 코드를 작성한다.<br>
:heavy_check_mark: 필요 시 beforeEach, afterEach를 활용하여 데이터베이스를 초기화 시켜줘야 한다.<br>
:heavy_check_mark: 모든 테스트는 독립적으로 작동되도록 테스트한다.<br>

### :ballot_box_with_check: 기타
:heavy_check_mark: 객체 지향 설계 원칙 최대한 지키면서 개발하기<br>
:heavy_check_mark: order 엔티티를 조회할 때 orderBook를 페치 조인으로 한번에 긁어와야 한다.<br>


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
<summary>쿼리 보기</summary>
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
 

### 필요없는 쿼리 지우기
쿼리를 확인해보니, member를 조회하는 select 문이 나가는 것을 확인되었다. member 정보를 사용하지 않지만 쿼리가 발생한 이유는 현재 Order 클래스가 갖고 있는 Member 프로퍼티가 즉시로딩(default)로 설정되어 있기때문이었다. 이 설정을 지연 로딩으로 설정하면 member 정보를 사용할 때까지 조회를 미루기 때문에 불필요한 쿼리가 발생되는 것을 막을 수 있다.
 
```java
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩으로 설정
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
```

<details>
<summary> 개선된 쿼리 1</summary>
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

### N+1 문제 해결
또 다른 문제는 각 주문(Order)마다 orderBooks를 조회하는 쿼리문을 발생시킨다는 것이였다. <br>
이 경우 주문 목록에 100만개의 주문이 있다면, orderBooks를 조회하는 동일한 쿼리문을 100만 번 발생시킨다는 의미였다. <br>
 
- 코드 확인
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


 
 
 
 
 
 
 
## :pushpin: 6. 평가
:blush: 그동안 공부했던 내용을 프로젝트를 직접 만들어봄으로써 잘 정리할 수 있었다.<br>
:blush: 레포지터리 개발에 생각보다 많은 시간이 소요되었다. 스프링 데이터 jpa 기술을 배워 레포지터리까지 좀 더 효율적으로 개발할 수 있도록 해야겠다. <br>
:blush: 시큐리티 분야도 좀 더 배워 보완에 더 대비된 코드를 작성할 수 있도록 해야겠다.
