package com.it.yousefl.navigationex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs


class SecondFragment : Fragment() {

    private val args:SecondFragmentArgs by navArgs()
    private lateinit var text:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_second, container, false)
      text=view.findViewById(R.id.text)
        val myNumber=args.number
        text.text="$myNumber"
        view.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToFirstFragment)
        }

        return view
    }

}