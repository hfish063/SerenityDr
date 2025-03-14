package com.example.serenitydr.ui.screens.addRouteScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.serenitydr.ui.composables.SearchBar
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

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveRouteScreen(navController: NavController) {
    val saveRouteViewModel: SaveRouteScreenViewModel = viewModel()
    val routeDetails by remember { saveRouteViewModel.routeState }
    val temp = LatLng(34.2164, -119.0376)
    val cameraPos = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(temp, 15f)
    }

    val description: String = routeDetails.route.description ?: ""

    var searchActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        // Route title
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

        // Google maps
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
                routeDetails.route.coordinates.forEach { routeCoord ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(routeCoord.latitude, routeCoord.longitude)
                        )
                    )
                }
            }
            OutlinedButton(
                modifier = Modifier.padding(16.dp),
                onClick = { searchActive = true },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Start a location search"
                )
            }

            // Location search
            if (searchActive) {
                ModalBottomSheet(onDismissRequest = { searchActive = false }) {
                    SearchBar(
                        currValue = "",
                        onValueChange = {},
                        onSubmit = {},
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

        // Remove coordinate(s)
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

        // Route description
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


