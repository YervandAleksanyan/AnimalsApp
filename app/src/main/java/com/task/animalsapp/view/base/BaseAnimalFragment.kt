package com.task.animalsapp.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.animalsapp.BR
import com.task.animalsapp.R
import com.task.animalsapp.databinding.FragmentAnimalBinding
import com.task.animalsapp.view.controls.bindableRecylcerView.bindings.ItemBinder
import com.task.animalsapp.view.controls.bindableRecylcerView.eventHandlers.ClickHandler
import com.task.animalsapp.view.utils.MarginItemDecoration
import com.task.animalsapp.viewmodel.animals.base.IAnimalBaseViewModel
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import kotlinx.android.synthetic.main.fragment_animal.*

abstract class BaseAnimalFragment : Fragment(), ItemBinder<AnimalItemViewModel> {

    private lateinit var binding: FragmentAnimalBinding

    abstract val viewModel: IAnimalBaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_animal, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.loadCommand.execute()
        }
        initBindings()
        intRv()
    }

    override fun getLayoutRes(model: AnimalItemViewModel): Int =
        R.layout.cell_animal

    override fun getBindingVariable(model: AnimalItemViewModel): Int = BR.viewModel

    abstract fun getAnimalClickHandler(): ClickHandler<AnimalItemViewModel>

    private fun intRv() {
        animals_rv.layoutManager = LinearLayoutManager(context)
        animals_rv.addItemDecoration(
            MarginItemDecoration(
                context?.resources?.getDimension(R.dimen.list_view_vertical_margin)!!.toInt(),
                context?.resources?.getDimension(R.dimen.list_view_horizontal_margin)!!.toInt()
            )
        )
    }

    private fun initBindings() {
        binding.viewModel = viewModel
        binding.view = this
        binding.lifecycleOwner = viewLifecycleOwner
    }
}