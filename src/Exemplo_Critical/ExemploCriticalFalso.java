package Exemplo_Critical;

import jomp.runtime.OMP;

public class ExemploCriticalFalso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
				int i = 0;
				int n = 30;
				int x = 0;
				int temp = 0;
				
				OMP.setNumThreads(2);
				
				//omp parallel private (i)
				{
					//omp sections
					{							
						//omp section
						{
							for (i = 0; i < n; i++) {
								temp = x + 1;	
								x = temp;
							}
						}
						//omp section
						{
							for (i = 0; i < n; i++) {
								temp = x - 1;
								x = temp;
							}
						}
					}
					
				}
				
				System.out.println("resultado " + x);
	}

}
