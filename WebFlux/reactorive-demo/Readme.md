

# Getting starting with reactive developments

## Kirill sereda tutorials
|КАТЕГОРИЯ ОПЕРАТОРА| ПРИМЕРЫ |
| ---: | :--- |
|Создание новой последовательности |just, fromArray, fromIterable, fromStream|
|Преобразование существующей последовательности |map, flatMap, startWith, concatWith|
|Заглядывать в последовательность |doOnNext, doOnComplete, doOnError, doOnCancel|
|Фильтрация последовательности |filter, ignoreElements, distinct, elementAt, takeLast|
|Обработка ошибок |onErrorReturn, onErrorResume, retry|
|Работаем со временем|elapsed, interval, timestamp, timeout|
|Расщепление потока|buffer, groupBy, window|
|Возвращаясь к синхронному миру |block, blockFirst, blockLast, toIterable, toStream|
|Многоадресная рассылка потока нескольким подписчикам |publish, cache, replay|

## Холодные и горячие Publisher

Холодные , ничего не происходит, пока мы не подпишемся
hot издатель не зависит от подписчиков

Чтобы получить более значимую трассировку стека, которая включает информацию о сборке (также называемую трассировкой),
вы можете добавить вызов **Hooks.onOperatorDebug()** в свое приложение. Однако это нельзя использовать в производственной среде, потому что это связано с перемещением тяжелого стека и может отрицательно повлиять на производительность
