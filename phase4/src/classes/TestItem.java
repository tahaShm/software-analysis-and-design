package classes;

import java.util.ArrayList;
import java.util.List;

public class TestItem {
    private int id;
    private String description;
    private ArrayList<String> tools;

    TestItem(int id, String description, ArrayList<String> tools){
        this.id = id;
        this.description = description;
        this.tools = tools;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getTools() {
        return tools;
    }
}
