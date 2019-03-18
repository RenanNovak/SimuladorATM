package controller;

import java.util.Scanner;

import entity.Users;

public class Main {

	public static void main(String[] args) {
		int option;
		int opcao = 0;
		double balance = 0;
		Users users = new Users();
		Scanner scan = new Scanner(System.in);
		while(opcao != 3){
			System.out.println("1. Cadastro de conta. \n2. Logar em sua conta."
					+ "\n3. Desligar sistema.");
			opcao = scan.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("Você irá cadastrar sua conta corrente.");
				users.addUser();
				break;
			case 2:
				int index = users.login();
				if (index == -1){
					System.out.println("Verifique se as informações foram digitadas corretamente.");
					break;
				}else{
					option = 0;
					while (option != 5) {
						System.out.println("Escolha uma opção:"
											+ "\n1. Consultar saldo."
											+ "\n2. Sacar dinheiro."
											+ "\n3. Transferir dinheiro."
											+ "\n4. Depositar dinheiro."
											+ "\n5. Sair.");
						option = scan.nextInt();
						switch (option) {
						case 1:
							System.out.println("Você irá consultar seu saldo.");
							balance = users.readBalance(index);
							System.out.println(balance);
							break;
						case 2:
							System.out.println("Você irá sacar dinheiro.");
							users.withdraw(index);
							break;
						case 3:
							System.out.println("Você irá transferir dinheiro.");
							users.transfer(index);
							break;
						case 4:
							System.out.println("Você irá depositar dinheiro.");
							users.deposit(index);
							break;
						case 5:
							break;
						default:
							System.out.println("Escolha uma opção válida.");
							break;
						}
					}
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Escolha uma opção válida.");
				break;
			}
		}
		scan.close();
	}
}
