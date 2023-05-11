package com.segov.pssdecodeexercise.domain

/**
 * PS Algorithm Use Case
 * Top secret algorithm by counting consonants/vowels,
 * common factors and if names are uneven or not
 */
class GetSuitabilityUseCase {

    private val vowels = arrayOf('a', 'e', 'i', 'o', 'u')

    operator fun invoke(driverName: String, streetName: String) : Double {

        var suitabilityScore: Double = if (streetName.length % 2 == 0) { // If is even
            val vowels = driverName.countConsonantOrVowel(isVowel = true)
            vowels * 1.5
        } else {
            val consonants = driverName.countConsonantOrVowel(isVowel = false)
            consonants.toDouble()
        }

        if (driverName.checkForSharedCommonFactors(streetName)) suitabilityScore *= 1.5

        return suitabilityScore
    }

    private fun String.countConsonantOrVowel(isVowel: Boolean): Int {
        var total = 0
        if (isVowel) {
            forEach { if (vowels.contains(it)) total += 1 }
        } else {
            forEach { if (!vowels.contains(it)) total += 1 }
        }
        return total
    }

    private fun String.checkForSharedCommonFactors(other: String): Boolean {
        // Consider any shared common factors between s1 and s2, ignore number 1
        if (length == other.length) return true
        var iterator = 2 // Ignore number 1
        while (iterator < length && iterator < other.length) {
            // Return at first instance, we know we have a common factor if remainder is 0
            if (length % iterator == 0 && other.length % iterator == 0) return true
            iterator++
        }
        return false
    }
}