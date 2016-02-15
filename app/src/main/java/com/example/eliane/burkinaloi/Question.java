package com.example.eliane.burkinaloi;

/**
 * Created by eliane on 2/12/2016.
 */
public class Question {
    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String ANSWER;

    public Question(int id,String qUESTION, String oPTA, String oPTB, String oPTC,
                    String aNSWER) {
        this.ID=id;
        this.QUESTION = qUESTION;
        this.OPTA = oPTA;
        this.OPTB = oPTB;
        this.OPTC = oPTC;
        this.ANSWER = aNSWER;
    }
    public int getID()
    {
        return ID;
    }
    public String getQUESTION() {
        return QUESTION;
    }
    public String getOPTA() {
        return OPTA;
    }
    public String getOPTB() {
        return OPTB;
    }
    public String getOPTC() {
        return OPTC;
    }
    public String getANSWER() {
        return ANSWER;
    }
    public void setID(int id)
    {
        this.ID=id;
    }
    public void setQUESTION(String qUESTION) {
        this.QUESTION = qUESTION;
    }
    public void setOPTA(String oPTA) {
        this.OPTA = oPTA;
    }
    public void setOPTB(String oPTB) {
        this.OPTB = oPTB;
    }
    public void setOPTC(String oPTC) {
        this.OPTC = oPTC;
    }
    public void setANSWER(String aNSWER) {
        ANSWER = aNSWER;
    }
}
