
//「オブジェクト指向プログラミング」
//「スレッドを理解する」「マルチスレッドを利用する」
//「ストップウォッチ」
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//▼ Runnableインターフェースを実装しクラスを定義
public class L0413 extends BaseFrame implements Runnable
{
	//▼ スレッドオブジェクトを格納するため「myTh」を用意
	Thread myTh;
	String strLblMes1 = "Time: ";
	String strLblMes2 = "Lap : ";
	int intCount = 0;

	//▼ GUIを生成するための各種部品づくり
	JButton btnStart = new JButton("Start");
	JButton btnLap = new JButton("Lap");
	JButton btnClear = new JButton("Clear");
	JLabel lblTime = new JLabel(strLblMes1 + "0.00");
	JLabel lblLap = new JLabel(strLblMes2 + "0.00");
	JPanel pnl1 = new JPanel(new GridLayout(1,3));
	JPanel pnl2 = new JPanel(new GridLayout(2,1));

	//▼ Javaアプリケーションとして実行するためここから処理開始
	public static void main(String args [ ]) {
		L0413 myAppli = new L0413("Simple Stop Watch");
		myAppli.setSize(200,150);
		myAppli.setVisible(true);
	}

	//▼ クラスと同じ名前ですからコンストラクタ（オブジェクト生成の際、利用）
	public L0413(String title) {
		//▼▼ 親のコンストラクタ（BaseFrame()）を呼ぶ
		super (title) ;

		//▼ Startボタン（Stopボタン）のイベント処理を定義
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (btnStart.getText().equals("Start")) {
					btnStart.setText("Stop");
					startThread();
				}else{
					//▼▼ スレッドを停止
					myTh = null ; 					

					//▼ ボタンの表記を「Stop」から「Start」に
					btnStart.setText("Start");
				}
			}
		});

		//▼ Lapボタンのイベント処理を定義
		btnLap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (myTh != null) {					
					lblLap.setText(strLblMes2 + String.format("%.2f", (intCount/100.0)));
				}
			}
		});

		//▼ Clearボタンのイベント処理を定義
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				lblTime.setText(strLblMes1 + "0,00");
				lblLap.setText(strLblMes2 + "0.00");
				//▼▼ カウンタをリセット
				intCount = 0 ;

			}
		});

		//▼ GUIを生成するための各種部品を必要な場所に組み込む
		pnl1.add(btnStart);
		pnl1.add(btnLap);
		pnl1.add(btnClear);
		pnl2.add(lblTime);
		pnl2.add(lblLap);
		getContentPane().add(pnl1, BorderLayout.NORTH);
		getContentPane().add(pnl2, BorderLayout.CENTER);
	}

	//▼ スレッドを生成し、開始させるメソッドを定義
	public void startThread() {
		myTh = new Thread(this);
		myTh.start();
	}

	//▼▼ スレッドを開始（start()メソッド）した際の処理内容を書くメソッドをオーバーライド
	public void run() {
		//▼ 現在稼働中のスレッドを取得
		Thread crntThread = Thread.currentThread();
		//▼ 現在稼働中のスレッドが、既に動作中のスレッドと同じ
		//▼ 有効なスレッドと判断
		while(myTh == crntThread) {
			//▼ 少なくとも0.01秒より大きい間隔でカウンタ（整数値）を作成
			intCount++;
			lblTime.setText(strLblMes1 + intCount);
			//▼ 約0.01秒単位で画面出力
			lblTime.setText(strLblMes1 + String.format("%.2f", (intCount/100.0)));
			try {
				//▼▼ スレッドを0.01秒間一次的に停止
					Thread.sleep(10); // 0.01 seconds = 10 milliseconds
			} catch (InterruptedException ie) {}
		}
	}

	//▼ stopメソッドのオーバーライド
	public void stop() {
		myTh = null;
	}
}