package com.bae.photoproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bae.photoproject.R
import com.bae.photoproject.application.JSApplication
import com.bae.photoproject.databinding.FragmentFavoritePhotosBinding
import com.bae.photoproject.model.entities.FavoritePhoto
import com.bae.photoproject.view.adapters.FavoritePhotoAdapter
import com.bae.photoproject.viewmodel.FavoritePhotoViewModel
import com.bae.photoproject.viewmodel.FavoritePhotoViewModelFactory

class FavoritePhotosFragment : Fragment()
{
    private var mBinding: FragmentFavoritePhotosBinding? = null
    private val mFavoritePhotoViewModel: FavoritePhotoViewModel by viewModels{
        FavoritePhotoViewModelFactory(((requireActivity().application) as JSApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFavoritePhotosBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFavoritePhotoViewModel.favoritePhotos.observe(viewLifecycleOwner){ photos ->
            photos.let {
                mBinding!!.rvFavoritePhotosList.layoutManager =
                    GridLayoutManager(requireActivity(), 1)
                val adapter = FavoritePhotoAdapter(this)
                mBinding!!.rvFavoritePhotosList.adapter = adapter

                if(it.isNotEmpty()){
                    mBinding!!.rvFavoritePhotosList.visibility = View.VISIBLE
                    mBinding!!.tvNotYetAddedFavorite.visibility =View.GONE
                    adapter.photosList(it)
                }else{
                    mBinding!!.rvFavoritePhotosList.visibility = View.GONE
                    mBinding!!.tvNotYetAddedFavorite.visibility =View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mBinding = null
    }

    fun deleteFromFavorite(photo: FavoritePhoto) {
        val builder = AlertDialog.Builder(requireActivity())

        builder.run {
            setTitle(resources.getString(R.string.title_delete_photo))
            setMessage(resources.getString(R.string.msg_delete_photo_dialog))
            setIcon(android.R.drawable.ic_dialog_alert)
            setPositiveButton(resources.getString(R.string.lbl_yes)){dialogInterface, _ ->
                mFavoritePhotoViewModel.delete(photo)
                dialogInterface.dismiss()
            }
            setNegativeButton(resources.getString(R.string.lbl_no)){dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}