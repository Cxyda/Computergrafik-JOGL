package TextureViewer.cg2.elements;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

/*
    The Material class hanldes all material related properties
 */
public class Material {

    private static float[] diffuseColor = new float[] {1f, 1f, 1f, 1.0f};
    private static float[] specularColor = new float[] {0.1f, 0.1f, 0.1f, 1.0f};
    private static float shininess = 110;

    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
    }

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glPushMatrix();

        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, Material.diffuseColor, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, Material.specularColor, 0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, Material.shininess );

        gl.glPopMatrix();
    }
}
