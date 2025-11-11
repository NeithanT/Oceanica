package Action;

import java.io.Serializable;

public class Action implements Serializable {
 
    private ActionType type;
    private String context;
    
    public Action(ActionType type, String context) {
        this.type = type;
        this.context = context;
    }
    
    public ActionType getType() {
        return type;
    }
    
    public void setType(ActionType type) {
        this.type = type;
    }
    
    public String getContext() {
        return context;
    }
    
    public void setContext(String context) {
        this.context = context;
    }
    
    @Override
    public String toString() {
        return "Action{" +
                "type=" + type +
                ", context='" + context + '\'' +
                '}';
    }
}
