package com.task.animalsapp.view.cat

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
import com.task.animalsapp.view.utils.MarginItemDecoration
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.cats.ICatsViewModel
import kotlinx.android.synthetic.main.fragment_animal.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatsFragment : Fragment(), ItemBinder<AnimalItemViewModel> {

    companion object {
        fun newInstance() = CatsFragment()
    }

    private lateinit var binding: FragmentAnimalBinding

    private val viewModel: ICatsViewModel by viewModel()

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
        initBindings()
        intRv()
        viewModel.loadCommand.execute()
    }

    private fun intRv() {
        cats_rv.layoutManager = LinearLayoutManager(context)
        cats_rv.addItemDecoration(
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

    override fun getLayoutRes(model: AnimalItemViewModel): Int =
        R.layout.cell_animal

    override fun getBindingVariable(model: AnimalItemViewModel): Int = BR.viewModel
}