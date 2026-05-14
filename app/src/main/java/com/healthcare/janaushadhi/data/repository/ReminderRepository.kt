package com.healthcare.janaushadhi.data.repository

import com.healthcare.janaushadhi.data.models.Reminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ReminderRepository {
    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders: StateFlow<List<Reminder>> = _reminders.asStateFlow()

    fun addReminder(reminder: Reminder) {
        _reminders.value = _reminders.value + reminder
    }

    fun deleteReminder(reminderId: String) {
        _reminders.value = _reminders.value.filter { it.id != reminderId }
    }

    fun getUpcomingReminders(): List<Reminder> {
        val currentTime = System.currentTimeMillis()
        return _reminders.value
            .filter { it.isActive && it.refillDate > currentTime }
            .sortedBy { it.refillDate }
    }

    fun updateReminder(reminder: Reminder) {
        _reminders.value = _reminders.value.map {
            if (it.id == reminder.id) reminder else it
        }
    }

    fun clearAllReminders() {
        _reminders.value = emptyList()
    }
}