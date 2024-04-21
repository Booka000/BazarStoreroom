package com.albara.bazarstoreroom.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albara.bazarstoreroom.data.models.Product
import com.albara.bazarstoreroom.data.models.Section
import com.albara.bazarstoreroom.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: Repository
) : ViewModel() {

    private val showType = MutableStateFlow<ShowType>(ShowType.AllProducts)

    var bottomSheetDialogIsVisible by mutableStateOf(false)
        private set
    var bottomSheetDialogProduct by mutableStateOf(Product(0,"",""))
        private set


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

    fun openBottomSheetDialogProduct(product: Product){
        bottomSheetDialogProduct = product
        bottomSheetDialogIsVisible = true
    }

    fun closeBottomSheetDialog(){
        bottomSheetDialogIsVisible = false
    }
}