package services;

import database.Database;
import java.sql.*;
import java.util.Scanner;

public class QuizService {

    private Connection conn;
    private Scanner sc = new Scanner(System.in);

    public QuizService() {
        conn = Database.getConnection();
    }

    // Admin creates a quiz
    public int createQuiz() {
        try {
            System.out.print("Enter quiz title: ");
            String title = sc.nextLine();
            System.out.print("Enter quiz description: ");
            String description = sc.nextLine();

            String query = "INSERT INTO quizzes (title, description) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Add questions to a quiz
    public void addQuestion(int quizId) {
        try {
            System.out.print("Enter question text: ");
            String text = sc.nextLine();
            System.out.print("Option A: "); String a = sc.nextLine();
            System.out.print("Option B: "); String b = sc.nextLine();
            System.out.print("Option C: "); String c = sc.nextLine();
            System.out.print("Option D: "); String d = sc.nextLine();
            System.out.print("Correct Option (A/B/C/D): "); String correct = sc.nextLine().toUpperCase();

            String query = "INSERT INTO questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, quizId); ps.setString(2, text);
            ps.setString(3, a); ps.setString(4, b);
            ps.setString(5, c); ps.setString(6, d);
            ps.setString(7, correct);
            ps.executeUpdate();
            System.out.println("✅ Question added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View user results
    public void viewResults(int userId) {
        try {
            String query = "SELECT r.result_id, q.title, r.score, r.total_questions, r.attempt_time " +
                    "FROM results r JOIN quizzes q ON r.quiz_id = q.quiz_id " +
                    "WHERE r.user_id=? ORDER BY r.attempt_time DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n=== Your Quiz Results ===");
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                System.out.println("Quiz: " + rs.getString("title") +
                        " | Score: " + rs.getInt("score") + "/" + rs.getInt("total_questions") +
                        " | Date: " + rs.getTimestamp("attempt_time"));
            }
            if (!hasResults) System.out.println("You haven't attempted any quiz yet.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show all quizzes
    public void showAvailableQuizzes() {
        try {
            String query = "SELECT quiz_id, title FROM quizzes";
            ResultSet rs = conn.createStatement().executeQuery(query);
            System.out.println("\n=== Available Quizzes ===");
            while (rs.next()) {
                System.out.println(rs.getInt("quiz_id") + ". " + rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
