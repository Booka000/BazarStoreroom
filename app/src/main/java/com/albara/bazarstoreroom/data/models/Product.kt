package com.albara.bazarstoreroom.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val productName : String,
    val unit : String,
    val amountForWeekday : Double?,
    val amountForWeekend : Double?,
    val amountToOrder : Double? = null,
    val sectionId : Int? = null
)