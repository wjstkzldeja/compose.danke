package com.example.composedanke.ui.clicktest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedanke.ui.theme.ComposeDankeTheme

@Composable
fun ClickOneScreen(
    viewModel: ClickOneViewModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Test("Login Screen", onClick)
    }
}

@Composable
fun Test(name: String, onClick: () -> Unit) {
    Text(text = "Hello $name!", modifier = Modifier.clickable { onClick() })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewClickOne() {
    ComposeDankeTheme {
        val viewModel: ClickOneViewModel = viewModel(
            factory = ClickOneViewModel.Factory
        )
//        Test("test",onClick = { }, )
        ClickOneScreen(viewModel = viewModel, onClick = {})
    }
}