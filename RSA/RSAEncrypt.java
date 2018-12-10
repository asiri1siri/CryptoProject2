import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSAEncrypt 
{
	public static void main(String[] args) throws IOException
	{
		char alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
		
	    String file ="src/test.txt";

	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    StringBuilder builder = new StringBuilder();
	    String currentLine = reader.readLine();
	    
	    while (currentLine != null) 
	    {
	        builder.append(currentLine);
	        builder.append("\n");
	        currentLine = reader.readLine();
	    }
	    
	    reader.close();
	    
	    String para = builder.toString();
	    
	    String clean = para.replace(".", "").replace(",", "").replace("\n", "").toLowerCase();
	    
	    StringBuilder newBuilder = new StringBuilder(clean);
	    
	    while((newBuilder.length() % 3) != 0)
	    {
	    	newBuilder.append(" ");
	    }
	    
	    List<String> contents = new ArrayList<String>();

	    String newPara = newBuilder.toString();
	    //System.out.println(newPara + "\n");
	    	    
	    for(int i = 0; i < newPara.length(); i = i + 3)
	    {	    	
	    	StringBuilder sb = new StringBuilder();
	    	
	    	for(int j = 0; j < alphabet.length; j++)
	    	{
	    		if(newPara.charAt(i) == (alphabet[j]))
	    		{	    			
	    			if(String.valueOf(j).length() != 2)
	    			{
	    				sb.append(0);
	    				sb.append(j);
	    			}
	    			else
	    			{	    				
	    				sb.append(j);
	    			}
	    		}
	    	}
	    	
	    	for(int j = 0; j < alphabet.length; j++)
	    	{
	    		if(newPara.charAt(i+1) == (alphabet[j]))
	    		{
	    			if(String.valueOf(j).length() != 2)
	    			{
	    				sb.append(0);
	    				sb.append(j);
	    			}
	    			else
	    			{	    				
	    				sb.append(j);
	    			}
	    		}
	    	}
	    	
	    	for(int j = 0; j < alphabet.length; j++)
	    	{
	    		if(newPara.charAt(i+2) == (alphabet[j]))
	    		{	    			
	    			if(String.valueOf(j).length() != 2)
	    			{
	    				sb.append(0);
	    				sb.append(j);
	    			}
	    			else
	    			{	    				
	    				sb.append(j);
	    			}
	    		}
	    	}
	    	
	    	contents.add(sb.toString());
	    }
	    
	    List<String> values = new ArrayList<String>();
	    
	    String pub_file ="src/pub_key.txt";
		
		BufferedReader pub_reader = new BufferedReader(new FileReader(pub_file));
	    StringBuilder keyBuilder = new StringBuilder();
	    String pubcurrentLine = pub_reader.readLine();
	    
	    while (pubcurrentLine != null) 
	    {
	        keyBuilder.append(pubcurrentLine);
	        keyBuilder.append("\n");
	        pubcurrentLine = pub_reader.readLine();
	        values.add(pubcurrentLine);
	    }
	    
	    pub_reader.close();
	      	
	    BigInteger e = BigInteger.valueOf(Integer.parseInt(values.get(0).substring(values.indexOf("e: ") + 4)));
	    BigInteger n = BigInteger.valueOf(Integer.parseInt(values.get(1).substring(values.indexOf("n: ") + 4)));
	    BufferedWriter priWriter = new BufferedWriter(new FileWriter("src/test.enc"));
	    
	    for(int i = 0; i < contents.size(); i++) 
	    {	    	
	    	BigInteger P = new BigInteger(contents.get(i));
	    	BigInteger C = P.modPow(e, n);
	    	
	    	priWriter.write(C.toString() + " ");
        }   
	    
	    priWriter.close();
	    
	    System.out.println("Contents have been written to test.enc successfully!");
	}
}