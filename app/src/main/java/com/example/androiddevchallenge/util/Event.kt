package com.example.androiddevchallenge.util

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

    fun copy() = Event(content)
}


//@Stable
//interface EventState<T> : State<T> {
//   override val value: T
//}


/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}

//@Composable
//fun <T> LiveData<T>.observeAsEventState(): State<T?> = observeAsEventState(value)
//
//@Composable
//fun <R, T : R> LiveData<T>.observeAsEventState(initial: R): State<R> {
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val state = remember { mutableStateOf(initial) }
//    DisposableEffect(this, lifecycleOwner) {
//        val observer : Observer<T> = EventObserver<T> { state.value = it } as Observer<T>
//        observe(lifecycleOwner, observer)
//        onDispose { removeObserver(observer) }
//    }
//    return state
//}
