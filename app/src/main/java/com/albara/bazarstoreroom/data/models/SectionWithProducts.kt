package com.albara.bazarstoreroom.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class SectionWithProducts (
    @Embedded
    val section : Section? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "sectionId"
    )
    val products: List<Product>
    )