/**
 * ベジェ曲線
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.util.LinkedList;
import java.util.ArrayList;

public class Bezier
{
    static final int DIVISION = 50;    //曲線補間の分割数（曲線の分解能）

    private LinkedList<Point> points = new LinkedList<>();  //曲線の座標列

    public void addPoint( int x, int y ) {  //曲線に必要な座標を追加するメソッド
        points.add( new Point( (double)x , (double)y ) );
    }

    public ArrayList<int[]> getPoints() {
        ArrayList<int[]> resultPoints = new ArrayList<>();
        for( int i=0 ; i<=DIVISION ; i++ ) {
            Point point = getBezierPointInner( i*(1.0/DIVISION) , points );
            int[] xyw = { (int)point.x , (int)point.y , 1 };
            resultPoints.add( xyw );
        }
        return ( resultPoints );
    }

    // ベジェ曲線の補間座標を生成するメソッド（再帰的に実現）
    private Point getBezierPointInner( double t , LinkedList<Point> ps ) {
        if ( ps.isEmpty() ) {
            return ( new Point( 0,0 ) );
        } else if ( ps.size() == 1 ) {
            return ( ps.getFirst() );
        } else {
            LinkedList<Point> pHeads = new LinkedList<>();
            pHeads.addAll( ps );
            pHeads.removeLast();
            Point p0 = getBezierPointInner( t , pHeads );

            LinkedList<Point> pTails = new LinkedList<>();
            pTails.addAll( ps );
            pTails.removeFirst();
            Point p1 = getBezierPointInner( t , pTails );

            return ( new Point( (1.0-t)*p0.x + t*p1.x , (1.0-t)*p0.y + t*p1.y ) );
        }
    }

    private class Point {  //(x,y)座標を構成する内部クラス
        double x;
        double y;
        public Point( double x, double y ) {
            this.x = x;
            this.y = y;
        }
    }
}
