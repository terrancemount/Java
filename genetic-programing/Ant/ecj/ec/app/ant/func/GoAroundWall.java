package ec.app.ant.func;
import ec.*;
import ec.app.ant.*;
import ec.gp.*;
import ec.util.*;

/* 
 * Left.java
 * 
 * Created: Wed Nov  3 18:26:37 1999
 * By: Sean Luke
 */

/**
 * @author Sean Luke
 * @version 1.0 
 */

public class GoAroundWall extends GPNode implements EvalPrint
    {
    public String toString() { return "go-around-wall"; }

    public void checkConstraints(final EvolutionState state,
                                 final int tree,
                                 final GPIndividual typicalIndividual,
                                 final Parameter individualBase)
        {
        super.checkConstraints(state,tree,typicalIndividual,individualBase);
        if (children.length!=0)
            state.output.error("Incorrect number of children for node " + 
                               toStringForError() + " at " +
                               individualBase);
        }

    public void eval(final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem)
        {
	boolean TurnLeft = true;
	boolean TurnRight = true;
	int DistLeft = 0;
	int DistRight = 0;
	int xMud;
	int yMud;
	int counter = 0;
        Ant p = (Ant)problem;
        switch (p.orientation)
            {
            case Ant.O_UP:
		xMud = p.posx;
		yMud = (p.posy-1+p.maxy)%p.maxy;
		if (p.map[xMud][yMud] == Ant.WALL){
			//check left
			while (p.map[(xMud-counter+p.maxx)%p.maxx][yMud] == Ant.WALL){
				counter++;
			} 
			DistLeft = counter;
			counter = 0;
                        //check right
                        while (p.map[(xMud+counter+p.maxx)%p.maxx][yMud] == Ant.WALL){
                                counter++;
                        }
			DistRight = counter;
			if (DistRight > DistLeft){
				//turn left
				p.orientation = Ant.O_LEFT;
				p.moves++;
			}		
			else{
				//turn right
				p.orientation = Ant.O_RIGHT;
				p.moves++;
			}
		}
		else p.moves++;
                break;
            case Ant.O_LEFT:
                xMud = (p.posx-1+p.maxx)%p.maxx;
                yMud = p.posy;
                if (p.map[xMud][yMud] == Ant.WALL){        
                        //check left
                        while (p.map[xMud][(yMud+counter+p.maxy)%p.maxy] == Ant.WALL){
                                counter++;
                        } 
                        DistLeft = counter;
                        counter = 0;
                        //check right
                        while (p.map[xMud][(yMud-counter+p.maxy)%p.maxy] == Ant.WALL){
                                counter++;
                        } 
                        DistRight = counter;
                        if (DistRight > DistLeft){
                                //turn left
                                p.orientation = Ant.O_DOWN;
                                p.moves++;
                        }
                        else{
                                //turn right
                                p.orientation = Ant.O_UP;
                                p.moves++;
                        }
                }
		else p.moves++;
                break;
            case Ant.O_DOWN:
                xMud = p.posx;
                yMud = (p.posy+1+p.maxy)%p.maxy;
                if (p.map[xMud][yMud] == Ant.WALL){        
                        //check left
                        while (p.map[(xMud+counter+p.maxx)%p.maxx][yMud] == Ant.WALL){
                                counter++;
                        } 
                        DistLeft = counter;
                        counter = 0;
                        //check right
                        while (p.map[(xMud-counter+p.maxx)%p.maxx][yMud] == Ant.WALL){
                                counter++;
                        } 
                        DistRight = counter;
                        if (DistRight > DistLeft){
                                //turn left 
                                p.orientation = Ant.O_RIGHT;
                                p.moves++;
                        }
                        else{
                                //turn right
                                p.orientation = Ant.O_LEFT;
                                p.moves++;
                        }
                }
		else p.moves++;
                break;
            case Ant.O_RIGHT:
                xMud = (p.posx+1+p.maxx)%p.maxx;
                yMud = p.posy;
                if (p.map[xMud][yMud] == Ant.WALL){        
                        //check left
                        while (p.map[xMud][(yMud-counter+p.maxy)%p.maxy] == Ant.WALL){
                                counter++;
                        } 
                        DistLeft = counter;
                        counter = 0;
                        //check right
                        while (p.map[xMud][(yMud+counter+p.maxy)%p.maxy] == Ant.WALL){
                                counter++;
                        } 
                        DistRight = counter;
                        if (DistRight > DistLeft){
                                //turn left 
                                p.orientation = Ant.O_DOWN; 
                                p.moves++;
                        }
                        else{
                                //turn right
                                p.orientation = Ant.O_UP;   
                                p.moves++;
                        }
                }
		else p.moves++;
                break;
            default:  // whoa!
                state.output.fatal("Whoa, somehow I got a bad orientation! (" + p.orientation + ")");
                break;
            }
        }


    public void evalPrint(final EvolutionState state,
                          final int thread,
                          final GPData input,
                          final ADFStack stack,
                          final GPIndividual individual,
                          final Problem problem,
                          final int[][] map2)
        {
        eval(state,thread,input,stack,individual,problem);
        }
    }



