package rocks.luketaylor.yummyyummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke.taylor on 21/12/2017.
 */

public class Basket {
    public ArrayList<String> Items;
    public Double Cost = 0.0;
    public String Address = "";

    public void AddItem(String item){
        Items.add(item);
    }

    public void RemoveItem(String item){
        for(int i = 0; i < Items.size(); i ++){
            if(Items.get(i) == item){
                Items.remove(i);
                break;
            }
        }
    }

    private Basket(){
        Items = new ArrayList<>();
    }

    private static final Basket basket = new Basket();

    public static Basket getInstance() {
        return basket;
    }
}
