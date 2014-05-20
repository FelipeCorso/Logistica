package Exemplo_Critical;

import jomp.runtime.OMP;

public class ExemploCritical {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i = 0;
		int n = 30;
		int x = 0;
		
		OMP.setNumThreads(2);
		
		//omp parallel private (i)
		{
			//omp sections
			{							
				//omp section
				{
					for (i = 0; i < n; i++) {
						//omp critical
						{
							x = x + 1;	
						}					
					}
				}
				//omp section
				{
					for (i = 0; i < n; i++) {
						//omp critical
						{
							x = x - 1;
						}
					}
				}
			}
			
		}
		
		System.out.println("resultado " + x);
	}

}
