package com.healthcare.janaushadhi.data.models

data class Medicine(
    val id: Int = 0,
    val brandedName: String,
    val genericName: String,
    val brandedPrice: Double,
    val genericPrice: Double,
    val category: String = "General"
) {
    fun getSavings(): Double = brandedPrice - genericPrice
    fun getSavingsPercentage(): Int =
        ((getSavings() / brandedPrice) * 100).toInt()
}