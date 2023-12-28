package com.example.composedanke.ui.viewtest.search

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedanke.R
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.example.entity.ProjectVo
import com.example.entity.ProjectVoItem
import timber.log.Timber.Forest.d


/** 이미지 맞춤 설정
 * https://developer.android.com/jetpack/compose/graphics/images/customize?hl=ko*/
private var mViewModel = SearchViewModel()

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposeDankeTheme {
        SearchScreen(createTestProjectList())
    }
}

/** 테스트용 데이터 생성*/
private fun createTestProjectList(): ProjectVo {
    val projectList: ProjectVo = ProjectVo()
    val test = arrayListOf<ProjectVoItem>(
        ProjectVoItem(
            imgs = arrayListOf("testImage", "testImage", "testImage"),
            project = "title",
            tags = arrayListOf("1", "2", "3")
        )
    )
    projectList.addAll(test)
    return projectList
}

@Composable
fun SearchScreen(
    list: ProjectVo,
    viewModel: SearchViewModel = viewModel(),
) {
    mViewModel = viewModel
    mViewModel.initProjectList(list)
    val bgImg =
        arrayListOf<Int>(R.drawable.album_moon, R.drawable.album_star, R.drawable.neon_img).random()
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.color_070318))
            .paint(
                painterResource(id = bgImg),
                contentScale = ContentScale.Crop,
                alpha = 0.5f
            )
    ) {
        SearchBar()
        ProjectListColumn()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = mViewModel.viewModelInputText.value,
        onValueChange = { textValue ->
            mViewModel.setText(textValue)
            d("searchTest textValue 1 : ${textValue}")
        },

//        keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            mViewModel.changeList()
        }, onSearch = {
            focusManager.clearFocus()
            mViewModel.changeList()
        }),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Color.White,
                contentDescription = null,
                modifier = modifier.clickable {
                    focusManager.clearFocus()
                    mViewModel.changeList()
                })
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color.Green,
            containerColor = colorResource(id = R.color.color_070318),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
    )
}

@Composable
fun ProjectListColumn() {
    LazyColumn(
        modifier = Modifier.wrapContentSize()
    ) {
        itemsIndexed(
            mViewModel.projectList.value
        ) { index, item ->
            ProjectItem(index, item)
        }
    }
}

@Composable
fun ProjectItem(
    index: Int,
    item: ProjectVoItem
) {
    val imageBitmap = ImageBitmap.imageResource(getResId(item.imgs[0]))
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

    val rainbowColorsBrush2 = remember {
        Brush.linearGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    //    val drawable = ResourcesCompat.getDrawable(re, R.drawable.girl, null)
//    val bitmap = (painterResource(id = R.drawable.olivestonelab_portfolio_211224_0002) as BitmapDrawable?)!!.bitmap
//    val drawable1: Drawable = BitmapDrawable(context.resources, bitmap)
//    val drawable = painterResource(id = R.drawable.olivestonelab_portfolio_211224_0002)
//    // Drawable 이미지를 비트맵으로 변환
//    val bitmap: Bitmap = drawable as Bitmap


//    val imageBitmap = ImageBitmap.imageResource(R.drawable.olivestonelab_portfolio_211224_0002)
    Column(
//        modifier = Modifier.background(Color.Cyan)
    ) {
        if (index != 0) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(2.dp)
//                    .background(Color.Gray)
                    .border(
                        BorderStroke(borderWidth, rainbowColorsBrush2),
                    )
            )
        }
        Text(
            modifier = Modifier.padding(top = 15.dp, bottom = 10.dp, start = 10.dp),
            text = "프로젝트 명 : ${item.project}",
            color = Color.White,
            fontSize = 13.sp
        )
        Image(
            bitmap = mViewModel.calculateInSampleSize(imageBitmap),
//            painter = painterResource(getResId(item.imgs[0])),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    RoundedCornerShape(16.dp)
                )
//                .height(200.dp)
//                .clip(RoundedCornerShape(16.dp))
//                .clip(CircleShape)

        )
        Spacer(modifier = Modifier.height(10.dp))
        ImageListRowView(item)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun ImageListRowView(item: ProjectVoItem) {
    LazyRow(
//        modifier = Modifier.background(Color.Yellow).padding(0.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(item.imgs) { index, item ->
            if (index != 0) {
                ImageListRowItem(
                    index,
                    item
                )
            }
        }
    }
}

@Composable
fun ImageListRowItem(
    index: Int, item: String
) {
    val imageBitmap = ImageBitmap.imageResource(getResId(item))
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
//        modifier = Modifier.background(Color.Magenta)
    ) {
        Image(
            bitmap = mViewModel.calculateInSampleSizeImageList(imageBitmap),
//            painter = painterResource(getResId(item)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
//            modifier = Modifier.aspectRatio(9f / 16f)
            modifier = Modifier
                /* .width(180.dp)
                 .height(92.dp)*/
                .width(170.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    RoundedCornerShape(16.dp)
                )

        )
    }
}

/** 파일 이름으로 이미지 리소스 아이디 찾아서 사용하는 함수*/
@Composable
fun getResId(imageName: String?): Int {
    val mContext = LocalContext.current
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

fun getImageName(imageName: String): String {
    /** 이미지 이름 가져오기
     * assets/imgs/image.jpeg
     * 형식 텍스트 image로 변환*/
    val split = imageName.split("/")
    val findName = split[2]
    val replaceName = findName.replace(".jpeg", "")
    return replaceName
}