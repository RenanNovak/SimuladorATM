package entity;

import java.util.ArrayList;
import java.util.Scanner;

public class Users {
	Scanner scan = new Scanner(System.in);
	private ArrayList<Integer> userArray = new ArrayList<Integer>();
	private ArrayList<Integer> passwordArray = new ArrayList<Integer>();
	private ArrayList<Double> balanceArray = new ArrayList<Double>();
	private int accnumber;
	private int password;
	private int position;
	private int pos;
	private int transfacc;
	private float transfamount;

	// Cadastro de usu�rio: Adiciona mais um valor nas ArrayLists;
	public void addUser() {
		System.out.println("Escolha seu id (numeros apenas)");
		accnumber = scan.nextInt();
		pos = userArray.indexOf(accnumber);

		if (pos == -1){
			System.out.println("Escolha sua senha (n�meros apenas)");
			password = scan.nextInt();
			userArray.add(accnumber);
			passwordArray.add(password);
			balanceArray.add(0.0);
			System.out.println(userArray.get(userArray.indexOf(accnumber)));
			System.out.println(passwordArray.get(userArray.indexOf(accnumber)));
		}else{
			System.out.printf("N�mero de conta n�o dispon�vel. J� cadastrado na posi��o ", pos, "\n");
		}
	}

	// Login: Solicita o n�mero da conta, busca a posi��o na ArrayList e
	// verifica se a senha est� correta;
	public int login() {
		System.out.println("Digite seu n�mero de conta:");
		accnumber = scan.nextInt();
		System.out.println("Digite sua senha:");
		password = scan.nextInt();

		try{
			position = userArray.indexOf(accnumber);
			if (passwordArray.get(position) != password) {
				position = -1;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			position = -1;
		}
		return position;
	}

	// Consulta de saldo: Retorna o valor dispon�vel na conta do usu�rio;
	public double readBalance(int index) {
		double balance = balanceArray.get(index);
		return balance;
	}

	// Saque: solicita o valor a ser sacado, verifica o saldo e remove o valor da conta;
	// N�o permite sacar valores float por n�o ser capaz de devolver centavos;
	public void withdraw(int index) {
		System.out.println("Digite o total a ser sacado.");
		int amount = scan.nextInt();

		if (verifybal(position, amount)){
			balanceArray.set(index, (balanceArray.get(index)-amount));
		}else{
			System.out.println("Voc� n�o possui saldo suficiente para a transa��o.");
		}
	}

	// Transfer�ncia: Busca a posi��o da conta na ArrayList, remove o valor da conta do usu�rio
	// e o adiciona na outra conta informada;
	// Informa o usu�rio que este n�o possui saldo e n�o realiza a transa��o, se este for o caso;
	public void transfer(int index) {
		System.out.println("Digite o n�mero da conta a ser transferido");
		transfacc = scan.nextInt();
		System.out.println("Digite o total a ser transferido");
		transfamount = scan.nextFloat();

		if (verifybal(index, transfamount)) {
			try{
				pos = userArray.indexOf(transfacc);
			}catch(ArrayIndexOutOfBoundsException e){
				pos = -1;
			}

			if (pos != -1){
				balanceArray.set(pos, (balanceArray.get(pos)+transfamount));
				balanceArray.set(index, (balanceArray.get(index)-transfamount));
			}else{
				System.out.println("Conta n�o encontrada.");
			}
		}else{
			System.out.println("Voc� n�o possui saldo suficiente para a transa��o.");
		}
	}

	public void deposit(int index){
		System.out.println("Digite o total a ser depositado:");
		float amount = scan.nextFloat();
		balanceArray.set(index, (balanceArray.get(index)+amount));
	}

	// Verifica��o de saldo: retorna true se possui saldo suficiente, sen�o retorna false;
	private boolean verifybal(int index, float amount) {
		if (balanceArray.get(index) >= amount){
			return true;
		}else{
			return false;
		}
	}
}
