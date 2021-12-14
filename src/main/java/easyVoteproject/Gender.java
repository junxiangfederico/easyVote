package easyVoteproject;
public enum Gender {
    MALE, FEMALE;

    public  static /*@ pure @*/ Gender fromString(String value){
        if (value.equals("m")) return Gender.MALE;
        else if (value.equals("f")) return Gender.FEMALE;
        else throw new IllegalArgumentException("Invalid gender, enter 'm' or 'f'");
    }

}