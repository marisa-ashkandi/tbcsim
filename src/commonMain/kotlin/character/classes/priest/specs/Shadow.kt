package character.classes.priest.specs

import character.Spec
import character.SpecEpDelta

class Shadow : Spec() {
    override val name: String = "Shadow"
    override val epBaseStat: SpecEpDelta = spellPowerBase
    override val epStatDeltas: List<SpecEpDelta> = defaultCasterDeltas

    override fun redSocketEp(deltas: Map<String, Double>): Double {
        // 9 spell dmg
        return 9.0
    }

    override fun yellowSocketEp(deltas: Map<String, Double>): Double {
        // 9 spell dmg
        return 9.0
    }

    override fun blueSocketEp(deltas: Map<String, Double>): Double {
        // 9 spell dmg
        return 9.0
    }
}
