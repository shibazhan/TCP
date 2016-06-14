package tcpTesting;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
public class Set  {
	public void set(JFrame e){
		//�������
		e.setVisible(true);
		//�����޸�
		e.setResizable(false);
		//���ô��������ʾ
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize(); 	
		Dimension framesize = e.getSize(); 
		int x = (int)screensize.getWidth()/2 - (int)framesize.getWidth()/2; 
		int y = (int)screensize.getHeight()/2 - (int)framesize.getHeight()/2; 
		e.setLocation(x,y); 
		//�رմ����¼�
		e.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){				
					System.exit(0);
			}
		});
	}
}