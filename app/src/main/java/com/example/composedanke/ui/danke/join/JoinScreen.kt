package com.example.composedanke.ui.danke.join

import androidx.compose.foundation.background
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
internal fun JoinScreen(
    viewModel: JoinViewModel,
    onClickOne: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Join Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDankeTheme {
        val viewModel: JoinViewModel = viewModel(
            factory = JoinViewModel.Factory
        )
        JoinScreen(viewModel = viewModel, onClickOne = {})
    }
}
