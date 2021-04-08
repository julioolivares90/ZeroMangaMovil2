package com.zerodev.zeromanga.ui.tmo.descripcion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.DescripcionViewPagerAdapter
import com.zerodev.zeromanga.databinding.DescripcionFragmentBinding
import com.zerodev.zeromanga.net.models.MangaResponse
import com.zerodev.zeromanga.utlities.constantes.ENVIAR_URL
import org.koin.android.ext.android.inject


class DescripcionFragment : Fragment(R.layout.descripcion_fragment) {

    companion object {
        fun newInstance() = DescripcionFragment()
    }

    private  val viewModel: DescripcionViewModel by inject()

    private lateinit var binding: DescripcionFragmentBinding

    private lateinit var descripcionViewPagerAdapter : DescripcionViewPagerAdapter

    private lateinit var mangaResponse: MangaResponse

    private lateinit var mangaUrlRefer : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DescripcionFragmentBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this).get(DescripcionViewModel::class.java)

        val arguments = arguments

        val mangaUrl = arguments?.getString(ENVIAR_URL)

        mangaUrl.let {
            viewModel.setInfoManga(it!!)
            mangaUrlRefer = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.IsLoading().observe(viewLifecycleOwner, Observer {
            if (it){
                binding.pbDescripcion.visibility = View.VISIBLE
                hideComponents()
            }
            else{
                binding.pbDescripcion.visibility = View.GONE
                showComponents()
            }

        })
        setUpViewPager()

        binding.fbAddMagafav.setOnClickListener {

        }
    }

    fun setUpViewPager() {
        viewModel.getInfoManga().observe(viewLifecycleOwner, Observer {
            mangaResponse = it


            //(activity as AppCompatActivity).setSupportActionBar()
            val toolbar = (activity as AppCompatActivity).supportActionBar

            toolbar?.title = mangaResponse.data.title
           /*
            val tb_imagen= toolbar?.customView?.findViewById<ImageView>(R.id.tb_imagen)
            tb_imagen?.let {imagen ->
                Glide.with(requireContext()).load(mangaResponse.data.image).into(imagen)
            }

            */

            //Glide.with(requireContext()).load(mangaResponse.data.image).into(binding.imageView)
            descripcionViewPagerAdapter = DescripcionViewPagerAdapter(mangaResponse,mangaUrlRefer = mangaUrlRefer ,this)
            binding.viewPagerDescripcion.adapter = descripcionViewPagerAdapter

            TabLayoutMediator(binding.tabLayout,binding.viewPagerDescripcion,
                TabLayoutMediator.TabConfigurationStrategy{ tab: TabLayout.Tab, position: Int ->
                    when(position){
                        0-> {
                            tab.text = "Informacion"
                            tab.icon = resources.getDrawable(R.drawable.ic_baseline_recent_actors_24)
                        }
                        1-> {
                            tab.text = "Capitulo"
                            tab.icon = resources.getDrawable(R.drawable.ic_baseline_list_24)
                        }
                    }
                }).attach()
        })
    }

    fun destroyViewPager(){
        binding.viewPagerDescripcion.adapter = null
    }

    fun hideComponents(){
       // binding.imageView.visibility = View.GONE
        binding.viewPagerDescripcion.visibility = View.GONE
        binding.tabLayout.visibility = View.GONE
    }

    fun showComponents(){
       // binding.imageView.visibility = View.VISIBLE
        binding.viewPagerDescripcion.visibility = View.VISIBLE
        binding.tabLayout.visibility = View.VISIBLE
    }

    //override methods
    override fun onPause() {
        super.onPause()
        destroyViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyViewPager()
    }

    override fun onResume() {
        super.onResume()
        setUpViewPager()
    }



}