package com.s.looka.core.network.impl

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.core.content.getSystemService
import com.s.looka.core.network.api.ConnectivityManagerChecker
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.util.Collections
import javax.inject.Inject

internal class ConnectivityManagerCheckerImpl @Inject constructor(context: Context): ConnectivityManagerChecker {

    private val manager = context.getSystemService<ConnectivityManager>()

    companion object {
        private const val TAG = "ConnectivityManagerChecker"

        private fun log(message: String) {
            Log.d(TAG, message)
        }
    }

    override fun isConnectionEnable(): Boolean {
        val activeNetwork = manager?.activeNetwork ?: return false
        val capabilities = manager.getNetworkCapabilities(activeNetwork) ?: return false

        return capabilities.hasInternet()
    }

    override fun observeConnectionState(): Flow<Boolean> = callbackFlow {
        // new: Thread-save HashSet
        //  of valid (has internet) networks
        val validNetworks = Collections.synchronizedSet(HashSet<Network>())

        fun updateState() {
            trySend(validNetworks.isNotEmpty())
        }

        // callback for internet status
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                log("[onLost] Network: $network")
                validNetworks.remove(network)
                updateState()
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                val hasInternet = networkCapabilities.hasInternet()
                log("[onCapabilitiesChanged] Network: $network, hasInternet: $hasInternet")

                // add/remove valid network
                if (hasInternet) validNetworks.add(network)
                else validNetworks.remove(network)

                // sending current internet state
                updateState()
            }

            override fun onAvailable(network: Network) {
                log("[onAvailable] Network: $network")
                val capabilities = manager?.getNetworkCapabilities(network)
                if (capabilities?.hasInternet() == true) {
                    validNetworks.add(network)
                    updateState()
                }
            }
        }

        // Make request to check internet status
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        manager?.registerNetworkCallback(request, callback)

        // Get initialState when subscribed
        val activeNetwork = manager?.activeNetwork
        val currentCapabilities = manager?.getNetworkCapabilities(activeNetwork)
        val initialStatus = currentCapabilities?.hasInternet() ?: false
        log("[InitialState] activeNetwork: $activeNetwork, initialStatus: $initialStatus")
        if (activeNetwork != null && initialStatus) {
            validNetworks.add(activeNetwork)
        }
        trySend(initialStatus)

        awaitClose { manager?.unregisterNetworkCallback(callback) }
    }.distinctUntilChanged()

    // Util function for get internet status
    private fun NetworkCapabilities.hasInternet(): Boolean {
        return this.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && // internet (WiFi/Mobile Data) turned on
                this.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) // ping google services for check has internet or not
    }
}