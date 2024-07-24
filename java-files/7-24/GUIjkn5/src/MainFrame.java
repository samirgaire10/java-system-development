import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//▼アノテーションを使っているので引数に反応して警告を抑制してくれます
@SuppressWarnings("serial")
//▼JFrameを親クラスとしてMainFrameクラスを定義
public class MainFrame extends JFrame{

  //▼グーボタン
  private JButton buttonRock;
  //▼チョキボタン
  private JButton buttonScissors;
  //▼パーボタン
  private JButton buttonPaper;

  //▼ラベルへの初期メッセージ
  private JLabel labelSubTitle = new JLabel("↑↑ボタンをクリックし、自分の手を決めて↑↑");
  private JLabel labelResult = new JLabel("　　　　最初はグー、ジャンケン～　");

  //▼ラベルのフォント設定を作っておく
  private Font f = new Font("Dialog", Font.BOLD, 25);

  //▼コンピュータ用の画像を入れるラベルを作っておく
  private JLabel labelRockPc;
  private JLabel labelScissorsPc;
  private JLabel labelPaperPc;
  private JLabel labelPc;
  private JLabel labelUserChoice;
  private JLabel labelPcChoice;

  //▼コンピュータ用の画像ファイルから収めるイメージアイコンを用意しておく
  ImageIcon imageRockPc;
  ImageIcon imageScissorsPc;
  ImageIcon imagePaperPc;
  ImageIcon imageRock;
  ImageIcon imageScissors;
  ImageIcon imagePaper;

  
  //▼コンストラクタ
  public MainFrame(){
    //▼親（JFrame）のコンストラクタを呼び出す
    super("じゃんけん（改良版）");
    //▼表示する場所を指定する
    super.setBounds(50,50,600,800);
    //▼終了（閉じるボタンの）処理
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //▼パネルの作成
    JPanel panel = new JPanel();
    JPanel panelUser = new JPanel();
    panelUser.setLayout(new BorderLayout());
    JPanel panelField = new JPanel();
    //▼パネルpanelFieldをボーダーレイアウトに設定
    panelField.setLayout(new BorderLayout());
    JPanel panelP = new JPanel();

    //▼人間（ユーザ）側のボタンで使用するグー画像を読み込み、イメージアイコン型のimageRockにセット
    imageRock = new ImageIcon("M-j_gu01.png");
    //▼imageRockのスケーリング（拡大縮小）とその際の優先項目を指定しimageRockを再設定
    imageRock.setImage(imageRock.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    //▼人間（ユーザ）側のグーボタンをimageRockを使って作成
    this.buttonRock = new JButton(imageRock);

    //▼人間（ユーザ）側のボタンで使用するチョキ画像を読み込み、イメージアイコン型のimageScissorsにセット
    imageScissors = new ImageIcon("M-j_ch01.png");
    //▼imageScissorsのスケーリング（拡大縮小）とその際の優先項目を指定しimageScissorsを再設定
    imageScissors.setImage(imageScissors.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    //▼人間（ユーザ）側のチョキボタンをimageScissorsを使って作成
    this.buttonScissors = new JButton(imageScissors);

    //▼人間（ユーザ）側のボタンで使用するパー画像を読み込み、イメージアイコン型のimagePaperにセット
    imagePaper = new ImageIcon("M-j_pa01.png");
    //▼imagePaperのスケーリング（拡大縮小）とその際の優先項目を指定しimagePaperを再設定
    imagePaper.setImage(imagePaper.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    //▼人間（ユーザ）側のパーボタンをimagePaperを使って作成
    this.buttonPaper = new JButton(imagePaper);

    this.labelUserChoice = new JLabel(imageRock);


    //▼コンピュータ側の画像ファイル（グー）をイメージアイコン型imageRockPcへ取り込む
    imageRockPc = new ImageIcon("M-j_gu02.png");
    //▼imageRockPcのスケーリング（拡大縮小）とその際の優先項目を指定しimageRockPcを再設定
    imageRockPc.setImage(imageRockPc.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    //▼コンピュータ側の画像ファイル（グー）のイメージアイコンをラベルlabelRockPcに格納
    this.labelRockPc = new JLabel(imageRockPc);

    //▼コンピュータ側の画像ファイル（チョキ）をイメージアイコン型imageScissorsPcへ取り込む
    imageScissorsPc = new ImageIcon("M-j_ch02.png");
    //▼imageScissorsPcのスケーリング（拡大縮小）とその際の優先項目を指定しimageScissorsPcを再設定
    imageScissorsPc.setImage(imageScissorsPc.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    //▼コンピュータ側の画像ファイル（チョキ）のイメージアイコンをラベルlabelScissorsPcに格納
    this.labelScissorsPc = new JLabel(imageScissorsPc);

    //▼コンピュータ側の画像ファイル（パー）をイメージアイコン型imagePaperPcへ取り込む
    imagePaperPc = new ImageIcon("M-j_pa02.png");
    //▼imagePaperPcのスケーリング（拡大縮小）とその際の優先項目を指定しimagePaperPcを再設定
    imagePaperPc.setImage(imagePaperPc.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    //▼コンピュータ側の画像ファイル（パー）のイメージアイコンをラベルlabelPaperPcに格納
    this.labelPaperPc = new JLabel(imagePaperPc);
    //▼最初はグー
    this.labelPcChoice = new JLabel(imageRockPc);

    //▼親クラス（JFrame）のデフォルトレイアウトはBorderLayout（東西南北中央）
    //▼第一引数に"Center"とあるため、labelResultはボダーレイアウトにおける中央に配置
    super.add("Center",labelResult);

    //▼▼ラベル（labelResult）の前景色を赤に


    //▼作っておいたラベルにフォントを設定
    labelResult.setFont(f);
    labelSubTitle.setFont(f);

    //▼パネル（透明シート）のデフォルトレイアウトはFlowLayout（左上から右下に向かって流し込み）
    //▼パネルの左からグーボタン、チョキボタン、パーボタンを格納
    panelP.add(labelRockPc);
    panelP.add(labelScissorsPc);
    panelP.add(labelPaperPc);

    //▼▼パネルpanelFieldの設定　3つのラベルを適切な場所に格納して下さい（3行）




    //▼▼パネル（panelField）の背景色をピンクに


    //▼パネルを縁取り、色をオレンジに
    //panelField.setBorder(new LineBorder(Color.ORANGE, 6, true));
   
    panel.add(buttonRock);
    panel.add(buttonScissors);
    panel.add(buttonPaper);
    panelUser.add("Center",panel);
    panelUser.add("South",labelSubTitle);

    super.add("North",panelP);
    super.add("Center", panelField);
    super.add("South", panelUser);

    //▼起動時のコンピュータ側の手の表示をグーとしてラベルにセット
    labelPc = labelRockPc;

    //▼設定したフレームを見えるように
    super.setVisible(true);

  }

  //▼促しのメッセージや結果を表示するラベルlabelSubTitleに文字列を流し込むメソッド
  public void setResult(String resultMess){
    labelResult.setText(resultMess);
  }

  //▼コンピュータ側の画像にグーをセットするメソッド（MainControllerで呼び出されます）
  public void setimageRockPc(){
    this.labelPcChoice.setIcon(imageRockPc);
  }
  //▼コンピュータ側の画像にチョキをセットするメソッド（MainControllerで呼び出されます）
  public void setimageScissorsPc(){
	this.labelPcChoice.setIcon(imageScissorsPc);
  }
  //▼コンピュータ側の画像にパーをセットするメソッド（MainControllerで呼び出されます）
  public void setimagePaperPc(){
	this.labelPcChoice.setIcon(imagePaperPc);
  }
  //▼ユーザ側の画像にグーをセットするメソッド（MainControllerで呼び出されます）
  public void setimageRockUser(){
	this.labelUserChoice.setIcon(imageRock);
  }
  //▼ユーザ側の画像にチョキをセットするメソッド（MainControllerで呼び出されます）
  public void setimageScissorsUser(){
	this.labelUserChoice.setIcon(imageScissors);
  }
  //▼ユーザ側の画像にパーをセットするメソッド（MainControllerで呼び出されます）
  public void setimagePaperUser(){
	this.labelUserChoice.setIcon(imagePaper);
  }

  //▼グーボタンオブジェクトを返すメソッド
  public JButton getButtonRock() {
    return buttonRock;
  }
  //▼チョキボタンオブジェクトを返すメソッド
  public JButton getButtonScissors() {
    return buttonScissors;
  }
  //▼パーボタンオブジェクトを返すメソッド
  public JButton getButtonPaper() {
    return buttonPaper;
  }

}