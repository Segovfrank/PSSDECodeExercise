package com.segov.pssdecodeexercise

import com.segov.pssdecodeexercise.domain.GetSuitabilityUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit test using the example input data
 */
class GetSuitabilityTest {
    @Test
    fun `Get Suitability Score For Driver and Street`() {
        val useCase = GetSuitabilityUseCase()
        val driverName = "Daniel Davidson"
        val shipmentAddress = "44 Fake Dr., San Diego,CA 92122"

        val result = useCase(driverName, shipmentAddress)
        assertEquals(9.0, result, 0.0)
    }
}