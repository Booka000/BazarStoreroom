package com.albara.bazarstoreroom.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Section (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val sectionName : String
    ) {
    override fun toString(): String {
        return sectionName
    }
}