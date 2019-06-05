package TextureViewer.cg2.elements;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.VectorUtil;
import com.jogamp.opengl.util.texture.Texture;

/*
    The Shape class
    - handles all Shaües related properties
    - holds / calculates all mesh data of the shapes
    - draws the shapes to the screen
 */
public class Shapes {

    public static boolean DrawSurface = true;

    public static boolean DrawVertecies = false;
    public static boolean DrawVertexNormals = false;

    public static boolean DrawWireframe = false;

    public static int TesselationLevel = 1;

    private static float[] redColor = new float[] {1.0f, 0.0f, 0.0f};
    private static float[] greenColor = new float[] {0.0f, 1.0f, 0.0f};
    private static float[] blueColor = new float[] {0.0f, 0.0f, 1.0f};

    private static Texture diffuseMap;

    private static float wireframeLineWitdh = 3.0f;
    private static float[] wireframeLineColor = new float[] {1.0f, 1.0f, 1.0f};

    private static boolean disableDiffuseMap = false;

    private static float[][] _sphereVertexArray;
    private static int[][] _sphereTriangleArray;
    private static float[][] _sphereUVArray;

    public static void setDiffuseMap( Texture texture)
    {
        if(texture == null)
        {
            disableDiffuseMap = true;
        }
        else
        {
            Shapes.diffuseMap = texture;
        }
    }
    public static Texture getDiffuseMap()
    {
        return Shapes.diffuseMap;
    }
    public static void drawCoordinateSystem(GL2 gl) {

        float[] origin = new float[] {0.0f, 0.0f, 0.0f };

        drawVectorAtPosition(gl, origin, new float[] {1.0f, 0.0f, 0.0f}, Shapes.redColor, 0.3f);
        drawVectorAtPosition(gl, origin, new float[] {0.0f, 1.0f, 0.0f}, Shapes.greenColor, 0.3f);
        drawVectorAtPosition(gl, origin, new float[] {0.0f, 0.0f, 1.0f}, Shapes.blueColor, 0.3f);
    }

    public static void drawQuadAtCenter(GL2 gl, float size)
    {
        float halfSize = size *0.5f;
        // All points of the quad
        float[][] vertecies = new float[][]{
                new float[]{-halfSize, halfSize, 0.0f},
                new float[]{-halfSize, -halfSize, 0.0f},
                new float[]{halfSize, -halfSize, 0.0f},
                new float[]{halfSize, halfSize, 0.0f},
        };

        float[][] uvs = new float[][]{
                new float[]{0.0f, 1.0f},
                new float[]{0.0f, 0.0f},
                new float[]{1.0f, 1.0f},
                new float[]{1.0f, 1.0f},
                new float[]{0.0f, 0.0f},
                new float[]{1.0f, 0.0f}
        };

    // all triagles built from vertexID
    int[][] triangles = new int[][] {
            new int[] {0,1,3},      // Triangle 1 connects Vertex 0, 1 and 3
            new int[] {3,1,2}       // Triangle 1 connects Vertex 3, 1 and 2
    };

        Shapes.drawMesh(gl, vertecies, triangles, uvs);
        gl.glFlush();
    }
    private static void prepareLineRendering(GL2 gl, boolean prepare)
    {
        if(prepare)
        {
            // disable Textures, Lighting and reset Color
            gl.glDisable(GL2.GL_TEXTURE_2D);
            gl.glDisable(GL2.GL_LIGHTING);
        }
        else
        {
            gl.glColor3f(1,1,1);
            gl.glEnable(GL2.GL_LIGHTING);
            gl.glEnable(GL2.GL_TEXTURE_2D);
        }
    }
    private static void drawVectorAtPosition(GL2 gl, float[] position, float[] vector, float[] color, float length)
    {
        gl.glColor3f(color[0],color[1],color[2]);
        prepareLineRendering(gl, true);
        gl.glPushMatrix();
        gl.glTranslatef( position[0], position[1], position[2] );

        gl.glBegin(GL.GL_LINES);
        // draw vector
        gl.glVertex3f(0f, 0f, 0f);
        gl.glVertex3f(vector[0] * length, vector[1] * length, vector[2] * length);
        gl.glEnd();

        gl.glPopMatrix();
        prepareLineRendering(gl, false);
    }
    public static void drawCubeAtCenter(GL2 gl, float size)
    {
        float halfSize = size *0.5f;
        // All points of the cube
        float[][] vertecies = new float[][]{
                new float[]{-halfSize, halfSize, halfSize},    // front
                new float[]{-halfSize, -halfSize, halfSize},   // front
                new float[]{halfSize, -halfSize, halfSize},    // front
                new float[]{halfSize, halfSize, halfSize},     // front

                new float[]{halfSize, halfSize, -halfSize},      // back
                new float[]{halfSize, -halfSize, -halfSize},     // back
                new float[]{-halfSize, -halfSize, -halfSize},    // back
                new float[]{-halfSize, halfSize, -halfSize}      // back
        };

        float[][] uvs = new float[][]{
                new float[]{0.0f, 1.0f},    // face 1
                new float[]{0.0f, 0.0f},    // face 1
                new float[]{1.0f, 1.0f},    // face 1
                new float[]{1.0f, 1.0f},    // face 2
                new float[]{0.0f, 0.0f},    // face 2
                new float[]{1.0f, 0.0f},    // face 2

                new float[]{0.0f, 1.0f},    // face 3
                new float[]{0.0f, 0.0f},    // face 3
                new float[]{1.0f, 1.0f},    // face 3
                new float[]{1.0f, 1.0f},    // face 4
                new float[]{0.0f, 0.0f},    // face 4
                new float[]{1.0f, 0.0f},    // face 4

                new float[]{0.0f, 1.0f},    // face 5
                new float[]{0.0f, 0.0f},    // face 5
                new float[]{1.0f, 1.0f},    // face 5
                new float[]{1.0f, 1.0f},    // face 6
                new float[]{0.0f, 0.0f},    // face 6
                new float[]{1.0f, 0.0f},    // face 6

                new float[]{0.0f, 1.0f},    // face 7
                new float[]{0.0f, 0.0f},    // face 7
                new float[]{1.0f, 1.0f},    // face 7
                new float[]{1.0f, 1.0f},    // face 8
                new float[]{0.0f, 0.0f},    // face 8
                new float[]{1.0f, 0.0f},    // face 8

                new float[]{0.0f, 1.0f},    // face 9
                new float[]{0.0f, 0.0f},    // face 9
                new float[]{1.0f, 1.0f},    // face 9
                new float[]{1.0f, 1.0f},    // face 10
                new float[]{0.0f, 0.0f},    // face 10
                new float[]{1.0f, 0.0f},    // face 10

                new float[]{0.0f, 1.0f},    // face 11
                new float[]{0.0f, 0.0f},    // face 11
                new float[]{1.0f, 1.0f},    // face 11
                new float[]{1.0f, 1.0f},    // face 12
                new float[]{0.0f, 0.0f},    // face 12
                new float[]{1.0f, 0.0f},    // face 12

        };

        // all triagles built from vertexID
        int[][] triangles = new int[][] {
                new int[] {0,1,3},  // front
                new int[] {3,1,2},  // front

                new int[] {3,2,4},  // right
                new int[] {4,2,5},  // right

                new int[] {4,5,7},  // back
                new int[] {7,5,6},  // back

                new int[] {7,6,0},  // left
                new int[] {0,6,1},  // left

                new int[] {7,0,4},  // top
                new int[] {4,0,3},  // top

                new int[] {1,6,2},  // bottom
                new int[] {2,6,5},  // bottom
        };

        Shapes.drawMesh(gl, vertecies, triangles, uvs);
        gl.glFlush();
    }

    private static float[][] generateVerteciesForSphere(int longSubDivs, int latSubdivs, float radius)
    {
        double DEGS_TO_RAD = Math.PI/180.0f;
        int numVertices = 0;
        int p, s;
        float x, y, z, out;
        int nPitch = longSubDivs + 1;

        double pitchInc = (180. / (float)nPitch) * DEGS_TO_RAD;
        double rotInc   = (360. / (float)latSubdivs) * DEGS_TO_RAD;

        // init the vertex array. Globes have a vertex count of
        // latSubdivs * (longSubDivs-1) * 2
        float[][] vertecies = new float[latSubdivs * (longSubDivs-1) * 2][3];

        // position of the north-pole
        vertecies[numVertices] = new float[] {0,radius,0};
        numVertices++;

        // generate all vertecies between the poles
        for(p=1; p<nPitch; p++)
        {
            // Distance of the point at the current angle
            out = (float)(radius * Math.sin(p * pitchInc));
            out = Math.abs(out);
            // y-coordinate
            y   = (float)(radius * Math.cos(p * pitchInc));

            for(s=0; s<latSubdivs; s++)
            {
                x = (float)(out * Math.cos(s * rotInc));
                z = (float)(out * Math.sin(s * rotInc));
                vertecies[numVertices] = new float[] {x,y,z};
                numVertices++;
            }
        }
        // position of the south-pole
        vertecies[numVertices] = new float[] {0,-radius,0};
        return vertecies;
    }
    private static int[][] generateTrianglesForSphere(int longSubDivs, int latSubdivs)
    {
        int s, p;
        int nPitch = longSubDivs + 1;
        int numVertices = ((longSubDivs-1) * latSubdivs) + 2;
        int[][] triangles = new int[2 * longSubDivs * latSubdivs][];
        // set the start index to where the first quads start
        int numTriangles = latSubdivs;
        // generate the north-pole cap
        for (s = 0; s < latSubdivs; s++) {
            int[] t = new int[]{0, (s+1)%latSubdivs + 1, s+1};
            triangles[s] = t;
        }
        // generate the sphere-mantle
        for(p=1; p < nPitch-1; p++)
        {
            for (s = 0; s < latSubdivs; s++) {
                // next vertex in this row
                int v0 = (p-1) * latSubdivs + s + 1;
                // next vertex in the next row
                int v1 = v0 + latSubdivs;
                // next vertex
                int v2 = v1 + 1;
                int v4 = v0 + 1;
                if(s == (latSubdivs -1))
                {
                    // at the last iteration, connect back to the beginning
                    v2 -= latSubdivs;
                    v4 -= latSubdivs;
                }
                // finally build triangle 1
                int[] t1 = new int[]{v0, v2, v1};
                triangles[numTriangles++] = t1;
                // finally build triangle 2
                int[] t2 = new int[]{v0, v4, v2};
                triangles[numTriangles++] = t2;
            }
        }
        // generate the south-pole cap
        for (s = 0; s < latSubdivs; s++) {
            int[] t = new int[]{numVertices, numVertices-latSubdivs + s, numVertices-latSubdivs + (s + 1)%latSubdivs};
            triangles[numTriangles+s] = t;
        }
        return triangles;
    }
    private static float[][] generateSphhereUVs(float[][] vertecies, int[][] triangles, int uSubDivs, int vSubDivs)
    {
        float[][] uvs = new float[triangles.length * 3][2];

        double u, v;
        for (int cnt = 0; cnt < triangles.length; cnt++)
        {
            int[] triangle = triangles[cnt];

            for(int i = 0; i < 3; i++)
            {
                float[] vertex = new float[] { vertecies[triangle[i]][0], vertecies[triangle[i]][1], vertecies[triangle[i]][2]};
                VectorUtil.normalizeVec3(vertex);
                u = 0.5f + (Math.atan2(vertex[2], vertex[0]) / (2*Math.PI));
                v = 0.5f - (Math.asin(vertex[1]) / Math.PI);
                uvs[3 * cnt + i] = new float[] {(float)u, (float)v };
            }
        }
        return uvs;
    }
    public static void generateSphere(int longSubDivs, int latSubdivs, float radius)
    {
        _sphereVertexArray = generateVerteciesForSphere(longSubDivs, latSubdivs, radius);
        _sphereTriangleArray = generateTrianglesForSphere(longSubDivs, latSubdivs);
        _sphereUVArray = generateSphhereUVs(_sphereVertexArray, _sphereTriangleArray, longSubDivs, latSubdivs);

    }
    public static void drawSphereAtCenter(GL2 gl)
    {
        drawMesh(gl, _sphereVertexArray, _sphereTriangleArray, _sphereUVArray);
    }

    public static void drawPyramid(GL2 gl, float size) {

        float halfSize = size / 2.0f;
        // All points of the pyramid
        float[][] vertecies = new float[][]{
                new float[]{0.0f, halfSize, 0.0f},
                new float[]{-halfSize, -halfSize, halfSize},
                new float[]{halfSize, -halfSize, halfSize},
                new float[]{halfSize, -halfSize, -halfSize},
                new float[]{-halfSize, -halfSize, -halfSize},
        };
        float[][] uvs = new float[][]{
                new float[]{0.5f, 1.0f},
                new float[]{0.0f, 0.0f},
                new float[]{1.0f, 0.0f},
                new float[]{0.5f, 1.0f},
                new float[]{0.0f, 0.0f},
                new float[]{1.0f, 0.0f},
                new float[]{0.5f, 1.0f},
                new float[]{0.0f, 0.0f},
                new float[]{1.0f, 0.0f},
                new float[]{0.5f, 1.0f},
                new float[]{0.0f, 0.0f},
                new float[]{1.0f, 0.0f},

                new float[]{0.0f, 1.0f},
                new float[]{0.0f, 0.0f},
                new float[]{1.0f, 0.0f},

                new float[]{0.0f, 1.0f},
                new float[]{1.0f, 0.0f},
                new float[]{1.0f, 1.0f},

        };

        // all triagles built from vertexID
        int[][] faces = new int[][] {
                new int[] {0,1,2},  // front
                new int[] {0,2,3},  // right
                new int[] {0,3,4},  // back
                new int[] {0,4,1},  // left
                new int[] {1,4,3},  // bottom1
                new int[] {1,3,2},  // bottom2
        };

        Shapes.drawMesh(gl, vertecies, faces, uvs);
        gl.glFlush();
    }

    private static void drawMesh(GL2 gl, float[][] points, int[][] faces, float[][] uvs)
    {

        if(Shapes.diffuseMap != null)
        {
            Shapes.diffuseMap.bind(gl);
        }
        if(Shapes.diffuseMap != null && disableDiffuseMap)
        {
            Shapes.diffuseMap.destroy(gl);
            disableDiffuseMap = false;
        }

        int cnt = 0;
        for (int[] face : faces) {
            Shapes.tesselateAndDrawTriangle(gl,
                    new float[][]{points[face[0]], points[face[1]], points[face[2]]},
                    new float[][]{uvs[cnt*3], uvs[cnt*3+1], uvs[cnt*3+2]}, 1);
            cnt++;
        }
    }

    private static float[] calculateNormal(float[] p1, float[] p2, float[]p3 )
    {
        float[] v1 = new float[] {
                p2[0] - p1[0],
                p2[1] - p1[1],
                p2[2] - p1[2]
        };
        float[] v2 = new float[] {
                p3[0] - p1[0],
                p3[1] - p1[1],
                p3[2] - p1[2]
        };
        float[] normal = new float[3];
        VectorUtil.crossVec3(normal, v1, v2);
        VectorUtil.normalizeVec3(normal);
        return normal;
    }
    private static void tesselateAndDrawTriangle(GL2 gl, float[][] vertecies, float[][] uvs, int currentTesselationLevel)
    {
        if(currentTesselationLevel < 1)
        {
            currentTesselationLevel = 1;
        }
        if(currentTesselationLevel == TesselationLevel)
        {
            drawTriangle(gl, vertecies, uvs);
        }
        else if(currentTesselationLevel < TesselationLevel)
        {
            float[][] tesseletedTriangle = tesselateTriange(vertecies);
            float[][] tesselatedUVs = tesselateUVs(uvs);
            currentTesselationLevel++;
            for(int i = 0; i < 4; i++)
            {
                tesselateAndDrawTriangle(gl,
                        new float[][] {
                                tesseletedTriangle[3*i],
                                tesseletedTriangle[3*i + 1],
                                tesseletedTriangle[3*i + 2]} ,
                        new float[][] {
                                tesselatedUVs[3*i],
                                tesselatedUVs[3*i + 1],
                                tesselatedUVs[3*i + 2]} ,
                        currentTesselationLevel);
            }
        }
        else {
            currentTesselationLevel--;
        }
    }

    private static float[][] tesselateUVs(float[][] uvs)
    {
        float[][] tesselatedUV = new float[uvs.length * 4][2];

        // Get the midpoint of vector 1
        float[] mid01 = midVec2(uvs[0], uvs[1]);

        // Get the midpoint of vector 2
        float[] mid02 = midVec2(uvs[0], uvs[2]);

        // Get the midpoint of vector 3
        float[] mid03 = midVec2(uvs[1], uvs[2]);

        // construct triangles
        // triangle 1
        tesselatedUV[0] = uvs[0];
        tesselatedUV[1] = mid01;
        tesselatedUV[2] = mid02;
        // triangle 2
        tesselatedUV[3] = mid02;
        tesselatedUV[4] = mid01;
        tesselatedUV[5] = mid03;
        // triangle 3
        tesselatedUV[6] = mid01;
        tesselatedUV[7] = uvs[1];
        tesselatedUV[8] = mid03;
        // triangle 4
        tesselatedUV[9] = mid02;
        tesselatedUV[10] = mid03;
        tesselatedUV[11] = uvs[2];

        return tesselatedUV;
    }

    private static float[][] tesselateTriange(float[][] vertecies)
    {
        float[][] tesselatedTriangle = new float[12][3];

        // Get the midpoint of vector 1
        float[] mid01 = new float[3];
        VectorUtil.midVec3(mid01, vertecies[0], vertecies[1]);

        // Get the midpoint of vector 2
        float[] mid02 = new float[3];
        VectorUtil.midVec3(mid02, vertecies[0], vertecies[2]);

        // Get the midpoint of vector 3
        float[] mid03 = new float[3];
        VectorUtil.midVec3(mid03, vertecies[1], vertecies[2]);

        // construct triangles
        // triangle 1
        tesselatedTriangle[0] = vertecies[0];
        tesselatedTriangle[1] = mid01;
        tesselatedTriangle[2] = mid02;
        // triangle 2
        tesselatedTriangle[3] = mid02;
        tesselatedTriangle[4] = mid01;
        tesselatedTriangle[5] = mid03;
        // triangle 3
        tesselatedTriangle[6] = mid01;
        tesselatedTriangle[7] = vertecies[1];
        tesselatedTriangle[8] = mid03;
        // triangle 4
        tesselatedTriangle[9] = mid02;
        tesselatedTriangle[10] = mid03;
        tesselatedTriangle[11] = vertecies[2];

        return tesselatedTriangle;
    }
    private static void drawTriangle(GL2 gl, float[][] vertecies, float[][] uvs)
    {
        if(vertecies.length != 3)
        {
            throw new IllegalArgumentException("Vertexcount is invalid. Expected vertecies are 3.");
        }
        float[] normal = calculateNormal(vertecies[0], vertecies[1], vertecies[2]);

        if(Shapes.DrawSurface)
        {
    gl.glBegin( GL2.GL_TRIANGLES );

    gl.glTexCoord2fv(uvs[0], 0);   gl.glVertex3fv(vertecies[0], 0);     gl.glNormal3fv(normal, 0);
    gl.glTexCoord2fv(uvs[1], 0);   gl.glVertex3fv(vertecies[1], 0);     gl.glNormal3fv(normal, 0);
    gl.glTexCoord2fv(uvs[2], 0);   gl.glVertex3fv(vertecies[2], 0);     gl.glNormal3fv(normal, 0);

    gl.glEnd();
        }

        prepareLineRendering(gl, true);

        if(Shapes.DrawVertecies)
        {
            gl.glBegin(GL2.GL_POINTS);
            gl.glVertex3fv(vertecies[0], 0);
            gl.glVertex3fv(vertecies[1], 0);
            gl.glVertex3fv(vertecies[2], 0);
            gl.glEnd();
        }

        if (Shapes.DrawWireframe) {
            gl.glColor3fv(wireframeLineColor, 0);
            gl.glLineWidth(wireframeLineWitdh);
            for (int i = 0; i < vertecies.length; i++) {

                gl.glBegin(GL2.GL_LINES);
                gl.glVertex3fv(vertecies[i], 0);
                int nextId = (i + 1) % vertecies.length;
                gl.glVertex3fv(vertecies[nextId], 0);

                gl.glEnd();
            }
            gl.glLineWidth(1.0f);
        }

        if (Shapes.DrawVertexNormals)
        {
            drawVectorAtPosition(gl, vertecies[0], normal, Shapes.blueColor, 0.2f);
            drawVectorAtPosition(gl, vertecies[1], normal, Shapes.blueColor, 0.2f);
            drawVectorAtPosition(gl, vertecies[2], normal, Shapes.blueColor, 0.2f);
        }

        prepareLineRendering(gl, false);
    }
    public static float[] midVec2(float[] var1, float[] var2) {
        float[] var0 = new float[2];
        var0[0] = (var1[0] + var2[0]) * 0.5F;
        var0[1] = (var1[1] + var2[1]) * 0.5F;
        return var0;
    }
}
