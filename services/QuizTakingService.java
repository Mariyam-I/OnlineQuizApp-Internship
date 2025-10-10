package services;

import database.Database;

import java.sql.*;
import java.util.Scanner;

public class QuizTakingService {

    private Connection conn;
    private Scanner sc = new Scanner(System.in);

    public QuizTakingService() {
        conn = Database.getConnection();
    }

    // Take quiz with immediate feedback
    public void takeQuiz(int userId, int quizId) {
        int score = 0, total = 0;
        try {
            String query = "SELECT * FROM questions WHERE quiz_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total++;
                System.out.println("\nQ" + total + ". " + rs.getString("question_text"));
                System.out.println("A. " + rs.getString("option_a"));
                System.out.println("B. " + rs.getString("option_b"));
                System.out.println("C. " + rs.getString("option_c"));
                System.out.println("D. " + rs.getString("option_d"));
                System.out.print("Your answer: ");
                String ans = sc.nextLine().toUpperCase();
                String correct = rs.getString("correct_option").toUpperCase();

                if (ans.equals(correct)) {
                    System.out.println("✅ Correct!");
                    score++;
                } else {
                    System.out.println("❌ Incorrect! Correct: " + correct);
                }
            }

            System.out.println("\n=== Quiz Finished ===");
            System.out.println("Your Score: " + score + "/" + total);
            saveResult(userId, quizId, score, total);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveResult(int userId, int quizId, int score, int total) {
        try {
            String query = "INSERT INTO results (user_id, quiz_id, score, total_questions) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId); ps.setInt(2, quizId);
            ps.setInt(3, score); ps.setInt(4, total);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
