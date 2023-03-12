package com.rigelramadhan.beritacompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rigelramadhan.beritacompose.R
import com.rigelramadhan.beritacompose.ui.theme.BeritaComposeTheme
import com.rigelramadhan.beritacompose.ui.theme.CardBackgroundDark
import com.rigelramadhan.beritacompose.ui.theme.CardBackgroundLight

@Composable
fun NewsItem(
    title: String,
    newsSite: String,
    summary: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = if (isSystemInDarkTheme()) CardBackgroundDark else CardBackgroundLight,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = modifier) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl,
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
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = newsSite,
                    style = MaterialTheme.typography.caption,
                )

                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
                )

                Text(
                    text = summary,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewsItemPreview() {
    BeritaComposeTheme {
        NewsItem(
            title = stringResource(R.string.placeholder_news_title),
            newsSite = stringResource(R.string.dicoding),
            summary = stringResource(R.string.placeholder_news_description),
            imageUrl = stringResource(R.string.placeholder_news_url),
        )
    }
}