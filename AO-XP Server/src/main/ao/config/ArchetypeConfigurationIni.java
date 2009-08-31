package ao.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import ao.domain.character.archetype.Archetype;

public class ArchetypeConfigurationIni implements ArchetypeConfiguration {
	
	private static final String CONFIG_FILE_PATH = "Dat/balance.ini";
	private static final String BLOCK_POWER_HEADER = "MODESCUDO";
	private static final String EVASION_HEADER = "MODEVASION";
	private static final String MELEE_ACCURACY_HEADER = "MODATAQUEARMAS";
	private static final String MELEE_DAMAGE_HEADER = "MODDAÑOARMAS";
	private static final String RANGED_ACCURACY_HEADER = "MODATAQUEPROYECTILES";
	private static final String RANGED_DAMAGE_HEADER = "MODDAÑOPROYECTILES";
	private static final String WRESTLING_DAMAGE_HEADER = "MODDAÑOWRESTLING";
	
	private Ini config;
	
	/**
	 * Constructor, loads the config file.
	 * @throws InvalidFileFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArchetypeConfigurationIni() throws Exception {
		config = new Ini(new FileInputStream(CONFIG_FILE_PATH));
	}
	
	/**
	 * Retrieves the archetype's name in the config file.
	 * @param archetype The archetype.
	 * @return The archetype's name.
	 */
	private String getArchetypeName(Class<? extends Archetype> archetype) {
		
		// Esto está así cableadísimo porque es lo que nos obliga a hacer el ini actual del ao.
		String archetypeName = archetype.getSimpleName().replace( "Archetype", "");
		
		if (archetypeName == "Bard") {
			return "Bardo";
		} else if (archetypeName == "Cleric") {
			return "Clerigo";
		} else if (archetypeName == "Druid") {
			return "Druida";
		} else if (archetypeName == "Fisher") {
			return "Pescador";
		} else if (archetypeName == "Lumberjack") {
			return "Leñador";
		} else if (archetypeName == "Mage") {
			return "Mago";
		} else if (archetypeName == "Miner") {
			return "Minero";
		} else if (archetypeName == "Pirate") {
			return "Pirata";
		}
		
		return null;
	}
	
	@Override
	public float getBlockPowerModifier(Class<? extends Archetype> archetype) {
		return Float.valueOf(config.get(BLOCK_POWER_HEADER).get(getArchetypeName(archetype)));
	}
	
	@Override
	public float getEvasionModifier(Class<? extends Archetype> archetype) {
		return Float.valueOf(config.get(EVASION_HEADER).get(getArchetypeName(archetype)));
	}
	
	@Override
	public float getMeleeAccuracyModifier(Class<? extends Archetype> archetype) {
		return Float.valueOf(config.get(MELEE_ACCURACY_HEADER).get(getArchetypeName(archetype)));
	}
	
	@Override
	public float getMeleeDamageModifier(Class<? extends Archetype> archetype) {
		return Float.valueOf(config.get(MELEE_DAMAGE_HEADER).get(getArchetypeName(archetype)));
	}
	
	@Override
	public float getRangedAccuracyModifier(Class<? extends Archetype> archetype) {
		return Float.valueOf(config.get(RANGED_ACCURACY_HEADER).get(getArchetypeName(archetype)));
	}
	
	@Override
	public float getRangedDamageModifier(Class<? extends Archetype> archetype) {
		return Float.valueOf(config.get(RANGED_DAMAGE_HEADER).get(getArchetypeName(archetype)));
	}
	
	@Override
	public float getWrestlingDamageModifier(Class<? extends Archetype> archetype) {
		return Float.valueOf(config.get(WRESTLING_DAMAGE_HEADER).get(getArchetypeName(archetype)));
	}
}
