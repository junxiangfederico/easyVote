package models.utenti;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.scene.control.DatePicker;

public class Data {
    private final int giorno;
    private final int mese;
    private final int anno;

    // "07-10-2000" -> 
    /**                 split[0] = 07       = giorno
     *                  split[1] = 10       = mese
     *                  split[2] = 2000     = anno
     * 
     * @param input
     */
    public Data(String input){
        String[] split = input.split("-");
        anno = Integer.parseInt(split[0]);
        mese = Integer.parseInt(split[1]);
        giorno = Integer.parseInt(split[2]);
    }


    public int getGiorno() {
        return giorno;
    }

    public int getMese() {
        return mese;
    }

    public int getAnno() {
        return anno;
    }
	public static String processDate(DatePicker fieldData) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.US);
		String formattedValue = (fieldData.getValue()).format(formatter);
		
		return formattedValue;
	}

    @Override
    public String toString() {
        return anno + "-" + mese + "-" + giorno;
    }
}