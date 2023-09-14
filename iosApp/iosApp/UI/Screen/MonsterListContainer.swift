import shared
import Factory

public class MonsterListContainer: SharedContainer {
        
    public static let stateMachine = Factory {
        MonsterListStateMachine(monsterRepository: AppContainer.monsterRepository())
    }    
}
