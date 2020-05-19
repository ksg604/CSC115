import java.util.Arrays;

public class HashFunction {

	int[] hashTable;
	int arraySize;
	int count;
	
	public HashFunction(int size)  {
		
		arraySize = size;
		hashTable = new int[size];
		
	}
	
	
	public void linearProbe(int[] intsForArray, int[] hashTable) {
		for(int i = 0; i < intsForArray.length; i++) {
			int newElementVal = intsForArray[i];
			
			int arrayIndex = newElementVal % arraySize;
			
			System.out.println("Index "+ arrayIndex + " assigned to value " +newElementVal);
			
			while(hashTable[arrayIndex] != 0) {
				++arrayIndex;
				
				if(arrayIndex == arraySize) {
					arrayIndex = 0;
				}
				
				System.out.println("Collision Try " + arrayIndex + " Instead");
				
				arrayIndex %= arraySize;
			}
			
			hashTable[arrayIndex] = newElementVal;
		}
	}
	

	
	public void quadProbe(int[] intsForArray, int[] hashTable) {
		for(int i = 0; i < intsForArray.length; i++) {
			int newElementVal = intsForArray[i];
			int arrayIndex = newElementVal % arraySize;
			
			System.out.println("Index " + arrayIndex + " Assigned For Value " + newElementVal);
			
			int currIndex = arrayIndex;
			int stepSize = 0;
			
			while(hashTable[arrayIndex] != 0) {
				++stepSize;
				arrayIndex = currIndex + stepSize * stepSize;
				
				arrayIndex %= arraySize;
				
				System.out.println("Collision Try " + arrayIndex + " Instead");
			}
			hashTable[arrayIndex] = newElementVal;
		}
	}
	
	public void doubleHash(int[] intsForArray, int[] hashTable) {
		for(int i = 0; i < intsForArray.length; i++) {
			int newElementVal = intsForArray[i];
			int arrayIndex = newElementVal % arraySize;
			
			System.out.println("Index " + arrayIndex + " Assigned For Value " + newElementVal);
			
			int currIndex = arrayIndex;
			int stepSize = 7 - (newElementVal%7);
			
			while(hashTable[arrayIndex] != 0) {
				arrayIndex = arrayIndex + stepSize * stepSize;
				
				arrayIndex %= arraySize;
				
				System.out.println("Collision Try " + arrayIndex + " Instead");
			}
			hashTable[arrayIndex] = newElementVal;
		}
	}
	
	
	public int findKey(int key) {
		
		int arrayIndexHash = key % arraySize;
		
		while(hashTable[arrayIndexHash] != 0) {
			
			if(hashTable[arrayIndexHash] == key) {
				return hashTable[arrayIndexHash];
			}
			
			++arrayIndexHash;
			
			arrayIndexHash %= arraySize;
		}
		return -1; //key was not in the hashtable
	}
	
	public int findKeyQP(int key) {
		
		int arrayIndexHash = key % arraySize;
		int currIndex = arrayIndexHash;
		int stepSize = 0;
		
		while(hashTable[arrayIndexHash] != 0) {
			
			++stepSize;
			if(hashTable[arrayIndexHash] == key) {
				System.out.println(key+ " was found in index "+arrayIndexHash);
				return hashTable[arrayIndexHash];
			}
			
			arrayIndexHash = currIndex + stepSize;
			
			arrayIndexHash %= arraySize;
		}
		return -1; //key was not in the hashtable
	}
	
	public int findKeyDH(int key) {
		
		int arrayIndexHash = key % arraySize;
		
		int stepSize = 7 - (key%7);
		
		while(hashTable[arrayIndexHash] != 0) {
			
			if(hashTable[arrayIndexHash] == key) {
				
				System.out.println(key+ " was found in index " + arrayIndexHash);
				return hashTable[arrayIndexHash];
			}
			
			arrayIndexHash = arrayIndexHash + stepSize;
			
			arrayIndexHash %= arraySize;
		}
		return -1; //key was not in the hashtable
	}
	
	private void clearHashTable() {
		for(int i = 0; i < hashTable.length; i++) {
			hashTable[i] = 0;
		}
	}

	



	
	public void displayTheStack() {

		int increment = 0;

		for (int m = 0; m < 3; m++) {

			increment += 10;

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				System.out.format("| %3s " + " ", n);

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				if (hashTable[n] ==(0))
					System.out.print("|      ");

				else
					System.out
							.print(String.format("| %3s " + " ", hashTable[n]));

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

		}

	}
	

	
	public static void main(String[] args) {
		
		HashFunction func = new HashFunction(30);
		
		int[] elementsToAdd = {134,212,3353,553,712,1723, 299, 599, 312, 242, 272, 302, 329, 328, 358};
		
		func.linearProbe(elementsToAdd,func.hashTable); //linear probing
		
		func.displayTheStack();
		
		func.clearHashTable();
		
		func.quadProbe(elementsToAdd,func.hashTable); //quad probing
		
		func.displayTheStack();
		
		func.findKeyQP(712);
		
		func.clearHashTable();
		
		func.doubleHash(elementsToAdd,func.hashTable); //doublehash
		
		func.displayTheStack();
		func.findKeyDH(299);
		
		func.clearHashTable();
	}

}