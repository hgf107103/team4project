package module.CryptoModule;

public class cryptoTest {

	public static void main(String[] args) {
		cryptoObject cpt = cryptoObject.getInstence("¾È³ç¾È³ç");
		
		cpt.setSHA512String();
		System.out.println(cpt.getNormalString());
		System.out.println(cpt.getHashString());
		
		cpt.setSHA256String();
		System.out.println(cpt.getNormalString());
		System.out.println(cpt.getHashString());
	}

}
