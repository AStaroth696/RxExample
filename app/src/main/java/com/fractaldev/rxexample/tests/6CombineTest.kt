package com.fractaldev.rxexample.tests

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.random.Random

fun main() {
    combineTest()
    readLine()
}

private fun combineTest() {
    val source1 = (0 until 5).map { "item$it" }
    val observable1 = Observable.fromIterable(source1).subscribeOn(Schedulers.newThread())
        .map {
            val timeout = Random.nextLong(3000)
            Thread.sleep(timeout)
            "$it, timeout: $timeout"
        }
    val source2 = (0 until 7).toList()
    val observable2 = Observable.fromIterable(source2).subscribeOn(Schedulers.newThread())
        .map {
            val timeout = Random.nextLong(3000)
            Thread.sleep(timeout)
            "$it, timeout: $timeout"
        }
    Observable.combineLatest(observable1, observable2) { first, second ->
        "first: $first, second: $second"
    }.subscribe(
        {
            println("rx___ $it")
        }, {}
    )
}