import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== INICIANDO TESTES DE BANCO DE DADOS (FASE 6) =====");

        try {
            System.out.println("\n--- [TESTE] CRUD de User ---");
            runUserDbCrudDemo();
        } catch (Exception e) {
            System.err.println("Falha no teste de UserDAO!");
            e.printStackTrace();
        }

        try {
            System.out.println("\n--- [TESTE] CRUD de Company ---");
            runCompanyDbCrudDemo();
        } catch (Exception e) {
            System.err.println("Falha no teste de CompanyDAO!");
            e.printStackTrace();
        }

        try {
            System.out.println("\n--- [TESTE] CRUD de Cryptocurrency ---");
            runCryptocurrencyDbCrudDemo();
        } catch (Exception e) {
            System.err.println("Falha no teste de CryptocurrencyDAO!");
            e.printStackTrace();
        }

        try {
            System.out.println("\n--- [TESTE] CRUD de Investment ---");
            runInvestmentDbCrudDemo();
        } catch (Exception e) {
            System.err.println("Falha no teste de InvestmentDAO!");
            e.printStackTrace();
        }

        System.out.println("\n===== TESTES DE BANCO DE DADOS FINALIZADOS =====");
    }

    // ===================== USER CRUD DEMO =====================

    static void runUserDbCrudDemo() throws SQLException {
        UserDAO dao = new UserDAO();
        User user = new User();
        user.setName("Huguinho");
        user.setEmail("huguinho@email.com");
        user.setPassword("senha123");
        user.setTwoFactorAuth(false);

        // INSERT
        long novoId = dao.insert(user);
        System.out.println("[DB][USER][INSERT] ID=" + novoId);

        // UPDATE
        user.setName("Huguinho Pato");
        user.setTwoFactorAuth(true);
        int rows = dao.update(novoId, user);
        System.out.println("[DB][USER][UPDATE] Linhas afetadas=" + rows);

        // SELECT by ID
        var row = dao.findById(novoId);
        System.out.println("[DB][USER][FIND] " + row);

        // SELECT ALL
        var allUsers = dao.listAll();
        System.out.println("[DB][USER][LIST] Total=" + allUsers.size());
        for (var r : allUsers) System.out.println("  - " + r);

        // DELETE
        rows = dao.deleteById(novoId);
        System.out.println("[DB][USER][DELETE] Linhas afetadas=" + rows);

        // SELECT ALL (para confirmar)
        allUsers = dao.listAll();
        System.out.println("[DB][USER][LIST] Total após delete=" + allUsers.size());
    }

    // ===================== COMPANY CRUD DEMO =====================

    static void runCompanyDbCrudDemo() throws SQLException {
        CompanyDAO dao = new CompanyDAO();
        Company company = new Company("Empresa do Zezinho", "11.222.333/0001-44", "Startup");

        // INSERT
        long novoId = dao.insert(company);
        System.out.println("[DB][COMPANY][INSERT] ID=" + novoId);

        // UPDATE
        company.setName("Zezinho Corp");
        int rows = dao.update(novoId, company);
        System.out.println("[DB][COMPANY][UPDATE] Linhas afetadas=" + rows);

        // SELECT by ID
        var row = dao.findById(novoId);
        System.out.println("[DB][COMPANY][FIND] " + row);

        // SELECT ALL
        var allCompanies = dao.listAll();
        System.out.println("[DB][COMPANY][LIST] Total=" + allCompanies.size());
        for (var r : allCompanies) System.out.println("  - " + r);

        // DELETE
        rows = dao.deleteById(novoId);
        System.out.println("[DB][COMPANY][DELETE] Linhas afetadas=" + rows);

        // SELECT ALL (para confirmar)
        allCompanies = dao.listAll();
        System.out.println("[DB][COMPANY][LIST] Total após delete=" + allCompanies.size());
    }

    // ===================== CRYPTOCURRENCY CRUD DEMO =====================

    static void runCryptocurrencyDbCrudDemo() throws SQLException {
        CryptocurrencyDAO dao = new CryptocurrencyDAO();
        Cryptocurrency crypto = new Cryptocurrency("Cardano", "ADA", 1.50, 1.45);

        // INSERT
        long novoId = dao.insert(crypto);
        System.out.println("[DB][CRYPTO][INSERT] ID=" + novoId);

        // UPDATE
        crypto.setPrice(1.55);
        int rows = dao.update(novoId, crypto);
        System.out.println("[DB][CRYPTO][UPDATE] Linhas afetadas=" + rows);

        // SELECT by ID
        var row = dao.findById(novoId);
        System.out.println("[DB][CRYPTO][FIND] " + row);

        // SELECT ALL
        var allCryptos = dao.listAll();
        System.out.println("[DB][CRYPTO][LIST] Total=" + allCryptos.size());
        for (var r : allCryptos) System.out.println("  - " + r);

        // DELETE
        rows = dao.deleteById(novoId);
        System.out.println("[DB][CRYPTO][DELETE] Linhas afetadas=" + rows);

        // SELECT ALL (para confirmar)
        allCryptos = dao.listAll();
        System.out.println("[DB][CRYPTO][LIST] Total após delete=" + allCryptos.size());
    }

    // ===================== INVESTMENT CRUD DEMO =====================

    static void runInvestmentDbCrudDemo() throws Exception {
        // Criar registros dependentes para o teste de Investment
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setName("Zezinho");
        user.setEmail("zezinho@email.com");
        user.setPassword("senha456");
        long userId = userDAO.insert(user);

        CompanyDAO companyDAO = new CompanyDAO();
        Company company = new Company("Invest Zezinho", "44.555.666/0001-77", "Broker");
        long companyId = companyDAO.insert(company);

        CryptocurrencyDAO cryptoDAO = new CryptocurrencyDAO();
        Cryptocurrency crypto = new Cryptocurrency("Solana", "SOL", 150.0, 148.0);
        long cryptoId = cryptoDAO.insert(crypto);

        System.out.println("[DB][INVESTMENT] Dependências criadas: userId=" + userId + ", companyId=" + companyId + ", cryptoId=" + cryptoId);

        InvestmentDAO dao = new InvestmentDAO();

        // INSERT
        long novoId = dao.insert(userId, companyId, cryptoId, 10.5, 151.0);
        System.out.println("[DB][INVESTMENT][INSERT] ID=" + novoId);

        // UPDATE
        int rows = dao.updateById(novoId, 12.0, 155.0);
        System.out.println("[DB][INVESTMENT][UPDATE] Linhas afetadas=" + rows);

        // SELECT by ID
        var row = dao.findById(novoId);
        System.out.println("[DB][INVESTMENT][FIND] " + row);

        // SELECT ALL
        var allInvestments = dao.listAll();
        System.out.println("[DB][INVESTMENT][LIST] Total=" + allInvestments.size());
        for (var r : allInvestments) System.out.println("  - " + r);

        // DELETE
        rows = dao.deleteById(novoId);
        System.out.println("[DB][INVESTMENT][DELETE] Linhas afetadas=" + rows);

        // SELECT ALL (para confirmar)
        allInvestments = dao.listAll();
        System.out.println("[DB][INVESTMENT][LIST] Total após delete=" + allInvestments.size());

        // Limpar dependências
        userDAO.deleteById(userId);
        companyDAO.deleteById(companyId);
        cryptoDAO.deleteById(cryptoId);
        System.out.println("[DB][INVESTMENT] Dependências limpas.");
    }
}