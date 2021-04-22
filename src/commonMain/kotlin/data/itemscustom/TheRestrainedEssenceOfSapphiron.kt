package data.itemscustom

import character.Buff
import character.CharacterType
import character.Stats
import data.Constants
import data.buffs.Buffs
import data.model.Item
import data.model.ItemSet
import data.model.Socket
import data.model.SocketBonus
import sim.SimParticipant

class TheRestrainedEssenceOfSapphiron : Item() {
    override var isAutoGenerated: Boolean = false
    override var id: Int = 23046
    override var name: String = "The Restrained Essence of Sapphiron"
    override var itemLevel: Int = 90
    override var quality: Int = 4
    override var icon: String = "inv_trinket_naxxramas06.jpg"
    override var inventorySlot: Int = 12
    override var itemSet: ItemSet? = null
    override var itemClass: Constants.ItemClass? = Constants.ItemClass.ARMOR
    override var itemSubclass: Constants.ItemSubclass? = Constants.ItemSubclass.MISC
    override var minDmg: Double = 0.0
    override var maxDmg: Double = 0.0
    override var speed: Double = 0.0
    override var stats: Stats = Stats(
        spellDamage = 40
    )
    override var sockets: Array<Socket> = arrayOf()
    override var socketBonus: SocketBonus? = null

    override val buffs: List<Buff> by lazy {
        listOfNotNull(
            Buffs.byIdOrName(28779, "The Restrained Essence of Sapphiron", this)
        )
    }


}