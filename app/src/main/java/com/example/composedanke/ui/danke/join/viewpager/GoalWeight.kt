package com.example.composedanke.ui.danke.join.viewpager

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedanke.R
import com.example.composedanke.common.dpToSp
import com.example.composedanke.ui.danke.join.JoinViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.Forest.d

/** range와 index
 * range == 300~1000
 * range /10 을 진행해 30.0 ~ 100.0으로 변환
 * index == 0~700
 * 기존 0~100으로 계산하던 로직들을
 * index는 300을 더하거나 빼서 계산해줘야 함*/
val firstRange = 300
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoalWeight(mViewModel: JoinViewModel, pagerState: PagerState) {
    Timber.d("logTest view")
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            modifier = Modifier.padding(start = 14.dp, top = 16.dp),
            painter = painterResource(id = R.drawable.img_diagnosis_good),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            modifier = Modifier.padding(start = 20.dp),
            fontSize = dpToSp(dp = 20.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            text = "아무개님 몸무게를 알려주세요."
        )

        Spacer(modifier = Modifier.height(116.dp))

        /** ruler 레이아웃*/
//        val range = IntRange(900, 2000)
        val range = IntRange(firstRange, 1000)
        val state = rememberLazyListState()
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = dpToSp(dp = 36.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            text = String.format("%.1f", mViewModel.tallValue.value.toFloat() / 10f)
        )
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            /** 스크롤 리스너*/
            val nestedScrollConnection = remember {
                object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        coroutineScope.launch {
                            state.animateScrollAndCentralizeItem(mViewModel, maxWidth)
                        }
                        return super.onPreScroll(available, source)
                    }
                }
            }

            LazyRow(
                modifier = Modifier.nestedScroll(nestedScrollConnection),
                state = state,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = state),
                contentPadding = PaddingValues(
                    start = this.maxWidth / 2 - 6.dp,
                    end = this.maxWidth / 2 - 6.dp
                )
            ) {
                itemsIndexed(range.toList()) { index, item ->
                    WeightRowItem(
                        state,
                        index,
                        item,
                        mViewModel,

                        )
                }
            }
            LaunchedEffect(key1 = "test") {
                state.scrollToItem(550-firstRange, 0)
                mViewModel.setTallValue("550")
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .width(3.dp)
                    .height(160.dp)
                    .background(Color.White)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(3, 0f)
                    }
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontSize = dpToSp(dp = 15.dp),
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        text = "이전"
                    )
                }
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(5, 0f)
                    }
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff5480f7),
                ),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontSize = dpToSp(dp = 15.dp),
                        fontWeight = FontWeight.Medium,
                        text = "다음"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}


/** 1.LazyListState로 진행한 경우
 * - 처음과 끝 패딩 있는부분 새로고침이 안됨(리스너를 안탐), ex) 0이 가운데 있는 경우 이벤트에 안들어와서 값을 변경할 수 없었음
 * 2. NestedScrollConnection 리스터로 진행한 경우
 * - LazyListState와 동일하게 TallRowItem이 리프레쉬 되진 않아음
 * - 스크롤 움직일 때마다 LazyListState로 인덱스 값으로 값 변경
 * - tall 값이 변경되면서 TallRowItem을 다시 리프레쉬 시킴
 * - 결론 스크롤로 진행해서 성공함*/
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WeightRowItem(
    state: LazyListState, index: Int, item: Int, mViewModel: JoinViewModel
) {
    val tenUnit = item % 10
    Column(
        modifier = Modifier.height(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom
    ) {
        /*  d("lowTest index : ${index}")
          d("lowTest item : ${item}")
          d("lowTest itemInfo : ${itemInfo?.index}")*/
        d("lowTest index : ${index.toFloat()}")
        d("lowTest mViewModel.tallValue.value : ${mViewModel.tallValue.value}")

        val barColor =
            when (index.toFloat()+firstRange) {
                in mViewModel.tallValue.value.toFloat()..550f -> {
                    Color.Blue
                }

                in 551f..mViewModel.tallValue.value.toFloat() -> {
                    Color.Magenta
                }

                else -> {
                    Color.White
                }
            }
        Row() {
            Box(
                modifier = Modifier
                    .width(12.dp)
                    .height(35.dp)
                    .background(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(if (tenUnit == 0) 35.dp else 14.dp)
                        .background(barColor)
                        .align(Alignment.BottomCenter)
                )
            }
        }
        /*Text(
            textAlign = TextAlign.Center,
            fontSize = dpToSp(dp = 7.dp),
            fontWeight = FontWeight.Medium,
            color = barColor,
            text = item.toString()//(item/10).toString()
        )*/
    }
}

private fun LazyListState.animateScrollAndCentralizeItem(
    mViewModel: JoinViewModel,
    maxWidth: Dp
) {
    /**viewItemSize : view width 와 막대 하나의 넓이(12dp)를 계산해 한화면에 그려지는 총 아이템 갯수를 계산*/
    val itemInfo = this.layoutInfo.visibleItemsInfo
    val first = itemInfo.first().index +firstRange
    val last = itemInfo.last().index +firstRange

    /** 뷰 전체 아이템 갯수 = 현재는 30*/
    val viewItemSize = (maxWidth / 12.dp).toInt() //현재 30

    /** 현재 보여지는 아이템 갯수 = 여백에 따라 아이템 갯수가 달라짐 15~30 */
    val realItemSize = (itemInfo.size - 1)

    /** 전체 갯수 - 현재 갯수*/
    val calItemSize = viewItemSize - realItemSize
    Timber.d("logTest viewItemSize : ${viewItemSize}")
    Timber.d("logTest first : ${first}")
    Timber.d("logTest last : ${last}")
    Timber.d("logTest realItemSize : ${realItemSize}")
    Timber.d("logTest calItemSize : ${calItemSize}")

    /** 선택된 값 구하는 공식
     * 점과 점사이에 좌표 구하는 공식이며
     * 오른쪽 왼쪽 패딩에 따라 값이 달라져야 해서
     * calItemSize 이용해 계산식을 추가*/
    val centerDot = if (first == firstRange) {
        (first + (last - calItemSize)) / 2
    } else {
        (first + (last + calItemSize)) / 2
    }
    Timber.d("logTest centerDot : ${centerDot}")
    mViewModel.setTallValue((centerDot).toString())
}