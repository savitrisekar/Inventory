package com.sekar.inventory.model

import com.google.gson.annotations.SerializedName

data class Item(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ItemsItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("supplier")
	val supplier: String? = null,

	@field:SerializedName("qty")
	val qty: String? = null,

    @field:SerializedName("date")
	val date: String? = null
)
