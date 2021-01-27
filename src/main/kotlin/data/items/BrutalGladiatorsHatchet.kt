package `data`.items

import `data`.Constants
import `data`.buffs.Buffs
import `data`.model.Item
import `data`.model.ItemSet
import `data`.model.Socket
import `data`.model.SocketBonus
import character.Buff
import character.Stats
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.collections.List

public class BrutalGladiatorsHatchet : Item() {
  public override var isAutoGenerated: Boolean = true

  public override var name: String = "Brutal Gladiator's Hatchet"

  public override var itemLevel: Int = 154

  public override var itemSet: ItemSet? = null

  public override var itemClass: Constants.ItemClass? = Constants.ItemClass.WEAPON

  public override var itemSubclass: Constants.ItemSubclass? = Constants.ItemSubclass.AXE_1H

  public override var minDmg: Double = 196.0

  public override var maxDmg: Double = 365.0

  public override var speed: Double = 2600.0

  public override var stats: Stats = Stats(
      stamina = 31,
      physicalCritRating = 20.0,
      physicalHitRating = 9.0,
      spellCritRating = 20.0,
      spellHitRating = 9.0
      )

  public override var sockets: List<Socket> = listOf()

  public override var socketBonus: SocketBonus? = null

  public override var buffs: List<Buff> = listOfNotNull(
      Buffs.byIdOrName(15809, "Attack Power 42", this),
      Buffs.byIdOrName(47041, "Armor Penetration 42", this)
      )
}