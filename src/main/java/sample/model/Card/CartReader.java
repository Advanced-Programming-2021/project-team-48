package sample.model.Card;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class CartReader {
    private static ArrayList<MonsterCard> monsterCards = new ArrayList<>();
    private static ArrayList<SpellCard> spellCards = new ArrayList<>();
    private static ArrayList<TrapCard> trapCards = new ArrayList<>();


    public CartReader() {
        csv();
        //run();
    }

    private void csv() {

        boolean aval = true;
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf("C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\model\\Card\\Monster.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!aval) {
                    String[] values = line.split(",");
                    monsterCards.add(new MonsterCard(values[0],values[7],Integer.parseInt(values[8]),Integer.parseInt(values[1]),values[4],Integer.parseInt(values[5]),Integer.parseInt(values[6]),MonsterType.valueOf(values[3].toUpperCase(Locale.ROOT)), Attribute.valueOf(values[2].toUpperCase(Locale.ROOT))));
                } else aval = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        aval = true;
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf("C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\model\\Card\\SpellTrap.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!aval) {
                    String[] values = line.split(",");
                    if (values[1].equals("Trap")){

                        trapCards.add(new TrapCard(values[0],values[3],Integer.parseInt(values[5]), Property.valueOf(values[2].toUpperCase(Locale.ROOT)),Status.valueOf(values[4].toUpperCase(Locale.ROOT))));
                    }else {

                        spellCards.add(new SpellCard(values[0],values[3],Integer.parseInt(values[5]),Property.valueOf(values[2].toUpperCase(Locale.ROOT)),Status.valueOf(values[4].toUpperCase(Locale.ROOT))));
                    }
                } else aval = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void run() {
        monsterCards.add(new MonsterCard("Battle Ox", "A monster with tremendous power, it destroys enemies with a swing of its axe.", 2900, 4, "Normal", 1700, 1000, MonsterType.valueOf("BEAST_WARRIOR".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Axe Raider", "An axe-wielding monster of tremendous strength and agility.", 3100, 4, "Normal", 1700, 1150, MonsterType.valueOf("WARRIOR".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Yomi Ship", "If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.", 1700, 3, "Effect", 800, 1400, MonsterType.valueOf("AQUA".toUpperCase(Locale.ROOT)), Attribute.valueOf("WATER".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Horn Imp", "A small fiend that dwells in the dark, its single horn makes it a formidable opponent.", 2500, 4, "Normal", 1300, 1000, MonsterType.valueOf("Fiend".toUpperCase(Locale.ROOT)), Attribute.valueOf("DARK".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Silver Fang", "A snow wolf that's beautiful to the eye, but absolutely vicious in battle.", 1700, 3, "Normal", 1200, 800, MonsterType.valueOf("Beast".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Suijin", "During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.", 8700, 7, "Effect", 2500, 2400, MonsterType.valueOf("AQUA".toUpperCase(Locale.ROOT)), Attribute.valueOf("WATER".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Fireyarou", "A malevolent creature wrapped in flames that attacks enemies with intense fire.", 2500, 4, "Normal", 1300, 1000, MonsterType.valueOf("Pyro".toUpperCase(Locale.ROOT)), Attribute.valueOf("FIRE".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Curtain of the dark ones", "A curtain that a spellcaster made, it is said to raise a dark power.", 700, 2, "Normal", 600, 500, MonsterType.valueOf("Spellcaster".toUpperCase(Locale.ROOT)), Attribute.valueOf("DARK".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Feral Imp", "A playful little fiend that lurks in the dark, waiting to attack an unwary enemy.", 2800, 4, "Normal", 1300, 1400, MonsterType.valueOf("Fiend".toUpperCase(Locale.ROOT)), Attribute.valueOf("DARK".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Dark magician", "The ultimate wizard in terms of attack and defense.", 8300, 7, "Normal", 2500, 2100, MonsterType.valueOf("Spellcaster".toUpperCase(Locale.ROOT)), Attribute.valueOf("DARK".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Wattkid", "A creature that electrocutes opponents with bolts of lightning.", 1300, 3, "Normal", 1000, 500, MonsterType.valueOf("Thunder".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT")));
        monsterCards.add(new MonsterCard("Baby dragon", "Much more than just a child, this dragon is gifted with untapped power.", 1600, 3, "Normal", 1200, 700, MonsterType.valueOf("Dragon".toUpperCase(Locale.ROOT)), Attribute.valueOf("WIND")));
        monsterCards.add(new MonsterCard("Hero of the east", "Feel da strength ah dis sword-swinging samurai from da Far East.", 1700, 3, "Normal", 1100, 1000, MonsterType.valueOf("WARRIOR".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH")));
        monsterCards.add(new MonsterCard("Battle warrior", "A warrior that fights with his bare hands!!!", 1300, 3, "Normal", 700, 1000, MonsterType.valueOf("WARRIOR".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH")));
        monsterCards.add(new MonsterCard("Crawling dragon", "This weakened dragon can no longer fly, but is still a deadly force to be reckoned with.", 3900, 5, "Normal", 1600, 1400, MonsterType.valueOf("Dragon".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH")));
        monsterCards.add(new MonsterCard("Flame manipulator", "This Spellcaster attacks enemies with fire-related spells such as \"Sea of Flames\" and \"Wall of Fire\".", 1500, 3, "Normal", 900, 1000, MonsterType.valueOf("Spellcaster".toUpperCase(Locale.ROOT)), Attribute.valueOf("FIRE")));
        monsterCards.add(new MonsterCard("Blue-Eyes white dragon", "This legendary dragon is a powerful engine of destruction. Virtually invincible, very few have faced this awesome creature and lived to tell the tale.", 11300, 8, "Ritual", 3000, 2500, MonsterType.valueOf("Dragon".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT")));
        monsterCards.add(new MonsterCard("Crab Turtle", "This monster can only be Ritual Summoned with the Ritual Spell smaple.model.Card,\"Turtle Oath\". You must also offer monsters whose total Level Stars equal 8 or more as a Tribute from the field or your hand.", 10200, 8, "Ritual", 2550, 2500, MonsterType.valueOf("AQUA".toUpperCase(Locale.ROOT)), Attribute.valueOf("WATER")));
        monsterCards.add(new MonsterCard("Skull Guardian", "This monster can only be Ritual Summoned with the Ritual Spell smaple.model.Card,\"Novox's Prayer\". You must also offer monsters whose total Level Stars equal 7 or more as a Tribute from the field or your hand.", 7900, 7, "Normal", 2050, 2500, MonsterType.valueOf("WARRIOR".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT")));
        monsterCards.add(new MonsterCard("Slot Machine", "The machine's ability is said to vary according to its slot results.", 7500, 7, "Normal", 2000, 2300, MonsterType.valueOf("Machine".toUpperCase(Locale.ROOT)), Attribute.valueOf("DARK")));
        monsterCards.add(new MonsterCard("Haniwa", "An earthen figure that protects the tomb of an ancient ruler.", 600, 2, "Normal", 500, 500, MonsterType.valueOf("Rock".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH")));
        monsterCards.add(new MonsterCard("Man-Eater Bug", "FLIP: Target 1 monster on the field; destroy that target.", 300, 2, "Effect", 450, 600, MonsterType.valueOf("Insect".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH")));
        monsterCards.add(new MonsterCard("Gate Guardian", "Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 \"Sanga of the Thunder\",\"Kazejin\", and \"Suijin\".", 20000, 11, "Effect", 3750, 3400, MonsterType.valueOf("WARRIOR".toUpperCase(Locale.ROOT)), Attribute.valueOf("DARK")));
        monsterCards.add(new MonsterCard("Scanner", "Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the End Phase, this card's name is treated as the selected monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field while this effect is applied, remove it from play.", 8000, 1, "Effect", 0, 0, MonsterType.valueOf("Machine".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Bitron", "A new species found in electronic space. There's not much information on it.", 1000, 2, "Normal", 200, 2000, MonsterType.valueOf("Cyberse".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Marshmallon", "Cannot be destroyed by battle. After damage calculation, if this card was attacked, and was face-down at the start of the Damage Step: The attacking player takes 1000 damage.", 700, 3, "Effect", 300, 500, MonsterType.valueOf("Fairy".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Beast King Barbaros", "You can Normal Summon/Set this card without Tributing, but its original ATK becomes 1900. You can Tribute 3 monsters to Tribute Summon (but not Set) this card. If Summoned this way: Destroy all cards your opponent controls.", 9200, 8, "Effect", 3000, 1200, MonsterType.valueOf("BEAST_WARRIOR".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Texchanger", "Once per turn, when your monster is targeted for an attack: You can negate that attack, then Special Summon 1 Cyberse Normal Monster from your hand, smaple.model.Deck, or GY.", 200, 1, "Effect", 100, 100, MonsterType.valueOf("Cyberse".toUpperCase(Locale.ROOT)), Attribute.valueOf("DARK".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Leotron", "A territorial electronic monster that guards its own domain.", 2500, 4, "Normal", 2000, 0, MonsterType.valueOf("Cyberse".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("The Calculator", "The ATK of this card is the combined Levels of all face-up monsters you control x 300.", 8000, 5, "Effect", 0, 0, MonsterType.valueOf("Thunder".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Alexandrite Dragon", "Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.", 2600, 4, "Normal", 2000, 100, MonsterType.valueOf("Dragon".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Mirage Dragon", "Your opponent cannot activate Trap Cards during the Battle Phase.", 2500, 4, "Effect", 1600, 600, MonsterType.valueOf("Dragon".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Herald of Creation", "Once per turn: You can discard 1 card, then target 1 Level 7 or higher monster in your Graveyard; add that target to your hand.", 2700, 4, "Effect", 1800, 600, MonsterType.valueOf("Spellcaster".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Exploder Dragon", "If this card is destroyed by battle and sent to the Graveyard: Destroy the monster that destroyed it. Neither player takes any battle damage from attacks involving this attacking card.", 1000, 3, "Effect", 1000, 0, MonsterType.valueOf("Dragon".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Warrior Dai Grepher", "The warrior who can manipulate dragons. Nobody knows his mysterious past.", 3400, 4, "Normal", 1700, 1600, MonsterType.valueOf("Warrior".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Dark Blade", "They say he is a dragon-manipulating warrior from the dark world. His attack is tremendous, using his great swords with vicious power.", 3500, 4, "Normal", 1800, 1500, MonsterType.valueOf("Warrior".toUpperCase(Locale.ROOT)), Attribute.valueOf("DARK".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Wattaildragon", "Capable of indefinite flight. Attacks by wrapping its body with electricity and ramming into opponents. IMPORTANT: Capturing the \"Wattaildragon\" is forbidden by the Ancient Rules and is a Level 6 offense, the minimum sentence for which is imprisonment for no less than 2500 heliocycles.", 5800, 6, "Normal", 2500, 1000, MonsterType.valueOf("Dragon".toUpperCase(Locale.ROOT)), Attribute.valueOf("LIGHT".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Terratiger", "When this card is Normal Summoned: You can Special Summon 1 Level 4 or lower Normal Monster from your hand in Defense Position.", 3200, 4, "Effect", 1800, 1200, MonsterType.valueOf("Warrior".toUpperCase(Locale.ROOT)), Attribute.valueOf("EARTH".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("The Tricky", "You can Special Summon this card (from your hand) by discarding 1 card.", 4300, 5, "Efect", 2000, 1200, MonsterType.valueOf("Spellcaster".toUpperCase(Locale.ROOT)), Attribute.valueOf("WIND".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Spiral Serpent", "When huge whirlpools lay cities asunder, it is the hunger of this sea serpent at work. No one has ever escaped its dreaded Spiral Wave to accurately describe the terror they experienced.", 11700, 8, "Normal", 2900, 2900, MonsterType.valueOf("SEA_SERPENT".toUpperCase(Locale.ROOT)), Attribute.valueOf("WATER".toUpperCase(Locale.ROOT))));
        monsterCards.add(new MonsterCard("Command Knight", "All Warrior-Type monsters you control gain 400 ATK. If you control another monster, monsters your opponent controls cannot target this card for an attack.", 2100, 4, "Effect", 1000, 1000, MonsterType.valueOf("Warrior".toUpperCase(Locale.ROOT)), Attribute.valueOf("FIRE".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Trap Hole", "When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.", 2000, Property.valueOf("normal".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Mirror Force", "When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.", 2000, Property.valueOf("normal".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Magic Cylinder", "When an opponent's monster declares an attack: Target the attacking monster; negate the attack, and if you do, inflict damage to your opponent equal to its ATK.", 2000, Property.valueOf("normal".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Mind Crush", "Declare 1 card name; if that card is in your opponent's hand, they must discard all copies of it, otherwise you discard 1 random card.", 2000, Property.valueOf("normal".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Torrential Tribute", "When a monster(s) is Summoned: Destroy all monsters on the field.", 2000, Property.valueOf("normal".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Time Seal", "Skip the Draw Phase of your opponent's next turn.", 2000, Property.valueOf("normal".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Negate Attack", "When an opponent's monster declares an attack: Target the attacking monster; negate the attack, then end the Battle Phase.", 3000, Property.valueOf("Counter".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Solemn Warning", "When a monster(s) would be Summoned, OR when a Spell/Trap smaple.model.Card, or monster effect, is activated that includes an effect that Special Summons a monster(s): Pay 2000 LP; negate the Summon or activation, and if you do, destroy it.", 3000, Property.valueOf("Counter".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Magic Jammer", "When a Spell smaple.model.Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.", 3000, Property.valueOf("Counter".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Call of The Haunted", "Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position. When this card leaves the field, destroy that monster. When that monster is destroyed, destroy this card.", 3500, Property.valueOf("Continuous".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Vanity's Emptiness", "Neither player can Special Summon monsters. If a card is sent from the smaple.model.Deck or the field to your Graveyard: Destroy this card.", 3500, Property.valueOf("Continuous".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        trapCards.add(new TrapCard("Wall of Revealing Light", "Activate by paying any multiple of 1000 Life Points. Monsters your opponent controls cannot attack if their ATK is less than or equal to the amount you paid.", 3500, Property.valueOf("Continuous".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Monster Reborn", "Target 1 monster in either GY; Special Summon it.", 2500, Property.valueOf("Normal".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Terraforming", "Add 1 Field Spell from your smaple.model.Deck to your hand.", 2500, Property.valueOf("Normal".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Pot of Greed", "Draw 2 cards.", 2500, Property.valueOf("Normal".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Raigeki", "Destroy all monsters your opponent controls.", 2500, Property.valueOf("Normal".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Change Of Heart", "Target 1 monster your opponent controls; take control of it until the End Phase.", 2500, Property.valueOf("Normal".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Sword Of Revealing Light", "After this card's activation, it remains on the field, but destroy it during the End Phase of your opponent's 3rd turn. When this card is activated: If your opponent controls a face-down monster, flip all monsters they control face-up. While this card is face-up on the field, your opponent's monsters cannot declare an attack.", 2500, Property.valueOf("Normal".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Harpie's Feather Duster", "Destroy all Spells and Traps your opponent controls.", 2500, Property.valueOf("Normal".toUpperCase(Locale.ROOT)), Status.valueOf("limited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Dark Hole", "Destroy all monsters on the field.", 2500, Property.valueOf("Normal".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Supply Squad", "Once per turn, if a monster(s) you control is destroyed by battle or card effect: Draw 1 card.", 4000, Property.valueOf("Continuous".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Spell Absorption", "Each time a Spell smaple.model.Card is activated, gain 500 Life Points immediately after it resolves.", 4000, Property.valueOf("Continuous".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Messenger Of peace", "Monsters with 1500 or more ATK cannot declare an attack. Once per turn, during your Standby Phase, pay 100 LP or destroy this card.", 4000, Property.valueOf("Continuous".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Twin Twisters", "Discard 1 card, then target up to 2 Spells/Traps on the field; destroy them.", 3500, Property.valueOf("Quick_play".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Mystical space typhoon", "Target 1 Spell/Trap on the field; destroy that target.", 3500, Property.valueOf("Quick_play".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Ring Of defense", "When a Trap effect that inflicts damage is activated: Make that effect damage 0.", 3500, Property.valueOf("Quick_play".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Yami", "All Fiend and Spellcaster monsters on the field gain 200 ATK/DEF, also all Fairy monsters on the field lose 200 ATK/DEF.", 4300, Property.valueOf("Field".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Forest", "All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.", 4300, Property.valueOf("Field".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Closed Forest", "All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn this card is destroyed.", 4300, Property.valueOf("Field".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Umiiruka", "Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 points.", 4300, Property.valueOf("Field".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Sword Of dark destruction", "A DARK monster equipped with this card increases its ATK by 400 points and decreases its DEF by 200 points.", 4300, Property.valueOf("Equip".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Black Pendant", "The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.", 4300, Property.valueOf("Equip".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("United We Stand", "The equipped monster gains 800 ATK/DEF for each face-up monster you control.", 4300, Property.valueOf("Equip".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Magnum Shield", "Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position. ● Attack Position: It gains ATK equal to its original DEF. ● Defense Position: It gains DEF equal to its original ATK.", 4300, Property.valueOf("Equip".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));
        spellCards.add(new SpellCard("Advanced Ritual Art", "This card can be used to Ritual Summon any 1 Ritual Monster. You must also send Normal Monsters from your smaple.model.Deck to the Graveyard whose total Levels equal the Level of that Ritual Monster.", 3000, Property.valueOf("Ritual".toUpperCase(Locale.ROOT)), Status.valueOf("unlimited".toUpperCase(Locale.ROOT))));


//        try {
//            FileWriter myWriter = new FileWriter("monsterCards.txt");
//            myWriter.write(new Gson().toJson(monsterCards));
//            myWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}

