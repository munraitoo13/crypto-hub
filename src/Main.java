import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            long novoId = testInsert();     // INSERT
            testUpdate(novoId);             // UPDATE
            testFind(novoId);               // SELECT by ID
            testListAll();                  // SELECT *
            testDelete(novoId);             // DELETE
            testListAll();                  // SELECT * (confirma remoção)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long testInsert() throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        long userId = 1L;     // Alice
        long companyId = 1L;  // Acme
        long cryptoId = 1L;   // BTC
        long id = dao.insert(userId, companyId, cryptoId, 0.25, 320000.00);
        System.out.println("[INSERT] id=" + id);
        return id;
    }

    static void testUpdate(long id) throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        int rows = dao.updateById(id, 0.30, 315000.00);
        System.out.println("[UPDATE] rows=" + rows + " id=" + id);
    }

    static void testFind(long id) throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        var row = dao.findById(id);
        System.out.println("[FIND] " + row);
    }

    static void testListAll() throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        List<InvestmentDAO.InvestmentRow> all = dao.listAll();
        System.out.println("[LIST] total=" + all.size());
        for (var r : all) System.out.println("  - " + r);
    }

    static void testDelete(long id) throws Exception {
        InvestmentDAO dao = new InvestmentDAO();
        int rows = dao.deleteById(id);
        System.out.println("[DELETE] rows=" + rows + " id=" + id);
    }
}
