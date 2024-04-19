package stub;

/**
 * クラス PowerLed の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PowerLed extends Led
{
    public PowerLed( int assign )
    {
        super( assign );
    }

    @Override
    public void reset() {
        on();
    }

    @Override
    public void low() {
    }

    @Override
    protected void setLedColor( Graphics g ) {
        g.setColor( Color.GREEN );
    }

}
