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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composedanke.R
import com.example.composedanke.common.dpToSp
import com.example.composedanke.common.noRippleClickable
import com.example.composedanke.ui.danke.join.JoinViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NickNameView(mViewModel: JoinViewModel, pagerState: PagerState) {
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .noRippleClickable {
                focusManager.clearFocus()
            },
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
            text = "환영해요!\n닉네임을 설정해 주세요."
        )
        /**닉네임*/
        /**닉네임*/
        Spacer(modifier = Modifier.height(60.dp))
        Row {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                fontSize = dpToSp(dp = 13.dp),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                text = "닉네임"
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(end = 24.dp)
                    .alpha(0.6f),
                fontSize = dpToSp(dp = 13.dp),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                text = "${mViewModel.nameTextLength.value}/6"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(horizontal = 20.dp),
        ) {
            TextField(
                value = mViewModel.nameText.value,
                onValueChange = { textValue ->
                    if (textValue.length > 6) return@TextField
                    mViewModel.setNameText(textValue)
                    mViewModel.setNameTextLength(textValue.length.toString())
                },
                singleLine = true,
                placeholder = {
                    Text(
                        modifier = Modifier.alpha(0.4f),
                        fontSize = dpToSp(dp = 14.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        text = "6자 이내로 입력해 주세요"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    containerColor = Color(0xFF212129),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 14.dp),
                fontSize = dpToSp(dp = 14.dp),
                color = Color(0xff5480f7),
                fontWeight = FontWeight.Medium,
                text = "중복확인"
            )
        }
        /**가입코드*/
        Spacer(modifier = Modifier.height(50.dp))
        Row {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                fontSize = dpToSp(dp = 13.dp),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                text = "가입코드"
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(end = 24.dp)
                    .alpha(0.6f),
                fontSize = dpToSp(dp = 13.dp),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                text = "${mViewModel.codeTextLength.value}/10"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = mViewModel.codeText.value,
            onValueChange = { textValue ->
                if (textValue.length > 10) return@TextField
                mViewModel.setCodeText(textValue)
                mViewModel.setCodeTextLength(textValue.length.toString())
            },
            singleLine = true,
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.4f),
                    fontSize = dpToSp(dp = 14.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    text = "대소문자/숫자/기호/띄어쓰기를 확인해 주세요",
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                containerColor = Color(0xFF212129),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(horizontal = 20.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(1, 0f)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff1e3253),
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
        Spacer(modifier = Modifier.height(12.dp))
    }
}