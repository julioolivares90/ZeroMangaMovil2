package com.zerodev.zeromanga.ui.favoritos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zerodev.zeromanga.R

class FavoritosFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritosFragment()
    }

    private lateinit var viewModel: FavoritosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favoritos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}