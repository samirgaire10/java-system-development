// 「オブジェクト指向プログラミング」
// 「スレッドを理解する」「マルチスレッドを利用する」

//▼ mainメソッドのあるクラス
public class L0412Ex{

	public L0412Ex(){
		//▼ １つ目のスレッドを生成
		MyThread myTh = new MyThread();
		//▼▼ １つ目のスレッドをスタート（資料P.1 Ⅰの③参照）
		myTh.start();
		//▼ ２つ目のスレッドを生成
		MyThread2 myTh2 = new MyThread2();
		//▼▼ ２つ目のスレッドをスタート（資料P.1 Ⅰの③参照）
		myTh2.start();
	}

	public static void main(String args[]) {
		//▼ Javaアプリケーションとして上記のコンストラクタを実行する
		new L0412Ex();
	}
}

//▼▼ Threadクラスを継承し新しいクラス（１つ目）を定義する
class MyThread extends Thread {
	//▼ スレッドを開始（start()メソッド）した際の処理内容をオーバーライド
	public void run() {
		for(int i = 0; i <=10; i++) {
			System.out.println("1st Thread is running !: " + i);
			try {
				//▼▼ スレッドを１秒間休止（資料P.2 Ⅲ参照）
				Thread.sleep(1000);
			//▼ スレッド操作を行う際に発生する例外「InterruptedException」
			} catch (InterruptedException ie) {}
		}
	}
}

//▼▼ Threadクラスを継承し新しいクラス（２つ目）を定義する
class MyThread2 extends Thread {
	public void run() {
		for(int i = 0; i <=10; i++) {
			System.out.println("2nd Thread is also running !: " + i);
			try {
				//▼▼ スレッドを１秒間休止（資料P.2 Ⅲ参照）
				Thread.sleep(1000);
			} catch (InterruptedException ie) {}
		}
	}
}