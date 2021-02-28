/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.GravityCompat
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

data class PuppyItem(
    val name : String,
    val thumbnail : Int,
    val postDate : String,
    val puppyImage : Int
)


// Start building your app here!
@Composable
fun MyApp() {
    val dogItemOnClick = object : () -> Unit{
        override fun invoke() {
            Log.e("Logging on click","")
        }
    }
    val dogItems  : List<PuppyItem> = listOf(
        PuppyItem("Han Jin",R.drawable.img_1428,"2020/02/28",0),
        PuppyItem("Han Ju",R.drawable.img_0636,"2020/02/28",0),
        PuppyItem("Han Jun",R.drawable.img_0805,"2020/02/28",0),
        PuppyItem("Jin Ju Jun",R.drawable.img_1423,"2020/02/28",0)
    )

    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier
            .background(colorResource(id = R.color.color_back))
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Selling a Puppy")
            LazyColumn(
                content = {
                items(dogItems){ item ->
                    DogItem(model = item,onClick = { dogItemOnClick() })
                }
            })
        }
    }
}

@Composable
fun DogItem(model : PuppyItem,onClick : () -> Unit){
    val padding = 16.dp
    val margin= 16.dp
    val thumbnailSize  = 72.dp
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(padding),elevation = 4.dp) {
        Column(
            Modifier
                .clickable { onClick() }
                .clipToBounds()
                .fillMaxWidth()
                .padding(padding)
                .background(colorResource(id = R.color.material_on_primary_emphasis_medium))
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = model.thumbnail),
                    contentDescription = "",
                    modifier = Modifier
                        .size(thumbnailSize, thumbnailSize)
                        .clip(CircleShape)
                        .clipToBounds(),
                    contentScale = ContentScale.FillBounds)
                Column(verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(margin, 0.dp, 0.dp, 0.dp)) {
                    Text(text = "${model.name}")
                    Text(text = "${model.postDate}")
                }
            }
            Image(
                painter = painterResource(id = model.thumbnail),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .background(colorResource(id = R.color.color_3)),
            contentScale = ContentScale.Fit)
                }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
//}
