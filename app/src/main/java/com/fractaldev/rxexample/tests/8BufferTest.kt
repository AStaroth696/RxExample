package com.fractaldev.rxexample.tests

import com.fractaldev.rxexample.tests.utils.intLoop
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    bufferTest()
//    advancedBufferTest()
    readLine()
}

private fun bufferTest() {
    val source = (0 until 8)
    Observable.fromIterable(source).buffer(3)
        .subscribeOn(Schedulers.io())
        .map {
            Thread.sleep(1000)
            it
        }.subscribe(
            {
                println("rx___ item: $it")
            }, {}
        )
}

private fun advancedBufferTest() {
    val loop = intLoop()
    loop.subject.observeOn(Schedulers.io())
        .buffer(3)
        .subscribe(
            {
                println("rx___ item: $it")
            }, {}
        )
    loop.start()
}
