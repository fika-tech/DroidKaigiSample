package tech.fika.droidkaigi.data.repository

import tech.fika.droidkaigi.entities.Monster

interface MonsterRepository {

    suspend fun getMonsters(offset: Int, limit: Int): List<Monster>
}