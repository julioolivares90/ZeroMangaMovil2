package com.zerodev.zeromanga.utlities

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.*
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val TAG = "c-Manager"
class CheckNetwork(val context: Context) : LiveData<Boolean>() {

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    private val validNetwork : MutableSet<Network> = HashSet()

    private fun checkValidNetworks(){
        postValue(validNetwork.size > 0)
    }

    override fun onActive() {
        super.onActive()
        networkCallback = createNetworkCallback()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .build()
        cm.registerNetworkCallback(networkRequest,networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        cm.unregisterNetworkCallback(networkCallback)
    }
    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(TAG,"onAvailable :${network}")
            val networkCapabilities = cm.getNetworkCapabilities(network)
            val hasInternetCapabilities = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
            Log.d(TAG,"onAvailable : ${network}, $hasInternetCapabilities")
            if (hasInternetCapabilities == true){
                CoroutineScope(Dispatchers.IO).launch{
                    val hasInternet = DoesNetworkHaveInternet.execute(network.socketFactory)
                    if (hasInternet){
                        withContext(Dispatchers.Main){
                            Log.d(TAG,"onAvailable: adding network. ${network}")
                            validNetwork.add(network)
                            checkValidNetworks()
                        }
                    }
                }
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(TAG,"onLost: ${network}")
            validNetwork.remove(network)
            checkValidNetworks()
        }
    }
}