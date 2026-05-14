package com.healthcare.janaushadhi.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.healthcare.janaushadhi.data.models.Medicine
import com.healthcare.janaushadhi.utils.FuzzySearch
import com.healthcare.janaushadhi.ui.theme.AppColors
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {

    var searchQuery by remember {
        mutableStateOf("")
    }

    val allMedicines = remember {
        SampleData.getMedicines()
    }

    val filteredMedicines = remember(searchQuery) {

        if (searchQuery.isBlank()) {

            allMedicines

        } else {

            allMedicines
                .filter { medicine ->

                    FuzzySearch.advancedFuzzyMatch(
                        searchQuery,
                        medicine.brandedName
                    ) ||

                            FuzzySearch.advancedFuzzyMatch(
                                searchQuery,
                                medicine.genericName
                            ) ||

                            FuzzySearch.advancedFuzzyMatch(
                                searchQuery,
                                medicine.category
                            )
                }

                .sortedByDescending { medicine ->

                    maxOf(
                        FuzzySearch.getMatchScore(
                            searchQuery,
                            medicine.brandedName
                        ),

                        FuzzySearch.getMatchScore(
                            searchQuery,
                            medicine.genericName
                        ),

                        FuzzySearch.getMatchScore(
                            searchQuery,
                            medicine.category
                        ) * 0.5
                    )
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.BackgroundGradient)
    ) {

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
                        text = "💊 Find Medicines",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Surface(
                            shape = CircleShape,
                            color = Color.White.copy(alpha = 0.25f)
                        ) {

                            Box(
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 4.dp
                                )
                            ) {

                                Text(
                                    text = "500+ medicines",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Surface(
                            shape = CircleShape,
                            color = Color.White.copy(alpha = 0.25f)
                        ) {

                            Box(
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 4.dp
                                )
                            ) {

                                Text(
                                    text = "Smart Search",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(18.dp),
                    ambientColor = AppColors.PrimaryBlue.copy(alpha = 0.15f),
                    spotColor = AppColors.PrimaryBlue.copy(alpha = 0.15f)
                ),

            shape = RoundedCornerShape(18.dp),

            color = Color.White
        ) {

            OutlinedTextField(
                value = searchQuery,

                onValueChange = {
                    searchQuery = it
                },

                modifier = Modifier.fillMaxWidth(),

                placeholder = {

                    Text(
                        text = "Type medicine name...",
                        fontSize = 14.sp,
                        color = AppColors.TextLight
                    )
                },

                leadingIcon = {

                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = AppColors.PrimaryBlue
                    )
                },

                trailingIcon = {

                    if (searchQuery.isNotEmpty()) {

                        IconButton(
                            onClick = {
                                searchQuery = ""
                            }
                        ) {

                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                tint = AppColors.TextSecondary
                            )
                        }
                    }
                },

                singleLine = true,

                colors = OutlinedTextFieldDefaults.colors(

                    focusedTextColor = AppColors.TextPrimary,

                    unfocusedTextColor = AppColors.TextPrimary,

                    focusedContainerColor = Color.White,

                    unfocusedContainerColor = Color.White,

                    focusedBorderColor = Color.Transparent,

                    unfocusedBorderColor = Color.Transparent
                ),

                shape = RoundedCornerShape(18.dp)
            )
        }

        AnimatedVisibility(
            visible = searchQuery.isNotBlank(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),

                shape = RoundedCornerShape(12.dp),

                color = AppColors.InfoBlue.copy(alpha = 0.1f)
            ) {

                Row(
                    modifier = Modifier.padding(12.dp),

                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = AppColors.InfoBlue
                    )

                    Column {

                        Text(
                            text = "${filteredMedicines.size} medicines found",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.InfoBlue
                        )

                        Text(
                            text = "Fuzzy search active",
                            fontSize = 11.sp,
                            color = AppColors.InfoBlue.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }

        if (filteredMedicines.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "😕",
                        fontSize = 70.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "No medicines found",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextPrimary
                    )
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),

                verticalArrangement = Arrangement.spacedBy(14.dp),

                contentPadding = PaddingValues(bottom = 16.dp)
            ) {

                items(filteredMedicines) { medicine ->

                    ModernMedicineCard(
                        medicine = medicine,
                        searchQuery = searchQuery
                    )
                }
            }
        }
    }
}

@Composable
fun ModernMedicineCard(
    medicine: Medicine,
    searchQuery: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp)
            ),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.White,
                            getCategoryColor(
                                medicine.category
                            ).copy(alpha = 0.03f)
                        )
                    )
                )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Surface(
                    shape = RoundedCornerShape(8.dp),

                    color = getCategoryColor(
                        medicine.category
                    ).copy(alpha = 0.12f)
                ) {

                    Text(
                        text = medicine.category,

                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 6.dp
                        ),

                        fontSize = 11.sp,

                        fontWeight = FontWeight.Bold,

                        color = getCategoryColor(
                            medicine.category
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = medicine.brandedName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Generic: ${medicine.genericName}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.SuccessGreen
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Brand ₹${medicine.brandedPrice.toInt()}",
                        color = AppColors.ErrorRed,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Generic ₹${medicine.genericPrice.toInt()}",
                        color = AppColors.SuccessGreen,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    AppColors.AccentOrange,
                                    AppColors.AccentPink
                                )
                            ),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(14.dp),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Save ₹${medicine.getSavings().toInt()}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

private fun getCategoryColor(
    category: String
): Color {

    return when (category.lowercase()) {

        "pain relief" -> Color(0xFFE91E63)

        "antibiotic" -> Color(0xFF2196F3)

        "allergy",
        "cold" -> Color(0xFFFF9800)

        "diabetes" -> Color(0xFF4CAF50)

        "acidity" -> Color(0xFF9C27B0)

        "blood pressure",
        "heart" -> Color(0xFFF44336)

        "cholesterol" -> Color(0xFF673AB7)

        "vitamins",
        "supplements" -> Color(0xFF009688)

        "respiratory" -> Color(0xFF00BCD4)

        "skin" -> Color(0xFFFF5722)

        else -> Color(0xFF607D8B)
    }
}