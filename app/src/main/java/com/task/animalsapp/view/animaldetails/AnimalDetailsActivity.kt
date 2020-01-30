package com.task.animalsapp.view.animaldetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.task.animalsapp.R
import com.task.animalsapp.databinding.ActivityAnimalDetailsBinding
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.details.IAnimalDetailsViewModel
import com.task.animalsapp.viewmodel.messages.AnimalItemMessage
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class AnimalDetailsActivity : AppCompatActivity() {

    companion object {
        private const val ITEM_KEY = "item_key"
        fun start(context: Context, item: AnimalItemViewModel) {
            val intent = Intent(context, AnimalDetailsActivity::class.java)
            intent.putExtra(ITEM_KEY, item)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityAnimalDetailsBinding

    private val eventBus: EventBus by inject()

    private val viewModel: IAnimalDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animal_details)
        initBindings()
        viewModel.setupCommand.execute()
        intent?.let {
            eventBus.post(AnimalItemMessage(it.getParcelableExtra(ITEM_KEY)))
        }
    }

    private fun initBindings() {
        binding.viewModel = viewModel
    }
}