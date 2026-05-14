package com.healthcare.janaushadhi.ui.stock

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import com.healthcare.janaushadhi.ui.theme.AppColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.healthcare.janaushadhi.data.SampleData
import com.healthcare.janaushadhi.data.models.Medicine
import com.healthcare.janaushadhi.data.models.Store
import com.healthcare.janaushadhi.data.repository.StoreRepository
import com.healthcare.janaushadhi.utils.FuzzySearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockCheckerScreen(navController: NavController? = null) {

    var medicineName by remember { mutableStateOf("") }
    var selectedMedicine by remember { mutableStateOf<Medicine?>(null) }

    val medicines = remember { SampleData.getMedicines() }

    val filteredMedicines = remember(medicineName, medicines) {
        if (medicineName.length >= 1) {
            medicines
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

    // When a medicine is selected, find which stores have it
    val storesWithStock = remember(selectedMedicine) {
        selectedMedicine?.let {
            StoreRepository.getStoresWithMedicine(it.genericName)
        } ?: emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.BackgroundGradient)
    ) {
        // Header
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Transparent,
            shadowElevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.PrimaryGradient)
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = "📦 Stock Checker",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "Find which stores have your medicine",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Search bar
            item {
                OutlinedTextField(
                    value = medicineName,
                    onValueChange = {
                        medicineName = it
                        selectedMedicine = null  // reset selection when typing
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Search Medicine Name") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null,
                            tint = AppColors.PrimaryBlue)
                    },
                    trailingIcon = {
                        if (medicineName.isNotEmpty()) {
                            IconButton(onClick = {
                                medicineName = ""
                                selectedMedicine = null
                            }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear",
                                    tint = AppColors.TextSecondary)
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AppColors.PrimaryBlue,
                        unfocusedBorderColor = AppColors.TextSecondary.copy(alpha = 0.4f),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }

            // Empty / hint state
            if (medicineName.isBlank() && selectedMedicine == null) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("🔍", fontSize = 56.sp)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                "Type a medicine name",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = AppColors.TextPrimary
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                "e.g. Crocin, Paracetamol, Dolo",
                                fontSize = 14.sp,
                                color = AppColors.TextSecondary
                            )
                        }
                    }
                }
            }

            // Search results (before selecting)
            if (selectedMedicine == null && filteredMedicines.isNotEmpty()) {
                item {
                    Text(
                        "Search Results",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                }
                items(filteredMedicines) { medicine ->
                    MedicineSearchResultCard(
                        medicine = medicine,
                        onClick = {
                            selectedMedicine = medicine
                            medicineName = medicine.brandedName
                        }
                    )
                }
            }

            if (medicineName.isNotBlank() && filteredMedicines.isEmpty() && selectedMedicine == null) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No medicines found for \"$medicineName\"",
                                color = AppColors.TextSecondary,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            // ── SELECTED MEDICINE DETAIL + STORE AVAILABILITY ──────
            selectedMedicine?.let { med ->

                // Medicine detail card
                item {
                    MedicineDetailCard(medicine = med)
                }

                // Store availability section
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "🏥 Available At Stores",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.TextPrimary
                        )
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = if (storesWithStock.isNotEmpty())
                                AppColors.SuccessGreen.copy(alpha = 0.15f)
                            else
                                AppColors.ErrorRed.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "${storesWithStock.size} stores",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (storesWithStock.isNotEmpty())
                                    AppColors.SuccessGreen
                                else
                                    AppColors.ErrorRed
                            )
                        }
                    }
                }

                if (storesWithStock.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(28.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("😔", fontSize = 40.sp)
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    "Not available at any tracked store",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = AppColors.TextSecondary
                                )
                            }
                        }
                    }
                } else {
                    items(storesWithStock) { store ->
                        StoreAvailabilityCard(
                            store = store,
                            medicineName = med.genericName,
                            onViewOnMap = {
                                // Navigate to stores screen with this city pre-filtered
                                navController?.navigate("stores?city=${store.city}")
                            }
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// ── Medicine search result (compact tap-to-select) ──────────────────
@Composable
fun MedicineSearchResultCard(medicine: Medicine, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(AppColors.PrimaryBlue.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Medication,
                    contentDescription = null,
                    tint = AppColors.PrimaryBlue,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    medicine.brandedName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )
                Text(
                    "Generic: ${medicine.genericName}",
                    fontSize = 13.sp,
                    color = AppColors.SuccessGreen,
                    fontWeight = FontWeight.Medium
                )
            }
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = AppColors.TextSecondary
            )
        }
    }
}

// ── Full medicine detail after selection ────────────────────────────
@Composable
fun MedicineDetailCard(medicine: Medicine) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(18.dp)),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                medicine.brandedName,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = AppColors.TextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Generic: ${medicine.genericName}",
                fontSize = 15.sp,
                color = AppColors.SuccessGreen,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Category: ${medicine.category}",
                fontSize = 13.sp,
                color = AppColors.TextSecondary
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Price comparison row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Branded price
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    color = AppColors.ErrorRed.copy(alpha = 0.08f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Branded", fontSize = 12.sp, color = AppColors.TextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "₹${medicine.brandedPrice}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = AppColors.ErrorRed
                        )
                    }
                }
                // Generic price
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    color = AppColors.SuccessGreen.copy(alpha = 0.1f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Generic", fontSize = 12.sp, color = AppColors.TextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "₹${medicine.genericPrice}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = AppColors.SuccessGreen
                        )
                    }
                }
                // Savings
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    color = AppColors.PrimaryBlue.copy(alpha = 0.08f)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("You Save", fontSize = 12.sp, color = AppColors.TextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "₹${medicine.brandedPrice - medicine.genericPrice}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = AppColors.PrimaryBlue
                        )
                    }
                }
            }
        }
    }
}

// ── Store availability card ──────────────────────────────────────────
@Composable
fun StoreAvailabilityCard(
    store: Store,
    medicineName: String,
    onViewOnMap: () -> Unit
) {
    val isOpen = store.isCurrentlyOpen()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Stock indicator dot
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(AppColors.SuccessGreen.copy(alpha = 0.12f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = AppColors.SuccessGreen,
                    modifier = Modifier.size(26.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    store.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    store.address,
                    fontSize = 12.sp,
                    color = AppColors.TextSecondary,
                    lineHeight = 17.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Open/closed badge
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isOpen)
                            AppColors.SuccessGreen.copy(alpha = 0.12f)
                        else
                            AppColors.ErrorRed.copy(alpha = 0.12f)
                    ) {
                        Text(
                            if (isOpen) "● Open" else "● Closed",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isOpen) AppColors.SuccessGreen else AppColors.ErrorRed
                        )
                    }
                    // Distance if available
                    if (store.distance > 0f) {
                        Text(
                            "${"%.1f".format(store.distance)} km away",
                            fontSize = 12.sp,
                            color = AppColors.TextSecondary
                        )
                    }
                }
            }

            // View on map button
            IconButton(
                onClick = onViewOnMap,
                modifier = Modifier
                    .size(40.dp)
                    .background(AppColors.PrimaryBlue.copy(alpha = 0.1f), CircleShape)
            ) {
                Icon(
                    Icons.Default.Map,
                    contentDescription = "View on Map",
                    tint = AppColors.PrimaryBlue,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}