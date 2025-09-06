import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Declarando as variáveis fora dos blocos
        HashMap<User, Cryptocurrency> investments = new HashMap<>();

        ArrayList<Cryptocurrency> cryptos = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        User user = null;
        Company company = null;
        Cryptocurrency crypto = null;

        try {
            user = new User();
            user.setName("Tio Patinhas");
            user.setEmail("patinhas@email.com");
            System.out.println("Usuário criado: " + user.getName());

            // user2
            User user2 = new User();
            user2.setName("Pato Donald");
            user2.setEmail("donald@email.com");
            System.out.println("Usuário criado: " + user2.getName());

            // array list 1
            users.add(user);
            users.add(user2);

        } catch (Exception e) {
            System.out.println("Erro ao criar Usuário: " + e.getMessage());
        }

        try {
            company = new Company();
            company.setName("Patinhas Investimentos");
            company.setCnpj("12.345.678/0001-99");
            company.setType("Holding de Criptoativos");
            System.out.println("Empresa criada: " + company.getName());
        } catch (Exception e) {
            System.out.println("Erro ao criar Empresa: " + e.getMessage());
        }

        try {
            crypto = new Cryptocurrency();
            crypto.setName("Bitcoin");
            crypto.setSymbol("BTC");
            crypto.setPrice(250000.0);
            System.out.println("Criptomoeda criada: " + crypto.getName());

            // crypto2
            Cryptocurrency crypto2 = new Cryptocurrency();
            crypto2.setName("Ethereum");
            crypto2.setSymbol("ETH");
            crypto2.setPrice(18000.0);
            System.out.println("Criptomoeda criada: " + crypto2.getName());

            // array list 2
            cryptos.add(crypto);
            cryptos.add(crypto2);
        } catch (Exception e) {
            System.out.println("Erro ao criar Criptomoeda: " + e.getMessage());
        }

        try {
            Investment investment = new Investment(user, company, crypto, 2.0, 500000.0);
            System.out.println("Investimento criado: " + investment.getAmount() + " unidades de " + crypto.getName());
        } catch (Exception e) {
            System.out.println("Erro ao criar Investimento: " + e.getMessage());
        }

        try {
            Alert alert = new Alert(user, crypto, 260000.0, "Acima");
            System.out.println("Alerta criado para " + alert.getCryptocurrency().getName() + " quando ultrapassar R$ " + alert.getTargetPrice());
        } catch (Exception e) {
            System.out.println("Erro ao criar Alerta: " + e.getMessage());
        }

        try {
            Notification notification = new Notification(user, "Seu alerta foi disparado!", "WhatsApp");
            System.out.println("Notificação criada para: " + notification.getUser().getName() + " via " + notification.getChannel());
        } catch (Exception e) {
            System.out.println("Erro ao criar Notificação: " + e.getMessage());
        }

        // hashmap com 2 classes (user e investment)
        investments.put(user, crypto);

        // log array lists and hashmaps
        System.out.println(investments);
        System.out.println(cryptos);
        System.out.println(users);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (User u : users) {
                writer.write(u.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        try {
            runDbCrudDemo();  // INSERT -> UPDATE -> FIND -> LIST -> DELETE -> LIST
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // ===================== FASE 5 - TESTES DB (Investment) =====================

    static long dbTestInsert() throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        long userId = 1L;     // ajuste se necessário
        long companyId = 1L;  // ajuste se necessário
        long cryptoId = 1L;   // ajuste se necessário
        long id = dao.insert(userId, companyId, cryptoId, 0.25, 320000.00);
        System.out.println("[DB][INSERT] id=" + id);
        return id;
    }

    static void dbTestUpdate(long id) throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        int rows = dao.updateById(id, 0.30, 315000.00);
        System.out.println("[DB][UPDATE] rows=" + rows + " id=" + id);
    }

    static void dbTestFind(long id) throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        var row = dao.findById(id);
        System.out.println("[DB][FIND] " + row);
    }

    static void dbTestListAll() throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        java.util.List<InvestmentDAO.InvestmentRow> all = dao.listAll();
        System.out.println("[DB][LIST] total=" + all.size());
        for (var r : all) System.out.println("  - " + r);
    }

    static void dbTestDelete(long id) throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        int rows = dao.deleteById(id);
        System.out.println("[DB][DELETE] rows=" + rows + " id=" + id);
    }

    /** Executa a sequência completa de testes da Fase 5 para Investment */
    static void runDbCrudDemo() throws Exception {
        long novoId = dbTestInsert();   // INSERT
        dbTestUpdate(novoId);           // UPDATE
        dbTestFind(novoId);             // SELECT by ID
        dbTestListAll();                // SELECT *
        dbTestDelete(novoId);           // DELETE
        dbTestListAll();                // SELECT * (confirma remoção)
    }
// ===================== FIM FASE 5 - TESTES DB =====================

}