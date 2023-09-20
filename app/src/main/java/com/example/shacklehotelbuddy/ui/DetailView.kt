package com.example.shacklehotelbuddy.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.test.domain.model.Property

/**
 * Detail View: Displays the detail view with a top app bar and a body that shows either a loading view, an empty data view, or a list of properties.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailView(
    navController: NavController,
    propertiesList: State<List<Property>>,
    isLoading: State<Boolean>
) {
    Scaffold(topBar = { TopAppBarView(navController) }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            DetailViewBody(propertiesList, isLoading)
        }
    }
}

/**
 * Detail View Body: Displays either a loading view, an empty data view, or a list of properties based on the loading state and the properties list.
 */
@Composable
fun DetailViewBody(propertiesList: State<List<Property>>, isLoading: State<Boolean>) {
    when {
        isLoading.value -> LoadingView()
        propertiesList.value.isEmpty() -> EmptyDataView()
        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = propertiesList.value) {
                    PropertyItem(property = it)
                }
            }
        }
    }
}

/**
 * Empty Data View: Displays a message indicating that there is no data available.
 */
@Composable
fun EmptyDataView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.no_data),
            style = ShackleHotelBuddyTheme.typography.bodyMedium,
            color = ShackleHotelBuddyTheme.colors.grayText
        )
    }
}

/**
 * Loading View: Displays a circular progress indicator to indicate that data is being loaded.
 */
@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

/**
 * Property Item: Displays the details of a single property including an image, name, location, price, and rating.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyItem(property: Property) {
    val imageSize = 200.dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        GlideImage(
            model = property.propertyImage,
            contentDescription = "image_personal",
            modifier = Modifier
                .fillMaxWidth()
                .size(imageSize)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        ) {
            it.error(R.drawable.ic_launcher_background)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = property.name ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = property.locationName ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.grayText,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = property.priceString ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "4.5",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.black
                )
            }
        }
    }
}

/**
 * Top App Bar View: Displays a top app bar with a title and a navigation icon to navigate back.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(navController: NavController) {
    CenterAlignedTopAppBar(title = { TopAppBarTitleText() }, navigationIcon = {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Outlined.ArrowBack, null, tint = Color.Black)
        }
    })
}

/**
 * Top App Bar Title Text: Displays the title text for the top app bar.
 */
@Composable
fun TopAppBarTitleText() {
    Text(
        text = stringResource(id = R.string.search),
        color = Color.Black,
        style = ShackleHotelBuddyTheme.typography.bodyMedium
    )
}

// ... (rest of your code including preview functions)
