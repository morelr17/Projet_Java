package game.util;

public class NumberUtils {

    private NumberUtils() {
        
    }

    /**
     *return true if the number is a power of 2
     *@param value an integer
     *@return true if the number is a power of 2
     */
    public static Boolean isPowerof2(int value){
        while ((value > 1) && (value % 2 == 0)){
            value = value/2;
        }
        return value == 1;
    }   
}
