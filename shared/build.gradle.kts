setupKotlinMultiplatform(
    buildTargets = listOf(
        BuildTarget.Ios, BuildTarget.Android(namespace = "monsters.shared")
    ),
)

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementations(
                deps.kotlinx.coroutinesCore,
                deps.macaron.core,
                deps.macaron.statemachine,
            )
            apis(
                deps.logging.kermit,
            )
        }
    }
}
