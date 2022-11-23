import java.util.Scanner;

public class Main {
    public static JokenPo startGame() {
        System.out.println("***** JO-KEN-PO *****\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe seu nome:");
        String playerName = scanner.next().toUpperCase();

        Player user = new Player(playerName);
        Player IA = new Player("IA");

        System.out.print(playerName + " informe quantos rouns você deseja jogar: ");
        int rounds = scanner.nextInt();

        return new JokenPo(user,IA,rounds);
    }

    public static void main(String[] args) {

        JokenPo jokenPo = startGame();
        jokenPo.toPlayer();
        jokenPo.showFinalResult();
    }
}