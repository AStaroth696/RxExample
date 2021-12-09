package com.fractaldev.rxexample.tests

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {
//    flatmapTest()
//    readLine()
    switchMapTest()
//    readLine()
//    concatMapTest()
    readLine()
}

private fun concatMapTest() {
    println("rx___ concatmap----------------------------------")
    Flowable.just(0, 1, 2, 3, 4)
        .subscribeOn(Schedulers.io())
        .concatMap {
            val delay = Random.nextLong(4)
            println("rx___ item: $it, delay: $delay")
            Flowable.just(it).delay(delay, TimeUnit.SECONDS)
        }.observeOn(Schedulers.computation())
        .subscribe(
            {
                println("rx___ next: $it")
            }, {

            }
        )
}

private fun switchMapTest() {
    println("rx___ switchmap----------------------------------")
    Flowable.just(0, 1, 2, 3, 4)
        .subscribeOn(Schedulers.io())
        .switchMap {
            val delay = Random.nextLong(4)
            println("rx___ item: $it, delay: $delay")
            Flowable.just(it).delay(delay, TimeUnit.SECONDS)
        }.observeOn(Schedulers.computation())
        .subscribe(
            {
                println("rx___ next: $it")
            }, {

            }
        )
}

private fun flatmapTest() {
    println("rx___ flatmap----------------------------------")
    Flowable.just(0, 1, 2, 3, 4)
        .subscribeOn(Schedulers.io())
        .flatMap {
            val delay = Random.nextLong(4)
            println("rx___ item: $it, delay: $delay")
            Flowable.just(it).delay(delay, TimeUnit.SECONDS)
        }.observeOn(Schedulers.computation())
        .subscribe(
            {
                println("rx___ next: $it")
            }, {

            }
        )
}