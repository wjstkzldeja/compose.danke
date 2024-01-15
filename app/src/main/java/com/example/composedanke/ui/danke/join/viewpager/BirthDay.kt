package com.example.composedanke.ui.danke.join.viewpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.composedanke.R
import com.example.composedanke.common.dpToSp
import com.example.composedanke.ui.danke.join.JoinViewModel
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BirthDayView(mViewModel: JoinViewModel, pagerState: PagerState) {
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
            text = "반가워요 아무개님!\n정보를 입력해 주세요."
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            modifier = Modifier.padding(start = 24.dp),
            fontSize = dpToSp(dp = 13.dp),
            color = Color.White,
            fontWeight = FontWeight.Medium,
            text = "생년월일"
        )
        Spacer(modifier = Modifier.height(10.dp))
        /*val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = Instant.now().toEpochMilli()
        )
        DatePicker(state = datePickerState,)*/
        var pickerYYYYValue by remember { mutableIntStateOf(1989) }
        var pickerMMValue by remember { mutableIntStateOf(6) }
        var pickerDDValue by remember { mutableIntStateOf(13) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xff212129))
                    .fillMaxWidth()
                    .height(52.dp)
                    .align(Alignment.Center)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                NumberPicker(
                    modifier = Modifier.width(100.dp),
                    value = pickerYYYYValue,
                    range = 1900..2024,
                    onValueChange = {
                        pickerYYYYValue = it
                    },
                    dividersColor = Color.Transparent,
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = dpToSp(dp = 17.dp),
                        fontWeight = FontWeight.Bold,
                    )
                )

                NumberPicker(
                    modifier = Modifier.width(100.dp),
                    value = pickerMMValue,
                    range = 1..12,
                    onValueChange = {
                        pickerMMValue = it
                    },
                    dividersColor = Color.Transparent,
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = dpToSp(dp = 17.dp),
                        fontWeight = FontWeight.Bold
                    )
                )

                NumberPicker(
                    modifier = Modifier.width(100.dp),
                    value = pickerDDValue,
                    range = 1..31,
                    onValueChange = {
                        pickerDDValue = it
                    },
                    dividersColor = Color.Transparent,
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = dpToSp(dp = 17.dp),
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.padding(start = 24.dp),
            fontSize = dpToSp(dp = 13.dp),
            color = Color.White,
            fontWeight = FontWeight.Medium,
            text = "성별"
        )
        Spacer(modifier = Modifier.height(8.dp))
        var isMan by remember { mutableStateOf(true) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .background(Color.Green)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    isMan = true
                },
                modifier = Modifier
                    .width(156.dp)
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isMan) Color(0xff5480f7) else Color(0xff282834),
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
                        text = "남성"
                    )
                }
            }

            Button(
                onClick = {
                    isMan = false
                },
                modifier = Modifier
                    .width(156.dp)
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isMan) Color(0xff5480f7) else Color(0xff282834),
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
                        text = "여성"
                    )
                }
            }
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
                        pagerState.scrollToPage(0, 0f)
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
                        pagerState.scrollToPage(2, 0f)
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