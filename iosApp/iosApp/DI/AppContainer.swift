import Factory
import shared

public class AppContainer: SharedContainer {
        
    public static let storeFactory = Factory<StoreFactory> {
        DefaultStoreFactory()
    }
    
    public static let monsterRepository = Factory<MonsterRepository> {
        MockMonsterRepository(throwErrorRate: 0.3)
    }
}
