package character.classes.warlock.talents

import character.Buff
import character.Stats
import character.Talent
import sim.SimIteration

class FelIntellect(currentRank: Int) : Talent(currentRank) {
    companion object {
        const val name = "Fel Intellect"
    }
    override val name: String = Companion.name
    override val maxRank: Int = 3

    val intBuff = object : Buff() {
        override val name: String = Companion.name
        override val durationMs: Int = -1
        override val hidden: Boolean = true

        override fun modifyStats(sim: SimIteration): Stats {
            return Stats(intellectMultiplier = 1.0 + (0.01 * currentRank))
        }
    }

    override fun buffs(sim: SimIteration): List<Buff> = listOf(intBuff)
}