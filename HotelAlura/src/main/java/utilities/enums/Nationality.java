package utilities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing various nationalities.
 */
@Getter
@AllArgsConstructor
public enum Nationality {
    AFGHAN("Afghan"),
    GERMAN("German"),
    ARAB("Arab"),
    ARGENTINE("Argentine"),
    AUSTRALIAN("Australian"),
    BELGIAN("Belgian"),
    BOLIVIAN("Bolivian"),
    BRAZILIAN("Brazilian"),
    CAMBODIAN("Cambodian"),
    CANADIAN("Canadian"),
    CHILEAN("Chilean"),
    CHINESE("Chinese"),
    COLOMBIAN("Colombian"),
    KOREAN("Korean"),
    COSTA_RICAN("Costa Rican"),
    CUBAN("Cuban"),
    DANISH("Danish"),
    ECUADORIAN("Ecuadorian"),
    EGYPTIAN("Egyptian"),
    SALVADORAN("Salvadoran"),
    SCOTTISH("Scottish"),
    SPANISH("Spanish"),
    AMERICAN("American"),
    ESTONIAN("Estonian"),
    ETHIOPIAN("Ethiopian"),
    FILIPINO("Filipino"),
    FINNISH("Finnish"),
    FRENCH("French"),
    WELSH("Welsh"),
    GREEK("Greek"),
    GUATEMALAN("Guatemalan"),
    HAITIAN("Haitian"),
    DUTCH("Dutch"),
    HONDURAN("Honduran"),
    INDONESIAN("Indonesian"),
    ENGLISH("English"),
    IRAQI("Iraqi"),
    IRANIAN("Iranian"),
    IRISH("Irish"),
    ISRAELI("Israeli"),
    ITALIAN("Italian"),
    JAPANESE("Japanese"),
    JORDANIAN("Jordanian"),
    LAOTIAN("Laotian"),
    LATVIAN("Latvian"),
    MALAYSIAN("Malaysian"),
    MOROCCAN("Moroccan"),
    MEXICAN("Mexican"),
    NICARAGUAN("Nicaraguan"),
    NORWEGIAN("Norwegian"),
    NEW_ZEALANDER("New Zealander"),
    PANAMANIAN("Panamanian"),
    PARAGUAYAN("Paraguayan"),
    PERUVIAN("Peruvian"),
    POLISH("Polish"),
    PORTUGUESE("Portuguese"),
    PUERTO_RICAN("Puerto Rican"),
    DOMINICAN("Dominican"),
    ROMANIAN("Romanian"),
    RUSSIAN("Russian"),
    SWEDISH("Swedish"),
    SWISS("Swiss"),
    THAI("Thai"),
    TAIWANESE("Taiwanese"),
    TURKISH("Turkish"),
    UKRAINIAN("Ukrainian"),
    URUGUAYAN("Uruguayan"),
    VENEZUELAN("Venezuelan"),
    VIETNAMESE("Vietnamese");

    private final String displayName;

    /**
     * Get an array of all display names for the nationalities.
     *
     * @return Array of display names
     */
    public static String[] getAllDisplayNames() {
        String[] displayNames = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            displayNames[i] = values()[i].getDisplayName();
        }
        return displayNames;
    }
}
