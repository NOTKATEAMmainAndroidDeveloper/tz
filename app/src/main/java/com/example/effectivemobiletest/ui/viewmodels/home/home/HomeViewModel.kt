package com.example.effectivemobiletest.ui.viewmodels.home.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.ui.models.json.ItemModelJson
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

data class CategoryModel(
    var title: String,
    var iconResource: Int
)

class HomeViewModel: ViewModel() {
    var ui = HomeView(this)

    private val API_LATEST_URL = "https://run.mocky.io/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7"
    private val API_SALES_URL = "https://run.mocky.io/v3/a9ceeb6e-416d-4352-bde6-2203416576ac"

    var isGet = mutableStateOf(false)

    var latestItemList = SnapshotStateList<ItemModelJson>()
    var salesItemList = SnapshotStateList<ItemModelJson>()

    var allCategory = arrayOf(
        CategoryModel("Phones", R.drawable.category_phone),
        CategoryModel("Headphones", R.drawable.category_head),
        CategoryModel("Games", R.drawable.category_game),
        CategoryModel("Cars", R.drawable.category_cars),
        CategoryModel("Furniture", R.drawable.category_sleep),
        CategoryModel("Kids", R.drawable.category_kids)
    )

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun init(context: Context){
        this@HomeViewModel.context = context

        getObject()


        //isGet.value = true
    }

    private fun getObject() = runBlocking{
        val request: RequestQueue = Volley.newRequestQueue(context)
        val request2: RequestQueue = Volley.newRequestQueue(context)

        val dataSalesStr = StringRequest(
            Request.Method.GET, API_SALES_URL,
            { response ->
                val strResp = response.toString()
                val jsonObj = JSONObject(strResp)

                var itemsJson = jsonObj.getJSONArray("flash_sale")

                for(index in 0 until itemsJson.length()){
                    var jsonObject: JSONObject = itemsJson.getJSONObject(index)

                    var newModel = ItemModelJson(
                        category = jsonObject.getString("category"),
                        name = jsonObject.getString("name"),
                        price = jsonObject.getDouble("price"),
                        discount = jsonObject.getInt("discount"),
                        imageUrl = jsonObject.getString("image_url"),
                    )
                    salesItemList.add(newModel)

                    onGet()
                }

            }, {

            })

        val dataLatestStr = StringRequest(
            Request.Method.GET, API_LATEST_URL,
            { response ->
                val strResp = response.toString()
                val jsonObj = JSONObject(strResp)


                var itemsJson = jsonObj.getJSONArray("latest")

                for(index in 0 until itemsJson.length()){
                    var jsonObject: JSONObject = itemsJson.getJSONObject(index)

                    var newModel = ItemModelJson(
                        category = jsonObject.getString("category"),
                        name = jsonObject.getString("name"),
                        price = jsonObject.getDouble("price"),
                        imageUrl = jsonObject.getString("image_url"),
                        discount = 0
                    )

                    latestItemList.add(newModel)
                    onGet()
                }
            }, {

            })

        var firstRequestAsync = async {
            request.add(dataLatestStr)
            request.start()
        }

        var secondRequestAsync = async {
            request2.add(dataSalesStr)
            request2.start()
        }

        firstRequestAsync.start()
        secondRequestAsync.start()

    }

    private fun onGet() {
        isGet.value = latestItemList.isNotEmpty() && salesItemList.isNotEmpty()
    }
}