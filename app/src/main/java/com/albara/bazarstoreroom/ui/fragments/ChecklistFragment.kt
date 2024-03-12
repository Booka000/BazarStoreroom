package com.albara.bazarstoreroom.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.albara.bazarstoreroom.R
import com.albara.bazarstoreroom.databinding.FragmentChecklistBinding
import com.albara.bazarstoreroom.ui.MainActivity
import com.albara.bazarstoreroom.ui.MainViewModel

class ChecklistFragment : BaseFragment<FragmentChecklistBinding, MainViewModel>() {
    override fun getMainViewModel(): MainViewModel {
        return (activity as MainActivity).viewModel
    }

    override fun inflateBinding(): FragmentChecklistBinding {
        return FragmentChecklistBinding.inflate(layoutInflater)
    }
}