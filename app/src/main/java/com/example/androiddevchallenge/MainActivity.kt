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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.io.Serializable

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
    val name: String,
    val thumbnail: List<Int>,
    val postDate: String,
    val puppyImage: Int,
    val content: String
) : Serializable

// Start building your app here!
@Composable
fun MyApp() {
    val vm = MainViewModel()
    val puppyItems: List<PuppyItem> by vm.puppyItems.observeAsState(
        listOf<PuppyItem>(
            PuppyItem("Han Jin", listOf(R.drawable.img_1428), "2020/02/28", 0, "Hi! It's my cat. plz get my cat"),
            PuppyItem("Han Ju", listOf(R.drawable.img_0636), "2020/02/28", 0, "I found she in mountain\nbring she your house."),
            PuppyItem("Han Jun", listOf(R.drawable.img_0805), "2020/02/28", 0, "Hello!\nhis name is Wo-Ju\nHis age is 10\nso cute."),
            PuppyItem("Jin Ju Jun", listOf(R.drawable.img_1423), "2020/02/28", 0, "I lost she in four weeks\nbut i found she in front of my house.")
        )
    )
    val onClickBoardAction: PuppyItem? by vm.onClickBoardAction.observeAsState(null)

    onClickBoardAction?.let {
        LocalContext.current.apply {
            val intent = Intent(this, PuppyDetailActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.color_back))
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp), text = "Selling a Puppy", fontWeight = FontWeight.Bold)
            LazyColumn(
                content = {
                    items(puppyItems) { item ->
                        DogItem(model = item, onClick = { vm.onClickBoard(item) })
                    }
                }
            )
        }
    }
}

@Composable
fun DogItem(model: PuppyItem, onClick: (() -> Unit)? = null) {
    val padding = 16.dp
    val margin = 16.dp
    val thumbnailSize = 72.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        elevation = 4.dp
    ) {
        Column(
            Modifier
                .clickable { if (onClick != null) onClick() }
                .clipToBounds()
                .fillMaxWidth()
                .padding(padding)
                .background(colorResource(id = R.color.material_on_primary_emphasis_medium))
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = model.thumbnail.first()),
                    contentDescription = "",
                    modifier = Modifier
                        .size(thumbnailSize, thumbnailSize)
                        .clip(CircleShape)
                        .clipToBounds(),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(margin, 0.dp, 0.dp, 0.dp)
                ) {
                    Text(text = "${model.name}")
                    Text(text = "${model.postDate}")
                }
            }

            if (onClick == null) {
                LazyRow(
                    content = {
                        items(model.thumbnail) { item ->

                            Box(Modifier.fillMaxWidth(1f).padding(10.dp, 0.dp)) {
                                Image(
                                    painter = painterResource(id = item),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.6f)
                                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                                        .background(colorResource(id = R.color.color_3)),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                )
            } else {
                Image(
                    painter = painterResource(id = model.thumbnail.first()),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                        .background(colorResource(id = R.color.color_3)),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp), text = model.content, fontSize = 24.sp,
                maxLines = if (onClick == null) {
                    Int.MAX_VALUE
                } else {
                    2
                },
                overflow = TextOverflow.Ellipsis
            )
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

// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
// }
