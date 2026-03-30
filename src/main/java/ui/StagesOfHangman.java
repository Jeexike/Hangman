package ui;

public enum StagesOfHangman {
    STAGE_0("""
               ________
               |      |
               |      
               |      
               |      
               |      
            ___|___
            """),
    STAGE_1("""
               ________
               |      |
               |      O
               |      
               |      
               |      
            ___|___
            """),
    STAGE_2("""
               ________
               |      |
               |      O
               |      |
               |      |
               |      
            ___|___
            """),
    STAGE_3("""
               ________
               |      |
               |      O
               |     /|
               |      |
               |      
            ___|___
            """),
    STAGE_4("""
               ________
               |      |
               |      O
               |     /|\\
               |      |
               |      
            ___|___
            """),
    STAGE_5("""
               ________
               |      |
               |      O
               |     /|\\
               |      |
               |     /
            ___|___
            """),
    STAGE_6("""
               ________
               |      |
               |      O
               |     /|\\
               |      |
               |     / \\
            ___|___
            """);

    private final String displayName;

    StagesOfHangman(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static StagesOfHangman getStage(int num) {
        if (num < 0) num = 0;
        if (num > 6) num = 6;

        return switch (num) {
            case 0 -> STAGE_0;
            case 1 -> STAGE_1;
            case 2 -> STAGE_2;
            case 3 -> STAGE_3;
            case 4 -> STAGE_4;
            case 5 -> STAGE_5;
            case 6 -> STAGE_6;
            default -> STAGE_0;
        };
    }
}
