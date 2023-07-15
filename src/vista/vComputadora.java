package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.daoComputadora;
import Modelo.Computadora;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vComputadora extends JFrame {

    /**
     *
     */
    // Se declaran todos los componenetes de la interfaz grafica como variables globales y otros datos y variables que seran necesarios en diversos metodos
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtMarca;
    private JTextField txtProcesador;
    private JTextField txtSO;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    daoComputadora dao=new daoComputadora();
    DefaultTableModel modelo= new DefaultTableModel();
    ArrayList<Computadora> lista;
    private JTable tblComputadoras;
    int fila=-1;
    Computadora computadora=new Computadora();


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    vComputadora frame = new vComputadora();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void actualizarTabla() { //Creamos un metodo que actualice la tabla que aparece en la interfaz

        while(modelo.getRowCount()>0) {
            modelo.removeRow(0);
        }
        lista=dao.consultaComputadoras();
        for(Computadora c:lista) {
            Object compu[]=new Object[3];
            compu[0]=c.getMarca();
            compu[1]=c.getProcesador();
            compu[2]=c.getSistemaoperativo();
            modelo.addRow(compu);
        }
        tblComputadoras.setModel(modelo);
    }

    public void limpiar() { //Creamos un metodo para limpiar los datos ingresados por teclado
        txtMarca.setText("");
        txtProcesador.setText("");
        txtSO.setText("");
    }

    public vComputadora() { //Se crean los diversos elementos de la interfaz grafica
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 827, 352);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Inventario");
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMarca.setBounds(94, 43, 57, 27);
        contentPane.add(lblMarca);

        JLabel lblProcesador = new JLabel("Procesador:");
        lblProcesador.setHorizontalAlignment(SwingConstants.RIGHT);
        lblProcesador.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblProcesador.setBounds(59, 70, 92, 27);
        contentPane.add(lblProcesador);

        JLabel lblSistemaOperativo = new JLabel("Sistema Operativo:");
        lblSistemaOperativo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSistemaOperativo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSistemaOperativo.setBounds(10, 101, 141, 27);
        contentPane.add(lblSistemaOperativo);

        txtMarca = new JTextField();
        txtMarca.setBounds(161, 48, 180, 20);
        contentPane.add(txtMarca);
        txtMarca.setColumns(10);

        txtProcesador = new JTextField();
        txtProcesador.setColumns(10);
        txtProcesador.setBounds(161, 75, 180, 20);
        contentPane.add(txtProcesador);

        txtSO = new JTextField();
        txtSO.setColumns(10);
        txtSO.setBounds(161, 106, 180, 20);
        contentPane.add(txtSO);

        btnAgregar = new JButton("Agregar"); //Le damos funcionalidad al boton agregar
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(txtMarca.getText().equals("")|| txtProcesador.getText().equals("")||txtSO.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "No se permiten campos vacios"); //Indicamos que si algun campo esta vacio no se pueden agregar datos
                        return;
                    }
                    Computadora compu = new Computadora();
                    compu.setMarca(txtMarca.getText());
                    compu.setProcesador(txtProcesador.getText());
                    compu.setSistemaoperativo(txtSO.getText());
                    if(dao.insertarComputadora(compu)) {
                        actualizarTabla();
                        limpiar();
                        JOptionPane.showMessageDialog(null, "Ha sido agregada correctamente"); //Indicamos que se han agregado los datos a la base de datos
                    } else {
                        JOptionPane.showMessageDialog(null, "error");//si se han ingresado algun dato de forma erronea indicamos que ha ocurrido un error
                    }


                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "error");//Si ocurre algun error al momento de agregar se indica
                }
            }
        });
        btnAgregar.setBounds(161, 152, 89, 23);
        contentPane.add(btnAgregar);

        btnEliminar = new JButton("Eliminar");//le damos funcionabilidad al boton eliminar
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int opcion= JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer eliminar este usuario?", "Eliminar usuario", JOptionPane.YES_NO_OPTION);
                    //Para evitar eliminaciones accidenetales agregamos una ventana para preguntar al usuario si esta seguro de querer eliminar
                    if(opcion==0) {
                        if(dao.eliminarComputadora(computadora.getMarca())) {
                            actualizarTabla();
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Se ha eliminado de manera exitosa");//Se le indica al usuario que se ha elimnado el dato de la base de datos
                        }else {
                            JOptionPane.showMessageDialog(null, "Error");//si se han ingresado algun dato de forma erronea indicamos que ha ocurrido un error
                        }
                    }
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error");//Si ocurre algun error al momento de agregar se indica
                }
            }
        });
        btnEliminar.setBounds(161, 186, 89, 23);
        contentPane.add(btnEliminar);

        btnLimpiar = new JButton("Limpiar"); //Se le da funcionabilidad al boton de limpiar para que borre los datos de las casillas ingresados por teclado
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
        btnLimpiar.setBounds(161, 220, 89, 23);
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(376, 11, 425, 276);
        contentPane.add(scrollPane);

        tblComputadoras = new JTable();
        tblComputadoras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fila=tblComputadoras.getSelectedRow();
                computadora=lista.get(fila);
                txtMarca.setText(computadora.getMarca());
                txtProcesador.setText(computadora.getProcesador());
                txtSO.setText(computadora.getSistemaoperativo());
            }
        });
        tblComputadoras.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                },
                new String[] {
                        "New column", "New column", "New column"
                }
        ));
        scrollPane.setViewportView(tblComputadoras);
        modelo.addColumn("Marca");
        modelo.addColumn("Procesador");
        modelo.addColumn("Sistema Operativo");
        actualizarTabla();//Llamamos al metodo actualizar tbala para que cada vez que se ejecute el programa o se realice algun cambio se actualice la tabla.

    }
}