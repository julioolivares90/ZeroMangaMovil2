package com.zerodev.zeromanga.ui.favoritos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.databinding.FavoritosFragmentBinding
import org.koin.android.ext.android.inject


class FavoritosFragment : Fragment(R.layout.favoritos_fragment) {

    private lateinit var binding : FavoritosFragmentBinding
    companion object {
        fun newInstance() = FavoritosFragment()
    }

    private  val viewModel: FavoritosViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavoritosFragmentBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this).get(FavoritosViewModel::class.java)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}