package com.sekar.inventory.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sekar.inventory.model.ItemCreate
import com.sekar.inventory.model.ItemResponse
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {

    private val repository = ItemRespository()

    val showSuccess = repository.showSuccess
    val showFailure = repository.showFailure

    fun getItem() {
        viewModelScope.launch { repository.getItem() }
    }

    fun createItem(itemCreate: ItemCreate) {
        viewModelScope.launch { repository.createItem(itemCreate) }
    }

    fun updateItem(itemCreate: ItemCreate, id: String) {
        viewModelScope.launch { repository.updateItem(itemCreate, id) }
    }

    fun deleteItem(id: String) {
        viewModelScope.launch { repository.deleteItem(id) }
    }
}