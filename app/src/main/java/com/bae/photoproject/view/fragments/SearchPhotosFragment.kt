package com.bae.photoproject.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bae.photoproject.R
import com.bae.photoproject.databinding.FragmentSearchPhotosBinding
import com.bae.photoproject.utils.JSLog
import com.bae.photoproject.viewmodel.SearchPhotosViewModel

class SearchPhotosFragment : Fragment()
{
    private var mBinding: FragmentSearchPhotosBinding? = null
    private lateinit var mSearchPhotoViewModel: SearchPhotosViewModel
    private var mProgressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSearchPhotosBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSearchPhotoViewModel = ViewModelProvider(this).get(SearchPhotosViewModel::class.java)

        searchViewModelObserver()

        mBinding?.svPhotos?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    JSLog.i("TextSubmit Search -> $it")
                    mSearchPhotoViewModel.getSearchPhotoFromAPI(it)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                JSLog.i("onQueryTextChange $newText")

                return true
            }
        })
    }

    private fun showCustomProgressDialog() {
        mProgressDialog = Dialog(requireActivity())
        mProgressDialog?.let {
            it.setContentView(R.layout.dialog_custom_progress)
            it.show()
        }
    }

    private fun hideProgressDialog() {
        mProgressDialog?.dismiss()
    }

    private fun searchViewModelObserver() {
        mSearchPhotoViewModel.photoResponse.observe(viewLifecycleOwner,
            { response ->
                response?.let {
                    JSLog.i("Search Response ${response.photos}")
                }
            }
        )

        mSearchPhotoViewModel.loadingError.observe(viewLifecycleOwner,
            { dataError ->
                dataError?.let {
                    JSLog.e("Search API Error $dataError")
                }
            }
        )

        mSearchPhotoViewModel.loadPhotos.observe(viewLifecycleOwner,
            { loadPhoto ->
                loadPhoto?.let {
                    JSLog.i("Photo Loading $loadPhoto")

                    if(loadPhoto) {
                        showCustomProgressDialog()
                    }else {
                        hideProgressDialog()
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        mBinding = null
    }
}