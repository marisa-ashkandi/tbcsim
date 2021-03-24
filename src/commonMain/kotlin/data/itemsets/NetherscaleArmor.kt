package data.itemsets

import character.Buff
import character.Stats
import data.model.ItemSet
import sim.SimParticipant

class NetherscaleArmor : ItemSet() {
    override val id: Int = 616

    val threeBuff = object : Buff() {
        override val name: String = "Netherscale Armor (3 set)"
        override val durationMs: Int = -1

        override fun modifyStats(sp: SimParticipant): Stats {
            return Stats(physicalHitRating = 20.0)
        }
    }

    override val bonuses: List<Bonus> = listOf(
        Bonus(id, 3, threeBuff)
    )
}
