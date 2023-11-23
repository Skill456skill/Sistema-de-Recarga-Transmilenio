import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import java.util.Random;

public class Cajero {

    private JFrame frame;
    private JButton recargarButton;
    private JButton cancelarButton;
    private JFormattedTextField cantidadInsertadaField;
    private JFormattedTextField cambioField;
    private JLabel saldoLabel;
    private JLabel recargaMinimaLabel;
    private JLabel codigoLabel;

    private int saldo;
    private int codigoAleatorio;

    public static void main(String[] args) {
        // Utilizar SwingUtilities para manejar la interfaz gráfica de usuario (GUI)
        SwingUtilities.invokeLater(() -> {
            Cajero cajero = new Cajero();
            cajero.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        // Generar un saldo aleatorio entre -2950 y 20000
        saldo = generarSaldo();

        // Generar un código aleatorio de 5 dígitos
        codigoAleatorio = CodigoAleatorio.generarCodigo();

        // Configurar la ventana principal
        frame = new JFrame("Recarga Transmilenio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Configurar el formato para los campos de texto con números
        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);

        // Configurar los campos de texto para la cantidad y el cambio
        cantidadInsertadaField = new JFormattedTextField(formatter);
        cantidadInsertadaField.setColumns(10);

        cambioField = new JFormattedTextField(formatter);
        cambioField.setColumns(10);

        // Configurar las etiquetas iniciales para saldo, recarga mínima y código
        saldoLabel = new JLabel("Saldo: $" + saldo);
        recargaMinimaLabel = new JLabel("Recarga Mínima: $" + RecargaMinima.verificarSaldoNegativo(saldo));
        codigoLabel = new JLabel("Código: " + codigoAleatorio);

        // Configurar los botones de recargar y cancelar
        recargarButton = new JButton("Recargar");
        estilizarBoton(recargarButton, Color.GREEN, Color.WHITE);
        recargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarRecarga();
            }
        });

        cancelarButton = new JButton("Cancelar");
        estilizarBoton(cancelarButton, Color.RED, Color.WHITE);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarTransaccion();
            }
        });

        // Configurar el panel de recarga con diseño vertical
        JPanel recargaPanel = new JPanel();
        recargaPanel.setLayout(new BoxLayout(recargaPanel, BoxLayout.Y_AXIS));

        // Aumentar el tamaño de la fuente para las etiquetas
        Font font = new Font("Arial", Font.PLAIN, 15);

        recargaPanel.add(saldoLabel);
        recargaPanel.add(recargaMinimaLabel);
        recargaPanel.add(codigoLabel);
        recargaPanel.add(crearEtiqueta("Cantidad Ingresada: ", font));
        recargaPanel.add(cantidadInsertadaField);
        recargaPanel.add(crearEtiqueta("Cambio: ", font));
        recargaPanel.add(cambioField);

        // Configurar el panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(recargarButton);
        buttonPanel.add(cancelarButton);

        // Agregar los paneles al panel principal
        panel.add(recargaPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Configurar la ventana principal
        frame.getContentPane().add(panel);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void realizarRecarga() {
        // Implementación de la lógica de recarga
        int cantidad = (int) cantidadInsertadaField.getValue();
        int vuelto = (int) cambioField.getValue();
        int recargaMinima = RecargaMinima.verificarSaldoNegativo(saldo);

        if (cantidad >= recargaMinima) {
            // Calcular el cambio
            int cambio = cantidad - vuelto;

            if (cambio >= recargaMinima) {
                // Realizar la recarga
                saldo += cambio;

                // Actualizar las etiquetas de saldo y recarga mínima
                saldoLabel.setText("Saldo: $" + saldo);
                recargaMinimaLabel.setText("Recarga Mínima: $" + RecargaMinima.verificarSaldoNegativo(saldo));

                // Mostrar mensaje de recarga exitosa
                JOptionPane.showMessageDialog(frame, "Recarga realizada con éxito. \n Saldo actualizado: $" + saldo +
                        "\n Recarga Mínima Adicional: $" + recargaMinima);
            } else {
                // Mostrar mensaje de cambio insuficiente
                JOptionPane.showMessageDialog(frame, "El cambio no cubre la recarga mínima adicional. Por favor, ingrese una cantidad válida.");
            }
        } else {
            // Mostrar mensaje de recarga insuficiente
            JOptionPane.showMessageDialog(frame, "La cantidad ingresada es inferior a la recarga mínima. Por favor, ingrese una cantidad válida.");
        }
    }

    private void cancelarTransaccion() {
        // Cerrar la aplicación al hacer clic en el botón "Cancelar"
        System.exit(0);
    }

    // Generar saldo aleatorio entre -2950 y 20000
    private int generarSaldo() {
        Random random = new Random();
        return random.nextInt(22501) - 2950;
    }

    // Aumentar el tamaño de la fuente para las etiquetas
    private void estilizarBoton(JButton button, Color backgroundColor, Color textColor) {
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
    }

    // Genera las etiquetas
    private JLabel crearEtiqueta(String texto, Font font) {
        JLabel label = new JLabel(texto);
        label.setFont(font);
        return label;
    }
}

// Clase que gestiona la recarga mínima adicional según el saldo
class RecargaMinima {
    public static int verificarSaldoNegativo(int saldo) {
        if (saldo < 0) {
            return Math.abs(saldo) * 2;
        } else {
            return 0;
        }
    }
}

// Clase que genera un código aleatorio de 5 dígitos
class CodigoAleatorio {
    public static int generarCodigo() {
        Random random = new Random();
        return 10000 + random.nextInt(90000); // Números aleatorios de 10000 a 99999
    }
}
