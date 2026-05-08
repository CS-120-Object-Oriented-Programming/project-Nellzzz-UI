package edu.kings;

public class NPC {
    private final String name;
    private String riddle;
    private String correctAnswer;
    private final int sanityLoss;
    private String[] options;
    private boolean solved;
    private String npcType;

    
    // Npc constructor 
    public NPC(String name, String riddle, String correctAnswer, int sanityLoss, String npcType) {

        this.name = name;
        this.riddle = riddle;
        this.correctAnswer = correctAnswer;
        this.sanityLoss = sanityLoss;
        this.options = new String[4];
        this.npcType = npcType;
        this.solved = false; 
        
    }

    public NPC(String name, int sanityLoss) {
        this.name = name;
        this.sanityLoss = sanityLoss;
    }

    //option
    public String[] getOptions() {
        return options;
    }
    // npc name 
    public String getName() {
        return name;
    }
    //npc riddle question
    public String getRiddle(){
        return riddle;
    }
    // Get correct answer 
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getSanityLoss() {
        return sanityLoss;
        
    }
    //Gets the riddle type 
    public String getNPCType() {
        return npcType;
    }
    // Marks riddle as solved 
    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setCorrectAnswer(String answer) {
        this.correctAnswer = answer;
    }

    public boolean checkAnswer(String playerAnswer) {
        return playerAnswer.toLowerCase().equals(correctAnswer.toLowerCase());
    }

}
