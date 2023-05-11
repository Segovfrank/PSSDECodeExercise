package com.segov.pssdecodeexercise.utils

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.segov.pssdecodeexercise.R
import com.segov.pssdecodeexercise.data.DriversAndShipments
import java.io.InputStreamReader

fun Resources.getDriverAndShipmentObject(): DriversAndShipments {
    val gson = Gson()
    val type = object : TypeToken<DriversAndShipments>() {}.type
    val inputStream = openRawResource(R.raw.driver_shipments_list)
    return gson.fromJson(JsonReader(InputStreamReader(inputStream)), type)
}
