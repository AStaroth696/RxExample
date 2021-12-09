package com.fractaldev.rxexample.tests.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.Subject

fun <O: Observable<T>, T: Any> O.emitOnNext(subject: Subject<T>): Observable<T> {
    return doOnNext {
        subject.onNext(it)
    }
}
