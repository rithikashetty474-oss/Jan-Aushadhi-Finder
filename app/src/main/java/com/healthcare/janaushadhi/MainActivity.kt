package com.healthcare.janaushadhi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.healthcare.janaushadhi.ui.home.HomeScreen
import com.healthcare.janaushadhi.ui.maps.StoresScreen
import com.healthcare.janaushadhi.ui.reminders.RemindersScreen
import com.healthcare.janaushadhi.ui.search.SearchScreen
import com.healthcare.janaushadhi.ui.stock.StockCheckerScreen
import com.healthcare.janaushadhi.ui.theme.JanAushadhiFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JanAushadhiFinderTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != "home") {
                TopAppBar(
                    title = {
                        Text(
                            when (currentRoute) {
                                "search" -> "Find Medicine"
                                "stores" -> "Nearby Stores"
                                "stock" -> "Stock Checker"
                                "reminders" -> "Refill Alerts"
                                else -> "Jan-Aushadhi Finder"
                            }
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        },
        bottomBar = {
            if (currentRoute == "home") {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = true,
                        onClick = { }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                        label = { Text("Search") },
                        selected = false,
                        onClick = { navController.navigate("search") }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Place, contentDescription = "Stores") },
                        label = { Text("Stores") },
                        selected = false,
                        onClick = { navController.navigate("stores") }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Notifications, contentDescription = "Alerts") },
                        label = { Text("Alerts") },
                        selected = false,
                        onClick = { navController.navigate("reminders") }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("search") { SearchScreen() }
            composable("stores") { StoresScreen() }
            composable("stock") { StockCheckerScreen() }
            composable("reminders") { RemindersScreen() }
        }
    }
}