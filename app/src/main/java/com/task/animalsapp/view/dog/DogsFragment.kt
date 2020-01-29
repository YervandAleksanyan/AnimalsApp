package com.task.animalsapp.view.dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.task.animalsapp.R
import com.task.animalsapp.databinding.FragmentDogBinding

class DogsFragment : Fragment() {

    companion object {
        fun newInstance() = DogsFragment()
    }

    private lateinit var binding: FragmentDogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog, container, false)
        return binding.root
    }
}