package quizApplication;

import services.*;
import database.Database;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Database.initialize();
        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();
        QuizService quizService = new QuizService();
        QuizTakingService quizTakingService = new QuizTakingService();

        int currentUserId = -1; // Track logged-in user
        boolean running = true;

        while (running) {
            System.out.println("\n=== Online Quiz App ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create Quiz (Admin)");
            System.out.println("4. Add Questions to Quiz (Admin)");
            System.out.println("5. Take Quiz");
            System.out.println("6. View My Results");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1: // Register
                    System.out.print("Enter new username: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();
                    int userId = userService.registerUser(uname, pass);

                    if (userId != -1) {
                        System.out.println("✅ Registration successful!");
                        System.out.println("Please login now...");

                        // Immediately ask for login
                        System.out.print("Username: ");
                        String loginUname = sc.nextLine();
                        System.out.print("Password: ");
                        String loginPass = sc.nextLine();

                        currentUserId = userService.loginUser(loginUname, loginPass);
                        if (currentUserId != -1) {
                            System.out.println("✅ Login successful! Welcome, " + loginUname);
                        } else {
                            System.out.println("❌ Login failed. Try again from main menu.");
                        }
                    }
                    break;

                case 2: // Login
                    System.out.print("Enter username: ");
                    String loginU = sc.nextLine();
                    System.out.print("Enter password: ");
                    String loginP = sc.nextLine();
                    currentUserId = userService.loginUser(loginU, loginP);

                    if (currentUserId != -1) {
                        System.out.println("✅ Login successful! Welcome, " + loginU);
                    } else {
                        System.out.println("❌ Invalid credentials.");
                    }
                    break;

                case 3: // Create Quiz
                    int quizId = quizService.createQuiz();
                    if (quizId != -1) System.out.println("Quiz created with ID: " + quizId);
                    break;

                case 4: // Add Questions
                    System.out.print("Enter Quiz ID to add questions: ");
                    int qid = sc.nextInt(); sc.nextLine();
                    quizService.addQuestion(qid);
                    break;

                case 5: // Take Quiz
                    if (currentUserId == -1) {
                        System.out.println("⚠️ Please login first!");
                    } else {
                        quizService.showAvailableQuizzes();
                        System.out.print("Enter Quiz ID to take: ");
                        int tqid = sc.nextInt(); sc.nextLine();
                        quizTakingService.takeQuiz(currentUserId, tqid);
                    }
                    break;

                case 6: // View Results
                    if (currentUserId == -1) {
                        System.out.println("⚠️ Please login first!");
                    } else {
                        quizService.viewResults(currentUserId);
                    }
                    break;

                case 7:
                    running = false;
                    System.out.println("👋 Exiting... Thank you!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again!");
            }
        }

        sc.close();
    }
}
