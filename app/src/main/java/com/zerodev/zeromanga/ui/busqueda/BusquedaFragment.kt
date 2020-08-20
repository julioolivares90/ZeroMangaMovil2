package com.zerodev.zeromanga.ui.busqueda

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zerodev.zeromanga.R

class BusquedaFragment : Fragment() {

    companion object {
        fun newInstance() = BusquedaFragment()
    }

    private lateinit var viewModel: BusquedaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.busqueda_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BusquedaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}