package com.albara.bazarstoreroom.data.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.albara.bazarstoreroom.data.models.Product
import com.albara.bazarstoreroom.data.models.Section
import com.albara.bazarstoreroom.data.models.SectionWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreroomDao {

    @Upsert
    suspend fun upsertSection(section: Section)

    @Upsert
    suspend fun upsertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Delete
    suspend fun deleteProducts(products: List<Product>)

    @Delete
    suspend fun deleteSection(section: Section)

    @Query("SELECT * FROM Section")
    fun getAllSections() : Flow<List<Section>>

    @Transaction
    @Query("SELECT * FROM Section")
    fun getAllSectionsWithProducts() : Flow<List<SectionWithProducts>>

    @Transaction
    @Query("SELECT * FROM Section WHERE id == :sectionId")
    fun getProductsBySection(sectionId : Int) : Flow<List<SectionWithProducts>>

    @Query("SELECT * FROM Product WHERE amountToOrder != null")
    fun getOrders() : Flow<List<Product>>

    @Query("SELECT * FROM product WHERE sectionId == null")
    fun getProductsWithoutSection() : Flow<List<Product>>
}