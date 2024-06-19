//シンプルアニメーションの作成
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

//▼▼スレッドを利用する為のインターフェースを実装する
public class L0413Ex_pr8 extends BaseFrame implements Runnable{


	Thread myTh;
	int intCount;
	ImageIcon[] icons = new ImageIcon[5];

	//JLabel lblScreen = new JLabel(new ImageIcon("D:\\pleiades-e4.5-java-jre_20160312\\pleiades\\workspace\\L0413Ex\\img0.gif"));
	//▼▼自身の環境においてgifファイルが格納されている場所に書き換える
	JLabel lblScreen = new JLabel(new ImageIcon("./L0413Ex_pr8/img0.gif"));


	JButton btnStart = new JButton("Start");

	public static void main(String args [ ]) {
		 L0413Ex_pr8 myAppli = new L0413Ex_pr8("Simple Animation");
		 myAppli.setSize(200,200);
		 myAppli.setVisible(true);
	}

	public L0413Ex_pr8(String title) {
		super(title);

		for (int i = 0; i < 5; i++){
			icons[i] = new ImageIcon("./L0413Ex_pr8/img" + i + ".gif");
			//▼▼自身の環境においてgifファイルが格納されている場所に書き換える

		}

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (btnStart.getText().equals("Start")) {
					btnStart.setText("Stop");
					startThread();
				}else{
					//▼▼「スレッドを停止」の記述
					myTh = null ; 					

					btnStart.setText("Start");
				}
			}
		});

		getContentPane().add(btnStart, BorderLayout.NORTH);
		getContentPane().add(lblScreen, BorderLayout.CENTER);
	}

	public void startThread() {
		myTh = new Thread(this);
		myTh.start();
	}

	public void run() {
		Thread crntThread = Thread.currentThread();
		//▼▼「スレッドが有効である」の条件をwhileの条件として指定
		while( myTh == crntThread) {
			if(intCount < 4) {
				intCount++;
			}else{
				intCount = 0;
			}

			lblScreen.setIcon(icons[intCount]);
			try{
				//▼▼スレッドを0.5秒間停止
				Thread.sleep(500); // 0.01 seconds = 10 milliseconds
				

			}catch(InterruptedException ie) {}
		}
	}

	public void stop() {
		myTh = null;
	}

}
