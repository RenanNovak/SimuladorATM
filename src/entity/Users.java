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

	// Cadastro de usuário: Adiciona mais um valor nas ArrayLists;
	public void addUser() {
		System.out.println("Escolha seu id (numeros apenas)");
		accnumber = scan.nextInt();
		pos = userArray.indexOf(accnumber);

		if (pos == -1){
			System.out.println("Escolha sua senha (números apenas)");
			password = scan.nextInt();
			userArray.add(accnumber);
			passwordArray.add(password);
			balanceArray.add(0.0);
			System.out.println(userArray.get(userArray.indexOf(accnumber)));
			System.out.println(passwordArray.get(userArray.indexOf(accnumber)));
		}else{
			System.out.printf("Número de conta não disponível. Já cadastrado na posição ", pos, "\n");
		}
	}

	// Login: Solicita o número da conta, busca a posição na ArrayList e
	// verifica se a senha está correta;
	public int login() {
		System.out.println("Digite seu número de conta:");
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

	// Consulta de saldo: Retorna o valor disponível na conta do usuário;
	public double readBalance(int index) {
		double balance = balanceArray.get(index);
		return balance;
	}

	// Saque: solicita o valor a ser sacado, verifica o saldo e remove o valor da conta;
	// Não permite sacar valores float por não ser capaz de devolver centavos;
	public void withdraw(int index) {
		System.out.println("Digite o total a ser sacado.");
		int amount = scan.nextInt();

		if (verifybal(position, amount)){
			balanceArray.set(index, (balanceArray.get(index)-amount));
		}else{
			System.out.println("Você não possui saldo suficiente para a transação.");
		}
	}

	// Transferência: Busca a posição da conta na ArrayList, remove o valor da conta do usuário
	// e o adiciona na outra conta informada;
	// Informa o usuário que este não possui saldo e não realiza a transação, se este for o caso;
	public void transfer(int index) {
		System.out.println("Digite o número da conta a ser transferido");
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
				System.out.println("Conta não encontrada.");
			}
		}else{
			System.out.println("Você não possui saldo suficiente para a transação.");
		}
	}

	public void deposit(int index){
		System.out.println("Digite o total a ser depositado:");
		float amount = scan.nextFloat();
		balanceArray.set(index, (balanceArray.get(index)+amount));
	}

	// Verificação de saldo: retorna true se possui saldo suficiente, senão retorna false;
	private boolean verifybal(int index, float amount) {
		if (balanceArray.get(index) >= amount){
			return true;
		}else{
			return false;
		}
	}
}
