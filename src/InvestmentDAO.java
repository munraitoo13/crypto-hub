import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDAO {

    public int updateById(long id, double newAmount, double newPrice) throws java.sql.SQLException {
        final String sql = "UPDATE investments SET amount = ?, price = ?, updated_at = SYSTIMESTAMP WHERE id = ?";
        try (var c = ConnectionFactory.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setDouble(1, newAmount);
            ps.setDouble(2, newPrice);
            ps.setLong(3, id);
            int rows = ps.executeUpdate();  // <- quantas linhas o UPDATE atingiu
            c.commit();
            return rows;
        }
    }

    /** DTO simples para exibir dados com nomes via JOIN */
    public static class InvestmentRow {
        public long id, userId, companyId, cryptoId;
        public String userName, companyName, cryptoSymbol;
        public double amount, price;
        public Timestamp createdAt, updatedAt;

        @Override public String toString() {
            return "InvestmentRow{id=" + id +
                    ", user=" + userName + " (" + userId + ")" +
                    ", company=" + companyName + " (" + companyId + ")" +
                    ", crypto=" + cryptoSymbol + " (" + cryptoId + ")" +
                    ", amount=" + amount + ", price=" + price +
                    ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "}";
        }
    }

    /** Lista todos os investments (com nomes via JOIN) */
    public List<InvestmentRow> listAll() throws SQLException {
        final String sql = """
            SELECT i.id,
                   i.user_id, u.name AS user_name,
                   i.company_id, co.name AS company_name,
                   i.crypto_id, cr.symbol AS crypto_symbol,
                   i.amount, i.price,
                   i.created_at, i.updated_at
              FROM investments i
              JOIN users u  ON u.id  = i.user_id
              JOIN companies co ON co.id = i.company_id
              JOIN cryptocurrencies cr ON cr.id = i.crypto_id
             ORDER BY i.id
            """;

        List<InvestmentRow> out = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(map(rs));
        }
        return out;
    }

    /** Busca por ID (com nomes via JOIN) */
    public InvestmentRow findById(long id) throws SQLException {
        final String sql = """
            SELECT i.id,
                   i.user_id, u.name AS user_name,
                   i.company_id, co.name AS company_name,
                   i.crypto_id, cr.symbol AS crypto_symbol,
                   i.amount, i.price,
                   i.created_at, i.updated_at
              FROM investments i
              JOIN users u  ON u.id  = i.user_id
              JOIN companies co ON co.id = i.company_id
              JOIN cryptocurrencies cr ON cr.id = i.crypto_id
             WHERE i.id = ?
            """;
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return map(rs);
            }
        }
    }

    private InvestmentRow map(ResultSet rs) throws SQLException {
        InvestmentRow r = new InvestmentRow();
        r.id = rs.getLong("id");
        r.userId = rs.getLong("user_id");
        r.userName = rs.getString("user_name");
        r.companyId = rs.getLong("company_id");
        r.companyName = rs.getString("company_name");
        r.cryptoId = rs.getLong("crypto_id");
        r.cryptoSymbol = rs.getString("crypto_symbol");
        r.amount = rs.getDouble("amount");
        r.price = rs.getDouble("price");
        r.createdAt = rs.getTimestamp("created_at");
        r.updatedAt = rs.getTimestamp("updated_at");
        return r;
    }

    /** INSERT: cria um investment e retorna o ID gerado */
    public long insert(long userId, long companyId, long cryptoId, double amount, double price) throws SQLException {
        final String sql = "INSERT INTO investments (user_id, company_id, crypto_id, amount, price) VALUES (?,?,?,?,?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, new String[] { "ID" })) {

            ps.setLong(1, userId);
            ps.setLong(2, companyId);
            ps.setLong(3, cryptoId);
            ps.setDouble(4, amount);
            ps.setDouble(5, price);

            ps.executeUpdate();

            long idGerado = -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) idGerado = rs.getLong(1);
            }
            c.commit();
            return idGerado;
        }
    }

    /** DELETE: remove um investment pelo ID; retorna linhas afetadas */
    public int deleteById(long id) throws java.sql.SQLException {
        final String sql = "DELETE FROM investments WHERE id = ?";
        try (var c = ConnectionFactory.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            c.commit();
            return rows;
        }
    }


}
