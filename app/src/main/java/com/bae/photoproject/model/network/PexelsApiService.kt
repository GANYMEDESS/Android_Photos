package com.bae.photoproject.model.network

import com.bae.photoproject.model.entities.Pexels
import com.bae.photoproject.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PexelsApiService
{
    private val api = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(PhotoSearchAPI::class.java)

    fun getSearchPhotos(): Single<Pexels.PhotoInfo> {
        return api.getSearchPhotos(Constants.API_AUTHORIZATION_VALUE)
    }
}