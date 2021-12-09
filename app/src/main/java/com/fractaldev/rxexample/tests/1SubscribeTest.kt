package com.fractaldev.rxexample.tests

import io.reactivex.rxjava3.core.Observable
import kotlin.random.Random

fun main() {
    subscribeTest()
    readLine()
}

private fun subscribeTest() {
    val observable = Observable.just( Random.nextInt() )
//    val observable = Observable.fromCallable { Random.nextInt() }
    observable.subscribe(
        {
            println("rx___ first subscribe: $it")
        }, {}
    )
    observable.subscribe(
        {
            println("rx___ second subscribe: $it")
        }, {}
    )
}