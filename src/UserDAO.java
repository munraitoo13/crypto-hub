import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /** DTO para representar uma linha da tabela users */
    public static class UserRow {
        public long id;
        public String name;
        public String email;
        public String password;
        public boolean twoFactorAuth;
        public Timestamp createdAt;
        public Timestamp updatedAt;

        @Override
        public String toString() {
            return "UserRow{" +
                    "id=" + id +
                    ", name='" + name + "'" +
                    ", email='" + email + "'" +
                    ", twoFactorAuth=" + twoFactorAuth +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    '}';
        }
    }

    /** INSERT: cria um usuário e retorna o ID gerado */
    public long insert(User user) throws SQLException {
        final String sql = "INSERT INTO users (name, email, password, two_factor_auth) VALUES (?, ?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, new String[]{"ID"})) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.isTwoFactorAuth() ? "Y" : "N");

            ps.executeUpdate();

            long idGerado = -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getLong(1);
                }
            }
            c.commit();
            return idGerado;
        }
    }

    /** UPDATE: atualiza um usuário pelo ID */
    public int update(long id, User user) throws SQLException {
        final String sql = "UPDATE users SET name = ?, email = ?, password = ?, two_factor_auth = ?, updated_at = SYSTIMESTAMP WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.isTwoFactorAuth() ? "Y" : "N");
            ps.setLong(5, id);

            int rows = ps.executeUpdate();
            c.commit();
            return rows;
        }
    }

    /** DELETE: remove um usuário pelo ID */
    public int deleteById(long id) throws SQLException {
        final String sql = "DELETE FROM users WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            c.commit();
            return rows;
        }
    }

    /** SELECT: busca um usuário por ID */
    public UserRow findById(long id) throws SQLException {
        final String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
                return null;
            }
        }
    }

    /** SELECT ALL: lista todos os usuários */
    public List<UserRow> listAll() throws SQLException {
        final String sql = "SELECT * FROM users ORDER BY id";
        List<UserRow> users = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(map(rs));
            }
        }
        return users;
    }

    /** Mapeia um ResultSet para um objeto UserRow */
    private UserRow map(ResultSet rs) throws SQLException {
        UserRow row = new UserRow();
        row.id = rs.getLong("id");
        row.name = rs.getString("name");
        row.email = rs.getString("email");
        row.password = rs.getString("password");
        row.twoFactorAuth = "Y".equals(rs.getString("two_factor_auth"));
        row.createdAt = rs.getTimestamp("created_at");
        row.updatedAt = rs.getTimestamp("updated_at");
        return row;
    }
}