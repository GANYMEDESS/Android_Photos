package com.bae.photoproject.viewmodel

import androidx.lifecycle.*
import com.bae.photoproject.model.database.FavoritePhotoRepository
import com.bae.photoproject.model.entities.FavoritePhoto
import kotlinx.coroutines.launch

class FavoritePhotoViewModel(private val repository: FavoritePhotoRepository): ViewModel()
{
    fun insert(favoritePhoto: FavoritePhoto) = viewModelScope.launch {
        repository.insertFavoritePhotoInfos(favoritePhoto)
    }

    fun update(favoritePhoto: FavoritePhoto) = viewModelScope.launch {
        repository.updateFavoritePhotoInfos(favoritePhoto)
    }

    fun delete(favoritePhoto: FavoritePhoto) = viewModelScope.launch {
        repository.deleteFavoritePhotoInfos(favoritePhoto)
    }

    val favoritePhotos: LiveData<List<FavoritePhoto>> = repository.favoritePhotos.asLiveData()
}

class FavoritePhotoViewModelFactory(private val repository: FavoritePhotoRepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavoritePhotoViewModel::class.java)){
            return FavoritePhotoViewModel(repository) as T
        }
        throw IllegalAccessError("Unknown ViewModel Class")
    }
}