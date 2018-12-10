import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RSADecrypt 
{
	public static void main(String[] args) throws IOException
	{
		char alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
		
		List<String> contents = new ArrayList<String>();

		File file = new File("src/test.enc");
	    Scanner input = new Scanner(file); 
	 
	    while (input.hasNext()) 
	    {
	    	String word = input.next();
	    	contents.add(word);
	    }
	    
	    /*for(int i = 0; i < contents.size(); i++) 
	    {
	    	System.out.println(contents.get(i));
        }*/
	    
	    input.close();
	    
	    List<String> keys = new ArrayList<String>();
	    
	    String pri_file ="src/pri_key.txt";
		
		BufferedReader pri_reader = new BufferedReader(new FileReader(pri_file));
	    StringBuilder keyBuilder = new StringBuilder();
	    String pricurrentLine = pri_reader.readLine();
	    
	    while (pricurrentLine != null) 
	    {
	        keyBuilder.append(pricurrentLine);
	        keyBuilder.append("\n");
	        pricurrentLine = pri_reader.readLine();
	        keys.add(pricurrentLine);
	    }
	    
	    pri_reader.close();
	      	
	    BigInteger d = BigInteger.valueOf(Integer.parseInt(keys.get(0).substring(keys.indexOf("d: ") + 4)));
	    BigInteger n = BigInteger.valueOf(Integer.parseInt(keys.get(1).substring(keys.indexOf("n: ") + 4)));

	    List<String> ready = new ArrayList<String>();
	    
	    for(int i = 0; i < contents.size(); i++) 
	    {
	    	BigInteger C = BigInteger.valueOf(Integer.parseInt(contents.get(i)));
	    	BigInteger P = C.modPow(d, n);
	    	
	    	ready.add(P.toString());
        }

	    List<String> dec = new ArrayList<String>();
	    
	    for(int i = 0; i < ready.size(); i++) 
	    {
	    	StringBuilder builder = new StringBuilder();
	    	builder.append(ready.get(i));
	    	
	    	while(builder.length() != 6)
	    	{
	    		builder.insert(0, "0");
	    	}
	    	
	    	//System.out.println(builder);
	    	dec.add(builder.toString());
        }
	    
	    System.out.println("Decrypted Message: ");
	    
	    List<String> total = new ArrayList<String>();
	    
	    for(int i = 0; i < dec.size(); i++) 
	    {
	    	total.add(String.valueOf(alphabet[Integer.parseInt(dec.get(i).substring(0, 2))]));
	    	total.add(String.valueOf(alphabet[Integer.parseInt(dec.get(i).substring(2, 4))]));
	    	total.add(String.valueOf(alphabet[Integer.parseInt(dec.get(i).substring(4, 6))]));
        }
	    
	    BufferedWriter priWriter = new BufferedWriter(new FileWriter("src/test.dec"));
	    
	    String message = "";
	    
	    for(int i = 0; i < total.size(); i++) 
	    {
	    	message = message + total.get(i);
        }
	    
	    System.out.println(message);
	    
	    priWriter.write(message);
	    priWriter.close();
	}
}