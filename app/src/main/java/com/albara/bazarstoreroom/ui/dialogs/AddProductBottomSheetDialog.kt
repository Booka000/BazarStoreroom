package com.albara.bazarstoreroom.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.albara.bazarstoreroom.R
import com.albara.bazarstoreroom.data.models.Product
import com.albara.bazarstoreroom.data.models.Section
import com.albara.bazarstoreroom.databinding.AddProductDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddProductBottomSheetDialog (private val sections : List<Section>,
                                   private val onAddClickListener: (Product)-> Unit)
    : BottomSheetDialogFragment() {

    private var _binding: AddProductDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddProductDialogBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, sections)

        binding.spProductDialog.adapter = adapter

        binding.addProductDialogButton.setOnClickListener {
            val product = generateProduct()
            product?.also{
                onAddClickListener(it)
                dismiss()
            }
        }
    }

    private fun generateProduct(): Product? {
        val productTitle = binding.etAddProductName.text.toString()
        val productUnit = binding.etAddProductUnit.text.toString()
        if (productTitle.isNotBlank() && productUnit.isNotBlank()) {
            val amountInWeekdays = binding.etAddProductWeekdaysAmount.text
                .toString()
            val amountInWeekend = binding.etAddProductWeekdaysAmount.text
                .toString()
            if(amountInWeekdays.notBlankAndValidDouble() && amountInWeekend.notBlankAndValidDouble()) {
                return Product( 0,
                    productTitle,
                    productUnit,
                    amountInWeekdays.toDouble(),
                    amountInWeekend.toDouble(),
                    null,
                    binding.spProductDialog.getSelectedSectionId()
                )
            } else {
                if(amountInWeekdays.isNotBlank()) {
                    binding.etAddProductWeekdaysAmount.error =
                        getString(R.string.productNumberAddingError)
                    return null
                }
                if(amountInWeekend.isNotBlank()) {
                    binding.etAddProductWeekendAmount.error =
                        getString(R.string.productNumberAddingError)
                    return null
                }
                return Product(0,
                productTitle,
                productUnit,
                amountInWeekdays.toDoubleOrNull(),
                amountInWeekend.toDoubleOrNull(),
                null,
                binding.spProductDialog.getSelectedSectionId())
            }
        } else {
            if (productTitle.isBlank()) binding.etAddProductName.error =
                getString(R.string.productTitleAddingError)
            if (productUnit.isBlank()) binding.etAddProductUnit.error =
                getString(R.string.productTitleAddingError)
            return null
        }
    }

    private fun String.notBlankAndValidDouble() : Boolean {
        return this.isNotBlank() && this.toDoubleOrNull() != null
    }
    
    private fun Spinner.getSelectedSectionId() : Int? {
        return if(selectedItemPosition == AdapterView.INVALID_POSITION)
            null
        else (getItemAtPosition(selectedItemPosition) as Section).id
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}