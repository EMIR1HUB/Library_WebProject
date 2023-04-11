## Example for use Hibernate

### LibraryProjectApplication
```Java

@SpringBootApplication
@EnableAutoConfiguration(exclude = { //
        DataSourceAutoConfiguration.class, //
        DataSourceTransactionManagerAutoConfiguration.class, //
        HibernateJpaAutoConfiguration.class })
public class LibraryProjectApplication {

    @Autowired
    private Environment environment;
    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("db.driver")));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        System.out.println("## getDataSource: " + dataSource);
        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        properties.put("current_session_context_class", environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));

        // Fix Postgres JPA Error:
        // Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",false);
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        // Package contain entity classes
        factoryBean.setPackagesToScan(new String[] { "" });
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();

        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
    
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() { return new HiddenHttpMethodFilter(); }
}
```

### application.properties
```
# ===============================
# DATABASE
# ===============================
db.driver=org.postgresql.Driver
db.url=jdbc:postgresql://localhost:5432/db_library
db.username=postgres
db.password=3696

# ===============================
# JPA / HIBERNATE
# ===============================
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL82Dialect
spring.jpa.properties.hibernate.current_session_context_class=org.springframework.orm.hibernate5.SpringSessionContext

# Fix Postgres JPA Error:
# Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
```

### pom.xml
```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-orm -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>6.0.3</version>
		</dependency>


		<!--		DAO dependencies -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>6.1.6.Final</version>
		</dependency>
```

### HibernateSessionFactoryUtil

```Java
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(PersonInfo.class);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
```

### PersonInfo
```Java
// Предоставляет данные одной записи оператора запроса

public class PersonInfo {

    private Long id;
    private String fullName;
    private int dateBirth;


    public PersonInfo() {
    }

    // Used in Hibernate query
    public PersonInfo(Long id, String fullName, int dateBirth) {
        this.id = id;
        this.fullName = fullName;
        this.dateBirth = dateBirth;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }
}
```

### Person
```Java

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "date_birth")
    private int dateBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
    }
}
```

### PersonDao
```Java
@Repository
@Transactional
public class PersonDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public PersonDAO() {
    }

    public Person findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    public List<PersonInfo> listPersonInfo() {
        String SQL = "SELECT NEW " + PersonInfo.class.getName() + "(p.id, p.fullName, p.dateBirth) "
                + " FROM " + Person.class.getName() + " p ";
        Session session = this.sessionFactory.getCurrentSession();
        Query<PersonInfo> query = session.createQuery(SQL, PersonInfo.class);
        return query.getResultList();
    }
}
```

### index.html

```html
<table border="1">
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Date Birth</th>
    </tr>
    <tr th:each="person : ${people}">
        <td th:utext="${person.getId()}">user</td>
        <td><a th:href="@{/people/{id}(id=${person.getId()})}" th:text="${person.getFullName()}">user</a></td>
        <td th:utext="${person.getDateBirth()}">user</td>
    </tr>
</table>

<br><hr>

<a href="/people/new">Create new person</a>
```