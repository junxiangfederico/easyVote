package easyVoteproject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import models.utenti.Elettore;

public class main {

	public static void main(String[] args) {
		
			String hex="";
			try {
		        MessageDigest md = MessageDigest.getInstance("SHA-256");
		        md.update("chen123".getBytes(StandardCharsets.UTF_8));
		        byte[] digest = md.digest();
		        hex = String.format("%064x", new BigInteger(1, digest));
		        
		    }
		    catch (NoSuchAlgorithmException e) {
		        System.err.println("not valid message digest algorithm");
		    }
	        
			System.out.print(hex);
			
		}


	   

	}
