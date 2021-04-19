package character.classes.priest

import character.*
import data.model.Item

class Priest(talents: Map<String, Talent>, spec: Spec) : Class(talents, spec) {
    override val baseStats: Stats = Stats(
        agility = 184,
        intellect = 180,
        strength = 146,
        stamina = 154,
        spirit = 135
    )
    override val buffs: List<Buff>
        get() = TODO("Not yet implemented")

    override fun abilityFromString(name: String, item: Item?): Ability? {
        TODO("Not yet implemented")
    }

    override fun talentFromString(name: String, ranks: Int): Talent? {
        TODO("Not yet implemented")
    }

    override val resourceType: MutableList<Resource.Type>
        get() = TODO("Not yet implemented")
    override val canDualWield: Boolean
        get() = TODO("Not yet implemented")
    override val attackPowerFromAgility: Int
        get() = TODO("Not yet implemented")
    override val attackPowerFromStrength: Int
        get() = TODO("Not yet implemented")
    override val critPctPerAgility: Double
        get() = TODO("Not yet implemented")
    override val rangedAttackPowerFromAgility: Int
        get() = TODO("Not yet implemented")
    override val baseMana: Int = 2620
    override val baseSpellCritChance: Double
        get() = TODO("Not yet implemented")
    override val dodgePctPerAgility: Double = 1.0 / 25.0
    override val baseDodgePct: Double = 3.18
}
