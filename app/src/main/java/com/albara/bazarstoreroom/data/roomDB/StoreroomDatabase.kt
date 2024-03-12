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


    companion object {

        private var INSTANCE : StoreroomDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDataBase(context).also { INSTANCE = it }
        }

        private fun createDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                StoreroomDatabase::class.java,
                "StoreRoomDatabase"
            ).build()
    }

}