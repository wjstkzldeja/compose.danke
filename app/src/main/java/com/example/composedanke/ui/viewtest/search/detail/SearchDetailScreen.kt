package com.example.composedanke.ui.viewtest.search.detail

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.composedanke.R
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.example.entity.ProjectVoItem
import timber.log.Timber.Forest.d

private var mViewModel = SearchDetailViewModel()

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposeDankeTheme {
        SearchDetailScreen(
            projectVoItem = createTestProjectList(),
            projectVoItem2 = createTestProjectList()
        )
    }
}

/** 테스트용 데이터 생성*/
private fun createTestProjectList(): ProjectVoItem {
    return ProjectVoItem(
        imgs = arrayListOf("testImage", "testImage", "testImage"),
        project = "title",
        tags = arrayListOf("1", "2", "3")
    )
}

@Composable
fun SearchDetailScreen(
    viewModel: SearchDetailViewModel = viewModel(),
    projectVoItem: ProjectVoItem,
    projectVoItem2: ProjectVoItem
) {
    mViewModel = viewModel
    d("voTest projectVoItem : ${projectVoItem}")
    d("voTest projectVoItem2 : ${projectVoItem2}")
    val bgImg =
        arrayListOf<Int>(
            R.drawable.album_moon,
            R.drawable.album_star, /*R.drawable.neon_img*/
        ).random()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(radiusX = 10.dp, radiusY = 10.dp)
                .background(color = colorResource(id = R.color.color_070318)),
            painter = painterResource(id = R.drawable.album_star/*bgImg*/),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alpha = 0.8f,
        )
        ProjectListColumn(projectVoItem)
    }
}

@Composable
private fun ProjectListColumn(projectVoItem: ProjectVoItem) {
    val state = rememberLazyListState()
    val mContext = LocalContext.current
    ProjectItem(projectVoItem)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProjectItem(
    item: ProjectVoItem,
) {
    val mContext = LocalContext.current
    val borderWidth = 4.dp
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            ).shuffled()
        )
    }
    Column(
    ) {
        Text(
            modifier = Modifier.padding(top = 30.dp, start = 10.dp),
            text = "프로젝트 명 : ${item.project}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        GlideImage(
            model = getResId(mContext, item.imgs[0]),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    RoundedCornerShape(16.dp)
                )
        ) {
            it.diskCacheStrategy(DiskCacheStrategy.ALL)
            it.encodeQuality(10)
        }
        Spacer(modifier = Modifier.height(10.dp))
        ImageListRowView(item)
        Spacer(modifier = Modifier.height(10.dp))
        FlowListRowView(item)
        Spacer(modifier = Modifier.height(10.dp))

    }
}

@Composable
private fun ImageListRowView(item: ProjectVoItem) {
    val state = rememberLazyListState()
    val mContext = LocalContext.current
    LazyRow(
//        modifier = Modifier.background(Color.Yellow).padding(0.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        itemsIndexed(item.imgs) { index, item ->
                ImageListRowItem(
                    index,
                    item
                )
        }
    }
}

@SuppressLint("CheckResult")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageListRowItem(
    index: Int, item: String
) {
    val mContext = LocalContext.current
    val borderWidth = 2.dp
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            ).shuffled()
        )
    }

    Column(
    ) {
        GlideImage(
            model = getResId(mContext, item),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(180.dp)
                .height(92.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    RoundedCornerShape(16.dp)
                )

        ) {
            it.diskCacheStrategy(DiskCacheStrategy.ALL)
            it.encodeQuality(10)
        }
    }
}

/** flow view
 * https://developer.android.com/jetpack/compose/layouts/flow?hl=ko */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowListRowView(item: ProjectVoItem) {
    /*FlowColumn {

    }*/
    FlowRow(
        modifier = Modifier.padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        item.tags.forEach {
            FlowListRowItem(it)
        }
    }
}

@Composable
private fun FlowListRowItem(
    item: String
) {
    val borderWidth = 2.dp
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            ).shuffled()
        )
    }
    Box(
        modifier = Modifier
            .border(
                BorderStroke(borderWidth, rainbowColorsBrush),
                RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = "#${item}",
            color = Color.White,
            fontSize = 13.sp,
        )
    }

}

private fun getResId(mContext: Context, imageName: String?): Int {
    d("searchTest imageName : ${imageName}")
    if (imageName.isNullOrBlank()) return R.drawable.no_image
    if (imageName == "testImage") return R.drawable.no_image

    val resId = mContext.resources.getIdentifier(
        getImageName(imageName),
        "drawable",
        mContext.packageName
    )
    d("searchTest resIdTest : ${resId}")
    return if (resId == 0) return R.drawable.no_image else resId
}

private fun getImageName(imageName: String): String {
    /** 이미지 이름 가져오기
     * assets/imgs/image.jpeg
     * 형식 텍스트 image로 변환*/
    val split = imageName.split("/")
    val findName = split[2]
    val replaceName = findName.replace(".jpeg", "")
    return replaceName
}