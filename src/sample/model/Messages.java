package sample.model;

import javafx.collections.ObservableList;

public class Messages {
    ObservableList<String> array;

    public ObservableList<String> getList() {
        return array;
    }

    public String getElement(Integer index) {
        if (array.size()<index && array != null) {
            return array.get(index);
        } else {
            return null;
        }
    }

    public Messages(ObservableList<String> array) {
        this.array = array;
    }

    public void addElement (String element){
        array.add(element);
    }

    public void addElement (Integer index, String element){
        array.add(index, element);
    }
}
