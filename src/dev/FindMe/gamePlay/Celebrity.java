package dev.FindMe.gamePlay;

import java.util.ArrayList;

public class Celebrity {

    private String name;
    private ArrayList<String []> QAList = new ArrayList<>();

    public Celebrity(){
    }

    public void addString(String Q, String Ans, String pri){
        QAList.add(new String[]{Q, Ans, pri});
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String[]> getQAList() {
        return QAList;
    }
}
