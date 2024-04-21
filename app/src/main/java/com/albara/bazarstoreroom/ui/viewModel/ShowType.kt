package com.albara.bazarstoreroom.ui.viewModel

sealed class ShowType {
    data object AllProducts : ShowType()
    data class SpecificSection(val sectionId : Int) : ShowType()
    data object ProductsWithoutSection : ShowType()
}
