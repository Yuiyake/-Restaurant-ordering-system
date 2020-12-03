import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class System extends JFrame {
	//���췽��
	System(){
		setTitle("****�������ϵͳ****");
		setBounds(20,20,1500,820);
		setLayout(null);
		nav_init();
		nav_event();
		right_init();
		bottom_init();
		bottom_event();
		center_init();
		center_event();
		left_init();
		left_event();
		setResizable(false);    //���ô��ڴ�С�����Ա仯
		setVisible(true);	  //���ô��ڿ���
	}
	
	//����
	//����(����)�˵���
	JMenuBar jmb;
	JMenu jm;
	JMenu jm1;
	JMenu keeper;
	JMenuItem jmt1;
	JMenuItem jmt2;
	JMenuItem jmt3;
	JMenuItem jmt4;
	JMenuItem jmt8;
	JPanel tjp;
	JLabel tjlb;
	//nav��ʼ��
	public void nav_init() {
		jmb = new JMenuBar();
		keeper = new JMenu("�鿴�˵�");
		jm = new JMenu("ϵͳ����");
		jmb.add(jm);
		jmb.add(keeper);
		jmb.setBounds(0,0,1500,20);
		jmt1 = new JMenuItem("��  ɫ");
		jmt2 = new JMenuItem("��  ɫ");
		jmt3 = new JMenuItem("��  ɫ");
		jmt4 = new JMenuItem("��  ɫ");
		jmt8 = new JMenuItem("����Ա��½");
		jm1 = new JMenu("���ñ��ⱳ��ɫ");
		jm1.add(jmt1);
		jm1.add(jmt2);
		jm1.add(jmt3);
		jm1.add(jmt4);
		jm.add(jm1);
		keeper.add(jmt8);
		add(jmb);
		tjp = new JPanel();
		tjlb = new JLabel("++++ �� ӭ �� ��  �� �� �� �� �� ++++");
		tjlb.setFont(new Font("����",1,30));
		tjp.add(tjlb);
		tjp.setBounds(0,20,1500,60);
		add(tjp);
	}
	//nav�¼�
	public void nav_event() {
		jmt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tjp.setBackground(Color.PINK);
			}
		});
		jmt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tjp.setBackground(Color.WHITE);
			}
		});
		jmt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tjp.setBackground(Color.CYAN);
			}
		});
		jmt4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tjp.setBackground(Color.LIGHT_GRAY);
			}
		});
		jmt8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JPasswordField pwd = new JPasswordField();
				JTextArea jta = new JTextArea();
				jta.setFont(new Font("����",1,15));
				int i = 0;
				Object[] msg = {"����������:",pwd};
				JFrame jframe = new JFrame("�˵�");
				JScrollPane scr = new JScrollPane(jta);
				
				try {
					File file = new File("�˵�.txt");
					if(!file.exists())
						file.createNewFile();
					
					FileReader fileReader = new FileReader(file);
					BufferedReader br = new BufferedReader(fileReader);
					String line;
					while((line = br.readLine()) != null) {
						jta.append(line + "\n");
						i++;
					}
					br.close();
				} catch(IOException e1) {
					e1.printStackTrace();
				}
				jframe.add(scr);
				jframe.setBounds(600,200,600,700);
				while(true) {
					int res = JOptionPane.showConfirmDialog(null,msg,"����������",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(res == 0) {
						String password= String.valueOf(pwd.getPassword());
						if("123456".equals(password)) {
							jframe.setVisible(true);
							break;
						} else {
							JOptionPane.showMessageDialog(null, "�����������������","��ʾ",JOptionPane.YES_OPTION);
							pwd.setText("");
						}
					}else {
						break;
					}
				}
			}
		});
	}
	
	
	//�Ҳ�
	//�������Ҳࣩ��
	JPanel rjp;
	JLabel rjlb1;
	JLabel rjlb2;
	JLabel rjlb3;
	JLabel jlb_price;
	JComboBox<Integer> jcb1;
	JComboBox<Integer> jcb2;
	
	JTable table;
	DefaultTableModel model;
	String[] names = {"����","����","�۸�"};
	String row[][][] = new String[15][33][3];
	int money = 0;
	int tableid[]= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};//���ӱ��
	int tablenum[] = {1,2,3,4,5,6};//��������
	int total[] = new int[15];
	
	String food[]= {" �� �� ��","����С��Ϻ","�Ǵ��Ź�","��������","��˺����","���ݳ���",
			"�����Ƿ�","�ֹ���Բ","��ʽ�̲�","��������"," �� �� ��"," �� �� ��"," �� �� ��","ˮ����Ƭ",
			" �� �� ��","����С��Ϻ","��ɳ���","�Ǵ��Ź�","��������","����ձ�����","�⽷����","��θ���޲�"
			,"��˺����","��������"," �� �� ��"," �� �� ��","ţ��֥ʿ�h��","������","�����Ƿ�","���ݳ���"
			,"��      ��","��ݮ����","������˹","â������"," �� �� ��","�ֹ���Բ","���̲�","��ʽ�̲�","�ɿڿ���"
			,"�������","���ϱ�","��ե��֭"};
	int price[] = {38,48,28,20,12,15,18,15,12,28,32,32,38,26,38,48,26,28,
			20,20,16,12,12,20,3,18,20,12,18,15,10,16,30,12,10,15,10,12,5,12,10,10};
	
	//����ʼ��
	public void right_init() {
		rjp = new JPanel();
		rjp.setLayout(null);
		rjp.setBackground(Color.WHITE);
		rjp.setBounds(1080,80,400,620);
		
		jlb_price = new JLabel("�˵��ܶ" + money + "Ԫ");
		jlb_price.setFont(new Font("����",1,15));
		jlb_price.setBounds(20,560,300,50);
		rjp.add(jlb_price);
		
		rjlb1 = new JLabel("ѡ��Ͳ����ţ�");
		rjlb1.setFont(new Font("����",1,18));
		rjlb2 = new JLabel("�������ò�������");
		rjlb2.setFont(new Font("����",1,18));
		rjlb3 = new JLabel("�Ѿ���Ĳˣ�");
		jcb1 = new JComboBox<Integer>();
		jcb2 = new JComboBox<Integer>();
		for(int i=0; i<tableid.length; i++) {
			jcb1.addItem(tableid[i]);
		}
		for(int i=0; i<tablenum.length; i++) {
			jcb2.addItem(tablenum[i]);
		}
		
		
		model = new DefaultTableModel(null,names);
		table = new JTable(model);
		TableColumn column = null;
		int columns = table.getColumnCount();
		for(int i=0; i<columns; i++) {
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(120);//ÿһ��Ĭ�Ͽ��120
		}
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //����JTable�Զ������б��״̬���˴�����Ϊ�ر�
		JScrollPane scroll = new JScrollPane(table);   //��JScrollPaneװ��JTable������������Χ���оͿ���ͨ�����������鿴
		scroll.setBounds(20, 220, 363, 340);
		table_change();
		
		
		rjlb1.setBounds(20,0,200,30);
		jcb1.setBounds(20, 40, 200, 30);
		rjlb2.setBounds(20, 90, 200, 30);
		jcb2.setBounds(20, 130, 200, 30);
		rjlb3.setBounds(20, 180, 200, 30);
		
		rjp.add(rjlb1);
		rjp.add(jcb1);
		rjp.add(rjlb2);
		rjp.add(jcb2);
		rjp.add(rjlb3);
		rjp.add(scroll);
		add(rjp);
		rjp.setBorder(BorderFactory.createLineBorder(Color.pink,2));
		
	}
	
	//�в���ѡ�������ظ���ʼֵ
	void free() {
		for(int i=0; i<42; i++) {
			number[i] = 0;
			num[i].setText("����" + number[i]);
			jcb[i].setSelected(false);
		}
	}
	
	//�������������������������Զ����²�ʽ�б�
	void table_change(){
		jcb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					int k = jcb1.getSelectedIndex();
					free();
					model.setRowCount(0);
					jcb2.setSelectedIndex(0);
					money = 0;
					if(row[k][0][0] != null) {
						for(int i=0; i<row[k].length; i++) {
							if(row[k][i][0] == null)
								break;
							model.addRow(row[k][i]);
						}
						jcb2.setSelectedIndex(renshu[k]);
						money = total[k];
					}
					jlb_price.setText("�˵��ܶ�:" + money + "Ԫ");
				}
			}
		});
	}
	
	//�в�
	//����
	JPanel cjp;
	JTabbedPane jtp;
	JCheckBox[] jcb = new JCheckBox[42];
	ImageIcon image[] = new ImageIcon[42];
	JLabel jlb[] = new JLabel[42];
	JLabel jlabel[] = new JLabel[42];
	JLabel num[] = new JLabel[42];
	int number[] = new int[42];
	JButton numbt1[] = new JButton[42];
	JButton numbt2[] = new JButton[42];
	int renshu[] = new int[15];
	JPanel cjp1;
	JPanel cjp2;
	JPanel cjp3;
	JPanel cjp4;
	JPanel cjp5;
	JPanel cjp6;
	
	JLabel imglabel1[] = new JLabel[9];
	JPanel jp_1[] = new JPanel[42];
	
	
	//��ʼ��
	public void center_init() {
		cjp = new JPanel();
		cjp.setLayout(null);
		cjp.setBounds(170,80,900,620);
		
		rexiao_init();
		xiaochao_init();
		sucai_init();
		zhushi_init();
		tianpin_init();
		yinpin_init();
		
		
		jtp = new JTabbedPane();//ѡ�
		jtp.addTab("������ʽ", null, cjp1);
		jtp.addTab("С����", null, cjp2);
		jtp.addTab("�ز���", null, cjp3);
		jtp.addTab("��ʳ��", null, cjp4);
		jtp.addTab("��Ʒ��", null, cjp5);
		jtp.addTab("��Ʒ��", null, cjp6);
		jtp.setBackground(Color.WHITE);
		jtp.setBounds(0,0,900,620);
		
		//�������ѡ��¼�
		
		cjp.add(jtp);
		add(cjp);
		
	}
	//ʵ�ְ�ť�����Ӽ�
	public void center_event() {
		for(int i=0;i<42;i++) {
			final int j=i;
			numbt1[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(number[j]>0) {
						number[j]--;
						num[j].setText("����"+number[j]);
					}
				}
			});
			numbt2[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						number[j]++;
						num[j].setText("����"+number[j]);
				}
			});
		}
	}
	
	//�¼�
	void rexiao_init() {
		cjp1 = new JPanel();
		cjp1.setLayout(new GridLayout(3,3,10,10));		
		String rx[] = {"������ 20Ԫ","����С��Ϻ 48Ԫ","�Ǵ��Ź� 28Ԫ","�������� 20Ԫ",
		"��˺���� 20Ԫ","���ݳ��� 15Ԫ","�����Ƿ� 18Ԫ","�ֹ���Բ  15Ԫ","��ʽ�̲� 12Ԫ"
		};
		String imga = "C:\\Users\\\\Yuiyake-�ձ�\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\���ϵͳ\\src\\img"
				+ "\\1-\\";//ͼƬ�ļ���·��
		for(int i=0; i<9; i++) {
			
			jp_1[i] = new JPanel();
			jp_1[i].setLayout(new FlowLayout());
			
			jcb[i] = new JCheckBox();
			number[i] = 0;
			numbt1[i]=new JButton("-");
			numbt1[i].setFont(new Font("����",1,10));
			num[i]=new JLabel("����"+number[i]);
			num[i].setFont(new Font("����",Font.BOLD,12));
			numbt2[i]=new JButton("+");
			numbt2[i].setFont(new Font("����",1,10));
			jlabel[i] = new JLabel(rx[i]);
			jlabel[i].setFont(new Font("����",Font.BOLD,12));
			
			String pain=imga+(i+1)+".jpg";//ͼƬ;��
			jlb[i]=new JLabel();
			image[i]=new ImageIcon(pain);
			jlb[i].setIcon(image[i]);
			image[i].setImage(image[i].getImage().getScaledInstance(273, 130, Image.SCALE_DEFAULT));
			
			jp_1[i].add(jlb[i]);//ͼƬ
			jp_1[i].add(jcb[i]);
			jp_1[i].add(jlabel[i]);//����
			jp_1[i].add(num[i]);
			jp_1[i].add(numbt1[i]);
			jp_1[i].add(numbt2[i]);
			cjp1.add(jp_1[i]);
		}
		
		add(cjp1);
	}
	
	void xiaochao_init() {
		cjp2 = new JPanel();
		cjp2.setLayout(new GridLayout(3,3,10,10));		
		String xc[] = {"��������    28Ԫ","������    32Ԫ","������    32Ԫ","������    38Ԫ","ˮ����Ƭ    26Ԫ",
				"�����    38Ԫ","����С��Ϻ    48Ԫ","��ɳ���    26Ԫ","�Ǵ��Ź�    28Ԫ"};
		String imga = "C:\\Users\\\\Yuiyake-�ձ�\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\���ϵͳ\\src\\img"
				+ "\\2-\\";//ͼƬ�ļ���·��
		for(int i=0; i<9; i++) {
			
			jp_1[9+i] = new JPanel();
			jp_1[9+i].setLayout(new FlowLayout());
			
			number[9+i] = 0;
			numbt1[9+i]=new JButton("-");
			numbt1[9+i].setFont(new Font("����",1,10));
			num[9+i]=new JLabel("����"+number[i]);
			num[9+i].setFont(new Font("����",Font.BOLD,12));
			numbt2[9+i]=new JButton("+");
			numbt2[9+i].setFont(new Font("����",1,10));
			jlabel[9+i] = new JLabel(xc[i]);
			jlabel[9+i].setFont(new Font("����",Font.BOLD,12));
			jcb[9+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//ͼƬ;��
			jlb[9+i]=new JLabel();
			image[9+i]=new ImageIcon(pain);
			jlb[9+i].setIcon(image[9+i]);
			image[9+i].setImage(image[9+i].getImage().getScaledInstance(273, 130, Image.SCALE_DEFAULT));
			
			jp_1[9+i].add(jlb[9+i]);
			jp_1[9+i].add(jcb[9+i]);
			jp_1[9+i].add(jlabel[9+i]);
			jp_1[9+i].add(num[9+i]);
			jp_1[9+i].add(numbt1[9+i]);
			jp_1[9+i].add(numbt2[9+i]);
			cjp2.add(jp_1[9+i]);
		}
		
		add(cjp2);
	}
	
	void sucai_init() {
		cjp3 = new JPanel();
		cjp3.setLayout(new GridLayout(2,3,10,10));		
		String sc[] = {"��������    20Ԫ","�����ձ�����  20Ԫ","�⽷�ӱ���    16Ԫ","��θ���޲�    12Ԫ"
				,"��˺����    12Ԫ","��������    20Ԫ"};
		String imga = "C:\\Users\\\\Yuiyake-�ձ�\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\���ϵͳ\\src\\img"
				+ "\\3-\\";//ͼƬ�ļ���·��
		for(int i=0; i<6; i++) {
			
			jp_1[18+i] = new JPanel();
			jp_1[18+i].setLayout(new FlowLayout());
			
			number[18+i] = 0;
			numbt1[18+i]=new JButton("-");
			numbt1[18+i].setFont(new Font("����",1,10));
			num[18+i]=new JLabel("����"+number[i]);
			num[18+i].setFont(new Font("����",Font.BOLD,12));
			numbt2[18+i]=new JButton("+");
			numbt2[18+i].setFont(new Font("����",1,10));
			jlabel[18+i] = new JLabel(sc[i]);
			jlabel[18+i].setFont(new Font("����",Font.BOLD,12));
			jcb[18+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//ͼƬ;��
			jlb[18+i]=new JLabel();
			image[18+i]=new ImageIcon(pain);
			jlb[18+i].setIcon(image[18+i]);
			image[18+i].setImage(image[18+i].getImage().getScaledInstance(273, 200, Image.SCALE_DEFAULT));
			
			jp_1[18+i].add(jlb[18+i]);
			jp_1[18+i].add(jcb[18+i]);
			jp_1[18+i].add(jlabel[18+i]);
			jp_1[18+i].add(num[18+i]);
			jp_1[18+i].add(numbt1[18+i]);
			jp_1[18+i].add(numbt2[18+i]);
			cjp3.add(jp_1[18+i]);
		}
		add(cjp3);
	}
	
	void zhushi_init() {
		cjp4 = new JPanel();
		cjp4.setLayout(new GridLayout(2,3,10,10));		
		String zs[] = {"���׷�     3Ԫ","������    18Ԫ","ţ��֥ʿ�h��  20Ԫ",
				"������     12Ԫ","�����Ƿ�    18Ԫ","���ݳ���     15Ԫ"};
		String imga = "C:\\Users\\\\Yuiyake-�ձ�\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\���ϵͳ\\src\\img"
				+ "\\4-\\";//ͼƬ�ļ���·��
		for(int i=0; i<6; i++) {
			
			jp_1[24+i] = new JPanel();
			jp_1[24+i].setLayout(new FlowLayout());
			
			number[24+i] = 0;
			numbt1[24+i]=new JButton("-");
			numbt1[24+i].setFont(new Font("����",1,10));
			num[24+i]=new JLabel("����"+number[i]);
			num[24+i].setFont(new Font("����",Font.BOLD,12));
			numbt2[24+i]=new JButton("+");
			numbt2[24+i].setFont(new Font("����",1,10));
			jlabel[24+i] = new JLabel(zs[i]);
			jlabel[24+i].setFont(new Font("����",Font.BOLD,12));
			jcb[24+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//ͼƬ;��
			jlb[24+i]=new JLabel();
			image[24+i]=new ImageIcon(pain);
			jlb[24+i].setIcon(image[24+i]);
			image[24+i].setImage(image[24+i].getImage().getScaledInstance(273, 200, Image.SCALE_DEFAULT));
			
			jp_1[24+i].add(jlb[24+i]);
			jp_1[24+i].add(jcb[24+i]);
			jp_1[24+i].add(jlabel[24+i]);
			jp_1[24+i].add(num[24+i]);
			jp_1[24+i].add(numbt1[24+i]);
			jp_1[24+i].add(numbt2[24+i]);
			cjp4.add(jp_1[24+i]);
		}
		add(cjp4);
	}
	
	void tianpin_init() {
		cjp5 = new JPanel();
		cjp5.setLayout(new GridLayout(2,3,10,10));		
		String tp[] = {"����    10Ԫ","��ݮ����    16Ԫ","������˹  30Ԫ","â������    12Ԫ",
				"���ɲ�     10Ԫ","�ֹ���Բ     15Ԫ"};
		String imga = "C:\\Users\\\\Yuiyake-�ձ�\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\���ϵͳ\\src\\img"
				+ "\\5-\\";//ͼƬ�ļ���·��
		for(int i=0; i<6; i++) {
			
			jp_1[30+i] = new JPanel();
			jp_1[30+i].setLayout(new FlowLayout());
			
			number[30+i] = 0;
			numbt1[30+i]=new JButton("-");
			numbt1[30+i].setFont(new Font("����",1,10));
			num[30+i]=new JLabel("����"+number[i]);
			num[30+i].setFont(new Font("����",Font.BOLD,12));
			numbt2[30+i]=new JButton("+");
			numbt2[30+i].setFont(new Font("����",1,10));
			jlabel[30+i] = new JLabel(tp[i]);
			jlabel[30+i].setFont(new Font("����",Font.BOLD,12));
			jcb[30+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//ͼƬ;��
			jlb[30+i]=new JLabel();
			image[30+i]=new ImageIcon(pain);
			jlb[30+i].setIcon(image[30+i]);
			image[30+i].setImage(image[30+i].getImage().getScaledInstance(273, 200, Image.SCALE_DEFAULT));
			
			jp_1[30+i].add(jlb[30+i]);
			jp_1[30+i].add(jcb[30+i]);
			jp_1[30+i].add(jlabel[30+i]);
			jp_1[30+i].add(num[30+i]);
			jp_1[30+i].add(numbt1[30+i]);
			jp_1[30+i].add(numbt2[30+i]);
			cjp5.add(jp_1[30+i]);
		}
		add(cjp5);
	}

	void yinpin_init() {
		cjp6 = new JPanel();
		cjp6.setLayout(new GridLayout(2,3,10,10));		
		String yp[] = {"�����֭   10Ԫ","��ʽ�̲�     12Ԫ","�ɿڿ���     5Ԫ","�������    12Ԫ","���ϱ�    10Ԫ",
				"��ե��֭     10Ԫ"};
		String imga = "C:\\Users\\\\Yuiyake-�ձ�\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\���ϵͳ\\src\\img"
				+ "\\6-\\";//ͼƬ�ļ���·��
		for(int i=0; i<6; i++) {
			
			jp_1[36+i] = new JPanel();
			jp_1[36+i].setLayout(new FlowLayout());
			
			number[36+i] = 0;
			numbt1[36+i]=new JButton("-");
			numbt1[33+i].setFont(new Font("����",1,10));
			num[36+i]=new JLabel("����"+number[i]);
			num[36+i].setFont(new Font("����",Font.BOLD,12));
			numbt2[36+i]=new JButton("+");
			numbt2[36+i].setFont(new Font("����",1,10));
			jlabel[36+i] = new JLabel(yp[i]);
			jlabel[36+i].setFont(new Font("����",Font.BOLD,12));
			jcb[36+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//ͼƬ;��
			jlb[36+i]=new JLabel();
			image[36+i]=new ImageIcon(pain);
			jlb[36+i].setIcon(image[36+i]);
			image[36+i].setImage(image[36+i].getImage().getScaledInstance(273, 200, Image.SCALE_DEFAULT));
			
			jp_1[36+i].add(jlb[36+i]);
			jp_1[36+i].add(jcb[36+i]);
			jp_1[36+i].add(jlabel[36+i]);
			jp_1[36+i].add(num[36+i]);
			jp_1[36+i].add(numbt1[36+i]);
			jp_1[36+i].add(numbt2[36+i]);
			cjp6.add(jp_1[36+i]);
		}
		add(cjp5);
	}
	
	
	//�ײ�
	//�ײ�����
	JPanel jp3;
	JButton jbt1;
	JButton jbt2;
	JButton jbt3;
	JButton jbt4;
	//��ʼ��
	public void bottom_init() {
		jp3 = new JPanel();
		jp3.setLayout(null);
		jp3.setBounds(0,700,1500,150);
		
		jbt1 = new JButton("��  ��");
		jbt2 = new JButton("��  ��");
		jbt3 = new JButton("��  ��");
		jbt4 = new JButton("��  ��");
		
		jbt1.setBounds(400,20,100,50);
		jbt2.setBounds(600,20,100,50);
		jbt3.setBounds(800,20,100,50);
		jbt4.setBounds(1000,20,100,50);
		
		jp3.add(jbt1);
		jp3.add(jbt2);
		jp3.add(jbt3);
		jp3.add(jbt4);
		add(jp3);
	}
	
	//�ײ�����¼�����
	void delete() {
		int k = jcb1.getSelectedIndex();
		free();
		model.setRowCount(0);
		jcb2.setSelectedIndex(0);
		money=0;
		for(int i=0; i<row[k].length; i++) {
			row[k][i][0] = null;
			row[k][i][1] = null;
			row[k][i][2] = null;
		}
		total[k] = 0;
		jlb_price.setText("�˵��ܶ�:" + money + "Ԫ");
	}
	//�ײ��¼�����
	void bottom_event() {
		jbt1.addActionListener(new ActionListener() {    //��հ�ť�¼���ӣ�������ť����ѵ��ʽ
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ��ձ��������ѵ��ʽ��", "***��  ʾ***", JOptionPane.YES_NO_OPTION);
				if(result==0) {
					delete();
				}
			}
		
		});
		
		jbt2.addActionListener(new ActionListener() {	//�µ���ť�¼���ӣ� ��ť������ѡ�Ĳ˺�������ӵ��ѵ�˵��б�

			@Override
			public void actionPerformed(ActionEvent e) {
				//��ѡ�еĲ������Ϣ��������
				int k = jcb1.getSelectedIndex();
				renshu[k] = (int)jcb2.getSelectedItem()-1;
				model.setRowCount(0);
				for(int i=0; i<row[k].length; i++) {
					row[k][i][0] = null;
					row[k][i][1] = null;
					row[k][i][2] = null;
				}
				total[k] = 0;
				int a = 0;
				for(int i=0; i<42; i++) {
					if(jcb[i].isSelected() && number[i] != 0) {
						row[k][a][0] = food[i];
						row[k][a][1] = ""+number[i];
						row[k][a][2] = "" + number[i]*price[i];
						a++;
						total[k]+= number[i]*price[i];
					}
				}
				for(int j=0; j<a; j++) {
					model.addRow(row[k][j]);
				}
				money = total[k];
				jlb_price.setText("�˵��ܶ�:" + money + "Ԫ");
				free();
			}
		});
		
		jbt3.addActionListener(new ActionListener() {	//�ӵ���ť�¼���ӣ�������ť������ѡ�Ĳ˺�������ӵ��ѵ��ʽ�б�

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int k = jcb1.getSelectedIndex();
				int a = 0;
				for(int i=0; i<row[k].length; i++) {
					if(row[k][i][0] != null)
						a++;
				}
				int n = a;
				for(int i=0; i<42; i++) {
					if(jcb[i].isSelected() && number[i] !=0) {
						row[k][a][0] = food[i];
						row[k][a][1] = "" + number[i];
						row[k][a][2] = "" + number[i]*price[i];
						a++;
						total[k] += number[i]*price[i];
					}
				}
				money = total[k];
				jlb_price.setText("�˵��ܶ�:" + money + "Ԫ");
				free();
				for(int j=n; j<a; j++)
					model.addRow(row[k][j]);
			}
		});
		
		jbt4.addActionListener(new ActionListener() {	//���˰�ť�¼����

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int k = jcb1.getSelectedIndex();
				renshu[k] = (int)jcb2.getSelectedItem();
				String str1 = "����:"+ (jcb1.getSelectedIndex()+1)+"\n����                ����                ���\n";
				for(int i=0; i<row[k].length; i++) {
					if(row[k][i][0] == null)
						break;
					str1+=row[k][i][0] + "                " + row[k][i][1] + "                " + row[k][i][2]+"\n";
				}
				str1 += "\n��λ�ѣ�        "+renshu[k]+"  x  4               "+(renshu[k]*4);
				str1 += "\n\n�ܽ�    "+"              "+((renshu[k]*4)+money)+"   Ԫ\n";
				int result = JOptionPane.showConfirmDialog(null, str1,"***��  ��***",JOptionPane.YES_NO_OPTION);//����ֵΪ0��1
				if(result == 0) {//����㡰�ǡ�����ȷ�Ͻ��ˣ���ʼд���ļ�
					try {
						File file = new File("�˵�.txt");
						if(!file.exists())
							file.createNewFile();
						FileWriter fw = new FileWriter(file,true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("������:" + (jcb1.getSelectedIndex() + 1));
						bw.newLine();
						bw.write("�ò�����:" + (jcb2.getSelectedIndex() + 1));
						bw.newLine();
						bw.write("*����*        *����*        *���*");
						bw.newLine();
						for(int i=0; i<row[k].length; i++) {
							if(row[k][i][0] == null)
								break;
							bw.write(row[k][i][0] + "           " + row[k][i][1] + "           " + row[k][i][2]);
							bw.newLine();
						}
						bw.write("��λ�ѣ�         "+renshu[k]+"x 4          " + (renshu[k]*4));
						bw.newLine();
						bw.write("�ܽ�                       "+((renshu[k]*4)+money)+"   Ԫ");
						bw.newLine();
						bw.write("--------------------------------------------------------");
						bw.newLine();
						bw.close();
					} catch(IOException e1) {
						e1.printStackTrace();
					}
					delete();
				}
			}
		});
		
	}
	
	//��
	//������
	JPanel ljp;
	JTabbedPane ljtp;
	JButton ljbt1;
	JButton ljbt2;
	JButton ljbt3;
	JButton ljbt4;
	JButton ljbt5;
	JButton ljbt6;
	
	//��ʼ��
	public void left_init() {
		ljp = new JPanel();
		ljp.setLayout(null);
		ljp.setBounds(10,80,150,620);
		
		ljtp = new JTabbedPane();
		ljtp.setBackground(Color.WHITE);
		ljtp.setBounds(0,0,150,620);
		
		JPanel jp6 = new JPanel();
		jp6.setLayout(null);
		jp6.setBounds(0,0,150,600);
		
		ljbt1 = new JButton("������ʽ");
		ljbt2 = new JButton("С �� ��");
		ljbt3 = new JButton("�� �� ��");
		ljbt4 = new JButton("�� ʳ ��");
		ljbt5 = new JButton("�� Ʒ ��");
		ljbt6 = new JButton("�� Ʒ ��");
		ljbt1.setBounds(25,20,100,40);
		ljbt2.setBounds(25,80,100,40);
		ljbt3.setBounds(25,140,100,40);
		ljbt4.setBounds(25,200,100,40);
		ljbt5.setBounds(25,260,100,40);
		ljbt6.setBounds(25,320,100,40);
		
		ljtp.addTab("��ʽ����",null,jp6);
		
		jp6.add(ljbt1);
		jp6.add(ljbt2);
		jp6.add(ljbt3);
		jp6.add(ljbt4);
		jp6.add(ljbt5);
		jp6.add(ljbt6);
		ljp.add(ljtp);
		add(ljp);
		
	}
	//����¼�(��������Ч��)
	public void left_event() {
		ljbt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//�в�����Ĳ˵�������
				jtp.setSelectedIndex(0);
			}
		});
		ljbt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtp.setSelectedIndex(1);
			}
		});
		ljbt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtp.setSelectedIndex(2);
			}
		});
		ljbt4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtp.setSelectedIndex(3);
			}
		});
		ljbt5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtp.setSelectedIndex(4);
			}
		});
		ljbt6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtp.setSelectedIndex(5);
			}
		});
		
	}
	
	
	public static void main(String[] args) {
		
		new System();
		
	}

}
