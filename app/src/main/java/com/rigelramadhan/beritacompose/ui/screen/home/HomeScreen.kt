package com.rigelramadhan.beritacompose.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rigelramadhan.beritacompose.R
import com.rigelramadhan.beritacompose.core.data.Resource
import com.rigelramadhan.beritacompose.core.domain.model.News
import com.rigelramadhan.beritacompose.ui.components.NewsItem
import com.rigelramadhan.beritacompose.ui.theme.BeritaComposeTheme
import kotlin.random.Random

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val newsList: Resource<List<News>> by viewModel.uiState.collectAsState(initial = Resource.Loading())

    when (newsList) {
        is Resource.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        is Resource.Success -> {
            val data = newsList.data
            if (data.isNullOrEmpty()) {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.no_news_found),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                HomeContent(
                    news = data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
        }

        is Resource.Error -> {
            Box(modifier = modifier.fillMaxSize()) {
                Text(
                    text = stringResource(R.string.something_went_wrong),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    news: List<News>, modifier: Modifier = Modifier, navigateToDetail: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(news) { data ->
            NewsItem(title = data.title,
                newsSite = data.newsSite,
                summary = data.summary,
                imageUrl = data.imageUrl,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    BeritaComposeTheme {
        val dummyNews = News(
            summary = stringResource(R.string.placeholder_news_description),
            featured = false,
            publishedAt = stringResource(R.string.placeholder_news_date),
            imageUrl = stringResource(R.string.placeholder_news_url),
            newsSite = stringResource(R.string.dicoding),
            id = Random(10000).nextInt(),
            title = stringResource(R.string.placeholder_news_title),
            url = "",
            updatedAt = "",
            isFavorite = false,
        )
        HomeContent(
            news = listOf(
                dummyNews,
                dummyNews,
                dummyNews,
                dummyNews,
                dummyNews,
                dummyNews,
                dummyNews,
                dummyNews,
                dummyNews,
                dummyNews,
            ),
            navigateToDetail = {}
        )
    }
}