package com.example.serenitydr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.serenitydr.model.Coordinate
import com.example.serenitydr.ui.theme.SerenityDrTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SerenityDrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        //We Might want to move this
                        SaveRouteScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SerenityDrTheme {
        Greeting("Android")
    }
}

@Composable
fun SaveRouteScreen() {
    val temp = LatLng(.001, .001)
    val cameraPos = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(temp, 10f)
    }

    var routeTitle by remember { mutableStateOf("") }
    var routeDesc by remember { mutableStateOf("") }
    var routeCoords by remember { mutableStateOf(emptyList<Coordinate>()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        TextField(
            value = routeTitle,
            singleLine = true,
            label = { Text("Title") },
            onValueChange = { routeTitle = it },
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
                    routeCoords = routeCoords + Coordinate(
                        latitude = latLng.latitude,
                        longitude = latLng.longitude,
                        order = routeCoords.count(),
                        id = 0
                    )
                }
            ) {
                //do google map calls here?
                routeCoords.forEach { routeCoord ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(routeCoord.latitude, routeCoord.longitude)
                        )
                    )
                }
            }
        }
        TextField(
            value = routeDesc,
            label = { Text("Description") },
            onValueChange = { routeDesc = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 8.dp)
        )
        Button(
            onClick = { handleSaveRoute(routeTitle, routeDesc) },
        ) { Text("Submit") }
    }
}

fun handleSaveRoute(title: String, desc: String) {
    //Validation for route data
    if (title.length < 5)
        return

    //SEND DATA HERE

}
