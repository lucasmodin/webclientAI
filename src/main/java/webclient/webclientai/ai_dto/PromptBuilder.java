package webclient.webclientai.ai_dto;

import webclient.webclientai.blizzard_dto.Item_dto.EquippedItemDTO;
import webclient.webclientai.blizzard_dto.Item_dto.ItemEffectDTO;
import webclient.webclientai.blizzard_dto.Item_dto.PrimaryStatsDTO;
import webclient.webclientai.blizzard_dto.Item_dto.SecondaryStatsDTO;
import webclient.webclientai.raiderio_dto.RaiderIOCharacterDTO;

import java.util.ArrayList;
import java.util.List;

public class PromptBuilder {

    public static String buildPrompt(RaiderIOCharacterDTO character, List<EquippedItemDTO> gear) {
        StringBuilder sb = new StringBuilder();

        //character overview
        sb.append("Character: ").append(character.getName())
                .append(" (").append(character.getActiveSpec()).append(" ").append(character.getCharacter_class()).append(") ")
                .append("from realm ").append(character.getRealm()).append(", region ").append(character.getRegion()).append("\n\n");

        //mythic+ Info
        var scoreSeason = character.getMythic_plus_scores_by_season() != null && !character.getMythic_plus_scores_by_season().isEmpty()
                ? character.getMythic_plus_scores_by_season().get(0)
                : null;

        if (scoreSeason != null) {
            sb.append("Mythic+ Score: ").append(scoreSeason.getScores().getAll()).append("\n");
        }

        var ranks = character.getMythic_plus_ranks();
        if (ranks != null) {
            sb.append("Ranks:\n");
            if (ranks.getOverall() != null)
                sb.append("  - Overall: World #").append(ranks.getOverall().getWorld()).append(", Region #").append(ranks.getOverall().getRegion()).append(", Realm #").append(ranks.getOverall().getRealm()).append("\n");
            if (ranks.getClassRanks() != null)
                sb.append("  - Class Rank: World #").append(ranks.getClassRanks().getWorld()).append(", Region #").append(ranks.getClassRanks().getRegion()).append(", Realm #").append(ranks.getClassRanks().getRealm()).append("\n");
            if (ranks.getClassDps() != null)
                sb.append("  - DPS Rank: World #").append(ranks.getClassDps().getWorld()).append(", Region #").append(ranks.getClassDps().getRegion()).append(", Realm #").append(ranks.getClassDps().getRealm()).append("\n");
            if (ranks.getClassHealer() != null)
                sb.append("  - Healer Rank: World #").append(ranks.getClassHealer().getWorld()).append(", Region #").append(ranks.getClassHealer().getRegion()).append(", Realm #").append(ranks.getClassHealer().getRealm()).append("\n");
            if (ranks.getClassTank() != null)
                sb.append("  - Tank Rank: World #").append(ranks.getClassTank().getWorld()).append(", Region #").append(ranks.getClassTank().getRegion()).append(", Realm #").append(ranks.getClassTank().getRealm()).append("\n");
        }

        sb.append("\nEquipped gear:\n\n");

        //gear
        for (EquippedItemDTO item : gear) {
            sb.append("- ").append(item.getSlot()).append(": ").append(item.getName())
                    .append(" (iLvl ").append(item.getItemLevel()).append(", ").append(item.getQuality()).append(")\n");

            PrimaryStatsDTO primary = item.getPrimaryStats();
            if (primary != null) {
                sb.append("  - Primary Stats: ");
                List<String> primaryStats = getStrings(primary);
                sb.append(String.join(", ", primaryStats)).append("\n");
            }

            SecondaryStatsDTO secondary = item.getSecondaryStats();
            if (secondary != null) {
                sb.append("  - Secondary Stats: ");
                List<String> secondaryStats = getStrings(secondary);
                sb.append(String.join(", ", secondaryStats)).append("\n");
            }

            List<ItemEffectDTO> effects = item.getEquipEffects();
            if (effects != null && !effects.isEmpty()) {
                sb.append("  - Effects:\n");
                for (ItemEffectDTO effect : effects) {
                    sb.append("    * ").append(effect.getSpellName()).append(": ").append(effect.getDescription()).append("\n");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }


    public static String buildGearOnly(List<EquippedItemDTO> gear) {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipped Gear:\n\n");

        for (EquippedItemDTO item : gear) {
            sb.append("- ").append(item.getSlot()).append(": ").append(item.getName())
                    .append("(iLvl ").append(item.getItemLevel()); //maybe not necessary to fetch item quality, as

            PrimaryStatsDTO primary = item.getPrimaryStats();
            if(primary != null) {
                sb.append(", Primary Stats: ");
                List<String> primaryStats = getStrings(primary);
                sb.append(String.join(", ", primaryStats)).append("\n");
            }

            SecondaryStatsDTO secondary = item.getSecondaryStats();
            if(secondary != null) {
                sb.append(", Secondary Stats: ");
                List<String> secondaryStats = getStrings(secondary);
                sb.append(String.join(", ", secondaryStats)).append("\n");
            }

            List<ItemEffectDTO> effects = item.getEquipEffects();
            if(effects != null && !effects.isEmpty()) {
                sb.append("  - Effects:\n");
                for (ItemEffectDTO effect : effects) {
                    sb.append("    * ").append(effect.getSpellName()).append(": ").append(effect.getDescription()).append("\n");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String buildMythicPlusPrompt(RaiderIOCharacterDTO character, List<EquippedItemDTO> gear) {
        StringBuilder sb = new StringBuilder();

        sb.append("Character: ").append(character.getName())
                .append(" (").append(character.getActiveSpec()).append(" ").append(character.getCharacter_class()).append(") ");

        //to get mythic+ score
        var season = character.getMythic_plus_scores_by_season();
        if (season != null) {
            var s = season.get(0);
            sb.append("Mythic+ Score: ").append(s.getScores().getAll()).append("\n");
        }
        //to fetch mythic+ rank
        var ranks = character.getMythic_plus_ranks();
        if (ranks != null) {
            sb.append("Mythic+ Ranks:\n");
            if(ranks.getOverall() != null)
                sb.append(" - Overall: World #").append(ranks.getOverall().getWorld()).append(ranks.getOverall().getRegion())
                        .append(", Region #").append(ranks.getOverall().getRealm()).append("\n");
            if(ranks.getClassRanks() != null) {
                sb.append(" - Class Rank: World #").append(ranks.getClassRanks().getWorld()).append("\n");
            }
        }
        //for gear context
        sb.append("\nEquipped gear:\n\n").append(buildGearOnly(gear));
        return sb.toString();
    }

    public static String buildGeneralPrompt(RaiderIOCharacterDTO character, List<EquippedItemDTO> gear) {
        StringBuilder sb = new StringBuilder();

        sb.append("Character overview:\n")
                .append("Name: ").append(character.getName()).append("\n")
                .append("Spec: ").append(character.getActiveSpec()).append("\n")
                .append("Class: ").append(character.getCharacter_class()).append("\n")
                .append("Realm: ").append(character.getRealm()).append("\n")
                .append("Region: ").append(character.getRegion()).append("\n");

        // Score, if available
        var season = character.getMythic_plus_scores_by_season();
        if (season != null && !season.isEmpty()) {
            var s = season.get(0);
            sb.append("Current Mythic+ Score: ").append(s.getScores().getAll()).append("\n");
        }

        // Short gear section
        sb.append("\nEquipped Gear (Summary):\n");
        for (EquippedItemDTO item : gear) {
            sb.append("- ").append(item.getSlot()).append(": ").append(item.getName())
                    .append(" (iLvl ").append(item.getItemLevel()).append(")\n");
        }

        return sb.toString();
    }











    //method to extract secondary stats from item
    private static List<String> getStrings(SecondaryStatsDTO secondary) {
        List<String> secondaryStats = new ArrayList<>();
        if(secondary.getCrit() != null) secondaryStats.add("Crit +" + secondary.getCrit());
        if(secondary.getMastery() != null) secondaryStats.add("Mastery +" + secondary.getMastery());
        if(secondary.getHaste() != null) secondaryStats.add("Haste +" + secondary.getHaste());
        if(secondary.getVersatility() != null) secondaryStats.add("Versatility +" + secondary.getVersatility());
        if(secondary.getAvoidance() != null) secondaryStats.add("Avoidance +" + secondary.getAvoidance());
        if(secondary.getSpeed() != null) secondaryStats.add("Speed +" + secondary.getSpeed());
        if(secondary.getLeech() != null) secondaryStats.add("Leech +" + secondary.getLeech());
        return secondaryStats;
    }

    //method to extract primary stats from item
    private static List<String> getStrings(PrimaryStatsDTO primary) {
        List<String> primaryStats = new ArrayList<>();
        if(primary.getStrength() != null) primaryStats.add("Strength +" + primary.getStrength());
        if(primary.getAgility() != null) primaryStats.add("Agility +" + primary.getAgility());
        if(primary.getIntellect() != null) primaryStats.add("Intellect +" + primary.getIntellect());
        //stamina is debatable to send in the prompt as dps dont really need it, but tanks do
        if(primary.getStamina() != null) primaryStats.add("Stamina +" + primary.getStamina());
        return primaryStats;
    }
}

