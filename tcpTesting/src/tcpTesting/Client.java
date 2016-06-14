package tcpTesting;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * �ͻ���
 */
public class Client {
	//���Ӵ�����
    private int port;//�˿ں�
    private int numTasks;//��������
    private String IP;//IP��ַ
    private String client_msg;//������Ϣ
    private String kind="Ӧ��ģʽ";//Ĭ����Ӧ��ģʽ
    private int dely;//���ʱ��
    
    
    public int control=0;
    public static String connect_text;
    
    public Client(){}
    public Client(String IP,int port,int numTasks,String msg,String kind,int dely){
    	this.port=port;
    	this.numTasks=numTasks;
    	this.IP=IP;
    	this.client_msg=msg;
    	this.kind=kind;
    	this.dely=dely;
    }
    
    //�������ݱ����Ϣ
    public static String[]table=null;
    private DefaultTableModel listRecords = null;  
    String[] title={"���","��Ӧ��ʱ(ms)","��������","�����ֽ�����","��������","�����ֽ�����"};
    static JTable chart=new JTable();//���ɵ���Ϣ����һ��JTable�ؼ�
    //��ʼ����title�Ǳ��������
    void init(){
    listRecords= new DefaultTableModel(null, this.title);
    chart.setModel(this.listRecords);   
    }
    

    //���Ӳ���

    public void connect() throws InterruptedException{
    	init();
        table=new String[6];//�����Ϣ����          
    	listRecords.insertRow(0,title); //��һ����ʾ��������
		table[2]="1";
		table[3]=String.valueOf(client_msg.getBytes().length);
		table[4]="1";
		table[5]=String.valueOf(client_msg.getBytes().length);
		int i;
        for (i = 1; i <=numTasks; i++) {
        	//������������쳣������ѭ��
        	if(control==1)
        		break;
        	//���ͳ��ύ���񣬽��в���
        	test();   	
        	connect_text=String.valueOf(i);
        	if(kind.equals("���ģʽ"))
        	{
        		Thread.sleep(dely);
        		table[1]=String.valueOf(dely);  
        	}else{
        		table[1]="0";
        	}
        	table[0]=String.valueOf(i);	
        	
			listRecords.insertRow(i,table);
        }
    }
    

    
    
	public void test() {
		try {
			//1.�����ͻ���Socket��ָ����������ַ�Ͷ˿�
			Socket socket=new Socket(IP, port);
			//2.��ȡ���������������˷�����Ϣ
			OutputStream os=socket.getOutputStream();//�ֽ������
			PrintWriter pw=new PrintWriter(os);//���������װΪ��ӡ��
			pw.write(client_msg);
			pw.flush();
			socket.shutdownOutput();//�ر������
			//3.��ȡ������������ȡ�������˵���Ӧ��Ϣ
			InputStream is=socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String info=null;
			while((info=br.readLine())!=null){
				System.out.println("���ǿͻ��ˣ�������˵��"+info);
			}
			//4.�ر���Դ
//			br.close();
//			is.close();
			pw.close();
			os.close();
			//socket.close();
		} catch (Exception e) {
			control=1;
			e.printStackTrace();
		} 
	}
}
