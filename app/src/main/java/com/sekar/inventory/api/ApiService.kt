package com.sekar.ritxbertaniminiapp.api

import com.sekar.inventory.model.Item
import com.sekar.inventory.model.ItemCreate
import com.sekar.inventory.model.ItemResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("api/item")
    suspend fun getItem(): Response<Item>

    @POST("api/item")
    fun createItem(@Body itemCreate: ItemCreate): Call<ItemResponse>

    @PUT("api/item/{id}")
    fun updateItem(
        @Body itemCreate: ItemCreate,
        @Path("id") id: String
    ): Call<ItemResponse>

    @DELETE("api/item/{id}")
    fun deleteItem(
        @Path("id") id: String
    ): Call<Unit>
}
