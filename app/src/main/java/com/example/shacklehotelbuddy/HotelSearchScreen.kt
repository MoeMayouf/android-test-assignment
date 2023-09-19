package com.example.shacklehotelbuddy

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shacklehotelbuddy.ui.components.MainColumn
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.theme.Teal
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

// ... (rest of your code, including other composables like SearchQueryItem and textFieldColors)
