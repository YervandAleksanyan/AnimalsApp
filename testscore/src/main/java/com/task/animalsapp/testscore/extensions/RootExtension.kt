package com.task.animalsapp.testscore.extensions

import io.mockk.spyk
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.meta.*
import org.spekframework.spek2.style.gherkin.FeatureBody

@Synonym(SynonymType.GROUP, prefix = "Feature: ")
@Descriptions(Description(DescriptionLocation.VALUE_PARAMETER, 0))
fun Root.feature(description: String, body: FeatureBody.() -> Unit) {
    group("Feature: $description", defaultCachingMode = CachingMode.EACH_GROUP) {
        val feature = spyk(FeatureBody(this))
        body(feature)
    }
}

