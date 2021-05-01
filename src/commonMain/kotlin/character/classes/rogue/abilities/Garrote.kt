package character.classes.rogue.abilities

import character.*
import mechanics.Melee
import sim.Event
import sim.SimParticipant
import data.Constants
import data.model.Item
import character.classes.rogue.talents.*
import character.classes.rogue.debuffs.*
import character.classes.rogue.buffs.*
import mechanics.Rating
import mechanics.Spell

class Garrote : Ability() {
    companion object {
        const val name = "Garrote"
    }

    override val id: Int = 26884
    override val name: String = Companion.name

    override fun gcdMs(sp: SimParticipant): Int = sp.physicalGcd().toInt()

    override fun resourceType(sp: SimParticipant): Resource.Type = Resource.Type.ENERGY
    override fun resourceCost(sp: SimParticipant): Double {
        val dd = sp.character.klass.talents[DirtyDeeds.name] as DirtyDeeds?
        val reduction = dd?.energyReduction() ?: 0.0
        return 50.0 - reduction
    }

    fun available(sp: SimParticipant): Boolean {
        val inStealth = sp.buffs[Stealth.name] != null
        return inStealth && super.available(sp)
    }

    override fun cast(sp: SimParticipant) {
        // can't be dodged, parried or blocked. parry/block implied by sp.sim.opts.allowParryAndBlock
        val result = Melee.attackRoll(sp, 0.0, null, isWhiteDmg = false, noDodgeAllowed = true)

        val event = Event(
            eventType = Event.Type.DAMAGE,
            damageType = Constants.DamageType.PHYSICAL,
            abilityName = name,
            amount = 0.0,
            result = result.second,
        )
        sp.logEvent(event)

        if(result.second != Event.Result.MISS) {
            sp.sim.target.addDebuff(GarroteDot(sp))
            sp.addResource(1, Resource.Type.COMBO_POINT, name)
        }

        val triggerTypes = when(result.second) {
            Event.Result.HIT -> listOf(Proc.Trigger.MELEE_YELLOW_HIT)
            Event.Result.CRIT -> listOf(Proc.Trigger.MELEE_YELLOW_HIT) // can't crit
            Event.Result.MISS -> listOf(Proc.Trigger.MELEE_MISS)
            Event.Result.PARRY -> listOf(Proc.Trigger.MELEE_PARRY)
            Event.Result.BLOCK -> listOf(Proc.Trigger.MELEE_YELLOW_HIT) 
            Event.Result.BLOCKED_CRIT -> listOf(Proc.Trigger.MELEE_YELLOW_HIT) // can't crit
            else -> null
        }

        if(triggerTypes != null) {
            sp.fireProc(triggerTypes, null, this, event)
        }
    }
}
