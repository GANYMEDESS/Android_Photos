package com.bae.photoproject.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bae.photoproject.R
import com.bae.photoproject.databinding.ItemFavoritePhotoLayoutBinding
import com.bae.photoproject.model.entities.FavoritePhoto
import com.bae.photoproject.view.fragments.FavoritePhotosFragment
import com.bumptech.glide.Glide

class FavoritePhotoAdapter(private val fragment: Fragment): RecyclerView.Adapter<FavoritePhotoAdapter.ViewHolder>()
{
    private var photos: List<FavoritePhoto> = listOf()

    class ViewHolder(view: ItemFavoritePhotoLayoutBinding): RecyclerView.ViewHolder(view.root){
        val ivPhotoImage = view.ivPhotoImage
        val tvTitle = view.tvPhotoTitle
        val ivFavoriteImage = view.ivFavoritePhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFavoritePhotoLayoutBinding = ItemFavoritePhotoLayoutBinding.inflate(
            LayoutInflater.from(fragment.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        Glide.with(fragment)
            .load(photo.previewPhoto)
            .into(holder.ivPhotoImage)

        holder.tvTitle.text = photo.photographer
        if(photo.favoritePhoto) {
            holder.ivFavoriteImage.setImageDrawable(
                ContextCompat.getDrawable(
                fragment.requireContext(),
                R.drawable.ic_favorite_selected
            ))
        }else{
            holder.ivFavoriteImage.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.ic_favorite_unselected
                ))
        }

        holder.ivFavoriteImage.setOnClickListener {
            if(fragment is FavoritePhotosFragment){
                fragment.deleteFromFavorite(photo)
            }
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun photosList(list: List<FavoritePhoto>) {
        photos = list
        notifyDataSetChanged()
    }
}