package com.bae.photoproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bae.photoproject.databinding.FragmentCuratedPhotosBinding

class CuratedPhotosFragment : Fragment()
{
    private var mBinding: FragmentCuratedPhotosBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCuratedPhotosBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onDestroy() {
        super.onDestroy()

        mBinding = null
    }
}