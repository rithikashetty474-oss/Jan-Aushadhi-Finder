package com.healthcare.janaushadhi.data.models

data class Store(
    val id: Int = 0,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String = "",
    var distance: Float = 0f,
    val city: String = "",
    val state: String = "",
    val isOpen: Boolean = true,
    val openTime: String = "09:00 AM",
    val closeTime: String = "09:00 PM"
) {
    fun isCurrentlyOpen(): Boolean {
        // Simple logic: open between 9 AM - 9 PM
        val currentHour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        return isOpen && currentHour in 9..21
    }
}