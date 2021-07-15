package character.classes.priest.abilities

import character.Ability
import character.Buff
import character.Proc
import character.classes.priest.buffs.PowerInfusion
import character.classes.priest.buffs.InnerFocus as InnerFocusBuff
import character.classes.priest.talents.InnerFocus as InnerFocusTalent
import character.classes.priest.talents.SurgeOfLight
import character.classes.priest.talents.FocusedPower
import character.classes.priest.talents.SearingLight
import character.classes.priest.talents.HolySpecialization
import character.classes.priest.talents.DivineFury
import data.Constants
import data.itemsets.IncarnateRegalia
import mechanics.Spell
import sim.Event
import sim.EventResult
import sim.EventType
import sim.SimParticipant

class Smite : Ability() {
    companion object {
        const val name: String = "Smite"
    }
    override val id: Int = 585
    override val name: String = Companion.name
    override fun gcdMs(sp: SimParticipant): Int = sp.spellGcd().toInt()

    val baseCastTimeMs = 2500
    override fun castTimeMs(sp: SimParticipant): Int {
        val divineFury: DivineFury? = sp.character.klass.talentInstance(DivineFury.name)
        val solProc = sp.buffs[SurgeOfLight.buffName]
        if (solProc != null) {
            return 1500
        }
        return ((baseCastTimeMs - (divineFury?.smiteHolyFireCastTimeReductionMs() ?: 0)) / sp.spellHasteMultiplier()).toInt()
    }

    val baseResourceCost = 385.0
    override fun resourceCost(sp: SimParticipant): Double {
        val innerFocusBuff = sp.buffs[InnerFocusBuff.name] as InnerFocusBuff?
        if(innerFocusBuff != null){
            return 0.0
        }
        
        val piBuff = sp.buffs[PowerInfusion.name] as PowerInfusion?
        val piMult = piBuff?.manaCostMultiplier() ?: 1.0

        return baseResourceCost * piMult        
    }

    val baseDamage = Pair(549.0, 616.0)
    val school = Constants.DamageType.HOLY
    val spellPowerCoeff = Spell.spellPowerCoeff(baseCastTimeMs)
    override fun cast(sp: SimParticipant) {
        val focusedPower: FocusedPower? = sp.character.klass.talentInstance(FocusedPower.name)
        val fpHit = focusedPower?.bonusSmiteMindBlastHitPct() ?: 0.0

        val hs: HolySpecialization? = sp.character.klass.talentInstance(HolySpecialization.name)
        val hsCrit = hs?.holySpellsCrit() ?: 0.0

        val innerFocusBuff = sp.buffs[InnerFocusBuff.name] as InnerFocusBuff?
        val ifCrit = innerFocusBuff?.critPct() ?: 0.0

        val solBuff = sp.buffs[SurgeOfLight.buffName]
        var crit = true  
        if(solBuff != null){
            crit = false
        }

        val searingLight: SearingLight? = sp.character.klass.talentInstance(SearingLight.name)
        val searingLightMultiplier = searingLight?.smiteHolyFireDamageMultiplier() ?: 1.0

        val damageRoll = Spell.baseDamageRoll(sp, baseDamage.first, baseDamage.second, school, spellPowerCoeff)

        // Check T4 set bonus
        val t4Bonus = sp.buffs[IncarnateRegalia.FOUR_SET_BUFF_NAME] != null
        val t4DmgIncrease = if(t4Bonus) { IncarnateRegalia.fourSetDmgMultiplierPct() } else 0.0

        val result = Spell.attackRoll(sp, damageRoll * (searingLightMultiplier + t4DmgIncrease), school, bonusCritChance = hsCrit + ifCrit, bonusHitChance = fpHit, canCrit = crit)

        val event = Event(
            eventType = EventType.DAMAGE,
            damageType = school,
            abilityName = name,
            amount = result.first,
            result = result.second,
        )
        sp.logEvent(event)

        // Inner Focus is not consumed by smite if Surge of Light is active
        if(solBuff == null){
            if(innerFocusBuff != null){
                sp.consumeBuff(innerFocusBuff)
            }
        }

        val triggerTypes = when(result.second) {
            EventResult.HIT -> listOf(Proc.Trigger.SPELL_HIT, Proc.Trigger.HOLY_DAMAGE_NON_PERIODIC, Proc.Trigger.SMITE_CAST)
            EventResult.CRIT -> listOf(Proc.Trigger.SPELL_CRIT, Proc.Trigger.HOLY_DAMAGE_NON_PERIODIC, Proc.Trigger.SMITE_CAST)
            EventResult.RESIST -> listOf(Proc.Trigger.SPELL_RESIST)
            else -> null
        }

        if(triggerTypes != null) {
            sp.fireProc(triggerTypes, listOf(), this, event)
        }
    }
}
