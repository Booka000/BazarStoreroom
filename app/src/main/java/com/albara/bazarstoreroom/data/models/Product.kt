package com.albara.bazarstoreroom.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val productName : String,
    val unit : String,
    val amountForWeekday : Double? = null,
    val amountForWeekend : Double? = null,
    val amountToOrder : Double? = null,
    val sectionId : Int? = null
)