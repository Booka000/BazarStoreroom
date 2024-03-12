package com.albara.bazarstoreroom.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.albara.bazarstoreroom.R
import com.albara.bazarstoreroom.adapters.SectionAdapter
import com.albara.bazarstoreroom.adapters.SpinnerAdapter
import com.albara.bazarstoreroom.data.models.Section
import com.albara.bazarstoreroom.databinding.FragmentProductsBinding
import com.albara.bazarstoreroom.ui.MainActivity
import com.albara.bazarstoreroom.ui.MainViewModel
import com.albara.bazarstoreroom.ui.ShowType
import com.albara.bazarstoreroom.ui.dialogs.AddProductBottomSheetDialog
import com.albara.bazarstoreroom.ui.dialogs.AddSectionBottomSheetDialog


class ProductsFragment : BaseFragment<FragmentProductsBinding, MainViewModel>() {

    private val rotateOpen : Animation by lazy{ AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim)}
    private val rotateClose : Animation by lazy{ AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim)}
    private val scaleOpen : Animation by lazy{ AnimationUtils.loadAnimation(context, R.anim.from_bottun_anim)}
    private val scaleClose : Animation by lazy{ AnimationUtils.loadAnimation(context, R.anim.to_button_anim)}
    private var clicked = false

    private lateinit var initialList : List<Section>
    private lateinit var spinnerAdapter : SpinnerAdapter
    private val sectionAdapter = SectionAdapter()


    override fun getMainViewModel(): MainViewModel {
        return (activity as MainActivity).viewModel
    }

    override fun inflateBinding(): FragmentProductsBinding {
        return FragmentProductsBinding.inflate(layoutInflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.rvSectionWithProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSectionWithProducts.adapter = sectionAdapter

        initialList = listOf(Section(0, getString(R.string.all)),
            Section(0, getString(R.string.noSectionPlaceholder)))

        spinnerAdapter = SpinnerAdapter(requireContext(), initialList.toMutableList())
        binding.spSections.adapter = spinnerAdapter

        setListeners()
        setCollectors()
    }

    private fun setCollectors() {
        collectLatestLifecycleFlow(viewModel.sections) {sections ->
            val newList = mutableListOf<Section>()
            newList.add(initialList[0])
            newList.addAll(sections)
            newList.add(initialList[1])
            spinnerAdapter.updateData(newList)
        }

        collectLatestLifecycleFlow(viewModel.sectionsWithProducts) { sectionsWithProducts ->
            sectionAdapter.differ.submitList(sectionsWithProducts)
        }
    }

    private fun setListeners() {
        binding.actionButtonAdd.setOnClickListener{
            onAddButtonClicked()
        }

        binding.ActionButtonAddSection.setOnClickListener{
            popUpAddSectionDialog()
        }

        binding.spSections.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selectedSection = spinnerAdapter.getItem(position)
                selectedSection.apply {
                    when(sectionName) {
                        getString(R.string.all) -> viewModel.updateShowType(ShowType.AllProducts)
                        getString(R.string.noSectionPlaceholder) ->
                            viewModel.updateShowType(ShowType.ProductsWithoutSection)
                        else -> viewModel.updateShowType(ShowType.SpecificSection(id))
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.actionButtonAddProduct.setOnClickListener{
            popUpAddProductDialog()
        }
    }

    private fun popUpAddProductDialog() {
        val addProductBottomSheetDialog = AddProductBottomSheetDialog(
            viewModel.sections.value) {product ->
            viewModel.upsertProduct(product)
        }
        addProductBottomSheetDialog.show(childFragmentManager,
            addProductBottomSheetDialog.tag)
    }

    private fun popUpAddSectionDialog() {
        val addSectionBottomSheetDialog = AddSectionBottomSheetDialog{section ->
            viewModel.upsertSection(section)
        }
        addSectionBottomSheetDialog.show(childFragmentManager,
            addSectionBottomSheetDialog.tag)
    }

    private fun onAddButtonClicked() {
        clicked = !clicked
        setVisibility()
        startAnimation()
    }

    private fun startAnimation() {
        if(clicked){
            binding.actionButtonAddProduct.startAnimation(scaleOpen)
            binding.ActionButtonAddSection.startAnimation(scaleOpen)
            binding.actionButtonAdd.startAnimation(rotateOpen)
        } else {
            binding.actionButtonAddProduct.startAnimation(scaleClose)
            binding.ActionButtonAddSection.startAnimation(scaleClose)
            binding.actionButtonAdd.startAnimation(rotateClose)
        }
    }

    private fun setVisibility() {
        if(clicked) {
            binding.actionButtonAddProduct.visibility = View.VISIBLE
            binding.actionButtonAddProduct.isClickable = true
            binding.ActionButtonAddSection.visibility = View.VISIBLE
            binding.ActionButtonAddSection.isClickable = true
        } else {
            binding.actionButtonAddProduct.visibility = View.INVISIBLE
            binding.actionButtonAddProduct.isClickable = false
            binding.ActionButtonAddSection.visibility = View.INVISIBLE
            binding.ActionButtonAddSection.isClickable = false
        }
    }
}