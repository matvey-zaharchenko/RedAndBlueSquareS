package com.example.redandbluesquares

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.redandbluesquares.ui.theme.RedAndBlueSquaresTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RedAndBlueSquaresTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.background)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.status_bar_purple))

                    ) {
                        GridScreen(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun GridScreen(viewModel: MainViewModel) {
    val items = viewModel.items
    val configuration = LocalConfiguration.current

    val columns = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        integerResource(id = R.integer.portrait_columns)
    else
        integerResource(id = R.integer.landscape_columns)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.default_padding))
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(items) { index ->
                    val color = if (index % 2 == 0)
                        colorResource(id = R.color.even_color)
                    else
                        colorResource(id = R.color.odd_color)

                    Box(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.default_padding))
                            .aspectRatio(1f)
                            .background(color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = index.toString(), color = colorResource(id = R.color.text_color))
                    }
                }
            }

            FloatingActionButton(
                onClick = { viewModel.addItem() },
                containerColor = colorResource(id = R.color.button),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = dimensionResource(id = R.dimen.button_padding), bottom = dimensionResource(id = R.dimen.button_padding))
            ) {
                Text(text = stringResource(id = R.string.button_text), color = colorResource(id = R.color.text_color), fontSize = dimensionResource(id = R.dimen.text_size).value.sp)
            }
        }
    }
}