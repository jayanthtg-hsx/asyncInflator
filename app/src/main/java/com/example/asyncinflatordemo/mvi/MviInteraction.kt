package com.example.asyncinflatordemo.mvi

sealed class Events {
    object Loading : Events()
    class Content(val content: String): Events()
    class Error(val throwable: Throwable): Events()
}

sealed class Action {
    object OnReady: Action()
    object ReLoad: Action()
}