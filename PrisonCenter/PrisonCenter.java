import java.util.Scanner;
import java.util.Arrays;

public class PrisonCenter
{
    public static final int gMAX_PRISONER = 200;
    public static final int gMAX_CENTERS = 12;
    
    public static int[] gPrisoner = new int[gMAX_CENTERS];
    public static int gCenterCounter;
//
public static void clear(int[] gPrisoner){Arrays.fill(gPrisoner, 0);
    return;
}
//
public static void list(int[] gPrisoner){System.out.println("List of Prisoner Detention Center Occupancy");
    for(int i = 0; i < gCenterCounter; i++){System.out.println("Center["+i+"] : "+gPrisoner[i]);}
    return;
}
//
public static void addSub(int[] gPrisoner){
    int index = gCenterCounter - 1;
    int j;
    int movingPrisoners;
    char answer = 'V';
    Scanner gSCANNER = new Scanner (System.in);
    
    System.out.println("Enter the index (0 to "+index+") :");
	j = gSCANNER.nextInt();
	
	while(j < 0 || j > index){System.out.println("ERROR, you need to enter a valid value based on the next message.");
	    	System.out.println("Enter the index (0 to "+index+") :");
	    	j = gSCANNER.nextInt();}
	    	
	System.out.println("The current occupancy of the center at index "+j+" is : "+gPrisoner[j]);
	System.out.println("Enter the number of prisoners to move (0 - 200) :");
	movingPrisoners = gSCANNER.nextInt();
	
	while(movingPrisoners < 0 || movingPrisoners > 200){System.out.println("ERROR, you need to enter a valid value based on the next message.");
	    	System.out.println("The current occupancy of the center at index "+j+" is : "+gPrisoner[j]);
        	System.out.println("Enter the number of prisoners to move (0 - 200) :");
        	movingPrisoners = gSCANNER.nextInt();}
        	
    System.out.println("Are the prisoners added to the center at index "+j+"? (Y/N):");    	
    answer = gSCANNER.next().charAt(0);
    
    while(answer != 'Y' && answer != 'N'){System.out.println("ERROR, you need to enter a valid value based on the next message.");
        System.out.println("Are the prisoners added to the center at index "+j+"? (Y/N):");    	
        answer = gSCANNER.next().charAt(0);
        }
        
    while(answer == 'N' && gPrisoner[j] < movingPrisoners){System.out.println("ERROR, you can't subtract more prisoners than the inmates at center at index "+j+". Try again");
    answer = gSCANNER.next().charAt(0);
        }
        
    if(answer == 'Y'){gPrisoner[j] += movingPrisoners;}
    
    else if(answer == 'N'){gPrisoner[j] -= movingPrisoners;}

    return;
}
//
public static void append(int[] copy){
    int movingPrisoners;
    int newCenter = gCenterCounter + 1;
    Scanner gSCANNER = new Scanner (System.in);
    
    if(gMAX_CENTERS < newCenter){System.out.println("The database is full, no more centers can be added");
        return;}
    
    Arrays.copyOf(gPrisoner, gCenterCounter);
    int[] newPrisoner = new int[newCenter];
    
    for(int i = 0; i < gCenterCounter; i++){newPrisoner[i] = copy[i];}
    
    System.out.println("Enter the number of prisoners to assign to the new center (0 - 200) :");
	movingPrisoners = gSCANNER.nextInt();
	
	while(movingPrisoners < 0 || movingPrisoners > 200){System.out.println("ERROR, you need to enter a valid value based on the next message.");
    System.out.println("Enter the number of prisoners to assign to the new center (0 - 200) :");
    movingPrisoners = gSCANNER.nextInt();}
    
    newPrisoner[newCenter - 1] = movingPrisoners;
    gPrisoner = newPrisoner;
    gCenterCounter = newCenter;
    return;
}

//
public static void analysis(int[] gPrisoner){
    System.out.println("Occupancy Classification Summary.");
    int j = 0;
    int classification = 0;
    int under = 0, just = 0, close = 0, full = 0, over = 0;
    
    while(j < gCenterCounter){
        
    for(int i = 0; i < 5; i++){
        if(getPrisonersByClass(gPrisoner[j], i) == true && i == 0){under++;}
        else if(getPrisonersByClass(gPrisoner[j], i) == true && i == 1){just++;}
        else if(getPrisonersByClass(gPrisoner[j], i) == true && i == 2){close++;}
        else if(getPrisonersByClass(gPrisoner[j], i) == true && i == 3){full++;}
        else if(getPrisonersByClass(gPrisoner[j], i) == true && i == 4){over++;}
    }
    
    j++;
    }
    System.out.println("Under capacity        :"+under);
    System.out.println("Just right            :"+just);
    System.out.println("Close to over capacity:"+close);
    System.out.println("Full                  :"+full);
    System.out.println("Over capacity         :"+over);
    
    return;
}
//
public static boolean getPrisonersByClass(int prisoner, int i){
    
    for(int key = 0; key < 5; key++){
    if(prisoner < 51 && i == 0){return true;}
    else if (prisoner > 50 && prisoner < 151 && i == 1){return true;}
    else if (prisoner > 150 && prisoner < 200 && i == 2){return true;}
    else if (prisoner == 200 && i == 3){return true;}
    else if (prisoner > 200 && i == 4){return true;}
    }  
    return false;    
}
//
public static void main (String[]args)
  {
	Scanner gSCANNER = new Scanner (System.in);
    int choice = 0;
    
	System.out.println("Please, enter the initial number of detention centers in the database (Max 12)");
	gCenterCounter = gSCANNER.nextInt();
	gPrisoner = new int[gCenterCounter];
	
	while(gCenterCounter == 0 || gCenterCounter > 12){System.out.println("ERROR, you need to enter a valid value based on the next message.");
	    	System.out.print("Please, enter the initial number of detention centers in the database (Max 12)");
	    	gCenterCounter = gSCANNER.nextInt();
	        gPrisoner = new int[gCenterCounter];
	}
do{
    System.out.println("\nMAIN MENU");
	System.out.println("0 - Clear centers, 1 - List centers, 2 - Add/Subtract prisioners, 3 - Add new center, 4 - Center analysis, 5 - Exit");
	System.out.println("Select an option: ");
	choice = gSCANNER.nextInt();
	
	while(choice < 0 || choice > 5){System.out.println("ERROR, you need to enter a valid value based on the next message.\n");
	System.out.println("MAIN MENU");
	System.out.println("0 - Clear centers, 1 - List centers, 2 - Add/Subtract prisioners, 3 - Add new center, 4 - Center analysis, 5 - Exit");
	System.out.println("Select an option: ");
	choice = gSCANNER.nextInt();}
	        
	if (choice == 0){clear(gPrisoner);}
	
	else if (choice == 1){list(gPrisoner);}
	
    else if (choice == 2){addSub(gPrisoner);}
	
	else if (choice == 3){append(gPrisoner);}
	
	else if (choice == 4){analysis(gPrisoner);} 
}while(choice != 5);

  }
}
