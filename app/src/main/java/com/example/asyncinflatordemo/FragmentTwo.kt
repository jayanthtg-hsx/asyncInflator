package com.example.asyncinflatordemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment

class FragmentTwo : Fragment() {

    private val lazyInflater by lazy { AsyncLayoutInflater(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_one, container, false) as ViewGroup
        lazyInflater.inflate(R.layout.text_views, root)
        { view, res, parent -> parent!!.addView(view) }
        return root
    }
}