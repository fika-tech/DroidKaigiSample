import SwiftUI
import shared

@main
struct iOSApp: App {
    let viewModel = MonsterListViewModel()
    
	var body: some Scene {
		WindowGroup {
			MonsterListScreen(
                contract: Contract<
                    MonsterListIntent,
                    MonsterListAction,
                    MonsterListState
                >(store: viewModel.store)
            )
		}
	}
}
