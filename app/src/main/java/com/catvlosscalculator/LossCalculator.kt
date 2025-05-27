
package com.catvlosscalculator

object LossCalculator {

    private val cableLossMap = mapOf(
        "RG-59" to 7.1,
        "RG-6" to 6.5,
        "RG-11" to 3.0
    )

    private val splitterLossMap = mapOf(
        "2-way" to 3.5,
        "3-way" to 5.5,
        "4-way" to 7.0,
        "8-way" to 11.0
    )

    fun calculateCableLoss(cableType: String, lengthFeet: Double): Double {
        val lossPer100ft = cableLossMap[cableType] ?: return 0.0
        return (lengthFeet / 100.0) * lossPer100ft
    }

    fun calculateSplitterLoss(splitterType: String, count: Int): Double {
        val lossPerSplitter = splitterLossMap[splitterType] ?: return 0.0
        return count * lossPerSplitter
    }

    fun calculateTotalLoss(
        cableType: String,
        lengthFeet: Double,
        splitterType: String,
        splitterCount: Int
    ): Double {
        val cableLoss = calculateCableLoss(cableType, lengthFeet)
        val splitterLoss = calculateSplitterLoss(splitterType, splitterCount)
        return cableLoss + splitterLoss
    }
}
