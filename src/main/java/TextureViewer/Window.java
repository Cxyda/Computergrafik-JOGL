package TextureViewer;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

/*
    This class handles all Window related properties
 */
public class Window {

    private static int FPS = 60;
    private static int WINDOW_WIDTH = 1280;
    private static int WINDOW_HEIGHT = 768;

    static GLWindow createWindow() {
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(profile);
        caps.setDepthBits(16);

        // init the window
        GLWindow window = GLWindow.create(caps);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // window.setDefaultCloseOperation(??);

        // init the animator
        FPSAnimator animator = new FPSAnimator(window, FPS);
        animator.start();

        window.setVisible(true);
        window.requestFocus();

        return window;
    }

    public static int getWindowWidth()
    {
        return WINDOW_WIDTH;
    }
    public static int getWindowHeight()
    {
        return WINDOW_HEIGHT;
    }
}
