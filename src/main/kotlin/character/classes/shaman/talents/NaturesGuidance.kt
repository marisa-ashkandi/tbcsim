package character.classes.shaman.talents

import character.Buff
import character.Proc
import character.Stats
import character.Talent
import mechanics.Rating
import sim.SimIteration

class NaturesGuidance(currentRank: Int) : Talent(currentRank) {
    companion object {
        const val name: String = "Nature's Guidance"
    }

    override val name: String = Companion.name
    override val maxRank: Int = 3

    override fun buffs(sim: SimIteration): List<Buff> {
        return listOf(
            object : Buff() {
                override val name: String = Companion.name
                override val durationMs: Int = -1
                override val hidden: Boolean = true

                override fun modifyStats(sim: SimIteration, stats: Stats): Stats {
                    val modifier = currentRank
                    val physicalHitRating = modifier * Rating.meleeHitPerPct
                    val spellHitRating = modifier * Rating.spellHitPerPct
                    return stats.add(
                        Stats(
                            physicalHitRating = physicalHitRating,
                            spellHitRating = spellHitRating
                        )
                    )
                }

                override fun procs(sim: SimIteration): List<Proc> = listOf()
            }
        )
    }

    override fun procs(sim: SimIteration): List<Proc> = listOf()
}
