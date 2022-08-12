# Getting Started

@EnableTransactionManagement является необязательным в Spring boot, при условии, что spring-data* или 
spring-tx находятся в classpath.

### Types of Transaction management in Spring:
* Local — голый JDBC
* Programmatic
```java
    Transaction transaction = entityManager.getTransaction();
    
try{
        transaction.begin();
        transaction.commit();
    }catch(Exception e){transaction.rollback();
        throw e;
    }
```
* Declarative
@Transactional or XML based approach

Spring создает proxy для классов, объявленных аннотацией **@Transactional**. Proxy в большинстве случаев невидим во 
время выполнения. Он предоставляет способ для Spring вводить поведение “до”, “после” или “во время” 
вызовов методов в proxy-объект. 

Поэтому, когда мы определяем метод с **@Transactional**, Spring динамически создает proxy. Когда клиенты 
совершают вызовы в наш объект, вызовы перехватываются, а поведение будет вводиться через механизм proxy.

----------------------
одно соединение — только одна открытая транзакция. Нет возможности в рамках
одного соединения выполнять одновременно несколько команд. 

По умолчанию Spring ищет есть ли у нас бин с именем **TransactionManager**. Если есть, то Spring его найдет и сам 
подключит, нам не надо его дополнительно настраивать

**TransactionManager** создает **EntityManager**, если он необходим, и осуществляет старт новой транзакции. 

**Transactional**
только на public методы

------------
поведение транзакции по отношению к остальным - propagation

### Параметры аннотации @Transactional:
```java
@Transactional(
        value = “testTransaction”,
        isolation = Isolation.SERIALIZABLE,
        propagation = Propagation.SUPPORTS, 
        readOnly = true, 
        timeout = 1,
        rollbackFor = “IOException”
)
```

где, _readOnly_ — имеет значение только внутри транзакции. Если операция происходит вне контекста транзакции, флаг
 просто игнорируется
_timeout_ — выставляется именно для транзакций (надо вставлять в том месте, где начинается новая транзакция). По 
умолчанию, если возникает 
_RuntimeException_ — то транзакция будет откатываться. 
 Если _checked exception_ — то транзакция не будет откатываться. По умолчанию используется таймаут, установленный
  по умолчанию для базовой транзакционной системы.
_rollbackFor_ — указываем роллбэк для определенного exception rollbackForClassName
_noRollbackFor_ — Указывает, что откат не должен происходить, если целевой метод вызывает исключение, которое вы укажете.
noRollbackForClassNam


TEST 1

http://localhost:8080/candidate/valid

в CandidateValidateService  мы указываем что транзакция должна пройти в случае IllegalArgumentException(по скольку 
мы обрабатываем его уровнем выше)
```json
{
      "name" : "firstname",
      "age" : "23",
      "gender":"man"
}
```

### пояснение
если один транзакционный метод вызывает другой транзакционный метод в другом классе и этот внутренний вызов 
выбрасывает Runtime exception, то вся транзакция целиком будет отменена. В этом случае можно использовать параметр 
noRollBackFor или выполнить внутреннюю транзакцию в новой транзакции (свойства propagation).


Spring автоматически откатывает транзакции для unchecked (Runtime) исключений:
----
TEST 2
здесь базовый пример. Сервис аннотьирован при помощи аннотации @Transaction - и по сему когда выскакиев ексепшн 
выполняется роллбек и в базу ничего не ложится  

http://localhost:8080/candidate/valid
```json
{
      "name" : "firstname",
      "age" : "23",
      "gender":"man"
}
```

