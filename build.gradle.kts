import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.tasks.BaseKtLintCheckTask
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ktlint) apply false
}

subprojects {
    pluginManager.apply("io.gitlab.arturbosch.detekt")
    pluginManager.apply("org.jlleitschuh.gradle.ktlint")

    extensions.configure<KtlintExtension>("ktlint") {
        ignoreFailures.set(false)
        filter {
            exclude("**/build/**")
            exclude("**/generated/**")
        }
    }

    tasks.withType<BaseKtLintCheckTask>().configureEach {
        exclude("**/build/**", "**/generated/**")
        exclude { fileTreeElement ->
            fileTreeElement.file.invariantSeparatorsPath.contains("/build/generated/")
        }
    }

    extensions.configure<DetektExtension>("detekt") {
        ignoreFailures = false
        buildUponDefaultConfig = true
        config.setFrom(rootProject.file("detekt.yml"))
    }

    tasks.withType<Detekt>().configureEach {
        setSource(files(projectDir.resolve("src")))
        include("**/*.kt", "**/*.kts")
        exclude("build/**", "**/build/**", "**/generated/**")
        exclude { fileTreeElement ->
            fileTreeElement.file.invariantSeparatorsPath.contains("/build/generated/")
        }
    }
}

tasks.register("detektAll") {
    group = "verification"
    description = "Runs Detekt for all subprojects."
}

tasks.register("ktlintCheckAll") {
    group = "verification"
    description = "Runs ktlintCheck for all subprojects."
    dependsOn(subprojects.map { "${it.path}:ktlintCheck" })
}

gradle.projectsEvaluated {
    tasks.named("detektAll") {
        val detektTaskPaths = subprojects.mapNotNull { project ->
            when {
                project.tasks.findByName("detektMetadataMain") != null -> "${project.path}:detektMetadataMain"
                project.tasks.findByName("detekt") != null -> "${project.path}:detekt"
                else -> null
            }
        }
        dependsOn(detektTaskPaths)
    }
}
