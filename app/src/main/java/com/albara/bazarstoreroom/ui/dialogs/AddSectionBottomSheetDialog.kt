package com.albara.bazarstoreroom.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.albara.bazarstoreroom.R
import com.albara.bazarstoreroom.data.models.Section
import com.albara.bazarstoreroom.databinding.AddSectionDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddSectionBottomSheetDialog(val onAddButtonClicked : (Section) -> Unit) : BottomSheetDialogFragment() {

    private var _binding: AddSectionDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddSectionDialogBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addSectionDialogButton.setOnClickListener{
            val sectionTitle = binding.addSectionDialogText.text.toString()
            val color = binding.addSectionDialogText
            sectionTitle.also {
                if(it.isNotBlank()) {
                    val section = Section(0, it)
                    onAddButtonClicked(section)
                    dismiss()
                } else binding.addSectionDialogText.error = getString(R.string.sectionTitleAddingError)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}