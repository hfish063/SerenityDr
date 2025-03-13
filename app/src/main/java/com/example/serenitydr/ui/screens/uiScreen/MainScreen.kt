package com.example.serenitydr.ui.screens.uiScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.serenitydr.R
import com.example.serenitydr.ui.screens.addRouteScreen.SaveRouteScreen
import com.example.serenitydr.ui.screens.listAllRoutesScreen.ListAllRoutesViewModel
import com.example.serenitydr.ui.screens.listAllRoutesScreen.ViewAllRoutesScreen
import com.example.serenitydr.ui.screens.viewRouteScreen.ViewRouteScreen
import com.example.serenitydr.utils.LocationUtils


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val contentNavController = rememberNavController()

    val navItemList = listOf(
        NavItem(
            label = "Explore",
            icon = ImageVector.vectorResource(id = R.drawable.explore_24px),
            route = "viewRoute"
        ),
        NavItem(label = "Add", icon = Icons.Default.Add, route = "saveRoute"),
    )
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color.Unspecified) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            contentNavController.navigate(navItem.route) {
                                popUpTo("viewRoute") {
                                    inclusive = false
                                } // Keeps Explore screen in history
                                launchSingleTop = true
                                restoreState =
                                    true  // Restores previous state instead of recreating screen
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
    NavHost(navController = navController, startDestination = "viewRoute", modifier = modifier) {
        composable("viewRoute") {
            val viewModel: ListAllRoutesViewModel = viewModel()
            ViewAllRoutesScreen(viewModel, navController)  // Ensure navController is passed
        }
        composable("saveRoute") {
            val context = LocalContext.current
            SaveRouteScreen(
                navController,
                LocationUtils(context)
            )  // Ensure navController is passed
        }
        composable("viewRoute/{routeId}") { backStackEntry ->
            val routeId = backStackEntry.arguments?.getString("routeId")?.toLongOrNull() ?: 0L
            ViewRouteScreen(routeId)  // Pass the route ID to ViewRouteScreen
        }
    }
}
