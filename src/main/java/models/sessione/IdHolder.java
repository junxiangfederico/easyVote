package models.sessione;

public final class IdHolder {
	  
	  private int id;
	  private final static IdHolder INSTANCE = new IdHolder();
	  
	  private IdHolder() {}
	  
	  public static IdHolder getInstance() {
	    return INSTANCE;
	  }
	  
	  public void setId(int id) {
	    this.id= id;
	  }
	  
	  public int getid() {
	    return this.id;
	  }
	}