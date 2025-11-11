import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final UserDAO userDAO = new UserDAO();
    private static final CompanyDAO companyDAO = new CompanyDAO();
    private static final CryptocurrencyDAO cryptoDAO = new CryptocurrencyDAO();
    private static final InvestmentDAO investmentDAO = new InvestmentDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        handleUsersMenu();
                        break;
                    case 2:
                        handleCompaniesMenu();
                        break;
                    case 3:
                        handleCryptosMenu();
                        break;
                    case 4:
                        handleInvestmentsMenu();
                        break;
                    case 5:
                        System.out.println("Saindo do programa. Até mais!");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n===== MENU PRINCIPAL - CRYPTO HUB =====");
        System.out.println("1. Gerenciar Usuários");
        System.out.println("2. Gerenciar Empresas");
        System.out.println("3. Gerenciar Criptomoedas");
        System.out.println("4. Gerenciar Investimentos");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // ===================================================================
    // ========================== USERS MENU =============================
    // ===================================================================

    private static void handleUsersMenu() {
        while (true) {
            printSubMenu("Usuários");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        listAllUsers();
                        break;
                    case 2:
                        findUserById();
                        break;
                    case 3:
                        addUser();
                        break;
                    case 4:
                        updateUser();
                        break;
                    case 5:
                        deleteUser();
                        break;
                    case 6:
                        return; // Back to main menu
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void listAllUsers() {
        System.out.println("\n--- Lista de Todos os Usuários ---");
        try {
            var users = userDAO.listAll();
            if (users.isEmpty()) {
                System.out.println("Nenhum usuário encontrado.");
            } else {
                users.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    private static void findUserById() {
        System.out.print("Digite o ID do usuário a ser exibido: ");
        try {
            long id = scanner.nextLong();
            scanner.nextLine();
            var user = userDAO.findById(id);
            if (user != null) {
                System.out.println(user);
            } else {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido. Deve ser um número.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    private static void addUser() {
        try {
            System.out.print("Nome: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String password = scanner.nextLine();
            System.out.print("Habilitar autenticação de dois fatores? (S/N): ");
            boolean twoFactor = scanner.nextLine().equalsIgnoreCase("S");

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setTwoFactorAuth(twoFactor);

            long newId = userDAO.insert(user);
            System.out.println("Usuário criado com sucesso! ID: " + newId);
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    private static void updateUser() {
        try {
            System.out.print("Digite o ID do usuário a ser atualizado: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            if (userDAO.findById(id) == null) {
                System.out.println("Usuário com ID " + id + " não encontrado.");
                return;
            }

            System.out.print("Novo Nome: ");
            String name = scanner.nextLine();
            System.out.print("Novo Email: ");
            String email = scanner.nextLine();
            System.out.print("Nova Senha: ");
            String password = scanner.nextLine();
            System.out.print("Habilitar autenticação de dois fatores? (S/N): ");
            boolean twoFactor = scanner.nextLine().equalsIgnoreCase("S");

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setTwoFactorAuth(twoFactor);

            int rows = userDAO.update(id, user);
            if (rows > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("A atualização falhou.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido. Deve ser um número.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private static void deleteUser() {
        try {
            System.out.print("Digite o ID do usuário a ser deletado: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            int rows = userDAO.deleteById(id);
            if (rows > 0) {
                System.out.println("Usuário deletado com sucesso!");
            } else {
                System.out.println("Usuário com ID " + id + " não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido. Deve ser um número.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    // ===================================================================
    // ======================== COMPANIES MENU ===========================
    // ===================================================================

    private static void handleCompaniesMenu() {
        while (true) {
            printSubMenu("Empresas");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        listAllCompanies();
                        break;
                    case 2:
                        findCompanyById();
                        break;
                    case 3:
                        addCompany();
                        break;
                    case 4:
                        updateCompany();
                        break;
                    case 5:
                        deleteCompany();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        }
    }

    private static void listAllCompanies() {
        System.out.println("\n--- Lista de Todas as Empresas ---");
        try {
            var companies = companyDAO.listAll();
            if (companies.isEmpty()) {
                System.out.println("Nenhuma empresa encontrada.");
            } else {
                companies.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empresas: " + e.getMessage());
        }
    }

    private static void findCompanyById() {
        System.out.print("Digite o ID da empresa: ");
        try {
            long id = scanner.nextLong();
            scanner.nextLine();
            var company = companyDAO.findById(id);
            if (company != null) {
                System.out.println(company);
            } else {
                System.out.println("Empresa com ID " + id + " não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar empresa: " + e.getMessage());
        }
    }

    private static void addCompany() {
        try {
            System.out.print("Nome: ");
            String name = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            System.out.print("Tipo: ");
            String type = scanner.nextLine();

            Company company = new Company(name, cnpj, type);
            long newId = companyDAO.insert(company);
            System.out.println("Empresa criada com sucesso! ID: " + newId);
        } catch (SQLException e) {
            System.err.println("Erro ao inserir empresa: " + e.getMessage());
        }
    }

    private static void updateCompany() {
        try {
            System.out.print("Digite o ID da empresa a ser atualizada: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            if (companyDAO.findById(id) == null) {
                System.out.println("Empresa com ID " + id + " não encontrada.");
                return;
            }

            System.out.print("Novo Nome: ");
            String name = scanner.nextLine();
            System.out.print("Novo CNPJ: ");
            String cnpj = scanner.nextLine();
            System.out.print("Novo Tipo: ");
            String type = scanner.nextLine();

            Company company = new Company(name, cnpj, type);
            int rows = companyDAO.update(id, company);
            if (rows > 0) {
                System.out.println("Empresa atualizada com sucesso!");
            } else {
                System.out.println("A atualização falhou.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar empresa: " + e.getMessage());
        }
    }

    private static void deleteCompany() {
        try {
            System.out.print("Digite o ID da empresa a ser deletada: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            int rows = companyDAO.deleteById(id);
            if (rows > 0) {
                System.out.println("Empresa deletada com sucesso!");
            } else {
                System.out.println("Empresa com ID " + id + " não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar empresa: " + e.getMessage());
        }
    }

    // ===================================================================
    // ====================== CRYPTOCURRENCIES MENU ====================== 
    // ===================================================================

    private static void handleCryptosMenu() {
        while (true) {
            printSubMenu("Criptomoedas");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        listAllCryptos();
                        break;
                    case 2:
                        findCryptoById();
                        break;
                    case 3:
                        addCrypto();
                        break;
                    case 4:
                        updateCrypto();
                        break;
                    case 5:
                        deleteCrypto();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        }
    }

    private static void listAllCryptos() {
        System.out.println("\n--- Lista de Todas as Criptomoedas ---");
        try {
            var cryptos = cryptoDAO.listAll();
            if (cryptos.isEmpty()) {
                System.out.println("Nenhuma criptomoeda encontrada.");
            } else {
                cryptos.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar criptomoedas: " + e.getMessage());
        }
    }

    private static void findCryptoById() {
        System.out.print("Digite o ID da criptomoeda: ");
        try {
            long id = scanner.nextLong();
            scanner.nextLine();
            var crypto = cryptoDAO.findById(id);
            if (crypto != null) {
                System.out.println(crypto);
            } else {
                System.out.println("Criptomoeda com ID " + id + " não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar criptomoeda: " + e.getMessage());
        }
    }

    private static void addCrypto() {
        try {
            System.out.print("Nome: ");
            String name = scanner.nextLine();
            System.out.print("Símbolo: ");
            String symbol = scanner.nextLine();
            System.out.print("Preço: ");
            double price = scanner.nextDouble();
            System.out.print("Preço Anterior: ");
            double previousPrice = scanner.nextDouble();
            scanner.nextLine();

            Cryptocurrency crypto = new Cryptocurrency(name, symbol, price, previousPrice);
            long newId = cryptoDAO.insert(crypto);
            System.out.println("Criptomoeda criada com sucesso! ID: " + newId);
        } catch (InputMismatchException e) {
            System.out.println("Entrada de preço inválida.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir criptomoeda: " + e.getMessage());
        }
    }

    private static void updateCrypto() {
        try {
            System.out.print("Digite o ID da criptomoeda a ser atualizada: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            var existingCrypto = cryptoDAO.findById(id);
            if (existingCrypto == null) {
                System.out.println("Criptomoeda com ID " + id + " não encontrada.");
                return;
            }

            System.out.print("Novo Nome: ");
            String name = scanner.nextLine();
            System.out.print("Novo Símbolo: ");
            String symbol = scanner.nextLine();
            System.out.print("Novo Preço: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Cryptocurrency crypto = new Cryptocurrency(name, symbol, price, existingCrypto.price);
            int rows = cryptoDAO.update(id, crypto);
            if (rows > 0) {
                System.out.println("Criptomoeda atualizada com sucesso!");
            } else {
                System.out.println("A atualização falhou.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar criptomoeda: " + e.getMessage());
        }
    }

    private static void deleteCrypto() {
        try {
            System.out.print("Digite o ID da criptomoeda a ser deletada: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            int rows = cryptoDAO.deleteById(id);
            if (rows > 0) {
                System.out.println("Criptomoeda deletada com sucesso!");
            } else {
                System.out.println("Criptomoeda com ID " + id + " não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar criptomoeda: " + e.getMessage());
        }
    }

    // ===================================================================
    // ======================== INVESTMENTS MENU =========================
    // ===================================================================

    private static void handleInvestmentsMenu() {
        while (true) {
            printSubMenu("Investimentos");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        listAllInvestments();
                        break;
                    case 2:
                        findInvestmentById();
                        break;
                    case 3:
                        addInvestment();
                        break;
                    case 4:
                        updateInvestment();
                        break;
                    case 5:
                        deleteInvestment();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        }
    }

    private static void listAllInvestments() {
        System.out.println("\n--- Lista de Todos os Investimentos ---");
        try {
            var investments = investmentDAO.listAll();
            if (investments.isEmpty()) {
                System.out.println("Nenhum investimento encontrado.");
            } else {
                investments.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar investimentos: " + e.getMessage());
        }
    }

    private static void findInvestmentById() {
        System.out.print("Digite o ID do investimento: ");
        try {
            long id = scanner.nextLong();
            scanner.nextLine();
            var investment = investmentDAO.findById(id);
            if (investment != null) {
                System.out.println(investment);
            } else {
                System.out.println("Investimento com ID " + id + " não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar investimento: " + e.getMessage());
        }
    }

    private static void addInvestment() {
        try {
            System.out.print("ID do Usuário: ");
            long userId = scanner.nextLong();
            System.out.print("ID da Empresa: ");
            long companyId = scanner.nextLong();
            System.out.print("ID da Criptomoeda: ");
            long cryptoId = scanner.nextLong();
            System.out.print("Quantidade: ");
            double amount = scanner.nextDouble();
            System.out.print("Preço de compra: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            long newId = investmentDAO.insert(userId, companyId, cryptoId, amount, price);
            System.out.println("Investimento criado com sucesso! ID: " + newId);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. IDs e valores devem ser numéricos.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir investimento: " + e.getMessage() + ". Verifique se os IDs de usuário, empresa e cripto existem.");
        }
    }

    private static void updateInvestment() {
        try {
            System.out.print("Digite o ID do investimento a ser atualizado: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            if (investmentDAO.findById(id) == null) {
                System.out.println("Investimento com ID " + id + " não encontrado.");
                return;
            }

            System.out.print("Nova Quantidade: ");
            double amount = scanner.nextDouble();
            System.out.print("Novo Preço: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            int rows = investmentDAO.updateById(id, amount, price);
            if (rows > 0) {
                System.out.println("Investimento atualizado com sucesso!");
            } else {
                System.out.println("A atualização falhou.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar investimento: " + e.getMessage());
        }
    }

    private static void deleteInvestment() {
        try {
            System.out.print("Digite o ID do investimento a ser deletado: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            int rows = investmentDAO.deleteById(id);
            if (rows > 0) {
                System.out.println("Investimento deletado com sucesso!");
            } else {
                System.out.println("Investimento com ID " + id + " não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ID inválido.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar investimento: " + e.getMessage());
        }
    }

    // ===================================================================
    // ========================== UTILITIES =============================
    // ===================================================================

    private static void printSubMenu(String moduleName) {
        System.out.printf("\n--- Gerenciar %s ---\n", moduleName);
        System.out.println("1. Listar Todos");
        System.out.println("2. Exibir por ID");
        System.out.println("3. Adicionar Novo");
        System.out.println("4. Atualizar Existente");
        System.out.println("5. Deletar");
        System.out.println("6. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }
}
