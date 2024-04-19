package stub;

/**
 * クラス PuchSwitch の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PushSwitch extends JPanel implements Resettable
{
    private static final int BUTTON_WIDTH = 50;
    private static final int BUTTON_HEIGHT = 35;
    private static final int SW_WIDTH = 100;
    private static final int SW_HEIGHT = 50;
    private static final int TEXT_HEIGHT = 20;
    private static final int TEXT_OFFSET = BUTTON_WIDTH;
    private static final Icon releasedIcon = new ImageIcon( "./stub/PushSWT_H.png" );
    private static final Icon pushedIcon = new ImageIcon( "./stub/PushSWT_L.png" );

    private JToggleButton sw;
    private int ch;
    private String caption;
    private String hotKey;

    PushSwitchListener pswListener;

    public PushSwitch( int ch , int x , int y ) {
        this.ch = ch;
        setBounds( x, y, SW_WIDTH, SW_HEIGHT );
        setLayout( null );
        setBackground( Color.WHITE );
        sw = new JToggleButton();
        sw.setIcon( releasedIcon );
        sw.setSelectedIcon( pushedIcon );
        if ( ch == 1 ) {
            caption = "SW1";
            hotKey = "(J)";
        } else {
            caption = "SW2";
            hotKey = "(U)";
        }
        sw.setBounds( 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT );
        add( sw );
        reset();
    }

    @Override
    public void reset() {
        sw.setSelected( false );
    }

    public boolean addListener( PushSwitchListener pswListener ) { 
        if ( pswListener != null ) {
            this.pswListener = pswListener;
            sw.addActionListener( new ActionListener(){
                    @Override
                    public void actionPerformed( ActionEvent e ) {
                        pswListener.pushSwitchPerformed();

                    }
                }
            );
            return ( true );
        } else {
            return ( false );
        }
    }

    public boolean isHigh() {
        return ( !sw.isSelected() );
    }

    public boolean isLow() {
        return ( sw.isSelected() );
    }

    public boolean isPush() {
        return ( sw.isSelected() );
    }

    public void altSelected() {
        sw.setSelected(!sw.isSelected());
        if ( pswListener != null ) {
            pswListener.pushSwitchPerformed();
        }
    }

    @Override
    public void paint( Graphics g ) {
        super.paint( g );
        g.setColor( Color.BLACK );
        g.setFont( new Font( "Arial", Font.BOLD, 20) );
        g.drawString( caption , TEXT_OFFSET , TEXT_HEIGHT );
        g.setFont( new Font( "Arial", Font.BOLD, 16) );
        g.drawString( hotKey , TEXT_OFFSET+10 , TEXT_HEIGHT+16 );

    }
}
