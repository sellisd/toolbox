package com.example.toolbox

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toolbox.ui.theme.ToolboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolboxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Actions()
                }
            }
        }
    }
}

@Composable
fun CardWithTitle(title: String, drawable: Int, sound: MediaPlayer){
    Card{
        Text(title,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center)
        Image(painterResource(id = drawable), null,

        modifier = Modifier.fillMaxWidth().clickable(onClick={
            sound.seekTo(0)
            sound.start()
        }),
        contentScale = ContentScale.FillWidth)
    }
}

@Composable
fun Actions() {
    val context = LocalContext.current
    val playBell = MediaPlayer.create(context, R.raw.bell)
    val playDrum = MediaPlayer.create(context, R.raw.drum)
    val playMoo = MediaPlayer.create(context, R.raw.moo)
    val playClapping = MediaPlayer.create(context, R.raw.clapping)
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            CardWithTitle(title = "Pause Smile", R.drawable.drum, playDrum)
        }
        item {
            CardWithTitle(title = "Close Ticket", R.drawable.bell, playBell)
        }
        item {
            CardWithTitle(title = "Close Ticket Response", R.drawable.clap, playClapping)
        }
        item {
            CardWithTitle(title = "Out of topic", R.drawable.moo, playMoo)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToolboxTheme {
        Actions()
    }
}