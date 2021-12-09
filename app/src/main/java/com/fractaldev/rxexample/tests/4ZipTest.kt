package com.fractaldev.rxexample.tests

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    zipTest()
    readLine()
}

private fun zipTest() {
    Single.zip(
        Single.fromCallable {
            println("rx___ first: ${Thread.currentThread()}")
            Thread.sleep(2000)
            "first"
        }.subscribeOn(Schedulers.io()),
        Single.fromCallable {
            println("rx___ second: ${Thread.currentThread()}")
            Thread.sleep(2000)
            "second"
        }.subscribeOn(Schedulers.io())
    ) { first, second ->
        "$first and $second"
    }.subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.computation())
        .subscribe(
            {
                println("rx___ result: $it on ${Thread.currentThread()}")
            }, {

            }
        )
}