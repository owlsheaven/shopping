package com.owlsheaven.shopping.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owlsheaven.shopping.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject internal constructor(
    private val repository: Repository
) : ViewModel() {

    fun updateFavorite(productId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateFavorite(productId, isFavorite)
        }
    }
}