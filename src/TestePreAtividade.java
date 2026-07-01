public class TestePreAtividade {

    public static void main(String[] args) {

        // 1. Gere uma lista com N = 10.000 valores inteiros aleatórios, usando Random
        // com semente fixa (new
        // Random(42)), para que o experimento seja reproduzível.

        int N = 10000;
        int[] lista = new int[N];
        java.util.Random random = new java.util.Random(42);
        java.util.Set<Integer> inseridos = new java.util.HashSet<>();

        int count = 0;
        while (count < N) {
            int valor = random.nextInt(100000); // valores aleatórios entre 0 e 99.999
            if (inseridos.add(valor)) {
                lista[count] = valor;
                count++;
            }
        }

        // 2. Insira essa mesma sequência de valores, na mesma ordem, em uma
        // ABB<Integer, Integer> e em uma
        // AVL<Integer, Integer> (use o próprio valor como chave e como item).

        ABB<Integer, Integer> abb = new ABB<>();
        AVL<Integer, Integer> avl = new AVL<>();

        for (int i = 0; i < N; i++) {
            abb.inserir(lista[i], lista[i]);
            avl.inserir(lista[i], lista[i]);
        }

        // 3. Gere uma segunda lista com M = 1.000 valores aleatórios, representando
        // "pedidos de busca" (alguns estarão
        // nas árvores, outros não).

        int M = 1000;
        int[] pedidosBusca = new int[M];
        for (int i = 0; i < M; i++) {
            pedidosBusca[i] = random.nextInt(100000); // valores aleatórios entre 0 e 99.999
        }

        // 4. Para cada valor dessa segunda lista, pesquise-o nas duas árvores e acumule
        // o total de comparações
        // (getComparacoes()) e o tempo total (getTempo()) gasto por cada estrutura.

        long tempoTotalABB = 0;
        long tempoTotalAVL = 0;
        long comparacoesTotalABB = 0;
        long comparacoesTotalAVL = 0;

        for (int i = 0; i < M; i++) {
            long inicioABB = System.nanoTime();
            try {
                abb.pesquisar(pedidosBusca[i]);
            } catch (java.util.NoSuchElementException e) {
            }
            long fimABB = System.nanoTime();
            tempoTotalABB += (fimABB - inicioABB);
            comparacoesTotalABB += abb.getComparacoes();

            long inicioAVL = System.nanoTime();
            try {
                avl.pesquisar(pedidosBusca[i]);
            } catch (java.util.NoSuchElementException e) {
            }
            long fimAVL = System.nanoTime();
            tempoTotalAVL += (fimAVL - inicioAVL);
            comparacoesTotalAVL += avl.getComparacoes();
        }

        System.out.println("ABB: Total de comparações = " + comparacoesTotalABB +
                ", Tempo total gasto = " + tempoTotalABB + " nanosegundos");
        System.out.println("AVL: Total de comparações = " + comparacoesTotalAVL +
                ", Tempo total gasto = " + tempoTotalAVL + " nanosegundos");

        // 5. Repita os passos 2 a 4, mas agora inserindo os N valores em ordem
        // crescente nas duas árvores.
        
        ABB<Integer, Integer> abbOrdenada = new ABB<>();
        AVL<Integer, Integer> avlOrdenada = new AVL<>();
        
        // Clona e ordena a lista original para inserir os mesmos dados
        int[] listaOrdenada = lista.clone();
        java.util.Arrays.sort(listaOrdenada);

        for (int i = 0; i < N; i++) {
            abbOrdenada.inserir(listaOrdenada[i], listaOrdenada[i]);
            avlOrdenada.inserir(listaOrdenada[i], listaOrdenada[i]);
        }

        tempoTotalABB = 0;
        tempoTotalAVL = 0;
        comparacoesTotalABB = 0;
        comparacoesTotalAVL = 0;

        for (int i = 0; i < M; i++) {
            // Busca na ABB Ordenada
            long inicioABB = System.nanoTime();
            try {
                abbOrdenada.pesquisar(pedidosBusca[i]);
            } catch (java.util.NoSuchElementException e) {
                // Item não encontrado
            }
            long fimABB = System.nanoTime();
            tempoTotalABB += (fimABB - inicioABB);
            comparacoesTotalABB += abbOrdenada.getComparacoes();

            // Busca na AVL Ordenada
            long inicioAVL = System.nanoTime();
            try {
                avlOrdenada.pesquisar(pedidosBusca[i]);
            } catch (java.util.NoSuchElementException e) {
                // Item não encontrado
            }
            long fimAVL = System.nanoTime();
            tempoTotalAVL += (fimAVL - inicioAVL);
            comparacoesTotalAVL += avlOrdenada.getComparacoes();
        }

        System.out.println("ABB (inserção ordenada): Total de comparações = " + comparacoesTotalABB +
                ", Tempo total gasto = " + tempoTotalABB + " nanosegundos");
        System.out.println("AVL (inserção ordenada): Total de comparações = " + comparacoesTotalAVL +
                ", Tempo total gasto = " + tempoTotalAVL + " nanosegundos");
    }
}
