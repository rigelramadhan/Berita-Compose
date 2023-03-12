package com.rigelramadhan.beritacompose.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.rigelramadhan.beritacompose.R
import com.rigelramadhan.beritacompose.core.data.Resource
import com.rigelramadhan.beritacompose.core.domain.model.News
import com.rigelramadhan.beritacompose.ui.theme.BeritaComposeTheme
import kotlin.random.Random

@Composable
fun DetailScreen(
    newsId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    openNews: (String) -> Unit
) {
    LaunchedEffect(newsId) {
        viewModel.init(newsId)
    }
    val news by viewModel.uiState.collectAsState(initial = Resource.Loading())

    when (news) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        is Resource.Success -> {
            val data = news.data
            if (data != null) {
                DetailContent(
                    news = data,
                    modifier = Modifier,
                    navigateBack = navigateBack,
                    openNews = openNews
                )
            }
        }

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
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
fun DetailContent(
    news: News,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    openNews: (String) -> Unit,
) {
    Box(modifier = modifier) {
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = news.imageUrl,
                    placeholder = painterResource(R.drawable.image_placeholder)
                ),
                contentDescription = stringResource(
                    R.string.description_news_image
                ),
                modifier = Modifier.aspectRatio(ratio = 1 / 1f),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.padding(all = 16.dp)) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = news.newsSite,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = news.publishedAt,
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Medium
                    ),
                )

                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                )

                Text(
                    text = news.summary,
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = modifier.height(16.dp))
                Button(
                    onClick = { openNews(news.url) },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ExitToApp,
                        contentDescription = "Open in browser"
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(text = "Open in Browser")
                }
            }
        }
        IconButton(onClick = navigateBack) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back button",
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = CircleShape
                    )
                    .padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
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
        DetailContent(
            news = dummyNews,
            navigateBack = {},
            openNews = {}
        )
    }
}