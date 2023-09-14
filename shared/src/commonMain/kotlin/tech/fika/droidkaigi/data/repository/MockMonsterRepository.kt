package tech.fika.droidkaigi.data.repository

import kotlinx.coroutines.delay
import tech.fika.droidkaigi.entities.Monster

class MockMonsterRepository(
    private val throwErrorRate: Double? = null,
) : MonsterRepository {
    override suspend fun getMonsters(offset: Int, limit: Int): List<Monster> {
        // Delay to emulate API or database calls
        delay(2_000L)
        // Randomly throw error based on [throwErrorRate]
        if (throwErrorRate != null && (0..99).random() < throwErrorRate * 100) {
            throw Throwable("An error has occurred")
        }
        // Return random list of monsters
        return List(limit) { index ->
            val id = offset + index + 1
            val nameLength = (8..20).random()
            val name = buildString(nameLength) {
                repeat(nameLength) { append(('a'..'z').random()) }
            }
            val imageUrl = "https://github.com/fika-tech/DroidKaigiSample/raw/main/resources/${(1..30).random()}.png"

            Monster(id = id, name = name, imageUrl = imageUrl)
        }
    }
}
