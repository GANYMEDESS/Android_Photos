package com.bae.photoproject.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bae.photoproject.databinding.FragmentPhotoDetailBinding
import com.bae.photoproject.utils.JSLog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.IOException

class PhotoDetailFragment : Fragment()
{
    private var mBinding: FragmentPhotoDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: PhotoDetailFragmentArgs by navArgs()

        JSLog.i("Image -> ${args.photoDetails}")
        mBinding?.run {
            try {
                val circularProgressDrawable = CircularProgressDrawable(requireContext())
                circularProgressDrawable.strokeWidth = 10f
                circularProgressDrawable.centerRadius = 50f
                circularProgressDrawable.start()

                Glide.with(requireActivity())
                    .load(args.photoDetails)
                    .placeholder(circularProgressDrawable)
                    .addListener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            JSLog.e("Error Loading Image. $e")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                    })
                    .into(ivDetailPhoto)
            }catch(e: IOException){e.printStackTrace()}
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mBinding = null
    }
}