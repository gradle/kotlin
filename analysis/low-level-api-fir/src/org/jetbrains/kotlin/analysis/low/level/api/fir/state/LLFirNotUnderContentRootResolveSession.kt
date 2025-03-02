/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.low.level.api.fir.state

import org.jetbrains.kotlin.analysis.low.level.api.fir.api.DiagnosticCheckerFilter
import org.jetbrains.kotlin.analysis.low.level.api.fir.sessions.LLFirSession
import org.jetbrains.kotlin.analysis.project.structure.KtModule
import org.jetbrains.kotlin.diagnostics.KtPsiDiagnostic
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile

internal class LLFirNotUnderContentRootResolveSession(
    useSiteFirSession: LLFirSession
) : LLFirResolvableResolveSession(useSiteFirSession) {
    override fun getDiagnostics(element: KtElement, filter: DiagnosticCheckerFilter): List<KtPsiDiagnostic> =
        emptyList()

    override fun collectDiagnosticsForFile(ktFile: KtFile, filter: DiagnosticCheckerFilter): Collection<KtPsiDiagnostic> =
        emptyList()

    override fun getModuleKind(module: KtModule): ModuleKind {
        return when (module) {
            useSiteKtModule -> ModuleKind.RESOLVABLE_MODULE
            else -> ModuleKind.BINARY_MODULE
        }
    }
}

