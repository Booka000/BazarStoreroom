package com.albara.bazarstoreroom.ui.fragments

import androidx.fragment.app.viewModels
import com.albara.bazarstoreroom.databinding.FragmentChecklistBinding
import com.albara.bazarstoreroom.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChecklistFragment : BaseFragment<FragmentChecklistBinding, MainViewModel>() {
    override fun getMainViewModel(): MainViewModel {
        val viewModel: MainViewModel by viewModels()
        return viewModel
    }

    override fun inflateBinding(): FragmentChecklistBinding {
        return FragmentChecklistBinding.inflate(layoutInflater)
    }
}