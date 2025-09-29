import javax.swing.SwingUtilities; 

// Esta es la clase principal que inicia la aplicación
public class Main {
    
    // El método principal de la aplicación, el punto de inicio de la ejecución
    public static void main(String[] args) {
        
        // SwingUtilities.invokeLater asegura que el código que manipula la interfaz gráfica
        // se ejecute en el Event Dispatch Thread 
        // es esencial para que la aplicación gráfica funcione de forma segura y correcta
        SwingUtilities.invokeLater(() -> {
            
            // Creamos una instancia de la clase que contiene la lógica central y las estructuras de datos
            RestaurantePro restaurante = new RestaurantePro();
            
            // Creamos y mostramos la ventana de la aplicación, pasándole la lógica central
            new RestauranteAppGUI(restaurante);
        });
    }
}