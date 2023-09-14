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
            )
            apis(
                deps.macaron.core,
                deps.macaron.statemachine,
                deps.macaron.logging,
                deps.logging.kermit,
            )
        }
    }
}
