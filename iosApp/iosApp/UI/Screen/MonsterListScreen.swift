import SwiftUI
import shared

struct MonsterListScreen: View {
    
    @ObservedObject
    var contract: Contract<MonsterListIntent, MonsterListAction, MonsterListState>
    
    var body: some View {
        ZStack {
            switch contract.state {
            case _ as MonsterListState.Loading:
                LoadingIndicator()
            case let state as MonsterListState.Stable:
                MonsterListView(state: state, dispatch: contract.dispatch)
            case let state as MonsterListState.Error:
                FullScreenErrorView(
                    error: state.error,
                    onClickRetry: {
                        contract.dispatch(MonsterListIntent.ClickErrorRetry())
                    }
                )
            default:
                EmptyView()
            }
        }.onAppear {
            contract.dispatch(.OnInit())
        }
    }
}

struct MonsterListScreen_Previews: PreviewProvider {
    static var previews: some View {
        MonsterListScreen(
            contract: Contract(state: MonsterListState.Initial())
        )
    }
}
