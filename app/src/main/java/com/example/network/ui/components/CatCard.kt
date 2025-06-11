package com.example.network.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.catapi.R
import com.example.network.data.model.Breed
import com.example.network.data.model.CatImageModel

@Composable
fun CatCard(
    cat: CatImageModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(cat.url)
                    .crossfade(true)
                    .build(),
                contentDescription = "Cat image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(android.R.drawable.ic_menu_report_image)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "ID: ${cat.id}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Size: ${cat.width} x ${cat.height}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            cat.breeds?.firstOrNull()?.let { breed ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = breed.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                breed.description?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3
                    )
                }
                breed.temperament?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Temperament: $it",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatCardPreview() = CatCard(
    cat = CatImageModel(
        id = "123",
        url = "https://placekitten.com/400/300",
        width = 400,
        height = 300,
        breeds = listOf(
            Breed(
                id = "abys",
                name = "Abyssinian",
                description = "Abyssinians are highly active and love to explore.",
                temperament = "Active, Energetic, Independent"
            )
        )
    )
)
