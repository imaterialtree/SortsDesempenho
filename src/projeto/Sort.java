package projeto;

public class Sort {
    // BubbleSort
    static public void bubbleSort(int[] vetor) {
        for (int i=1; i<vetor.length; i++) {
            for (int j=0; j<vetor.length-i; j++) {
                if (vetor[j]>vetor[j+1]) {
                    int bubble = vetor[j+1];
                    vetor[j+1] = vetor[j];
                    vetor[j] = bubble;
                }
            }
        }
    }

    // InsertSort
    public static void insertSort(int[] vetor) {
        for (int i=0; i< vetor.length-1; i++) {
            int key = vetor[i+1];
            for (int j=i; j>=0; j--) {
                if (vetor[j] > key) {
                    vetor[j+1] = vetor[j];
                    if (j==0) {
                        vetor[j] = key;
                    }
                }else {
                    vetor[j+1] = key;
                    break;
                }
            }
        }
    }

    //SelectionSort
    public static void selectionSort(int[] vetor) {
        for (int i=0; i<vetor.length-1; i++) {
            int e = vetor[i];
            int menor=i+1;
            for (int j=i+2; j<vetor.length; j++) {
                if (vetor[j]<vetor[menor]) {
                    menor = j;
                }
            }
            if (vetor[menor] < e) {
                vetor[i] = vetor[menor];
                vetor[menor] = e;
            }
        }
    }

    // ShellSort
    public static void shellSort(int[] v){
        int aux, j;
        int h=v.length/2;
        while (h>0) {
            for (int i = h; i < v.length; i++) {
                j = i;
                aux = v[i];
                while (j >= h && aux < v[j - h]) {
                    v[j] = v[j - h];
                    j--;
                }
                v[j] = aux;
            }
            h=h/2;
        }
    }

    // MergeSort
    public static void mergeSort(int[] v, int[] aux, int start, int end) {
        if (start < end) {
            int mid = (start+end) / 2;
            mergeSort(v, aux, start, mid);
            mergeSort(v, aux, mid+1, end);
            intercalar(v, aux, start, mid, end);
        }
    }
    public static void intercalar(int[] v, int[] aux, int start, int mid, int end) {
        for(int k=start; k <= end; k++) {
            aux[k] = v[k];
        }
        int i = start;
        int j = mid + 1;
        for (int k=start; k <= end; k++) {
            if (i > mid) {
                v[k] = aux[j++];
            } else if (j > end) {
                v[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                v[k] = aux[i++];
            } else {
                v[k] = aux[j++];
            }
        }
    }
    // Short call mergeSort

    public static void mergeSort(int[] v) {
        int[] aux = new int[v.length];
        mergeSort(v, aux, 0, v.length-1);
    }
    // QuickSort
    public static void quickSort(int[] v, int start, int end) {
        int i, j, pivot, aux;
        i = start;
        j = end;
        pivot = v[(start+end)/2];
        while(i<j) {
            while(v[i]<pivot) {
                i++;
            }
            while(v[j]>pivot) {
                j--;
            }
            if(i<=j){
                aux = v[i];
                v[i] = v[j];
                v[j] = aux;
                i++;
                j--;
            }
        }
        if(j>start) {
            quickSort(v, start, j);
        }
        if(i<end) {
            quickSort(v, i, end);
        }
    }
    // Short call quickSort
    public static void quickSort(int[] v) {
        quickSort(v, 0, v.length-1);
    }

}