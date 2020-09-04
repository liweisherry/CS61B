
    public class Body {

        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName;
        final static double g = 6.67E-11;
        public Body ( double xP, double yP, double xV,
                      double yV, double m, String img){
            xxPos =xP;
            yyPos =yP;
            xxVel =xV;
            yyVel = yV;
            mass = m;
            imgFileName = img;
    }
        public Body(Body b){
            xxPos = b.xxPos;
            yyPos = b.yyPos;
            xxVel = b.xxVel;
            yyVel = b.yyVel;
            mass = b.mass;
            imgFileName = b.imgFileName;
        }

        public double calcDistance(Body b){
            double a = xxPos;
            double c = yyPos;
            return Math.sqrt(Math.pow(a-b.xxPos,2)+Math.pow(c-b.yyPos,2));
        }

        public double calcForceExertedBy(Body b){
            double distance = calcDistance(b);
            return (g*mass*b.mass)/Math.pow(distance,2);

        }
        public double calcForceExertedByX(Body b){
            double distance = calcDistance(b);
            double ForceExpert = calcForceExertedBy(b);
            return ForceExpert*Math.abs(xxPos-b.xxPos)/distance;
        }

        public double calcForceExertedByY(Body b){
            double distance = calcDistance(b);
            double ForceExpert = calcForceExertedBy(b);
            return ForceExpert*Math.abs(yyPos-b.yyPos)/distance;
        }
        public double calcNetForceExertedByX(Body[] bodies){
            double sum =0;
            for (int i=0 ; i < bodies.length; i++){
                if (equals(bodies[i]) != true){
                    sum += calcForceExertedByX(bodies[i]);
                }

            }
            return sum;
        }
        public double calcNetForceExertedByY(Body[] bodies){
            double sum =0;
            for (int i=0 ; i < bodies.length; i++){
                if (equals(bodies[i]) != true){
                    sum += calcForceExertedByY(bodies[i]);
                }

            }
            return sum;
        }

        public void update(double sec, double xf, double yf){
            double ax = xf/mass;
            double ay = yf/mass;
            xxVel = xxVel + ax*sec;
            yyVel = yyVel + ay*sec;
            xxPos = xxPos + xxVel*sec;
            yyPos = yyPos + yyVel*sec;

        }
        public void  draw(){
            StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
            StdDraw.show();
        }


    }

