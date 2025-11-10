package menea.Fighters;

import java.io.File;
import menea.Tiles.Board;
import menea.Vanity.ImageLoader;


public class GameManager {

    private Fighter fighters[];
    private Board board;
    
    public GameManager() {
        fighters = new Fighter[3];
        board = new Board();
    }
    
     public Board getBoard() {
        return board;
    }

    // tenemos esta logica aca y no en comandos, por que hay que
    // comprobar quee las casillas, ataques, etc ...
    public boolean isValidFighter(String[] args) {
        // args [0] = name
        // args[1] = imageId
        // args[2] = attackType
        // args[3] = mapPercentage
        // args[4] = strength
        // args[5] = endurance
        // args[6] = sanity
        if (args.length != 7) return false;
        
        String name = args[0];
        String imageId = args[1];
        String attackType = args[2];
        String mapPercStr = args[3];
        String strengthStr = args[4];
        String enduranceStr = args[5];
        String sanityStr = args[6];
        
        if (!isValidName(name)) return false;
        if (!isValidImageId(imageId)) return false;
        if (!isValidAttackType(attackType)) return false;
        if (!isValidMapPercentage(mapPercStr)) return false;
        if (!isValidStat(strengthStr)) return false;
        if (!isValidStat(enduranceStr)) return false;
        if (!isValidStat(sanityStr)) return false;
        
        return true;
    }
    
    private boolean isValidArgsLength(String[] args) {
        return args.length >= 7;
    }
    
    private boolean isValidName(String name) {
        // TODO CHECK IF THE NAME IS REPEATED ...
        return !name.trim().isEmpty();
    }
    
    private boolean isValidImageId(String imageId) {
        return getClass().getResource(ImageLoader.IMAGE_PATH + imageId + ".png") != null;
    }
    
    private AttackType getAttackType(String attackType) {
        attackType = attackType.toUpperCase();
        attackType = attackType.replace(" ", "_");
        switch (attackType) {
            case "THUNDERS_UNDERS_THE_SEA":
                return AttackType.THUNDERS_UNDERS_THE_SEA;
            case "FISH_TELEPHATY":
                return AttackType.FISH_TELEPHATY;
            case "RELEASE_THE_KRAKEN":
                return AttackType.RELEASE_THE_KRAKEN;
            case "WAVES_CONTROL":
                return AttackType.WAVES_CONTROL;
            case "THE_TRIDENT":
                return AttackType.THE_TRIDENT;
            case "UNDERSEA_VOLCANOES":
                return AttackType.UNDERSEA_VOLCANOES;
            default:
                return AttackType.NONE;
        }

        return AttackType.NONE;
    }

    private boolean isValidAttackType(String attackType) {
        if (getAttackType(attackType) != AttackType.NONE) {
            return true;
        }

        return false;
    }
    
    private boolean isValidMapPercentage(String mapPercStr) {
        try {
            // TODO, check that is valid map percentage
            int mapPercentage = Integer.parseInt(mapPercStr);
            return mapPercentage >= 0 && mapPercentage <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isValidStat(String statStr) {
        try {
            // TODO, check if the 75, 100 are used
            int stat = Integer.parseInt(statStr);
            return stat == 50 || stat == 75 || stat == 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean createFighter(String []args) {
        
        if (!isValidFighter(args)) {
            return false;
        }

        String name = args[0];
        String imageId = args[1];
        String attackTypeStr = args[2];
        int mapPercentage = Integer.parseInt(args[3]);
        int strength = Integer.parseInt(args[4]);
        int endurance = Integer.parseInt(args[5]);
        int sanity = Integer.parseInt(args[6]);

        AttackType attackType = getAttackType(attackTypeStr);
        if (attackType == AttackType.NONE) {
            // No se como llegaria aca despues del isValidFighter Pero ok
            return false;
        }

        String imagePath = ImageLoader.IMAGE_PATH + imageId + ".png";

        Fighter fighter = null;
        switch (attackType) {
            case THUNDERS_UNDERS_THE_SEA:
                fighter = new ThundersUnderTheSea(name, imagePath, attackType, strength, endurance, sanity, mapPercentage);
                break;
            case FISH_TELEPHATY:
                fighter = new FishTelepathy(name, imagePath, attackType, strength, endurance, sanity, mapPercentage);
                break;
            case RELEASE_THE_KRAKEN:
                fighter = new ReleaseTheKraken(name, imagePath, attackType, strength, endurance, sanity, mapPercentage);
                break;
            case WAVES_CONTROL:
                fighter = new WavesControl(name, imagePath, attackType, strength, endurance, sanity, mapPercentage);
                break;
            case THE_TRIDENT:
                fighter = new TheTrident(name, imagePath, attackType, strength, endurance, sanity, mapPercentage);
                break;
            case UNDERSEA_VOLCANOES:
                fighter = new UnderseaVolcanoes(name, imagePath, attackType, strength, endurance, sanity, mapPercentage);
                break;
            default:
                return false;
        }

        // Add to fighters array (find empty slot)
        for (int i = 0; i < fighters.length; i++) {
            if (fighters[i] == null) {
                fighters[i] = fighter;

                updateFrame(fighter);
                return true;
            }
        }
        // If no space available
        return false;
    }

}
