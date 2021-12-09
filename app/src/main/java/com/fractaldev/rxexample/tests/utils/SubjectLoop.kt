package com.fractaldev.rxexample.tests.utils

import io.reactivex.rxjava3.subjects.BehaviorSubject

fun intLoop(
    prompt: String = "Enter the number",
    terminator: String = "quit",
    transformer: (String) -> Int = { try { it.toInt() } catch (e: NumberFormatException) { 0 } }
): SubjectLoop<Int> {
    return SubjectLoop.Builder<Int>()
        .withPrompt(prompt)
        .withTerminator(terminator)
        .withTransformer(transformer)
        .build()
}

fun stringLoop(
    prompt: String = "Enter the string",
    terminator: String = "quit",
    transformer: (String) -> String = { it }
): SubjectLoop<String> {
    return SubjectLoop.Builder<String>()
        .withPrompt(prompt)
        .withTerminator(terminator)
        .withTransformer(transformer)
        .build()
}

class SubjectLoop<T> private constructor(
    private val prompt: String,
    private val terminator: String,
    private val transformer: (String) -> T
) {

    val subject = BehaviorSubject.create<T>()

    fun start() {
        var line: String
        while (true) {
            println(prompt)
            line = readLine() ?: ""
            if (line == terminator) {
                break
            }
            subject.onNext(transformer(line))
        }
        subject.onComplete()
    }

    class Builder<T> {
        private var prompt: String = ""
        private var terminator: String = ""
        private var transformer: ((String) -> T)? = null

        fun withPrompt(prompt: String): Builder<T> = apply {
            this.prompt = prompt
        }

        fun withTerminator(terminator: String): Builder<T> = apply {
            this.terminator = terminator
        }

        fun withTransformer(transformer: (String) -> T): Builder<T> = apply {
            this.transformer = transformer
        }

        fun build(): SubjectLoop<T> {
            return SubjectLoop(
                prompt = prompt,
                terminator = terminator,
                transformer = transformer
                    ?: throw IllegalStateException("Transformer must be specified")
            )
        }
    }

}