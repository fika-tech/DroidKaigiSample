import Foundation
import shared

final class MonsterListViewModel {
    let store = AppContainer.storeFactory().create(
        initialState: MonsterListState.Initial(),
        processor: MonsterListContainer.stateMachine().processor,
        reducer: MonsterListContainer.stateMachine().reducer
    )
}
