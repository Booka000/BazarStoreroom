package com.albara.bazarstoreroom.data.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.albara.bazarstoreroom.data.models.Product
import com.albara.bazarstoreroom.data.models.Section


@Database(
    entities = [Product::class, Section::class],
    version = 1
)
abstract class StoreroomDatabase : RoomDatabase() {

    abstract fun getDao() : StoreroomDao

}