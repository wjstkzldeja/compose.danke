package com.example.composedanke.ui.danke.login

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

private var mViewModel = LoginViewModel()

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDankeTheme {
        val viewModel: LoginViewModel = viewModel(
            factory = LoginViewModel.Factory
        )
//        Test("test",onClick = { }, )
        LoginScreen(viewModel = viewModel, onJoinView = {})
    }
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onJoinView: () -> Unit
) {
    mViewModel = viewModel

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
        SnsLoginButtons()
    }
    BottomSheet(onJoinView)
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
            .wrapContentHeight(),
        state = pagerState,
    ) { pager ->
        Column {
            LottieView(pager)
            ContentTextView(pager)
        }
    }

    /* column에 센터정렬 되어있어 안해도 되지만 이런 식으로도 가운데 정렬 가능해서 냅둠*/
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPagerIndicator(
            pagerState = pagerState, pageCount = 3,
            activeColor = colorResource(id = R.color.white),
            inactiveColor = colorResource(id = R.color.purple_200),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(26.dp),
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
        modifier = Modifier.height(398.dp),
        composition = composition,
        progress = { progress },
    )
}

@Composable
fun ContentTextView(pager: Int) {
    mViewModel.changeContentText(pager)
    /* 텍스트 영역 주고 정렬 시킬때도 box 사용해야하는듯
 text의 경우 dp 가 없어서 커스텀으로 만들어 사용해야함*/
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = stringResource(id = mViewModel.content.value),
            color = colorResource(id = R.color.white),
            fontSize = dpToSp(dp = 20.dp)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(0.6f),
            textAlign = TextAlign.Center,
            text = stringResource(id = mViewModel.contentSub.value),
            color = colorResource(id = R.color.white),
            fontSize = dpToSp(dp = 13.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnsLoginButtons() {
    Button(
        onClick = {
            mViewModel.setSheetState(true)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xfffee500),
        ),
        contentPadding = PaddingValues(6.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.ic_login_kakao), contentDescription = "")
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = dpToSp(dp = 15.dp),
                fontWeight = FontWeight.Medium,
                text = "카카오 로그인"
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
    Button(
        onClick = { mViewModel.setSheetState(true) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        ),
        contentPadding = PaddingValues(6.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.ic_login_google),
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = dpToSp(dp = 15.dp),
                fontWeight = FontWeight.Medium,
                text = "구글 로그인"
            )
        }
    }
}

/**
 * bottom sheeet 머터리얼 버전별 설명
 * https://github-wiki-see.page/m/softeerbootcamp-2nd/H9-88Hey/wiki/%5BAND%5D-Compose-BottomSheet-%EC%82%AC%EC%9A%A9%EA%B8%B0
 * */
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onJoinView: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(mViewModel.isSheetOpen.value)
    var checkedState by remember { mutableStateOf(true) }
    if (mViewModel.isSheetOpen.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                mViewModel.setSheetState(false)
            },
//            dragHandle = {},
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    fontSize = dpToSp(dp = 20.dp),
                    color = Color(0xff222222),
                    fontWeight = FontWeight.Bold,
                    text = "약관에 동의해 주세요"
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.alpha(0.6f),
                    fontSize = dpToSp(dp = 13.dp),
                    color = Color(0xff222222),
                    fontWeight = FontWeight.Medium,
                    text = "안심하세요. 콕핏이 여러분의 개인정보와 \n" +
                            "서비스 이용정보를 안전하게 지켜드릴게요."
                )
                Row(modifier = Modifier.padding(top = 29.dp)) {
                    /**기본 체크박스*/
                    //Checkbox(checked = checkedState, onCheckedChange = { checkedState = it })
                    Image(
                        painter = if (checkedState) painterResource(id = R.drawable.ic_checkbox_sel) else painterResource(
                            id = R.drawable.ic_checkbox_nor
                        ),
                        contentDescription = "Custom Checkbox",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { checkedState = !checkedState }
                    )
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text(
                            fontSize = dpToSp(dp = 16.dp),
                            color = Color(0xff222222),
                            fontWeight = FontWeight.Bold,
                            text = "모두 동의해요"
                        )

                        Text(
                            modifier = Modifier.alpha(0.6f),
                            fontSize = dpToSp(dp = 13.dp),
                            color = Color(0xff222222),
                            fontWeight = FontWeight.Medium,
                            text = "약관 및 개인정보처리방침에 동의합니다."
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .height(2.dp)
                        .background(
                            Color(0xffd0d0d0)
                        )
                )
                Row() {
                    /**기본 체크박스*/
                    //Checkbox(checked = checkedState, onCheckedChange = { checkedState = it })
                    Image(
                        painter = if (checkedState) painterResource(id = R.drawable.ic_check_sel) else painterResource(
                            id = R.drawable.ic_check_nor
                        ),
                        contentDescription = "Custom Checkbox",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { checkedState = !checkedState }
                    )
                    Text(
                        fontSize = dpToSp(dp = 16.dp),
                        color = Color(0xff222222),
                        fontWeight = FontWeight.Bold,
                        text = "(필수) 만 14세 이상이에요"
                    )
                }
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    /**기본 체크박스*/
                    //Checkbox(checked = checkedState, onCheckedChange = { checkedState = it })
                    Image(
                        painter = if (checkedState) painterResource(id = R.drawable.ic_check_sel) else painterResource(
                            id = R.drawable.ic_check_nor
                        ),
                        contentDescription = "Custom Checkbox",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { checkedState = !checkedState }
                    )
                    Text(
                        fontSize = dpToSp(dp = 16.dp),
                        color = Color(0xff222222),
                        fontWeight = FontWeight.Bold,
                        text = "(필수) 서비스 이용약관 동의"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        fontSize = dpToSp(dp = 13.dp),
                        color = Color(0xff222222),
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Medium,
                        text = "보기"
                    )
                }
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    /**기본 체크박스*/
                    //Checkbox(checked = checkedState, onCheckedChange = { checkedState = it })
                    Image(
                        painter = if (checkedState) painterResource(id = R.drawable.ic_check_sel) else painterResource(
                            id = R.drawable.ic_check_nor
                        ),
                        contentDescription = "Custom Checkbox",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { checkedState = !checkedState }
                    )
                    Text(
                        fontSize = dpToSp(dp = 16.dp),
                        color = Color(0xff222222),
                        fontWeight = FontWeight.Bold,
                        text = "(필수) 개인정보처리방침 동의"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        fontSize = dpToSp(dp = 13.dp),
                        color = Color(0xff222222),
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        fontWeight = FontWeight.Medium,
                        text = "보기"
                    )
                }
                Spacer(modifier = Modifier.height(26.dp))
                Button(
                    onClick = {
                        mViewModel.setSheetState(false)
                        onJoinView()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff5480f7),
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = dpToSp(dp = 14.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        text = "가입 완료"
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

