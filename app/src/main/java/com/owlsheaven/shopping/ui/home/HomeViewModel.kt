package com.owlsheaven.shopping.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.owlsheaven.shopping.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val repository: Repository
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val products = repository.products().cachedIn(viewModelScope)

    val banners = repository.banners().asLiveData()

}