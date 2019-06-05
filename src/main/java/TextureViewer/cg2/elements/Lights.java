package TextureViewer.cg2.elements;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

/*
    The Lights class handles all Light related properties
 */
public class Lights {

    private static float[] LightPosition = new float[] {-15f, 10f, 1.0f};

    private GLU glu = new GLU();
    private GLUT glut = new GLUT();

    private float[] zero = { 0.0f, 0.0f, 0.0f, 1.0f};

    public static void setLightPosition(float x, float y)
    {
        LightPosition[0] = x;
        LightPosition[1] = y;
    }
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        // enable Lights
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_NORMALIZE);

        // enable light source 0
        gl.glEnable(GL2.GL_LIGHT0);

        float[] diffuseLight = { 1f, 1f, 1f, 1f };
        float[] ambientLight = { 0.1f, 0.1f, 0.1f, 1f };
        float[] specularLight = { 0.1f, 0.1f, 0.1f, 0.1f };

        // activate diffuse, ambient and specular light for light source 0
        gl.glLightfv( GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0 );
        gl.glLightfv( GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0 );
        gl.glLightfv( GL2.GL_LIGHT0, GL2.GL_SPECULAR, specularLight, 0 );
    }

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glPushMatrix();
        gl.glTranslatef(LightPosition[0], LightPosition[1], LightPosition[2]);

        gl.glLightfv(GL2.GL_LIGHT0,
                GL2.GL_POSITION,
                zero, 0);

        gl.glPopMatrix();
    }
}
