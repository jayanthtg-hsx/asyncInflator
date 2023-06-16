package com.example.asyncinflatordemo.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment
import com.example.asyncinflatordemo.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MVIFragment : Fragment() {

    private val lazyInflater by lazy { AsyncLayoutInflater(requireContext()) }
    private val parent by lazy { FrameLayout(requireContext()) }
    private val viewModel = MviViewModel()
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lazyInflater.inflate(R.layout.layout_mvi_fragment, parent) { view, _, _ ->
            parent.addView(view)
            //let viewmodel know once the view is inflated so that view model can start sending events.
            viewModel.onAction(Action.OnReady)
        }
        return parent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //with normal inflation view will be ready by the time onViewCreated() called, now with
        //async layout inflater view may not be ready onViewCreated(), instead of blindly requesting
        //events from viewmodel on onViewCreated() we rely on new Action called 'OnReady' which will
        // be fired in async layout inflater callback. upon receiving this event viewmodel can start
        // sending the events to Fragments/Activities or replay the events.
        scope.launch { viewModel.events.collect(::onEvent) }
    }

    private fun onEvent(event: Events) {
        when (event) {
            is Events.Content -> onContent(event.content)
            is Events.Error -> onError(event.throwable)
            Events.Loading -> onLoading()
        }
    }

    private fun onLoading() {
        //logic for loading
    }

    private fun onError(throwable: Throwable) {
        //logic for error
    }

    private fun onContent(value: String) {
        //logic for showing content
    }
}