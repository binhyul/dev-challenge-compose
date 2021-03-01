package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class PuppyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model = intent.getSerializableExtra("item") as PuppyItem
        setContent {
            MyTheme {
                PuppyCompose(model)
            }
        }
    }
}
@Composable
fun PuppyCompose(model : PuppyItem? = null) {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier
            .background(colorResource(id = R.color.color_back))
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(modifier = Modifier.padding(0.dp,10.dp,0.dp,10.dp),text = "Selling a Puppy", fontWeight = FontWeight.Bold)
//            if(model ==null) return@Column
            DogItem(model ?:  PuppyItem("Han Jin",listOf(R.drawable.img_1428),"2020/02/28",0,"Hi! It's my cat. plz get my cat"))
        }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPuppyDetailPreview() {
    MyTheme {
        PuppyCompose()
    }
}