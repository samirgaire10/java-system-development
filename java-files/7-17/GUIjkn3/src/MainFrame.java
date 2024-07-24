
//▼修正箇所が６箇所あります
import java.awt.Image;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//▼アノテーションを使っているので引数に反応して警告を抑制してくれます
@SuppressWarnings("serial")
public class MainFrame extends JFrame {


  private JLabel pcimageRock ;
  // ▼グーボタン
  private JButton buttonRock;

  // ▼チョキボタン
  private JButton buttonScissors;

  // ▼パーボタン
  private JButton buttonPaper;

  // ▼▼ラベルへの初期メッセージ 資料を見て表示する文字列を入れて下さい
  private JLabel labelSubTitle = new JLabel("ボタンをクリクし、自分の手を決める");
  private JLabel labelResult = new JLabel("  ");

  // ▼コンストラクタ
  public MainFrame() {
    // ▼親（JFrame）のコンストラクタを呼び出す
    super("じゃんけん");
    // ▼▼表示する場所（X座標：100、Y座標：50）を指定する
    super.setBounds(100, 50, 800, 300);
    // ▼終了（閉じるボタンの）処理
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // ▼パネル（透明シート）の作成
    JPanel panel = new JPanel();


// -------------------------
// pc 

// -----------------------

    
    // ▼▼人間（ユーザ）側のボタンで使用するグー画像を読み込み、イメージアイコン型のimageRockにセット
    ImageIcon imageRock = new ImageIcon("M-j_gu01.png");
    // ▼imageRockのスケーリング（拡大縮小）とその際の優先項目を指定しimageRockを再設定
    imageRock.setImage(imageRock.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
    // ▼人間（ユーザ）側のグーボタンをimageRockを使って作成
    this.buttonRock = new JButton(imageRock);

    // ▼▼人間（ユーザ）側のボタンで使用するチョキ画像を読み込み、イメージアイコン型のimageScissorsにセット
    ImageIcon imageScissors = new ImageIcon("M-j_ch01.png");
    // ▼imageScissorsのスケーリング（拡大縮小）とその際の優先項目を指定しimageScissorsを再設定
    imageScissors.setImage(imageScissors.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
    // ▼人間（ユーザ）側のチョキボタンをimageScissorsを使って作成
    this.buttonScissors = new JButton(imageScissors);

    // ▼▼人間（ユーザ）側のボタンで使用するパー画像を読み込み、イメージアイコン型のimagePaperにセット
    ImageIcon imagePaper = new ImageIcon("M-j_pa01.png");
    // ▼imagePaperのスケーリング（拡大縮小）とその際の優先項目を指定しimagePaperを再設定
    imagePaper.setImage(imagePaper.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
    // ▼人間（ユーザ）側のパーボタンをimagePaperを使って作成
    this.buttonPaper = new JButton(imagePaper);

    // ▼親クラス（JFrame）のデフォルトレイアウトはBorderLayout（東西南北中央）
    // ▼第一引数に"North"とあるため、labelSubTitleはボダーレイアウトにおける上部に配置
    super.add("North", labelSubTitle);
    // labelResult
    super.add("South",labelResult);

    // leabel font setting work
    // labelSubTitle.setFont(new Font("Serif",Font.PLAIN,30));
    labelSubTitle.setFont(new Font("Serif", Font.PLAIN, 30));
    labelResult.setFont(new Font("Serif", Font.PLAIN, 30));

    // ▼パネル（透明シート）のデフォルトレイアウトはFlowLayout（左上から右下に向かって流し込み）
    // ▼▼パネルの左からグーボタン、チョキボタン、パーボタンを格納
    panel.add(buttonRock);
    panel.add(buttonScissors);
    panel.add(buttonPaper);

    // ▼第一引数に"Center"とあるため、panelはボダーレイアウトにおける中央に配置
    super.add("Center", panel);
    // ▼設定したフレームを見えるように
    super.setVisible(true);

  }

  // ▼促しのメッセージや結果を表示するラベルlabelSubTitleに文字列を流し込むメソッド
  public void setrisult(String subTitle) {
    // labelSubTitle.setText(subTitle);
    labelResult.setText(subTitle);

  }

public JLabel getLabelpcimageRock()
{
  return pcimageRock ;
}

  // ▼グーボタンオブジェクトを返すメソッド
  public JButton getButtonRock() {
    return buttonRock;
  }

  // ▼チョキボタンオブジェクトを返すメソッド
  public JButton getButtonScissors() {
    return buttonScissors;
  }

  // ▼パーボタンオブジェクトを返すメソッド
  public JButton getButtonPaper() {
    return buttonPaper;
  }

}