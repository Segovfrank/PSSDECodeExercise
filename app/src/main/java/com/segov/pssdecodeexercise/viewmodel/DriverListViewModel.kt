package com.segov.pssdecodeexercise.viewmodel

import androidx.lifecycle.ViewModel
import com.segov.pssdecodeexercise.data.DriversAndShipments
import com.segov.pssdecodeexercise.domain.GetSuitabilityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class DriverListUiState(
    val drivers: List<String> = emptyList(),
    val shipments: List<String> = emptyList(),
    val isLoading: Boolean = true
)

class DriverListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DriverListUiState())
    val uiState: StateFlow<DriverListUiState> = _uiState.asStateFlow()

    // Used Shipments and Indicators
    private var shipmentsAndIndicator = mutableMapOf<String, Double>()

    // Used Drivers and Shipments
    // Note: we just save in memory since we know it's not a big data input.
    private val driversAndShipments = mutableMapOf<String, String>()

    private val suitabilityUseCase = GetSuitabilityUseCase()

    fun loadList(data: DriversAndShipments) {
        _uiState.update { state ->
            state.copy(
                drivers = data.drivers,
                shipments = data.shipments,
                isLoading = false
            )
        }
    }


    fun onDriverClick(driverName: String): Pair<String, Double> {
        var highest = 0.0
        var bestShipment = ""

        // Retrieving a value if we already have it saved on map
        if (driversAndShipments.containsKey(driverName)) return Pair(driversAndShipments[driverName]!!, shipmentsAndIndicator[driversAndShipments[driverName]]!!)

        // Calculating highest indicator and shipment for a driver
        _uiState.value.shipments.forEach { shipment ->
            if (!shipmentsAndIndicator.containsKey(shipment)) {
                val suitabilityIndicator = suitabilityUseCase(driverName, shipment)
                if (suitabilityIndicator > highest) {
                    highest = suitabilityIndicator
                    bestShipment = shipment
                }
            }
        }
        shipmentsAndIndicator[bestShipment] = highest
        driversAndShipments[driverName] = bestShipment
        return Pair(bestShipment, highest)
    }


    // Other solution. Takes into consideration all at once. n^2
    fun onDriverClickTemp(name: String) {
        // Maximize for the set of drivers
        val driverAndSuitabilityList = mutableMapOf<String, String>()
        var mostSuitable = ""
        var highest = 0.0
        _uiState.value.drivers.forEach { driver ->
            _uiState.value.shipments.forEach { shipment ->
                val ssIndicator = suitabilityUseCase(driver, shipment)
                if (ssIndicator > highest && !driverAndSuitabilityList.containsKey(shipment)) {
                    highest = ssIndicator
                    mostSuitable = shipment
                }
            }
            driverAndSuitabilityList[mostSuitable] = "$driver-$highest"
            highest = 0.0
            mostSuitable = ""
        }
    }

}