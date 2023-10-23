package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LocationJobService(context: Context, params: WorkerParameters, private val test: Int) :
    CoroutineWorker(context, params) {

    private val locationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        Log.d("MYTAGMYTAG", "test = $test")
        val task = locationClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY, CancellationTokenSource().token,
        )
        val result = Tasks.await(task)
        val lat = result.latitude
        val long = result.longitude
        val res = Pair(lat, long)
        val outputData = Data.Builder().putDoubleArray(TAG, doubleArrayOf(lat, long)).build()
        return Result.success(outputData)
    }


    companion object {
        const val workName = "LocationJobService"
        const val TAG = "BackgroundLocationWork"
    }
}