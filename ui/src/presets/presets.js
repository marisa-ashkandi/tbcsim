import React, { useState } from 'react';
import { Col, Dropdown, Row } from 'rsuite';
import _ from 'lodash';

import { classes } from '../data/constants';

import hunterBmPreraid from './samples/hunter_bm_preraid.yml'
import hunterBmPhase1 from './samples/hunter_bm_phase1.yml'
import hunterSurvPreraid from './samples/hunter_surv_preraid.yml'
import hunterSurvPhase1 from './samples/hunter_surv_phase1.yml'
import mageArcanePreraid from './samples/mage_arcane_preraid.yml'
import mageArcanePhase1 from './samples/mage_arcane_phase1.yml'
import mageFirePreraid from './samples/mage_fire_preraid.yml'
import mageFirePhase1 from './samples/mage_fire_phase1.yml'
import mageFrostPreraid from './samples/mage_frost_preraid.yml'
import mageFrostPhase1 from './samples/mage_frost_phase1.yml'
import priestSmitePhase1 from './samples/priest_smite_phase1.yml'
import priestShadowPreraid from './samples/priest_shadow_preraid.yml'
import priestShadowPhase1 from './samples/priest_shadow_phase1.yml'
import rogueAssassinationPreraid from './samples/rogue_assassination_preraid.yml'
import rogueAssassinationPhase1 from './samples/rogue_assassination_phase1.yml'
import rogueCombatPreraid from './samples/rogue_combat_preraid.yml'
import rogueCombatPhase1 from './samples/rogue_combat_phase1.yml'
import shamanElePreraid from './samples/shaman_ele_preraid.yml'
import shamanElePhase1 from './samples/shaman_ele_phase1.yml'
import shamanEnhSubElePreraid from './samples/shaman_enh_subele_preraid.yml'
import shamanEnhSubElePhase1 from './samples/shaman_enh_subele_phase1.yml'
import shamanEnhSubRestoPreraid from './samples/shaman_enh_subresto_preraid.yml'
import shamanEnhSubRestoPhase1 from './samples/shaman_enh_subresto_phase1.yml'
import shamanEnhSubRestoPreraidAnniMh from './samples/shaman_enh_subresto_preraid_annihilator_mh.yml'
import shamanEnhSubRestoPhase1AnniMh from './samples/shaman_enh_subresto_phase1_annihilator_mh.yml'
import warlockAfflictionRuinPreraid from './samples/warlock_affliction_ruin_preraid.yml'
import warlockAfflictionRuinPhase1 from './samples/warlock_affliction_ruin_phase1.yml'
import warlockDestructionFirePreraid from './samples/warlock_destruction_fire_preraid.yml'
import warlockDestructionFirePhase1 from './samples/warlock_destruction_fire_phase1.yml'
import warlockDestructionShadowPreraid from './samples/warlock_destruction_shadow_preraid.yml'
import warlockDestructionShadowPhase1 from './samples/warlock_destruction_shadow_phase1.yml'
import warriorArmsPreraid from './samples/warrior_arms_preraid.yml'
import warriorArmsPhase1 from './samples/warrior_arms_phase1.yml'
import warriorFuryPreraid from './samples/warrior_fury_preraid.yml'
import warriorFuryPhase1 from './samples/warrior_fury_phase1.yml'

import * as tbcsim from 'tbcsim';

const presets = {
  hunter: {
    preraid: [
      hunterBmPreraid,
      hunterSurvPreraid
    ],
    phase1: [
      hunterBmPhase1,
      hunterSurvPhase1
    ]
  },
  mage: {
    preraid: [
      mageArcanePreraid,
      mageFirePreraid,
      mageFrostPreraid
    ],
    phase1: [
      mageArcanePhase1,
      mageFirePhase1,
      mageFrostPhase1
    ]
  },
  priest: {
    preraid: [
      priestShadowPreraid,
    ],
    phase1: [
      priestShadowPhase1,
      priestSmitePhase1
    ]
  },
  rogue: {
    preraid: [
      rogueAssassinationPreraid,
      rogueCombatPreraid
    ],
    phase1: [
      rogueAssassinationPhase1,
      rogueCombatPhase1
    ]
  },
  shaman: {
    preraid: [
      shamanElePreraid,
      shamanEnhSubElePreraid,
      shamanEnhSubRestoPreraid,
      shamanEnhSubRestoPreraidAnniMh
    ],
    phase1: [
      shamanElePhase1,
      shamanEnhSubElePhase1,
      shamanEnhSubRestoPhase1,
      shamanEnhSubRestoPhase1AnniMh
    ]
  },
  warlock: {
    preraid: [
      warlockDestructionFirePreraid,
      warlockDestructionShadowPreraid,
      warlockAfflictionRuinPreraid
    ],
    phase1: [
      warlockDestructionFirePhase1,
      warlockDestructionShadowPhase1,
      warlockAfflictionRuinPhase1
    ]
  },
  warrior: {
    preraid: [
      warriorArmsPreraid,
      warriorFuryPreraid
    ],
    phase1: [
      warriorArmsPhase1,
      warriorFuryPhase1
    ]
  }
}

function RaceSelect({ character, dispatch }) {
  if(!character || !character.class) return null;

  const classData = classes[character.class.toLowerCase()]
  const racesForClass = classData && classData.races;
  if(!racesForClass) return null;

  function onSelect(race) {
    dispatch({ type: 'character.race', value: race })
  }

  return (
    <>
      <Dropdown title="Race">
        {racesForClass.map(race => {
          return <Dropdown.Item key={race} eventKey={race} onSelect={onSelect}>{race}</Dropdown.Item>
        })}
      </Dropdown>
      <span>{character.race}</span>
    </>
  );
}

function PhaseSelect({ phase, dispatch }) {
  if(phase == null) return null;

  const allPhases = [1, 2, 3, 4, 5]

  function onSelect(phase) {
    dispatch({ type: 'phase', value: phase })
  }

  return (
    <>
      <Dropdown title="Phase">
        {allPhases.map(phase => {
          return <Dropdown.Item key={phase} eventKey={phase} onSelect={onSelect}>{phase}</Dropdown.Item>
        })}
      </Dropdown>
      <span>{phase}</span>
    </>
  );
}

function EpSelect({ epCategoryKey, dispatch }) {
  if(epCategoryKey == null) return null;

  const allEpCategories = [{
    name: 'Pre-raid',
    key: 'preraid'
  },{
    name: 'Phase 1',
    key: 'phase1'
  }]
  const epCategoryEntry = allEpCategories.find(epc => epc.key == epCategoryKey)
  if(epCategoryEntry == null) return null;

  const epCategoryName = epCategoryEntry.name;

  function onSelect(epCategory) {
    dispatch({ type: 'character.epCategory', value: epCategory })
  }

  return (
    <>
      <Dropdown title="EP Category">
        {allEpCategories.map(epCategory => {
          return <Dropdown.Item key={epCategory.key} eventKey={epCategory.key} onSelect={onSelect}>{epCategory.name}</Dropdown.Item>
        })}
      </Dropdown>
      <span>{epCategoryName}</span>
    </>
  );
}

export default ({ character, phase, dispatch }) => {
  const [isOpen, setIsOpen] = useState(false);

  function onSelect(key, evt) {
    const [klass, category, idx] = key.split('-')
    const clone = JSON.parse(JSON.stringify(presets[klass][category][idx]))
    clone.gear = _.mapValues(clone.gear, rawItem => {
      // TODO: This method is code generator internals, and possibly fragile
      let item = tbcsim.data.Items.byName.get_35(rawItem.name)
      if(!item) {
         return;
      }
      item = item()

      if(rawItem.gems) {
        rawItem.gems.forEach((gemName, idx) => {
          const gem = tbcsim.data.Items.byName.get_35(gemName)()
          item.sockets[idx].gem = gem
        })
      }

      if(rawItem.enchant) {
        const enchant = tbcsim.data.Enchants.byName.get_35(rawItem.enchant)
        if(enchant) {
          item.enchant = enchant(item)
        }
      }

      if(rawItem.tempEnchant) {
        const tmpEnchant = tbcsim.data.TempEnchants.byName.get_35(rawItem.tempEnchant)
        if(tmpEnchant) {
          item.tempEnchant = tmpEnchant(item)
        }
      }

      return item
    })
    dispatch({ type: 'loadCharacterPreset', value: clone })
    setIsOpen(false)

    // Track preset + class interest
    if(window.gtag) {
      window.gtag('event', 'load', {
        'event_category': 'characterPreset',
        'event_label': clone.description || 'unknown preset',
        'event_value': 1
      });
    }
  }

  function presetsFor(klass, category) {
    return <>
      {(presets[klass][category] || []).filter(it => it && (it.epCategory === category)).map((p, idx) => {
        const key = `${klass}-${category}-${idx}`;
        return <Dropdown.Item key={key} eventKey={key} onSelect={onSelect}>{p.description}</Dropdown.Item>
      })}
    </>
  }

  return (
    <Row style={{padding: '10px 0px', fontWeight: 800}}>
      <Col style={{ display: 'inline-block' }}>
        <Dropdown title='Presets'
          onMouseEnter={() => setIsOpen(true)}
          onMouseLeave={() => setIsOpen(false)}
          open={isOpen}
        >
          <Dropdown.Menu title='Hunter'>
            <Dropdown.Menu key={'phase1'} title='Phase 1'>
              {presetsFor('hunter', 'phase1')}
            </Dropdown.Menu>
            <Dropdown.Menu key={'preraid'} title='Pre-raid'>
              {presetsFor('hunter', 'preraid')}
            </Dropdown.Menu>
          </Dropdown.Menu>
          <Dropdown.Menu title='Mage'>
            <Dropdown.Menu key={'phase1'} title='Phase 1'>
              {presetsFor('mage', 'phase1')}
            </Dropdown.Menu>
            <Dropdown.Menu key={'preraid'} title='Pre-raid'>
              {presetsFor('mage', 'preraid')}
            </Dropdown.Menu>
          </Dropdown.Menu>
          <Dropdown.Menu title='Rogue'>
            <Dropdown.Menu key={'phase1'} title='Phase 1'>
              {presetsFor('rogue', 'phase1')}
            </Dropdown.Menu>
            <Dropdown.Menu key={'preraid'} title='Pre-raid'>
              {presetsFor('rogue', 'preraid')}
            </Dropdown.Menu>
          </Dropdown.Menu>
          <Dropdown.Menu title='Shaman'>
            <Dropdown.Menu key={'phase1'} title='Phase 1'>
              {presetsFor('shaman', 'phase1')}
            </Dropdown.Menu>
            <Dropdown.Menu key={'preraid'} title='Pre-raid'>
              {presetsFor('shaman', 'preraid')}
            </Dropdown.Menu>
          </Dropdown.Menu>
          <Dropdown.Menu title='Warlock'>
            <Dropdown.Menu key={'phase1'} title='Phase 1'>
              {presetsFor('warlock', 'phase1')}
            </Dropdown.Menu>
            <Dropdown.Menu key={'preraid'} title='Pre-raid'>
              {presetsFor('warlock', 'preraid')}
            </Dropdown.Menu>
          </Dropdown.Menu>
          <Dropdown.Menu title='Warrior'>
            <Dropdown.Menu key={'phase1'} title='Phase 1'>
              {presetsFor('warrior', 'phase1')}
            </Dropdown.Menu>
            <Dropdown.Menu key={'preraid'} title='Pre-raid'>
              {presetsFor('warrior', 'preraid')}
            </Dropdown.Menu>
          </Dropdown.Menu>
        </Dropdown>

        {character && character.description ?
          <span>{character.description}</span> :
          <span>Please select a preset</span>
        }
      </Col>
      <Col style={{ display: 'inline-block', marginLeft: 10 }}>
        <RaceSelect character={character} dispatch={dispatch} />
      </Col>
      <Col style={{ display: 'inline-block', marginLeft: 10 }}>
        <PhaseSelect phase={phase} dispatch={dispatch} />
      </Col>
      <Col style={{ display: 'inline-block', marginLeft: 10 }}>
        <EpSelect epCategoryKey={character && character.epCategory} dispatch={dispatch} />
      </Col>
    </Row>
  )
}
