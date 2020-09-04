

import java.lang.reflect.Array;
import java.util.Scanner;

public class NBody {
    public static String imgs = "images/starfield.jpg";
    public static double readRadius(String filename){
        In in = new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }
    public static Planet[] readBodies(String filename){

        In in = new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        Planet[] bodies = new Planet[firstItemInFile];
        for (int i =0; i < bodies.length; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return bodies;

    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] bodies = readBodies(filename);
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,imgs);
        StdDraw.show();

        for (Planet b : bodies){
            b.draw();
        }

        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time <= T){
            double[] xforce = new double[bodies.length];
            double[] yforce = new double[bodies.length];
            for(int i = 0; i< bodies.length;i++){
                xforce[i] = bodies[i].calcNetForceExertedByX(bodies);
                yforce[i] = bodies[i].calcNetForceExertedByY(bodies);

            }
            for(int i = 0; i< bodies.length;i++){
                bodies[i].update(dt,xforce[i],yforce[i]);
            }

            StdDraw.setScale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0,0,imgs);
            for (Planet b : bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
