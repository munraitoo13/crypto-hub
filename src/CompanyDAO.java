
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    /** DTO para representar uma linha da tabela companies */
    public static class CompanyRow {
        public long id;
        public String name;
        public String cnpj;
        public String type;
        public Timestamp createdAt;
        public Timestamp updatedAt;

        @Override
        public String toString() {
            return "CompanyRow{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", cnpj='" + cnpj + '\'' +
                    ", type='" + type + '\'' +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    '}';
        }
    }

    /** INSERT: cria uma empresa e retorna o ID gerado */
    public long insert(Company company) throws SQLException {
        final String sql = "INSERT INTO companies (name, cnpj, type) VALUES (?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, new String[]{"ID"})) {

            ps.setString(1, company.getName());
            ps.setString(2, company.getCnpj());
            ps.setString(3, company.getType());

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

    /** UPDATE: atualiza uma empresa pelo ID */
    public int update(long id, Company company) throws SQLException {
        final String sql = "UPDATE companies SET name = ?, cnpj = ?, type = ?, updated_at = SYSTIMESTAMP WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, company.getName());
            ps.setString(2, company.getCnpj());
            ps.setString(3, company.getType());
            ps.setLong(4, id);

            int rows = ps.executeUpdate();
            c.commit();
            return rows;
        }
    }

    /** DELETE: remove uma empresa pelo ID */
    public int deleteById(long id) throws SQLException {
        final String sql = "DELETE FROM companies WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            c.commit();
            return rows;
        }
    }

    /** SELECT: busca uma empresa por ID */
    public CompanyRow findById(long id) throws SQLException {
        final String sql = "SELECT * FROM companies WHERE id = ?";
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

    /** SELECT ALL: lista todas as empresas */
    public List<CompanyRow> listAll() throws SQLException {
        final String sql = "SELECT * FROM companies ORDER BY id";
        List<CompanyRow> companies = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                companies.add(map(rs));
            }
        }
        return companies;
    }

    /** Mapeia um ResultSet para um objeto CompanyRow */
    private CompanyRow map(ResultSet rs) throws SQLException {
        CompanyRow row = new CompanyRow();
        row.id = rs.getLong("id");
        row.name = rs.getString("name");
        row.cnpj = rs.getString("cnpj");
        row.type = rs.getString("type");
        row.createdAt = rs.getTimestamp("created_at");
        row.updatedAt = rs.getTimestamp("updated_at");
        return row;
    }
}
