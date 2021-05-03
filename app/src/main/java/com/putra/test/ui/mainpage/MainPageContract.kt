package com.putra.test.ui.mainpage

import com.putra.test.model.ResponseGetData

interface MainPageContract {

    fun messageGetData(msg:String)
    fun getData(data:List<ResponseGetData?>?)

}