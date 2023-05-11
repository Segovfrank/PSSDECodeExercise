package com.segov.pssdecodeexercise.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// For App Usage
@Parcelize
data class DriverAndSuitability (
    val name: String,
    val shipment: String,
    val suitability: Double
) : Parcelable