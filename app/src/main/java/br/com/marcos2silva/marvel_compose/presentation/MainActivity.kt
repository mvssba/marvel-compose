package br.com.marcos2silva.marvel_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import br.com.marcos2silva.marvel_compose.model.Character
import br.com.marcos2silva.marvel_compose.ui.theme.MarvelComposeTheme
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: CharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComposeTheme {
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: CharacterViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Marvel Compose") })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                CharactersList(viewModel.getCharacters(""))
            }
        }
    )
}

@Composable
fun CharactersList(characters: Flow<PagingData<Character>>) {
    val itemsState = characters.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(itemsState) { character ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {

                ConstraintLayout {
                    val (image, text) = createRefs()

                    Image(
                        painter = rememberImagePainter(data = character?.thumbnail),
                        contentDescription = null,
                        modifier = Modifier
                            .constrainAs(image) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                            .size(50.dp)
                    )

                    Text(
                        modifier = Modifier.constrainAs(text) {
                            start.linkTo(image.end, margin = 8.dp)
                            top.linkTo(image.top)
                            bottom.linkTo(image.bottom)
                        },
                        text = character?.name ?: ""
                    )
                }

//                Row {
//                    Image(
//                        painter = rememberImagePainter(data = character?.thumbnail),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(50.dp)
//                            .fillMaxHeight()
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        modifier = Modifier.align(Alignment.CenterVertically),
//                        text = character?.name ?: ""
//                    )
//                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun Loading(b: Boolean) {
    Dialog(
        onDismissRequest = { !b }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Loading")
        }
    }
}