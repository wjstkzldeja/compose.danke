package com.example.composedanke.ui.danke.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.composedanke.R
import com.example.composedanke.common.dpToSp
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.google.accompanist.pager.HorizontalPagerIndicator
import timber.log.Timber.Forest.d

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onClick: () -> Unit
) {
    /*column 하위 뷰들은 자동으로 column이 적용되어 있는듯
    * 자등으로 간격 띄워짐*/
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.color_070318))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(52.dp))
        ViewPager()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        3
    }
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .height(398.dp)
            .background(Color.LightGray),
        state = pagerState,
    ) { pager ->
        LottieView(pager)
    }
    /* 텍스트 영역 주고 정렬 시킬때도 box 사용해야하는듯
    text의 경우 dp 가 없어서 커스텀으로 만들어 사용해야함*/
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Red),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.content_1),
            color = colorResource(id = R.color.white),
            fontSize = dpToSp(dp = 13.dp)
        )
    }

    /* column에 센터정렬 되어있어 안해도 되지만 이런 식으로도 가운데 정렬 가능해서 냅둠*/
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
    ) {
        HorizontalPagerIndicator(
            pagerState = pagerState, pageCount = 3,
            activeColor = colorResource(id = R.color.white),
            inactiveColor = colorResource(id = R.color.purple_200),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
        )
    }
}

@Composable
fun LottieView(pager: Int) {
    d("logTest pager : ${pager}")
    val lottieList = arrayListOf<Int>(R.raw.login_1, R.raw.login_2, R.raw.login_3)
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieList[pager]))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDankeTheme {
        val viewModel: LoginViewModel = viewModel(
            factory = LoginViewModel.Factory
        )
//        Test("test",onClick = { }, )
        LoginScreen(viewModel = viewModel, onClick = {})
    }
}