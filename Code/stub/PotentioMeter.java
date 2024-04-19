package stub;

/**
 * クラス PotentioMater の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class PotentioMeter extends JPanel implements Resettable
{
    private static final int KNOB_SIZE = 60;
    private static final int SLIDER_WIDTH = 100;
    private static final int SLIDER_HEIGHT = 20;
    private static final int TEXT_WIDTH = 20;
    private static final int POT_WIDTH = SLIDER_WIDTH;
    private static final int POT_HEIGHT = KNOB_SIZE + 80;
    private static final int KNOB_CENTER_X = SLIDER_WIDTH/2;
    private static final int KNOB_CENTER_Y = SLIDER_WIDTH/2;
    private static final int SLIDER_OFFSET = KNOB_SIZE/2;
    private static final int TEXT_OFFSET = KNOB_CENTER_Y + 70;

    private Knob knob;
    private JSlider slider;
    private int ch;
    private String caption;
    private String[] hotKey = new String[2];
    private int[] hotKeyX = new int[2];
    private int[] hotKeyY = new int[2];

    private PotentioMeterListener pmtrListener;
    public PotentioMeter( int ch , int x, int y ) {
        this.ch = ch;
        setBounds( x, y, POT_WIDTH, POT_HEIGHT );
        setLayout( null );
        setBackground( Color.WHITE );
        slider = new JSlider( 0, 255 ); //min=0 , max=255
        slider.setBackground( Color.WHITE );
        if ( ch == 0 ) {
            caption = "X";
            hotKey = new String[]{ "(A)" , "(D)" };
            hotKeyX = new int[]{ 0 , SLIDER_WIDTH-22 };
            hotKeyY[0] = hotKeyY[1] = KNOB_CENTER_Y+SLIDER_OFFSET+SLIDER_HEIGHT-20;
            slider.setOrientation( JSlider.HORIZONTAL );
            slider.setInverted( false );
            slider.setBounds( 0, KNOB_CENTER_Y+SLIDER_OFFSET, SLIDER_WIDTH, SLIDER_HEIGHT);
        } else {
            caption = "Y";
            hotKey = new String[]{ "(W)" , "(S)" };
            hotKeyX[0] = hotKeyX[1] = KNOB_CENTER_X+SLIDER_OFFSET-SLIDER_HEIGHT;
            hotKeyY = new int[]{ 12 , SLIDER_WIDTH-4 };
            slider.setOrientation( JSlider.VERTICAL );
            slider.setInverted( false );
            slider.setBounds( KNOB_CENTER_X+SLIDER_OFFSET, 0, SLIDER_HEIGHT, SLIDER_WIDTH);
        }
        knob = new Knob();
        add( knob );
        add( slider );
        slider.addChangeListener( new ChangeListener() {
                @Override
                public void stateChanged( ChangeEvent e ) {
                    repaint();
                }
            }
        );

        reset();
    }

    @Override
    public void reset() {
    }

    public boolean addListener( PotentioMeterListener pmtrListener ) {
        if ( pmtrListener != null ) {
            this.pmtrListener = pmtrListener;
            slider.addChangeListener( new ChangeListener() {
                    @Override
                    public void stateChanged( ChangeEvent e ) {
                        pmtrListener.potentioMeterPerformed();
                    }
                }
            );
            return ( true );
        } else {
            return ( false );
        }

    }

    public int getValue() {
        return ( slider.getValue() );
    }

    public int getValuePerCent() {
        return ( getValue()*100/255 );
    }

    public void setValue( int value ) {
        slider.setValue( value );
    }

    @Override
    // public void paintComponent( Graphics g ) {
    public void paint( Graphics g ) {
        super.paint( g );
        knob.repaint();
        g.setColor( Color.BLACK );
        g.setFont( new Font( "Arial", Font.BOLD, 28) );
        g.drawString( caption, KNOB_CENTER_X - TEXT_WIDTH/2, TEXT_OFFSET );
        g.setFont( new Font( "Arial", Font.BOLD, 16) );
        g.drawString( hotKey[0], hotKeyX[0], hotKeyY[0] );
        g.drawString( hotKey[1], hotKeyX[1], hotKeyY[1] );
    }

    private class Knob extends JPanel {
        private AffineTransform af = new AffineTransform();
        private Shape knobMarker = new Rectangle2D.Double( KNOB_SIZE/2-3,5,8,15 );

        public Knob() {
            setLayout( null );
            setBounds( KNOB_CENTER_X - KNOB_SIZE/2, KNOB_CENTER_Y - KNOB_SIZE/2, KNOB_SIZE, KNOB_SIZE );
            setOpaque( true );
            setBackground( Color.WHITE );
        }

        @Override
        public void paint( Graphics g ) {
            super.paint( g );
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor( Color.BLACK );
            g2.fillOval( 0, 0, KNOB_SIZE, KNOB_SIZE );
            af.setToRotation((double)(getValue()-127)*Math.PI/180,KNOB_SIZE/2,KNOB_SIZE/2);
            g2.transform(af);
            g2.setColor( Color.LIGHT_GRAY );
            g2.fill( knobMarker );
        }

    }

}
