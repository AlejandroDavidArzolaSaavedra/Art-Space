package com.example.art_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.art_space.ui.theme.ArtSpaceTheme
import com.example.art_space.ui.theme.Artwork

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryApp()
                }
            }
        }
    }
}



@Composable
fun ArtworkDetails(artwork: Artwork) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = Color.LightGray) // Fondo para la imagen
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = artwork.title,
            style = MaterialTheme.typography.bodyLarge, // Tamaño del título
            fontWeight = FontWeight.Bold // Peso de la fuente
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "by ${artwork.artist}", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Year: ${artwork.year}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun ArtGalleryApp() {
    var currentArtworkIndex by remember { mutableStateOf(0) }
    val artworks = listOf(
        Artwork(R.drawable.soles, "Artwork 1", "Artist 1", "2022"),
        Artwork(R.drawable.gioconda, "Artwork 2", "Artist 2", "2023"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0x9842FCAB))// Cambiar el color de fondo
            .padding(16.dp)
    ) {
        Text(
            text = "Art-Space",
            fontWeight = FontWeight.Bold, // Aplicar negrita al texto
            style = MaterialTheme.typography.titleLarge, // Tamaño y estilo del título
            textAlign = TextAlign.Center, // Alinear el texto al centro
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Previous",
                tint = Color.Black,
                modifier = Modifier.clickable {
                    currentArtworkIndex = (currentArtworkIndex - 1 + artworks.size) % artworks.size
                }
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next",
                tint = Color.Black,
                modifier = Modifier.clickable {
                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ArtworkDetails(artwork = artworks[currentArtworkIndex])
    }
}


