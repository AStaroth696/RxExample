package com.fractaldev.rxexample.tests

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    composeTest()
    readLine()
}

private fun composeTest() {
    Observable.fromIterable((0 until 5).toList())
        .compose(applyTransformers())
        .subscribe(
            {
                println("rx___ $it")
            }, {}
        )
}

private fun applyTransformers(): ObservableTransformer<Int, String> {
    return ObservableTransformer<Int, String> { upstream ->
        upstream.subscribeOn(Schedulers.newThread())
            .map { number -> "item $number" }
            .observeOn(Schedulers.io())
    }

}

