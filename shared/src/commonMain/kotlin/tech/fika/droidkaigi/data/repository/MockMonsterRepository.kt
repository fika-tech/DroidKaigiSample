package tech.fika.droidkaigi.data.repository

import kotlinx.coroutines.delay
import tech.fika.droidkaigi.entities.Monster

class MockMonsterRepository : MonsterRepository {
    override suspend fun getMonsters(offset: Int, limit: Int): List<Monster> {
        delay(2_000L)
        return List(limit) { index ->
            val id = offset + index + 1
            val nameLength = (8..20).random()
            val name = buildString(nameLength) {
                repeat(nameLength) { append(('a'..'z').random()) }
            }
            val imageUrl = "https://github.com/fika-tech/DroigKaigiSample/tree/main/resources/" + (1..30).random()
            Monster(id = id, name = name, imageUrl = imageUrl)
        }
    }
}