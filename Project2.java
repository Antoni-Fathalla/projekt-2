import java.util.Scanner;

public class Project2 {
    static Scanner scan = new Scanner(System.in);
    static int[] sittplatser = new int[21];
    static String[] namn = new String [21];
    static String[] kön = new String [21];
    

    
    public static void main (String[] args){ 
        startmeny();
     }

    //alternativ meny
     static void startmeny() {
        while (true){  
            System.out.println(); 
            meny(); 
            int x = scan.nextInt();
            switch (x){
                case 1:
                    bokning();
                    break;
                case 2:
                    ledigaplatser(sittplatser);
                    break;
                case 3:
                    System.out.println(Vinst(0));
                    break;
                case 4:
                    Avbokning();
                    break;
                case 5: 
                    Hittaplats();
                    break;
                default: 
            }
        }
    }

    //meny
    static void meny(){
        System.out.println("Meny");
        System.out.println("[1] Boka biljett");
        System.out.println("[2] Antal lediga platser");
        System.out.println("[3] Beräkna vinsten av sålda biljetter");
        System.out.println("[4] Avboka biljett");
        System.out.println("[5] Hitta din bokning");
    }

    
    //bokningssystemet
    static void bokning(){
        int ar;
        int månad;
        int dag;
        String personnummer="";
        int omvandla=0;


        while (true){
            try{
                System.out.println("Skriv ditt personnummer: ");
                int sittplats = scan.nextInt();
                personnummer = Integer.toString(sittplats);

                String nu1 = personnummer.substring(0, 4);
                String nu2 = personnummer.substring(4, 6);
                String nu3 = personnummer.substring(6, 8);

                ar = Integer.parseInt(nu1);
                månad = Integer.parseInt(nu2);
                dag = Integer.parseInt(nu3);
            }catch(Exception e){
                System.out.println("Använd 8 siffror");
                scan.nextLine();
                scan.nextLine();
                continue;
            }
            if (månad > 12){
                System.out.println("INVALID INPUT");
                continue;
            }else if (dag > 31){
                System.out.println("INVALID INPUT");
                continue;
            }else if (ar > 2023){
                System.out.println("INVALID INPUT");
                continue;
            }
            omvandla = Integer.parseInt(personnummer);
            break;
        }
    

        for(int i=0; i<sittplatser.length; i++){
            if (sittplatser[i] == 0){
                sittplatser[i] = omvandla;
                System.out.println("Du har plats "+(i+1));
                break;
            }
        }
        System.out.println("Bekräfta bokningen genom att trycka enter");
        scan.nextLine();
        scan.nextLine();
    }

    //kollar lediga platser
    /*sätter antal lediga platser till 0, därefter loppar man igenom platserna. Om platsen är lika med 0 ökar antalet platser med 1.
      om antal platser är lika med 0 printas det ut att bussen är full och annars så printas antalet tillängliga platser
      alltså de somhar värdet 1.
    */
    static void ledigaplatser (int [] sittplatser){
        int ledigaplatser = 0;

        for (int i = 0; i<sittplatser.length; i++){
            if(sittplatser[i] == 0){
                ledigaplatser++;
            }
        }
        if (ledigaplatser == 0){
            System.out.println("bussen är full");
        }else{
            System.out.println("\nTillängliga platser: "+ledigaplatser);
        }

        System.out.println("Tryck enter för att gå tillbaka till menyn");
        scan.nextLine();
        scan.nextLine();
    }

    //Beräkna vinst
    /*först kollar jag om index är 21 så returnas 0 för att avsluta recrutionen. sedan tar jag in åldern från platsernas index 
    och därefter om personnummret är 0 så anropar jag vinst metoden igen med nästa index och adderar med 0 för att få resultatet.
    om personnummret inte är 0 tar jag ut åldern från personnummret och sedan plockar jag fram priset för åldern 
    slutligen anorpas den igen med nästa index och adderar resultatet med priset och returnerar resultatet.*/
    
    static double Vinst (int index){
        if(index==21)return 0;

        int personnummer = sittplatser[index];

        if(personnummer == 0)return 0 + Vinst(index + 1);

        String årString = Integer.toString(personnummer).substring(0, 4);
        int år = Integer.parseInt(årString);

        double pris = 0;
        if (år<=2005 && år>1963)pris=299.9;
        else if (år>2005)pris=149.9;
        else if (år<=1963)pris=199.9;
        return pris + Vinst(index + 1); 
}

    //Avbokning
    static void Avbokning(){
        System.out.println("Ange personnummer för avbokning: ");
        int personnummer = scan.nextInt();

        for (int i = 0; i<sittplatser.length; i++){
            if(sittplatser[i] == personnummer){
                sittplatser[i] = 0;
                System.out.println("Nu är din plats avbokad");
                scan.nextLine();
                scan.nextLine();
                return;
            }   
        }
        System.out.println("Du har ingen bokning");
        scan.nextLine();
        scan.nextLine();
      
    }

    static int Hittaplats(){
       
        System.out.println("Ange personnummer för att hitta din plats: ");
        int personnummer = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < sittplatser.length; i++){
            if (sittplatser[i] == personnummer){
                System.out.println("du har bokad plats: "+(i+1));
                scan.nextLine();
                return i;
            }   
        }
        System.out.println("inga bokade platser med detta personnummer!!!");
        scan.nextLine();
        return -1;
    }
}