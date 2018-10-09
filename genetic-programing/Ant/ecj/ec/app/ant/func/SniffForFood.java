
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

public class SniffForFood extends GPNode implements EvalPrint
    {
    public String toString() { return "sniff-for-food"; }

    public void checkConstraints(final EvolutionState state,
                                 final int tree,
                                 final GPIndividual typicalIndividual,
                                 final Parameter individualBase)
        {
        super.checkConstraints(state,tree,typicalIndividual,individualBase);
        if (children.length!=2)
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

        Ant p = (Ant)problem;
	int rangeX = p.maxx/2;
	int rangeY = p.maxy/2;
	int closeFoodX=0;
	int closeFoodY=0;
	int Dist = 0;
	int Quad = 0;
	boolean FoundFood = false;
	boolean Debugging = false;

	//find nearest food
        switch (p.orientation)
            {
            case Ant.O_UP:
		for (int x=1;(x<=rangeX)&&(!FoundFood);x++){
			for(int yy = p.posy-x;yy<=p.posy+x&&(!FoundFood);yy++){
				for(int xx=p.posx-x;xx<=p.posx+x&&(!FoundFood);xx++){
					if (p.map[(xx+p.maxx)%p.maxx][(yy+p.maxy)%p.maxy] == p.FOOD){
						FoundFood = true;
						closeFoodX = xx;
						closeFoodY = yy;
						Dist = x;
						if (Debugging) state.output.warning("Found some food in Sniffer at ("+xx+","+yy+").");
						if (Debugging) state.output.warning("Ant at location ("+p.posx+","+p.posy+") facing "+p.orientation+".");
					}
				}
			}
		}
		if (Dist == 1 && closeFoodY!=p.posy){
			if (p.map[(p.posx+p.maxx-1)%p.maxx][p.posy] == p.FOOD){
				closeFoodX = (p.posx+p.maxx-1)%p.maxx;
				closeFoodY = p.posy;
			}
                        else if (p.map[(p.posx+p.maxx+1)%p.maxx][p.posy] == p.FOOD){
                                closeFoodX = (p.posx+p.maxx+1)%p.maxx;
                                closeFoodY = p.posy;
                        }
		}
		break;
            case Ant.O_LEFT:
                for (int x=1;(x<=rangeX)&&(!FoundFood);x++){
                        for(int xx = p.posx-x;xx<=p.posx+x&&(!FoundFood);xx++){
                                for(int yy=p.posy-x;yy<=p.posy+x&&(!FoundFood);yy++){
                                        if (p.map[(xx+p.maxx)%p.maxx][(yy+p.maxy)%p.maxy] == p.FOOD){
                                                FoundFood = true;
                                                closeFoodX = xx;
                                                closeFoodY = yy;
                                                Dist = x;
                                                if (Debugging) state.output.warning("Found some food in Sniffer at ("+xx+","+yy+").");
                                                if (Debugging) state.output.warning("Ant at location ("+p.posx+","+p.posy+") facing "+p.orientation+".");
                                        }
                                }
                        }
                }
                if (Dist == 1 && closeFoodX!=p.posx){
                        if (p.map[p.posx][(p.posy+p.maxy-1)%p.maxy] == p.FOOD){
                                closeFoodX = p.posx;
                                closeFoodY = (p.posy+p.maxy-1)%p.maxy;
                        }
                        else if (p.map[p.posx][(p.posy+p.maxy+1)%p.maxy] == p.FOOD){
                                closeFoodX = p.posx;
                                closeFoodY = (p.posy+p.maxy+1)%p.maxy;
                        }
                }   
		break;

            case Ant.O_DOWN:
                for (int x=1;(x<=rangeX)&&(!FoundFood);x++){
                        for(int yy = p.posy+x;yy>=p.posy-x&&(!FoundFood);yy--){
                                for(int xx=p.posx-x;xx<=p.posx+x&&(!FoundFood);xx++){
                                        if (p.map[(xx+p.maxx)%p.maxx][(yy+p.maxy)%p.maxy] == p.FOOD){
                                                FoundFood = true;
                                                closeFoodX = xx;
                                                closeFoodY = yy;
                                                Dist = x;
                                                if (Debugging) state.output.warning("Found some food in Sniffer at ("+xx+","+yy+").");
                                                if (Debugging) state.output.warning("Ant at location ("+p.posx+","+p.posy+") facing "+p.orientation+".");
                                        }
                                }
                        }
                }
                if (Dist == 1 && closeFoodY!=p.posy){
                        if (p.map[(p.posx+p.maxx-1)%p.maxx][p.posy] == p.FOOD){
                                closeFoodX = (p.posx+p.maxx-1)%p.maxx;
                                closeFoodY = p.posy;                  
                        }
                        else if (p.map[(p.posx+p.maxx+1)%p.maxx][p.posy] == p.FOOD){
                                closeFoodX = (p.posx+p.maxx+1)%p.maxx;
                                closeFoodY = p.posy;                  
                        }
                }
		break;

            case Ant.O_RIGHT:
                for (int x=1;(x<=rangeX)&&(!FoundFood);x++){
                        for(int xx = p.posx+x;xx>=p.posx-x&&(!FoundFood);xx--){
                                for(int yy=p.posy-x;yy<=p.posy+x&&(!FoundFood);yy++){
                                        if (p.map[(xx+p.maxx)%p.maxx][(yy+p.maxy)%p.maxy] == p.FOOD){
                                                FoundFood = true;
                                                closeFoodX = xx;
                                                closeFoodY = yy;
                                                Dist = x;
                                                if (Debugging) state.output.warning("Found some food in Sniffer at ("+xx+","+yy+").");
                                                if (Debugging) state.output.warning("Ant at location ("+p.posx+","+p.posy+") facing "+p.orientation+".");
                                        }
                                }
                        }
                }
                if (Dist == 1 && closeFoodX!=p.posx){
                        if (p.map[p.posx][(p.posy+p.maxy-1)%p.maxy] == p.FOOD){                  
                                closeFoodX = p.posx;                  
                                closeFoodY = (p.posy+p.maxy-1)%p.maxy;
                        }
                        else if (p.map[p.posx][(p.posy+p.maxy+1)%p.maxy] == p.FOOD){
                                closeFoodX = p.posx;                  
                                closeFoodY = (p.posy+p.maxy+1)%p.maxy;
                        }
                }   
		break;

            default:  // whoa!
                state.output.fatal("Whoa, somehow I got a bad orientation! (" + p.orientation + ")");
                break;
            }

	//quadrant locations
	//   123
	//   405
	//   678
	if (closeFoodX == p.posx){
		if (closeFoodY < p.posy){
			Quad = 2;
		}
		else{
			Quad = 7;
		}
	}
	else if (closeFoodY == p.posy){
		if (closeFoodX < p.posx){
			Quad = 4;
		}
		else{
			Quad = 5;
		}
	}
	else if (closeFoodX < p.posx){
		if (closeFoodY < p.posy){
			Quad = 1;
		}
		else{
			Quad = 6;
		}
	}
	else if (closeFoodX > p.posx){
		if (closeFoodY < p.posy){
			Quad = 3;
		}
		else{
			Quad = 8;
		}
	}

	switch (p.orientation)
            {
            case Ant.O_UP:

                if (Debugging)  state.output.warning("Direction facing is up.  Food is in Quad "+Quad+".");
		if (Quad == 4 || Quad == 6){
			p.orientation = Ant.O_LEFT;
			p.moves++;
                        if (Debugging)  state.output.warning("Direction facing is up. Food should be in Quad 4 or 6. Food is in Quad "+Quad+".");
		}
		else if (Quad == 5 || Quad == 8){
			p.orientation = Ant.O_RIGHT;
			p.moves++;
                        if (Debugging)  state.output.warning("Direction facing is up. Food should be in Quad 5 or 8. Food is in Quad "+Quad+".");
		}
		else if (Quad == 7){
			p.orientation = Ant.O_DOWN;
			p.moves = p.moves + 2;
                        if (Debugging)  state.output.warning("Direction facing is up. Food should be in Quad 7. Food is in Quad "+Quad+".");
		}
		else{
               	 	if (p.map[p.posx][(p.posy-1+p.maxy)%p.maxy]==Ant.FOOD)            
                    		children[0].eval(state,thread,input,stack,individual,problem); 
               	 	else children[1].eval(state,thread,input,stack,individual,problem);
		}
                break;

            case Ant.O_LEFT:

               if (Debugging)  state.output.warning("Direction facing is left.  Food is in Quad "+Quad+".");
               if (Quad == 2 || Quad == 3){
                        p.orientation = Ant.O_UP;
                        p.moves++;
			if (Debugging) state.output.warning("Direction facing is left. Food should be in Quad 2 or 3. Food is in Quad "+Quad+".");
                }
                else if (Quad == 7 || Quad == 8){
                        p.orientation = Ant.O_DOWN;
                        p.moves++;
			if (Debugging) state.output.warning("Direction facing is left. Food should be in Quad 7 or 8. Food is in Quad "+Quad+".");
                }
                else if (Quad == 5){
                        p.orientation = Ant.O_RIGHT;
                        p.moves = p.moves + 2;
			if (Debugging) state.output.warning("Direction facing is left. Food should be in Quad 5. Food is in Quad "+Quad+".");
                }
                else{             
                	if (p.map[(p.posx-1+p.maxx)%p.maxx][p.posy]==Ant.FOOD)
                    		children[0].eval(state,thread,input,stack,individual,problem);
                	else children[1].eval(state,thread,input,stack,individual,problem);
                }		
                break;

            case Ant.O_DOWN:

                if (Debugging) state.output.warning("Direction facing is down.  Food is in Quad "+Quad+".");
               if (Quad == 1 || Quad == 4){
                        p.orientation = Ant.O_LEFT;
                        p.moves++;
			if (Debugging) state.output.warning("Direction facing is down. Food should be in Quad 1 or 4. Food is in Quad "+Quad+".");
                }
                else if (Quad == 3 || Quad == 5){
                        p.orientation = Ant.O_RIGHT;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is down. Food should be in Quad 3 or 5. Food is in Quad "+Quad+".");
                }
                else if (Quad == 2){
                        p.orientation = Ant.O_UP;
                        p.moves = p.moves + 2;
                        if (Debugging) state.output.warning("Direction facing is down. Food should be in Quad 2. Food is in Quad "+Quad+".");
                }
                else{             
                	if (p.map[p.posx][(p.posy+1)%p.maxy]==Ant.FOOD)
                    		children[0].eval(state,thread,input,stack,individual,problem);
                	else children[1].eval(state,thread,input,stack,individual,problem);
                }		
                break;

            case Ant.O_RIGHT:
		if (Debugging) state.output.warning("Direction facing is right.  Food is in Quad "+Quad+".");
               if (Quad == 1 || Quad == 2){
                        p.orientation = Ant.O_UP;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is right. Food should be in Quad 1 or 2. Food is in Quad "+Quad+".");
                }
                else if (Quad == 6 || Quad == 7){
                        p.orientation = Ant.O_DOWN;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is right. Food should be in Quad 6 or 7. Food is in Quad "+Quad+".");
                }
                else if (Quad == 4){
                        p.orientation = Ant.O_LEFT;
                        p.moves = p.moves + 2;
                        if (Debugging) state.output.warning("Direction facing is right. Food should be in Quad 4. Food is in Quad "+Quad+".");
                }
                else{             
                	if (p.map[(p.posx+1)%p.maxx][p.posy]==Ant.FOOD)
                    		children[0].eval(state,thread,input,stack,individual,problem);
                	else children[1].eval(state,thread,input,stack,individual,problem);
                }
                break;
            default:  // whoa!
                state.output.fatal("Whoa, somehow I got a bad orientation! (" + p.orientation + ")");
                break;
            }
	 if (Debugging) state.output.warning ("Food Eaten is "+p.sum+".");
        }

    public void evalPrint(final EvolutionState state,
                          final int thread,
                          final GPData input,
                          final ADFStack stack,
                          final GPIndividual individual,
                          final Problem problem,
                          final int[][] map2)
        {
        Ant p = (Ant)problem;
        int rangeX = p.maxx/2;
        int rangeY = p.maxy/2;
        int closeFoodX=0;
        int closeFoodY=0;
	int Dist = 0;
        int Quad = 0;
        boolean FoundFood = false;
        boolean Debugging = false;

        //find nearest food
        switch (p.orientation)
            {         
            case Ant.O_UP:   
                for (int x=1;(x<=rangeX)&&(!FoundFood);x++){
                        for(int yy = p.posy-x;yy<=p.posy+x&&(!FoundFood);yy++){
                                for(int xx=p.posx-x;xx<=p.posx+x&&(!FoundFood);xx++){
                                        if (p.map[(xx+p.maxx)%p.maxx][(yy+p.maxy)%p.maxy] == p.FOOD){
                                                FoundFood = true;
                                                closeFoodX = xx;
                                                closeFoodY = yy;
                                                Dist = x;
                                                if (Debugging) state.output.warning("Found some food in Sniffer at ("+xx+","+yy+").");
                                                if (Debugging) state.output.warning("Ant at location ("+p.posx+","+p.posy+") facing "+p.orientation+".");
                                        }
                                }
                        }
                }
                if (Dist == 1 && closeFoodY!=p.posy){
                        if (p.map[(p.posx+p.maxx-1)%p.maxx][p.posy] == p.FOOD){
                                closeFoodX = (p.posx+p.maxx-1)%p.maxx;
                                closeFoodY = p.posy;                  
                        }
                        else if (p.map[(p.posx+p.maxx+1)%p.maxx][p.posy] == p.FOOD){
                                closeFoodX = (p.posx+p.maxx+1)%p.maxx;
                                closeFoodY = p.posy;                                
                        }
                }        
                break;   
            case Ant.O_LEFT:
                for (int x=1;(x<=rangeX)&&(!FoundFood);x++){
                        for(int xx = p.posx-x;xx<=p.posx+x&&(!FoundFood);xx++){
                                for(int yy=p.posy-x;yy<=p.posy+x&&(!FoundFood);yy++){
                                        if (p.map[(xx+p.maxx)%p.maxx][(yy+p.maxy)%p.maxy] == p.FOOD){
                                                FoundFood = true;
                                                closeFoodX = xx;
                                                closeFoodY = yy;
                                                Dist = x;
                                                if (Debugging) state.output.warning("Found some food in Sniffer at ("+xx+","+yy+").");
                                                if (Debugging) state.output.warning("Ant at location ("+p.posx+","+p.posy+") facing "+p.orientation+".");
                                        }
                                }
                        }
                }
                if (Dist == 1 && closeFoodX!=p.posx){
                        if (p.map[p.posx][(p.posy+p.maxy-1)%p.maxy] == p.FOOD){
                                closeFoodX = p.posx;
                                closeFoodY = (p.posy+p.maxy-1)%p.maxy;
                        }
                        else if (p.map[p.posx][(p.posy+p.maxy+1)%p.maxy] == p.FOOD){
                                closeFoodX = p.posx;
                                closeFoodY = (p.posy+p.maxy+1)%p.maxy;
                        }
                }
                break;
            case Ant.O_DOWN: 
                for (int x=1;(x<=rangeX)&&(!FoundFood);x++){
                        for(int yy = p.posy+x;yy>=p.posy-x&&(!FoundFood);yy--){
                                for(int xx=p.posx-x;xx<=p.posx+x&&(!FoundFood);xx++){
                                        if (p.map[(xx+p.maxx)%p.maxx][(yy+p.maxy)%p.maxy] == p.FOOD){
                                                FoundFood = true;
                                                closeFoodX = xx;
                                                closeFoodY = yy;
                                                Dist = x;
                                                if (Debugging) state.output.warning("Found some food in Sniffer at ("+xx+","+yy+").");
                                                if (Debugging) state.output.warning("Ant at location ("+p.posx+","+p.posy+") facing "+p.orientation+".");
                                        }
                                }
                        }
                }
                if (Dist == 1 && closeFoodY!=p.posy){
                        if (p.map[(p.posx+p.maxx-1)%p.maxx][p.posy] == p.FOOD){
                                closeFoodX = (p.posx+p.maxx-1)%p.maxx;
                                closeFoodY = p.posy;
                        }
                        else if (p.map[(p.posx+p.maxx+1)%p.maxx][p.posy] == p.FOOD){
                                closeFoodX = (p.posx+p.maxx+1)%p.maxx;              
                                closeFoodY = p.posy;
                        }
                }        
                break;
            case Ant.O_RIGHT:
                for (int x=1;(x<=rangeX)&&(!FoundFood);x++){
                        for(int xx = p.posx+x;xx>=p.posx-x&&(!FoundFood);xx--){
                                for(int yy=p.posy-x;yy<=p.posy+x&&(!FoundFood);yy++){
                                        if (p.map[(xx+p.maxx)%p.maxx][(yy+p.maxy)%p.maxy] == p.FOOD){
                                                FoundFood = true;
                                                closeFoodX = xx;
                                                closeFoodY = yy;
                                                Dist = x;
                                                if (Debugging) state.output.warning("Found some food in Sniffer at ("+xx+","+yy+").");
                                                if (Debugging) state.output.warning("Ant at location ("+p.posx+","+p.posy+") facing "+p.orientation+".");
                                        }
                                }
                        }
                }
                if (Dist == 1 && closeFoodX!=p.posx){
                        if (p.map[p.posx][(p.posy+p.maxy-1)%p.maxy] == p.FOOD){
                                closeFoodX = p.posx;
                                closeFoodY = (p.posy+p.maxy-1)%p.maxy;
                        }
                        else if (p.map[p.posx][(p.posy+p.maxy+1)%p.maxy] == p.FOOD){
                                closeFoodX = p.posx;
                                closeFoodY = (p.posy+p.maxy+1)%p.maxy;
                        }
                }
                break;

            default:  // whoa!
                state.output.fatal("Whoa, somehow I got a bad orientation! (" + p.orientation + ")");
                break;
            }

        //quadrant locations
        //   123
        //   405
        //   678
        if (closeFoodX == p.posx){
                if (closeFoodY < p.posy){
                        Quad = 2;
                }
                else{
                        Quad = 7;
                }
        }
        else if (closeFoodY == p.posy){
                if (closeFoodX < p.posx){
                        Quad = 4;
                }
                else{
                        Quad = 5;
                }
        }
      else if (closeFoodX < p.posx){
                if (closeFoodY < p.posy){
                        Quad = 1; 
                }
                else{
                        Quad = 6;   
                }
        }
        else if (closeFoodX > p.posx){
                if (closeFoodY < p.posy){
                        Quad = 3;
                }
                else{
                        Quad = 8;
                }
        }             
       switch (p.orientation)
            {
            case Ant.O_UP:

                if (Debugging)  state.output.warning("Direction facing is up.  Food is in Quad "+Quad+".");
                if (Quad == 4 || Quad == 6){
                        p.orientation = Ant.O_LEFT;
                        p.moves++;
                        if (Debugging)  state.output.warning("Direction facing is up. Food should be in Quad 4 or 6. Food is in Quad "+Quad+".");
                }
                else if (Quad == 5 || Quad == 8){
                        p.orientation = Ant.O_RIGHT;
                        p.moves++;
                        if (Debugging)  state.output.warning("Direction facing is up. Food should be in Quad 5 or 8. Food is in Quad "+Quad+".");
                }
                else if (Quad == 7){          
                        p.orientation = Ant.O_DOWN;
                        p.moves = p.moves + 2;
                        if (Debugging)  state.output.warning("Direction facing is up. Food should be in Quad 7. Food is in Quad "+Quad+".");
                }
                else{
                if (p.map[p.posx][(p.posy-1+p.maxy)%p.maxy]==Ant.FOOD)
                    ((EvalPrint)children[0]).evalPrint(state,thread,input,stack,individual,problem,map2);
                else ((EvalPrint)children[1]).evalPrint(state,thread,input,stack,individual,problem,map2);
                }
                break;                     

            case Ant.O_LEFT:

               if (Debugging)  state.output.warning("Direction facing is left.  Food is in Quad "+Quad+".");
               if (Quad == 2 || Quad == 3){      
                        p.orientation = Ant.O_UP;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is left. Food should be in Quad 2 or 3. Food is in Quad "+Quad+".");
                }
                else if (Quad == 7 || Quad == 8){
                        p.orientation = Ant.O_DOWN;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is left. Food should be in Quad 7 or 8. Food is in Quad "+Quad+".");
                }
                else if (Quad == 5){          
                        p.orientation = Ant.O_RIGHT;
                        p.moves = p.moves + 2;
                        if (Debugging) state.output.warning("Direction facing is left. Food should be in Quad 5. Food is in Quad "+Quad+".");
                }
                else{
                if (p.map[(p.posx-1+p.maxx)%p.maxx][p.posy]==Ant.FOOD)
                    ((EvalPrint)children[0]).evalPrint(state,thread,input,stack,individual,problem,map2);
                else ((EvalPrint)children[1]).evalPrint(state,thread,input,stack,individual,problem,map2);
                }
                break;

            case Ant.O_DOWN:

                if (Debugging) state.output.warning("Direction facing is down.  Food is in Quad "+Quad+".");
               if (Quad == 1 || Quad == 4){
                        p.orientation = Ant.O_LEFT;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is down. Food should be in Quad 1 or 4. Food is in Quad "+Quad+".");
                }
                else if (Quad == 3 || Quad == 5){
                        p.orientation = Ant.O_RIGHT;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is down. Food should be in Quad 3 or 5. Food is in Quad "+Quad+".");
                }
                else if (Quad == 2){
                        p.orientation = Ant.O_UP;
                        p.moves = p.moves + 2;
                        if (Debugging) state.output.warning("Direction facing is down. Food should be in Quad 2. Food is in Quad "+Quad+".");
                }
                else{
                if (p.map[p.posx][(p.posy+1)%p.maxy]==Ant.FOOD)
                    ((EvalPrint)children[0]).evalPrint(state,thread,input,stack,individual,problem,map2);
                else ((EvalPrint)children[1]).evalPrint(state,thread,input,stack,individual,problem,map2);
                }
                break;

            case Ant.O_RIGHT:
                if (Debugging) state.output.warning("Direction facing is right.  Food is in Quad "+Quad+".");
               if (Quad == 1 || Quad == 2){
                        p.orientation = Ant.O_UP;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is right. Food should be in Quad 1 or 2. Food is in Quad "+Quad+".");
                }
                else if (Quad == 6 || Quad == 7){
                        p.orientation = Ant.O_DOWN;
                        p.moves++;
                        if (Debugging) state.output.warning("Direction facing is right. Food should be in Quad 6 or 7. Food is in Quad "+Quad+".");
                }
                else if (Quad == 4){
                        p.orientation = Ant.O_LEFT;
                        p.moves = p.moves + 2;
                        if (Debugging) state.output.warning("Direction facing is right. Food should be in Quad 4. Food is in Quad "+Quad+".");
                }
                else{
                if (p.map[(p.posx+1)%p.maxx][p.posy]==Ant.FOOD)
                    ((EvalPrint)children[0]).evalPrint(state,thread,input,stack,individual,problem,map2);
                else ((EvalPrint)children[1]).evalPrint(state,thread,input,stack,individual,problem,map2);
                }
                break;
            default:  // whoa!
                state.output.fatal("Whoa, somehow I got a bad orientation! (" + p.orientation + ")");
                break;
            }
         if (Debugging) state.output.warning ("Food Eaten is "+p.sum+".");
        }
    }

