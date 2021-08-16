package com.bae.photoproject.model.database

import androidx.annotation.WorkerThread
import com.bae.photoproject.model.entities.FavoritePhoto
import kotlinx.coroutines.flow.Flow

class FavoritePhotoRepository(private val favoritePhotoDao: FavoritePhotoDao)
{
    @WorkerThread
    suspend fun insertFavoritePhotoInfos(favoritePhoto: FavoritePhoto){
        favoritePhotoDao.insertFavoritePhotoInfos(favoritePhoto)
    }
    @WorkerThread
    suspend fun updateFavoritePhotoInfos(favoritePhoto: FavoritePhoto){
        favoritePhotoDao.updateFavoritePhotoInfos(favoritePhoto)
    }
    @WorkerThread
    suspend fun deleteFavoritePhotoInfos(favoritePhoto: FavoritePhoto){
        favoritePhotoDao.deleteFavoritePhotoInfos(favoritePhoto)
    }
    val favoritePhotos: Flow<List<FavoritePhoto>> = favoritePhotoDao.getAllFavoritePhotos()
}