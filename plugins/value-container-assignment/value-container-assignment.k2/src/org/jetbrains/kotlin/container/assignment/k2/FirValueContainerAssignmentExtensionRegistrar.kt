/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.container.assignment.k2

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class FirValueContainerAssignmentExtensionRegistrar(private val annotations: List<String>) : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::FirValueContainerAssignmentAltererExtension
        +::FirValueContainerAssignmentCheckersExtension
        +FirValueContainerAnnotationMatchingService.getFactory(annotations)
    }
}
