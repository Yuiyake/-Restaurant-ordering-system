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
	//构造方法
	System(){
		setTitle("****餐厅点餐系统****");
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
		setResizable(false);    //设置窗口大小不可以变化
		setVisible(true);	  //设置窗口可视
	}
	
	//顶部
	//声明(顶部)菜单栏
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
	//nav初始化
	public void nav_init() {
		jmb = new JMenuBar();
		keeper = new JMenu("查看账单");
		jm = new JMenu("系统设置");
		jmb.add(jm);
		jmb.add(keeper);
		jmb.setBounds(0,0,1500,20);
		jmt1 = new JMenuItem("粉  色");
		jmt2 = new JMenuItem("白  色");
		jmt3 = new JMenuItem("蓝  色");
		jmt4 = new JMenuItem("灰  色");
		jmt8 = new JMenuItem("管理员登陆");
		jm1 = new JMenu("设置标题背景色");
		jm1.add(jmt1);
		jm1.add(jmt2);
		jm1.add(jmt3);
		jm1.add(jmt4);
		jm.add(jm1);
		keeper.add(jmt8);
		add(jmb);
		tjp = new JPanel();
		tjlb = new JLabel("++++ 欢 迎 来 到  开 心 餐 厅 ！ ++++");
		tjlb.setFont(new Font("宋体",1,30));
		tjp.add(tjlb);
		tjp.setBounds(0,20,1500,60);
		add(tjp);
	}
	//nav事件
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
				jta.setFont(new Font("宋体",1,15));
				int i = 0;
				Object[] msg = {"请输入密码:",pwd};
				JFrame jframe = new JFrame("账单");
				JScrollPane scr = new JScrollPane(jta);
				
				try {
					File file = new File("账单.txt");
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
					int res = JOptionPane.showConfirmDialog(null,msg,"请输入密码",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(res == 0) {
						String password= String.valueOf(pwd.getPassword());
						if("123456".equals(password)) {
							jframe.setVisible(true);
							break;
						} else {
							JOptionPane.showMessageDialog(null, "密码错误，请重新输入","提示",JOptionPane.YES_OPTION);
							pwd.setText("");
						}
					}else {
						break;
					}
				}
			}
		});
	}
	
	
	//右侧
	//声明（右侧）表单
	JPanel rjp;
	JLabel rjlb1;
	JLabel rjlb2;
	JLabel rjlb3;
	JLabel jlb_price;
	JComboBox<Integer> jcb1;
	JComboBox<Integer> jcb2;
	
	JTable table;
	DefaultTableModel model;
	String[] names = {"菜名","数量","价格"};
	String row[][][] = new String[15][33][3];
	int money = 0;
	int tableid[]= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};//桌子编号
	int tablenum[] = {1,2,3,4,5,6};//就坐人数
	int total[] = new int[15];
	
	String food[]= {" 红 烧 鱼","蒜蓉小龙虾","糖醋排骨","红烧茄子","手撕包菜","扬州炒饭",
			"香煎猪扒饭","手工芋圆","港式奶茶","爆炒鱿鱼"," 东 坡 肉"," 红 烧 肉"," 红 烧 鱼","水煮肉片",
			" 酸 菜 鱼","蒜蓉小龙虾","笋干炒肉","糖醋排骨","红烧茄子","香煎日本豆腐","尖椒炒肉","开胃娃娃菜"
			,"手撕包菜","鱼香茄子"," 白 米 饭"," 蛋 包 饭","牛肉芝士焗饭","五谷渔粉","香煎猪扒饭","扬州炒饭"
			,"冰      粉","草莓蛋糕","哈根达斯","芒果布丁"," 烧 仙 草","手工芋圆","啵啵奶茶","港式奶茶","可口可乐"
			,"金桔柠檬","西瓜冰","鲜榨橙汁"};
	int price[] = {38,48,28,20,12,15,18,15,12,28,32,32,38,26,38,48,26,28,
			20,20,16,12,12,20,3,18,20,12,18,15,10,16,30,12,10,15,10,12,5,12,10,10};
	
	//表单初始化
	public void right_init() {
		rjp = new JPanel();
		rjp.setLayout(null);
		rjp.setBackground(Color.WHITE);
		rjp.setBounds(1080,80,400,620);
		
		jlb_price = new JLabel("账单总额：" + money + "元");
		jlb_price.setFont(new Font("宋体",1,15));
		jlb_price.setBounds(20,560,300,50);
		rjp.add(jlb_price);
		
		rjlb1 = new JLabel("选择就餐桌号：");
		rjlb1.setFont(new Font("宋体",1,18));
		rjlb2 = new JLabel("请输入用餐人数：");
		rjlb2.setFont(new Font("宋体",1,18));
		rjlb3 = new JLabel("已经点的菜：");
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
			column.setPreferredWidth(120);//每一列默认宽度120
		}
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //设置JTable自动调整列表的状态，此处设置为关闭
		JScrollPane scroll = new JScrollPane(table);   //用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看
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
	
	//中部复选框，数量回复初始值
	void free() {
		for(int i=0; i<42; i++) {
			number[i] = 0;
			num[i].setText("数量" + number[i]);
			jcb[i].setSelected(false);
		}
	}
	
	//餐桌号下拉监听，换桌号则自动更新菜式列表
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
					jlb_price.setText("账单总额:" + money + "元");
				}
			}
		});
	}
	
	//中部
	//声明
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
	
	
	//初始化
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
		
		
		jtp = new JTabbedPane();//选项卡
		jtp.addTab("热销菜式", null, cjp1);
		jtp.addTab("小炒类", null, cjp2);
		jtp.addTab("素菜类", null, cjp3);
		jtp.addTab("主食类", null, cjp4);
		jtp.addTab("甜品类", null, cjp5);
		jtp.addTab("饮品类", null, cjp6);
		jtp.setBackground(Color.WHITE);
		jtp.setBounds(0,0,900,620);
		
		//插入各个选项卡事件
		
		cjp.add(jtp);
		add(cjp);
		
	}
	//实现按钮数量加减
	public void center_event() {
		for(int i=0;i<42;i++) {
			final int j=i;
			numbt1[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(number[j]>0) {
						number[j]--;
						num[j].setText("数量"+number[j]);
					}
				}
			});
			numbt2[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						number[j]++;
						num[j].setText("数量"+number[j]);
				}
			});
		}
	}
	
	//事件
	void rexiao_init() {
		cjp1 = new JPanel();
		cjp1.setLayout(new GridLayout(3,3,10,10));		
		String rx[] = {"红烧鱼 20元","蒜蓉小龙虾 48元","糖醋排骨 28元","红烧茄子 20元",
		"手撕包菜 20元","扬州炒饭 15元","香煎猪扒饭 18元","手工芋圆  15元","港式奶茶 12元"
		};
		String imga = "C:\\Users\\\\Yuiyake-烧杯\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\点餐系统\\src\\img"
				+ "\\1-\\";//图片文件夹路径
		for(int i=0; i<9; i++) {
			
			jp_1[i] = new JPanel();
			jp_1[i].setLayout(new FlowLayout());
			
			jcb[i] = new JCheckBox();
			number[i] = 0;
			numbt1[i]=new JButton("-");
			numbt1[i].setFont(new Font("宋体",1,10));
			num[i]=new JLabel("数量"+number[i]);
			num[i].setFont(new Font("宋体",Font.BOLD,12));
			numbt2[i]=new JButton("+");
			numbt2[i].setFont(new Font("宋体",1,10));
			jlabel[i] = new JLabel(rx[i]);
			jlabel[i].setFont(new Font("宋体",Font.BOLD,12));
			
			String pain=imga+(i+1)+".jpg";//图片途径
			jlb[i]=new JLabel();
			image[i]=new ImageIcon(pain);
			jlb[i].setIcon(image[i]);
			image[i].setImage(image[i].getImage().getScaledInstance(273, 130, Image.SCALE_DEFAULT));
			
			jp_1[i].add(jlb[i]);//图片
			jp_1[i].add(jcb[i]);
			jp_1[i].add(jlabel[i]);//菜名
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
		String xc[] = {"爆炒鱿鱼    28元","东坡肉    32元","红烧肉    32元","红烧鱼    38元","水煮肉片    26元",
				"酸菜鱼    38元","蒜蓉小龙虾    48元","笋干炒肉    26元","糖醋排骨    28元"};
		String imga = "C:\\Users\\\\Yuiyake-烧杯\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\点餐系统\\src\\img"
				+ "\\2-\\";//图片文件夹路径
		for(int i=0; i<9; i++) {
			
			jp_1[9+i] = new JPanel();
			jp_1[9+i].setLayout(new FlowLayout());
			
			number[9+i] = 0;
			numbt1[9+i]=new JButton("-");
			numbt1[9+i].setFont(new Font("宋体",1,10));
			num[9+i]=new JLabel("数量"+number[i]);
			num[9+i].setFont(new Font("宋体",Font.BOLD,12));
			numbt2[9+i]=new JButton("+");
			numbt2[9+i].setFont(new Font("宋体",1,10));
			jlabel[9+i] = new JLabel(xc[i]);
			jlabel[9+i].setFont(new Font("宋体",Font.BOLD,12));
			jcb[9+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//图片途径
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
		String sc[] = {"红烧茄子    20元","红烧日本豆腐  20元","尖椒杏鲍菇    16元","开胃娃娃菜    12元"
				,"手撕包菜    12元","鱼香茄子    20元"};
		String imga = "C:\\Users\\\\Yuiyake-烧杯\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\点餐系统\\src\\img"
				+ "\\3-\\";//图片文件夹路径
		for(int i=0; i<6; i++) {
			
			jp_1[18+i] = new JPanel();
			jp_1[18+i].setLayout(new FlowLayout());
			
			number[18+i] = 0;
			numbt1[18+i]=new JButton("-");
			numbt1[18+i].setFont(new Font("宋体",1,10));
			num[18+i]=new JLabel("数量"+number[i]);
			num[18+i].setFont(new Font("宋体",Font.BOLD,12));
			numbt2[18+i]=new JButton("+");
			numbt2[18+i].setFont(new Font("宋体",1,10));
			jlabel[18+i] = new JLabel(sc[i]);
			jlabel[18+i].setFont(new Font("宋体",Font.BOLD,12));
			jcb[18+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//图片途径
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
		String zs[] = {"白米饭     3元","蛋包饭    18元","牛肉芝士焗饭  20元",
				"五谷鱼粉     12元","香煎猪扒饭    18元","扬州炒饭     15元"};
		String imga = "C:\\Users\\\\Yuiyake-烧杯\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\点餐系统\\src\\img"
				+ "\\4-\\";//图片文件夹路径
		for(int i=0; i<6; i++) {
			
			jp_1[24+i] = new JPanel();
			jp_1[24+i].setLayout(new FlowLayout());
			
			number[24+i] = 0;
			numbt1[24+i]=new JButton("-");
			numbt1[24+i].setFont(new Font("宋体",1,10));
			num[24+i]=new JLabel("数量"+number[i]);
			num[24+i].setFont(new Font("宋体",Font.BOLD,12));
			numbt2[24+i]=new JButton("+");
			numbt2[24+i].setFont(new Font("宋体",1,10));
			jlabel[24+i] = new JLabel(zs[i]);
			jlabel[24+i].setFont(new Font("宋体",Font.BOLD,12));
			jcb[24+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//图片途径
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
		String tp[] = {"冰粉    10元","草莓蛋糕    16元","哈根达斯  30元","芒果布丁    12元",
				"烧仙草     10元","手工芋圆     15元"};
		String imga = "C:\\Users\\\\Yuiyake-烧杯\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\点餐系统\\src\\img"
				+ "\\5-\\";//图片文件夹路径
		for(int i=0; i<6; i++) {
			
			jp_1[30+i] = new JPanel();
			jp_1[30+i].setLayout(new FlowLayout());
			
			number[30+i] = 0;
			numbt1[30+i]=new JButton("-");
			numbt1[30+i].setFont(new Font("宋体",1,10));
			num[30+i]=new JLabel("数量"+number[i]);
			num[30+i].setFont(new Font("宋体",Font.BOLD,12));
			numbt2[30+i]=new JButton("+");
			numbt2[30+i].setFont(new Font("宋体",1,10));
			jlabel[30+i] = new JLabel(tp[i]);
			jlabel[30+i].setFont(new Font("宋体",Font.BOLD,12));
			jcb[30+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//图片途径
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
		String yp[] = {"百香果汁   10元","港式奶茶     12元","可口可乐     5元","金桔柠檬    12元","西瓜冰    10元",
				"鲜榨橙汁     10元"};
		String imga = "C:\\Users\\\\Yuiyake-烧杯\\Desktop\\study\\eclipse-java-"
				+ "2019-06-R-win32-x86_64\\eclipse\\workspace\\点餐系统\\src\\img"
				+ "\\6-\\";//图片文件夹路径
		for(int i=0; i<6; i++) {
			
			jp_1[36+i] = new JPanel();
			jp_1[36+i].setLayout(new FlowLayout());
			
			number[36+i] = 0;
			numbt1[36+i]=new JButton("-");
			numbt1[33+i].setFont(new Font("宋体",1,10));
			num[36+i]=new JLabel("数量"+number[i]);
			num[36+i].setFont(new Font("宋体",Font.BOLD,12));
			numbt2[36+i]=new JButton("+");
			numbt2[36+i].setFont(new Font("宋体",1,10));
			jlabel[36+i] = new JLabel(yp[i]);
			jlabel[36+i].setFont(new Font("宋体",Font.BOLD,12));
			jcb[36+i] = new JCheckBox();
			
			String pain=imga+(i+1)+".jpg";//图片途径
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
	
	
	//底部
	//底部声明
	JPanel jp3;
	JButton jbt1;
	JButton jbt2;
	JButton jbt3;
	JButton jbt4;
	//初始化
	public void bottom_init() {
		jp3 = new JPanel();
		jp3.setLayout(null);
		jp3.setBounds(0,700,1500,150);
		
		jbt1 = new JButton("清  除");
		jbt2 = new JButton("下  单");
		jbt3 = new JButton("加  单");
		jbt4 = new JButton("结  账");
		
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
	
	//底部清空事件方法
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
		jlb_price.setText("账单总额:" + money + "元");
	}
	//底部事件监听
	void bottom_event() {
		jbt1.addActionListener(new ActionListener() {    //清空按钮事件添加，单击按钮清空已点菜式
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "是否要清空本餐桌的已点菜式？", "***提  示***", JOptionPane.YES_NO_OPTION);
				if(result==0) {
					delete();
				}
			}
		
		});
		
		jbt2.addActionListener(new ActionListener() {	//下单按钮事件添加， 按钮，将勾选的菜和数量添加到已点菜单列表

			@Override
			public void actionPerformed(ActionEvent e) {
				//将选中的菜相关信息存入数组
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
				jlb_price.setText("账单总额:" + money + "元");
				free();
			}
		});
		
		jbt3.addActionListener(new ActionListener() {	//加单按钮事件添加，单击按钮，将勾选的菜和数量添加到已点菜式列表

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
				jlb_price.setText("账单总额:" + money + "元");
				free();
				for(int j=n; j<a; j++)
					model.addRow(row[k][j]);
			}
		});
		
		jbt4.addActionListener(new ActionListener() {	//结账按钮事件添加

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int k = jcb1.getSelectedIndex();
				renshu[k] = (int)jcb2.getSelectedItem();
				String str1 = "桌号:"+ (jcb1.getSelectedIndex()+1)+"\n菜名                数量                金额\n";
				for(int i=0; i<row[k].length; i++) {
					if(row[k][i][0] == null)
						break;
					str1+=row[k][i][0] + "                " + row[k][i][1] + "                " + row[k][i][2]+"\n";
				}
				str1 += "\n茶位费：        "+renshu[k]+"  x  4               "+(renshu[k]*4);
				str1 += "\n\n总金额：    "+"              "+((renshu[k]*4)+money)+"   元\n";
				int result = JOptionPane.showConfirmDialog(null, str1,"***账  单***",JOptionPane.YES_NO_OPTION);//返回值为0或1
				if(result == 0) {//如果点“是”，则确认结账，开始写入文件
					try {
						File file = new File("账单.txt");
						if(!file.exists())
							file.createNewFile();
						FileWriter fw = new FileWriter(file,true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("餐桌号:" + (jcb1.getSelectedIndex() + 1));
						bw.newLine();
						bw.write("用餐人数:" + (jcb2.getSelectedIndex() + 1));
						bw.newLine();
						bw.write("*菜名*        *数量*        *金额*");
						bw.newLine();
						for(int i=0; i<row[k].length; i++) {
							if(row[k][i][0] == null)
								break;
							bw.write(row[k][i][0] + "           " + row[k][i][1] + "           " + row[k][i][2]);
							bw.newLine();
						}
						bw.write("茶位费：         "+renshu[k]+"x 4          " + (renshu[k]*4));
						bw.newLine();
						bw.write("总金额：                       "+((renshu[k]*4)+money)+"   元");
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
	
	//左部
	//声明左部
	JPanel ljp;
	JTabbedPane ljtp;
	JButton ljbt1;
	JButton ljbt2;
	JButton ljbt3;
	JButton ljbt4;
	JButton ljbt5;
	JButton ljbt6;
	
	//初始化
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
		
		ljbt1 = new JButton("热销菜式");
		ljbt2 = new JButton("小 炒 类");
		ljbt3 = new JButton("素 菜 类");
		ljbt4 = new JButton("主 食 类");
		ljbt5 = new JButton("甜 品 类");
		ljbt6 = new JButton("饮 品 类");
		ljbt1.setBounds(25,20,100,40);
		ljbt2.setBounds(25,80,100,40);
		ljbt3.setBounds(25,140,100,40);
		ljbt4.setBounds(25,200,100,40);
		ljbt5.setBounds(25,260,100,40);
		ljbt6.setBounds(25,320,100,40);
		
		ljtp.addTab("菜式导航",null,jp6);
		
		jp6.add(ljbt1);
		jp6.add(ljbt2);
		jp6.add(ljbt3);
		jp6.add(ljbt4);
		jp6.add(ljbt5);
		jp6.add(ljbt6);
		ljp.add(ljtp);
		add(ljp);
		
	}
	//点击事件(导航激活效果)
	public void left_event() {
		ljbt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//中部组件的菜单栏激活
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
