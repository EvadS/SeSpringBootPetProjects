# Getting Started


**onSubscribe()** —вызывается когда мы подписываемся на поток

request(unbounded) — вызывается когда мы вызываем подписку (subscribe), за кулисами
мы создаем Subscription. Эта подписка запрашивает элементы из потока. В этом 
случае он запрашивает каждый доступный элемент.

onNext() — вызывается на каждом элементе
onComplete() — вызывается последним, после получения последнего элемента.

## demos
После того, как Flux/Mono отправил какие-то данные, подписка на эти события происходит просто: вызываем метод subscribe().

параметры 
1. передаем лябду - что делать с каждым елементов 
2. что делать если ошибка
3. что делать по завершению (onCompleted)

```java
Flux.just(1, 2, 3, 4)
        .subscribe(value -> System.out.println("Value: " + value),
                error -> {},
                () -> System.out.println("Successfull"));
```

### Вы можете создать свой Flux
- при помощи метода .generate()
- при помощи метода .create()
- при помощи just()
- justOrEmpty — выводит Mono<>, если элемент не null, иначе сигнал завершения
- fromArray
- fromIterable
- range
- fromStream
- fromSupplier
- fromRunnable
- fromFuture
- из стороннего Publisher