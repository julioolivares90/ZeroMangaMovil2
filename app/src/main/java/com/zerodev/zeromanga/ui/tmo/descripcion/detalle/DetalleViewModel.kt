package com.zerodev.zeromanga.ui.tmo.descripcion.detalle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zerodev.zeromanga.data.remote.models.MangaResponse

class DetalleViewModel : ViewModel() {

    var mangaResponse : MutableLiveData<MangaResponse> = MutableLiveData()

}