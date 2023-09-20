package com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.main

import android.annotation.SuppressLint
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shacklehotelbuddy.HiltTestActivity
import com.example.shacklehotelbuddy.HotelSearchScreen
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ShacklesViewModel
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.google.common.truth.Truth.assertThat
import com.test.domain.repository.ShacklesRepository
import com.test.domain.usecase.GetPropertiesListUseCase
import com.test.domain.usecase.GetPropertyQueryUseCase
import com.test.domain.usecase.InsertPropertyQueryUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    @Inject
    lateinit var shackleRepository: ShacklesRepository

    private lateinit var viewModel: ShacklesViewModel
    private lateinit var navController: TestNavHostController

    @Mock
    private lateinit var getSearchQueryUseCase: GetPropertyQueryUseCase

    @Mock
    private lateinit var insertSearchQueryUseCase: InsertPropertyQueryUseCase

    @Mock
    private lateinit var getPropertiesListUseCase: GetPropertiesListUseCase

    private val activity get() = composeTestRule.activity

    private fun injectAndSetupDi() {
        hiltRule.inject()
        getSearchQueryUseCase = GetPropertyQueryUseCase(shackleRepository)
        insertSearchQueryUseCase = InsertPropertyQueryUseCase(shackleRepository)
        getPropertiesListUseCase = GetPropertiesListUseCase(shackleRepository)
        viewModel =
            ShacklesViewModel(
                getPropertiesListUseCase,
                getSearchQueryUseCase,
                insertSearchQueryUseCase
            )
        setContent()
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    private fun setContent() {
        composeTestRule.setContent {
            ShackleHotelBuddyTheme {
                Surface {
                    navController = TestNavHostController(LocalContext.current).apply {
                        navigatorProvider.addNavigator(ComposeNavigator())
                    }
                    HotelSearchScreen(navController, viewModel.propertyQueryList.value) {}
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewsDisplayedProperly() = runBlockingTest {
        injectAndSetupDi()

        // Assert that all necessary texts are displayed
        activity.getString(R.string.check_in_date).assertIsDisplayed()
        activity.getString(R.string.check_out_date).assertIsDisplayed()
        activity.getString(R.string.adults).assertIsDisplayed()
        activity.getString(R.string.children).assertIsDisplayed()

        // Test Check-In Date Input
        "CheckInTag".performTextReplacement("08/09/2023")
        "CheckInTag".assertExists()
        "CheckInTag".performClick()
        Espresso.pressBack()

        // Test Adult Count Input
        val resultText = "2"
        "AdultsTag".performTextInput(resultText)
        "AdultsTag".assert(hasText(resultText))

        // Test Children Count Input
        "ChildrenTag".performTextInput(resultText)
        "ChildrenTag".assert(hasText(resultText))

        // Test Check-Out Date Input
        "CheckOutTag".performClick()
        Espresso.pressBack()
        "CheckOutTag".performTextReplacement("15/09/2023")
        "CheckOutTag".assertExists()
        Espresso.pressBack()

        // Test Search Button Click
        activity.getString(R.string.search).performClick()
        assertThat(navController.currentBackStackEntry?.destination?.route).isNull()
    }

    private fun String.assertIsDisplayed() {
        composeTestRule.onNodeWithText(this).assertIsDisplayed()
    }

    private fun String.performTextReplacement(text: String) {
        composeTestRule.onNodeWithTag(this).performTextReplacement(text)
    }

    private fun String.performTextInput(text: String) {
        composeTestRule.onNodeWithTag(this).performTextInput(text)
    }

    private fun String.performClick() {
        composeTestRule.onNodeWithTag(this).performClick()
    }

    private fun String.assertExists() {
        assertThat(composeTestRule.onNodeWithTag(this).assertExists()).isNotNull()
    }

    private fun String.assert(condition: SemanticsMatcher) {
        composeTestRule.onNodeWithTag(this).assert(condition)
    }
}
