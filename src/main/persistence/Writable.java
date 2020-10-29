package persistence;

import org.json.JSONObject;

//CREDITS TO CPSC210 CLASS AND THE EXAMPLES THEY'VE GIVEN. THIS INTERFACE WAS COPIED
// BASICALLY WORD FOR WORD BECAUSE THIS IS AS CONCISE AS THIS CAN BE
public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
