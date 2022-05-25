package models.utenti;

public final class UtentiHolder {
	  
	  private int id;
	  private final static UtentiHolder INSTANCE = new UtentiHolder();
	  
	  private UtentiHolder() {}
	  
	  public static UtentiHolder getInstance() {
	    return INSTANCE;
	  }
	  
	  public void setId(int i) {
	    this.id= i;
	  }
	  
	  public int getid() {
	    return id;
	  }
	}