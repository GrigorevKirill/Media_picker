package com.example.myapplication

import android.location.Location
import kotlinx.coroutines.flow.Flow


interface LocationClient {

    fun getLocationUpdates(interval: Long): Flow<Location>

    fun getLocationUpdatesWithoutFlow(interval: Long)

    fun stopLocationUpdates()

    class LocationException(message: String): Exception()
}