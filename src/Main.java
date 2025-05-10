public class Main {
    public static void main(String[] args) {
        // Declarando as variáveis fora dos blocos
        User user = null;
        Company company = null;
        Cryptocurrency crypto = null;

        try {
            user = new User();
            user.setName("Tio Patinhas");
            user.setEmail("patinhas@email.com");
            System.out.println("Usuário criado: " + user.getName());
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

    }
}
