package sample.controller.Game;

import sample.Game;
import sample.MonsterControlerInGame;
import sample.controller.UserLogined;
import sample.model.Card.MonsterForUser;
import sample.model.User;
import sample.view.graphic.GameGraphic;

public class GameController {
    public boolean hasSummonInThisRound = false;
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
