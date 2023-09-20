package com.example.shacklehotelbuddy.ui.components

import android.app.DatePickerDialog
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.theme.White

@Composable
fun MainColumn(
    checkInSelectedDateText: String,
    onCheckInDateChange: (String) -> Unit,
    checkOutSelectedDateText: String,
    onCheckOutDateChange: (String) -> Unit,
    adultText: String,
    onAdultTextChange: (String) -> Unit,
    childrenText: String,
    onChildrenTextChange: (String) -> Unit,
    checkInDatePicker: DatePickerDialog,
    checkOutDatePicker: DatePickerDialog,
    focusManager: FocusManager,
    content: @Composable () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        HeaderText(stringResource(R.string.select_guests_date_and_time))

        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = White),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                DateRow(
                    iconId = R.drawable.event_upcoming,
                    label = stringResource(R.string.check_in_date),
                    value = checkInSelectedDateText,
                    onValueChange = onCheckInDateChange,
                    placeholder = stringResource(R.string.date_hint),
                    datePickerDialog = checkInDatePicker,
                    focusManager = focusManager,
                    imeAction = ImeAction.Next,
                    tag = "CheckInTag"
                )

                Divider()

                DateRow(
                    iconId = R.drawable.event_available,
                    label = stringResource(R.string.check_out_date),
                    value = checkOutSelectedDateText,
                    onValueChange = onCheckOutDateChange,
                    placeholder = stringResource(R.string.date_hint),
                    datePickerDialog = checkOutDatePicker,
                    focusManager = focusManager,
                    imeAction = ImeAction.Next,
                    tag = "CheckOutTag"
                )

                Divider()

                NumberRow(
                    iconId = R.drawable.person,
                    label = stringResource(R.string.adults),
                    value = adultText,
                    onValueChange = onAdultTextChange,
                    placeholder = stringResource(R.string.adults),
                    focusManager = focusManager,
                    imeAction = ImeAction.Next,
                    tag = "AdultsTag"
                )

                Divider()

                NumberRow(
                    iconId = R.drawable.supervisor_account,
                    label = stringResource(R.string.children),
                    value = childrenText,
                    onValueChange = onChildrenTextChange,
                    placeholder = stringResource(R.string.app_name), //TODO:
                    focusManager = focusManager,
                    imeAction = ImeAction.Done,
                    tag = "ChildrenTag"
                )
            }
        }
    }
}

@Composable
fun HeaderText(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(16.dp),
        text = text,
        style = ShackleHotelBuddyTheme.typography.bodyMedium,
        color = ShackleHotelBuddyTheme.colors.white
    )
}

@Composable
fun Divider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(ShackleHotelBuddyTheme.colors.grayBorder)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRow(
    iconId: Int,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    datePickerDialog: DatePickerDialog,
    focusManager: FocusManager,
    imeAction: ImeAction,
    tag: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null, // Provide appropriate content description
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.background(Color.Transparent),
                text = label,
                style = ShackleHotelBuddyTheme.typography.bodyMedium,
                color = ShackleHotelBuddyTheme.colors.grayText
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(ShackleHotelBuddyTheme.colors.grayBorder)
        )
        OutlinedTextField(
            value = value,
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag(tag = tag)
                .clickable { datePickerDialog.show() },
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.grayText
                )
            },
            textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
            maxLines = 1,
            colors = textFieldColors(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NumberRow(
    iconId: Int,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    focusManager: FocusManager,
    imeAction: ImeAction,
    tag: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null, // Provide appropriate content description
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.background(Color.Transparent),
                text = label,
                style = ShackleHotelBuddyTheme.typography.bodyMedium,
                color = ShackleHotelBuddyTheme.colors.grayText
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(ShackleHotelBuddyTheme.colors.grayBorder)
        )
        OutlinedTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag("tag")
                .onPreviewKeyEvent {
                    if (it.key == Key.Tab && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                        focusManager.moveFocus(FocusDirection.Down)
                        true
                    } else {
                        false
                    }
                },
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.grayText
                )
            },
            textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction
            ),
            colors = textFieldColors(),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
    }
}

