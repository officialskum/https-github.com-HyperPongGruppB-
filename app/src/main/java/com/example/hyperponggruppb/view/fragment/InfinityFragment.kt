package com.example.hyperponggruppb.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hyperponggruppb.view.InfinityView


class InfinityFragment : Fragment() {

    //Contains points board and game View for infinity mode

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return activity?.applicationContext?.let { InfinityView(super.getContext(), activity!!) }
    }
}