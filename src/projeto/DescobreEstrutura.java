/*
DESCUBRA A ESTRUTURA DE DADOS
Existe uma estrutura de dados com os comandos
1 x
Coloque o elemento x
2
retire um elemento

Dada uma sequência de operações que retornam valores, você vai adivinhar a estrutura
de dados. É uma pilha, uma fila ou uma fila de prioridade!

Entrada
Existem muitos casos de testes. A primeira linha de cada caso é um único inteiro
n (1 <= n <= 1000). Cada uma das seguintes n linhas é um comando do tipo
1, ou 2, seguido de um número inteiro x. Isso significa que depois de
executar um comando do tipo 2, obtemos um elemento x sem erros. O valor de x é sempre
um número inteiro, positivo e não maior do que 100. O final da entrada é determinado
pelo final do arquivo (EOF). O tamanho do arquivo de entrada não excede 1MB.
*/

package projeto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DescobreEstrutura {
    static List<Integer> vetor = new ArrayList<>();
    static int inicio, fim, maior;
    static boolean isFila, isFilaPri, isPilha;

    public static void main(String[] args) {
        Scanner input = new Scanner(DescobreEstrutura.class.getResourceAsStream("entrada.txt"));
        do {
            reset();
            int n = input.nextInt();
//            System.out.println(n+" operacoes");
            for (int i=0; i<n; i++) {
                switch (input.nextInt()) {
                    case 1 -> add(input.nextInt());
                    case 2 -> remove(input.nextInt());
                    default -> System.out.println("Operação fora de escopo");
                }
//                System.out.println(vetor.toString());
//                System.out.println(String.format("i:%d,  e:%d,  m:%d,  f:%b,  p:%b,  fp:%b", inicio, fim, maior, isFila, isPilha, isFilaPri));
            }

            if (!(isPilha || isFila || isFilaPri)) {
                System.out.print("impossível   ");
            }else if ((isPilha&&isFila) || (isPilha&&isFilaPri) || (isFila&&isFilaPri)) {
                System.out.print("Não tenho certeza. Pode ser: ");
            }
            if (isPilha) System.out.print("pilha | ");
            if (isFila) System.out.print("fila | ");
            if (isFilaPri) System.out.print("fila de prioridade | ");
            System.out.println("\b\b\b");
        } while (input.hasNextLine());

        // contar quantos boolean tem:

    }
    // inicializa/reseta as variáveis para o próximo conjunto de operações
    static void reset() {
        vetor = new ArrayList<>();
        inicio=fim=maior= -1;
        isFilaPri=isFila=isPilha= true;
    }

    static void add(int i) {
        vetor.add(i);
        // atualiza variáveis
        if (vetor.size()==1) {
            inicio = i;
        }
        fim = i;
        maior = Math.max(maior, i);
//        System.out.println("Adiciona " + i);
    }

    static boolean remove(int i) {
        if (vetor.isEmpty()) {
            return false;
        }
        // se já descobriu que não pode ser algum, não valida mais
        isFila = isFila && (i==inicio);
        isPilha = isPilha && (i==fim);
        isFilaPri = isFilaPri && (i==maior);
        // retorna false se a operação for inválida
        boolean valid = vetor.remove((Object) i);
        // atualizar variáveis
        if (vetor.size()>0) {
            inicio = vetor.get(0);
            fim = vetor.get(vetor.size()-1);
            maior = Collections.max(vetor);
        } else {
            inicio = -1;
            fim = -1;
            maior = -1;
        }
//        System.out.println("Retira "+i);
        return valid;
    }
}
