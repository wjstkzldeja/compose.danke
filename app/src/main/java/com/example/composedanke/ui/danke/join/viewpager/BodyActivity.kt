package com.example.composedanke.ui.danke.join.viewpager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.composedanke.R
import com.example.composedanke.common.dpToSp
import com.example.composedanke.ui.danke.join.JoinViewModel
import kotlinx.coroutines.launch
import timber.log.Timber.Forest.d

/**
 *  https://developer.android.com/jetpack/compose/animation?hl=ko
 * */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyActivity(mViewModel: JoinViewModel, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)) {
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
                text = "평소 신체 활동량도 궁금해요!"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
//                .height(400.dp)
                    .fillMaxHeight()
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                /**animateDpAsState*/
                val isLowAni = remember { mutableStateOf(false) }
                val changeWidthLow by animateDpAsState(
                    targetValue = if (isLowAni.value) 216.dp else 68.dp,
                    label = ""
                )
                val changeAlphaLow by animateFloatAsState(
                    targetValue = if (isLowAni.value) 0f else 1f,
                    label = ""
                )
                val isMediumAni = remember { mutableStateOf(false) }
                val changeWidthMedium by animateDpAsState(
                    targetValue = if (isMediumAni.value) 216.dp else 68.dp,
                    label = "",
                )
                val changeAlphaMedium by animateFloatAsState(
                    targetValue = if (isMediumAni.value) 0f else 1f,
                    label = ""
                )
                Box(
                    modifier = Modifier
                        .width(changeWidthLow)
                        .height(changeWidthLow)
                        .clip(CircleShape)
                        .clickable {
                            isLowAni.value = !(isLowAni.value)
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.img_lazysloth),
                        contentDescription = ""
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .alpha(changeAlphaLow)
                            .background(Color(0xff282834))
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            fontSize = dpToSp(dp = 14.dp),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            text = "적어요"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(9.dp))
                Box(
                    modifier = Modifier
                        .width(changeWidthMedium)
                        .height(changeWidthMedium)
                        .clip(CircleShape)
                        .clickable {
                            isMediumAni.value = !(isMediumAni.value)
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.img_activedog),
                        contentDescription = ""
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .alpha(changeAlphaMedium)
                            .background(Color(0xff282834))
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            fontSize = dpToSp(dp = 14.dp),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            text = "적당해요"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(9.dp))
                /**animateContentSize*/
                val changeWidth = remember { mutableStateOf(68.dp) }
                Box(
                    modifier = Modifier
                        .width(changeWidth.value)
                        .height(changeWidth.value)
                        .clip(CircleShape)
                        .animateContentSize(
                            /*animationSpec = tween(
                                durationMillis = 1000,
                                delayMillis = 1000
                            )*/
                            /*animationSpec = snap(1000)*/
                            /*animationSpec = keyframes { },*/
                            finishedListener = { initialValue: IntSize, targetValue: IntSize ->
                                d("ani finish")
                            },

                            )
                        .background(Color(0xff282834))
                        .clickable {
                            if (changeWidth.value == 68.dp) {
                                changeWidth.value = 216.dp
                            } else changeWidth.value = 68.dp

                        }
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        fontSize = dpToSp(dp = 14.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        text = "적당해요"
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img_muscle_gorilla),
                        contentDescription = ""
                    )
                }

                /**AnimatedVisibility*/
                var visible by remember { mutableStateOf(true) }
                Box(
                    modifier = Modifier
                        .width(68.dp)
                        .height(68.dp)
                        .clip(CircleShape)
                        .background(Color(0xff282834))
                        .clickable {
                            visible = visible.not()
                        }
                ) {
                    this@Column.AnimatedVisibility(
                        modifier = Modifier.align(Alignment.Center),
                        visible = visible,
                        enter = scaleIn(),
                        exit = scaleOut(),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_muscle_gorilla),
                            contentDescription = ""
                        )
                    }
                    this@Column.AnimatedVisibility(
                        modifier = Modifier.align(Alignment.Center),
                        visible = visible,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            fontSize = dpToSp(dp = 14.dp),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            text = "적당해요"
                        )
                    }
                }
            }
        }
        /** bottom buttons*/
        Box(modifier = Modifier.fillMaxSize().padding(bottom = 12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(2, 0f)
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
                            pagerState.scrollToPage(4, 0f)
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
        }
    }
}