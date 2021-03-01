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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), SellingPuppyBoardListener {

    private val _puppyItems: MutableLiveData<List<PuppyItem>> = MutableLiveData(
        listOf(
            PuppyItem("Han Jin", listOf(R.drawable.img_1428, R.drawable.img_0636, R.drawable.img_0805), "2020/02/28", 0, "Hi! It's my cat. plz get my cat"),
            PuppyItem("Han Ju", listOf(R.drawable.img_0636), "2020/02/28", 0, "I found she in mountain\nbring she your house."),
            PuppyItem("Han Jun", listOf(R.drawable.img_0805, R.drawable.img_1423), "2020/02/28", 0, "Hello!\nhis name is Wo-Ju\nHis age is 10\nso cute."),
            PuppyItem("Jin Ju Jun", listOf(R.drawable.img_1423), "2020/02/28", 0, "I lost she in four weeks\nbut i found she in front of my house.")
        )
    )
    override val puppyItems: LiveData<List<PuppyItem>> = _puppyItems

    private val _onClickBoardAction: MutableLiveData<PuppyItem> = MutableLiveData()
    val onClickBoardAction: LiveData<PuppyItem> = _onClickBoardAction

    override fun onClickBoard(model: PuppyItem) {
        _onClickBoardAction.value = model
    }
}

interface SellingPuppyBoardListener {
    val puppyItems: LiveData<List<PuppyItem>>
    fun onClickBoard(model: PuppyItem)
}
