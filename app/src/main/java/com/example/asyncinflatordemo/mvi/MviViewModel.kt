package com.example.asyncinflatordemo.mvi

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MviViewModel {
    private val _events = MutableSharedFlow<Events>()
    val events = _events.asSharedFlow()

    fun onAction(action: Action) {
        when (action) {
            Action.OnReady -> onReady()
            Action.ReLoad -> onReload()
        }
    }

    private fun onReady() {
        _events.tryEmit(Events.Loading)
        _events.tryEmit(Events.Content("title"))
    }

    private fun onReload() {
        // doRetry() logics
        _events.tryEmit(Events.Loading)
        _events.tryEmit(Events.Content("title"))
    }
}