package com.albara.bazarstoreroom.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albara.bazarstoreroom.data.models.Product
import com.albara.bazarstoreroom.data.models.Section
import com.albara.bazarstoreroom.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel (private val repository: Repository) : ViewModel() {

    private val showType = MutableStateFlow<ShowType>(ShowType.AllProducts)

    val sectionsWithProducts = showType.flatMapLatest { showType ->
        when(showType) {
            is ShowType.ProductsWithoutSection -> repository.getProductsWithoutSection()
            is ShowType.AllProducts -> repository.getAllSectionsWithProducts()
            is ShowType.SpecificSection -> repository.getProductsBySection(showType.sectionId)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val sections = repository.getAllSections()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun upsertSection(section: Section) = viewModelScope.launch {
        repository.upsertSection(section)
    }

    fun upsertProduct(product: Product) = viewModelScope.launch {
        repository.upsertProduct(product)
    }

    fun updateShowType(newShowType: ShowType){
        showType.value = newShowType
    }
}