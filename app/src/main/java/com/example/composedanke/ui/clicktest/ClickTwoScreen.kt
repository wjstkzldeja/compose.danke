package com.example.composedanke.ui.clicktest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedanke.nav.obsTest
import com.example.composedanke.ui.theme.ComposeDankeTheme

var viewModelT = ClickTwoViewModel()

@Composable
internal fun ClickTwoScreen(
    viewModel: ClickTwoViewModel =viewModel(),
    onClickOne: () -> Unit,
    onThreeClick: () -> Unit,
) {
    viewModelT = viewModel
    val obsTest: String by obsTest.observeAsState("옵저버 대기")
    val count = remember { mutableStateOf(0) }
    val viewModelText: String by viewModel.viewModelText.observeAsState("옵저버 대기")

    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        OnClickTestOne(count)
        OnClickTestTwo(onClickOne, obsTest)
        OnClickTestThree(viewModel, viewModelText)
        OnThreeScreen(onThreeClick)
//        OnClickTestFour(navControl)
    }
}

@Composable
fun OnClickTestOne(count: MutableState<Int>) {
    Column(modifier = Modifier.padding(bottom = (30).dp)) {
        Text(text = "click_1", modifier = Modifier.clickable { count.value += 1 })
        Text(text = "${count.value}")
    }
}

@Composable
fun OnClickTestTwo(onClickOne: () -> Unit, obsTest: String) {
    Column(modifier = Modifier.padding(bottom = (30).dp)) {
        Text(text = "click_2", modifier = Modifier.clickable { onClickOne() })
        Text(text = obsTest)
    }
}

@Composable
fun OnClickTestThree(viewModel: ClickTwoViewModel, viewModelText: String) {
    Column(modifier = Modifier.padding(bottom = (30).dp)) {
        Text(text = "click_3", modifier = Modifier.clickable { viewModelT.setText("asdasd") })
        Text(text = viewModelText)
    }
}

@Composable
fun OnThreeScreen(onThreeClick: () -> Unit) {
    Column(modifier = Modifier.padding(bottom = (30).dp)) {
        Text(text = "click_4", modifier = Modifier.clickable { onThreeClick() })
    }
}

/*@Composable
fun OnClickTestFour(navControl: NavHostController) {
    Text(text = "onBackPressed", modifier = Modifier.clickable { navControl.popBackStack() })
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreviewClickTwo() {
    ComposeDankeTheme {
        val viewModel: ClickTwoViewModel = viewModel(
            factory = ClickTwoViewModel.Factory
        )
        ClickTwoScreen(viewModel = viewModel, onClickOne = {},onThreeClick = {})
    }
}
