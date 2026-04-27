package edu.kings;

public enum CommandEnum{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    LOOK("look"),
    STATUS("status"),
    BACK("back");

    private final String text;

    CommandEnum(String text) {
        this.text = text;
    }
    public String getText() {
        return this.text;
    }
}
