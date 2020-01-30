package com.task.animalsapp.viewmodel.animals.details.implementation

import com.task.animalsapp.viewmodel.base.IDisposableCommand
import com.task.animalsapp.viewmodel.base.implementation.OnceExecutableCommand
import com.task.animalsapp.viewmodel.messages.AnimalItemMessage
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SetupAnimalDetailsCommand(
    private val viewModel: AnimalDetailsViewModel,
    private val eventBus: EventBus
) : OnceExecutableCommand(), IDisposableCommand {

    override fun executeOnce() {
        eventBus.register(this)
    }

    override fun dispose() {
        eventBus.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAnimalItemMessageChanged(message: AnimalItemMessage) {
        viewModel.animalItemMutable.value = message.item
    }
}
