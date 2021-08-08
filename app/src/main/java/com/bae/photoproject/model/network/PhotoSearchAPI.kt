package com.bae.photoproject.model.network

import com.bae.photoproject.model.entities.Pexels
import com.bae.photoproject.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoSearchAPI
{
    @GET(Constants.API_SEARCH_ENDPOINT)
    fun getSearchPhotos(
        @Query(Constants.API_AUTHORIZATION) Authorization: String,
    ): Single<Pexels.PhotoInfo>
}