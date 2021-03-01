package com.sekar.inventory.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sekar.inventory.model.ItemCreate
import com.sekar.inventory.model.ItemResponse
import com.sekar.inventory.model.ItemsItem
import com.sekar.ritxbertaniminiapp.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ItemRespository {

    private val apiClient = ApiClient().api()

    val showSuccess = MutableLiveData<List<ItemsItem>>()
    val showFailure = MutableLiveData<Boolean>()

    suspend fun getItem() {
        try {
            val response = apiClient.getItem()
            Log.d(TAG, "$response")

            if (response.isSuccessful) {
                Log.d(TAG, "SUCCESS")
                Log.d(TAG, "${response.body()}")

                val body = response.body()

                body?.let {
                    showSuccess.postValue(it.items)
                }
            } else {
                Log.d(TAG, "FAILURE")
                Log.d(TAG, "${response.body()}")

                showFailure.postValue(true)
            }
        } catch (e: UnknownHostException) {
            showFailure.postValue(true)
        } catch (e: SocketTimeoutException) {
            showFailure.postValue(true)
        } catch (e: Exception) {
            showFailure.postValue(true)
        }
    }

    fun createItem(itemCreate: ItemCreate) {

        apiClient.createItem(itemCreate).enqueue(object : Callback<ItemResponse> {
            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d("Error insert", t.toString())
            }

            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {

                Log.d("Resposnse insert :", response?.message())
                if (response != null) {
                    if (response.isSuccessful && response.body()?.status.equals("true")) {
                        var result = response.body()?.status
                        var message = response.body()?.message

                        Log.d("Result", "response >>> $result$message")
                    }
                }
            }
        })
    }

    fun updateItem(itemCreate: ItemCreate, id: String) {

        apiClient.updateItem(itemCreate, id).enqueue(object : Callback<ItemResponse> {
            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d("Error update", t.toString())
            }

            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {

                Log.d("Resposnse update :", response?.message())
                if (response != null) {
                    if (response.isSuccessful && response.body()?.status.equals("true")) {
                        var result = response.body()?.status
                        var message = response.body()?.message

                        Log.d("Result", "response >>> $result$message")
                    }
                }
            }
        })
    }

    fun deleteItem(id: String) {
        apiClient.deleteItem(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("Error delete", t.toString())
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Log.d("Resposnse delete :", response?.message())
                if (response != null) {
                    if (response.isSuccessful) {
                        val res = response.body()

                        Log.d("Delete", "response >>> $res")
                    }
                }
            }
        })
    }

    companion object {
        val TAG = ItemRespository::class.java.simpleName
    }
}