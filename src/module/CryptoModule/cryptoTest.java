package module.CryptoModule;

public class cryptoTest {

	public static void main(String[] args) {
		cryptoObject cpt = cryptoObject.getInstence("�ȳ�ȳ�");
		
		cpt.setSHA512String();
		System.out.println(cpt.getNormalString());
		System.out.println(cpt.getHashString());
		
		cpt.setSHA256String();
		System.out.println(cpt.getNormalString());
		System.out.println(cpt.getHashString());
	}

}
