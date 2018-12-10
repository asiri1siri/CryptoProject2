import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class RSAGenKey
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Please Enter Large Prime Values for p & q");
		System.out.println("6551 4733 8311");
		
		Scanner scanP = new Scanner(System.in);
		BigInteger p = scanP.nextBigInteger();
		
		Scanner scanQ = new Scanner(System.in);
		BigInteger q = scanQ.nextBigInteger();
		
		BigInteger n = p.multiply(q);
		BigInteger one = new BigInteger("1");
		
		BigInteger phiN = (p.subtract(one)).multiply(q.subtract(one));
		
		System.out.println("phi(n): " + phiN);
	    
		System.out.println("Please Enter Prime for e, where e < phi(n)");
		
		Scanner scanE = new Scanner(System.in);
		BigInteger e = scanE.nextBigInteger();
		
		BigInteger gcd = e.gcd(phiN);
		
		System.out.println("gcd of e and phi(n): " + gcd);

		BigInteger d = e.modInverse(phiN);
		
		System.out.println("e modInverse phi(n): " + d);
		
		System.out.println("Public Key {" + e + "," + n + "}");
		System.out.println("Private Key {" + d + "," + n + "}");
		
	
		writeToFile(e, d, n);
		
		System.out.println("Keys have been generated!");
	}
	
	public static void writeToFile(BigInteger e, BigInteger d, BigInteger n) throws IOException 
	{
	    String pubKey = "Public Key: \n";
	    String priKey = "Private Key: \n";
		String stringE = "e: " + e + "\n";
	    String stringN = "n: " + n + "\n";
	    String stringD = "d: " + d + "\n";
	    
	    BufferedWriter pubWriter = new BufferedWriter(new FileWriter("src/pub_key.txt"));
	    
	    pubWriter.write(pubKey);
	    pubWriter.write(stringE);
	    pubWriter.write(stringN);
	    
	    pubWriter.close();
	    
	    BufferedWriter priWriter = new BufferedWriter(new FileWriter("src/pri_key.txt"));
	    
	    priWriter.write(priKey);
	    priWriter.write(stringD);
	    priWriter.write(stringN);
	    
	    priWriter.close();
	}
}