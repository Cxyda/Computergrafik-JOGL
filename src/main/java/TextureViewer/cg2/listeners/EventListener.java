package TextureViewer.cg2.listeners;

import TextureViewer.cg2.elements.Lights;
import TextureViewer.cg2.elements.Material;
import TextureViewer.cg2.elements.Shapes;
import TextureViewer.cg2.utils.GLQueue;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

@SuppressWarnings("Duplicates")
/*
    The main listener for thhe OpenGL events, this class calls / updates all element classes
    and initializes thhe scene
 */
public class EventListener implements GLEventListener {

    public enum Geometry {
        Nothing,
        Plane,
        Cube,
        Spehre,
        Pyramid
    }

    public static Geometry DrawGeometry;

    private GLU glu = new GLU();

    private Lights lights = new Lights();
    private Material material = new Material();

    private float xRotation = 0.0f;
    private float yRotation = 0.0f;
    private float zRotation = 0.0f;

    private static float xVelocity = 0.1f;
    private static float yVelocity = -0.2f;
    private static float zVelocity = 0.0f;

    static void setRotationVelocity(float xVelocity, float yVelocity)
    {
        EventListener.xVelocity = xVelocity;
        EventListener.yVelocity = yVelocity;
    }
    @Override
    public void display(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();
        GLQueue.getInstance().execute(drawable.getGL());
        // Clear The Screen And The Depth Buffer
        gl.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );

        // Display all other elements
        lights.display(drawable);
        material.display(drawable);

        // Draw the coordinate system
        // Push the current matrix
        gl.glPushMatrix();
        // Move the world
        gl.glTranslatef( -4.2f, -2.45f, -10.0f );

        // Rotate the world around x/y/z-axis
        gl.glRotatef( xRotation, 1.0f, 0.0f, 0.0f );
        gl.glRotatef( yRotation, 0.0f, 1.0f, 0.0f );
        gl.glRotatef( zRotation, 0.0f, 0.0f, 1.0f );

        Shapes.drawCoordinateSystem(gl);

        // Pop the matrix
        gl.glPopMatrix();
        // End draw coordinate system

        // Push the current matrix
        gl.glPushMatrix();

        // Move the world
        gl.glTranslatef( 0.0f, 0.0f, -10.0f );

        // Rotate the world around x/y/z-axis
        gl.glRotatef( xRotation, 1.0f, 0.0f, 0.0f );
        gl.glRotatef( yRotation, 0.0f, 1.0f, 0.0f );
        gl.glRotatef( zRotation, 0.0f, 0.0f, 1.0f );

        // Draw the geometry
        switch (DrawGeometry)
        {
            case Plane:
                Shapes.drawQuadAtCenter(gl, 2.0f);
                break;
            case Cube:
                Shapes.drawCubeAtCenter(gl, 2.0f);
                break;
            case Spehre:
                Shapes.generateSphere(12,12,1.5f);
                Shapes.drawSphereAtCenter(gl);
                break;
            case Pyramid:
                Shapes.drawPyramid(gl, 2.0f);
                break;
            default:
                break;
        }
        // Pop the last matrix
        gl.glPopMatrix();

        // Set rotation velocity
        xRotation += xVelocity;
        yRotation += yVelocity;
        zRotation += zVelocity;
    }

    @Override
    public void dispose( GLAutoDrawable drawable ) {
    }

    @Override
    public void init( GLAutoDrawable drawable ) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glShadeModel(GL2.GL_SMOOTH);

        gl.glClearColor(0.15f, 0.155f, 0.18f, 1f);
        gl.glClearDepth(1.0f);

        gl.glEnable(GL2.GL_DEPTH_TEST);

        // Enable Textures
        gl.glEnable(GL2.GL_TEXTURE_2D);

        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        lights.init(drawable);
        material.init(drawable);

    }

    @Override
    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {

        final GL2 gl = drawable.getGL().getGL2();
        if(height <= 0)
            height = 1;

        // calculate the aspect ratio
        final float h = ( float ) width / ( float ) height;
        gl.glViewport( 0, 0, width, height );
        gl.glMatrixMode( GL2.GL_PROJECTION );

        gl.glLoadIdentity();
        glu.gluPerspective( 30.0f, h, 1.0, 20.0 );
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
    }

}
