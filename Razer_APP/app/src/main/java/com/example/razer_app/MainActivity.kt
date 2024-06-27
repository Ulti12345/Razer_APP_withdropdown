package com.example.razer_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.razer_app.razor.Razor
import com.example.razer_app.razor.razer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RazorApp()
        }
    }
}

@Composable
fun RazorApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Button(
            onClick = { /* handle store button click */ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text(text = stringResource(id = R.string.store), fontSize = 18.sp, color = Color.White)
        }
        Button(
            onClick = { /* handle PC button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text(text = stringResource(id = R.string.pc), fontSize = 18.sp, color = Color.White)
        }
        Image(
            painter = painterResource(id = R.drawable.razer_logo),
            contentDescription = null
        )
        LazyColumn {
            items(razer) {
                RazerItem(razer = it)
            }
        }
    }
}

@Composable
fun RazerItem(
    razer: Razor,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        RazerIcon(razer.imageResourceId)
        Row{
            RazerInformation(razer.stringResourceId)
            Spacer(modifier = Modifier.weight(1f))
            RazerItemButton(
                expanded = expanded,
                onClick = { expanded = !expanded })
        }
    }
    if (expanded) {
        RazerDesc(
            razer.descResourceId,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        )
    }
}

@Composable
private fun RazerItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = "See more or less information.",
            tint = Green
        )
    }
}

@Composable
fun RazerIcon(
    @DrawableRes razerIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(razerIcon),
        contentDescription = null
    )
}

@Composable
fun RazerInformation(
    @StringRes razerInfo: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(razerInfo),
            color = Green,
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun RazerDesc(
    @StringRes razerDesc: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(razerDesc),
            style = MaterialTheme.typography.bodyLarge,
            color = Green
        )
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "RazerApp")
@Composable
fun RazerAppPreview() {
    RazorApp()
}