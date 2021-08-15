package com.bae.photoproject.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bae.photoproject.model.entities.FavoritePhoto

@Database(entities = [FavoritePhoto::class], version = 1)
abstract class FavoritePhotoRoomDatabase: RoomDatabase()
{
    abstract fun favoritePhotoDao(): FavoritePhotoDao

    companion object{
        @Volatile
        private var INSTANCE: FavoritePhotoRoomDatabase? = null

        fun getDatabase(context: Context): FavoritePhotoRoomDatabase{
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoritePhotoRoomDatabase::class.java,
                    "favorite_photo_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}