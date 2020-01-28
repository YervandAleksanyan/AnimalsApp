package com.task.animalsapp.testscore.extensions

import io.mockk.mockk
import org.koin.core.Koin
import org.koin.core.KoinApplication.Companion.logger
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.definition.BeanDefinition
import org.koin.core.error.NoBeanDefFoundException
import org.koin.core.qualifier.Qualifier
import org.koin.core.time.measureDuration
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module
import org.koin.ext.getFullName
import kotlin.reflect.KClass


inline fun <reified T : Any> Koin.declareMockk(
    qualifier: Qualifier? = null,
    noinline stubbing: (T.() -> Unit)? = null
): T {
    val clazz = T::class

    val foundDefinition: BeanDefinition<T> = getDefinition(clazz, this, qualifier)

    this.declareMockedDefinition(foundDefinition, stubbing)

    return this.get()
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> getDefinition(
    clazz: KClass<T>,
    koin: Koin,
    qualifier: Qualifier?
): BeanDefinition<T> {
    logger.info("declare mock for '${clazz.getFullName()}'")

    return koin.rootScope.beanRegistry.findDefinition(qualifier, clazz) as BeanDefinition<T>?
        ?: throw NoBeanDefFoundException("No definition found for qualifier='$qualifier' & class='$clazz'")
}

/**
 * Declare & Create a mock in Koin container for given type
 *
 * @author Arnaud Giuliani
 */
inline fun <reified T : Any> Koin.declareMock(
    qualifier: Qualifier? = null,
    noinline stubbing: (T.() -> Unit)? = null
): T {

    val clazz = T::class
    val foundDefinition: BeanDefinition<T> = getDefinition(clazz, this, qualifier)

    declareMockedDefinition(foundDefinition, stubbing)

    return get(qualifier)
}

inline fun <reified T : Any> Koin.declareMockedDefinition(
    foundDefinition: BeanDefinition<T>,
    noinline stubbing: (T.() -> Unit)?
) {
    val definition: BeanDefinition<T> = foundDefinition.createMockedDefinition(stubbing)
    rootScope.beanRegistry.saveDefinition(definition)
}

inline fun <reified T : Any> BeanDefinition<T>.createMockedDefinition(noinline stubbing: (T.() -> Unit)? = null): BeanDefinition<T> {
    val copy = BeanDefinition<T>(qualifier, scopeName, primaryType)
    copy.secondaryTypes = this.secondaryTypes
    copy.definition = {
        val (instance: T, time: Double) = measureDuration {
            mockk<T>()
        }
        logger.debug("| mock created in $time ms")
        stubbing?.let { instance.apply(stubbing) }
        instance
    }
    copy.properties = this.properties.copy()
    copy.options = this.options.copy()
    copy.options.override = true
    copy.kind = this.kind
    copy.createInstanceHolder()
    return copy
}

fun KoinComponent.declare(moduleDeclaration: ModuleDeclaration) {
    val module = module(override = true, moduleDeclaration = moduleDeclaration)
    GlobalContext.get().modules(module)
}