import java.util.Scanner;

public class Ejercicio 
{
	private int accumulator;	
	private int [] memory;	
	private int instructionRegister;	
	private int instructionCounter;	
	private int operationCode;	
	private int operand;	
	
	public Ejercicio ( ) 
	{
		displayWelcomeMessage ();
		initialiseVariables ();
		
	}	
	
	public void displayWelcomeMessage ( ) 
	{
		System.out.printf ("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s %s\n%s %s\n", 
		"*** ¡Bienvenido a Simpletron! ***",
		"*** Ingrese su programa una instrucción ***",
		"*** (o palabra de datos) a la vez en la entrada ***",
		"*** campo de texto. Mostraré la ubicación ***",
		"*** número y un signo de interrogación (?). Luego ***",
		"*** escribe la palabra para esa ubicación. Presiona el ***",
		"*** Botón Listo para dejar de ingresar a tu programa ***",
		"Loc", "Inst", "****", "*****");
	}	
	public void runSimulator () 
	{
		int submittedInstruction = 0;	
		int memoryPointer = 0;	

		Scanner input = new Scanner ( System.in );

		do
		{
			System.out.printf ("%d %s  ", memoryPointer, "?" );
			submittedInstruction = input.nextInt ();
			if ( submittedInstruction != -99999 ) 
				memory [ memoryPointer ] = submittedInstruction;

				memoryPointer++;
			
		} while ( submittedInstruction != -99999 );	

	        System.out.printf ("\n%s%s", "*** Carga del programa completada ***\n",
			"*** Comienza la ejecución del programa **\n");	
		
		
		for ( int code : memory ) 
		{
			
			if ( code != 0 )	
			{
				load ();
				execute ( operand, operationCode );
			}
		}

	}	
	
	public void initialiseVariables ( )
	{
		memory = new int [100];	
		instructionCounter = 0;	
	}	
	public void load ( ) 
	{
		
		
		operationCode = memory [ instructionCounter ] / 100;
		operand = memory [ instructionCounter ] % 100;

	}	
	public void execute (int operands, int operation ) 
	{
	
		switch ( operation ) 
		{
			case 10: 
				Scanner input = new Scanner ( System.in );
				System.out.print ( "Ingrese un número entero (positivo o negativo): " );
				memory [ operands ] = input.nextInt ();	
				break;
			case 11:	
				System.out.println ("El resultado de la opreacion es: " + memory [ operands] );
				break;
			case 20: 
				accumulator = memory [ operands ];
				break;
			case 21: 	
				memory [ operands ] = accumulator;
				break;
			case 30:
				accumulator += memory [ operands ];
				break;
			case 31: 
				accumulator -= memory [ operands ];
				break;
			case 32:	
				accumulator /=  memory [ operands ];
				break;
			case 33: 
				accumulator *= memory [ operands ];
				break;
			case 40:	
				instructionCounter = operands;
				break;
			case 41:	
				if ( accumulator < 0 )
					instructionCounter = operands;
				break;
			case 42:	
				if ( accumulator == 0 )
					instructionCounter = operands;
				break;
			case 43: 	
				dumpTheCore ();	
				System.out.printf ("\n%s\n", "El programa ha terminado");
				System.exit ( 0 );
				break;

		}	

		instructionCounter++;	
	}	

	
	public void dumpTheCore ( )
	{
		System.out.printf ("\n%30s\n%30s\t%s%4d\n%30s\t%2d\n%30s\t%2d\n%30s\t%2d\n%30s\t%2d\n\n%30s\n", "REGISTERS:", 
				"accumulator", "+", accumulator, "instruction counter", instructionCounter, "instruction register",
			       	instructionCounter, "operation code", operationCode, "operand", operand, "MEMORY:" );

		
		for ( int i = 0; i < 10; i++ )
		{
			System.out.printf ( "%6d", i);
		}

		System.out.println ();
		int counter = 0;	
		for (int i = 0; i < 10; i++ ) 
		{
			if ( counter %10 == 0 )
				System.out.printf ("%2d ", counter);
			for (int j = 0; j < 10; j++) 
			{	
				
				if ( memory [ counter ] == 0 )
					System.out.printf ( "%s%s", "+", "0000 ");
				else 
					System.out.printf ("%s%4d ", "+", memory [counter]);
				counter++;

			}	
		       
		System.out.println ();	

		}	 
	}	
}	