package easyVoteproject;

import models.utenti.Elettore;

public class main {

	public static void main(String[] args) {
		Data nascita=new Data("21-09-2022");
		CodiceFiscale cf=new CodiceFiscale("CHNSNG00P21D403X");
	    Elettore Angelo=new Elettore(1,"SHENG ANGELO","CHEN",nascita,"Empoli","m","ITA",cf);
	   
	    System.out.println(Angelo.getfirstname() + " "+ Angelo.getlastname()+" : "+  Elettore.getAge(nascita));
	    System.out.println("soggetto maggiorenne: "+Angelo.checkMaggiorenne() );
	    String str=Angelo.getlastname();
	    String str2=Angelo.getfirstname();
	    str=CodiceFiscale.normalize(str);
	    str2=CodiceFiscale.normalize(str2);
	    System.out.println("voted: "+ Angelo.getVote());
	    
	    System.out.println(str);
	    System.out.println(str2); 
	    
	    Character[] ch= CodiceFiscale.strtochar(str);
        Character[] ch2 = CodiceFiscale.strtochar(str2);
	    Character[] conson=CodiceFiscale.ConsonantiCognome(ch);
	    Character[] conson2=CodiceFiscale.ConsonantiNome(ch2);
	    Character[] anno=CodiceFiscale.strtochar(String.valueOf(Angelo.getDatanascita().getAnno()));
	 
	    for(char i:conson)
	    	System.out.print(i );

	    for(char i:conson2)
	    	System.out.print(i );
	    System.out.print(anno[2] );
	    System.out.print(anno[3] );
	    System.out.print(CodiceFiscale.mesetochar(Angelo.getDatanascita().getMese()) );
	    System.out.print(Angelo.getDatanascita().getGiorno() );
	    System.out.println();

	    System.out.println(("validity: "+ CodiceFiscale.checkValidity(cf,"CHEN","SHENG ANGELO",nascita,Gender.MALE,"ITA")));
	    Angelo.castVote();
	    System.out.println("\nvoted: "+ Angelo.getVote());



	   

	}

}
