package com.healthcare.janaushadhi.ui.home

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.healthcare.janaushadhi.data.repository.ReminderRepository
import com.healthcare.janaushadhi.ui.theme.AppColors
@Composable
fun HomeScreen(navController: NavController) {
    val upcomingReminders by ReminderRepository.reminders.collectAsState()
    val nextReminders = upcomingReminders
        .filter { it.isActive && it.getDaysUntilRefill() >= 0 }
        .sortedBy { it.refillDate }
        .take(3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.BackgroundGradient)
    ) {
        // Modern Gradient Header
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Transparent,
            shadowElevation = 12.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.PrimaryGradient)
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        text = "Hello there, 👋",
                        fontSize = 18.sp,
                        color = Color.White.copy(alpha = 0.9f),
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Jan-Aushadhi Finder",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    text = "Quick Actions",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    ModernQuickActionCard(
                        title = "Find Meds",
                        subtitle = "Generic search",
                        icon = Icons.Default.Search,
                        gradient = Brush.linearGradient(
                            colors = listOf(Color(0xFF4A90E2), Color(0xFF357ABD))
                        ),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("search") }
                    )
                    ModernQuickActionCard(
                        title = "Store Map",
                        subtitle = "Nearby stores",
                        icon = Icons.Default.Place,
                        gradient = Brush.linearGradient(
                            colors = listOf(Color(0xFF4CAF50), Color(0xFF388E3C))
                        ),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("stores") }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    ModernQuickActionCard(
                        title = "Stock Check",
                        subtitle = "Availability",
                        icon = Icons.Default.CheckCircle,
                        gradient = Brush.linearGradient(
                            colors = listOf(Color(0xFFFF9800), Color(0xFFF57C00))
                        ),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("stock") }
                    )
                    ModernQuickActionCard(
                        title = "Refill Alert",
                        subtitle = "Set reminders",
                        icon = Icons.Default.Notifications,
                        gradient = Brush.linearGradient(
                            colors = listOf(Color(0xFF9C27B0), Color(0xFF7B1FA2))
                        ),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("reminders") }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Upcoming Refills",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                    if (nextReminders.isNotEmpty()) {
                        TextButton(onClick = { navController.navigate("reminders") }) {
                            Text(
                                "View All",
                                color = AppColors.PrimaryBlue,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            if (nextReminders.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 6.dp,
                                shape = RoundedCornerShape(18.dp),
                                ambientColor = AppColors.PrimaryBlue.copy(alpha = 0.08f)
                            ),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(28.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "📅", fontSize = 56.sp)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "No upcoming refills",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.TextPrimary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Add refill alerts to track medicines",
                                fontSize = 14.sp,
                                color = AppColors.TextSecondary
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(
                                onClick = { navController.navigate("reminders") },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues(0.dp),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .height(48.dp)
                                    .width(180.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.horizontalGradient(
                                                colors = listOf(
                                                    AppColors.PrimaryPurple,
                                                    AppColors.AccentPink
                                                )
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.Add,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                        Text(
                                            "Add Refill Alert",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                items(nextReminders) { reminder ->
                    ModernUpcomingRefillCard(
                        medicineName = reminder.medicineName,
                        daysUntilRefill = reminder.getDaysUntilRefill(),
                        refillDate = reminder.getRefillDateFormatted(),
                        quantity = reminder.quantity
                    )
                }
            }

            item {
                Text(
                    text = "Health Tips",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 6.dp,
                            shape = RoundedCornerShape(18.dp),
                            ambientColor = AppColors.SuccessGreen.copy(alpha = 0.08f)
                        ),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "💡", fontSize = 40.sp)
                        Column {
                            Text(
                                text = "Save up to 90%",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.TextPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Generic medicines have the same composition as branded medicines",
                                fontSize = 14.sp,
                                color = AppColors.TextSecondary,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ModernQuickActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    gradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = AppColors.PrimaryBlue.copy(alpha = 0.12f),
                spotColor = AppColors.PrimaryBlue.copy(alpha = 0.12f)
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.White,
                            Color.White.copy(alpha = 0.95f)
                        )
                    )
                )
                .padding(18.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(gradient, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = AppColors.TextSecondary
                )
            }
        }
    }
}

@Composable
fun ModernUpcomingRefillCard(
    medicineName: String,
    daysUntilRefill: Int,
    refillDate: String,
    quantity: String
) {
    val isUrgent = daysUntilRefill <= 3

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = if (isUrgent)
                    AppColors.ErrorRed.copy(alpha = 0.1f)
                else
                    AppColors.PrimaryBlue.copy(alpha = 0.08f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUrgent)
                AppColors.ErrorRed.copy(alpha = 0.04f)
            else
                Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        if (isUrgent)
                            Brush.linearGradient(
                                colors = listOf(AppColors.ErrorRed, AppColors.AccentOrange)
                            )
                        else
                            Brush.linearGradient(
                                colors = listOf(AppColors.PrimaryBlue, AppColors.PrimaryCyan)
                            ),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = medicineName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
                if (quantity.isNotEmpty()) {
                    Text(
                        text = "Qty: $quantity",
                        fontSize = 12.sp,
                        color = AppColors.TextSecondary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = when {
                        daysUntilRefill == 0 -> AppColors.ErrorRed.copy(alpha = 0.15f)
                        daysUntilRefill <= 3 -> AppColors.WarningOrange.copy(alpha = 0.15f)
                        else -> AppColors.SuccessGreen.copy(alpha = 0.15f)
                    }
                ) {
                    Text(
                        text = when {
                            daysUntilRefill == 0 -> "🔔 Refill today!"
                            daysUntilRefill == 1 -> "Tomorrow"
                            daysUntilRefill <= 3 -> "In $daysUntilRefill days"
                            else -> refillDate
                        },
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = when {
                            daysUntilRefill <= 1 -> AppColors.ErrorRed
                            daysUntilRefill <= 3 -> AppColors.WarningOrange
                            else -> AppColors.SuccessGreen
                        }
                    )
                }
            }
        }
    }
}