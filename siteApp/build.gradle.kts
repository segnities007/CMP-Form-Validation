import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            @Suppress("DEPRECATION")
            implementation(compose.materialIconsExtended)
            implementation(libs.markdown.renderer)
            implementation(libs.markdown.renderer.m3)
            implementation(libs.markdown.renderer.code)
            implementation(projects.validationCore)
            implementation(projects.validationCompose)
        }
    }
}

compose.resources {
    packageOfResClass = "com.segnities007.cmp_form_validation.site.resources"
}

// ── Font download task ───────────────────────────────────────────────────────
// Downloads Noto Sans JP for CJK text rendering in Compose for Web (Skia-WASM).
// Fonts are cached locally and not committed to VCS.
val downloadFonts by tasks.registering {
    val fontDir = file("src/commonMain/composeResources/font")
    val notoFile = File(fontDir, "noto_sans_jp.ttf")

    outputs.file(notoFile)

    doLast {
        fontDir.mkdirs()
        if (!notoFile.exists()) {
            logger.lifecycle("Downloading Noto Sans JP font...")
            try {
                ant.invokeMethod(
                    "get",
                    mapOf(
                        "src" to "https://github.com/google/fonts/raw/main/ofl/notosansjp/NotoSansJP%5Bwght%5D.ttf",
                        "dest" to notoFile,
                        "skipexisting" to true,
                    ),
                )
                logger.lifecycle("Downloaded: ${notoFile.name} (${notoFile.length() / 1024} KB)")
            } catch (e: Exception) {
                logger.warn("Could not download Noto Sans JP: ${e.message}")
                logger.warn("Japanese text may not render correctly. Run './gradlew downloadFonts' with internet access.")
            }
        }
    }
}

afterEvaluate {
    tasks.configureEach {
        if (name.contains("generateComposeRes", ignoreCase = true) ||
            name.contains("prepareComposeRes", ignoreCase = true) ||
            name.contains("copyNonXmlValueResources", ignoreCase = true) ||
            name.contains("copyXmlValueResources", ignoreCase = true)
        ) {
            dependsOn(downloadFonts)
        }
    }
}
