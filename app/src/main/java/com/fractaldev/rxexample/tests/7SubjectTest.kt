package com.fractaldev.rxexample.tests

import com.fractaldev.rxexample.tests.utils.emitOnNext
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject

fun main() {
//    behaviorSubjectTest()
//    publishSubjectTest()
//    replaySubjectTest()
    asyncSubjectTest()
    readLine()
}

private fun behaviorSubjectTest() {
    val subject = BehaviorSubject.create<String>()
    subject.subscribe(
        {
            println("rx___ next: $it")
        }, {}
    )
    subject.onNext("test0")
    Observable.just("test", "test1")
        .doOnComplete {
            println("rx___ complete")
        }.emitOnNext(subject)
        .subscribe()
    subject.onNext("test2")
    subject.subscribe(
        {
            println("rx___ new subscriber: $it")
        }, {}
    )
    subject.onNext("new value")
}

private fun publishSubjectTest() {
    val subject = PublishSubject.create<String>()
    subject.subscribe(
        {
            println("rx___ next: $it")
        }, {}
    )
    subject.onNext("test0")
    Observable.just("test", "test1")
        .doOnComplete {
            println("rx___ complete")
        }.emitOnNext(subject)
        .subscribe()
    subject.onNext("test2")
    subject.subscribe(
        {
            println("rx___ new subscriber: $it")
        }, {}
    )
    subject.onNext("new value")
}

private fun replaySubjectTest() {
    val subject = ReplaySubject.create<String>(5)
//    val subject = ReplaySubject.create<String>()
    subject.subscribe(
        {
            println("rx___ next: $it")
        }, {}
    )
    subject.onNext("test0")
    Observable.just("test", "test1")
        .doOnComplete {
            println("rx___ complete")
        }.emitOnNext(subject)
        .subscribe()
    subject.onNext("test2")
    Thread.sleep(1000)
    subject.subscribe(
        {
            println("rx___ new subscriber: $it")
        }, {}
    )
    subject.onNext("new value")
}

private fun asyncSubjectTest() {
    val subject = AsyncSubject.create<String>()
    subject.subscribe(
        {
            println("rx___ next: $it")
        }, {}
    )
    subject.onNext("test0")
    Observable.just("test", "test1")
        .doOnComplete {
            println("rx___ complete")
        }//.emitOnNext(subject)
        .subscribe(subject)
    subject.onNext("test2")
    subject.onComplete()
    subject.subscribe(
        {
            println("rx___ new subscriber: $it")
        }, {}
    )
    subject.onNext("new value")
    subject.onComplete()
}



/**
 BehaviorSubject                    PublishSubject

rx___ next: test0                   rx___ next: test0
rx___ next: test                    rx___ next: test
rx___ next: test1                   rx___ next: test1
rx___ complete                      rx___ complete
rx___ next: test2                   rx___ next: test2
rx___ new subscriber: test2
rx___ next: new value               rx___ next: new value
rx___ new subscriber: new value     rx___ new subscriber: new value
 **/