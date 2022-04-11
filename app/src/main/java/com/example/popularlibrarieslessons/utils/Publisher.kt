package com.example.popularlibrarieslessons.utils

import android.util.Log

private const val TAG ="@@@ Publisher"

private typealias Subscriber<T> = (T) ->Unit
class Publisher<T> {

    fun post(value: T) {
        hasFirstValue = true
        this.value = value
        subscribers.forEach { it.invoke(value) }
    }
    private val subscribers: MutableSet<Subscriber<T>> = mutableSetOf()
    private var value: T? = null

    private var hasFirstValue = false

    fun subscribe(subscriber: Subscriber<T>) {
        Log.d(TAG, "subscribe() called with: subscriber = $subscriber")
        subscribers.add(subscriber)
        if (hasFirstValue) {
            value?.let { subscriber.invoke(it) }
        }
    }

    fun unsubscribe(subscriber: Subscriber<T>) {
        Log.d(TAG, "unsubscribe() called with: subscriber = $subscriber")
        subscribers.remove(subscriber)
    }

    fun unsubscribeAll() {
        Log.d(TAG, "unsubscribeAll() called")
        subscribers.clear()
    }
}

