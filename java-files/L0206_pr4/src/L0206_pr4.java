//▼ 「import」前期使用テキストP.110参照
//▼ 「javax.awt」パッケージは、swingより前に登場したGUIライブラリ
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

// Import the “JTextField” class from the “javax.swing” package for use with text boxes

//▼ 「javax.swing」パッケージは、クラス名の最初に「J」が付いている
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTextField;


//▼ BaseFrameクラスを継承（拡張）し、L0206_pr4クラスを作成
public class L0206_pr4 extends BaseFrame {
    JButton btnDraw;
    JLabel lblScreen;
    JTextField txtHost ;

    //▼ mainメソッド（Javaアプリケーション実行時最初に呼ばれるメソッド）の定義
    //▼ mainメソッドの説明は、前期使用テキストP.40
    public static void main(String args [ ]) {
        L0206_pr4 myAppli = new L0206_pr4("Practice4 Get Host's Information");
        myAppli.setSize(450,150);
        myAppli.setVisible(true);
    }
    //▼ クラスと同名のメソッド＝コンストラクタ
    //▼ オブジェクト（インスタンス）生成の為に使用されるメソッド
    //▼ コンストラクタの説明は、前期使用テキストP.77より
    public L0206_pr4(String title) {
        //▼ 親（この場合BaseFrame）のコンストラクタを呼び出しフレームを生成
        //▼ superの説明は、前期使用テキストP.153より
        super(title);
        //▼ ボタン、ラベルコンポーネントを作成
        btnDraw = new JButton("Get");
        lblScreen = new JLabel();
        txtHost = new JTextField() ; 
        //▼ lblScreenへの表示は中央揃え
        lblScreen.setHorizontalAlignment(JLabel.CENTER);
        //▼ btnDrawがクリックされた時のイベント処理関連について定義
        btnDraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
	                //▼getLocalHostメソッドを呼び出しInetAddressクラスのインスタンスを生成
	                //▼getLocalHostメソッドを呼び出しローカルマシンのIPアドレスを取得後、inaHostへ納める
	                //▼L0206_pr4【ネットワークプログラム1の説明】.pdxのP.2を参照
                    // InetAddress inaHost = InetAddress.getLocalHost();
                    InetAddress inaHost = InetAddress.getByName(txtHost.getText());


	                //▼▼取得したローカルホスト情報「inaHost」を基にホスト名、IPアドレスを取得し、表示
 	                lblScreen.setText("マシン名:" + inaHost.getHostName() + "  /  IPアドレス:" + inaHost.getHostAddress());

                    //▼ホスト情報が見つからなかった場合の例外を()に記述
                    //▼L0206_pr4【ネットワークプログラム1の説明】.pdxのP.2を参照
                }catch(SecurityException se) {
			        lblScreen.setText("セキュリティ例外です");

                    //▼セキュリティ規制により操作がキャンセルされた場合の例外を()に記述
                    //▼L0206_pr4【ネットワークプログラム1の説明】.pdxのP.2を参照
                }catch(UnknownHostException ue ) {
				    lblScreen.setText("ホスト情報が見つかりません");
                }
            }
        });
        //▼このコンストラクタが作成する自身のオブジェクトの上部にボタン「btnDraw」をセット
        this.add(btnDraw,BorderLayout.NORTH);
        //▼このコンストラクタが作成する自身のオブジェクトの中央部にラベル「lblScreen」をセット
        this.add(lblScreen, BorderLayout.CENTER);
        this.add(txtHost, BorderLayout.SOUTH);
    }
}
