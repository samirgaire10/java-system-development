import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

//▼▼ JFrameを親とする
class MyEditor extends JFrame{

    //▼ コンポーネント類の宣言
    JTextArea text;
    JScrollPane scroll;
    JMenuBar bar;
    JMenu menu_file, menu_edit;
    JMenuItem mi_new, mi_open, mi_save, mi_exit, mi_copy, mi_paste, mi_cut , mi_select;

    //▼ MyEditorクラスのコンストラクタを定義
    MyEditor(String title) {

        //▼▼ 親クラス（JFrame）のコンストラクタを呼び出しJFrameインスタンス作成
        super(title) ; 

        //▼ 生成したJFrameインスタンスにレイアウトをセット
        this.setLayout(new BorderLayout());

        //▼ 入力用テキストエリアを作成
        text = new JTextArea();

        //▼ 折り返しをサポート
        text.setLineWrap(true);

        //▼ スクロールペインを作成してテキストエリアをセット
        scroll = new JScrollPane(text);

        //▼ フレームにScrollPaneをセット
        add(scroll, BorderLayout.CENTER);


    //▼ メニューの作成と組み込み-----

        //▼▼ メニューバーの作成、資料 MyEditor【テキストエディタ１の説明】 .pdf ２の①参照
        bar = new JMenuBar(); //◀メニューバー「bar」の生成
        
        // this.setJMenuBar(bar); //◀ 自身のオブジェクト(フレーム)にメニューバー「bar」をセット        

        //▼ 境界の設定
        bar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        //▼ File Menuの作成と組み込み----------------
        menu_file = new JMenu("File");

        //▼▼ 「File」メニューにニーモニックキー「F」を設定、資料２の④参照
        menu_file.setMnemonic('F'); //◀1 文字が設定可の為、シングルクォーテーションを使用

        //▼ New
        mi_new = new JMenuItem("New", new ImageIcon("new.gif"));
        menu_file.add(mi_new);

        //▼ Open
        mi_open = new JMenuItem("Open", new ImageIcon("open.gif"));
        menu_file.add(mi_open);

        //▼ Save
        mi_save = new JMenuItem("Save", new ImageIcon("save.gif"));
        menu_file.add(mi_save);

        //▼ セパレータを挟む
        menu_file.addSeparator();

        //▼ Exit
        mi_exit = new JMenuItem("Exit", new ImageIcon("exit.gif"));
        menu_file.add(mi_exit);

        //▼ メニューバーに追加
        bar.add(menu_file);

        //▼ Edit Menuの作成と組み込み----------------
        menu_edit = new JMenu("Edit");
        //▼▼ 「Edit」メニューにニーモニックキー「E」を設定、資料２の④参照
        menu_file . setMnemonic('E'); //◀1 文字が設定可の為、シングルクォーテーションを使用        

        //▼ Copy
        mi_copy = new JMenuItem("Copy", new ImageIcon("copy.gif"));
        menu_edit.add(mi_copy);

        //▼ Paste
        mi_paste = new JMenuItem("Paste", new ImageIcon("paste.gif"));
        menu_edit.add(mi_paste);

        //▼ Cut
        mi_cut = new JMenuItem("Cut", new ImageIcon("cut.gif"));
        menu_edit.add(mi_cut);


        mi_select = new JMenuItem("Select", new ImageIcon("select.gif"));
        menu_edit.add(mi_select);
        

        //▼ メニューバーに「Edit」メニュー追加
        bar.add(menu_edit);

        //▼▼ メニューバーをフレームにセット、資料２の①参照
        this.setJMenuBar(bar);           


    //▲ メニューの作成と組み込みここまで-----


        //▼ フレーム（ウィンドウ）が閉じる際の処理を定義
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //▼ フレームのサイズを定義して表示
        this.setSize(500, 300);
        this.setVisible(true);
    }

    //▼ mainメソッド（MyEditorを起動）
    public static void main(String s[]) {
        //▼▼ MyEditorクラスの新規インスタンスを作成
new MyEditor("MyEditor");
    }
}