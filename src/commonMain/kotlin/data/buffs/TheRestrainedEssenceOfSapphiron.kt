package data.buffs

import character.Ability
import character.Buff
import character.Stats
import sim.SimParticipant

class TheRestrainedEssenceOfSapphiron : Buff() {

    override val id: Int = 28779
    override val name: String = "The Restrained Essence of Sapphiron (static)"
    override val durationMs: Int = -1
    override val hidden: Boolean = true

    val buffDurationMs = 20000
    val buff = object : Buff() {
        override val name: String  = "The Restrained Essence of Sapphiron"
        override val durationMs: Int = buffDurationMs

        override fun modifyStats(sp: SimParticipant): Stats? {
            return Stats(
                spellDamage = 130
            )
        }
    }

    val ability = object : Ability() {
        override val id: Int = 28779
        override val name: String = "The Restrained Essence of Sapphiron"
        override fun gcdMs(sp: SimParticipant): Int = 0
        override fun cooldownMs(sp: SimParticipant): Int = 120000

        override fun trinketLockoutMs(sp: SimParticipant): Int = buffDurationMs

        override fun cast(sp: SimParticipant) {
            sp.addBuff(buff)
        }
    }

    override fun activeTrinketAbility(sp: SimParticipant): Ability = ability
}
