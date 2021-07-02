package sample.controller.Game;

import sample.controller.UserLogined;
import sample.model.User;
import sample.view.graphic.GameGraphic1;

public class GameController {

    private User user1;
    private User user2;
    public static boolean dasteAval = true;
    public static boolean hasSummonInThisRound = false;
    public static User whosTurn;
    public static GameGraphic1 gameGraphic1;
    public static String phase = "";
    private int[] harif = {3, 1, 0, 2, 4};
    private int[] khodm = {4, 2, 0, 1, 3};

    public GameController(User user1, User user2) {
        setUser1(user1);
        setUser2(user2);
        user1.setLifePoint(8000);
        user2.setLifePoint(8000);
        run();
    }


    public void run() {
        for (int i = 0; i < 4; i++) {
            DrawCard.draw(user1);
            DrawCard.draw(user2);
        }
        boolean bool = true;
        play(user1,user2);
        /*while (bool) {
            whosTurn=user1;
            bool = play(user1, user2);
            if (bool) {
                whosTurn=user2;
                bool = play(user2, user1);
            }
        }

         */
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        user1.setActiveDeck(user1.getDeckByName(user1.getActiveDeck().getName()));
        user2.setActiveDeck(user2.getDeckByName(user2.getActiveDeck().getName()));
        user1.handMonster.clear();
        user2.handMonster.clear();
        user1.handTrap.clear();
        user2.handTrap.clear();
        user1.handSpell.clear();
        user2.handSpell.clear();
        user1.fieldZone=null;
        user2.fieldZone=null;
        for (int i=0;i<5;i++){
            user1.monsterZone[i]=null;
            user2.monsterZone[i]=null;
            user1.spellZone[i]=null;
            user2.spellZone[i]=null;
            user1.trapZone[i]=null;
            user2.trapZone[i]=null;
        }
        user1.monsterGrave.clear();
        user2.monsterGrave.clear();
        user1.spellGrave.clear();
        user2.spellGrave.clear();
        user1.spellGrave.clear();
        user2.spellGrave.clear();
        user1.NumOfGrave=0;
    }

    private boolean play(User user, User opponent) {
        hasSummonInThisRound = false;

        if (user.getActiveDeck().numberOfCardsInMain == 0||user.getLifePoint()==0||opponent.getLifePoint()==0) {
            return false;
        }
        if (!dasteAval) {
            drawPhase(user);
        }

        //mainPhase1(user, opponent);
        //battlePhase(user, opponent);
        if (user.getLifePoint()==0||opponent.getLifePoint()==0) {
            return false;
        }
        //mainPhase2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //endPhase(user, opponent);
        if (user.getLifePoint()==0||opponent.getLifePoint()==0) {
            return false;
        }
        dasteAval = true;
        //!!!!!!!!!!!!!!!!!!!!!!!
        return true;
    }

    public void standbyPhase(User user, User opponent) {
        System.out.println("shokr");
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public void drawPhase(User user){
        DrawCard.draw(user);
    }


    /* public boolean hasSummonInThisRound = false;
    private boolean dasteAval =false;

    public void run(){
        UserLogined.user.setLifePoint(8000);
        UserLogined.opponent.setLifePoint(8000);
        for (int i=0;i<4;i++){
            DrawCard.draw(UserLogined.user);
            DrawCard.draw(UserLogined.opponent);
        }
        boolean bool = true;
        while (bool) {
            bool = play(UserLogined.user, UserLogined.opponent);
            if (bool) {
                bool = play(UserLogined.opponent, UserLogined.user);
            }
        }
        UserLogined.user.setActiveDeck(UserLogined.user.getDeckByName(UserLogined.user.getActiveDeck().getName()));
        UserLogined.opponent.setActiveDeck(UserLogined.opponent.getDeckByName(UserLogined.opponent.getActiveDeck().getName()));
    }

    private boolean play(User user,User opponent){
        hasSummonInThisRound = false;
        if (user.getActiveDeck().numberOfCardsInMain == 0||user.getLifePoint()==0||opponent.getLifePoint()==0) {
            return false;
        }
        if (!dasteAval) {
            dasteAval = true;
        } else {
            drawPhase(user, opponent);
        }
        //standbyPhase(user, opponent);
        //mainPhase1(user, opponent);
        //battlePhase(user, opponent);
        //mainPhase2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //endPhase(user, opponent);

        //!!!!!!!!!!!!!!!!!!!!!!!
        return true;
    }

    private void drawPhase(User user,User opponent){

    }

    /*public static String Summon(MonsterForUser monsterForUser, User user,String phase){
        if (phase.equals("phase1") || phase.equals("phase2")) {
            //!!!!!!!!!!!!!!!!!!!!!!has summon???
            if (Game.hasSummonInThisRound) {
                System.out.println("you already summoned/set on this turn");
            } else {
                MonsterControlerInGame.summonControler(monsterForUser, user);
            }
        } else {
            System.out.println("action not allowed in this phase");
        }

    }

     */



}
