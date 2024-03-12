package com.albara.bazarstoreroom.ui.fragments

import android.os.Bundle
import android.view.View
import com.albara.bazarstoreroom.databinding.FragmentCartBinding
import com.albara.bazarstoreroom.ui.MainActivity
import com.albara.bazarstoreroom.ui.MainViewModel

class CartFragment : BaseFragment<FragmentCartBinding, MainViewModel>() {

    override fun getMainViewModel(): MainViewModel {
       return (activity as MainActivity).viewModel
    }

    override fun inflateBinding(): FragmentCartBinding {
        return FragmentCartBinding.inflate(layoutInflater)
    }

}