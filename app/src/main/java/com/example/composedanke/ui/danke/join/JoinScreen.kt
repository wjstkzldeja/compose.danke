package com.example.composedanke.ui.danke.join

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedanke.R
import com.example.composedanke.common.dpToSp
import com.example.composedanke.ui.danke.join.viewpager.BirthDayView
import com.example.composedanke.ui.danke.join.viewpager.BodyActivity
import com.example.composedanke.ui.danke.join.viewpager.GoalSteps
import com.example.composedanke.ui.danke.join.viewpager.GoalWeight
import com.example.composedanke.ui.danke.join.viewpager.NickNameView
import com.example.composedanke.ui.danke.join.viewpager.PodcastSlider
import com.example.composedanke.ui.danke.join.viewpager.TallView
import com.example.composedanke.ui.theme.ComposeDankeTheme

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDankeTheme {
        val viewModel: JoinViewModel = viewModel(
            factory = JoinViewModel.Factory
        )
        JoinScreen(viewModel = viewModel, onClickToday = {})
    }
}

private var mViewModel = JoinViewModel()

@Composable
internal fun JoinScreen(
    viewModel: JoinViewModel,
    onClickToday: () -> Unit,
) {
    /*    BackHandler {

        }*/
    mViewModel = viewModel

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.color_070318))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "기본정보(1/2)",
            color = Color.White,
            fontSize = dpToSp(dp = 13.dp)
        )
        Spacer(modifier = Modifier.height(9.dp))
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .padding(horizontal = 16.dp)
                    .alpha(0.2f)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(mViewModel.progressBar.value)
                    .height(3.dp)
                    .padding(horizontal = 16.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
            )
        }
        JoinViewPager()
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JoinViewPager() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        6
    }

    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        state = pagerState,
        userScrollEnabled = false,
    ) { pager ->
        when (pager) {
            0 -> {
                mViewModel.setProgressBar(0.25f)
                NickNameView(mViewModel, pagerState)
            }

            1 -> {
                mViewModel.setProgressBar(0.5f)
                BirthDayView(mViewModel,pagerState)
            }

            2 -> {
                TallView(mViewModel,pagerState)
    /*            PodcastSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    currentValueLabel = { value ->
                        androidx.compose.material.Text(
                            text = "${(value / 10)}.${(value % 10)}x",
                            style = MaterialTheme.typography.h6
                        )
                    },
                    indicatorLabel = { value ->
                        if (value % 5 == 0) {
                            androidx.compose.material.Text(
                                text = "${(value / 10)}.${(value % 10)}",
                                style = MaterialTheme.typography.body2,
                            )
                        }
                    }
                )*/
            }

            3 -> {
                mViewModel.setProgressBar(0.9f)
                BodyActivity(mViewModel,pagerState)
            }

            4 -> {
                GoalWeight(mViewModel,pagerState)
            }

            else -> {
                GoalSteps(mViewModel,pagerState)
            }
        }
    }
}
