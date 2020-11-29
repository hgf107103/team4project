package Module.cryptoModule;

public class cryptoTest {

	public static void main(String[] args) {
		cryptoObject cpt = cryptoObject.getInstence("Hwt0147258!");
		
		cpt.setSHA512String();
		System.out.println(cpt.getHashString());
		
		cpt.setSHA256String();
		System.out.println(cpt.getHashString());
	}

}
