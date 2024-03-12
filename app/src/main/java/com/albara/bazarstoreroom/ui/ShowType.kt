package com.albara.bazarstoreroom.ui

sealed class ShowType {
    object AllProducts : ShowType()
    data class SpecificSection(val sectionId : Int) : ShowType()
    object ProductsWithoutSection : ShowType()
}
