package com.example.serenitydr.ui.screens.viewRouteScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serenitydr.ui.composables.LoadingIcon
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ViewRouteScreen(routeId: Long = 0L) {
    val routeScreenViewModel: ViewRouteScreenViewModel =
        viewModel { ViewRouteScreenViewModel(routeId) }
    val routeDetails by remember { routeScreenViewModel.routeState }

    val temp = LatLng(34.2164, 119.0376)
    val cameraPos = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(temp, 10f)
    }

    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Details", "Reviews")

    if (!routeDetails.isLoading && routeDetails.error == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 36.sp,
                text = routeScreenViewModel.getRouteTitle(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Box(
                Modifier
                    .fillMaxHeight(0.5f)
                    .padding(8.dp)
            ) {
                GoogleMap(
                    cameraPositionState = cameraPos,
                    properties = MapProperties(mapType = MapType.NORMAL)
                )
            }
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                when (tabIndex) {
                    0 -> Text(routeScreenViewModel.getRouteDescription())
                    1 -> Text("Route Reviews")
                }
            }
        }
    } else {
        LoadingIcon()
    }
}