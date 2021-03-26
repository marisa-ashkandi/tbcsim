package data.gems

import character.Buff
import character.Stats
import data.model.Color
import data.model.Gem
import data.model.Quality
import data.model.Socket
import sim.SimParticipant
import kotlin.js.JsExport

@JsExport
class SwiftSkyfireDiamond : Gem(25894, "Swift Skyfire Diamond", "inv_misc_gem_diamond_07.jpg", null, Color.META, Quality.META) {
    val buff = object : Buff() {
        override val name: String = "Swift Skyfire Diamond"
        override val durationMs: Int = -1
        override val hidden: Boolean = true

        override fun modifyStats(sp: SimParticipant): Stats {
            return Stats(
                attackPower = 24,
                rangedAttackPower = 24
            )
        }
    }

    override fun metaActive(sockets: List<Socket>): Boolean {
        val byColor = socketsByColor(sockets)

        return byColor[Color.RED] ?: 0 >= 1 &&
                byColor[Color.YELLOW] ?: 0 >= 2
    }

    override var buffs: List<Buff> = listOf(buff)
}
