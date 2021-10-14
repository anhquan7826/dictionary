package dictionary.file;

public class Type {
    public static final String EV = "Eng - Vi";
    public static final String VE = "Vi - Eng";
    public static final String EVFav = "EVFav";
    public static final String VEFav = "VEFav";

    public static String convert(String type) {
        if (type == EVFav) {
            return EV;
        } else {
            return VE;
        }
    }
}
