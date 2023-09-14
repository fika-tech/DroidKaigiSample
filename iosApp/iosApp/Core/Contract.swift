import SwiftUI
import Combine
import shared

open class Contract<I: Intent, A: Action, S: shared.State>: ObservableObject {
    @Published public private(set) var state: S
    public let dispatch: (I) -> Void
    public var events: some Publisher<A, Never> { eventSubject }
    private let eventSubject = PassthroughSubject<A, Never>()
    private var cancellables: Set<AnyCancellable> = []


    public init(store: Store) {
        self.state = store.currentState as! S
        self.dispatch = { store.dispatch(intent: $0) }
        AnyCancellable { store.dispose() }.store(in: &cancellables)

        store.collect { [weak self] newState in
            if let self, let newState = newState as? S {
                self.state = newState
            }
        } onEvent: { [eventSubject, store] event in
            if let action = event as? A {
                store.process(event: action)
                eventSubject.send(action)
            }
        }
    }

    /// Mock 用 Initializer
    public init(state: S) {
        self.state = state
        self.dispatch = { _ in }
    }
}
