package com.sekar.inventory.model

import com.google.gson.annotations.SerializedName

data class ItemCreate(

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
