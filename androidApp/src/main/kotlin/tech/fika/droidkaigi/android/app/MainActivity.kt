package tech.fika.droidkaigi.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tech.fika.droidkaigi.android.ui.foundation.MonsterTheme
import tech.fika.droidkaigi.android.core.contract
import tech.fika.droidkaigi.android.ui.screen.MonsterListScreen
import tech.fika.droidkaigi.android.ui.screen.MonsterListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MonsterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MonsterTheme {
                MonsterListScreen(contract = contract(store = viewModel.store))
            }
        }
    }
}
