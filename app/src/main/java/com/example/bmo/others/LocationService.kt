package com.example.bmo.others

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import com.google.android.gms.location.*

class LocationService(private val context: Context) {

    private lateinit var fused_location_service: FusedLocationProviderClient
    private lateinit var location_request: LocationRequest
    private lateinit var location_callback: LocationCallback
    private val ACCESS_LOCATION_REQUEST_CODE = 200

    fun location_update(on_finish: (location: LocationResult)->Unit) {

        fused_location_service = LocationServices.getFusedLocationProviderClient(context)
        location_request =
            LocationRequest()
                .setInterval(50_000L)
                .setFastestInterval(50_000L)
                .setSmallestDisplacement(170f)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        location_callback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                p0 ?: return

                if (p0.locations.isNotEmpty())
                    on_finish(p0)

            }
        }
    }

    fun stop_location_update() =
        fused_location_service.removeLocationUpdates(location_callback)

    fun start_location_update() {
        if (!checkPermission())
            requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_LOCATION_REQUEST_CODE)

        fused_location_service.requestLocationUpdates(location_request, location_callback, null)
    }

    private fun checkPermission() =
        when(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            PackageManager.PERMISSION_GRANTED -> true
            else -> false
        }
}