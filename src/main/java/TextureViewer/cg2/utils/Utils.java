package TextureViewer.cg2.utils;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.FileInputStream;
import java.io.IOException;

/*
    A Utility class to solve recurrent tasks
 */
public class Utils {

    public static Texture loadTexture(final String path)
    {
        // put texture loading on a different thread, so the app stays responsive
        new Thread()
        {
            public void run()
            {
                GLQueue.getInstance().add( new GLAction()
                {
                    public Texture execute(GL target)
                    {
                        try {
                            return TextureIO.newTexture(new FileInputStream(path),false,".jpg");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        return null;
                    }
                });
            }

        }.start();


        return null;
    }
}
