package com.healthcare.janaushadhi.ui.maps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.healthcare.janaushadhi.data.models.Store
import com.healthcare.janaushadhi.data.repository.StoreRepository
import com.healthcare.janaushadhi.ui.theme.AppColors
import java.util.Locale

// ── Enums ────────────────────────────────────────────────────────────

enum class DistanceFilter(val label: String, val km: Float?) {
    ALL("All Distances", null),
    KM_10("Within 10 KM", 10f),
    KM_20("Within 20 KM", 20f),
    KM_50("Within 50 KM", 50f)
}

enum class StatusFilter(val label: String) {
    ALL("All Stores"),
    OPEN("Open Now"),
    CLOSED("Closed Now")
}

// ── Main Screen ──────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoresScreen() {
    val context = LocalContext.current

    var userLocation by remember { mutableStateOf<Location?>(null) }
    var locationPermissionGranted by remember { mutableStateOf(false) }
    var isLoadingLocation by remember { mutableStateOf(false) }

    // FIX: default is ALL so stores show immediately without location
    var selectedDistanceFilter by remember { mutableStateOf(DistanceFilter.ALL) }
    var selectedCity by remember { mutableStateOf("All Cities") }
    var selectedStatusFilter by remember { mutableStateOf(StatusFilter.ALL) }

    var showFilterSheet by remember { mutableStateOf(false) }
    var citySearchQuery by remember { mutableStateOf("") }

    // All unique cities from repository
    val allCities = remember { listOf("All Cities") + StoreRepository.getAllCities() }
    val filteredCities = remember(citySearchQuery, allCities) {
        if (citySearchQuery.isBlank()) allCities
        else allCities.filter { it.contains(citySearchQuery, ignoreCase = true) }
    }

    // Check permission on launch and get location
    LaunchedEffect(Unit) {
        locationPermissionGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (locationPermissionGranted) {
            isLoadingLocation = true
            getCurrentLocation(context) { location ->
                userLocation = location
                if (location != null) {
                    StoreRepository.updateStoreDistances(location.latitude, location.longitude)
                }
                isLoadingLocation = false
            }
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        locationPermissionGranted = isGranted
        if (isGranted) {
            isLoadingLocation = true
            getCurrentLocation(context) { location ->
                userLocation = location
                if (location != null) {
                    StoreRepository.updateStoreDistances(location.latitude, location.longitude)
                }
                isLoadingLocation = false
            }
        }
    }

    // ── Filter logic ─────────────────────────────────────────────────
    val filteredStores = remember(
        selectedDistanceFilter, selectedCity, selectedStatusFilter, userLocation
    ) {
        var stores = StoreRepository.getAllStores()

        // Distance filter: ONLY apply if location is known AND a specific distance chosen
        val maxKm = selectedDistanceFilter.km
        if (userLocation != null && maxKm != null) {
            stores = stores.filter { it.distance <= maxKm }
        }
        // If no location yet and a distance filter is set, ignore it (show all)

        // City filter
        if (selectedCity != "All Cities") {
            stores = stores.filter { it.city.equals(selectedCity, ignoreCase = true) }
        }

        // Status filter
        stores = when (selectedStatusFilter) {
            StatusFilter.ALL    -> stores
            StatusFilter.OPEN   -> stores.filter { it.isCurrentlyOpen() }
            StatusFilter.CLOSED -> stores.filter { !it.isCurrentlyOpen() }
        }

        // Sort: if location available sort by distance, else alphabetically
        if (userLocation != null) stores.sortedBy { it.distance }
        else stores.sortedBy { it.name }
    }

    // ── UI ───────────────────────────────────────────────────────────
    Box(modifier = Modifier.fillMaxSize()) {
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
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "🏥 Medical Stores",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = when {
                                    isLoadingLocation -> "Getting your location…"
                                    userLocation != null -> "${filteredStores.size} stores found • Location active ✓"
                                    else -> "${filteredStores.size} stores • Tap 📍 for distance info"
                                },
                                fontSize = 13.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Filter button with active badge
                            Box {
                                IconButton(
                                    onClick = { showFilterSheet = true },
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color.White.copy(alpha = 0.2f), CircleShape)
                                ) {
                                    Icon(
                                        Icons.Default.FilterList,
                                        contentDescription = "Filters",
                                        tint = Color.White
                                    )
                                }
                                val activeCount = listOf(
                                    selectedDistanceFilter != DistanceFilter.ALL,
                                    selectedCity != "All Cities",
                                    selectedStatusFilter != StatusFilter.ALL
                                ).count { it }
                                if (activeCount > 0) {
                                    Surface(
                                        modifier = Modifier.size(18.dp).align(Alignment.TopEnd),
                                        shape = CircleShape,
                                        color = AppColors.AccentOrange
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                "$activeCount",
                                                color = Color.White,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }

                            // Location button
                            IconButton(
                                onClick = {
                                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        if (userLocation != null) AppColors.SuccessGreen.copy(alpha = 0.4f)
                                        else Color.White.copy(alpha = 0.2f),
                                        CircleShape
                                    )
                            ) {
                                if (isLoadingLocation) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(22.dp),
                                        color = Color.White,
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Icon(
                                        Icons.Default.LocationOn,
                                        contentDescription = "Enable Location",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Active filter chips
            val hasActiveFilters = selectedDistanceFilter != DistanceFilter.ALL ||
                    selectedCity != "All Cities" || selectedStatusFilter != StatusFilter.ALL
            if (hasActiveFilters) {
                LazyRow(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (selectedDistanceFilter != DistanceFilter.ALL) {
                        item {
                            ActiveChip(selectedDistanceFilter.label) {
                                selectedDistanceFilter = DistanceFilter.ALL
                            }
                        }
                    }
                    if (selectedCity != "All Cities") {
                        item {
                            ActiveChip(selectedCity) { selectedCity = "All Cities" }
                        }
                    }
                    if (selectedStatusFilter != StatusFilter.ALL) {
                        item {
                            ActiveChip(selectedStatusFilter.label) {
                                selectedStatusFilter = StatusFilter.ALL
                            }
                        }
                    }
                }
            }

            // Location banner if no location yet
            if (userLocation == null && !isLoadingLocation) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = AppColors.AccentOrange.copy(alpha = 0.12f)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("📍", fontSize = 20.sp)
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Enable location for distance info",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = AppColors.TextPrimary
                            )
                            Text(
                                "Tap the 📍 button above to allow access",
                                fontSize = 12.sp,
                                color = AppColors.TextSecondary
                            )
                        }
                    }
                }
            }

            // Store list — always shown, no location gate
            when {
                isLoadingLocation -> LoadingPlaceholder()
                filteredStores.isEmpty() -> EmptyStoresPlaceholder()
                else -> {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 16.dp, top = 8.dp)
                    ) {
                        items(filteredStores, key = { it.id }) { store ->
                            ModernStoreCard(store = store, context = context)
                        }
                    }
                }
            }
        }

        // Filter bottom sheet
        if (showFilterSheet) {
            FilterBottomSheet(
                selectedDistanceFilter = selectedDistanceFilter,
                selectedCity = selectedCity,
                selectedStatusFilter = selectedStatusFilter,
                filteredCities = filteredCities,
                citySearchQuery = citySearchQuery,
                hasLocation = userLocation != null,
                onCitySearchChange = { citySearchQuery = it },
                onDistanceFilterChange = { selectedDistanceFilter = it },
                onCityChange = { selectedCity = it },
                onStatusFilterChange = { selectedStatusFilter = it },
                onDismiss = { showFilterSheet = false },
                onReset = {
                    selectedDistanceFilter = DistanceFilter.ALL
                    selectedCity = "All Cities"
                    selectedStatusFilter = StatusFilter.ALL
                    citySearchQuery = ""
                }
            )
        }
    }
}

// ── Active filter chip ───────────────────────────────────────────────

@Composable
fun ActiveChip(label: String, onRemove: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = AppColors.PrimaryBlue.copy(alpha = 0.15f),
        modifier = Modifier.height(34.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = AppColors.PrimaryBlue)
            IconButton(onClick = onRemove, modifier = Modifier.size(22.dp)) {
                Icon(Icons.Default.Close, contentDescription = null, tint = AppColors.PrimaryBlue, modifier = Modifier.size(14.dp))
            }
        }
    }
}

// ── Filter Bottom Sheet ──────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    selectedDistanceFilter: DistanceFilter,
    selectedCity: String,
    selectedStatusFilter: StatusFilter,
    filteredCities: List<String>,
    citySearchQuery: String,
    hasLocation: Boolean,
    onCitySearchChange: (String) -> Unit,
    onDistanceFilterChange: (DistanceFilter) -> Unit,
    onCityChange: (String) -> Unit,
    onStatusFilterChange: (StatusFilter) -> Unit,
    onDismiss: () -> Unit,
    onReset: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🔍 Filter Stores", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
                TextButton(onClick = onReset) {
                    Text("Reset All", color = AppColors.PrimaryBlue, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Distance filter (with note if no location)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("📏 Distance", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
                if (!hasLocation) {
                    Surface(shape = RoundedCornerShape(6.dp), color = AppColors.AccentOrange.copy(alpha = 0.15f)) {
                        Text(
                            "Enable location first",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontSize = 11.sp,
                            color = AppColors.AccentOrange,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                DistanceFilter.entries.forEach { filter ->
                    FilterBtn(
                        label = filter.label.replace("Within ", "").replace(" Distances", ""),
                        isSelected = selectedDistanceFilter == filter,
                        enabled = filter == DistanceFilter.ALL || hasLocation,
                        onClick = { onDistanceFilterChange(filter) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Status filter
            Text("🕐 Status", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StatusFilter.entries.forEach { filter ->
                    FilterBtn(
                        label = filter.label,
                        isSelected = selectedStatusFilter == filter,
                        onClick = { onStatusFilterChange(filter) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // City filter
            Text("🏙️ City", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = citySearchQuery,
                onValueChange = onCitySearchChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search any city in India…") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.PrimaryBlue,
                    unfocusedBorderColor = AppColors.TextSecondary.copy(alpha = 0.4f)
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            // City grid
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 240.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val rows = filteredCities.chunked(2)
                items(rows) { rowCities ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowCities.forEach { city ->
                            FilterBtn(
                                label = city,
                                isSelected = selectedCity == city,
                                onClick = { onCityChange(city) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (rowCities.size == 1) Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Apply button
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(AppColors.PrimaryGradient),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Apply Filters", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

// ── Filter button ────────────────────────────────────────────────────

@Composable
fun FilterBtn(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(42.dp),
        shape = RoundedCornerShape(10.dp),
        color = when {
            isSelected -> AppColors.PrimaryBlue
            !enabled   -> Color(0xFFF0F0F0)
            else       -> Color.White
        },
        border = if (!isSelected && enabled)
            androidx.compose.foundation.BorderStroke(1.dp, AppColors.PrimaryBlue.copy(alpha = 0.3f))
        else null,
        shadowElevation = if (isSelected) 4.dp else 0.dp,
        enabled = enabled
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = when {
                    isSelected -> Color.White
                    !enabled   -> AppColors.TextSecondary
                    else       -> AppColors.TextPrimary
                }
            )
        }
    }
}

// ── Store Card ───────────────────────────────────────────────────────

@Composable
fun ModernStoreCard(store: Store, context: Context) {
    val isOpen = store.isCurrentlyOpen()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(16.dp), ambientColor = AppColors.PrimaryBlue.copy(alpha = 0.08f)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

            // Name + distance row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        store.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary,
                        lineHeight = 21.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // City/state badge
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = AppColors.PrimaryBlue.copy(alpha = 0.08f)
                    ) {
                        Text(
                            "${store.city}, ${store.state}",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            fontSize = 11.sp,
                            color = AppColors.PrimaryBlue,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                // Distance badge
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = AppColors.PrimaryBlue.copy(alpha = 0.1f)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Default.Place, contentDescription = null, tint = AppColors.PrimaryBlue, modifier = Modifier.size(16.dp))
                        Text(
                            if (store.distance > 0f) "${"%.1f".format(store.distance)} km" else "—",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.PrimaryBlue
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Open/closed
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = if (isOpen) AppColors.SuccessGreen.copy(alpha = 0.12f) else AppColors.ErrorRed.copy(alpha = 0.12f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(modifier = Modifier.size(7.dp).background(if (isOpen) AppColors.SuccessGreen else AppColors.ErrorRed, CircleShape))
                    Text(
                        if (isOpen) "Open Now · ${store.openTime}–${store.closeTime}" else "Closed · Opens ${store.openTime}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isOpen) AppColors.SuccessGreen else AppColors.ErrorRed
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Address
            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(Icons.Default.Place, contentDescription = null, tint = AppColors.TextSecondary, modifier = Modifier.size(15.dp).padding(top = 2.dp))
                Text(store.address, fontSize = 13.sp, color = AppColors.TextSecondary, lineHeight = 18.sp)
            }

            if (store.phone.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Icon(Icons.Default.Phone, contentDescription = null, tint = AppColors.TextSecondary, modifier = Modifier.size(15.dp))
                    Text(store.phone, fontSize = 13.sp, color = AppColors.TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action buttons
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {
                        val uri = Uri.parse("google.navigation:q=${store.latitude},${store.longitude}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply { setPackage("com.google.android.apps.maps") }
                        if (mapIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(mapIntent)
                        } else {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&destination=${store.latitude},${store.longitude}")))
                        }
                    },
                    modifier = Modifier.weight(1f).height(44.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize().background(AppColors.SuccessGradient), contentAlignment = Alignment.Center) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                            Text("Navigate", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }

                if (store.phone.isNotEmpty()) {
                    OutlinedButton(
                        onClick = { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${store.phone}"))) },
                        modifier = Modifier.weight(1f).height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(2.dp, AppColors.PrimaryBlue),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = AppColors.PrimaryBlue)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(18.dp))
                            Text("Call", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// ── Placeholders ─────────────────────────────────────────────────────

@Composable
fun LoadingPlaceholder() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = AppColors.PrimaryBlue, modifier = Modifier.size(48.dp))
    }
}

@Composable
fun EmptyStoresPlaceholder() {
    Box(modifier = Modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🏪", fontSize = 64.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("No Stores Found", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = AppColors.TextPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Try adjusting your filters", fontSize = 14.sp, color = AppColors.TextSecondary)
        }
    }
}

// ── Location helper ──────────────────────────────────────────────────

private fun getCurrentLocation(context: Context, callback: (Location?) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    try {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { callback(it) }
            .addOnFailureListener { callback(null) }
    } catch (e: SecurityException) {
        callback(null)
    }
}