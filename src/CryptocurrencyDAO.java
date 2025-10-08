
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CryptocurrencyDAO {

    /** DTO para representar uma linha da tabela cryptocurrencies */
    public static class CryptocurrencyRow {
        public long id;
        public String name;
        public String symbol;
        public double previousPrice;
        public double price;
        public Timestamp lastUpdated;
        public Timestamp createdAt;
        public Timestamp updatedAt;

        @Override
        public String toString() {
            return "CryptocurrencyRow{" +
                    "id=" + id + 
                    ", name='" + name + "'" +
                    ", symbol='" + symbol + "'" +
                    ", price=" + price + 
                    ", lastUpdated=" + lastUpdated + 
                    '}';
        }
    }

    /** INSERT: cria uma criptomoeda e retorna o ID gerado */
    public long insert(Cryptocurrency crypto) throws SQLException {
        final String sql = "INSERT INTO cryptocurrencies (name, symbol, price, previous_price) VALUES (?, ?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, new String[]{"ID"})) {

            ps.setString(1, crypto.getName());
            ps.setString(2, crypto.getSymbol());
            ps.setDouble(3, crypto.getPrice());
            ps.setDouble(4, crypto.getPreviousPrice());

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

    /** UPDATE: atualiza uma criptomoeda pelo ID */
    public int update(long id, Cryptocurrency crypto) throws SQLException {
        final String sql = "UPDATE cryptocurrencies SET name = ?, symbol = ?, price = ?, previous_price = ?, updated_at = SYSTIMESTAMP WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, crypto.getName());
            ps.setString(2, crypto.getSymbol());
            ps.setDouble(3, crypto.getPrice());
            ps.setDouble(4, crypto.getPreviousPrice());
            ps.setLong(5, id);

            int rows = ps.executeUpdate();
            c.commit();
            return rows;
        }
    }

    /** DELETE: remove uma criptomoeda pelo ID */
    public int deleteById(long id) throws SQLException {
        final String sql = "DELETE FROM cryptocurrencies WHERE id = ?";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            c.commit();
            return rows;
        }
    }

    /** SELECT: busca uma criptomoeda por ID */
    public CryptocurrencyRow findById(long id) throws SQLException {
        final String sql = "SELECT * FROM cryptocurrencies WHERE id = ?";
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

    /** SELECT ALL: lista todas as criptomoedas */
    public List<CryptocurrencyRow> listAll() throws SQLException {
        final String sql = "SELECT * FROM cryptocurrencies ORDER BY id";
        List<CryptocurrencyRow> cryptos = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cryptos.add(map(rs));
            }
        }
        return cryptos;
    }

    /** Mapeia um ResultSet para um objeto CryptocurrencyRow */
    private CryptocurrencyRow map(ResultSet rs) throws SQLException {
        CryptocurrencyRow row = new CryptocurrencyRow();
        row.id = rs.getLong("id");
        row.name = rs.getString("name");
        row.symbol = rs.getString("symbol");
        row.previousPrice = rs.getDouble("previous_price");
        row.price = rs.getDouble("price");
        row.lastUpdated = rs.getTimestamp("last_updated");
        row.createdAt = rs.getTimestamp("created_at");
        row.updatedAt = rs.getTimestamp("updated_at");
        return row;
    }
}
