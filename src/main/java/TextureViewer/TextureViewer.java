package TextureViewer;

import TextureViewer.cg2.listeners.EventListener;
import TextureViewer.cg2.listeners.KeyboardInputListener;
import TextureViewer.cg2.listeners.MouseInputListener;
import com.jogamp.newt.opengl.GLWindow;

/*
    The entry point of thhis project
    It initializes the window and attaches all listeners
 */
public class TextureViewer {

    public static void main(String[] args) {
        GLWindow window = Window.createWindow();

        // add listeners
        window.addMouseListener(new MouseInputListener());
        window.addGLEventListener(new EventListener());
        window.addKeyListener(new KeyboardInputListener());

        // set default geometry
        EventListener.DrawGeometry = EventListener.Geometry.Cube;
    }
}
