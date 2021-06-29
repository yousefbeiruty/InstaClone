package com.it.yousefl.navigationex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_first, container, false)

        view.setOnClickListener {
            val acton=FirstFragmentDirections.navigateToSecondFragment(22)
            Navigation.findNavController(view).navigate(acton)
        }


        return view
    }


}