package com.bae.photoproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bae.photoproject.databinding.FragmentSearchPhotosBinding

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

    override fun onDestroy() {
        super.onDestroy()

        mBinding = null
    }
}