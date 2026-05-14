package com.healthcare.janaushadhi.ui.reminders

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthcare.janaushadhi.data.SampleData
import com.healthcare.janaushadhi.data.models.Reminder
import com.healthcare.janaushadhi.data.repository.ReminderRepository
import com.healthcare.janaushadhi.utils.FuzzySearch
import java.util.*
import com.healthcare.janaushadhi.ui.theme.AppColors
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen() {
    var showAddDialog by remember { mutableStateOf(false) }
    val reminders by ReminderRepository.reminders.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.BackgroundGradient)
    ) {
        // Modern Header
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Transparent,
            shadowElevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(AppColors.PrimaryPurple, AppColors.AccentPink)
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "📅 Refill Reminders",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Surface(
                            shape = CircleShape,
                            color = Color.White.copy(alpha = 0.25f)
                        ) {
                            Text(
                                text = "${reminders.size} active",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    FloatingActionButton(
                        onClick = { showAddDialog = true },
                        containerColor = Color.White,
                        contentColor = AppColors.PrimaryPurple,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }

        if (reminders.isEmpty()) {
            EmptyRemindersPlaceholder(onAddClick = { showAddDialog = true })
        } else {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(reminders.sortedBy { it.refillDate }, key = { it.id }) { reminder ->
                    ModernReminderCard(
                        reminder = reminder,
                        onDelete = { ReminderRepository.deleteReminder(reminder.id) }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddReminderDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { reminder ->
                ReminderRepository.addReminder(reminder)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun EmptyRemindersPlaceholder(onAddClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "📅", fontSize = 80.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "No Reminders Yet",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Set medication reminders to never miss a refill",
                fontSize = 15.sp,
                color = AppColors.TextSecondary
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onAddClick,
                modifier = Modifier
                    .height(56.dp)
                    .width(240.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(AppColors.PrimaryPurple, AppColors.AccentPink)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                        Text(
                            "Add Reminder",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ModernReminderCard(reminder: Reminder, onDelete: () -> Unit) {
    val daysUntil = reminder.getDaysUntilRefill()
    val isUrgent = daysUntil in 0..3

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(18.dp),
                ambientColor = if (isUrgent) AppColors.ErrorRed.copy(alpha = 0.1f)
                else AppColors.PrimaryBlue.copy(alpha = 0.08f)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = if (isUrgent) {
                            listOf(Color.White, AppColors.ErrorRed.copy(alpha = 0.05f))
                        } else {
                            listOf(Color.White, AppColors.PrimaryBlue.copy(alpha = 0.03f))
                        }
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = if (isUrgent)
                        AppColors.ErrorRed.copy(alpha = 0.12f)
                    else
                        AppColors.PrimaryBlue.copy(alpha = 0.12f),
                    modifier = Modifier.size(60.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            if (isUrgent) Icons.Default.NotificationImportant else Icons.Default.Notifications,
                            contentDescription = null,
                            tint = if (isUrgent) AppColors.ErrorRed else AppColors.PrimaryBlue,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                // Content
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = reminder.medicineName,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    if (reminder.quantity.isNotEmpty()) {
                        Text(
                            text = "Qty: ${reminder.quantity}",
                            fontSize = 13.sp,
                            color = AppColors.TextSecondary
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            Icons.Default.Schedule,
                            contentDescription = null,
                            tint = AppColors.TextSecondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${reminder.frequency} at ${reminder.time}",
                            fontSize = 12.sp,
                            color = AppColors.TextSecondary
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = when {
                            daysUntil < 0 -> AppColors.ErrorRed.copy(alpha = 0.15f)
                            isUrgent -> AppColors.WarningOrange.copy(alpha = 0.15f)
                            else -> AppColors.SuccessGreen.copy(alpha = 0.15f)
                        }
                    ) {
                        Text(
                            text = when {
                                daysUntil < 0 -> "⚠️ Overdue!"
                                daysUntil == 0 -> "🔔 Refill today"
                                daysUntil == 1 -> "Tomorrow"
                                daysUntil <= 7 -> "In $daysUntil days"
                                else -> reminder.getRefillDateFormatted()
                            },
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = when {
                                daysUntil < 0 -> AppColors.ErrorRed
                                isUrgent -> AppColors.WarningOrange
                                else -> AppColors.SuccessGreen
                            }
                        )
                    }
                }

                // Delete Button
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            AppColors.ErrorRed.copy(alpha = 0.1f),
                            CircleShape
                        )
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = AppColors.ErrorRed,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderDialog(
    onDismiss: () -> Unit,
    onAdd: (Reminder) -> Unit
) {
    var medicineName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var selectedFrequency by remember { mutableStateOf("Daily") }
    var selectedTime by remember { mutableStateOf("09:00 AM") }
    var selectedDaysAhead by remember { mutableStateOf(30) }
    var showSuggestions by remember { mutableStateOf(false) }

    val allMedicines = remember { SampleData.getMedicines() }

    // FUZZY AUTOCOMPLETE - Shows suggestions on 1-2 letters
    val suggestions = remember(medicineName) {
        if (medicineName.length >= 1) {
            allMedicines
                .filter { medicine ->
                    FuzzySearch.advancedFuzzyMatch(medicineName, medicine.brandedName) ||
                            FuzzySearch.advancedFuzzyMatch(medicineName, medicine.genericName)
                }
                .sortedByDescending { medicine ->
                    maxOf(
                        FuzzySearch.getMatchScore(medicineName, medicine.brandedName),
                        FuzzySearch.getMatchScore(medicineName, medicine.genericName)
                    )
                }
                .take(5)
        } else {
            emptyList()
        }
    }

    val frequencies = listOf("Daily", "Weekly", "Monthly")
    val times = listOf(
        "06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM",
        "12:00 PM", "02:00 PM", "04:00 PM", "06:00 PM", "08:00 PM", "09:00 PM"
    )
    val daysOptions = listOf(7, 15, 30, 60, 90)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Add Refill Reminder",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = AppColors.TextPrimary
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Medicine Name with Autocomplete
                Column {
                    OutlinedTextField(
                        value = medicineName,
                        onValueChange = {
                            medicineName = it
                            showSuggestions = it.isNotEmpty()
                        },
                        label = { Text("Medicine Name") },
                        placeholder = { Text("Start typing...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = null)
                        },
                        trailingIcon = {
                            if (medicineName.isNotEmpty()) {
                                IconButton(onClick = {
                                    medicineName = ""
                                    showSuggestions = false
                                }) {
                                    Icon(Icons.Default.Close, contentDescription = "Clear")
                                }
                            }
                        }
                    )

                    // Autocomplete Suggestions
                    AnimatedVisibility(visible = showSuggestions && suggestions.isNotEmpty()) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 200.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            LazyColumn {
                                items(suggestions) { medicine ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                medicineName = medicine.brandedName
                                                showSuggestions = false
                                            }
                                            .padding(14.dp)
                                    ) {
                                        Text(
                                            text = medicine.brandedName,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp,
                                            color = AppColors.TextPrimary
                                        )
                                        Text(
                                            text = medicine.genericName,
                                            fontSize = 13.sp,
                                            color = AppColors.TextSecondary
                                        )
                                    }
                                    if (medicine != suggestions.last()) {
                                        HorizontalDivider(color = Color(0xFFEEEEEE))
                                    }
                                }
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantity (Optional)") },
                    placeholder = { Text("e.g., 30 tablets") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                var daysExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = daysExpanded,
                    onExpandedChange = { daysExpanded = it }
                ) {
                    OutlinedTextField(
                        value = "Refill in $selectedDaysAhead days",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Refill Date") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = daysExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = daysExpanded,
                        onDismissRequest = { daysExpanded = false }
                    ) {
                        daysOptions.forEach { days ->
                            DropdownMenuItem(
                                text = { Text("In $days days") },
                                onClick = {
                                    selectedDaysAhead = days
                                    daysExpanded = false
                                }
                            )
                        }
                    }
                }

                var freqExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = freqExpanded,
                    onExpandedChange = { freqExpanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedFrequency,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Reminder Frequency") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = freqExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = freqExpanded,
                        onDismissRequest = { freqExpanded = false }
                    ) {
                        frequencies.forEach { frequency ->
                            DropdownMenuItem(
                                text = { Text(frequency) },
                                onClick = {
                                    selectedFrequency = frequency
                                    freqExpanded = false
                                }
                            )
                        }
                    }
                }

                var timeExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = timeExpanded,
                    onExpandedChange = { timeExpanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedTime,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Reminder Time") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = timeExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = timeExpanded,
                        onDismissRequest = { timeExpanded = false }
                    ) {
                        times.forEach { time ->
                            DropdownMenuItem(
                                text = { Text(time) },
                                onClick = {
                                    selectedTime = time
                                    timeExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (medicineName.isNotBlank()) {
                        val refillDate = System.currentTimeMillis() + (selectedDaysAhead * 24 * 60 * 60 * 1000L)
                        val reminder = Reminder(
                            id = UUID.randomUUID().toString(),
                            medicineName = medicineName,
                            quantity = quantity,
                            frequency = selectedFrequency,
                            time = selectedTime,
                            refillDate = refillDate,
                            isActive = true
                        )
                        onAdd(reminder)
                    }
                },
                enabled = medicineName.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(48.dp)
                    .width(140.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(AppColors.PrimaryPurple, AppColors.AccentPink)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Add Reminder",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = AppColors.TextSecondary)
            }
        }
    )
}