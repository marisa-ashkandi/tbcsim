package character.classes.mage

import character.*
import data.model.Item

class Mage(talents: Map<String, Talent>, spec: Spec) : Class(talents, spec) {
    override val baseStats: Stats = Stats(
        agility = 154,
        intellect = 180,
        strength = 112,
        stamina = 154,
        spirit = 199
    )
    override val buffs: List<Buff>
        get() = TODO("Not yet implemented")

    override fun abilityFromString(name: String, item: Item?): Ability? {
        TODO("Not yet implemented")
    }

    override fun talentFromString(name: String, ranks: Int): Talent? {
        TODO("Not yet implemented")
    }

    override val resourceTypes: List<Resource.Type>
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
    override val baseMana: Int = 2241
    override val baseSpellCritChance: Double = 0.91
    override val dodgePctPerAgility: Double = 1.0 / 25.0
    override val baseDodgePct: Double = 3.45
}
