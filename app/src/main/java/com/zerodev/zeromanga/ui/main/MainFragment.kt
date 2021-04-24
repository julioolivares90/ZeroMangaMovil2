package com.zerodev.zeromanga.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.adapters.MangasPopularesAdapter
import com.zerodev.zeromanga.adapters.MangasSeinenAdapter
import com.zerodev.zeromanga.databinding.MainFragmentBinding
import com.zerodev.zeromanga.listeners.MangaOnclickListener
import com.zerodev.zeromanga.data.remote.models.Manga
import com.zerodev.zeromanga.data.remote.models.Response
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.utlities.constantes.ENVIAR_URL
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private lateinit var adapter : MangasSeinenAdapter
    private lateinit var adapterPopulares : MangasPopularesAdapter

    private lateinit var binding: MainFragmentBinding
    private  val mainViewModel : MainViewModel by inject()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainFragmentBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.IsLoading().observe(viewLifecycleOwner, Observer {
            if (it){
                hideComponents()
                showProgressbar()
            }else {
                hideProgressBar()
                showComponents()
            }
        })

        mainViewModel.hasError().observe(viewLifecycleOwner,{
            if (it){
                hideComponents()
                hideProgressBar()
                showMessageError()
            }else{
                hideMessageError()
            }
        })
        mainViewModel.getMangaSeinen().observe(viewLifecycleOwner, Observer {
            val result = it
            when(result){
                is ResponseManga.Success<Response> -> {
                    adapter = MangasSeinenAdapter(result.data.data,mangaOnclickListener = object : MangaOnclickListener{
                        override fun onClick(manga: Manga) {
                            val bundle = Bundle()
                            bundle.putString(ENVIAR_URL,manga.mangaUrl)

                            Navigation
                                .findNavController(view)
                                .navigate(R.id.action_mainFragment_to_descripcionFragment
                                    ,bundle)
                        }
                    })
                }else -> {
                    hideComponents()
                    hideProgressBar()
                }
            }
            binding.rvMangasSeinen.adapter = adapter
        })

        mainViewModel.getMangasPopulares().observe(viewLifecycleOwner, Observer {
            val result = it
            when(result){
                is ResponseManga.Success<Response> -> {
                    adapterPopulares = MangasPopularesAdapter(result.data.data,mangaOnclickListener = object : MangaOnclickListener{
                        override fun onClick(manga: Manga) {
                            val bundle = Bundle()
                            bundle.putString(ENVIAR_URL,manga.mangaUrl)

                            Navigation
                                .findNavController(view)
                                .navigate(R.id.action_mainFragment_to_descripcionFragment
                                    ,bundle)
                        }
                    })
                }else -> {
                    hideComponents()
                    hideProgressBar()

                }
            }
            binding.rvMangasPopulares.adapter = adapterPopulares
        })
    }

    fun showProgressbar(){
        binding.pbCargarMangas.visibility = View.VISIBLE
    }
    fun hideProgressBar(){
        binding.pbCargarMangas.visibility = View.GONE
    }

    fun hideComponents(){
        binding.textView.visibility = View.GONE
        binding.rvMangasSeinen.visibility = View.GONE
        binding.rvMangasPopulares.visibility = View.GONE
        binding.tvSeinen.visibility = View.GONE
    }

    fun showComponents(){
        binding.textView.visibility = View.VISIBLE
        binding.rvMangasSeinen.visibility = View.VISIBLE
        binding.rvMangasPopulares.visibility = View.VISIBLE
        binding.tvSeinen.visibility = View.VISIBLE
    }

    fun showMessageError(){
        binding.tvError.visibility = View.VISIBLE
    }

    fun hideMessageError(){
        binding.tvError.visibility = View.GONE
    }
}