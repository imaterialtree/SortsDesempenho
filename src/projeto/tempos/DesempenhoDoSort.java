package projeto.tempos;

import projeto.Sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

import static java.lang.Math.pow;

public class DesempenhoDoSort {
    public static void main(String[] args) throws IOException {
        // Lista de funções para os testes (usar método Consumer.accept(int[]) para executar)
        Map<String, Consumer<int[]>> sorts = new HashMap<>(){
            {
                put("bubbleSort", Sort::bubbleSort);
                put("insertSort", Sort::insertSort);
                put("selectionSort", Sort::selectionSort);
                put("shellSort", Sort::shellSort);
                put("mergeSort", Sort::mergeSort);
                put("quickSort", Sort::quickSort);
            }
        };
        // tamanho do vetor de cada pilha de testes
        int[] tamanhos = new int[]{10, (int) pow(10, 3), (int) pow(10, 4), (int) pow(10, 5), (int) pow(10, 6)};
        String path = "src/projeto/tempos/tempos.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.append("""
                            TESTE DE TEMPOS
                ------------------------------------
                |     FUNÇÃO    |     TEMPO(ns)    |
                ------------------------------------"""
        );
        for (int t: tamanhos) {
            System.out.println(String.format("Vetor de tamanho %,d", t));
            int[] vetor = new int[t];
            preencher(vetor);
            writer.append(String.format("\n-       VETOR SIZE %-,15d -\n", t));
            writer.flush();
            for (var entry: sorts.entrySet()) { // Map.Entry<String, Consumer> -- use: entry.getKey() | entry.getValue()
                System.out.println(entry.getKey());
                String func = entry.getKey();
                if (t>pow(10, 5) && (func=="bubbleSort"||func=="selectionSort")) {
                    entry.setValue(x -> System.out.println("pular")); // fazer nada
                }
                writer.append(String.format("| %-13s |", func));
                writer.flush();
                long tempoInicial = System.nanoTime();
                entry.getValue().accept(vetor.clone());
                long tempoFinal = System.nanoTime();
                writer.append(String.format(" %,16d |\n", tempoFinal-tempoInicial));
            }
        }
        writer.close();
    }
    static void preencher(int[] vetor) {
        Random rd = new Random();
        for (int i=0; i<vetor.length; i++) {
            vetor[i] = rd.nextInt(1,100);
            rd.setSeed(rd.nextLong());
        }
    }
}
