package Hashing;
import java.lang.Math; 
public class DoubleHashing {
	public static int tablesize=1223; //Hash Table size
	public static void main(String args[]) {
	      int m=tablesize; 
	      Object hashkey[]= new Object[m]; //Keys and counts are implemented using parallel arrays
	      int hashcount[]= new int[m];
	      int j,k,l,collisions = 0;
	      int keys[] = new int[1100]; //Random 1100 natural numbers between 1 and 10,000
	        for(int i=0;i<1100;i++){
	             keys[i] = (int)(Math.random()*9999 + 1);
	          k=keys[i];
	            l=0;
	           while(l<m){
	        	 j=((k%m)+(l*(1+k%(m-1))))%m;  //Double Hashing h(k, i) = (h_0(k) + i*h_1(k)) % m).  
	            if(hashkey[j]== null){
	                hashkey[j]=k; 
	              hashcount[j]=1;
	                break;
	            }
	            else if(hashkey[j]== (Object)k){
		             hashkey[j]=k;
		             hashcount[j]=hashcount[j]+1;
		             break;
		             } 
	            else{            
	         		collisions=collisions+1;
	               	l=l+1;
	             	}
	           } 
	           /*if(l>m){
	        	  System.out.println("Hash table over flow");
	           }	*/	            
	        }
	             System.out.println("Number of collisions for Double Hashing: " +collisions +"\n");
	             System.out.println("HashTable using Double Hashing with m="+m+ ":\n");
	         		for (int i=0;i<m;i++){
	            	    System.out.println(hashcount[i]+"   "+hashkey[i]); 
	         	    }        	
	    }

}

