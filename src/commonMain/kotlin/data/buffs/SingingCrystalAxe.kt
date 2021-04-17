package data.buffs

import character.*
import data.model.Item
import sim.Event
import sim.SimParticipant

class SingingCrystalAxe(val sourceItem: Item) : ItemBuff(listOf(sourceItem)) {
    override val id: Int = 38282
    override val name: String = "Singing Crystal Axe (static)"
    override val durationMs: Int = -1
    override val hidden: Boolean = true

    val proc = object : ItemProc(listOf(sourceItem)) {
        override val triggers: List<Trigger> = listOf(
            Trigger.MELEE_AUTO_HIT,
            Trigger.MELEE_AUTO_CRIT,
            Trigger.MELEE_WHITE_HIT,
            Trigger.MELEE_WHITE_CRIT,
            Trigger.MELEE_YELLOW_HIT,
            Trigger.MELEE_YELLOW_CRIT,
            Trigger.MELEE_BLOCK,
            Trigger.MELEE_GLANCE,
        )

        override val type: Type = Type.PPM
        override val ppm: Double = 1.0
        //TODO:Confirm proc rate, based on wowwiki


        val buff = object : Buff() {
            override val name: String = "Singing Crystal Axe"
            override val durationMs: Int = 10000

            override fun modifyStats(sp: SimParticipant): Stats {
                return Stats(physicalHasteRating = 400.00)
            }
        }

        override fun proc(sp: SimParticipant, items: List<Item>?, ability: Ability?, event: Event?) {
            sp.addBuff(buff)
        }
    }

    override fun procs(sp: SimParticipant): List<Proc> = listOf(proc)
}
