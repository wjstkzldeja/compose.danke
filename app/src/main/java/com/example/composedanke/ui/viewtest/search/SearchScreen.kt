package com.example.composedanke.ui.viewtest.search

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
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
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.GlideLazyListPreloader
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.composedanke.R
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.example.entity.ProjectVo
import com.example.entity.ProjectVoItem
import timber.log.Timber.Forest.d


/**
 * 1. 이미지 맞춤 설정
 * https://developer.android.com/jetpack/compose/graphics/images/customize?hl=ko
 * 2. glide
 * https://bumptech.github.io/glide/int/compose.html
 * */
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
        arrayListOf<Int>(
            R.drawable.album_moon,
            R.drawable.album_star, /*R.drawable.neon_img*/
        ).random()
    Image(
        modifier = Modifier
            .fillMaxSize()
            .blur(radiusX = 10.dp, radiusY = 10.dp)
            .background(color = colorResource(id = R.color.color_070318)),
        painter = painterResource(id = bgImg),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        alpha = 0.8f,
    )
    Column(
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProjectListColumn() {
    val state = rememberLazyListState()
    val mContext = LocalContext.current
    LazyColumn(
        modifier = Modifier.wrapContentSize(),state=state
    ) {
        itemsIndexed(
            mViewModel.projectList.value
        ) { index, item ->
            ProjectItem(index, item)
        }
    }
    GlideLazyListPreloader(
        state = state,
        data = mViewModel.projectList.value,
        size = Size(100f,100f),
        numberOfItemsToPreload = 15,
        fixedVisibleItemCount = 2,
    ) { item, requestBuilder ->
        requestBuilder.load(getResId(mContext,item.imgs[0]))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProjectItem(
    index: Int,
    item: ProjectVoItem
) {
    val mContext = LocalContext.current
    val imageBitmap = ImageBitmap.imageResource(getResId(mContext, item.imgs[0]))
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

        GlideImage(
            model = getResId(mContext, item.imgs[0]),//mViewModel.calculateInSampleSize(ImageBitmap.imageResource(getResId(item.imgs[0]))),
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
            it.encodeQuality(20)
        }
        Spacer(modifier = Modifier.height(10.dp))
        ImageListRowView(item)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

/** glide로 비트맵 만들어서 로드 하는 예제*/
@Composable
private fun GlideBitmapLoadExample() {
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(R.drawable.ksh_1)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image(
            bitmap = fetchedBitmap,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp, 50.dp)
                .clip(CircleShape)
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.no_image),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(50.dp, 50.dp)
            .clip(CircleShape)
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageListRowView(item: ProjectVoItem) {
    val state = rememberLazyListState()
    val mContext = LocalContext.current
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
    GlideLazyListPreloader(
        state = state,
        data = item.imgs,
        size = Size(100f,100f),
        numberOfItemsToPreload = 15,
        fixedVisibleItemCount = 2,
    ) { item, requestBuilder ->
        requestBuilder.load(getResId(mContext,item))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageListRowItem(
    index: Int, item: String
) {
    val mContext = LocalContext.current
    val imageBitmap = ImageBitmap.imageResource(getResId(mContext, item))
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
        GlideImage(
            model = getResId(mContext, item),//mViewModel.calculateInSampleSizeImageList(ImageBitmap.imageResource(getResId(item))),
//            painter = painterResource(getResId(item)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
//            modifier = Modifier.aspectRatio(9f / 16f)
            modifier = Modifier
                .width(180.dp)
                .height(92.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    RoundedCornerShape(16.dp)
                )

        ) {
            it.encodeQuality(20)
        }
    }
}

/** 파일 이름으로 이미지 리소스 아이디 찾아서 사용하는 함수*/
fun getResId(mContext: Context, imageName: String?): Int {
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