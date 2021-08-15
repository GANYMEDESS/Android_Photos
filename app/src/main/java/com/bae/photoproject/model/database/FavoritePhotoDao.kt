package com.bae.photoproject.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bae.photoproject.model.entities.FavoritePhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePhotoDao
{
    @Insert
    suspend fun insertFavoritePhotoInfos(favoritePhoto: FavoritePhoto)

    @Query("SELECT * FROM FAVORITE_PHOTOS_TABLE ORDER BY id")
    fun getAllFavoritePhotos(): Flow<List<FavoritePhoto>>
}