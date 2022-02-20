 # 리북 Rebook : 책 주문&리뷰 웹사이트

## 소개
책을 주문할 수 있고, 책에 리뷰를 달 수 있는 웹사이트 제작 토이 프로젝트

## 기술 스택
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/jpa-E31E52?style=for-the-badge&logo=jpa&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

## 개발 환경
java11<br>
gradle<br>
spring-boot 2.6.3<br>

## 개발 일지
*  [[Feb 7, 2022]member domain create](https://github.com/LeeYongjun1030/rebook/commit/0ab21186312a220322ba584441f30b8acdd35a79)  
*  [[Feb 8, 2022]domain create](https://github.com/LeeYongjun1030/rebook/commit/3b506422c83b1469ba60eaa3c09ca4a91185113b)
 *  [[Feb 9, 2022]login create](https://github.com/LeeYongjun1030/rebook/commit/331f2c404716a05b9340bfde31ef8bdc320962f2)
*  [[Feb 10, 2022]review function create](https://github.com/LeeYongjun1030/rebook/commit/99c10800ebfbf73157844c93c34187bffbb9bbdb)
*  [[Feb 11, 2022]review create](https://github.com/LeeYongjun1030/rebook/commit/8874f96a27034dcbd88a15c89f573eedbb140cc3)
*  [[Feb 12, 2022]review delete function create ](https://github.com/LeeYongjun1030/rebook/commit/ca11fcd5e3650fa68d424649ef6144beae5082f5)
*  [[Feb 13, 2022]order service create](https://github.com/LeeYongjun1030/rebook/commit/a614f95b06b0bedc3b3371aabe8478a4d1bfe9bd)

*  [[Feb 14, 2022]order service create](https://github.com/LeeYongjun1030/rebook/commit/64c4b315fd13319f41f9e09296ba73a727ed267f)

*  [[Feb 14, 2022]order cancel function create](https://github.com/LeeYongjun1030/rebook/commit/039f76210755ab6d0b26123d4c954321df11b6c1)

*  [[Feb 16, 2022]add order service test](https://github.com/LeeYongjun1030/rebook/commit/140dc3faf7ac89c34e794a8863eee58f50948bb2)

*  [[Feb 16, 2022]html modified](https://github.com/LeeYongjun1030/rebook/commit/db649957b4a005550ffc0fea01dfdd873a17a222)


## 차례  
1. 요구 사항 분석  
2. 도메인 모델과 테이블 설계  
3. 개발과 구현  
4. 완성 및 실행  
5. 마치며  


## 1. 요구사항 분석  

[기본 요구사항]  

<회원>  
회원은 회원 가입을 할 수 있다.  
회원은 책 리뷰를 작성, 삭제할 수 있다.  
회원은 리뷰 수에 따라 등급이 부여된다.  
회원은 등급에 따라 구매 시 할인을 적용받는다.
<br><br>
<책>  
책에는 이름, 출판사, 분야, 가격 정보가 있다.
모든 책 리스트를 조회할 수 있다.   
특정 책에 대한 모든 리뷰를 조회할 수 있다.
<br><br>
<리뷰>  
리뷰는 평점과 코멘트가 있다.  
리뷰는 생성, 조회, 삭제가 가능하다.
<br><br>
<주문>  
주문은 주문 날짜가 표기되어 있다.
주문은 생성, 조회, 삭제가 가능하다.
<br><br>
<기타>  
데이터베이스는 메모리, h2, mysql을 유동적으로 쓸 수 있도록 설계한다.
등급에 따른 가격 할인 정책은 수시로 바뀔 수 있도록 설계한다.



## 2. 도메인 모델과 테이블 설계
도메인: 회원, 책, 리뷰, 주문<br>
![image](https://user-images.githubusercontent.com/78812317/154215024-8251ff72-5d72-4d7e-a5ab-0766ae8eec42.png)


## 3. 개발과 구현
### 전체 흐름
전체 흐름은 다음과 같다 : 클라이언트 -> 컨트롤러 -> 서비스 -> 리포지토리 -> 데이터베이스<br>

### 도메인 개발<br>
lombok라이브러리를 활용하여 getter, setter, constructor 코드를 간단히 작성<br>
엔티티 매핑을 위해 엔티티 설정 및 PK 생성<br>

### 리포지토리 개발<br>
어떠한 데이터베이스를 사용할 지 정해지지 않은 상황을 가정하였으므로, 데이터베이스는 유연하게 변경가능해야 한다.<br>
데이터베이스에 접근하는 리포지토리는 인터페이스를 활용하여 개발하고 구현체를 따로 만들어준다.<br>
이때 여러 구현체에 대해 중복 빈 문제가 발생할 수 있으므로 사용할 구현체에는 @Primary 어노테이션을 달아준다.<br>
엔티티 매니저를 주입하여 엔티티를 관리하고 db에 접근할 수 있도록 해준다.<br>

### 서비스 개발<br>
리포지토리에 접근할 수 있도록 연관관계를 주입시켜 준다. 연관관계 주입은 생성자 주입 방법을 사용한다.<br>
핵심 비즈니스 로직이 서비스 안에 담기도록 설계한다.<br>

### 컨트롤러 개발<br>
서비스를 주입받아 필요 서비스를 수행할 수 있도록 한다.<br>

### 웹 관련 개발<br>
member의 로그인 정보 등 중요 정보가 외부로 노출되지 않도록 뷰에는 Dto를 만들어서 넘겨주도록 한다.<br>
폼 객체 역시 별도로 두어 필요 정보만을 담을 수 있도록 한다.<br>


### 엔티티 매핑<br>
member와 review 엔티티는 1대N 매핑<br>
book와 review 엔티티는 1대N 매핑<br>
member와 order 엔티티는 1대N 매핑<br>
order와 book 엔티티는 N대N 매핑<br><br>
이때 order와 book 사이에 orderBook 엔티티를 두어 N대N 매핑을 1대N 매핑과 N대1 매핑으로 분리시킨다.<br>
그러면 orderBook 엔티티에서 order와 book의 id를 FK를 관리하게 된다.<br><br>
엔티티 매핑의 기본은 단방향으로 이루어지지만, order 객체를 통해 orderBook 객체에 접근할 수 있도록 <br>
order 클래스 안에 orderBook 리스트 객체를 담도록 한다.<br>
이때 CASCADE 설정을 CascadeType.ALL로 설정한다. 그러면 부모 객체인 order 객체가 엔티티 매니저에 의해 persist될 때 orderBook 객체도 자동으로 persist된다. <br>
뿐만 아니라 order가 remove될 시에도 자동으로 remove된다. 즉 orderBook의 생명주기를 order가 관리할 수 있도록 설정한다.<br>


### 회원가입, 로그인 개발
회원가입, 로그인에는 검증 단계가 실행되어야 한다. 이를 위해 데이터가 입력되는 폼 객체에 Bean Validation을 적용한다.<br>
검증에 오류가 있다면 폼 객체를 다시 보여주도록 한다.<br>
회원가입 시에는 아이디, 닉네임의 중복 여부도 검증하도록 한다.<br>
검증 실패에 대한 에러 메시지는 resources안에 errors.properties 파일을 별도로 두어 에러 메시지를 관리하도록 한다.<br><br>
로그인 성공 시에는 세션을 생성하여 정해진 시간동안 세션 정보를 계속 사용할 수 있도록 한다.<br>
주문, 리뷰 작성 페이지에는 비로그인 사용자가 접속하면 안되므로 로그인 여부를 확인하는 절차를 거쳐야 한다.<br>
이를 위해 스프링 인터셉터를 사용한다. <br>
특정 페이지에는 컨트롤러에 매핑이 되기 전 인터셉트를 걸어서 로그인 여부를 판단하도록 하면 된다. 이때 비로그인이라면 로그인 화면으로 리다이렉트 시켜준다.<br>
현재 요청 주소를 RequestParam로 넘겨주어 로그인 성공 시 자동으로 원래 페이지로 돌아갈 수 있도록 해준다. <br>


### 뷰 개발
타임리프를 활용하여 작성한다.<br>
resources안에 messages.properties파일을 별도로 두어 메시지 내용을 관리하도록 한다.<br>


### 테스트 코드 작성
작성한 서비스의 기능을 테스트하는 코드를 작성한다.<br>
필요 시 beforeEach, afterEach를 활용하여 데이터베이스를 초기화 시켜주고,<br>
모든 테스트는 독립적으로 작동되도록 테스트한다.<br>

### 기타
객체 지향 설계 원칙 최대한 지키면서 개발하기<br>
order 엔티티를 조회할 때 orderBook를 페치 조인으로 한번에 긁어와야한다.<br>


## 4. 완성 및 실행
### 홈 화면(로그인 전)
![image](https://user-images.githubusercontent.com/78812317/154215546-179caacf-847b-4fee-bfd6-bd978507577c.png) 

### 회원 가입
입력 텍스트를 검증하여 상황에 따라 다양한 오류 메시지를 표시해준다.
![image](https://user-images.githubusercontent.com/78812317/154215756-bd9f821a-864a-4bca-a913-fe4bb96e1c05.png)

### 로그인
![image](https://user-images.githubusercontent.com/78812317/154215895-307cadff-c96a-47f9-926f-865d9d2d3fb0.png)

### 홈 화면(로그인)
로그인을 하면 내 닉네임과 등급 정보를 표시해준다.
![image](https://user-images.githubusercontent.com/78812317/154216022-bf25a91c-a6d0-4655-a7f3-d12f6bccbaf3.png)


### 책 목록
모든 책 목록을 볼 수 있다.
![image](https://user-images.githubusercontent.com/78812317/154216355-b6a304a6-2306-460d-a689-795c076eef00.png)

### 책 상세 화면
책 목록에서 책을 클릭하면 상세정보와 함께 리뷰 목록을 볼 수 있다.
![image](https://user-images.githubusercontent.com/78812317/154217850-dbe1c4ca-16e4-4454-96e8-3374c9d03397.png)

### 리뷰 작성 화면
별점은 selection을 이용하여 1점부터 5점까지 선택할 수 있다.<br>
리뷰는 2자 이상, 30자 이내로 적을 수 있으며 이에 맞지 않을 시 오류 메시지가 보인다.<br>
![image](https://user-images.githubusercontent.com/78812317/154220496-b8c44e99-0f72-4ff0-996e-8f6d809116ec.png)


### 리뷰 보기
내가 쓴 리뷰 목록들을 볼 수 있다.<br>
원하는 리뷰를 체크박스로 선택하여 삭제할 수 있다.<br>
리뷰 개수에 따른 나의 등급 정보를 확인할 수 있다.<br>
![image](https://user-images.githubusercontent.com/78812317/154220718-37dd9ce8-0c7f-4c29-9d8d-95e8ec65ff9c.png)



### 책 주문하기
원하는 책을 선택하여 수량을 선택하여 주문할 수 있다.<br>
![image](https://user-images.githubusercontent.com/78812317/154220821-dc6e3041-bae1-49e2-986b-cf807e9ec78f.png)
![image](https://user-images.githubusercontent.com/78812317/154220863-c06ebd36-b53e-462a-ba4b-426fb5b845bf.png)


### 주문 목록 보기
내가 주문했던 모든 주문 목록을 조회할 수 있다.
![image](https://user-images.githubusercontent.com/78812317/154220935-3c613783-03b1-4666-995b-3966b84ff959.png)


### 주문 상세 보기
주문 목록에서 주문을 클릭하면 주문 상세 내용과 할인 내용을 확인할 수 있다.
주문 취소를 눌러 주문을 취소할 수도 있다.
![image](https://user-images.githubusercontent.com/78812317/154221184-a7fb81ce-d4f7-4763-bacc-b2606148a33c.png)


## 5. 마치며
그동안 공부했던 내용을 프로젝트를 직접 만들어봄으로써 잘 정리할 수 있었다.<br>
스프링 데이터 jpa 기술을 배워 레포지터리까지 좀 더 효율적으로 개발할 수 있도록 해야겠다.<br>
