package Exemplo_01;

import jomp.runtime.OMP;

public class hello_normal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int myId;
		
		OMP.setNumThreads(15);
		
		//omp parallel private(myId)
		{
			myId = OMP.getThreadNum();
			
			System.out.println("Hello from " + myId);
		}
	}

}