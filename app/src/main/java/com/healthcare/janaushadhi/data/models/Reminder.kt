package com.healthcare.janaushadhi.data.models

data class Reminder(
    val id: String = "",
    val medicineName: String,
    val quantity: String = "",
    val frequency: String,
    val time: String,
    val refillDate: Long = 0L,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun getDaysUntilRefill(): Int {
        val diff = refillDate - System.currentTimeMillis()
        return (diff / (1000 * 60 * 60 * 24)).toInt()
    }

    fun getRefillDateFormatted(): String {
        val sdf = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(refillDate))
    }
}