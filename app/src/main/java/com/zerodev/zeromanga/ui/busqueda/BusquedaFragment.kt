package com.zerodev.zeromanga.ui.busqueda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.databinding.BusquedaFragmentBinding

class BusquedaFragment : Fragment(R.layout.busqueda_fragment) {

    private lateinit var binding: BusquedaFragmentBinding
    private lateinit var viewModel: BusquedaViewModel
    companion object {
        fun newInstance() = BusquedaFragment()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BusquedaFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(BusquedaViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.svBuscarManga.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(requireContext(),"Buscando",Toast.LENGTH_SHORT)
                    .show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        ArrayAdapter.createFromResource(requireContext()
            ,R.array.sp_busqueda_array
            ,android.R.layout.simple_spinner_dropdown_item).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spBusqueda.adapter = adapter
        }

        ArrayAdapter.createFromResource(requireContext(),
            R.array.sp_ordenar_por_array,
            android.R.layout.simple_spinner_dropdown_item).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spOrdenarPor.adapter =adapter
        }
        ArrayAdapter.createFromResource(requireContext(),
            R.array.sp_order_by_desc_asc_array,
            android.R.layout.simple_spinner_dropdown_item
            ).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spOrderByDescAsc.adapter = adapter
        }
    }
}