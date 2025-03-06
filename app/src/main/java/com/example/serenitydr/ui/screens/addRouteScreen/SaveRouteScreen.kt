package com.example.serenitydr.ui.screens.addRouteScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun SaveRouteScreen(saveRouteViewModel: SaveRouteScreenViewModel = viewModel()) {
    val routeDetails by remember { saveRouteViewModel.routeState }
    val temp = LatLng(.001, .001)
    val cameraPos = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(temp, 10f)
    }

    val title: String = routeDetails.route.title
    val description: String = routeDetails.route.description ?: ""

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        TextField(
            value = title,
            singleLine = true,
            label = { Text("Title") },
            onValueChange = { saveRouteViewModel.onTitleChange(it) },
            placeholder = { Text("A Drive Around the Block") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)

        )
        Box(
            Modifier
                .fillMaxHeight(0.5f)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            GoogleMap(
                cameraPositionState = cameraPos,
                properties = MapProperties(mapType = MapType.NORMAL),
                onMapClick = { latLng ->
                    saveRouteViewModel.addCoord(lat = latLng.latitude, lng = latLng.longitude)
                }
            ) {
                //do google map calls here?
                saveRouteViewModel.routeState.value.route.coordinates.forEach { routeCoord ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(routeCoord.latitude, routeCoord.longitude)
                        )
                    )
                }
            }
        }
        TextField(
            value = description,
            label = { Text("Description") },
            onValueChange = { saveRouteViewModel.onDescChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 8.dp)
        )
        Button(
            onClick = { saveRouteViewModel.handleSaveRoute() },
        ) { Text("Submit") }
    }
}


