package com.sample.quoter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Evgeniy Skiba on 12.03.21
 * профилирование - в лог выводится время сколько работает метод
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Profiling {
}
