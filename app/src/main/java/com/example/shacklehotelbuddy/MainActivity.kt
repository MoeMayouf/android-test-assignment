package com.example.shacklehotelbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shacklehotelbuddy.ui.DetailView
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.test.domain.model.PropertyQuery
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShackleHotelBuddyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AppScreen.HotelSearchScreen.route
                ) {
                    composable(route = AppScreen.HotelSearchScreen.route) {
                        val viewModel: ShacklesViewModel = hiltViewModel()
                        val propertyQueryList =
                            viewModel.propertyQueryList.collectAsState(emptyList())
                        HotelSearchScreen(navController, propertyQueryList.value) { propertyQuery ->
                            viewModel.insertPropertyQuery(propertyQuery)
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "propertyQuery",
                                propertyQuery
                            )
                            navController.navigate(AppScreen.PropertyListScreen.route)
                        }
                    }

                    composable(route = AppScreen.PropertyListScreen.route) {
                        val propertyQuery =
                            navController.previousBackStackEntry?.savedStateHandle?.get<PropertyQuery>(
                                "propertyQuery"
                            )
                        val viewModel: ShacklesViewModel = hiltViewModel()

                        if (propertyQuery != null) {
                            viewModel.fetchPropertiesList(
                                propertyQuery.checkedInDate!!,
                                propertyQuery.checkedOutDate!!,
                                propertyQuery.adults!!,
                                propertyQuery.children!!
                            )
                        }

                        val propertiesList = viewModel.propertiesList.collectAsState()
                        val isLoading = viewModel.isLoading.collectAsState()
                        DetailView(navController, propertiesList, isLoading)
                    }
                }
            }
        }
    }
}
