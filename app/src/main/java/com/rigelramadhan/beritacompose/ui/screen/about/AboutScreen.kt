package com.rigelramadhan.beritacompose.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rigelramadhan.beritacompose.R
import com.rigelramadhan.beritacompose.ui.theme.BeritaComposeTheme

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.foto_rigel),
            contentDescription = "profile image",
            modifier = Modifier
                .padding(vertical = 32.dp)
                .clip(CircleShape)
                .size(200.dp)
        )
        Text(
            text = "Rigel Vibi Ramadhan",
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Medium
            ),
        )
        Text(
            text = "rigelvibi51@gmail.com",
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    BeritaComposeTheme {
        AboutScreen()
    }
}