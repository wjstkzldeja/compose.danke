package com.example.composedanke.ui.viewtest.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedanke.ui.theme.ComposeDankeTheme
import com.example.composedanke.ui.viewtest.lazy.ColumnExample
import com.example.composedanke.ui.viewtest.lazy.LazyColumnView
import com.example.composedanke.ui.viewtest.lazy.LazyGridView
import com.example.composedanke.ui.viewtest.lazy.LazyRowView
import com.example.composedanke.ui.viewtest.lazy.RowExample
import timber.log.Timber.Forest.d

private var mViewModel = SearchViewModel()

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onNextView: () -> Unit,
) {
    mViewModel = viewModel
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        SearchBar()
        ImageListColumn()
    }

//    ImageListGrid()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = mViewModel.viewModelInputText.value,
        onValueChange = { textValue ->
            mViewModel.setText(textValue)
            d("logTest textValue 1 : ${textValue}")
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = modifier.clickable {
                    focusManager.clearFocus()
                    mViewModel.changeList()
                })
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Blue,
            focusedIndicatorColor = Color.Green
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
    )
}

@Composable
fun ImageListColumn() {
    LazyColumn(
        modifier = Modifier
            .height(300.dp)

    ) {
        itemsIndexed(
            mViewModel.searchList.value
        ) { index, item ->
            ImageItem(index, item)
        }
    }
}

@Composable
fun ImageItem(
    index: Int,
    item: String
) {
    Column(
        modifier = Modifier.background(Color.Cyan)
    ) {
        Image(
            painter = painterResource(androidx.core.R.drawable.ic_call_answer),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)

        )
        Text(text = item)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposeDankeTheme {
        val viewModel: SearchViewModel = viewModel(
            factory = SearchViewModel.Factory
        )
        SearchScreen(viewModel = viewModel) {}
    }
}
