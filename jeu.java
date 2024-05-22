import java.util.*;
        import java.util.concurrent.*;

public class jeu {

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Quel est le chef-lieu de la région de l'Agnéby-Tiassa?", "Agboville"));
        questions.add(new Question("Quel est le chef-lieu de la région du Bafing?", "Touba"));
        questions.add(new Question("Quel est le chef-lieu de la région de la Bagoué?", "Boundiali"));
        questions.add(new Question("Quel est le chef-lieu de la région du Bélier?", "Yamoussoukro"));
        questions.add(new Question("Quel est le chef-lieu de la région du Béré?", "Mankono"));
        questions.add(new Question("Quel est le chef-lieu de la région du Bounkani?", "Bouna"));
        questions.add(new Question("Quel est le chef-lieu de la région du Cavally?", "Guiglo"));
        questions.add(new Question("Quel est le chef-lieu de la région du Folon?", "Minignan"));
        questions.add(new Question("Quel est le chef-lieu de la région du Gbêkê?", "Bouaké"));
        questions.add(new Question("Quel est le chef-lieu de la région du Gbôklé?", "Sassandra"));
        questions.add(new Question("Quel est le chef-lieu de la région du Gôh?", "Gagnoa"));
        questions.add(new Question("Quel est le chef-lieu de la région des Grands-Ponts?", "Dabou"));
        questions.add(new Question("Quel est le chef-lieu de la région du Guémon?", "Duékoué"));
        questions.add(new Question("Quel est le chef-lieu de la région du Hambol?", "Katiola"));
        questions.add(new Question("Quel est le chef-lieu de la région du Haut-Sassandra?", "Daloa"));
        questions.add(new Question("Quel est le chef-lieu de la région de l'Iffou?", "Daoukro"));
        questions.add(new Question("Quel est le chef-lieu de la région de l'Indénié-Djuablin?", "Abengourou"));
        questions.add(new Question("Quel est le chef-lieu de la région du Kabadougou?", "Odienné"));
        questions.add(new Question("Quel est le chef-lieu de la région du Lôh-Djiboua?", "Divo"));
        questions.add(new Question("Quel est le chef-lieu de la région de la Marahoué?", "Bouaflé"));
        questions.add(new Question("Quel est le chef-lieu de la région de la Mé?", "Adzopé"));
        questions.add(new Question("Quel est le chef-lieu de la région du Moronou?", "Bongouanou"));
        questions.add(new Question("Quel est le chef-lieu de la région de la Nawa?", "Soubré"));
        questions.add(new Question("Quel est le chef-lieu de la région du N'Zi?", "Dimbokro"));
        questions.add(new Question("Quel est le chef-lieu de la région du Poro?", "Korhogo"));
        questions.add(new Question("Quel est le chef-lieu de la région de San Pedro?", "San Pedro"));
        questions.add(new Question("Quel est le chef-lieu de la région du Sud-Comoé?", "Aboisso"));
        questions.add(new Question("Quel est le chef-lieu de la région du Tchologo?", "Ferkessédougou"));
        questions.add(new Question("Quel est le chef-lieu de la région du Tonkpi?", "Man"));
        questions.add(new Question("Quel est le chef-lieu de la région du Worodougou?", "Séguéla"));
        questions.add(new Question("Quel est le chef-lieu de la région du Zanzan?", "Bondoukou"));

        Scanner scanner = new Scanner(System.in);
        boolean replay;

        do {
            Collections.shuffle(questions);
            int score = 0;
            int timeLimit = 10;

            for (int i = 0; i < 10; i++) {
                Question q = questions.get(i);
                System.out.println("Question: " + q.getQuestionText());

                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<String> future = executor.submit(() -> scanner.nextLine());
                try {
                    String userAnswer = future.get(timeLimit, TimeUnit.SECONDS);

                    if (userAnswer.equalsIgnoreCase(q.getAnswer())) {
                        System.out.println("Bonne réponse !");
                        score++;
                    } else {
                        System.out.println("Mauvaise réponse. La bonne réponse est : " + q.getAnswer());
                    }
                } catch (TimeoutException e) {
                    System.out.println("Temps écoulé ! La bonne réponse est : " + q.getAnswer());
                    future.cancel(true);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    executor.shutdown();
                }
            }

            System.out.println("Vous avez terminé le quiz !");
            System.out.println("Votre score est : " + score + "/10");

            // Demander à l'utilisateur s'il veut rejouer
            String replayAnswer;
            do {
                System.out.println("Voulez-vous rejouer? (oui/non)");
                replayAnswer = scanner.nextLine().trim().toLowerCase();
            } while (!replayAnswer.equals("oui") && !replayAnswer.equals("non"));

            replay = replayAnswer.equals("oui");

        } while (replay);

        System.out.println("Merci d'avoir joué !");
        scanner.close();
    }
}

class Question {
    private String questionText;
    private String answer;

    public Question(String questionText, String answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
}
}

