package com.example.composedanke.ui.viewtest.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedanke.ui.theme.ComposeDankeTheme

private var mViewModel = HomeViewModel()

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onClickSearchView: () -> Unit,
    onClickWellnessView: () -> Unit,
    onClickLazyView: () -> Unit,
    onClickOneView: () -> Unit,
    onClickDankeView: () -> Unit,
    onClickKshAlbumView: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 10.dp, top = 10.dp)
    ) {
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        //contentPadding 기본 여백 제거
        Button(
            onClick = { onClickSearchView() },
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(0.dp),
            contentPadding = PaddingValues(0.dp),
            border = BorderStroke(
                1.dp, Color.Black
            )
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "검색 화면")
        }
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        Button(
            onClick = { onClickWellnessView() },
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(0.dp),
            border = BorderStroke(
                1.dp, Color.Black
            )
        ) {
            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Wellness 화면")
        }
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        Button(
            onClick = { onClickLazyView() },
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(0.dp),
            border = BorderStroke(
                1.dp, Color.Black
            )
        ) {
            Icon(imageVector = Icons.Default.List, contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Lazy 테스트 화면")
        }
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        Button(
            onClick = { onClickOneView() },
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(0.dp),
            border = BorderStroke(
                1.dp, Color.Black
            )
        ) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "클릭 테스트 화면")
        }
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
        Button(
            onClick = { onClickDankeView() },
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(0.dp),
            border = BorderStroke(
                1.dp, Color.Black
            )
        ) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "당케 테스트 화면")
        }

        Button(
            onClick = { onClickKshAlbumView() },
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(0.dp),
            border = BorderStroke(
                1.dp, Color.Black
            )
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "ksh album")
        }
    }
}

@Composable
private fun ButtonsLayout(onClickSearchView: () -> Unit) {

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposeDankeTheme {
        HomeScreen(
            onClickSearchView = {},
            onClickWellnessView = {},
            onClickLazyView = {},
            onClickOneView = {},
            onClickDankeView = {},
            onClickKshAlbumView = {})
    }
}
