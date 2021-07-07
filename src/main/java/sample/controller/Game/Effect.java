package sample.controller.Game;

import sample.MonsterControllerInGame;
import sample.SpellControllerInGame;
import sample.controller.UserLogined;
import sample.model.Card.Field;
import sample.model.Card.MonsterForUser;
import sample.model.Card.Position;
import sample.model.Card.SpellCardForUser;
import sample.model.User;
import sample.view.graphic.ActiveEquipSpell;
import sample.view.graphic.GameGraphic1;

public class Effect {
    public static boolean canAttackUserLogined = true;
    public static boolean canAttackUserOpponent = true;

    public static void activeMonster(MonsterForUser monsterForUser) {
        if (monsterForUser.getName().equals("Command knight")) {
            System.out.println(monsterForUser.getName());
            for (int a = 0; a < 5; a++) {
                if (UserLogined.user.monsterZone[a] != null)
                    UserLogined.user.monsterZone[a].ATK += 400;

                if (UserLogined.opponent.monsterZone[a] != null)
                    UserLogined.opponent.monsterZone[a].ATK += 400;
            }
        }
    }

    public static void deActiveMonster(MonsterForUser monsterForUser) {
        if (monsterForUser.getName().equals("Command knight")) {
            System.out.println(monsterForUser.getName());
            for (int a = 0; a < 5; a++) {
                if (UserLogined.user.monsterZone[a] != null)
                    UserLogined.user.monsterZone[a].ATK -= 400;

                if (UserLogined.opponent.monsterZone[a] != null)
                    UserLogined.opponent.monsterZone[a].ATK -= 400;
            }
        }
    }


    public static void activeSpell(SpellCardForUser spellCardForUser, User user, User opponent) throws Exception {
        if (spellCardForUser.getName().equals("Pot of Greed")) {
            DrawCard.draw(user);
            DrawCard.draw(user);
            SpellControllerInGame.sendSpellToGrave(spellCardForUser, user);
        } else if (spellCardForUser.getName().equals("Raigeki")) {
            for (int a = 0; a < 5; a++) {
                if (opponent.monsterZone[a] != null) {
                    opponent.monsterZone[a].setField(Field.valueOf("GRAVE"));
                    opponent.monsterGrave.add(opponent.monsterZone[a]);
                    opponent.monsterZone[a].address = opponent.NumOfGrave;
                    opponent.monsterZone[a] = null;
                    opponent.NumOfGrave++;

                }
                SpellControllerInGame.sendSpellToGrave(spellCardForUser, user);
            }

        } else if (spellCardForUser.getName().equals("Harpie's Feather Duster")) {
            for (int a = 0; a < 5; a++) {
                if (opponent.spellZone[a] != null) {
                    opponent.spellZone[a].field = (Field.valueOf("GRAVE"));
                    opponent.spellGrave.add(opponent.spellZone[a]);
                    opponent.spellZone[a].address = opponent.NumOfGrave;
                    opponent.spellZone[a] = null;
                    opponent.NumOfGrave++;
                } else if (opponent.trapZone[a] != null) {
                    opponent.trapZone[a].field = (Field.valueOf("GRAVE"));
                    opponent.trapGrave.add(opponent.trapZone[a]);
                    opponent.trapZone[a].address = opponent.NumOfGrave;
                    opponent.trapZone[a] = null;
                    opponent.NumOfGrave++;
                }
            }
            SpellControllerInGame.sendSpellToGrave(spellCardForUser, user);
        } else if (spellCardForUser.getName().equals("Dark Hole")) {
            for (int a = 0; a < 5; a++) {
                if (opponent.monsterZone[a] != null) {
                    opponent.monsterZone[a].setField(Field.valueOf("GRAVE"));
                    opponent.monsterGrave.add(opponent.monsterZone[a]);
                    opponent.monsterZone[a].address = opponent.NumOfGrave;
                    opponent.monsterZone[a] = null;
                    opponent.NumOfGrave++;

                }
                if (user.monsterZone[a] != null) {
                    user.monsterZone[a].setField(Field.valueOf("GRAVE"));
                    user.monsterGrave.add(user.monsterZone[a]);
                    user.monsterZone[a].address = user.NumOfGrave;
                    user.monsterZone[a] = null;
                    user.NumOfGrave++;
                }
                SpellControllerInGame.sendSpellToGrave(spellCardForUser, user);
            }
        } else if (spellCardForUser.getName().equals("Sword Of dark destruction")) {
            new ActiveEquipSpell().start(GameGraphic1.stage);
        } else if (spellCardForUser.getName().equals("Black Pendant")) {
            new ActiveEquipSpell().start(GameGraphic1.stage);
        } else if (spellCardForUser.getName().equals("United We Stand")){
            new ActiveEquipSpell().start(GameGraphic1.stage);
        }






        else {

            for (int a = 0; a < 5; a++) {
                if (user.spellZone[a] == null && user.trapZone[a] == null) {
                    user.spellZone[a] = spellCardForUser;
                    user.spellZone[a].position = Position.ATTACK;
                    user.spellZone[a].address = a;
                    user.spellZone[a].field = Field.valueOf("GAME");
                    user.handSpell.remove(spellCardForUser);
                }
            }
        }
    }
}
