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
                        HotelSearchScreen(navController, propertyQueryList.value, onSearchClick = {
                            viewModel.insertPropertyQuery(it)
                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set("searchQuery", it)
                            }
                            navController.navigate(AppScreen.PropertyListScreen.route)
                        })
                    }


                    composable(
                        route = AppScreen.PropertyListScreen.route
                    ) {
                        val propertyQuery =
                            navController.previousBackStackEntry?.savedStateHandle?.get<PropertyQuery>(
                                "searchQuery"
                            )
                        val viewModel: ShacklesViewModel = hiltViewModel()

                        viewModel.fetchPropertiesList(
                            propertyQuery?.checkedInDate ?: "",
                            propertyQuery?.checkedOutDate ?: "",
                            propertyQuery?.adults ?: 0,
                            propertyQuery?.children ?: 0
                        )

                        val propertiesList = viewModel.propertiesList.collectAsState()
                        val isLoading = viewModel.isLoading.collectAsState()
                        DetailView(navController = navController, propertiesList, isLoading)
                    }
                }
            }
        }
    }
}
