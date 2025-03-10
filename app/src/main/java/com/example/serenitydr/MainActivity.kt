package com.example.serenitydr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.serenitydr.ui.screens.addRouteScreen.SaveRouteScreen
import com.example.serenitydr.ui.screens.viewRouteScreen.ViewRouteScreen
import com.example.serenitydr.ui.theme.SerenityDrTheme
import com.example.serenitydr.ui.screens.uiScreen.MainScreen

//Comment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SerenityDrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        //We Might want to move this
                        MainScreen()
                    }
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewViewRouteScreen() {
    ViewRouteScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewAddRouteScreen() {
    SaveRouteScreen()
}*/

