package com.owlsheaven.shopping.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.owlsheaven.shopping.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject internal constructor(
    private val repository: Repository
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val products = repository.favoriteProducts().cachedIn(viewModelScope)
}