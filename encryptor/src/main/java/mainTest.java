
public class mainTest {

	
	//C:\\Users\\USER\\Desktop\\test.txt
	//C:\\Users\\USER\\Desktop\\test.encrypted.txt
	//C:\\Users\\USER\\Desktop\\test_decrypted.[original_file_Extension].txt
	public static void main(String[] args) {
		Ceaser_Algo c = new Ceaser_Algo();
		XOR_Algo x = new XOR_Algo();
		MWO_algo m = new MWO_algo();
		Double_Algo d = new Double_Algo(c.getTheFile(), c.getPath());
		Reverse_algo r = new Reverse_algo(c.getTheFile(), c.getPath());
		Split_Algo s = new Split_Algo(c.getTheFile(), c.getPath());
		Directory_Enc di = new Directory_Enc();

	}

}
