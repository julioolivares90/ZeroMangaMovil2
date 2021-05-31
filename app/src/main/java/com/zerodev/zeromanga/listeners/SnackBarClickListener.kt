package com.zerodev.zeromanga.listeners

import android.view.View
import androidx.navigation.findNavController
import com.zerodev.zeromanga.R

class SnackBarClickListener : View.OnClickListener {
    override fun onClick(v: View?) {
        v?.findNavController()?.navigate(R.id.action_descripcionFragment_to_favoritosFragment)
    }
}