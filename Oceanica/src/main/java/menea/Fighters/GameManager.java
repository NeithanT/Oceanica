package menea.Fighters;

import Action.Action;
import Action.ActionType;
import menea.Client.ClientConnection;
import menea.Tiles.Board;
import menea.Vanity.ImageLoader;
import menea.Client.GUI.FrameClient;


public class GameManager {

    private Fighter fighters[];
    private Board board;
    private FrameClient frameClient;
    private ClientConnection connection;
    private Object clientManager;
    
    public GameManager() {
        fighters = new Fighter[3];
        board = new Board();
        frameClient = null;
    }
    
    public void setFrameClient(FrameClient frame) {
        this.frameClient = frame;
    }
    
    public void setClientManager(Object manager) {
        this.clientManager = manager;
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
                return AttackType.THUNDERS_UNDER_THE_SEA;
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
            // No se como llegaria aca despues del isValidFighter Pero por si acaso
            return false;
        }

        Fighter fighter = new Fighter(name, imageId, attackType, strength, endurance, sanity, mapPercentage);
        switch (attackType) {
            case THUNDERS_UNDER_THE_SEA:
                fighter.setAttack(new ThundersUnderTheSea());
                break;
            case FISH_TELEPHATY:
                fighter.setAttack(new FishTelepathy());
                break;
            case RELEASE_THE_KRAKEN:
                fighter.setAttack(new ReleaseTheKraken());
                break;
            case WAVES_CONTROL:
                fighter.setAttack(new WavesControl());
                break;
            case THE_TRIDENT:
                fighter.setAttack(new TheTrident());
                break;
            case UNDERSEA_VOLCANOES:
                fighter.setAttack(new UnderseaVolcanoes());
                break;
            default:
                return false;
        }

        for (int i = 0; i < fighters.length; i++) {
            if (fighters[i] == null) {
                fighters[i] = fighter;

                board.assignZone(fighter, mapPercentage);
                updateFrame(fighter, i);
                return true;
            }
        }
        // If no space left
        return false;
    }
    
    private void updateFrame(Fighter fighter, int index) {
        if (frameClient == null) {
            return;
        }
        
        String name = fighter.getName();
        String imageId = fighter.getImageId();
        String typeLabel = fighter.getType().toString();
        String tilesLabel = fighter.getRepresentationPercentage() + "%";
        String strengthLabel = "Fuerza: " + fighter.getStrength();
        String enduranceLabel = "Resistencia: " + fighter.getEndurance();
        String sanityLabel = "Sanidad: " + fighter.getSanity();
        
        switch (index) {
            case 0:
                frameClient.updateCharacterOne(name, typeLabel, tilesLabel,
                                               strengthLabel, enduranceLabel, sanityLabel, imageId);
                break;
            case 1:
                frameClient.updateCharacterTwo(name, typeLabel, tilesLabel, 
                                               strengthLabel, enduranceLabel, sanityLabel, imageId);
                break;
            case 2:
                frameClient.updateCharacterThree(name, typeLabel, tilesLabel, strengthLabel,
                                                 enduranceLabel, sanityLabel, imageId);
                break;
        }
    }

    public boolean checkReady() {
        for (int i = 0; i < fighters.length; i++) {
            if (fighters[i] == null) {
                return false;
            }
        }
        try {
            connection = new ClientConnection(this);
            connection.start();
        } catch (Exception ex) {
            System.out.println("No se pudo conectar");
            return false;
        }
        return true;
    }
    
    public void sendAttack(String attackMethod) {
        if (connection == null) {
            System.out.println("Not connected to server");
            return;
        }
        connection.attack(attackMethod);
    }
    
    public void identifyAction(Action action) {
        if (action == null) {
            return;
        }
        
        ActionType type = action.getType();
        String context = action.getContext();
        
        switch (type) {
            case ATTACK:
                receiveAttack(context);
                break;
            case TURN:
                // TODO: turno
                break;
            case CONNECT:
                // TODO: coneccion
                break;
            case DISCONNECT:
                // TODO:ver si gano
                break;
            default:
                System.out.println("que paso aca: " + type);
        }
    }
    
    public void receiveAttack(String attackContext) {
        System.out.println("Se recibio ataque! Context: " + attackContext);
        // implementacion random de Chat xD
        int randomRow = (int) (Math.random() * board.getROWS());
        int randomCol = (int) (Math.random() * board.getCOLUMNS());
        board.attackAt(randomRow, randomCol);
        
        if (clientManager != null) {
            try {
                clientManager.getClass().getMethod("refreshBoard").invoke(clientManager);
            } catch (Exception e) {
                System.out.println("Error refreshing board: " + e.getMessage());
            }
        }
        if (frameClient != null) {
            frameClient.repaint();
        }
    }

    public void refreshAllFighters() {
        for (int i = 0; i < fighters.length; i++) {
            if (fighters[i] != null) {
                updateFrame(fighters[i], i);
            }
        }
    }


}
