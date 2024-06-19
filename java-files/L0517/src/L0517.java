import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//▼ 「javax.swing」パッケージは、クラス名の最初に「J」が付いている
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//▼ BaseFrameクラスを継承（拡張）し、L0517クラスを作成
public class L0517 extends BaseFrame {

    Connection myCon = null;
    JButton btnDraw = new JButton("検　索");
    JTextArea tarScreen = new JTextArea();
    JTextField txtValue = new JTextField();
    JLabel lbl1 = new JLabel("ACCESS_DB「sample.accdb」の検索");
    JLabel lbl2 = new JLabel("　PERSONAL_DATA( ID / NAME / AGE / TEL)表　where句から入力↓");
    JPanel panel = new JPanel();;

    //▼ mainメソッド（Javaアプリケーション実行時最初に呼ばれるメソッド）の定義
    //▼ mainメソッドの説明は、前期使用テキストP.40
    public static void main(String args [ ]) {
      L0517 myAppli = new L0517("Search records");
      myAppli.setSize(400,320);
      myAppli.setVisible(true);
    }

    //▼ クラスと同名のメソッド＝コンストラクタ
    //▼ オブジェクト（インスタンス）生成の為に使用されるメソッド
    //▼ コンストラクタの説明は、前期使用テキストP.77より
    public L0517(String title) {
      //▼ 親（この場合BaseFrame）のコンストラクタを呼び出しフレームを生成
      //▼ superの説明は、前期使用テキストP.153より
      super(title);
      //▼ テキストエリアオブジェクト「tarScreen」の折り返しを可能とする
      tarScreen.setLineWrap(true);

      //▼ btnDrawがクリックされた時のイベント処理関連について定義
      btnDraw.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          //▼ テキストエリアオブジェクト「tarScreen」の初期化
          tarScreen.setText("");
		  try {
              String url = "jdbc:ucanaccess://src/sample.accdb";
              String user = "";
              String pass = "";
              //▼▼データベースへの接続を確立 ①
              myCon = DriverManager.getConnection(url,user,pass);
              //▼▼接続先に関連付けたSQLステートメントを作成 ②
              Statement mySt = myCon.createStatement() ;
              //▼▼SQLステートメント（検索）を実行 ③
              ResultSet myRs = mySt.executeQuery("SELECT * FROM PERSONAL_DATA "

              + txtValue.getText());
              //▼実行結果をレコード移動しながら存在するか評価する
              while (myRs.next()){
                  // ▼検索でヒットしたid列値を取得
                  long id = myRs.getLong("ID") ;
                  //▼検索でヒットしたname列値を取得
                  String name = myRs.getString("NAME") ;
                  //▼検索でヒットしたage列値を取得
                  int age = myRs.getInt("AGE") ;
                  //▼検索でヒットしたtel列値を取得
                  String tel = myRs.getString("TEL") ;
                  //▼取得した各列値をtarScreenへ追記
                  tarScreen.append(id + " / " + name + " / " + age + " / " + tel + "\n");
                  //▼▼Connection、Statement、ResultSetのあとかたづけ（メモリの開放）⑤⑥⑦

              }
              myCon.close();
              myRs.close(); 
              mySt.close(); 

          //▼例外（エラー）が発生した場合、返されたエラーメッセージを表示
		  } catch (Exception ex) {
              tarScreen.setText("エラー発生：" + ex);
		  }
        }
      });
      //▼パネルに3行×1行のグリッドレイアウトをセット
      panel.setLayout(new GridLayout(3, 1, 5, 5));
      //▼パネル1行目にlbl1をセット
      panel.add(lbl1);
      //▼パネル2行目にlbl2をセット
      panel.add(lbl2);
      //▼パネル3行目にtxtValueをセット
      panel.add(txtValue);
      //▼このコンストラクタが作成する自身のオブジェクトの上部にパネル「panel」をセット
      this.add(panel, BorderLayout.NORTH);
      //▼このコンストラクタが作成する自身のオブジェクトの下部にボタン「btnDraw」をセット
      this.add(btnDraw, BorderLayout.SOUTH);
      //▼このコンストラクタが作成する自身のオブジェクトの中央にテキストエリア「tarScreen」をセット
      this.add(tarScreen, BorderLayout.CENTER);
  }
}