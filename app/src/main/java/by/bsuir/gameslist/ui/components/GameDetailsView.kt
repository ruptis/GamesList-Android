package by.bsuir.gameslist.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.bsuir.gameslist.model.Game
import by.bsuir.gameslist.model.Status
import coil.compose.AsyncImage

@Composable
fun GameDetailsView(
    game: Game,
    onStatusChange: (Status) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            AsyncImage(
                model = game.cover,
                contentDescription = "cover",
                modifier = Modifier
                    .background(color = Color.Gray, shape = MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }

        item {
            TitleSectionView(
                game = game,
                onStatusChange = onStatusChange
            )
        }

        item {
            InformationSectionView(game)
        }

        item {
            DescriptionSectionView(game)
        }

        item {
            ScreenshotsSectionView(game)
        }
    }
}

@Composable
private fun TitleSectionView(
    game: Game,
    onStatusChange: (Status) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = game.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth()
        )

        GameActionsView(status = game.status, onStatusChange = onStatusChange)
    }
}

@Composable
private fun InformationSectionView(game: Game) {
    HorizontalDivider(
        modifier = Modifier.padding(top = 16.dp)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Information",
            style = MaterialTheme.typography.titleMedium,
        )

        GameInformationView(game = game)
    }
}

@Composable
private fun GameInformationView(game: Game) {
    Column {
        InfoRowView(
            title = "Developer",
            value = game.developer
        )
        InfoRowView(
            title = "Publisher",
            value = game.publisher
        )
        InfoRowView(
            title = "Release date",
            value = game.releaseDate.toString()
        )
        InfoRowView(
            title = "Platforms",
            value = game.platforms
                .joinToString(", ", transform = { it.name })
        )
        InfoRowView(
            title = "Genres",
            value = game.genres.joinToString(", ")
        )
    }
}

@Composable
private fun DescriptionSectionView(game: Game) {
    HorizontalDivider(
        modifier = Modifier.padding(top = 16.dp)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = game.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ScreenshotsSectionView(game: Game) {
    HorizontalDivider(
        modifier = Modifier.padding(top = 16.dp)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Screenshots",
            style = MaterialTheme.typography.titleMedium,
        )

        GameScreenshotsView(game = game)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameScreenshotsView(game: Game) {
    val carouselState = rememberCarouselState(itemCount = { game.screenshots.size })

    HorizontalMultiBrowseCarousel(
        state = carouselState,
        preferredItemWidth = 384.dp,
        modifier = Modifier.height(216.dp),
        itemSpacing = 4.dp
    ) {
        AsyncImage(
            model = game.screenshots[it],
            contentDescription = "screenshot",
            modifier = Modifier
                .background(color = Color.Gray, shape = MaterialTheme.shapes.medium)
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "GameDetailsView", showBackground = true)
@Composable
private fun PreviewGameDetailsView() {
    GameDetailsView(game = Game.mockGame(), onStatusChange = {})
}