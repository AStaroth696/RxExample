package com.fractaldev.rxexample.tests

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.random.Random

fun main() {
//    mergeTest()
    concatTest()
    readLine()
}

private fun mergeTest() {
    val source1 = (0 until 1).map { "source1:$it" }
    val observable1 = Observable.fromIterable(source1).map {
        val timeout = Random.nextLong(2000)
        Thread.sleep(timeout)
        "$it timeout: $timeout"
    }.subscribeOn(Schedulers.newThread())
    val source2 = (0 until 5)
    val observable2 = Observable.fromIterable(source2).map {
        val timeout = Random.nextLong(2000)
        Thread.sleep(timeout)
    }.subscribeOn(Schedulers.newThread())
        .map { it.toString() }
    Observable.merge(observable1, observable2)
//    observable1.mergeWith(observable2)
        .subscribe(
            {
                println("rx___ item: $it")
            }, {}
        )

}

private fun concatTest() {
    val source1 = (0 until 5).map { "source1:$it" }
    val observable1 = Observable.fromIterable(source1).map {
        val timeout = Random.nextLong(2000)
        Thread.sleep(timeout)
        "$it timeout: $timeout"
    }.subscribeOn(Schedulers.newThread())
    val source2 = (0 until 5).map { "source2:$it" }
    val observable2 = Observable.fromIterable(source2).map {
        val timeout = Random.nextLong(2000)
        Thread.sleep(timeout)
        "$it timeout: $timeout"
    }.subscribeOn(Schedulers.newThread())
    Observable.concat(observable1, observable2)
//    observable1.concatWith(observable2)
        .subscribe(
            {
                println("rx___ item: $it")
            }, {}
        )
}