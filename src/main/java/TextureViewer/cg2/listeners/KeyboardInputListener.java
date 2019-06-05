package TextureViewer.cg2.listeners;

import TextureViewer.cg2.elements.Shapes;
import TextureViewer.cg2.utils.Utils;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.util.texture.Texture;

import java.awt.event.KeyEvent;

/*
    Handles the KeyboardInputs
 */
public class KeyboardInputListener implements KeyListener {

    private final String TEXTURE_PATH = "./TextureViewer/src/main/java/TextureViewer/cg2/textures/";

    @Override
    public void keyPressed(com.jogamp.newt.event.KeyEvent e) {

    }

    @Override
    public void keyReleased(com.jogamp.newt.event.KeyEvent e) {

        short keyCode = e.getKeyCode();
        // Textures
        if(keyCode == KeyEvent.VK_1)
        {
            if(Shapes.getDiffuseMap() == null)
            {
                // load diffuse map
                Texture tex = Utils.loadTexture(TEXTURE_PATH + "crate_diff.jpg");
                Shapes.setDiffuseMap(tex);
            }
        }
        if(keyCode == KeyEvent.VK_2)
        {
            if(Shapes.getDiffuseMap() == null)
            {
                // load diffuse map
                Texture tex = Utils.loadTexture(TEXTURE_PATH + "crate_normal.jpg");
                Shapes.setDiffuseMap(tex);
            }
        }
        if(keyCode == KeyEvent.VK_3)
        {
            if(Shapes.getDiffuseMap() == null)
            {
                // load diffuse map
                Texture tex = Utils.loadTexture(TEXTURE_PATH + "crate_ao.jpg");
                Shapes.setDiffuseMap(tex);
            }
        }
        if(keyCode == KeyEvent.VK_4)
        {
            if(Shapes.getDiffuseMap() == null)
            {
                // load diffuse map
                Texture tex = Utils.loadTexture(TEXTURE_PATH + "crate_bump.jpg");
                Shapes.setDiffuseMap(tex);
            }
        }
        // Helpers
        if(keyCode == KeyEvent.VK_Q) {
            Shapes.DrawSurface = !Shapes.DrawSurface;
        }
        if(keyCode == KeyEvent.VK_W) {
            Shapes.DrawVertecies = !Shapes.DrawVertecies;
        }
        if(keyCode == KeyEvent.VK_E) {
            Shapes.DrawWireframe = !Shapes.DrawWireframe;
        }
        if(keyCode == KeyEvent.VK_R) {
            Shapes.DrawVertexNormals = !Shapes.DrawVertexNormals;
        }
        // Geometry
        if(keyCode == KeyEvent.VK_A) {
            EventListener.DrawGeometry = EventListener.Geometry.Plane;
        }
        if(keyCode == KeyEvent.VK_S) {
            EventListener.DrawGeometry = EventListener.Geometry.Cube;
        }
        if(keyCode == KeyEvent.VK_D) {
            EventListener.DrawGeometry = EventListener.Geometry.Spehre;
        }
        // Tesselation
        if(keyCode == KeyEvent.VK_0 ) {
            Shapes.TesselationLevel += 1;
        }
        if(keyCode == KeyEvent.VK_9) {
            Shapes.TesselationLevel -= 1;
        }

    }
}
