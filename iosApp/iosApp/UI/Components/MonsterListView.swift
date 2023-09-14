import SwiftUI
import shared

struct MonsterListView: View {
    let state: MonsterListState.Stable
    let dispatch: (MonsterListIntent) -> Void
    
    var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(
                    Array(state.monsterList.enumerated()),
                    id: \.element
                ) { index, monster in
                    MonsterListItem(monster: monster) { monster in
                        dispatch(MonsterListIntent.ClickItem(monster: monster))
                    }.onAppear {
                        if index == Int(state.monsterList.count - 3) {
                            dispatch(MonsterListIntent.OnScrollToBottom())
                        }
                     }
                    Divider()
                }
                if state is MonsterListState.StablePageLoading {
                    PageLoadingIndicatorItem()
                }
                if let state = state as? MonsterListState.StablePageError {
                    PageErrorItem(error: state.error) {
                        dispatch(.ClickErrorRetry())
                    }
                }
            }
        }
    }
}

struct MonsterListView_Previews: PreviewProvider {
    static var previews: some View {
        MonsterListView(
            state: MonsterListState.StableInitial(
                monsterList: [
                    Monster(id: 1, name: "Name", imageUrl: "")
                ]
            ),
            dispatch: { _ in }
        )
    }
}
