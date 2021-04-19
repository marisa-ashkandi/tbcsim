package character.classes.druid

import character.*
import character.Class
import data.model.Item

class Druid(talents: Map<String, Talent>, spec: Spec)  : Class(talents, spec) {
    companion object {
        const val BEAR_FORM_BUFF_NAME = "Bear Form"
        const val DIRE_BEAR_FORM_BUFF_NAME = "Dire Bear Form"
        const val CAT_FORM_BUFF_NAME = "Cat Form"
    }
    override fun talentFromString(name: String, ranks: Int): Talent? {
        return null
    }

    override fun abilityFromString(name: String, item: Item?): Ability? {
        return null
    }

    override var baseStats: Stats = Stats(
        agility = 222,
        intellect = 180,
        strength = 108,
        stamina = 154,
        spirit = 135
    )
    override var buffs: List<Buff> = listOf()

    override val resourceType: MutableList<Resource.Type> = mutableListOf(Resource.Type.MANA)
    override var canDualWield: Boolean = false
    override var attackPowerFromAgility: Int = 0
    override var attackPowerFromStrength: Int = 0
    override val critPctPerAgility: Double = 0.0
    override val dodgePctPerAgility: Double = 1.0 / 14.7
    override val baseDodgePct: Double = -1.87
    override var rangedAttackPowerFromAgility: Int = 0

    override val baseMana: Int = 2370
    override val baseSpellCritChance: Double = 1.85
}
