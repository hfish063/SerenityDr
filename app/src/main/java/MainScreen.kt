package com.example.serenitydr


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val contentNavController = rememberNavController()
    val navItemList = listOf(
        NavItem(label = "Add", Icons.Default.Add, route = "saveRoute"),
        NavItem(label = "Explore", icon = ImageVector.vectorResource(id = R.drawable.explore_24px), route = "explore"),
    )
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HorizontalDivider(thickness = 2.dp)
            NavigationBar(
                containerColor = Color.Unspecified
            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            contentNavController.navigate(navItem.route) {
                                // Ensures we don't stack multiple instances of the same screen
                                popUpTo(contentNavController.graph.startDestinationId) { inclusive = false }
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.label,
                                modifier = Modifier.size(35.dp)
                            )
                        },
                        label = { Text(text = navItem.label) },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentNavigationGraph(contentNavController, Modifier.padding(innerPadding))
    }
}


@Composable
fun ContentNavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "saveRoute", modifier = modifier) {
        composable("explore") { ExploreScreen() }
        composable("saveRoute") { SaveRouteScreen(navController) } // Pass navController correctly
    }
}


// Keep your existing Explore screen unchanged
@Composable
fun ExploreScreen() {
    Text(
        text = "Explore screen (Existing Functionality)",
        modifier = Modifier.fillMaxSize()
    )
}




