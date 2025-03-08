package com.example.serenitydr.ui.screens.addRouteScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.serenitydr.ui.theme.Primary
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun SaveRouteScreen(navController: NavController) {
    val saveRouteViewModel: SaveRouteScreenViewModel = viewModel()
    val routeDetails by remember { saveRouteViewModel.routeState }
    val temp = LatLng(34.2164, -119.0376)
    val cameraPos = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(temp, 15f)
    }
    
    val description: String = routeDetails.route.description ?: ""

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        TextField(
            value = routeDetails.route.title,
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
                Polyline(
                    points = saveRouteViewModel.polylinePoints(),
                    color = Primary,
                    width = 40f,
                    endCap = RoundCap(),
                    startCap = RoundCap()
                )
                //do google map calls here?
                routeDetails.route.coordinates.forEach { routeCoord ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(routeCoord.latitude, routeCoord.longitude)
                        )
                    )
                }
            }
        }
        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            Button(
                onClick = { saveRouteViewModel.removeCoord() },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Remove Last Marker"
                )
            }
            Button(
                onClick = { saveRouteViewModel.clearCoords() },
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text("Clear")
            }
        }
        TextField(
            value = description,
            label = { Text("Description") },
            onValueChange = { saveRouteViewModel.onDescChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 8.dp, vertical = 10.dp)
        )
        Button(
            onClick = { saveRouteViewModel.handleSaveRoute() },
            modifier = Modifier.padding(horizontal = 10.dp)
        ) { Text("Submit") }
    }
}


