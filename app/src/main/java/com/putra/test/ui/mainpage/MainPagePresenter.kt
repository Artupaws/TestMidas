package com.putra.test.ui.mainpage

import com.putra.test.model.ResponseGetData
import com.putra.test.network.Http
import retrofit2.Call
import retrofit2.Response

class MainPagePresenter(val view:MainPageContract){

//    fun getDataApi(){
//        val callData = Http().getConnectionPhotos().getDataApi()
//        callData.enqueue(object : retrofit2.Callback<ResponseGetData>{
//            override fun onResponse(call: Call<ResponseGetData>, response: Response<ResponseGetData>) {
//                if (response.isSuccessful){
//                    val data = listOf(ResponseGetData(response.body()?.albumId, response.body()?.id, response.body()?.thumbnailUrl,
//                    response.body()?.url, response.body()?.title))
//                    view.getData(data)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
//                view.messageGetData(t.localizedMessage.toString())
//            }
//
//        })
//    }

    fun getDataApi(){
        val callData = Http().getConnectionPhotos().getDataApi()
        callData.enqueue(object : retrofit2.Callback<List<ResponseGetData>>{
            override fun onResponse(call: Call<List<ResponseGetData>>, response: Response<List<ResponseGetData>>) {
                if (response.isSuccessful){
                    val dataArrayList:List<ResponseGetData?>?
                    dataArrayList = response.body()
                    view.getData(dataArrayList)
                }
            }

            override fun onFailure(call: Call<List<ResponseGetData>>, t: Throwable) {
                view.messageGetData(t.localizedMessage.toString())
            }

        })
    }

}