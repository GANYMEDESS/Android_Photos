package com.bae.photoproject.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bae.photoproject.R
import com.bae.photoproject.databinding.FragmentSearchPhotosBinding
import com.bae.photoproject.utils.JSLog
import com.bae.photoproject.view.activities.MainActivity
import com.bae.photoproject.view.adapters.SearchPhotoAdapter
import com.bae.photoproject.viewmodel.SearchPhotosViewModel

class SearchPhotosFragment : Fragment()
{
    private var mBinding: FragmentSearchPhotosBinding? = null
    private var mProgressDialog: Dialog? = null
    private lateinit var mSearchPhotoViewModel: SearchPhotosViewModel
    private lateinit var mSearchPhotoAdapter: SearchPhotoAdapter

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

        mBinding?.run {
            // RecyclerView
            rvSearchPhotosList.layoutManager = GridLayoutManager(requireActivity(), 2)
            mSearchPhotoAdapter = SearchPhotoAdapter(this@SearchPhotosFragment)
            rvSearchPhotosList.adapter = mSearchPhotoAdapter

            tvNotYetSearched.visibility = View.VISIBLE
            rvSearchPhotosList.visibility = View.GONE

            // SearchView
            svPhotos.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        JSLog.i("TextSubmit Search -> $it")
                        mSearchPhotoViewModel.getSearchPhotoFromAPI(it)
                    }

                    svPhotos.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    JSLog.i("onQueryTextChange $newText")

                    return true
                }
            })
        }
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
                    mBinding?.tvNotYetSearched?.visibility = View.GONE
                    mBinding?.rvSearchPhotosList?.visibility = View.VISIBLE
                    mSearchPhotoAdapter.photosList(response.photos)
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

    fun photoDetails(photo: String) {
        findNavController().navigate(SearchPhotosFragmentDirections.actionNavigationSearchPhotosToPhotoDetailFragment(
            photo
        ))

        if(requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }
    }

    override fun onResume() {
        super.onResume()
        if(requireActivity() is MainActivity){
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mBinding = null
    }
}