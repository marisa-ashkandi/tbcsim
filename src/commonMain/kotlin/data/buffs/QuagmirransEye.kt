package data.buffs

import character.Ability
import character.Buff
import character.Proc
import character.Stats
import data.model.Item
import sim.Event
import sim.SimParticipant

class QuagmirransEye : Buff() {

    override val name: String = "Quagmirran's Eye (static)"
    override val durationMs: Int = -1
    override val hidden: Boolean = true

    val proc = object : Proc() {
        // TODO: Does this trigger on DoTs
        override val triggers: List<Trigger> = listOf(
            Trigger.SPELL_HIT,
            Trigger.SPELL_CRIT
        )
        override val type: Type = Type.PERCENT
        override fun percentChance(sp: SimParticipant): Double = 10.0
        override fun cooldownMs(sp: SimParticipant): Int = 45000

        val buff = object : Buff() {
            override val id: Int = 33297
            override val name: String = "Quagmirran's Eye"
            override val durationMs: Int = 6000

            override fun modifyStats(sp: SimParticipant): Stats? {
                return Stats(spellHasteRating = 320.0)
            }
        }
        override fun proc(sp: SimParticipant, items: List<Item>?, ability: Ability?, event: Event?) {
            sp.addBuff(buff)
        }
    }

    override fun procs(sp: SimParticipant): List<Proc> = listOf(proc)
}
