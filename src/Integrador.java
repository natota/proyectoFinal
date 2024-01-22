
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableModel;

public class Integrador {

    private JFrame frmCurdOperationSwing;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtApellido;
    private JTextArea txtArea;
    private JTextField txtFecha;
    private JTextField txtMail;

    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Integrador window = new Integrador();//de acuerdo al nombre de la clase
                window.frmCurdOperationSwing.setVisible(true);
            } catch (Exception e) {
            }
        });
    }

    public Integrador() {
        initialize();
        Connect();
        loadData();
    }

    //Database Connection
    Connection con = null;
    PreparedStatement pst;
    ResultSet rs;

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/oradores?characterEncoding=utf8";
            String username = "root";
            String password = "root";
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Clear All
    public void clear() {
        txtID.setText("");
        txtName.setText("");
        txtApellido.setText("");
        txtMail.setText("");
        txtArea.setText("");
        txtFecha.setText("");
        txtName.requestFocus();
    }

    // Load Table
    public void loadData() {
        try {
            pst = con.prepareStatement("Select * from oradores");
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmCurdOperationSwing = new JFrame();
        frmCurdOperationSwing.setTitle("CRUD MYSQL - ARCHIVO XML");
        frmCurdOperationSwing.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
        frmCurdOperationSwing.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("O R A D O R E S ");
        lblNewLabel.setForeground(Color.BLUE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(150, 11, 259, 60);
        frmCurdOperationSwing.getContentPane().add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setBounds(20, 71, 380, 350);
        frmCurdOperationSwing.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Id");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(21, 46, 46, 24);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(21, 81, 55, 24);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Apellido");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_2.setBounds(21, 116, 46, 24);
        panel.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Mail");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_3.setBounds(21, 154, 50, 24);
        panel.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("Tema");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_4.setBounds(21, 192, 50, 24);
        panel.add(lblNewLabel_1_4);

        JLabel lblNewLabel_1_5 = new JLabel("Fecha_alta");
        lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_5.setBounds(21, 268, 100, 24);
        panel.add(lblNewLabel_1_5);

        txtID = new JTextField();
        txtID.setEditable(false);
        txtID.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtID.setBounds(78, 46, 287, 24);
        panel.add(txtID);
        txtID.setColumns(10);

        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtName.setColumns(10);
        txtName.setBounds(78, 81, 287, 24);
        panel.add(txtName);

        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtApellido.setColumns(10);
        txtApellido.setBounds(78, 116, 287, 24);
        panel.add(txtApellido);

        txtMail = new JTextField();
        txtMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMail.setColumns(10);
        txtMail.setBounds(78, 154, 287, 24);
        panel.add(txtMail);

        txtArea = new JTextArea();
        txtArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtArea.setColumns(10);
        txtArea.setBounds(78, 192, 287, 72);
        panel.add(txtArea);

        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtFecha.setColumns(10);
        txtFecha.setBounds(150, 268, 120, 24);
        panel.add(txtFecha);

        JButton btnSave = new JButton("GUARDAR");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtID.getText();
                String name = txtName.getText();
                String apellido = txtApellido.getText();
                String mail = txtMail.getText();
                String tema = txtArea.getText();
                String fecha = txtFecha.getText();

                if (name == null || name.isEmpty() || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE SU NOMBRE");
                    txtName.requestFocus();
                    return;
                }
                if (apellido == null || apellido.isEmpty() || apellido.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE SU APELLIDO");
                    txtApellido.requestFocus();
                    return;
                }
                if (mail == null || mail.isEmpty() || mail.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE SU EMAIL");
                    txtMail.requestFocus();
                    return;
                }
                if (tema == null || tema.isEmpty() || tema.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE TEMA DE LA CONFERENCIA");
                    txtArea.requestFocus();
                    return;
                }
                if (fecha == null || fecha.isEmpty() || fecha.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE FECHA DE HOY");
                    txtFecha.requestFocus();
                    return;
                }

                if (txtID.getText().isEmpty()) {
                    try {
                        String sql = "insert into oradores (nombre,apellido,mail,tema,fecha_alta) values (?,?,?,?,?)";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, name);
                        pst.setString(2, apellido);
                        pst.setString(3, mail);
                        pst.setString(4, tema);
                        pst.setString(5, fecha);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "ingreso exitoso");
                        clear();
                        loadData();
                        CrearXML();

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (IOException ex) {
                        Logger.getLogger(Integrador.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSave.setBounds(20, 305, 105, 23);
        panel.add(btnSave);

        JButton btnUpdate = new JButton("MODIFICAR");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update Details
                String id = txtID.getText();
                String name = txtName.getText();
                String apellido = txtApellido.getText();
                String mail = txtMail.getText();
                String tema = txtArea.getText();
                String fecha = txtFecha.getText();

                if (name == null || name.isEmpty() || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE SU NOMBRE");
                    txtName.requestFocus();
                    return;
                }
                if (apellido == null || apellido.isEmpty() || apellido.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE SU APELLIDO");
                    txtApellido.requestFocus();
                    return;
                }
                if (mail == null || mail.isEmpty() || mail.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE SU EMAIL");
                    txtMail.requestFocus();
                    return;
                }
                if (tema == null || tema.isEmpty() || tema.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE TEMA DE LA CONFERENCIA");
                    txtArea.requestFocus();
                    return;
                }
                if (fecha == null || fecha.isEmpty() || fecha.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "INGRESE FECHA DE ALTA");
                    txtFecha.requestFocus();
                    return;
                }

                if (!txtID.getText().isEmpty()) {
                    try {
                        String sql = "update oradores  set nombre=?,apellido=?, mail=?, tema=?,fecha_alta=? where ID=?";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, name);
                        pst.setString(2, apellido);
                        pst.setString(3, mail);
                        pst.setString(4, tema);
                        pst.setString(5, fecha);
                        pst.setString(6, id);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Dato modificado exitosamente");
                        clear();
                        loadData();
                        CrearXML();

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (IOException ex) {
                        Logger.getLogger(Integrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnUpdate.setBounds(130, 305, 115, 23);
        panel.add(btnUpdate);

        JButton btnDelete = new JButton("BORRAR");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Delete Details
                String id = txtID.getText();
                if (!txtID.getText().isEmpty()) {

                    int result = JOptionPane.showConfirmDialog(null, "SEGURO?, QUIERE ELIMINAR AL ORADOR?", "borrar",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            String sql = "delete from oradores where ID=?";
                            pst = con.prepareStatement(sql);
                            pst.setString(1, id);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "DATO BORRADO EXITOSAMENTE");
                            clear();
                            loadData();
                            CrearXML();

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (IOException ex) {
                            Logger.getLogger(Integrador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDelete.setBounds(250, 305, 105, 23);
        panel.add(btnDelete);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(417, 71, 467, 284);
        frmCurdOperationSwing.getContentPane().add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int index = table.getSelectedRow();
                TableModel model = table.getModel();

                txtID.setText(model.getValueAt(index, 0).toString());
                txtName.setText(model.getValueAt(index, 1).toString());
                txtApellido.setText(model.getValueAt(index, 2).toString());
                txtMail.setText(model.getValueAt(index, 3).toString());
                txtArea.setText(model.getValueAt(index, 4).toString());
                txtFecha.setText(model.getValueAt(index, 5).toString());
            }
        });
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setRowHeight(30);
        scrollPane.setViewportView(table);
        frmCurdOperationSwing.setBounds(450, 100, 1000, 800);
        frmCurdOperationSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*<?xml version=\"1.0\" encoding=\"utf-8\"?>
     <oradores>
        <orador> 
		<nombre>Nombre</nombre> 
		<edad>Edad</edad> 
		<ciudad>Ciudad</ciudad> 
	</orador> 
     </oradores>*/
    public void CrearXML() throws IOException, SQLException {

        String filePath = "d:/Users/naty/Desktop/JAVA/integrador/Oradores.xml";
        //"Este equipo/Escritorio/CURSO_JAVA/integrador/Oradores.xml";

        Path path = Paths.get(filePath);
        Files.delete(path);

        try {
            pst = con.prepareStatement("Select * from oradores");
            rs = pst.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        String line = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";  //Coloco la cabecera
        FileWriter cb = new FileWriter(filePath, true);
        cb.write(line);
        cb.close();

        line = "<oradores >"; //abro el XML
        FileWriter ap = new FileWriter(filePath, true);
        ap.write(line);
        ap.close();

        while (rs.next()) {

            line = "<orador><nombre>" + rs.getString("nombre") + ", " + "</nombre><apellido>"
                    + rs.getString("apellido") + ", " + "</apellido><mail>"
                    + rs.getString("mail") + ", " + "</mail><tema>"
                    + rs.getString("tema") + "," + "</tema><fecha> "
                    + rs.getString("fecha_alta") + "," + "</fecha></orador>";

            FileWriter fw = new FileWriter(filePath, true);
            fw.write(line);
            fw.close();

        }
        line = "</oradores>";
        FileWriter fo = new FileWriter(filePath, true);
        fo.write(line);
        fo.close();
    }

}
