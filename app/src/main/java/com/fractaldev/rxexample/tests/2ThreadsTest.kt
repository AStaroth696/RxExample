package com.fractaldev.rxexample.tests

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    threadsTest()
    readLine()
}

private fun threadsTest() {
    Observable.fromCallable {
        println("rx___ Thread: ${Thread.currentThread()}")
        ""
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnEach {
            it.value
        }
        .observeOn(Schedulers.newThread())
        .map {
            println("rx___ map1: ${Thread.currentThread()}")
            it
        }.observeOn(Schedulers.computation())
        .map {
            println("rx___ map2: ${Thread.currentThread()}")
            it
        }
        .observeOn(Schedulers.io())
        .subscribe(
            {
                println("rx___ next: ${Thread.currentThread()}")
            },
            {
                println("rx___ error: $it")
            }
        )
}