import java.util.GregorianCalendar;

import utilitarios.Console;
import utilitarios.LtpUtil;


public class Prova {

	public static void main(String[] args) {
		int opcao;
		// TODO Auto-generated method stub
		do {
			
			
			Console.printPrompt("\nMenu - Prova\n");
			System.out.println("");
			System.out.println("1- Valor Emprestimo:");
			System.out.println("2- Validar Matricula:");
			System.out.println("3- Listar Domingos:");
			
			System.out.println("0-Sair");
			opcao = Console.readInt("Informe a op��o: ");
			
			
			switch (opcao) {
				case 1:
					double valorEmprestimo = Console.readDouble("Valor do Emprestimo: ");
					double juros = Console.readDouble("Taxa de Juros Mensal: ");
					int prazo = Console.readInt("Prazo: ");
					System.out.println("Valor das prestacoes  Mensal: " + (valorPrestacao(valorEmprestimo, juros, prazo)));
					Console.readLine("");
					
					break;
				case 2:
					String matricula = Console.readLine("\nInforme a matricula: ");
					if (validarMatricula(matricula))
						System.out.println("\nA matricula " + matricula + " Valida!\n");
					else
						System.out.println("\nA matricula " + matricula + " Nao e Valida!\n");
					Console.readLine("");
					break;
				
				case 3:
					int ano;
					GregorianCalendar[] calendario = new GregorianCalendar[5*12];							
					ano = utilitarios.Console.readInt("Informe o ano: ");
					
					calendario = calendarioDominical(ano);
					
					for (int i=0;i<calendario.length;i++){
						if (calendario[i] != null)
						System.out.println(LtpUtil.formatarData(calendario[i], "dd/MM/yyyy"));	
					}	
					break;
			
				case 0:
					break;
					
				default:
					System.out.println("Op��o inv�lida!");
					break;
				}
		} while (opcao!=0);
     
	
	}



public static double valorPrestacao (double valorEmprestimo, double juros, int prazo) {
	juros = juros / 100;
	return valorEmprestimo * (juros * Math.pow(1+juros, prazo)) / (Math.pow(1+juros, prazo)-1);

}

private static boolean validarMatricula(String s)
{
  int[] tabela = { 6, 5, 4, 3, 2 };
  if ((s.trim().equals("")) || (!s.matches("[0-9]*")) || (s.length() < 2 || s.length() > 6)) {
    return false;
  }
  String aux = s;
  int soma = 0;
  for (int j = 0; j < 6 - s.length(); j++) {
    aux = "0" + aux;
  }
  for (int i = 0; i < aux.length() - 1; i++) {
    soma += Integer.parseInt(aux.substring(i, i + 1)) * tabela[i];
  }
  int dv = 11 - (soma % 11);
	if (dv > 9) dv = 0;
	String x = Integer.toString(dv);
	if (s.substring(s.length()-1,s.length()).equals(x)) {
		return true;
	} else {
		return false;
	}
}


private static GregorianCalendar []  calendarioDominical (int ano){		
	
	GregorianCalendar [] calendario = new GregorianCalendar[5*12];
	GregorianCalendar gc = new GregorianCalendar(ano, 0, 1);
	int j = 0;
	
	for (int i = 0; i < gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR); i++ ){
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY){
			calendario[j] = new GregorianCalendar(ano, gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DAY_OF_MONTH));
			j++;
		}
		gc.add(GregorianCalendar.DAY_OF_YEAR, 1);
	}				
	return calendario;		
}




}// fim classe prova
