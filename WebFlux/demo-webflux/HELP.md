# Getting Started

Reactor — это полностью неблокирующая основа реактивного программирования для JVM с эффективным управление
Он предлагает составные API-интерфейсы асинхронной последовательности 
* Flux (для элементов [N])
* Mono (для элементов [1])


 Mono нужен для работы с единственным объектом.
Mono также может использоваться как какая-то асинхронная задача в стиле “выполнил и забыл”, без возвращаемого результата (очень похож на Runnable)

Flux — это Publisher, способный выпустить от 0 до N событий (элементов), в том числе и бесконечное их число.

Flux и Mono — lazy.
Для того чтобы запустить какую-то обработку и воспользоваться данными, лежащими в Mono и Flux, нужно на них подписаться с помощью subscribe().

 реализует спецификацию Reactive Streams.
## Describe traditional (RestController) and stream's (Route) webflux using
lets code
There are endpoint 
```http
 http://localhost:8080/hello?start=2&count=1
```

### base 
```http
http://localhost:8080/hello
```

```http request
http://localhost:8080/controller?start=2&count=10
```

## Notes 
Spring Framework 4 представил ListenableFuture — это Future реализация, которая добавляет 
неблокирующие возможности на основе обратного вызова.

Потом в java 8 появились *CompletableFuture*

CompletableFuture позволяет иметь дело с будущим неблокирующим образом обеспечивая возможности для цепочки 
отложенной обработки результатов
CompletableFuture используется для асинхронного программирования в Java. Асинхронное программирование
 — это средство написания неблокирующего кода путём выполнения задачи в отдельном, отличном от главного
 , потоке, а также уведомление главного потока о ходе выполнения, завершении или сбое
 
 
 ```
 CompletableFuture completableFuture = new CompletableFuture();
completableFuture.whenComplete(new BiConsumer() {
    @Override
    public void accept(Object o, Object o2) {
        //handle complete
    }
});     // complete the task
completableFuture.complete(new Object())
```
Для того, чтобы асинхронно выполнить некоторую фоновую задачу, которая не возвращает, результат, 
можно использовать метод CompletableFuture.runAsync(). Он принимает объект Runnable и возвращает
 CompletableFuture<Void>.

CompletableFuture выполняет эти задачи в потоке, полученном из глобального ForkJoinPool.commonPool().


Mono является реактивным эквивалентом CompletableFuture типа


```
Publisher<String> just = Mono.just("one");
```

В основе подхода лежит идея разделения компонентов на 2 типа: источник событий (Publisher) и обработчик событий (Subscriber).
Subscriber подписывается на события, которые создаёт Publisher, а затем каким-то образом их обрабатывает.
По сути это паттерн *Observer* с надстроенными поверх возможностями и особенностями.

### получить поток 
* Flux flux1 = Flux.just(“foo”, “bar”, “foobar”);
 * Flux flux2 = Flux.fromIterable(Arrays.asList(“A”, “B”, “C”));
 * Flux flux3 = Flux.range(5, 3);
 
 
 На самом деле, при вызове нового каждого оператора в цепочке создаётся новый Publisher, который добавляется к цепочке.
Например:

```
Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .limitRequest(5)
        .skip(3)
        .subscribe(value -> System.out.println("Value: " + value));
	
```		

Что касается исключений, так они являются терминальными. Это значит что поток сразу 
завершает свою работу.

Вы можете вернуть значение по умолчанию в случае ошибки выполнения метода при помощи *onErrorReturn()*.



Spring 5 представил платформу **WebFlux**, которая представляет собой полностью асинхронный и 
неблокирующий реактивный веб-стек, который позволяет обрабатывать огромное количество одновременных 
соединений.

*WebFlux* позиционирует себя как микрофреймворк.
Этот новый микрофреймворк поддерживает аннотированные контроллеры, функциональные конечные точки, WebClient (аналог RestTemplate в Spring Web MVC), 
WebSockets и многое другое. по умолчанию использует Netty. Tomcat не поддерживает реактивщину.

