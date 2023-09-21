package com.example.shacklehotelbuddy

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shacklehotelbuddy.ui.components.MainColumn
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.theme.Teal
import com.example.shacklehotelbuddy.ui.theme.White
import com.test.domain.model.PropertyQuery
import java.util.Calendar

@Composable
fun HotelSearchScreen(
    navController: NavController,
    searchQueryList: List<PropertyQuery>,
    onSearchClick: (PropertyQuery) -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var checkInDate by remember { mutableStateOf("") }
    var checkOutDate by remember { mutableStateOf("") }
    var adultCount by remember { mutableStateOf("") }
    var childrenCount by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val currentYear = calendar[Calendar.YEAR]
    val currentMonth = calendar[Calendar.MONTH]
    val currentDay = calendar[Calendar.DAY_OF_MONTH]

    val checkInDatePicker =
        createDatePickerDialog(context, currentYear, currentMonth, currentDay) { year, month, day ->
            checkInDate = "$day/${month + 1}/$year"
        }

    val checkOutDatePicker =
        createDatePickerDialog(context, currentYear, currentMonth, currentDay) { year, month, day ->
            checkOutDate = "$day/${month + 1}/$year"
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillWidth
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainColumn(
                checkInSelectedDateText = checkInDate,
                onCheckInDateChange = { newDate -> checkInDate = newDate },
                checkInDatePicker = checkInDatePicker,
                checkOutSelectedDateText = checkOutDate,
                onCheckOutDateChange = { newDate -> checkOutDate = newDate },
                checkOutDatePicker = checkOutDatePicker,
                adultText = adultCount,
                onAdultTextChange = { newText -> adultCount = newText },
                childrenText = childrenCount,
                onChildrenTextChange = { newText -> childrenCount = newText },
                focusManager = focusManager
            ) {

            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .absolutePadding(16.dp, 12.dp, 0.dp, 8.dp),
                text = stringResource(R.string.recent_searches),
                style = ShackleHotelBuddyTheme.typography.bodyMedium,
                color = ShackleHotelBuddyTheme.colors.white
            )

            LazyColumn {
                items(
                    items = searchQueryList
                ) {
                    SearchQueryItem(
                        task = it,
                        navController
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(60.dp)
                    .background(Teal, shape = RoundedCornerShape(20.dp)),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        if (checkInDate.isNotEmpty() && checkOutDate.isNotEmpty() && adultCount.isNotEmpty() && childrenCount.isNotEmpty()) {
                            val searchQuery = PropertyQuery(
                                checkedInDate = checkInDate,
                                checkedOutDate = checkOutDate,
                                adults = adultCount.toInt(),
                                children = childrenCount.toInt(),
                                time = System.currentTimeMillis()
                            )
                            onSearchClick(searchQuery)
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.valid_info),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        text = stringResource(R.string.search),
                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun createDatePickerDialog(
    context: android.content.Context,
    year: Int,
    month: Int,
    day: Int,
    onDateSet: (year: Int, month: Int, day: Int) -> Unit
): DatePickerDialog {
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            onDateSet(selectedYear, selectedMonth, selectedDayOfMonth)
        },
        year,
        month,
        day
    )
    datePickerDialog.datePicker.minDate = Calendar.getInstance().timeInMillis
    return datePickerDialog
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchQueryItem(
    task: PropertyQuery,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .absolutePadding(16.dp, 6.dp, 16.dp, 6.dp)
            .fillMaxWidth()
            .height(48.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.apply {
                set("propertyQuery", task)
            }
            navController.navigate(AppScreen.PropertyListScreen.route)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(8.dp, 0.dp, 8.dp, 0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.manage_history),
                contentDescription = stringResource(R.string.search),
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                text = "${task.checkedInDate} - ${task.checkedOutDate}    ${task.adults} adult, ${task.children} children",
                style = ShackleHotelBuddyTheme.typography.bodyMedium,
                color = ShackleHotelBuddyTheme.colors.grayText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun SearchQueryScreenView() {
    ShackleHotelBuddyTheme {
        HotelSearchScreen(
            rememberNavController(),
            listOf(),
            onSearchClick = {

            }
        )
    }
}

@Preview
@Composable
private fun SearchQueryScreenItemView() {
    ShackleHotelBuddyTheme {
        SearchQueryItem(
            PropertyQuery(2, "2/08/2023", "3/08/2023", 2, 3, 4L),
            rememberNavController()
        )
    }
}
