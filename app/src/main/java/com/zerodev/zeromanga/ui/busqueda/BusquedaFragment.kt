package com.zerodev.zeromanga.ui.busqueda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.AdapterMangaBusqueda
import com.zerodev.zeromanga.databinding.BusquedaFragmentBinding
import com.zerodev.zeromanga.net.models.Response
import com.zerodev.zeromanga.net.models.ResponseManga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusquedaFragment : Fragment(R.layout.busqueda_fragment) {

    private lateinit var binding: BusquedaFragmentBinding
    private lateinit var viewModel: BusquedaViewModel
    private lateinit var adapterMangaBusqueda: AdapterMangaBusqueda

    private var sp_order_field = ""
    private var sp_order_item = ""
    private var sp_order_dir = ""


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

        setSpinners()
        setSpinnerOrderItem()
        setSpinnerOrderDir()
        setSpinnerOrderField()
        binding.svBuscarManga.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                Toast.makeText(requireContext(),"Buscando",Toast.LENGTH_SHORT)
                    .show()

                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {

                   viewModel.findMangas(
                       title = query!!,
                       orderField = sp_order_field,
                       orderItem = sp_order_item,
                       orderDir = sp_order_dir
                   )

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


        viewModel.IsLoading().observe(viewLifecycleOwner, Observer {

        })

        viewModel.getMangasBusqueda().observe(viewLifecycleOwner, Observer {
            val result = it
            when(result){
                is ResponseManga.Success<Response> -> {
                    adapterMangaBusqueda = AdapterMangaBusqueda(result.data.data)
                }else ->{}
            }
            binding.rvMangasBusqueda.adapter = adapterMangaBusqueda
        })
    }

    private fun setSpinners(){
        ArrayAdapter.createFromResource(requireContext()
            ,R.array.sp_order_field
            ,android.R.layout.simple_spinner_dropdown_item).also {
                adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spOrderField.adapter = adapter
        }

        ArrayAdapter.createFromResource(requireContext(),
            R.array.sp_order_item,
            android.R.layout.simple_spinner_dropdown_item).also {
                adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spOrderItem.adapter =adapter
        }
        ArrayAdapter.createFromResource(requireContext(),
            R.array.sp_order_dir,
            android.R.layout.simple_spinner_dropdown_item
        ).also {
                adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spOrderDir.adapter = adapter
        }
    }
    private fun setSpinnerOrderItem(){
        binding.spOrderItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sp_order_item= parent?.getItemAtPosition(position).toString()
                sp_order_item.let {
                    Toast.makeText(requireContext(),sp_order_item,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
    private fun setSpinnerOrderField(){
        binding.spOrderField.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sp_order_field = parent?.getItemAtPosition(position).toString()
                sp_order_field.let {
                    Toast.makeText(requireContext(),sp_order_field,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun setSpinnerOrderDir(){
        binding.spOrderDir.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sp_order_dir = parent?.getItemAtPosition(position).toString()
                sp_order_dir.let {
                    Toast.makeText(requireContext(),sp_order_dir,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}