package edu.kings;

public enum CommandEnum{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    LOOK("look"),
    STATUS("status"),
    BACK("back"),
    DROP("drop"),
    EXAMINE("examine"),
    INVENTORY("inventory"),
    TAKE("take"),
    SCORE("score"),
    TURNS("turns"),
    LOCK("lock"),
    UNLOCK("unlock"),
    PACK("pack"),
    UNPACK("unpack"),
    DRINK("drink"),
    EAT("eat"),
    RIDDLE("riddle"),
    STATUS_REPORT("status");

    private final String text;

    CommandEnum(String text) {
        this.text = text;
    }
    public String getText() {
        return this.text;
    }
}
