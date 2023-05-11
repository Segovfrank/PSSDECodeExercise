package com.segov.pssdecodeexercise.data

// For Json Deserialization
data class DriversAndShipments(
    val shipments: List<String>,
    val drivers: List<String>
)
