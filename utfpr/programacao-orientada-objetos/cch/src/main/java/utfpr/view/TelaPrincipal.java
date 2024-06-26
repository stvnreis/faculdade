package utfpr.view;

import org.json.JSONArray;
import org.json.JSONObject;
import utfpr.http.ClienteHttp;
import utfpr.model.Municipio;
import utfpr.model.Municipios;
import utfpr.model.Uf;
import utfpr.model.Ufs;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TelaPrincipal{
    private Ufs ufs;
    private Municipios municipios;
    private Municipios municipiosByUf = new Municipios();
    private JComboBox<String> cb_unidades_federais;
    private JButton bt_carregar;
    private JTable tb_cidades;
    private DefaultTableModel model;

    public TelaPrincipal(){

        JFrame jf = new JFrame("Localidades");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1000, 600);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.getContentPane().setBackground(new Color(127, 139, 154));

        JLabel lb_unidades_federais = new JLabel("UF:");
        lb_unidades_federais.setBounds(30, 50, 20, 30);
        lb_unidades_federais.setForeground(Color.BLACK);
        jf.add(lb_unidades_federais);

        this.cb_unidades_federais = new JComboBox<>();
        this.cb_unidades_federais.setBounds(50, 50, 300, 30);
        this.cb_unidades_federais.setBackground(new Color(157, 168, 182));
        this.cb_unidades_federais.setForeground(Color.BLACK);
        this.cb_unidades_federais.addActionListener(this::adicionarSelecionado);
        jf.add(cb_unidades_federais);

        this.bt_carregar = new JButton("Carregar");
        this.bt_carregar.setBounds(50, 470, 300, 30);
        this.bt_carregar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        this.bt_carregar.setBackground(new Color(157, 168, 182));
        this.bt_carregar.setForeground(Color.BLACK);
        this.bt_carregar.addActionListener(this::fillCBox);
        this.bt_carregar.setFocusPainted(false);
        jf.add(bt_carregar);

        this.tb_cidades = new JTable();
        this.tb_cidades.setBackground(new Color(175, 175, 175));
        this.tb_cidades.setForeground(Color.BLACK);
        this.model = new DefaultTableModel(0, 0);
        this.model.setColumnIdentifiers(new String[]{"Municipios"});
        this.tb_cidades.setModel(model);
        this.tb_cidades.setDefaultEditor(tb_cidades.getColumnClass(tb_cidades.getColumnCount() - 1), null);
        this.tb_cidades.setBounds(450, 50, 500, 450);
        this.tb_cidades.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2)
                    showSelected(e);
            }
        });

        JScrollPane js = new JScrollPane(this.tb_cidades);
        js.setBounds(380, 50, 570, 450);
        js.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(84, 84, 84);
            }
        });
        js.setVisible(true);
        jf.add(js);

        jf.setVisible(true);
    }

    private void fillCBox(ActionEvent e){
        this.ufs = new Ufs();
        ClienteHttp cliente = new ClienteHttp();
        String baseUrl = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios";
        String result = new String(cliente.buscaDados(baseUrl));

        JSONArray arr = new JSONArray(result);

        this.municipios = new Municipios();
        for(int i=0; i<arr.length(); i++){
            JSONObject obj = new JSONObject(arr.getJSONObject(i).toString());
            JSONObject uf = new JSONObject(obj.getJSONObject("microrregiao").getJSONObject("mesorregiao").getJSONObject("UF").toString());
            JSONObject reg = new JSONObject(uf.getJSONObject("regiao").toString());

            int id_municipio = obj.getInt("id");
            String nome_municipio = obj.getString("nome");
            int id_uf = uf.getInt("id");
            String nome_uf = uf.getString("nome");
            String sigla = uf.getString("sigla");
            String regiao = reg.getString("nome");

            Uf unidade = new Uf(id_uf, nome_uf, sigla);
            Municipio municipio = new Municipio(id_municipio, nome_municipio, unidade, regiao);

            this.ufs.addUf(unidade);
            this.municipios.add(municipio);
        }

        for(Uf item: this.ufs.getUfs()){
            this.cb_unidades_federais.addItem(item.getNome());
        }
    }

    private void adicionarSelecionado(ActionEvent e){
        try{
            this.model.setRowCount(0);
            this.municipiosByUf.getMunicipios().clear();
            for(Municipio m: this.municipios.getMunicipiosByUfName(this.cb_unidades_federais.getSelectedItem().toString()).getMunicipios()){
                this.model.addRow(new Object[]{m.getNome()});
                this.municipiosByUf.add(m);
            }
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, "Erro " + exception.getLocalizedMessage());
        }
    }

    private void showSelected(MouseEvent e){
        Municipio m_selected = this.municipiosByUf.getMunicipioAt(this.tb_cidades.getSelectedRow());

        JFrame j = new JFrame();
        j.setSize(400, 200);
        j.setLocationRelativeTo(null);
        j.setLayout(null);

        JLabel lb_id = new JLabel("Id:");
        lb_id.setBounds(50, 30, 50, 20);

        JLabel lb_nome = new JLabel("Nome:");
        lb_nome.setBounds(150, 30, 50, 20);

        JLabel lb_uf = new JLabel("UF:");
        lb_uf.setBounds(50, 70, 20, 20);

        JLabel lb_regiao = new JLabel("Região:");
        lb_regiao.setBounds(150, 70, 50, 20);

        JFormattedTextField txt_id = new JFormattedTextField();
        String id = String.valueOf(m_selected.getId());
        txt_id.setText(id);
        txt_id.setBounds(80, 30, 65, 20);

        JTextField txt_nome = new JTextField();
        txt_nome.setText(m_selected.getNome());
        txt_nome.setBounds(210, 30, 150, 20);

        JTextField txt_uf = new JTextField();
        txt_uf.setText(m_selected.getUf().getSigla());
        txt_uf.setBounds(80, 70, 65, 20);

        JTextField txt_regiao = new JTextField();
        txt_regiao.setText(m_selected.getRegiao());
        txt_regiao.setBounds(210, 70, 100, 20);

        txt_id.setEditable(false);
        txt_nome.setEditable(false);
        txt_uf.setEditable(false);
        txt_regiao.setEditable(false);

        j.add(txt_nome);
        j.add(txt_uf);
        j.add(txt_regiao);
        j.add(txt_id);
        j.add(lb_regiao);
        j.add(lb_uf);
        j.add(lb_nome);
        j.add(lb_id);
        j.setVisible(true);
    }
}