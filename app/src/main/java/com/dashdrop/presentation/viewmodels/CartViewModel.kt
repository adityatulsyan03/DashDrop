package com.dashdrop.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashdrop.data.model.Cart
import com.dashdrop.data.model.Category
import com.dashdrop.data.repo.GetCartFireRepo
import com.dashdrop.data.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartFireRepo: GetCartFireRepo
) : ViewModel() {

    private val _cartData: MutableStateFlow<UiState<ArrayList<Cart>>> = MutableStateFlow(UiState.Idle)
    val cartData = _cartData.asStateFlow()

    fun getAllCart() {
        _cartData.value = UiState.Loading

        viewModelScope.launch {
            try {
                val result = cartFireRepo.getCartList()
                _cartData.value = result
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error fetching cart items: ${e.message}")
                _cartData.value = UiState.Error("Error fetching cart items")
            }
        }
    }

}