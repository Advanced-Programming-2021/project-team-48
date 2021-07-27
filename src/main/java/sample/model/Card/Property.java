package sample.model.Card;

import java.io.Serializable;

public enum Property  implements Serializable {
    NORMAL,
    COUNTER,
    FIELD,
    EQUIP,
    CONTINUOUS,
    QUICK_PLAY,
    RITUAL;
}