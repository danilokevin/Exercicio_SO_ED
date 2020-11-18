package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FifaController implements IFifaController {

	@Override
	public Stack<String> empilhaBrasileiros(String caminho, String nome) throws IOException {
		
		File arq = new File(caminho, nome);
		Stack<String> pilha = new Stack<String>(); 
		
		if (arq.exists() && arq.isFile()){
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
		
		
			while (linha != null){
				if (linha.contains("Brazil")){
					pilha.push(linha);
				}
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} else {
			throw new IOException("Arquivo inválido");
		}
		
		return pilha;
	}

	@Override
	public void desempilhaBonsBrasileiros(Stack<String> pilha) throws IOException {
		
		int tamanhoPilha = pilha.size();
		System.out.println("Jogadores brasileiros com overall > 80 \nNome, Overall \n");
		
		for (int i = 0; i < tamanhoPilha; i++){
			String aux = pilha.pop();
			String vetor[] = aux.split(",");
			int overall = Integer.parseInt(vetor[7]);
			if (overall > 80){
				System.out.println(vetor[2] + ", " + vetor[7]);
			}
			
		}
		
	}

	@Override
	public List<String> listaRevelacoes(String caminho, String nome) throws IOException {
		
		File arq = new File(caminho, nome);
		List<String> lista = new LinkedList<String>(); 
		
		if (arq.exists() && arq.isFile()){
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
		
		
			while (linha != null){
				String vetor[] = linha.split(",");
				int idade = Integer.parseInt(vetor[3]);
				if (idade <= 20){
					lista.add(linha);
				}
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} else {
			throw new IOException("Arquivo inválido");
		}
		
		return lista;
	}

	

	@Override
	public void buscaListaBonsJovens(List<String> lista) throws IOException {
		
		int tamanhoLista = lista.size(); 
		
		System.out.println("Jogadores com idade <= 20 e Overall > 80 \nNome, Idade, Overall \n");
		for (int i = tamanhoLista - 1; i >= 0; i--){ 
			String aux = lista.get(i); 
			String vetor[] = aux.split(",");
			int overall = Integer.parseInt(vetor[7]);
			
			if (overall > 80){
				System.out.println(vetor[2] + ", " + vetor[3] + ", " + vetor[7]);
			}
		}
		
	}

}
