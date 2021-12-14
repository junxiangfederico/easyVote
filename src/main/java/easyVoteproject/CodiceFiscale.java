package easyVoteproject;

import java.util.Arrays;
public class CodiceFiscale {

    private final Character[] codiceFiscale;

    public CodiceFiscale(String cf){
        codiceFiscale = new Character[16];
        for (int i = 0; i < cf.length();i++){
            codiceFiscale[i] = cf.charAt(i);
        }
    }

    public Character[] getCodiceFiscale() {
        return codiceFiscale;
    }


    public static Character[] ConsonantiCognome(Character[] name) {
    	Character[] consonanti= new Character[3];
    	int posizione=0;
    	for (int i=0 ; i<name.length &&  posizione <3; i++){
            char ch = name[i];
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'||ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' ){
               continue;
            }else{
              consonanti[posizione]=ch;
              posizione++;
            }
         }
    		for (int z=posizione ;z<3;z++) {
    			consonanti[z]='X';
    		}
    	return consonanti;
    
    }
    public static Character[] ConsonantiNome(Character[] name) {
    	Character[] consonanti= new Character[3];
    	int posizione=0;
    	for (int i=0 ; i<name.length &&  posizione <4; i++){
            char ch = name[i];
            if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' ){
               continue;
            }else{
              if (posizione==3){
            	  consonanti[1]=consonanti[2];
            	  consonanti[2]=ch;
            	  return consonanti;
              } 
              consonanti[posizione]=ch;
              posizione++;
            }
         }
    		for (int z=posizione ;z<3;z++) {
    			consonanti[z]='X';
    		}
    	
    	return consonanti;
    
    }

    public static String normalize(String cf){
		cf = cf.replaceAll("[ \t\r\n]", "");
		cf = cf.toUpperCase();
		return cf;
	}

    public static Character[] strtochar(String str) {
    	Character[] ch = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        return ch;
    }

    public static Character[] giornotocodice(int giorno,Gender sesso) {
    	Character[] giornocodice=new Character[2];
    	if (sesso==Gender.FEMALE)
    			giorno=giorno+40;
    	if (giorno <10){
    		giornocodice[0]='0';
    		char c=(char)(giorno+'0');
    		giornocodice[1]=c;	
    		return giornocodice;
    	}else {
    		String s=String.valueOf(giorno);
    		giornocodice=CodiceFiscale.strtochar(s);
    		return giornocodice;
    	}
    }

    public static Character mesetochar(int mese) {
    	switch(mese){
    		case 1:
    			return 'A';
    		case 2:
    			return 'B';
    		case 3:
    			return 'C';
    		case 4:
    			return 'D';
    		case 5:
    			return 'E';
    		case 6:
    			return 'H';
    		case 7:
    			return 'L';
    		case 8:
    			return 'M';
    		case 9:
    			return 'P';
    		case 10:
    			return 'R';
    		case 11:
    			return 'S';
    		case 12:
    			return 'T';	
    		default:
    			throw new IllegalArgumentException("Mese inserito non valido, riprovare");
    	}
    }
public static /*@ pure @*/ boolean checkValidity(CodiceFiscale cf,String lastname,String firstname,Data datanascita,Gender sesso,String nazionenascita) {
    	Character[] part1=Arrays.copyOfRange(cf.codiceFiscale, 0, 3); 
    	Character[] lastnamechar=CodiceFiscale.strtochar(CodiceFiscale.normalize(lastname));
    	Character[] conscognome=CodiceFiscale.ConsonantiCognome(lastnamechar);
    	Character[] part2=Arrays.copyOfRange(cf.codiceFiscale, 3 , 6); 
    	Character[] firstnamechar=CodiceFiscale.strtochar(CodiceFiscale.normalize(firstname));
    	Character[] consnome=CodiceFiscale.ConsonantiNome(firstnamechar);
    	if(Arrays.equals(part1,conscognome )==false){
    			return false;
    	}
    	if(Arrays.equals(part2,consnome )==false) {
    			return false;
    	}
    	Character[] anno=CodiceFiscale.strtochar(String.valueOf(datanascita.getAnno()));
    	if(cf.codiceFiscale[6]!=anno[2]||cf.codiceFiscale[7]!=anno[3]) {
    		return false;
    	}
    	if(cf.codiceFiscale[8]!=CodiceFiscale.mesetochar(datanascita.getMese())) {
    		return false;
    	}
    	Character[] part5=Arrays.copyOfRange(cf.codiceFiscale, 9 , 11); 
    	Character[] codiceG=CodiceFiscale.giornotocodice(datanascita.getGiorno(), sesso);
    	if(Arrays.equals(part5, codiceG)==false)
    		return false;
    	Character[]letterefinali= {cf.codiceFiscale[11],cf.codiceFiscale[15]};
    	Character[]numerifinali= {cf.codiceFiscale[12],cf.codiceFiscale[13],cf.codiceFiscale[14]};
    	for(char character:letterefinali) {
    		if (!Character.isLetter(character))
    			return false;
    	}
    	for(char character:numerifinali) {
    		if (!Character.isDigit(character))
    			return false;
    	}
    	if(nazionenascita!="ITA"&& cf.codiceFiscale[11]!='Z')
    		return false;
    	return true;
    	
    	
    }
    
}