import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestaurantePro restaurante = new RestaurantePro();
            RestauranteAppGUI app = new RestauranteAppGUI(restaurante);
        });
    }
}