package com.albara.bazarstoreroom.ui.fragments

import androidx.fragment.app.viewModels
import com.albara.bazarstoreroom.databinding.FragmentCartBinding
import com.albara.bazarstoreroom.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding, MainViewModel>() {

    override fun getMainViewModel(): MainViewModel {
        val viewModel: MainViewModel by viewModels()
        return viewModel
    }

    override fun inflateBinding(): FragmentCartBinding {
        return FragmentCartBinding.inflate(layoutInflater)
    }

}