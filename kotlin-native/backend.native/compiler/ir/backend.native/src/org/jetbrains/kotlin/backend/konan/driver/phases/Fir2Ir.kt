/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.backend.konan.driver.phases

import org.jetbrains.kotlin.backend.konan.driver.PhaseContext
import org.jetbrains.kotlin.backend.konan.driver.PhaseEngine
import org.jetbrains.kotlin.backend.konan.fir2Ir
import org.jetbrains.kotlin.backend.konan.ir.KonanSymbols
import org.jetbrains.kotlin.fir.backend.Fir2IrResult
import org.jetbrains.kotlin.fir.pipeline.FirResult

internal data class Fir2IrOutput(
        val firResult: FirResult,
        val fir2irResult: Fir2IrResult,
        val symbols: KonanSymbols
)

internal val Fir2IrPhase = createSimpleNamedCompilerPhase(
        "Fir2Ir", "Compiler Fir2Ir Frontend phase",
        outputIfNotEnabled = { _, _, _, _ -> error("Fir2Ir phase cannot be disabled") }
) { context: PhaseContext, input: FirOutput.Full ->
    context.fir2Ir(input)
}

internal fun <T : PhaseContext> PhaseEngine<T>.runFir2Ir(input: FirOutput.Full): Fir2IrOutput {
    return this.runPhase(Fir2IrPhase, input)
}
