package com.example.serenitydr.ui.screens.listAllRoutesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.serenitydr.model.Route

@Composable
fun ViewAllRoutesScreen() {
    val dummyRoutes = listOf(
        Route(id = 1, title = "Scenic Highway", description = "A beautiful coastal drive", coordinates = emptyList()),
        Route(id = 2, title = "Mountain Pass", description = "Winding roads with great views", coordinates = emptyList()),
        Route(id = 3, title = "Desert Trail", description = "Vast landscapes and open skies", coordinates = emptyList()),
        Route(id = 4, title = "Lakeside Road", description = "Peaceful drive by the lake", coordinates = emptyList()),
        Route(id = 5, title = "Urban Cruise", description = "Exploring city streets at night", coordinates = emptyList()),
        Route(id = 6, title = "Forest Pathway", description = "Serene drive through tall trees", coordinates = emptyList()),
        Route(id = 7, title = "Canyon Route", description = "Drive through deep canyons", coordinates = emptyList()),
        Route(id = 8, title = "Sunset Boulevard", description = "Perfect for watching the sunset", coordinates = emptyList()),
        Route(id = 9, title = "Snowy Pass", description = "A winter wonderland drive", coordinates = emptyList()),
        Route(id = 10, title = "Riverside Trail", description = "Following the gentle river bends", coordinates = emptyList()),
        Route(id = 11, title = "This is a very long route title that should definitely overflow", description =
        "This description is excessively long and should demonstrate how text gets truncated when it exceeds the designated space in the UI " +
                "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"
                + "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", coordinates = emptyList())
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(text = "All Routes", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(dummyRoutes) { route ->
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    com.example.serenitydr.ui.screens.RouteCard(route)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(64.dp)) // Space for future navigation bar
    }
}

@Composable
fun RouteCard(route: Route) {
    Card(
        modifier = Modifier.fillMaxWidth(0.95f).height(150.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = route.title, style = MaterialTheme.typography.bodyLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
            route.description?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = it, style = MaterialTheme.typography.bodyMedium, maxLines = 4, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}
