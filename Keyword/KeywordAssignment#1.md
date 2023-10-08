## Generic이란?

자바에서 제네릭(Generic)이란 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 방법을 말한다.

- ex : ArrayList<String\> list;

### 제네릭의 장점
1. 컴파일 단계에서 타입 체크를 할 수 있어서 잘못된 타입을 잡아낼 수 있다.
2. 클래스 외부에서 타입을 지정하기에 타입 체크나 불필요하고 번거로운 작업이 필요없다.
3. 재사용성이 증가한다.

### 사용 방법
    1. \<?\> : 타입 파라미터를 대치하는 것으로 모든 클래스나 인터페이스 타입이 올 수 있다.
    2. \<? extends T\> : T와 그 자손들만 올 수 있다.
    3. \<? super T\> : T와 그 조상들만 올 수 있다.

<br>

## 제어자와 접근 제어자
제어자는 클래스, 변수 또는 메서드의 선언부에 함께 사용되어 부가적인 의미를 부여한다. 접근 제어자는 멤버 또는 클래스에 사용되어, 외부로부터의 접근을 제한한다.
- 접근 제어자 : private, default, protected, public
- 그 외 제어자 : static, final, abstract, native, transient, synchronized, volatile, strictfp

### 대표적인 제어자
- static : 클래스의, 공통적인이라는 의미를 가지고 있다. static이 붙은 멤버변수는 인스턴스를 생성하지 않고도 사용 가능하다.
- final :final이 붙은 멤버변수는 상수이다. final이 붙은 메서드는 오버라이딩을 할 수 없다.
- abstract : 추상의, 미완성의. abstract가 붙은 클래스는 추상 클래스이다. 메소드의 선언부만 작성하고 실제 수행 내용은 상속 받은 클래스에서 구현한다.

### 접근 제어자
    - private : 같은 클래스 내에서만 접근이 가능하다.
    - default : 같은 패키지 내에서만 접근이 가능하다. (패키지는 같은 폴더라고 생각하면 됨)
    - protected : 같은 패키지 내에서, 그리고 다른 패키지의 자손 클래스에서 접근이 가능하다.
    - public : 접근 제한이 없다.

접근제어자에서 중요한 점은 클래스 내부에 선언된 데이터를 보호하기 위해서 사용한다는 점이다.
데이터 감추기 혹은 캡슐화라고 한다.

## 싱글톤 패턴 (Singleton Pattern)
    소프트웨어 디자인 패턴에서 싱글턴 패턴(Singleton pattern)을 따르는 클래스는, 
    생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이고 최초 생성 이후에 호출된 생성자는 
    최초의 생성자가 생성한 객체를 리턴한다. 이와 같은 디자인 유형을 싱글턴 패턴이라고 한다. 
    주로 공통된 객체를 여러 개 생성해서 사용하는 DBCP(DataBaseConnection Pool)와 같은 상황에서 많이 사용된다. - 위키백과 -

싱글톤 패턴은 **생성할 수 있는 객체의 인스턴스를 하나로 제한**하는 패턴이다.

```
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
            
        return instance;
    }
}
```

싱글톤 객체가 없을 때 getInstance()를 호출하면 새로 생성하고 그 이후에는 존재하는 인스턴스를 반환하는 방법으로 구현된다.

### 싱글톤 패턴의 장점
1. 메모리 낭비를 방지할 수 있다. 또한 생성된 인스턴스를 재사용함으로써 성능이 개선될 수 있다.
2. 싱글톤으로 만들어진 클래스의 인스턴스는 전역 인스턴스이기 때문에 다른 클래스의 인스턴스들이 데이터를 공유하기 쉽다.
3. 인스턴스를 여러 부분에서 공유해야 하는 경우 유용하다.

### 싱글톤 패턴의 단점
    1. 싱글톤 패턴을 구현하는 코드 자체가 많다.
    2. 의존관계상 클라이언트가 구체 클래스에 의존한다.
    3. 테스트하기 어렵다.
    4. 내부 속성을 변경하거나 초기화하기 어렵다.
    5. private 생성자로 자식 클래스를 만들기 어렵다.
    6. 결론적으로 유연성이 떨어진다.
    7. 하지만 스프링이 해당 단점들을 모두 해결해준다.

스프링에서 싱글톤 관련 코드를 작성하지 않아도 스프링의 기본 기능으로 빈에다가 객체를 1개 설정한다.

주의해야 할 점은 스프링에서 객체 인스턴스를 하나만 생성해 공유하기 때문에 객체 상태를 stateful하게 설계하면 안된다.
코드로 확인해봅시다!

```
public class StatefulService {

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " / price = " + price );
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }
}
```

여기서 문제는 price 필드를 수정하는 것이다. 특정 클라이언트의 price를 보장하지 않기에 문제가 발생하는 것이다.

<br>

## Spring의 의존성 주입
DI(Dependency Injection, 의존성 주입)이란, 어떤 객체가 사용하는 의존 객체를 직접 만들어 사용하는 것이 아니라, 주입 받아 사용하는 방법이다.
(즉 사용할 때마다 new로 생성하는 것이 아닌 해당 필드에 주입을 시켜주는 것이다)

### 의존성 주입 방법 (Spring Documentation 참고)
[Spring Documentation - Dependency Injection](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html)
1. 생성자(Constructor) 주입
```
public class SimpleMovieLister {

	// the SimpleMovieLister has a dependency on a MovieFinder
	private final MovieFinder movieFinder;

	// a constructor so that the Spring container can inject a MovieFinder
	public SimpleMovieLister(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}

	// business logic that actually uses the injected MovieFinder is omitted...
}
```
생성자에서 private final 필드에 주입을 시켜준다.
주로 사용되는 방법이고 Spring에서 공식적으로 기본적으로 권장하는 방법이다. 하지만 이 방법에도 문제가 있을 수 있는데 아래에서 다루겠습니다.

2. 설정자(Setter) 주입
```
public class SimpleMovieLister {

	// the SimpleMovieLister has a dependency on the MovieFinder
	private MovieFinder movieFinder;

	// a setter method so that the Spring container can inject a MovieFinder
	public void setMovieFinder(MovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}

	// business logic that actually uses the injected MovieFinder is omitted...
}
```
여기서는 setter 내부 로직에서 의존성을 주입해준다.

3. 필드(Field) 주입
```
public class SimpleMovieLister {

    // the SimpleMovieLister has a dependency on the MovieFinder
    @Autowired
    private MovieFinder movieFinder;

    // business logic that actually uses the injected MovieFinder is omitted...
}
```
여기서는 @Autowired 라는 어노테이션으로 의존성을 주입해준다. 보기에 굉장히 간편하지만 순환 참조가 발생할 수 있어 권장하지 않는다.
<br>
생성자 주입이나 setter 주입은 런타임 시점에 에러를 잡을 수 있기에 즉각적으로 문제를 해결할 수 있지만 @Autowired 방식은 운영 단계에서 문제를 마주칠 수 있다.
<br>
하지만 이런 간편함으로 테스트 코드에서는 자주 사용된다.

### 위에서 언급했던 문제는? 순환참조
순환참조의 예시를 들어보겠습니다.
클래스 A에서 B라는 인스턴스가 필요해 생성자 주입으로 DI를 하려 하고 반면에 B는 역으로 A의 의존성을 생성자 방식으로 DI 하려 한다고 합시다.
<br>
<br>
이런 경우 Spring IoC Container는 순환 참조를 감지하고 `BeanCurrentlyInCreationException`을 발생시킵니다.
<br>
여기에 대한 해결 방법으로 Spring에서는 생성자 주입 대신 setter 주입을 해결책으로 제시하고 있습니다.

<br>
<br>

## JAR와 WAR
JAR와 WAR 모두 아카이브(압축) 파일로, 애플리케이션을 배포하고 어느 환경에서나 구동시킬 수 있도록 소스나 관련 파일들을 하나로 패키징한 것이다.

### JAR 란?
JAR는 Java ARchive의 약자로, 자바 클래스 파일, 리소스, 메타데이터 등을 하나로 압축한 파일이다.
JAR는 ZIP과 비슷한 맥락이지만 압축을 따로 해제해주지 않아도 JDK에서 접근해 사용할 수 있도록 지원하는 편리함이 있다.

### WAR 란?
WAR는 Web application ARchive 의 약자로, 웹 애플리케이션을 배포할 때 사용하는 파일이다.

WAR는 배포 서술자라고 불리는 web.xml 파일을 통해 path 설정을 따로 해주어야 WAS가 이것을 읽고 실행할 수 있다.

**정리하면?**

    JAR는 JRE만 존재하면 프로젝트 구동이 가능하다.
    WAR는 별도의 웹서버 혹은 WAS가 있어야 프로젝트 구동이 가능하다.