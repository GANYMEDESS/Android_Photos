package com.bae.photoproject.model.database

import androidx.room.*
import com.bae.photoproject.model.entities.FavoritePhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePhotoDao
{
    @Insert
    suspend fun insertFavoritePhotoInfos(favoritePhoto: FavoritePhoto)

    @Update
    suspend fun updateFavoritePhotoInfos(favoritePhoto: FavoritePhoto)

    @Delete
    suspend fun deleteFavoritePhotoInfos(favoritePhoto: FavoritePhoto)

    @Query("SELECT * FROM FAVORITE_PHOTOS_TABLE ORDER BY id")
    fun getAllFavoritePhotos(): Flow<List<FavoritePhoto>>
}