package rocks.luketaylor.yummyyummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke.taylor on 21/12/2017.
 */

public class Basket {
    public ArrayList<String> Items;

    public void AddItem(String item){
        Items.add(item);
    }

    private Basket(){
        Items = new ArrayList<>();
    }

    private static final Basket basket = new Basket();

    public static Basket getInstance() {
        return basket;
    }
}
