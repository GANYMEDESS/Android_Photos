package com.bae.photoproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.bae.photoproject.databinding.FragmentSearchPhotosBinding
import com.bae.photoproject.utils.JSLog

class SearchPhotosFragment : Fragment()
{
    private var mBinding: FragmentSearchPhotosBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSearchPhotosBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.svPhotos?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                JSLog.i("TextSubmit Search -> $query")

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                JSLog.i("onQueryTextChange $newText")

                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        mBinding = null
    }
}