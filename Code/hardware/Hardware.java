package hardware;

/**
 * レーザー光描画装置全体を示すクラス
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hardware extends JFrame implements Resettable 
{
    private static Hardware hardware = new Hardware();

    public RaspIfBoard raspIfBoard = RaspIfBoard.getInstance();
    public DrawUnit drawUnit = DrawUnit.getInstance();
    public Manipulator manipulator = Manipulator.getInstance();

    private Hardware() {
        reset();
        frameSetting();
        Container contentPane = getContentPane();
        contentPane.add( new JPanel() {
                @Override
                public void paint( Graphics g ) {
                    g.setFont( new Font( "Dialog" , Font.BOLD , 28 ) );
                    g.setColor( Color.WHITE );
                    g.drawString( "実機で動作させています" ,40, 100 );
                }
            }
        );
        setVisible( true );
        repaint();

    }

    public static Hardware getInstance() {
        return ( hardware );
    }

    @Override
    public void reset() {
        raspIfBoard.reset();
        drawUnit.reset();
        manipulator.reset();
    }

    private void frameSetting() {
        setTitle( "レーザー光描画装置 Model DXY-150" );
        setBounds( 400,100,400,300 );
        setBackground( Color.BLUE );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        // setLayout(null);
    }
}
