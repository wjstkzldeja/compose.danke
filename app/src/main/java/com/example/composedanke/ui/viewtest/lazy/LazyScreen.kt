package com.example.composedanke.ui.viewtest.lazy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedanke.R
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.example.composedanke.ui.viewtest.search.SearchBar

/**
 * https://developer.android.com/codelabs/jetpack-compose-layouts?hl=ko&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fjetpack-compose-for-android-developers-1%3Fhl%3Dko%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-layouts#6
 * */
private var mViewModel = LazyViewModel()

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposeDankeTheme {
        LazyScreen()
    }
}

@Composable
fun LazyScreen(
    viewModel: LazyViewModel = viewModel(),
) {
    mViewModel = viewModel
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "LazyRowView")
        LazyRowView()
        Text(text = "LazyColumnView")
        LazyColumnView()
        Text(text = "LazyGridView")
        LazyGridView()
        Text(text = "RowExample")
        RowExample()
        Text(text = "ColumnExample")
        ColumnExample()
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun LazyRowView() {
    LazyRow() { itemsIndexed(mViewModel.lazyList) { index, item -> LazyRowItem(index, item) } }
}

@Composable
fun LazyRowItem(
    index: Int, item: LazyVo
) {
    Column(
        modifier = Modifier.background(Color.Cyan)
    ) {
        Image(
            painter = painterResource(R.drawable.img_detail_muscle_original),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)

        )
        Text(text = item.label)
    }
}

@Composable
fun LazyColumnView() {
    LazyColumn(modifier = Modifier.height(300.dp)) {
        itemsIndexed(mViewModel.lazyList) { index, item -> LazyColumnItem(index, item) }
    }
}

@Composable
fun LazyColumnItem(
    index: Int, item: LazyVo
) {
    Column(
        modifier = Modifier.background(Color.Gray)
    ) {
        Image(
            painter = painterResource(R.drawable.img_detail_muscle_original),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)

        )
        Text(text = item.label)
    }
}

@Composable
fun LazyGridView() {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .height(350.dp)
            .padding(top = 56.dp)
    ) {
        itemsIndexed(
            mViewModel.lazyList
        ) { index, item ->
            LazyGridItem(index, item)
        }
    }
}

@Composable
fun LazyGridItem(
    index: Int, item: LazyVo
) {
    Column(
        modifier = Modifier.background(Color.Magenta)
    ) {
        Image(
            painter = painterResource(R.drawable.img_detail_muscle_original),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)

        )
        Text(text = item.label)
    }
}

@Composable
fun RowExample() {
    Image(
        painter = painterResource(R.drawable.img_row_example),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ColumnExample() {
    Image(
        painter = painterResource(R.drawable.img_column_example),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}





