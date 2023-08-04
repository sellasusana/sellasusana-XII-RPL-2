import java.util.Scanner;


public class HelloWord{
	public static void main(String[] args) {
		Scanner mySuhu = new Scanner(System.in);
		System.out.print("Masukan Suhu Celcius : ");
		
		double celc, fahrenheit, kelvin;
		
		celc = mySuhu.nextDouble();
		fahrenheit = (9/5* celc)+ 32;
		kelvin = celc + 273.15;
		
		System.out.print("\n");
		System.out.println("Suhu Fahreheit :" + fahrenheit);
		System.out.println("Suhu Kelvin :" + kelvin);
	}
}