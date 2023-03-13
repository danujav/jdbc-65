package lk.ijse.singleton;

/*
    @author DanujaV
    @created 3/13/23 - 3:17 PM   
*/

public class B {
    private static B b; //1st step

    private B() {   //2nd step

    }

    public static B getInstance() {
        if(b == null) {
            b = new B();
            return b;
        } else {
            return b;
        }
    }
}
