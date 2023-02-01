# **Projeto Jo-Ken-Pô**

![](https://github.com/edvaldoljr/Projeto-Java-BackEnd-Jogo-Jo-Ken-Po/blob/main/img/img-projeto.gif?raw=true)

Este projeto é um jogo clássico de Jo-Ken-Po que permite ao usuário jogar contra uma inteligência artificial. O objetivo é escolher entre Pedra, Papel ou Tesoura e marcar mais pontos que a IA em um determinado número de rodadas. Ao iniciar o jogo, o usuário é solicitado a informar seu nome e quantas rodadas deseja jogar. A IA fará sua escolha aleatória em cada rodada e o resultado será exibido na tela. No final do jogo, o placar final será mostrado e o vencedor será declarado. Este projeto é uma ótima maneira de praticar programação e aprimorar suas habilidades de lógica. Além disso, é uma diversão garantida para todas as idades.

## Para executar esse projeto de Jo-ken-Pô, será necessário:

- Java Development Kit (JDK) instalado na máquina;
- Uma IDE de desenvolvimento Java, como o Eclipse ou o IntelliJ IDEA;
- Conhecimento básico em Java.

**Também é importante destacar que o código foi escrito com base em Java 14 e pode haver diferenças em versões anteriores.**

------

# **Desenvolvimento**

## class Player

Esta classe `Player` representa um jogador em um jogo. Ela possui três variáveis membro: `name` para armazenar o nome do jogador, `score` para armazenar a pontuação do jogador e dois métodos construtores para inicializar o nome do jogador e definir a pontuação inicial como 0.

O método `incrementScore` incrementa a pontuação do jogador em 1. Os métodos `getName` e `getScore` retornam, respectivamente, o nome e a pontuação do jogador. Os métodos `setName` e `setScore` permitem definir o nome e a pontuação do jogador, respectivamente.

Em resumo, esta classe fornece uma maneira de representar um jogador em um jogo, incluindo informações como nome e pontuação, bem como métodos para gerenciar essas informações.

```java
public class Player {
    // Declaração de variável de nome do jogador
    private String name;
    // Declaração de variável de pontuação do jogador
    private Integer score;

    // Construtor que inicializa o nome do jogador e define a pontuação inicial como 0
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // Método que incrementa a pontuação do jogador em 1
    public void incrementScore(){
        setScore(getScore() + 1);
    }

    // Método que retorna o nome do jogador
    public String getName() {
        return name;
    }

    // Método que define o nome do jogador
    public void setName(String name) {
        this.name = name;
    }

    // Método que retorna a pontuação do jogador
    public Integer getScore() {
        return score;
    }

    // Método que define a pontuação do jogador
    public void setScore(Integer score) {
        this.score = score;
    }
}

```

## record JokenPo

Este código implementa um jogo de JokenPo simples, onde o jogador joga contra uma inteligência artificial. O jogo consiste em escolher entre "pedra", "papel" ou "tesoura", e o vencedor é determinado por regras padrão.

A classe principal `JokenPo` possui três campos: o jogador `user`, a inteligência artificial `IA` e o número de rodadas `rounds`. O método `toPlayer` é responsável por executar as rodadas do jogo, pedindo para o usuário informar sua jogada e gerando a jogada da inteligência artificial. O método `winnerRound` determina o vencedor de cada rodada com base na diferença entre as jogadas do usuário e da IA. O método `showFinalResult` exibe o placar final e o vencedor geral do jogo.

O método `choiceIA` gera uma jogada aleatória para a inteligência artificial, usando a classe `Random` do pacote `java.util`. Já o método `choiceUser` pede ao usuário para informar sua jogada, usando a classe `Scanner` do pacote `java.util`.

Este código foi escrito na linguagem Java, utilizando a sintaxe de records, uma nova feature adicionada a partir da versão 14 da linguagem.

```java
import java.util.Random;
import java.util.Scanner;

public record JokenPo(
    // objeto que representa o usuário do jogo
    Player user,
    // objeto que representa a inteligência artificial do jogo
    Player IA,
    // número de rodadas a serem jogadas
    int rounds
) {

    // Método responsável por executar o jogo
    public void toPlayer() {
        System.out.println("---- SEJA BEMVINDO(A) " + user.getName() + " ----\n");

        // loop que executa o jogo por todas as rodadas
        for (int i = 1; i <= rounds; i++) {
            // escolha do usuário
            int choiceUser = choiceUser();
            // verifica se a escolha do usuário está dentro dos valores válidos (1, 2 ou 3)
            if (choiceUser < 1 || choiceUser > 3) {
                System.out.println("\nJOGADA INVALIDA! (1,2 ou 3): ");
                System.out.println("\tPONTO PARA " + IA.getName() + "\n");

                // adiciona ponto para a inteligência artificial, pois a escolha do usuário não é válida
                IA.incrementScore();
                // pula para a próxima rodada
                continue;
            }

            // escolha da inteligência artificial
            int choiceIA = choiceIA();

            System.out.println("\n" + choiceUser  + " X " + choiceIA );

            // resultado da rodada (diferença das escolhas)
            int result = choiceUser - choiceIA;

            // determina o vencedor da rodada
            winnerRound(result);
        }
    }

    // Método responsável por exibir o resultado final do jogo
    public void showFinalResult(){
        System.out.println("\n****************************************\n");

        // pontuação final do usuário
        Integer finalScoreUser = user.getScore();

        // pontuação final da inteligência artificial
        Integer finalScoreIA = IA.getScore();
        System.out.println("\n\tPLACAR FINAL: " + user.getName() + " " + finalScoreUser + " X " + finalScoreIA + " " + IA.getName());

        // verifica se houve empate
        if (finalScoreUser == finalScoreIA) {
            System.out.println("\t\t\tEMPATE");
        } else {
            // determina o vencedor final do jogo
            String finalWinner = (finalScoreUser > finalScoreIA) ? user.getName() : IA.getName();

            System.out.println("\t\tVENCEDOR: " + finalWinner.toUpperCase());
        }
        System.out.println("\n****************************************\n");
    }

    
    // Método responsável por determinar o vencedor de uma rodada
    private void winnerRound(int result) {
        String winnerRound;

        if (result == 0) {
            winnerRound = "-- EMPATE --";
        } else {
            if (result == -1 || result == 2) {
                IA().incrementScore();
                winnerRound = IA().getName();
            } else {
                user.incrementScore();
                winnerRound = user.getName();
            }
        }
        System.out.println("\nVencedor do Round: " + winnerRound + "\n");
    }
    private int choiceIA() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
    private int choiceUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[1] - PEDRA");
        System.out.println("[2] - PAPEL");
        System.out.println("[3] - TESOURA");

        System.out.print("Informe sua jogada:");
        return scanner.nextInt();
    }
}

```

## class Main

O código abaixo implementa um jogo chamado "Jo-Ken-Po". O jogo começa com a execução do método `startGame`, que solicita o nome do jogador e o número de rodadas a serem jogadas. Em seguida, as instâncias de `Player` são criadas, uma para o jogador e uma para o computador (IA).

Em seguida, é criada uma instância de `JokenPo` passando como parâmetros as instâncias de `Player` e o número de rodadas. O método `toPlayer` é responsável por exibir as jogadas e atualizar o placar a cada rodada. Quando todas as rodadas são concluídas, o método `showFinalResult` é chamado para exibir o resultado final.

A classe `Main` é responsável por inicializar o jogo, criando uma instância de `JokenPo` e chamando os métodos `toPlayer` e `showFinalResult`.

```java
import java.util.Scanner;

public class Main {
    // Método que inicia o jogo
    public static JokenPo startGame() {
        System.out.println("***** JO-KEN-PO *****\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe seu nome:");
        String playerName = scanner.next().toUpperCase();

        // Cria um jogador de usuário
        Player user = new Player(playerName);
        // Cria um jogador IA
        Player IA = new Player("IA");

        System.out.print(playerName + " informe quantos rounds você deseja jogar: ");
        int rounds = scanner.nextInt();

        // Retorna um novo objeto JokenPo
        return new JokenPo(user, IA, rounds);
    }

    public static void main(String[] args) {
        // Inicia o jogo
        JokenPo jokenPo = startGame();
        // Executa a partida de JokenPo
        jokenPo.toPlayer();
        // Mostra o resultado final
        jokenPo.showFinalResult();
    }
}

```

# ⭐️ **Deixe um Star** ⭐️

Obrigado por conferir meu repository! É muito gratificante saber que alguém está interessado no meu trabalho. Se você gostou do que viu, deixar um star seria uma grande ajuda no meu crescimento e me motivaria a continuar fazendo projetos. O apoio de pessoas como você é fundamental para que eu possa seguir evoluindo e produzindo conteúdos cada vez melhores. Obrigado mais uma vez e espero que você possa acompanhar meus futuros projetos!