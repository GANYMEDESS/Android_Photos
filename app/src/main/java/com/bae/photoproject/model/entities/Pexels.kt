package com.bae.photoproject.model.entities

object Pexels
{
   data class PhotoInfo(
       val photos: List<Information>
   )

    data class Information(
        val page: Int,
        val per_page: Int,
        val photos: List<Photos>,
        val total_results: Int,
        val next_page: String,
    )

    data class Photos(
        val id: Int,
        val width: Int,
        val height: Int,
        val url: String,
        val photographer: String,
        val photographer_url: String,
        val photographer_id: Int,
        val avg_color: String,
        val src: Src,
        val liked: Boolean
    )

    data class Src(
        val original: String,
        val large2x: String,
        val large: String,
        val medium: String,
        val small: String,
        val portrait: String,
        val landscape: String,
        val tiny: String
    )
}