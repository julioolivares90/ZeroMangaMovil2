package com.zerodev.zeromanga.ui.tmo.lector

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.databinding.LectorFragmentBinding
import com.zerodev.zeromanga.ui.tmo.descripcion.DescripcionViewModel

class LectorFragment : Fragment() {

    companion object {
        fun newInstance() = LectorFragment()
    }

    private lateinit var viewModel: LectorViewModel

    private lateinit var binding : LectorFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LectorFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(LectorViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}