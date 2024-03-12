package com.albara.bazarstoreroom.repository

import com.albara.bazarstoreroom.data.models.Product
import com.albara.bazarstoreroom.data.models.Section
import com.albara.bazarstoreroom.data.models.SectionWithProducts
import com.albara.bazarstoreroom.data.roomDB.StoreroomDao
import com.albara.bazarstoreroom.data.roomDB.StoreroomDatabase
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class Repository (db : StoreroomDatabase) {

    private val dao : StoreroomDao = db.getDao()

    suspend fun upsertSection(section: Section) = dao.upsertSection(section)

    fun getAllSections() = dao.getAllSections()

    fun getProductsWithoutSection () = dao.getProductsWithoutSection().map {products ->
        listOf(SectionWithProducts(null, products))
    }

    fun getProductsBySection(sectionId : Int) = dao.getProductsBySection(sectionId)

    fun getAllSectionsWithProducts() = combine(dao.getAllSectionsWithProducts(),dao.getProductsWithoutSection()) {
        productsWithSections, productsWithoutSection ->
        if (productsWithoutSection.isEmpty())
            productsWithSections
        else
            productsWithSections + SectionWithProducts(null,productsWithoutSection)
    }

    suspend fun upsertProduct(product: Product) = dao.upsertProduct(product)
}