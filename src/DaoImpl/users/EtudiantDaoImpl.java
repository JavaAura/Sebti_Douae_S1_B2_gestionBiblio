package DaoImpl.users;

import DB.DatabaseConnection;
import Dao.UtilisateurDao;
import entities.users.Etudiant;
import entities.users.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDaoImpl implements UtilisateurDao {

    private Connection conn;

    public EtudiantDaoImpl() {
        try {
            this.conn = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public void addUser(Utilisateur utilisateur) {
        if (!(utilisateur instanceof Etudiant)) {
            throw new IllegalArgumentException("User must be of type Etudiant");
        }

        Etudiant etudiant = (Etudiant) utilisateur;
        String sql = "INSERT INTO etudiant (name, email, age, CNE) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, etudiant.getName());
            pstmt.setString(2, etudiant.getEmail());
            pstmt.setInt(3, etudiant.getAge());
            pstmt.setString(4, etudiant.getCNE());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editUser(Utilisateur utilisateur) {
        if (!(utilisateur instanceof Etudiant)) {
            throw new IllegalArgumentException("User must be of type Etudiant");
        }

        Etudiant etudiant = (Etudiant) utilisateur;
        String sql = "UPDATE etudiant SET name = ?, email = ?, age = ?, CNE = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, etudiant.getName());
            pstmt.setString(2, etudiant.getEmail());
            pstmt.setInt(3, etudiant.getAge());
            pstmt.setString(4, etudiant.getCNE());
            pstmt.setInt(5, etudiant.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayUser(int id) {
        String sql = "SELECT * FROM etudiant WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nom: " + resultSet.getString("name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("CNE: " + resultSet.getString("CNE")); // Fixed column name
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Utilisateur> displayAllUsers() {
        List<Utilisateur> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet res = pstmt.executeQuery();
            while (res.next()) { // Use while to iterate over all results
                Etudiant etudiant = new Etudiant(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("age"),
                        res.getString("email"),
                        res.getString("CNE")
                );
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return etudiants;
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM etudiant WHERE email = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Retourne true si l'email existe
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'email : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Utilisateur getUserById(int id) {
        Utilisateur etudiant = null;
        String sql = "SELECT * FROM etudiant WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                etudiant = new Etudiant();
                etudiant.setId(resultSet.getInt("id"));
                etudiant.setName(resultSet.getString("name"));
                etudiant.setEmail(resultSet.getString("email"));
                etudiant.setAge(resultSet.getInt("age"));
                ((Etudiant) etudiant).setCNE(resultSet.getString("CNE")); // Fixed column name
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'étudiant : " + e.getMessage());
        }

        return etudiant;
    }

    // New method to get user by email
    @Override
    public Utilisateur getUserByEmail(String email) {
        Utilisateur etudiant = null;
        String sql = "SELECT * FROM etudiant WHERE email = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                etudiant = new Etudiant();
                etudiant.setId(resultSet.getInt("id"));
                etudiant.setName(resultSet.getString("name"));
                etudiant.setEmail(resultSet.getString("email"));
                etudiant.setAge(resultSet.getInt("age"));
                ((Etudiant) etudiant).setCNE(resultSet.getString("CNE")); // Fixed column name
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'étudiant : " + e.getMessage());
        }

        return etudiant;
    }
}
