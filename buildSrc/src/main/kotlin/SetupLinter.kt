import org.gradle.api.Project
import java.io.File

fun Project.setupLinter() {
    plugins.apply(deps.plugins.ktlint)

    ktlint {
        verbose.set(true)
        android.set(true)
        ignoreFailures.set(true)
        coloredOutput.set(true)
        outputColorName.set("RED")
        additionalEditorconfigFile.set(File("${rootDir.path}/.editorconfig"))
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
        }
        filter {
            include("**/kotlin/**")
            include("*.kts")
            exclude("**/build/**")
            exclude("**/generated/**")
        }
    }
}
