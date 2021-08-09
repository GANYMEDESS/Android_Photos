package com.bae.photoproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bae.photoproject.model.entities.Pexels
import com.bae.photoproject.model.network.PexelsApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchPhotosViewModel: ViewModel()
{
    private val pexelsApiService = PexelsApiService()
    private val compositeDisposable = CompositeDisposable()

    val loadPhotos = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()
    val photoResponse = MutableLiveData<Pexels.Information>()

    fun getSearchPhotoFromAPI(query: String) {
        loadPhotos.value = true

        compositeDisposable.add(
            pexelsApiService.getSearchPhotos(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Pexels.Information>(){
                    override fun onSuccess(photo: Pexels.Information?) {
                        loadPhotos.value = false
                        photoResponse.value = photo!!
                        loadingError.value = false
                    }

                    override fun onError(e: Throwable?) {
                        loadPhotos.value = false
                        loadingError.value = true
                        e?.printStackTrace()
                    }
                })
        )
    }
}