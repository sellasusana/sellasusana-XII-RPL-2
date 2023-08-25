import java.util.Scanner;

public class Calculators {
	public static void main(String[] args){
		int operator;
		String again;
		double result, number1, number2;
		boolean question = true;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\n==> Welcome To My Calculator <==\n");
		
	while(question){
		System.out.println(" (1). [+] Pertambahan");
		System.out.println(" (2). [-] Pengurangan");
		System.out.println(" (3). [/] Pembagian");
		System.out.println(" (4). [x] Perkalian");
		System.out.println(" (5). Leave Calculator\n");
		System.out.print("Masukan operator yang anda inginkan:");
		
		operator = scanner.nextInt();
		
		if(operator == 5){
			System.out.println("\n==> Thanks for use my Calculator <==\n");
			break;
		}
		
		if(operator <=4&&operator >=1){
			System.out.print("\nMasukan nilai pertama :");
			number1 = scanner.nextDouble();
			
			System.out.print("Masukan nilai kedua :");
			number2 = scanner.nextDouble();
			
			switch(operator){
				case 1:
					result = number1 + number2;
					System.out.println("Hasilnya:" + result);
					break;
				case 2:
					result = number1 - number2;
					System.out.println("Hasilnya:" + result);
					break;
				case 3:
					result = number1 / number2;
					System.out.println("Hasilnya:" + result);
					break;
				case 4:
					result = number1 * number2;
					System.out.println("Hasilnya:" + result);
					break;
			}
			
			System.out.print("\nMau mencoba kembali (y/n)?");
			again = scanner.next();
			
			if(again.toLowerCase().equals("n")){
				System.out.println("==> Thanks for use my calculator <==");
				
				question = false;
			}
		}else{
			System.out.println("Your input is not valid");
			break;
		}
	}
  }
}