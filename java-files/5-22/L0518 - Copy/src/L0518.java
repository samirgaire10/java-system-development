// 「Javaによるデータベース操作アプリケーション」
// 「ACCESSデータベースへの更新処理」

//▼ 「import」前期使用テキストP.110参照
//▼ 「java.awt」パッケージは、swingより前に登場したGUIライブラリ
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//▼ 「javax.swing」パッケージは、クラス名の最初に「J」が付いている
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//▼ BaseFrameクラスを継承（拡張）し、L0518クラスを作成
public class L0518 extends BaseFrame {

  //▼ コネクション（接続）情報を収められるmyConを用意
  Connection myCon = null;
  //▼ ステートメント（SQL文）情報を収められるmyStを用意
  Statement mySt = null;

  //▼ 各種GUIコンポーネントを「new」、「コンストラクタ」を使って生成
  JRadioButton rdoNew = new JRadioButton("新規追加", true);
  JRadioButton rdoEdit = new JRadioButton("編集");
  JRadioButton rdoDelete = new JRadioButton("削除");

  JTextField txtID = new JTextField();
  JTextField txtNAME = new JTextField();
  JTextField txtAGE = new JTextField();
  JTextField txtTEL = new JTextField();

  JLabel lblID = new JLabel("ID");
  JLabel lblNAME = new JLabel("NAME");
  JLabel lblAGE = new JLabel("AGE");
  JLabel lblTEL = new JLabel("TEL");

  JButton btnAction = new JButton("実行");

  JPanel myPanel1 = new JPanel();
  JPanel myPanel2 = new JPanel();

  ButtonGroup bg = new ButtonGroup();

  //▼ mainメソッド（Javaアプリケーション実行時最初に呼ばれるメソッド）の定義
  //▼ mainメソッドの説明は、前期使用テキストP.40
  public static void main(String args [ ]) {
    L0518 myAppli = new L0518("Update Record");
    myAppli.setSize(250,170);
    myAppli.setVisible(true);
  }

  //▼ クラスと同名のメソッド＝コンストラクタ
  //▼ オブジェクト（インスタンス）生成の為に使用されるメソッド
  //▼ コンストラクタの説明は、前期使用テキストP.77より
  public L0518(String title) {
    //▼ 親（この場合BaseFrame）のコンストラクタを呼び出しフレームを生成
    //▼ superの説明は、前期使用テキストP.153より
    super(title);

    //▼ 列IDに対応するtxtIDへの入力は不可とする
    txtID.setEnabled(false);

    //▼ rdoNew（新規追加）ラジオボタンがチェックされた際のイベント処理
    rdoNew.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        txtID.setEnabled(false);
        txtNAME.setEnabled(true);
        txtAGE.setEnabled(true);
        txtTEL.setEnabled(true);
      }
    });

    //▼ rdoEdit（編集）ラジオボタンがチェックされた際のイベント処理
    rdoEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        txtID.setEnabled(true);
        txtNAME.setEnabled(true);
        txtAGE.setEnabled(true);
        txtTEL.setEnabled(true);
      }
    });

    //▼ rdoDelete（削除）ラジオボタンがチェックされた際のイベント処理
    rdoDelete.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent ae) {
       txtID.setEnabled(true);
       txtNAME.setEnabled(false);
       txtAGE.setEnabled(false);
       txtTEL.setEnabled(false);
     }
    });

    //▼ rdoAction（実行）ボタンがチェックされた際のイベント処理
    btnAction.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        try {
          //▼▼ 前回同様文字列url,user,passを作成（①）
          String url = "jdbc:ucanaccess://src/sample.accdb";
          String user = "";
          String pass = "";

          //▼▼ 前回同様、接続を確立（②）
            myCon = DriverManager.getConnection(url, user, pass);

	      if (rdoNew.isSelected()) {
	          //▼▼「新規追加」に対応するinsert文を生成（⑤）
		      String strSQL = "INSERT INTO PERSONAL_DATA (NAME, AGE, TEL) VALUES('"
		    		  		+ txtNAME.getText() + "',"
		    		  		+ Integer.parseInt(txtAGE.getText())+ ",'"
		    		  		+ txtTEL.getText() + "')";
	          showMyMessage(doUpdate(strSQL)  + "件のレコードが追加されました");
	      }

	      if (rdoEdit.isSelected()) {
	          //▼▼「編集」に対応するupdate文を生成（⑥）
		    	String strSQL ="UPDATE PERSONAL_DATA SET NAME ='" + txtNAME.getText()
    					+ "',AGE = " + Integer.parseInt(txtAGE.getText())
    					+ ",TEL = '" + txtTEL.getText() + "'"
    					+  " WHERE ID = " + Integer.parseInt(txtID.getText());
	    	  if (doUpdate(strSQL) <= 0)  {
	    	      showMyMessage("該当するレコードはありませんでした");
	    	  }else{
            	  showMyMessage("レコードが更新されました");
	    	  }
	      }

	      if (rdoDelete.isSelected()) {
	          //▼▼「削除」に対応するupdate文を生成（⑦）
	    	  String strSQL = "DELETE FROM PERSONAL_DATA WHERE ID = "
			  			+ Integer.parseInt(txtID.getText());
	    	  if (doUpdate(strSQL) <= 0)  {
	    	      showMyMessage("該当するレコードはありませんでした");
	    	  }else{
	    	      showMyMessage("レコードが削除されました");
	    	  }
	      }
        //▼ 例外（エラー）処理　エラー内容を表示する
        }catch (Exception ex) {
            showMyMessage("エラー発生： " + ex);
            ex.printStackTrace();
        //▼ finallyブロック：例外（エラー）処理が発生していてもしていなくても実施する構文
        }finally{
            try {
                if (mySt != null) {
                    mySt.close();
                }
                if (myCon != null){
                    myCon.close();
                }
            }catch(SQLException sx) {
        		showMyMessage("エラー発生" + sx);
            }
        }
      }
    });

    myPanel1.setLayout(new GridLayout(1,3));
    myPanel2.setLayout(new GridLayout(4,2));

    myPanel1.add(rdoNew);
    myPanel1.add(rdoEdit);
    myPanel1.add(rdoDelete);
    bg.add(rdoNew);
    bg.add(rdoEdit);
    bg.add(rdoDelete);

    myPanel2.add(lblID);
    myPanel2.add(txtID);
    myPanel2.add(lblNAME);
    myPanel2.add(txtNAME);
    myPanel2.add(lblAGE);
    myPanel2.add(txtAGE);
    myPanel2.add(lblTEL);
    myPanel2.add(txtTEL);

    this.add(myPanel1, BorderLayout.NORTH);
    this.add(myPanel2, BorderLayout.CENTER);
    this.add(btnAction, BorderLayout.SOUTH);
  }

  void showMyMessage(String strMessage) {
    JOptionPane.showMessageDialog(this, strMessage);
  }

  //▼ 実行ボタンがクリックされた際に呼び出される更新処理の中心となるメソッド
  int doUpdate(String sql) {
      int intCount;
      try {

          //▼▼ 接続先を意識したステートメントを作成（③）

    	  mySt = myCon.createStatement() ;
          //▼▼ 作ったSQL文を実行（④）
          //▼▼ Statementオブジェクト.executeUpdate(SQLステートメント)
    	  intCount = mySt.executeUpdate(sql);

      }catch(Exception ex) {
          showMyMessage("doUpdateでエラー発生：" + ex);
          intCount = 0;
      }
      //▼ 更新されたレコード数が戻り値として戻される
      return intCount;
  }
}
