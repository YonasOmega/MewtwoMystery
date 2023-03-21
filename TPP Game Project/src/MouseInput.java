import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
        GamePanel gp;
        boolean mousePress = false;
        public int mx;
        public int my;
        public MouseInput(GamePanel gp){
                this .gp=gp;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

//
//                OBJ_Player_Projectile pProj = new OBJ_Player_Projectile(gp, gp.player.screenX, gp.player.screenY, mx, my);
        }


        @Override
        public void mousePressed(MouseEvent e) {
                mx = (e.getX() + (gp.player.worldX)-gp.player.screenX);
                my = (e.getY() + (gp.player.worldY)-gp.player.screenY);


                // System.out.println("getx"+e.getX());
                //System.out.println("screen: "+gp.player.screenX+", "+gp.player.screenY);

                mousePress=true;


        }

        @Override
        public void mouseReleased(MouseEvent e) {
                mousePress =false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
}
