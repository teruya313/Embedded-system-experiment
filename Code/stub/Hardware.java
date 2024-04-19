package stub;

/**
 * クラス Hardware の注釈をここに書きます.
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
    public DrawField drawField = DrawField.getInstance();

    //    private Hardware() {
    public Hardware() {
        reset();
        frameSetting();
        Container contentPane = getContentPane();
        contentPane.add( raspIfBoard );
        contentPane.add( drawUnit );
        contentPane.add( manipulator );
        contentPane.add( drawField );

        setVisible( true );
        repaint();
        requestFocusInWindow();
        addKeyListener( new KL() );
        addFocusListener( new FocusListener() {
                @Override
                public void focusGained( FocusEvent e ) {
                }

                @Override
                public void focusLost( FocusEvent e ) {
                    Hardware.getInstance().requestFocusInWindow();
                }
            }
        );

    }

    public static Hardware getInstance() {
        return ( hardware );
    }

    @Override
    public void reset() {
        // raspIfBoard.reset();
        // drawUnit.reset();
        // manipulator.reset();
    }

    private void frameSetting() {
        setTitle( "レーザー光描画装置 Model DXY-150" );
        setBounds( 400,100,900,700 );
        setBackground( Color.BLUE );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLayout(null);
    }

    public void paint( Graphics g ) {
        super.paint( g );
    }

    public class KL implements KeyListener {
        @Override
        public void keyPressed( KeyEvent e ) {
            keyPressedPerformed( e );
        }

        @Override
        public void keyReleased( KeyEvent e ) {
        }

        @Override
        public void keyTyped( KeyEvent e ) {
        }

    }    

    public void keyPressedPerformed( KeyEvent e ) {
        switch ( e.getKeyCode() ) {
            case KeyEvent.VK_A :
            manipulator.potX.setValue( manipulator.potX.getValue()-1 );
            break;
            case KeyEvent.VK_D :
            manipulator.potX.setValue( manipulator.potX.getValue()+1 );
            break;
            case KeyEvent.VK_W :
            manipulator.potY.setValue( manipulator.potY.getValue()+1 );
            break;
            case KeyEvent.VK_S :
            manipulator.potY.setValue( manipulator.potY.getValue()-1 );
            break;
            case KeyEvent.VK_J :
            manipulator.sw1.altSelected();
            break;
            case KeyEvent.VK_U :
            manipulator.sw2.altSelected();
            break;
        }
    }
}
