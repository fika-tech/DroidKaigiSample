package tech.fika.droidkaigi.data.repository

import kotlinx.coroutines.delay
import tech.fika.droidkaigi.entities.Monster

class MockMonsterRepository(
    private val throwInitialError: Boolean,
    private val throwPagingError: Boolean,
) : MonsterRepository {
    override suspend fun getMonsters(offset: Int, limit: Int): List<Monster> {
        delay(2_000L)
        if (throwInitialError || offset > 0 && throwPagingError) throw Throwable("An error has occured")
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
